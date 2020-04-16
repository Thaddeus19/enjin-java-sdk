package com.enjin.sdk.services.token.impl;

import java.util.List;

import com.enjin.sdk.graphql.GraphQLRequest;
import com.enjin.sdk.graphql.GraphQLResponse;
import com.enjin.sdk.graphql.GraphQuery;
import com.enjin.sdk.models.token.Token;
import com.enjin.sdk.models.token.event.TokenEvent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokensRetrofitService {

    @POST("graphql")
    @GraphQuery("GetTokensPaginated")
    @Headers("Content-Type: application/json")
    Call<GraphQLResponse<List<Token>>> getTokens(@Body GraphQLRequest request);

    @POST("graphql")
    @GraphQuery("GetToken")
    @Headers("Content-Type: application/json")
    Call<GraphQLResponse<Token>> getToken(@Body GraphQLRequest request);

    @POST("graphql")
    @GraphQuery("GetTokenEventsPaginated")
    @Headers("Content-Type: application/json")
    Call<GraphQLResponse<List<TokenEvent>>> getTokenEvents(@Body GraphQLRequest request);

    @POST("graphql")
    @GraphQuery("InvalidateTokenMetadata")
    @Headers("Content-Type: application/json")
    Call<GraphQLResponse<Boolean>> invalidateTokenMetadata(@Body GraphQLRequest request);

}