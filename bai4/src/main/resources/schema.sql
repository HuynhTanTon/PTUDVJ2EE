CREATE TABLE IF NOT EXISTS roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BIT NOT NULL
);

CREATE TABLE IF NOT EXISTS account_role (
    account_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (account_id, role_id),
    CONSTRAINT fk_account_role_account FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_account_role_role FOREIGN KEY (role_id) REFERENCES roles(id)
);
