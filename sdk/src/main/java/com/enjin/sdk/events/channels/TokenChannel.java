package com.enjin.sdk.events.channels;

import com.enjin.sdk.models.Platform;
import lombok.NonNull;

/**
 * Represents a channel to subscribe to for Enjin Cloud token (item) events.
 *
 * @see com.enjin.sdk.events.NotificationsService
 */
public class TokenChannel implements Channel {

    private final Platform platform;
    private final String token;

    /**
     * Constructs a channel for the given network and token (item).
     *
     * @param platform the platform
     * @param token the token ID
     */
    public TokenChannel(@NonNull Platform platform, @NonNull String token) {
        this.platform = platform;
        this.token = token;
    }

    @Override
    public String channel() {
        return String.format("enjincloud.%s.token.%s",
                             platform.getNetwork().toLowerCase(),
                             token);
    }

}