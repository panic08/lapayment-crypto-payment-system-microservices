package ru.panic.paymentservice.template.dto.crypto;

import lombok.Getter;

import java.util.List;

@Getter
public class RippleProviderResponse {
    private Result result;
    private List<Warning> warnings;
    @Getter
    public static class Result {
        private AccountResult account;
        private int ledger_index_min;
        private int ledger_index_max;
        private List<Transaction> transactions;
        private boolean validated;
        private Marker marker;
        private int limit;
        private String status;
    }
    @Getter
    public static class AccountResult {
        private String account;
        private int ledger_index_min;
        private int ledger_index_max;
        private List<Transaction> transactions;
        private boolean validated;
        private Marker marker;
        private int limit;
        private String status;
    }
    @Getter
    public static class Transaction {
        private Meta meta;
        private Tx tx;
        private boolean validated;
    }
    @Getter
    public static class Meta {
        private List<AffectedNode> AffectedNodes;
        private int TransactionIndex;
        private String TransactionResult;
        private String delivered_amount;
    }
    @Getter
    public static class AffectedNode {
        private ModifiedNode ModifiedNode;
    }
    @Getter
    public static class ModifiedNode {
        private FinalFields FinalFields;
        private String LedgerEntryType;
        private String LedgerIndex;
        private PreviousFields PreviousFields;
        private String PreviousTxnID;
        private int PreviousTxnLgrSeq;
    }
    @Getter
    public static class FinalFields {
        private String Account;
        private String Balance;
        private int Flags;
        private int OwnerCount;
        private int Sequence;
    }
    @Getter

    public static class PreviousFields {
        private String Balance;
        private int Sequence;
    }
    @Getter
    public static class Tx {
        private String Account;
        private String Amount;
        private String Destination;
        private String Fee;
        private int Flags;
        private int LastLedgerSequence;
        private int Sequence;
        private String SigningPubKey;
        private String TransactionType;
        private String TxnSignature;
        private String hash;
        private int ledger_index;
        private long date;
    }
    @Getter
    public static class Marker {
        private int ledger;
        private int seq;
    }
    @Getter
    public static class Warning {
        private int id;
        private String message;
    }
}
