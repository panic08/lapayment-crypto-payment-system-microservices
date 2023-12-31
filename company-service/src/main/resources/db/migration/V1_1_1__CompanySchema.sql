CREATE TABLE IF NOT EXISTS companies_table(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    webhook_url VARCHAR(255) NOT NULL,
    secret_key VARCHAR(255) NOT NULL,
    created_at BIGINT NOT NULL
    );