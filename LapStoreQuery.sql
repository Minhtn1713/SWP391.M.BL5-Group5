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
    id INT IDENTITY(1,1) PRIMARY KEY,
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
    weight DECIMAL(5, 2),
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