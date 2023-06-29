/*
 * This file is generated by jOOQ.
 */
package ru.panic.withdrawalservice.generatedClasses.tables;


import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.panic.withdrawalservice.generatedClasses.Keys;
import ru.panic.withdrawalservice.generatedClasses.Public;
import ru.panic.withdrawalservice.generatedClasses.tables.records.PaymentsTableRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PaymentsTable extends TableImpl<PaymentsTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.payments_table</code>
     */
    public static final PaymentsTable PAYMENTS_TABLE = new PaymentsTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PaymentsTableRecord> getRecordType() {
        return PaymentsTableRecord.class;
    }

    /**
     * The column <code>public.payments_table.id</code>.
     */
    public final TableField<PaymentsTableRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.payments_table.wallet_id</code>.
     */
    public final TableField<PaymentsTableRecord, String> WALLET_ID = createField(DSL.name("wallet_id"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.payments_table.o</code>.
     */
    public final TableField<PaymentsTableRecord, String> O = createField(DSL.name("o"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.payments_table.company_name</code>.
     */
    public final TableField<PaymentsTableRecord, String> COMPANY_NAME = createField(DSL.name("company_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.payments_table.amount</code>.
     */
    public final TableField<PaymentsTableRecord, Double> AMOUNT = createField(DSL.name("amount"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.payments_table.currency</code>.
     */
    public final TableField<PaymentsTableRecord, String> CURRENCY = createField(DSL.name("currency"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.payments_table.ip_address</code>.
     */
    public final TableField<PaymentsTableRecord, String> IP_ADDRESS = createField(DSL.name("ip_address"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.payments_table.timestamp</code>.
     */
    public final TableField<PaymentsTableRecord, Long> TIMESTAMP = createField(DSL.name("timestamp"), SQLDataType.BIGINT.nullable(false), this, "");

    private PaymentsTable(Name alias, Table<PaymentsTableRecord> aliased) {
        this(alias, aliased, null);
    }

    private PaymentsTable(Name alias, Table<PaymentsTableRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.payments_table</code> table reference
     */
    public PaymentsTable(String alias) {
        this(DSL.name(alias), PAYMENTS_TABLE);
    }

    /**
     * Create an aliased <code>public.payments_table</code> table reference
     */
    public PaymentsTable(Name alias) {
        this(alias, PAYMENTS_TABLE);
    }

    /**
     * Create a <code>public.payments_table</code> table reference
     */
    public PaymentsTable() {
        this(DSL.name("payments_table"), null);
    }

    public <O extends Record> PaymentsTable(Table<O> child, ForeignKey<O, PaymentsTableRecord> key) {
        super(child, key, PAYMENTS_TABLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<PaymentsTableRecord, Long> getIdentity() {
        return (Identity<PaymentsTableRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PaymentsTableRecord> getPrimaryKey() {
        return Keys.PAYMENTS_TABLE_PKEY;
    }

    @Override
    public PaymentsTable as(String alias) {
        return new PaymentsTable(DSL.name(alias), this);
    }

    @Override
    public PaymentsTable as(Name alias) {
        return new PaymentsTable(alias, this);
    }

    @Override
    public PaymentsTable as(Table<?> alias) {
        return new PaymentsTable(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentsTable rename(String name) {
        return new PaymentsTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentsTable rename(Name name) {
        return new PaymentsTable(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PaymentsTable rename(Table<?> name) {
        return new PaymentsTable(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Double, String, String, Long> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Long, ? super String, ? super String, ? super String, ? super Double, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Long, ? super String, ? super String, ? super String, ? super Double, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}