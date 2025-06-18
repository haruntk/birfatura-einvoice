CREATE TABLE invoice (
    id BIGSERIAL PRIMARY KEY,
    invoice_no VARCHAR(255) NOT NULL,
    invoice_date DATE NOT NULL DEFAULT CURRENT_DATE,
    invoice_time TIME NOT NULL DEFAULT CURRENT_TIME,
    invoice_type VARCHAR(255),
    order_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    status_code INT,
    uuid VARCHAR(255) NOT NULL,
    total_amount NUMERIC(8, 2) NOT NULL,
    pdf_url VARCHAR(255),
    created_by BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by BIGINT,
    updated_at TIMESTAMP,
    deleted_by BIGINT,
    deleted_at TIMESTAMP
);

CREATE INDEX IF NOT EXISTS order_id_idx
    ON invoice(order_id);

CREATE INDEX IF NOT EXISTS customer_id_idx
    ON invoice(customer_id);

CREATE INDEX IF NOT EXISTS uuid_id_idx
	ON invoice(uuid);