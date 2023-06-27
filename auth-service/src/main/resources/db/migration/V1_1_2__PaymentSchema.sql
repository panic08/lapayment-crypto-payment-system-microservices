CREATE TABLE IF NOT EXISTS payments_table(
                                             id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                             wallet_id VARCHAR(255) NOT NULL,
                                             o VARCHAR(255) NOT NULL,
                                             company_name VARCHAR(255) NOT NULL,
                                             amount DOUBLE PRECISION NOT NULL,
                                             currency VARCHAR(255) NOT NULL,
                                             ip_address VARCHAR(255) NOT NULL,
                                             timestamp BIGINT NOT NULL
);