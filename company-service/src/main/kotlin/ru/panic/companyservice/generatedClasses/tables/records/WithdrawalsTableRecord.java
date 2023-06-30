/*
 * This file is generated by jOOQ.
 */
package ru.panic.companyservice.generatedClasses.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import ru.panic.companyservice.generatedClasses.tables.WithdrawalsTable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WithdrawalsTableRecord extends UpdatableRecordImpl<WithdrawalsTableRecord> implements Record5<Long, String, Double, String, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.withdrawals_table.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.withdrawals_table.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.withdrawals_table.client_username</code>.
     */
    public void setClientUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.withdrawals_table.client_username</code>.
     */
    public String getClientUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.withdrawals_table.amount</code>.
     */
    public void setAmount(Double value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.withdrawals_table.amount</code>.
     */
    public Double getAmount() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>public.withdrawals_table.currency</code>.
     */
    public void setCurrency(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.withdrawals_table.currency</code>.
     */
    public String getCurrency() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.withdrawals_table.timestamp</code>.
     */
    public void setTimestamp(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.withdrawals_table.timestamp</code>.
     */
    public Long getTimestamp() {
        return (Long) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, Double, String, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, Double, String, Long> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return WithdrawalsTable.WITHDRAWALS_TABLE.ID;
    }

    @Override
    public Field<String> field2() {
        return WithdrawalsTable.WITHDRAWALS_TABLE.CLIENT_USERNAME;
    }

    @Override
    public Field<Double> field3() {
        return WithdrawalsTable.WITHDRAWALS_TABLE.AMOUNT;
    }

    @Override
    public Field<String> field4() {
        return WithdrawalsTable.WITHDRAWALS_TABLE.CURRENCY;
    }

    @Override
    public Field<Long> field5() {
        return WithdrawalsTable.WITHDRAWALS_TABLE.TIMESTAMP;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getClientUsername();
    }

    @Override
    public Double component3() {
        return getAmount();
    }

    @Override
    public String component4() {
        return getCurrency();
    }

    @Override
    public Long component5() {
        return getTimestamp();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getClientUsername();
    }

    @Override
    public Double value3() {
        return getAmount();
    }

    @Override
    public String value4() {
        return getCurrency();
    }

    @Override
    public Long value5() {
        return getTimestamp();
    }

    @Override
    public WithdrawalsTableRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public WithdrawalsTableRecord value2(String value) {
        setClientUsername(value);
        return this;
    }

    @Override
    public WithdrawalsTableRecord value3(Double value) {
        setAmount(value);
        return this;
    }

    @Override
    public WithdrawalsTableRecord value4(String value) {
        setCurrency(value);
        return this;
    }

    @Override
    public WithdrawalsTableRecord value5(Long value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public WithdrawalsTableRecord values(Long value1, String value2, Double value3, String value4, Long value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached WithdrawalsTableRecord
     */
    public WithdrawalsTableRecord() {
        super(WithdrawalsTable.WITHDRAWALS_TABLE);
    }

    /**
     * Create a detached, initialised WithdrawalsTableRecord
     */
    public WithdrawalsTableRecord(Long id, String clientUsername, Double amount, String currency, Long timestamp) {
        super(WithdrawalsTable.WITHDRAWALS_TABLE);

        setId(id);
        setClientUsername(clientUsername);
        setAmount(amount);
        setCurrency(currency);
        setTimestamp(timestamp);
        resetChangedOnNotNull();
    }
}