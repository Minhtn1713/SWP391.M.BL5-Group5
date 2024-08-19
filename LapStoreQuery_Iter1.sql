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
CREATE TABLE [dbo].[Role] (
    id [int] IDENTITY(1,1) PRIMARY KEY,
    role_name NVARCHAR(100) NOT NULL
);
--Create Account table
CREATE TABLE [dbo].[Account](
	[id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[username] [varchar](32) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[role_Id] [int] NOT NULL,
	[isActive] INT DEFAULT 1,
	CONSTRAINT FK_AccountRole FOREIGN KEY (role_id) REFERENCES Role(id)
);

-- Create User table
CREATE TABLE [User] (
    [id] [bigint] NOT NULL PRIMARY KEY,
	[fullname] [nvarchar](max) NULL,
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
	img VARCHAR(max) NULL,
    price DECIMAL(10, 2) NOT NULL,
    processor VARCHAR(200),
    graphic_card NVARCHAR(100),
    screen_details VARCHAR(50),
	size VARCHAR(50),
    weight DECIMAL(5, 2),
	operating_system NVARCHAR(50),
    brand int NOT NULL,
    battery_life NVARCHAR(50),
    description NVARCHAR(MAX),
    status INT NOT NULL,
	constraint FK_Product_Brand Foreign key (brand) references Brand(id)
);

-- **Create RAM table first:**
CREATE TABLE [dbo].[Ram](
	[Id] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[RAM] [varchar](max) NOT NULL,
	[Price_Bonus] [decimal](10, 2) NOT NULL,
	[Status] [int] NULL,
	
);

CREATE TABLE [dbo].[Storage](
	[Id] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[Storage_Size] [varchar](10) NULL,
	[Price_Bonus] [decimal](10, 2) NOT NULL,
	[Status] [int] NULL,
	
	);
CREATE TABLE [dbo].[Sale](
	[Id] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[Percent] [decimal](5, 2) NULL,
	);
GO
-- **Now create ProductVariant table:**
CREATE TABLE ProductVariant (
    id INT IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    RAM_id INT NOT NULL,
    Storage_id INT NOT NULL,
    quantity INT,
    variant_price DECIMAL(10, 2),
    sale_id INT NOT NULL,
    status INT NOT NULL,
	CONSTRAINT FK_ProductVariant_Product FOREIGN KEY (product_id) REFERENCES Product(id),
	CONSTRAINT FK_ProductVariant_RAM FOREIGN KEY (RAM_id) REFERENCES dbo.RAM(Id), 
	CONSTRAINT FK_ProductVariant_Storage FOREIGN KEY (Storage_id) REFERENCES Storage(Id),
	CONSTRAINT FK_ProductVariant_Sale FOREIGN KEY (sale_id) REFERENCES Sale(Id)
);
GO
CREATE TABLE [dbo].[ProductImage](
	[Id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[Url] [varchar](max) NOT NULL,
	[Product_Id] [int] NOT NULL,
	[Ram_Id] [int] NOT NULL,
	CONSTRAINT FK_ProductImage_Product FOREIGN KEY (Product_Id) REFERENCES Product(id),
	CONSTRAINT FK_ProductImage_RAM FOREIGN KEY (Ram_Id) REFERENCES RAM(Id)
);
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
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([id])
REFERENCES [dbo].[Account] ([id])
ON DELETE CASCADE
-- Insert sample data into the Brand table
INSERT INTO Brand (name) 
VALUES 
('Dell'),
('Apple'),
('HP'),
('Asus'),
('Lenovo');

-- Insert sample data into the Product table
INSERT INTO Product (name, img, price, processor, graphic_card, screen_details, size, weight, operating_system, brand, battery_life, description, status)
VALUES 
('Dell XPS 13', N'DellXPS13.jpg', 999.99, 'Intel Core i7', 'Intel Iris Xe', '13.3" FHD', '13.3 inches', 1.20, 'Windows 11', 1, '12 hours', 'Dell XPS 13 is a high-performance laptop with a sleek design.', 1),
('MacBook Pro 14', N'MacBookPro14.jpg', 1999.99, 'Apple M1 Pro', 'Integrated 14-core GPU', '14" Retina', '14 inches', 1.36, 'macOS', 2, '17 hours', 'The new MacBook Pro 14 offers incredible power and performance.', 1),
('HP Spectre x360', N'HPSpectrex360.jpg', 1299.99, 'Intel Core i5', 'Intel UHD Graphics', '13.3" 4K OLED', '13.3 inches', 1.30, 'Windows 11', 3, '15 hours', 'HP Spectre x360 is a versatile convertible laptop with a stunning display.', 1),
('Asus ROG Zephyrus G14', N'AsusROGZephyrusG14.jpg', 1499.99, 'AMD Ryzen 9', 'NVIDIA GeForce RTX 3060', '14" QHD', '14 inches', 1.60, 'Windows 11', 4, '10 hours', 'Asus ROG Zephyrus G14 is a gaming powerhouse in a compact form factor.', 1),
('Lenovo ThinkPad X1 Carbon', N'LenovoThinkPadX1Carbon.jpg', 1799.99, 'Intel Core i7', 'Intel Iris Xe', '14" FHD', '14 inches', 1.09, 'Windows 11', 5, '15 hours', 'Lenovo ThinkPad X1 Carbon is a lightweight, high-performance business laptop.', 1);

-- Insert sample data into the ProductImage table
GO
-- Insert Security questions
GO
SET IDENTITY_INSERT [dbo].[SecurityQuestion] ON;

INSERT INTO [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (1, N'What is your favorite song?');
INSERT INTO [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (2, N'Who is your best friend?');
INSERT INTO [dbo].[SecurityQuestion] ([Id], [Question]) VALUES (3, N'Where are you come from?');

SET IDENTITY_INSERT [dbo].[SecurityQuestion] OFF;
GO
SET IDENTITY_INSERT [dbo].[Role] ON;

INSERT INTO [dbo].[Role](id, role_name) VALUES (1, 'Admin');
INSERT INTO [dbo].[Role](id, role_name) VALUES (2, 'Customer');
INSERT INTO [dbo].[Role](id, role_name) VALUES (3, 'Staff') ;

SET IDENTITY_INSERT [dbo].[Role] OFF;
SELECT * FROM SecurityQuestion
SELECT * FROM Role
SET IDENTITY_INSERT [dbo].[Account] ON;
INSERT INTO Account(id, username, password, role_Id, isActive) VALUES (2, 'admin', 'admin@123', '1', '1');
SET IDENTITY_INSERT [dbo].[Account] OFF;
INSERT INTO dbo.Storage (Storage_Size, Price_Bonus, Status) VALUES
('256GB SSD', 0.00, 1),
('512GB SSD', 25.00, 1),
('1TB SSD', 50.00, 1);
INSERT INTO dbo.RAM (RAM, Price_Bonus, Status) VALUES
('8GB DDR4', 0.00, 1),
('16GB DDR5', 50.00, 1),
('32GB DDR5', 100.00, 1);
SET IDENTITY_INSERT [dbo].[Sale] ON 

INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (1, CAST(0.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (2, CAST(10.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (3, CAST(15.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (4, CAST(20.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (5, CAST(25.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (6, CAST(30.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (7, CAST(40.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (9, CAST(45.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (10, CAST(32.00 AS Decimal(5, 2)))
INSERT [dbo].[Sale] ([Id], [Percent]) VALUES (11, CAST(50.00 AS Decimal(5, 2)))
SET IDENTITY_INSERT [dbo].[Sale] OFF  
-- Insert into ProductVariant table
INSERT INTO ProductVariant (product_id, RAM_id, Storage_id, quantity, variant_price, sale_id, status)
VALUES 
(1, 1, 1, 10, 1099.99, 1,1),
(2, 2, 2, 5, 2299.99,  1, 1),
(3, 1, 1, 20, 1399.99,   1,1),
(4, 1, 2, 8, 1599.99, 1, 1),
(5, 1, 2, 12, 1899.99,1, 1);