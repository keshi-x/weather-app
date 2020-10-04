DROP TABLE IF EXISTS forecast_data;
CREATE TABLE forecast_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    server_id INT,
    city_id INT,
    temperature NUMERIC,
    humidity NUMERIC,
    pressure NUMERIC,
    date_created TIMESTAMP
);
DROP TABLE IF EXISTS city;
CREATE TABLE city (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(20),
      lat VARCHAR(20),
      lon VARCHAR(20)
);