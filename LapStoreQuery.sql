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
--Create Role table
CREATE TABLE [Role] (
    id [int] PRIMARY KEY,
    role_name NVARCHAR(100) NOT NULL
);
--Create Account table
CREATE TABLE [dbo].[Account](
	[id] [bigint] NOT NULL PRIMARY KEY,
	[username] [varchar](32) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[role_Id] [int] NOT NULL,
	[isActive] INT DEFAULT 1,
	CONSTRAINT FK_AccountRole FOREIGN KEY (role_id) REFERENCES Role(id)
);

-- Create User table
CREATE TABLE [User] (
    [id] [bigint] identity(1,1) NOT NULL PRIMARY KEY,
	[fullname] [nvarchar](max) NULL,
	[email] varchar(255) ,
	[phone] [varchar](12) NULL,
	[email][nvarchar](max) NULL,
	[address] [nvarchar](max) NULL,
	[gender] [bit] NULL
);
-- Create Brand table
create table Brand(
id int identity(1,1) primary key,
name nvarchar(100)
)

-- Create Product table
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(250),
    price DECIMAL(10, 2) NOT NULL,
    processor VARCHAR(200),
    graphic_card NVARCHAR(100),
    screen_details VARCHAR(50),
	size VARCHAR(50),
    weight DECIMAL(5, 2),
	operating_system NVARCHAR(50),
    brand int NOT NULL,
    release_date DATE,
    battery_life NVARCHAR(50),
    description NVARCHAR(MAX),
    status INT NOT NULL,
	constraint FK_Product_Brand Foreign key (brand) references Brand(id)
);


-- Create ProductImage table
CREATE TABLE ProductImage (
    id INT IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    url NVARCHAR(255),
    CONSTRAINT FK_ProductImage_Product FOREIGN KEY (product_id) REFERENCES Product(id)
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
);
GO
CREATE TABLE [dbo].[Security](
	[question_Id] [bigint],
	[account_Id] [bigint] NOT NULL PRIMARY KEY,
	[answer] [ntext] NOT NULL,
	)
GO
/****** Object:  Table [dbo].[SecurityQuestion]    Script Date: 7/25/2023 8:47:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SecurityQuestion](
	[id] [bigint] IDENTITY(1,1) NOT NULL  PRIMARY KEY,
	[question] [ntext] NOT NULL,

	);
-- Populate VariantStatus table
GO
ALTER TABLE [dbo].[Security]  WITH CHECK ADD FOREIGN KEY([account_Id])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Security]  WITH CHECK ADD FOREIGN KEY([question_Id])
REFERENCES [dbo].[SecurityQuestion] ([id])
GO
GO

ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([id])
REFERENCES [dbo].[User] ([id])
ON DELETE CASCADE

insert into Role (role_name) values 
('Admin'),
('Staff'),
('Customer');

insert into [User] (fullname,email,phone,address,gender) values (
'Tran Tien Dung','ttd21072004@gmail.com','0963177099','Ha Noi',1);
insert into [Account] (id,username,password,role_Id,isActive) values
(1,'dungle2107','1',1,1);

ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([id])
REFERENCES [dbo].[Account] ([id])
ON DELETE CASCADE
-- Insert data Product
-- Insert sample data into the Brand table
INSERT INTO Brand (name) 
VALUES 
('Dell'),
('Apple'),
('HP'),
('Asus'),
('Lenovo');

-- Insert sample data into the Product table
INSERT INTO Product (name, price, processor, graphic_card, screen_details, size, weight, operating_system, brand, release_date, battery_life, description, status)
VALUES 
('Dell XPS 13', 999.99, 'Intel Core i7', 'Intel Iris Xe', '13.3" FHD', '13.3 inches', 1.20, 'Windows 11', 1, '2023-05-01', '12 hours', 'Dell XPS 13 is a high-performance laptop with a sleek design.', 1),
('MacBook Pro 14', 1999.99, 'Apple M1 Pro', 'Integrated 14-core GPU', '14" Retina', '14 inches', 1.36, 'macOS', 2, '2023-03-15', '17 hours', 'The new MacBook Pro 14 offers incredible power and performance.', 1),
('HP Spectre x360', 1299.99, 'Intel Core i5', 'Intel UHD Graphics', '13.3" 4K OLED', '13.3 inches', 1.30, 'Windows 11', 3, '2023-06-20', '15 hours', 'HP Spectre x360 is a versatile convertible laptop with a stunning display.', 1),
('Asus ROG Zephyrus G14', 1499.99, 'AMD Ryzen 9', 'NVIDIA GeForce RTX 3060', '14" QHD', '14 inches', 1.60, 'Windows 11', 4, '2023-07-10', '10 hours', 'Asus ROG Zephyrus G14 is a gaming powerhouse in a compact form factor.', 1),
('Lenovo ThinkPad X1 Carbon', 1799.99, 'Intel Core i7', 'Intel Iris Xe', '14" FHD', '14 inches', 1.09, 'Windows 11', 5, '2023-04-22', '15 hours', 'Lenovo ThinkPad X1 Carbon is a lightweight, high-performance business laptop.', 1);

-- Insert sample data into the ProductImage table
INSERT INTO ProductImage (product_id, url)
VALUES 
(1, N'DellXPS13.jpg'),
(2, N'MacBookPro14.jpg'),
(3, N'HP Spectre x360'),
(4, N'Asus ROG Zephyrus G14'),
(5, N'Lenovo ThinkPad X1 Carbon.jpg')

-- Insert Security questions
GO
SET IDENTITY_INSERT [dbo].[SecurityQuestion] ON 

INSERT [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (1, N'What is your favorite song?')
INSERT [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (2, N'Who is your best friend?')
INSERT [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (3, N'Where are you come from?')
SET IDENTITY_INSERT [dbo].[SecurityQuestion] OFF

-- Verify that the ids match with those used in the Product table
SELECT * FROM [Product];
SELECT * FROM [Brand];
Select * from [ProductImage]

