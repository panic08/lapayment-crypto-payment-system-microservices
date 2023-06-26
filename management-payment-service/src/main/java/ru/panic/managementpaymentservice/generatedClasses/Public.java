/*
 * This file is generated by jOOQ.
 */
package ru.panic.managementpaymentservice.generatedClasses;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.panic.managementpaymentservice.generatedClasses.tables.ClientsTable;
import ru.panic.managementpaymentservice.generatedClasses.tables.FlywaySchemaHistory;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.clients_table</code>.
     */
    public final ClientsTable CLIENTS_TABLE = ClientsTable.CLIENTS_TABLE;

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            ClientsTable.CLIENTS_TABLE,
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY
        );
    }
}
