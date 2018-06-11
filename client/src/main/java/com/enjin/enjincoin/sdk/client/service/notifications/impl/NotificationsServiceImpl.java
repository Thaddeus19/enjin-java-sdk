package com.enjin.enjincoin.sdk.client.service.notifications.impl;

import com.enjin.enjincoin.sdk.client.enums.NotificationType;
import com.enjin.enjincoin.sdk.client.service.GraphQLKeys;
import com.enjin.enjincoin.sdk.client.service.GraphQLResponse;
import com.enjin.enjincoin.sdk.client.service.notifications.EventMatcher;
import com.enjin.enjincoin.sdk.client.service.notifications.NotificationListener;
import com.enjin.enjincoin.sdk.client.service.notifications.NotificationListenerRegistration;
import com.enjin.enjincoin.sdk.client.service.notifications.NotificationsService;
import com.enjin.enjincoin.sdk.client.service.notifications.ThirdPartyNotificationService;
import com.enjin.enjincoin.sdk.client.service.platform.PlatformService;
import com.enjin.enjincoin.sdk.client.service.platform.vo.PlatformResponseBody;
import com.enjin.java_commons.BooleanUtils;
import com.enjin.java_commons.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * <p>
 * NotificationsService - Synchronous.
 * </p>
 */
public class NotificationsServiceImpl implements NotificationsService {

    /**
     * Logger used by this class.
     */
    private static final Logger LOGGER = Logger.getLogger(NotificationsServiceImpl.class.getName());

    /**
     * Local variable for the third party notification service.
     */
    private ThirdPartyNotificationService thirdPartyNotificationService;

    /**
     * Local variable holding all the notification listeners.
     */
    private List<NotificationListenerRegistration> notificationListeners = new ArrayList<>();

    /**
     * Local variable for the platformService.
     */
    private PlatformService service;

    private int clientId;

    /**
     * Class constructor.
     *
     * @param service - the platform service to use
     * @param clientId - the app id to use
     */
    public NotificationsServiceImpl(final PlatformService service, final String clientId) {
       this.service = service;
       this.clientId = Integer.parseInt(clientId);
    }

    /**
     * Method to initialize the notifications service.
     *
     * @return boolean
     */
    @Override
    public boolean start() {

        //Call out to the reinitialize method in order to initialize the pusher notifications
        return this.restart();
    }


    /**
     * Method to re-initialize the notifications service.
     *
     * @return boolean
     */
    @Override
    public boolean restart() {
        boolean initResult = false;

        final Response<GraphQLResponse> platformDetails;
        try {
            platformDetails = this.service.getPlatformSync();
            if (platformDetails == null || platformDetails.body() == null) {
                LOGGER.warning("Failed to get platform details");
                return initResult;
            }

            final PlatformResponseBody body = parseJsonElement(platformDetails.body());
            if (body == null) {
                LOGGER.warning("Failed to get platform details");
                return initResult;
            }

            // Setup the thirdPartyNotificationService to use the pusher service.
            if (this.thirdPartyNotificationService == null) {
                this.thirdPartyNotificationService = new PusherNotificationServiceImpl(body, this.clientId);
            }

            //boolean initPusherResult = this.thirdPartyNotificationService.init(platformAuthDetailsResponseVO);
            final boolean initPusherResult = this.thirdPartyNotificationService.init();
            if (BooleanUtils.isFalse(initPusherResult)) {
                LOGGER.warning("A problem occured initializing the pusher library");
                return initResult;
            }
            initResult = initPusherResult;
        } catch (final IOException e) {
            LOGGER.warning(String.format("An IOException has occured. Exception: %s.", e.getMessage()));
        }


        return initResult;
    }

    @Override
    public void shutdown() {
        if (this.thirdPartyNotificationService != null) {
            this.thirdPartyNotificationService.shutdown();
        }
    }

    /**
     * Method to configure a listener.
     *
     * @param listener notificationListener to configer
     *
     * @return NotificationListenerRegistration
     */
    @SuppressWarnings("rawtypes")
    @Override
    public NotificationListenerRegistration.RegistrationListenerConfiguration configureListener(final NotificationListener listener) {
        return NotificationListenerRegistration.configure(this, listener);
    }

    /**
     * Method to add a notification listener.
     *
     * @param listener - listener to add
     *
     * @return NotificationListenerRegistration
     */
    @Override
    public synchronized NotificationListenerRegistration addNotificationListener(final NotificationListener listener) {
        NotificationListenerRegistration registration = null;
        if (ObjectUtils.isNull(listener)) {
            LOGGER.warning("Could not add a NotificationListener because it was null.");
            return null;
        } else {
            final long count = this.notificationListeners.stream().filter(r -> r.getListener() == listener).count();

            if (count == 0) {
                registration = NotificationListenerRegistration.configure(this, listener).register();
            } else {
                LOGGER.warning("Could not add a NotificationListener because it was already registered.");
            }
        }
        return registration;
    }

    /**
     * Method to add a notification listener.
     *
     * @param listener     the listener to add
     * @param eventMatcher to match against
     *
     * @return NotificationListenerRegistration
     */
    @Override
    public NotificationListenerRegistration addNotificationListener(final NotificationListener listener, final EventMatcher eventMatcher) {
        return this.configureListener(listener).withMatcher(eventMatcher).register();
    }

    /**
     * Method to configure a listener for allowed types.
     *
     * @param listener to configure
     * @param allowed  types
     *
     * @return NotificationListenerRegistration
     */
    @Override
    public NotificationListenerRegistration addAllowedTypesNotificationListener(final NotificationListener listener,
                                                                                final NotificationType... allowed) {
        return this.configureListener(listener).withAllowedEvents(allowed).register();
    }

    /**
     * Method to configure a listener for ignore types.
     *
     * @param listener to configure
     * @param ignored  types
     *
     * @return NotificationListenerRegistration
     */
    @Override
    public NotificationListenerRegistration addIgnoredTypesNotificationListener(final NotificationListener listener,
                                                                                final NotificationType... ignored) {
        return this.configureListener(listener).withIgnoredEvents(ignored).register();
    }

    /**
     * Method to remove a notification listener.
     *
     * @param listener - listener to remove
     */
    @Override
    public synchronized void removeNotificationListener(final NotificationListener listener) {
        if (ObjectUtils.isNull(listener)) {
            LOGGER.warning("Could not remove a NotificationListener because it was null.");
            return;
        }

        final List<NotificationListenerRegistration> matching = this.notificationListeners.stream()
                .filter(registration -> registration.getListener() == listener)
                .collect(Collectors.toList());

        if (matching.size() > 0) {
            matching.forEach(this::removeNotificationListenerRegistration);
            // thirdPartyNotificationService.setNotificationListeners(notificationListeners);
        } else {
            LOGGER.warning("Could not remove a NotificationListener because it wasn't already registered.");
        }
    }

    /**
     * Method to register a notificationListener.
     *
     * @param registration to add
     */
    @Override
    public void addNotificationListenerRegistration(final NotificationListenerRegistration registration) {
        if (registration != null) {
            this.notificationListeners.add(registration);
            this.thirdPartyNotificationService.setNotificationListeners(this.notificationListeners);
        } else {
            LOGGER.warning("Could not add a NotificationListenerRegistration because it was null.");
        }
    }

    /**
     * Method to remove a notificationListener.
     *
     * @param registration to remove
     */
    @Override
    public void removeNotificationListenerRegistration(final NotificationListenerRegistration registration) {
        if (registration != null) {
            this.notificationListeners.remove(registration);
            this.thirdPartyNotificationService.setNotificationListeners(this.notificationListeners);
        } else {
            LOGGER.warning("Could not add a NotificationListenerRegistration because it was null.");
        }
    }

    @Override
    public void startAsync(final CompletableFuture<Boolean> future) {
        this.restartAsync(future);
    }

    @Override
    public void restartAsync(final CompletableFuture<Boolean> future) {
        this.service.getPlatformAsync(new Callback<GraphQLResponse>() {
            @Override
            public void onResponse(final Call<GraphQLResponse> call, final Response<GraphQLResponse> response) {
                boolean result = false;
                if (response.isSuccessful()) {
                    final GraphQLResponse body = response.body();

                    shutdown();

                    final PlatformResponseBody platformResponseBody = parseJsonElement(body);
                    NotificationsServiceImpl.this.thirdPartyNotificationService =
                            new PusherNotificationServiceImpl(platformResponseBody,
                                    NotificationsServiceImpl.this.clientId);
                    result = NotificationsServiceImpl.this.thirdPartyNotificationService.init();
                }
                future.complete(result);
            }

            @Override
            public void onFailure(final Call<GraphQLResponse> call, final Throwable t) {
                LOGGER.warning("An error occurred while retrieving platform details.");
            }
        });
    }

    private PlatformResponseBody parseJsonElement(final GraphQLResponse response) {
        PlatformResponseBody body = null;
        if (response.isSuccessful()) {
            JsonObject data = response.data();
            if (data != null && data.has(GraphQLKeys.PLATFORM_QUERY_KEY)) {
                final Gson gson = new GsonBuilder()
                        .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                        .create();
                body = gson.fromJson(data.get(GraphQLKeys.PLATFORM_QUERY_KEY), PlatformResponseBody.class);
            }
        }
        return body;
    }
}