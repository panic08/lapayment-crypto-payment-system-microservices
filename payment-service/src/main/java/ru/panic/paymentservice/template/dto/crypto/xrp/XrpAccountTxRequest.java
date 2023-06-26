package ru.panic.paymentservice.template.dto.crypto.xrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class XrpAccountTxRequest {
    private String method;
    private List<Object> params;

    public XrpAccountTxRequest(String account, int limit) {
        this.method = "account_tx";
        this.params = new ArrayList<>();
        Map<String, Object> accountParams = new HashMap<>();
        accountParams.put("account", account);
        accountParams.put("ledger_index_min", -1);
        accountParams.put("ledger_index_max", -1);
        accountParams.put("binary", false);
        accountParams.put("limit", limit);
        accountParams.put("forward", false);
        this.params.add(accountParams);
    }
}
