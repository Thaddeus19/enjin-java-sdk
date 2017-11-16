package com.enjin.coin.sdk.service.transactionrequests.impl;

import java.util.concurrent.Future;

import com.enjin.coin.sdk.config.Config;
import com.enjin.coin.sdk.service.transactionrequests.TransactionRequestsAsyncService;
import com.enjin.coin.sdk.vo.transactionrequest.CancelTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.CancelTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.CreateTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.CreateTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.GetTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.GetTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.ListTransactionRequestsRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ListTransactionRequestsResponseVO;

/**
 * <p>Contains services related to transaction requests</p>
 */
public class TransactionRequestsAsyncServiceImpl extends TransactionRequestsServiceImpl implements TransactionRequestsAsyncService {

    /**
     * Class constructor
     *
     * @param enjinConfig - the enjinConfig to use
     */
    public TransactionRequestsAsyncServiceImpl(Config enjinConfig) {
        super(enjinConfig);
    }

    public Future<GetTransactionRequestResponseVO> getTransactionRequestAsync(GetTransactionRequestRequestVO getTransactionRequestRequestVO) {
        return executorService.submit(() -> getTransactionRequest(getTransactionRequestRequestVO));
    }

    public Future<ListTransactionRequestsResponseVO[]> listTransactionRequestsAsync(ListTransactionRequestsRequestVO listTransactionRequestsRequestVO) {
        return executorService.submit(() -> listTransactionRequests(listTransactionRequestsRequestVO));
    }

    public Future<CreateTransactionRequestResponseVO> createTransactionRequestAsync(CreateTransactionRequestRequestVO createTransactionRequestRequestVO) {
        return executorService.submit(() -> createTransactionRequest(createTransactionRequestRequestVO));
    }

    public Future<CancelTransactionRequestResponseVO> cancelTransactionRequestAsync(CancelTransactionRequestRequestVO cancelTransactionRequestRequestVO) {
        return executorService.submit(() -> cancelTransactionRequest(cancelTransactionRequestRequestVO));
    }

}