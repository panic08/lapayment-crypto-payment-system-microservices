CREATE TABLE IF NOT EXISTS clients_table(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    btc_balance DOUBLE PRECISION NOT NULL,
    eth_balance DOUBLE PRECISION NOT NULL,
    ltc_balance DOUBLE PRECISION NOT NULL,
    trx_balance DOUBLE PRECISION NOT NULL,
    ton_balance DOUBLE PRECISION NOT NULL,
    matic_balance DOUBLE PRECISION NOT NULL,
    xrp_balance DOUBLE PRECISION NOT NULL,
    tetherERC20_balance DOUBLE PRECISION NOT NULL,
    registeredAt BIGINT NOT NULL
    );