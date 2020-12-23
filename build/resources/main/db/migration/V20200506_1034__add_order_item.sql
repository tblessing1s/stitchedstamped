CREATE TABLE order_item
(
    id INT NOT NULL identity (1, 1),
    purchase_order_id INT CONSTRAINT FK_order_item_to_purchase_order FOREIGN KEY REFERENCES purchase_order(id),
    monogram_id INT CONSTRAINT FK_order_item_to_monogram FOREIGN KEY REFERENCES monogram(id),
    modified_ts DATETIMEOFFSET,
    create_ts   DATETIMEOFFSET CONSTRAINT DF_order_item_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__order_item PRIMARY KEY (id)
)
GO

CREATE TRIGGER TR_order_item_update ON order_item AFTER INSERT, UPDATE AS
    UPDATE f SET modified_ts = getutcdate()
    FROM order_item AS f
             INNER JOIN inserted AS i ON f.id = i.id
