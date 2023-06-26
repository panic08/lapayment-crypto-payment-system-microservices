package ru.panic.paymentservice.template.dto.crypto;

import lombok.Getter;

import java.util.List;

@Getter
public class TonProviderResponse {
    private boolean ok;
    private List<ResultDTO> result;
    @Getter
    public static class ResultDTO {
        private String type;
        private AddressDTO address;
        private int utime;
        private String data;
        private TransactionIdDTO transaction_id;
        private String fee;
        private String storage_fee;
        private String other_fee;
        private InMsgDTO in_msg;
        private List<OutMsgDTO> out_msgs;
    }
    @Getter
    public static class AddressDTO {
        private String type;
        private String account_address;
    }
    @Getter
    public static class TransactionIdDTO {
        private String type;
        private String lt;
        private String hash;
    }
    @Getter
    public static class InMsgDTO {
        private String type;
        private String source;
        private String destination;
        private String value;
        private String fwd_fee;
        private String ihr_fee;
        private String created_lt;
        private String body_hash;
        private MsgDataDTO msg_data;
        private String message;
    }

    public static class OutMsgDTO {
        private String type;
        private String source;
        private String destination;
        private String value;
        private String fwd_fee;
        private String ihr_fee;
        private String created_lt;
        private String body_hash;
        private MsgDataDTO msg_data;
        private String message;
    }

    public static class MsgDataDTO {
        private String type;
        private String body;
        private String init_state;
    }
}
