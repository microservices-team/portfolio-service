CREATE SCHEMA IF NOT EXISTS portfolio;

CREATE TABLE IF NOT EXISTS portfolio.experience (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    company         VARCHAR(255) NOT NULL,
    role            VARCHAR(255) NOT NULL,
    period          VARCHAR(100),
    location        VARCHAR(255),
    description_es  TEXT,
    description_en  TEXT,
    highlights_es   JSONB DEFAULT '[]',
    highlights_en   JSONB DEFAULT '[]',
    stack           JSONB DEFAULT '[]',
    sort_order      INT  DEFAULT 0,
    active          BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS portfolio.skills (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_es VARCHAR(100),
    category_en VARCHAR(100),
    icon        VARCHAR(50),
    items       JSONB DEFAULT '[]',
    sort_order  INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS portfolio.projects (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(255) NOT NULL,
    description_es  TEXT,
    description_en  TEXT,
    metrics         VARCHAR(255),
    stack           JSONB DEFAULT '[]',
    featured        BOOLEAN DEFAULT false,
    github_url      VARCHAR(500),
    live_url        VARCHAR(500),
    sort_order      INT DEFAULT 0,
    active          BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS portfolio.certifications (
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    issuer     VARCHAR(255),
    year       VARCHAR(10),
    badge      VARCHAR(50),
    sort_order INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS portfolio.education (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    institution VARCHAR(255) NOT NULL,
    degree_es   VARCHAR(255),
    degree_en   VARCHAR(255),
    period      VARCHAR(100),
    location    VARCHAR(255)
);
