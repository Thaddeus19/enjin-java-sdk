package com.enjin.sdk.models.balance;

import com.enjin.sdk.models.PaginationRequest;

import java.util.List;

public class GetBalances extends PaginationRequest<GetBalances> implements BalanceFragment<GetBalances> {

    public GetBalances appIds(List<Integer> ids) {
        set("appIds", ids);
        return this;
    }

    public GetBalances ethAddress(String ethAddr) {
        set("ethAddress", ethAddr);
        return this;
    }

    public GetBalances tokenId(String tokenId) {
        set("tokenId", tokenId);
        return this;
    }

    public GetBalances value(Integer value) {
        set("value", value);
        return this;
    }

    public GetBalances valGt(Integer value) {
        set("valueGt", value);
        return this;
    }

    public GetBalances valGte(Integer value) {
        set("valueGte", value);
        return this;
    }

    public GetBalances valLt(Integer value) {
        set("valueLt", value);
        return this;
    }

    public GetBalances valLte(Integer value) {
        set("valueLte", value);
        return this;
    }

}