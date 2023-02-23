ALTER TABLE likes ADD plant_record_id INT;
ALTER TABLE likes ADD FOREIGN KEY (plant_record_id) REFERENCES plant_records (id);
