CREATE TABLE special_order
(
    id INT NOT NULL identity (1, 1),
    item_name VARCHAR(255),
    brand VARCHAR(100),
    size VARCHAR(50),
    item_color VARCHAR(75),
    design_notes VARCHAR(MAX),
    modified_ts DATETIMEOFFSET,
    create_ts   DATETIMEOFFSET CONSTRAINT DF_special_order_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__special_order PRIMARY KEY (id)
);
GO

CREATE TRIGGER TR_special_order_update ON special_order AFTER INSERT, UPDATE AS
    UPDATE f SET modified_ts = getutcdate()
    FROM special_order AS f
             INNER JOIN inserted AS i ON f.id = i.id
