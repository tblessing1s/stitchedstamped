CREATE TABLE monogram
(
    id INT NOT NULL identity (1, 1),
    item_name VARCHAR(255),
    font VARCHAR(75),
    thread_color VARCHAR(100),
    placement VARCHAR(75),
    monogram  VARCHAR(50),
    design_notes VARCHAR(MAX),
    modified_ts DATETIMEOFFSET,
    create_ts   DATETIMEOFFSET CONSTRAINT DF_monogram_create_ts DEFAULT (GETUTCDATE()),
    CONSTRAINT PK__monogram PRIMARY KEY (id)
);
GO

CREATE TRIGGER TR_monogram_update ON monogram AFTER INSERT, UPDATE AS
    UPDATE f SET modified_ts = getutcdate()
    FROM monogram AS f
             INNER JOIN inserted AS i ON f.id = i.id
