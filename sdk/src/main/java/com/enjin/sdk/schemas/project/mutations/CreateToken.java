package com.enjin.sdk.schemas.project.mutations;

import com.enjin.sdk.graphql.GraphQLRequest;
import com.enjin.sdk.models.TokenSupplyModel;
import com.enjin.sdk.models.TokenTransferFeeSettings;
import com.enjin.sdk.models.TokenTransferable;
import com.enjin.sdk.schemas.shared.arguments.TransactionRequestArguments;

/**
 * Request for creating a token (item) on the platform.
 *
 * @see com.enjin.sdk.schemas.project.ProjectSchema
 */
public class CreateToken
        extends GraphQLRequest<CreateToken>
        implements TransactionRequestArguments<CreateToken> {

    /**
     * Sole constructor.
     */
    public CreateToken() {
        super("enjin.sdk.project.CreateToken");
    }

    /**
     * Sets the name of the token (item).
     *
     * @param name the name
     * @return this request for chaining
     */
    public CreateToken name(String name) {
        return set("name", name);
    }

    /**
     * Sets the total supply of the token (item).
     *
     * @param totalSupply the total supply
     * @return this request for chaining
     */
    public CreateToken totalSupply(String totalSupply) {
        return set("totalSupply", totalSupply);
    }

    /**
     * Sets the initial reserve of the token (item).
     *
     * @param initialReserve the reserve
     * @return this request for chaining
     */
    public CreateToken initialReserve(String initialReserve) {
        return set("initialReserve", initialReserve);
    }

    /**
     * Sets the supply model of the token (item).
     *
     * @param supplyModel the model
     * @return this request for chaining
     */
    public CreateToken supplyModel(TokenSupplyModel supplyModel) {
        return set("supplyModel", supplyModel);
    }

    /**
     * Sets the melt value of the token (item).
     * <br>
     * <p>
     *     Corresponds to the exchange rate.
     * </p>
     *
     * @param meltValue the value
     * @return this request for chaining
     */
    public CreateToken meltValue(String meltValue) {
        return set("meltValue", meltValue);
    }

    /**
     * Sets the ratio of the melt value to be returned to the creator.
     * <br>
     * <p>
     *     The ratio is in the range 0-5000 to allow fractional ratios, e.g. 1 = 0.01%, 5000 = 50%, ect...
     * </p>
     *
     * @param meltFeeRatio the ratio
     * @return this request for chaining
     */
    public CreateToken meltFeeRatio(int meltFeeRatio) {
        return set("meltFeeRatio", meltFeeRatio);
    }

    /**
     * Sets the transferable type of the token (item).
     *
     * @param transferable the transferable type
     * @return this request for chaining
     */
    public CreateToken transferable(TokenTransferable transferable) {
        return set("transferable", transferable);
    }

    /**
     * Sets the transfer fee settings of the token (item).
     *
     * @param transferFeeSettings the settings
     * @return this request for chaining
     */
    public CreateToken transferFeeSettings(TokenTransferFeeSettings transferFeeSettings) {
        return set("transferFeeSettings", transferFeeSettings);
    }

    /**
     * Sets the fungible state of the token (item).
     *
     * @param nonfungible the state
     * @return this request for chaining
     */
    public CreateToken nonfungible(boolean nonfungible) {
        return set("nonfungible", nonfungible);
    }

}