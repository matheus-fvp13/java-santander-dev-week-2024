CREATE TABLE IF NOT EXISTS champions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    role VARCHAR (255) NOT NULL,
    lore TEXT,
    image_url VARCHAR(255)
);