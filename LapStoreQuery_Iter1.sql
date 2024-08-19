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


-- Create ProductImage table
CREATE TABLE ProductImage (
    id INT IDENTITY(1,1) PRIMARY KEY,
    url NVARCHAR(255)
);
select * from Product
insert into ProductVariant (product_id, image_id , RAM , storage , quantity , variant_price, sale_id, status) values
(1,1,'16Gb','512Gb',2,50,1,1),
(2,2,'32Gb','1Tb',2,100,1,1);
-- Create ProductVariant table
CREATE TABLE ProductVariant (
    id INT IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    image_id INT NOT NULL,
    RAM NVARCHAR(100),
    storage NVARCHAR(50),
    quantity INT,
    variant_price DECIMAL(10, 2),
    sale_id INT,
    status INT NOT NULL,
    CONSTRAINT FK_ProductVariant_Product FOREIGN KEY (product_id) REFERENCES Product(id),
    CONSTRAINT FK_ProductVariant_ProductImage FOREIGN KEY (image_id) REFERENCES ProductImage(id)
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
SET IDENTITY_INSERT [dbo].[ProductImage] ON;
INSERT INTO ProductImage (id, url)
VALUES 
(1, N'DellXPS13.jpg'),
(2, N'MacBookPro14.jpg'),
(3, N'HPSpectrex360.jpg'),
(4, N'AsusROGZephyrusG14.jpg'),
(5, N'LenovoThinkPadX1Carbon.jpg');
SET IDENTITY_INSERT [dbo].[ProductImage] OFF;
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

create table [Order](
id int identity(1,1) primary key,
user_id bigint not null,
total_price DECIMAL(10, 2) NOT NULL,
created_date DATETIME DEFAULT GETDATE(),
name nvarchar(max) null,
address nvarchar(max) null,
phone varchar(12) null,
status int not null,
constraint FK_Order_User foreign key(user_id) references [User](id)
);
insert into [Order](user_id,total_price,name,address,phone,status) values 
(3,500,'Tran Tien Dung','Tro Thanh Lang, Ngo 1 cay xang 39, Thach That, Hanoi','0963177099',1)
create table OrderDetail(
id int identity(1,1) primary key,
order_id int not null,
productVariant_id int not null,
quantity int not null,
constraint FK_OrderDetail_Order foreign key(order_id) references [Order](id),
constraint productVariant_id foreign key(productVariant_id) references ProductVariant(id)
);
insert into OrderDetail(order_id,productVariant_id,quantity) values 
(2,2,1),
(2,3,2)
INSERT INTO [Account](username, password, role_Id, isActive) VALUES
('dungle2107','1',1,1),
('admin','1',1,1),
('john_doe', 'password123', 2, 1),
('jane_smith', 'securePass', 3, 1),
('alex_jones', 'alex@123', 2, 0),
('mary_jane', 'mjane456', 1, 1),
('peter_parker', 'spiderman', 2, 1),
('tony_stark', 'ironman', 3, 1),
('bruce_wayne', 'batman', 2, 1),
('clark_kent', 'superman', 1, 1),
('diana_prince', 'wonderwoman', 2, 0),
('barry_allen', 'flash123', 2, 1),
('arthur_curry', 'aquaman', 3, 1),
('victor_stone', 'cyborg', 2, 1),
('oliver_queen', 'greenarrow', 1, 1),
('hal_jordan', 'greenlantern', 2, 1),
('john_constantine', 'hellblazer', 3, 0);
INSERT INTO [User](id, fullname, phone, email, address, gender) VALUES
(2, 'Admin','01234567444','admin@example.com','Ha Noi',1),
(3, 'Tran Tien Dung','0963177099','ttd21072004@gmail.com','Ha Noi',1),
(4, 'Admin','01234656789','dsadas@example.com','Viet Nam',1),
(5, 'John Doe', '0912345678', 'john.doe@example.com', 'New York', 1),
(6, 'Jane Smith', '0923456789', 'jane.smith@example.com', 'Los Angeles', 0),
(7, 'Alex Jones', '0934567890', 'alex.jones@example.com', 'Chicago', 1),
(8, 'Mary Jane', '0945678901', 'mary.jane@example.com', 'Houston', 0),
(9, 'Peter Parker', '0956789012', 'peter.parker@example.com', 'Queens', 1),
(10, 'Tony Stark', '0967890123', 'tony.stark@example.com', 'Malibu', 1),
(11, 'Bruce Wayne', '0978901234', 'bruce.wayne@example.com', 'Gotham', 1),
(12, 'Clark Kent', '0989012345', 'clark.kent@example.com', 'Metropolis', 1),
(13, 'Diana Prince', '0990123456', 'diana.prince@example.com', 'Themyscira', 0),
(14, 'Barry Allen', '0901234567', 'barry.allen@example.com', 'Central City', 1),
(15, 'Arthur Curry', '0912345670', 'arthur.curry@example.com', 'Atlantis', 1),
(16, 'Victor Stone', '0923456701', 'victor.stone@example.com', 'Detroit', 1),
(17, 'Oliver Queen', '0934567012', 'oliver.queen@example.com', 'Star City', 1),
(18, 'Hal Jordan', '0945670123', 'hal.jordan@example.com', 'Coast City', 1),
(19, 'John Constantine', '0956780123', 'john.constantine@example.com', 'London', 1);

