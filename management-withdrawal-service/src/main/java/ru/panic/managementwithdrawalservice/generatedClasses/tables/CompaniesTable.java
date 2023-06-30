/*
 * This file is generated by jOOQ.
 */
package ru.panic.managementwithdrawalservice.generatedClasses.tables;


import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.panic.managementwithdrawalservice.generatedClasses.Keys;
import ru.panic.managementwithdrawalservice.generatedClasses.Public;
import ru.panic.managementwithdrawalservice.generatedClasses.tables.records.CompaniesTableRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CompaniesTable extends TableImpl<CompaniesTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.companies_table</code>
     */
    public static final CompaniesTable COMPANIES_TABLE = new CompaniesTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CompaniesTableRecord> getRecordType() {
        return CompaniesTableRecord.class;
    }

    /**
     * The column <code>public.companies_table.id</code>.
     */
    public final TableField<CompaniesTableRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.companies_table.company_name</code>.
     */
    public final TableField<CompaniesTableRecord, String> COMPANY_NAME = createField(DSL.name("company_name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.companies_table.owner</code>.
     */
    public final TableField<CompaniesTableRecord, String> OWNER = createField(DSL.name("owner"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.companies_table.webhook_url</code>.
     */
    public final TableField<CompaniesTableRecord, String> WEBHOOK_URL = createField(DSL.name("webhook_url"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.companies_table.secret_key</code>.
     */
    public final TableField<CompaniesTableRecord, String> SECRET_KEY = createField(DSL.name("secret_key"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.companies_table.created_at</code>.
     */
    public final TableField<CompaniesTableRecord, Long> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.BIGINT.nullable(false), this, "");

    private CompaniesTable(Name alias, Table<CompaniesTableRecord> aliased) {
        this(alias, aliased, null);
    }

    private CompaniesTable(Name alias, Table<CompaniesTableRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.companies_table</code> table reference
     */
    public CompaniesTable(String alias) {
        this(DSL.name(alias), COMPANIES_TABLE);
    }

    /**
     * Create an aliased <code>public.companies_table</code> table reference
     */
    public CompaniesTable(Name alias) {
        this(alias, COMPANIES_TABLE);
    }

    /**
     * Create a <code>public.companies_table</code> table reference
     */
    public CompaniesTable() {
        this(DSL.name("companies_table"), null);
    }

    public <O extends Record> CompaniesTable(Table<O> child, ForeignKey<O, CompaniesTableRecord> key) {
        super(child, key, COMPANIES_TABLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CompaniesTableRecord, Long> getIdentity() {
        return (Identity<CompaniesTableRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CompaniesTableRecord> getPrimaryKey() {
        return Keys.COMPANIES_TABLE_PKEY;
    }

    @Override
    public CompaniesTable as(String alias) {
        return new CompaniesTable(DSL.name(alias), this);
    }

    @Override
    public CompaniesTable as(Name alias) {
        return new CompaniesTable(alias, this);
    }

    @Override
    public CompaniesTable as(Table<?> alias) {
        return new CompaniesTable(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompaniesTable rename(String name) {
        return new CompaniesTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompaniesTable rename(Name name) {
        return new CompaniesTable(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompaniesTable rename(Table<?> name) {
        return new CompaniesTable(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, String, String, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
