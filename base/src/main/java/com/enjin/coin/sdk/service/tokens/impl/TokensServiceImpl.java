package com.enjin.coin.sdk.service.tokens.impl;

import com.enjin.coin.sdk.config.Config;
import com.enjin.coin.sdk.service.BaseService;
import com.enjin.coin.sdk.service.tokens.TokensService;
import com.enjin.coin.sdk.util.ArrayUtils;
import com.enjin.coin.sdk.util.Constants;
import com.enjin.coin.sdk.util.ObjectUtils;
import com.enjin.coin.sdk.util.StringUtils;
import com.enjin.coin.sdk.vo.token.GetTokenRequestVO;
import com.enjin.coin.sdk.vo.token.GetTokenResponseVO;
import com.enjin.coin.sdk.vo.token.ImmutableListTokensResponseVO;
import com.enjin.coin.sdk.vo.token.ListTokensRequestVO;
import com.enjin.coin.sdk.vo.token.ListTokensResponseVO;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Contains services related to tokens</p>
 */
public class TokensServiceImpl extends BaseService implements TokensService {

    private static final Logger LOGGER = Logger.getLogger(TokensServiceImpl.class.getName());

    /**
     * Class constructor
     *
     * @param enjinConfig - the enjinConfig to use
     */
    public TokensServiceImpl(Config enjinConfig) {
        super(enjinConfig);
    }

    public GetTokenResponseVO getToken(GetTokenRequestVO getTokenRequestVO) {
        GetTokenResponseVO getTokenResponseVO = null;

        if (ObjectUtils.isNull(getTokenRequestVO) || StringUtils.isEmpty(getTokenRequestVO.getTokenId())) {
            LOGGER.warning("getTokenRequestVO is null or tokenId passed in are null or empty");
            return getTokenResponseVO;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token_id", getTokenRequestVO.getTokenId().get());

        // Construct new request
        String method = Constants.METHOD_TOKENS_GET;

        getTokenResponseVO = (GetTokenResponseVO) jsonRpcUtils.sendJsonRpcRequest(getTokensUrl(), GetTokenResponseVO.class, method, params);

        return getTokenResponseVO;
    }

    public ListTokensResponseVO listTokens(ListTokensRequestVO listTokensRequestVO) {
        ListTokensResponseVO listTokensResponseVO = null;

        if (ObjectUtils.isNull(listTokensRequestVO) || StringUtils.isEmpty(listTokensRequestVO.getAppId())
                || StringUtils.isEmpty(listTokensRequestVO.getAfterTokenId())
                || StringUtils.isEmpty(listTokensRequestVO.getLimit())) {
            LOGGER.warning("listTokensRequestVO is null, appId, afterTokenId or limit passed in are null or empty");
            return listTokensResponseVO;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("app_id", listTokensRequestVO.getAppId().get());
        params.put("after_token_id", listTokensRequestVO.getAfterTokenId().get());
        params.put("limit", listTokensRequestVO.getLimit().get());

        // Construct new request
        String method = Constants.METHOD_TOKENS_LIST;

        GetTokenResponseVO[] getTokenResponseVOArray = (GetTokenResponseVO[]) jsonRpcUtils.sendJsonRpcRequest(getTokensUrl(), GetTokenResponseVO[].class, method, params);
        if (ArrayUtils.isEmpty(getTokenResponseVOArray)) {
            LOGGER.warning("No tokens returned");
            return listTokensResponseVO;
        }
        listTokensResponseVO = ImmutableListTokensResponseVO.builder()
                .setGetTokensResponseVOArray(getTokenResponseVOArray)
                .build();

        return listTokensResponseVO;
    }
}
