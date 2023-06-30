CREATE TABLE IF NOT EXISTS withdrawals_table(
                                             id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                             client_username VARCHAR(255) NOT NULL,
                                             amount DOUBLE PRECISION NOT NULL,
                                             currency VARCHAR(255) NOT NULL,
                                             timestamp BIGINT NOT NULL
);