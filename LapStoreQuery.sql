USE [master];
GO

-- Drop the database if it exists
IF DB_ID('LapStore') IS NOT NULL
BEGIN
    ALTER DATABASE LapStore SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [LapStore];
END

-- Create the database
CREATE DATABASE [LapStore];
USE [LapStore];
GO

-- Create User table
CREATE TABLE [User] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    full_name NVARCHAR(250),
    phone VARCHAR(12),
    email VARCHAR(255) UNIQUE NOT NULL,
    gender int ,
    address NVARCHAR(255) DEFAULT NULL,
	isActive INT DEFAULT NULL
);

create table Account(
	id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT FK_UserAccount FOREIGN KEY (id) REFERENCES [User](id)
);

CREATE TABLE Role (
    id INT IDENTITY(1,1) PRIMARY KEY,
    role_name NVARCHAR(50) NOT NULL
);

ALTER TABLE Account
ADD CONSTRAINT FK_RoleAccount FOREIGN KEY (role_id) REFERENCES Role(id);

-- Create ProductStatus table
CREATE TABLE ProductStatus (
    status_id INT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL
);

-- Insert data into ProductStatus
INSERT INTO ProductStatus (status_id, name) VALUES
(1, 'Available'),
(2, 'Out of Stock'),
(3, 'Discontinued');

-- Create Product table
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(250),
    price DECIMAL(10, 2) NOT NULL,
    processor VARCHAR(200),
    graphic_card NVARCHAR(100),
    screen_details VARCHAR(50),
    weight DECIMAL(5, 2),
    brand NVARCHAR(50) NOT NULL,
    release_date DATE,
    battery_life NVARCHAR(50),
    description NVARCHAR(MAX),
    status INT NOT NULL,
    CONSTRAINT FK_ProductStatus FOREIGN KEY (status) REFERENCES ProductStatus(status_id)
);

-- Create ProductImage table
CREATE TABLE ProductImage (
    id INT IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    url NVARCHAR(255),
    CONSTRAINT FK_ProductImage_Product FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Create VariantStatus table
CREATE TABLE VariantStatus (
    status_id INT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL
);




-- Create ProductVariant table
CREATE TABLE ProductVariant (
    id INT IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    RAM NVARCHAR(100),
    storage NVARCHAR(50),
    quantity INT,
    variant_price DECIMAL(10, 2),
    sale_id INT,
    status INT NOT NULL,
    CONSTRAINT FK_ProductVariant_Product FOREIGN KEY (product_id) REFERENCES Product(id),
    
    CONSTRAINT FK_ProductVariant_Status FOREIGN KEY (status) REFERENCES VariantStatus(status_id)
);

-- Populate VariantStatus table
INSERT INTO VariantStatus (status_id, name) VALUES
(1, 'Active'),
(2, 'Inactive'),
(3, 'Discontinued');
