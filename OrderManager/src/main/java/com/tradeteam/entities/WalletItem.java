package com.tradeteam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class WalletItem {

    String exchangeId;
    String companyAbbrev;
    int numberOwned;

    public static WalletItem of(String exchangeIdCompanyAbbrev, int numberOwned) {
        int splitIndex = exchangeIdCompanyAbbrev.indexOf(":");
        String exchangeId = exchangeIdCompanyAbbrev.substring(0, splitIndex);
        String companyAbbrev = exchangeIdCompanyAbbrev.substring(splitIndex + 1,
                exchangeIdCompanyAbbrev.length());
        return new WalletItem(exchangeId, companyAbbrev, numberOwned);
    }

}
