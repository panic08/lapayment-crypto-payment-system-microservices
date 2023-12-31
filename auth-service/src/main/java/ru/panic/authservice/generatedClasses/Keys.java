/*
 * This file is generated by jOOQ.
 */
package ru.panic.authservice.generatedClasses;


import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import ru.panic.authservice.generatedClasses.tables.ClientsTable;
import ru.panic.authservice.generatedClasses.tables.CompaniesTable;
import ru.panic.authservice.generatedClasses.tables.FlywaySchemaHistory;
import ru.panic.authservice.generatedClasses.tables.PaymentsTable;
import ru.panic.authservice.generatedClasses.tables.records.ClientsTableRecord;
import ru.panic.authservice.generatedClasses.tables.records.CompaniesTableRecord;
import ru.panic.authservice.generatedClasses.tables.records.FlywaySchemaHistoryRecord;
import ru.panic.authservice.generatedClasses.tables.records.PaymentsTableRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClientsTableRecord> CLIENTS_TABLE_PKEY = Internal.createUniqueKey(ClientsTable.CLIENTS_TABLE, DSL.name("clients_table_pkey"), new TableField[] { ClientsTable.CLIENTS_TABLE.ID }, true);
    public static final UniqueKey<CompaniesTableRecord> COMPANIES_TABLE_PKEY = Internal.createUniqueKey(CompaniesTable.COMPANIES_TABLE, DSL.name("companies_table_pkey"), new TableField[] { CompaniesTable.COMPANIES_TABLE.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<PaymentsTableRecord> PAYMENTS_TABLE_PKEY = Internal.createUniqueKey(PaymentsTable.PAYMENTS_TABLE, DSL.name("payments_table_pkey"), new TableField[] { PaymentsTable.PAYMENTS_TABLE.ID }, true);
}
