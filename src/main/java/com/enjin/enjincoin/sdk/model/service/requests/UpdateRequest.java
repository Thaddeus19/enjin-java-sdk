package com.enjin.enjincoin.sdk.model.service.requests;

import com.enjin.enjincoin.sdk.graphql.GraphQLRequest;
import com.enjin.enjincoin.sdk.model.service.requests.data.AddLogData;
import com.enjin.enjincoin.sdk.model.service.requests.data.AdvancedSendTokenData;
import com.enjin.enjincoin.sdk.model.service.requests.data.ApproveEnjData;
import com.enjin.enjincoin.sdk.model.service.requests.data.ApproveItemData;
import com.enjin.enjincoin.sdk.model.service.requests.data.BatchApproveData;
import com.enjin.enjincoin.sdk.model.service.requests.data.CompleteTradeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.CreateTokenData;
import com.enjin.enjincoin.sdk.model.service.requests.data.CreateTradeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.DecreaseMaxMeltFeeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.DecreaseMaxTransferFeeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.MeltTokenData;
import com.enjin.enjincoin.sdk.model.service.requests.data.MessageData;
import com.enjin.enjincoin.sdk.model.service.requests.data.MintTokenData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SendTokenData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetApprovalData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetApprovalForAllData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetItemUriData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetMeltFeeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetTransferFeeData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetTransferableData;
import com.enjin.enjincoin.sdk.model.service.requests.data.SetWhitelistedData;
import com.enjin.enjincoin.sdk.model.service.requests.data.UpdateItemNameData;

import java.math.BigInteger;

/**
 * A builder for updating a request on the Trusted platform.
 *
 * @author Evan Lindsay
 * @see com.enjin.enjincoin.sdk.service.requests.RequestsService
 */
public class UpdateRequest extends GraphQLRequest.Builder {

    /**
     * The id of the identity this request was created for.
     *
     * @param identityId the identity id.
     *
     * @return builder.
     */
    public UpdateRequest withIdentityId(BigInteger identityId) {
        withParameter("identity_id", identityId);
        return this;
    }

    /**
     * The transaction type.
     *
     * @param type the transaction type.
     *
     * @return the builder.
     */
    public UpdateRequest withType(TransactionType type) {
        withParameter("type", type);
        return this;
    }

    /**
     * Can be set to retry or cancel a transaction.
     *
     * @param rebroadcast the rebroadcast type.
     *
     * @return the builder.
     */
    public UpdateRequest withRebroadcast(RebroadcastType rebroadcast) {
        withParameter("rebroadcast", rebroadcast);
        return this;
    }

    /**
     * Sets the create token data.
     *
     * @param createTokenData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withCreateTokenData(CreateTokenData createTokenData) {
        withParameter("create_token_data", createTokenData);
        withType(TransactionType.CREATE);
        return this;
    }

    /**
     * Sets the create trade data.
     *
     * @param createTradeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withCreateTradeData(CreateTradeData createTradeData) {
        withParameter("create_trade_data", createTradeData);
        withType(TransactionType.CREATE_TRADE);
        return this;
    }

    /**
     * Sets the complete trade data.
     *
     * @param completeTradeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withCompleteTradeData(CompleteTradeData completeTradeData) {
        withParameter("complete_trade_data", completeTradeData);
        withType(TransactionType.COMPLETE_TRADE);
        return this;
    }

    /**
     * Sets the mint data.
     *
     * @param mintTokenData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withMintTokenData(MintTokenData mintTokenData) {
        withParameter("mint_token_data", mintTokenData);
        withType(TransactionType.MINT);
        return this;
    }

    /**
     * Sets the melt data.
     *
     * @param meltTokenData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withMeltTokenData(MeltTokenData meltTokenData) {
        withParameter("melt_token_data", meltTokenData);
        withType(TransactionType.MELT);
        return this;
    }

    /**
     * Sets the send token data.
     *
     * @param sendTokenData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSendTokenData(SendTokenData sendTokenData) {
        withParameter("send_token_data", sendTokenData);
        withType(TransactionType.SEND);
        return this;
    }

    /**
     * Sets the advanced send token data.
     *
     * @param advancedSendTokenData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withAdvancedSendTokenData(AdvancedSendTokenData advancedSendTokenData) {
        withParameter("advanced_send_token_data", advancedSendTokenData);
        withType(TransactionType.ADVANCED_SEND);
        return this;
    }

    /**
     * Sets the update item name data.
     *
     * @param updateItemNameData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withUpdateItemNameData(UpdateItemNameData updateItemNameData) {
        withParameter("update_item_name_data", updateItemNameData);
        withType(TransactionType.UPDATE_NAME);
        return this;
    }

    /**
     * Sets the set item uri data.
     *
     * @param setItemUriData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetItemUriData(SetItemUriData setItemUriData) {
        withParameter("set_item_uri_data", setItemUriData);
        withType(TransactionType.SET_ITEM_URI);
        return this;
    }

    /**
     * Sets the set whitelisted data.
     *
     * @param setWhitelistedData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetWhitelistedData(SetWhitelistedData setWhitelistedData) {
        withParameter("set_whitelisted_data", setWhitelistedData);
        withType(TransactionType.SET_WHITELISTED);
        return this;
    }

    /**
     * Sets the approve enj data.
     *
     * @param approveEnjData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withApproveEnjData(ApproveEnjData approveEnjData) {
        withParameter("approve_enj_data", approveEnjData);
        withType(TransactionType.APPROVE);
        return this;
    }

    /**
     * Sets the approve item data.
     *
     * @param approveItemData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withApproveItemData(ApproveItemData approveItemData) {
        withParameter("approve_item_data", approveItemData);
        return this;
    }

    /**
     * Sets the set transferable data.
     *
     * @param setTransferableData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetTransferableData(SetTransferableData setTransferableData) {
        withParameter("set_transferable_data", setTransferableData);
        withType(TransactionType.SET_TRANSFERABLE);
        return this;
    }

    /**
     * Sets the set melt fee data.
     *
     * @param setMeltFeeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetMeltFeeData(SetMeltFeeData setMeltFeeData) {
        withParameter("set_melt_fee_data", setMeltFeeData);
        withType(TransactionType.SET_MELT_FEE);
        return this;
    }

    /**
     * Sets the decrease max melt fee data.
     *
     * @param decreaseMaxMeltFeeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withDecreaseMaxMeltFeeData(DecreaseMaxMeltFeeData decreaseMaxMeltFeeData) {
        withParameter("decrease_max_melt_fee_data", decreaseMaxMeltFeeData);
        withType(TransactionType.DECREASE_MAX_MELT_FEE);
        return this;
    }

    /**
     * Sets the set transfer fee data.
     *
     * @param setTransferFeeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetTransferFeeData(SetTransferFeeData setTransferFeeData) {
        withParameter("set_transfer_fee_data", setTransferFeeData);
        withType(TransactionType.SET_TRANSFER_FEE);
        return this;
    }

    /**
     * Sets the decrease max transfer fee data.
     *
     * @param decreaseMaxTransferFeeData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withDecreaseMaxTransferFeeData(DecreaseMaxTransferFeeData decreaseMaxTransferFeeData) {
        withParameter("decrease_max_transfer_fee_data", decreaseMaxTransferFeeData);
        withType(TransactionType.DECREASE_MAX_TRANSFER_FEE);
        return this;
    }

    /**
     * Sets the add log data.
     *
     * @param addLogData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withAddLogData(AddLogData addLogData) {
        withParameter("add_log_data", addLogData);
        withType(TransactionType.ADD_LOG);
        return this;
    }

    /**
     * Sets the batch approve data.
     *
     * @param batchApproveData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withBatchApproveData(BatchApproveData batchApproveData) {
        withParameter("batch_approve_data", batchApproveData);
        withType(TransactionType.BATCH_APPROVE);
        return this;
    }

    /**
     * Sets the set approval data.
     *
     * @param setApprovalData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetApprovalData(SetApprovalData setApprovalData) {
        withParameter("set_approval_data", setApprovalData);
        withType(TransactionType.SET_APPROVAL);
        return this;
    }

    /**
     * Sets the set approval for all data.
     *
     * @param setApprovalForAllData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withSetApprovalForAllData(SetApprovalForAllData setApprovalForAllData) {
        withParameter("set_approval_for_all_data", setApprovalForAllData);
        withType(TransactionType.SET_APPROVAL_FOR_ALL);
        return this;
    }

    /**
     * Sets the message data.
     *
     * @param messageData the data.
     *
     * @return the builder.
     */
    public UpdateRequest withMessageData(MessageData messageData) {
        withParameter("message_data", messageData);
        withType(TransactionType.MESSAGE);
        return this;
    }

}