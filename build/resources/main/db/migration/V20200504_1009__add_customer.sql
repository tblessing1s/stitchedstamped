CREATE TABLE customer
(
    id          INT          NOT NULL identity (1, 1),
    first_name     VARCHAR(25) NOT NULL,
    last_name     VARCHAR(25) NOT NULL,
    email     VARCHAR(80) NULL,
    phone VARCHAR(50) NULL,
    modified_ts DATETIMEOFFSET,
    create_ts   DATETIMEOFFSET CONSTRAINT DF_customer_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__customer PRIMARY KEY (id)
)
