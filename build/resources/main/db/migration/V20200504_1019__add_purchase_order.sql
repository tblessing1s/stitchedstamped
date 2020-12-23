CREATE TABLE purchase_order
(
    id INT NOT NULL identity (1, 1),
    order_source VARCHAR(50),
    customer_id INT CONSTRAINT FK_purchase_order_to_customer FOREIGN KEY REFERENCES customer(id),
    modified_ts DATETIMEOFFSET,
    create_ts   DATETIMEOFFSET CONSTRAINT DF_purchase_order_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__purchase_order PRIMARY KEY (id)
);
GO

CREATE TRIGGER TR_purchase_order_update ON purchase_order AFTER INSERT, UPDATE AS
    UPDATE f SET modified_ts = getutcdate()
    FROM purchase_order AS f
        INNER JOIN inserted AS i ON f.id = i.id
