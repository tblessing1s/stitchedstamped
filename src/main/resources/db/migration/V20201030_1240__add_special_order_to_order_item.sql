ALTER TABLE order_item
    Add special_order_id INT CONSTRAINT FK_order_item_to_special_order FOREIGN KEY REFERENCES special_order(id)
