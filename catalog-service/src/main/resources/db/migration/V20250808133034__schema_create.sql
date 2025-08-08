CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    image TEXT
);

CREATE TABLE chapter (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    categories_id UUID REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    chapter_id UUID REFERENCES chapter(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(19,2) NOT NULL
);

CREATE TABLE product_image (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID REFERENCES product(id) ON DELETE CASCADE,
    image_url TEXT NOT NULL
);