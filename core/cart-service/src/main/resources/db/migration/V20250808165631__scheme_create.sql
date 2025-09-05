CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE basket (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL
);

CREATE TABLE basket_item (
    basket_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (basket_id, product_id)
);