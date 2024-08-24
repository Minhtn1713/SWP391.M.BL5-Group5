
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
    variant_price DECIMAL(10, 2) NULL,
    sale_id INT NULL,
    status INT  NULL,
	CONSTRAINT FK_ProductVariant_Product FOREIGN KEY (product_id) REFERENCES Product(id),
	CONSTRAINT FK_ProductVariant_Ram FOREIGN KEY (RAM_id) REFERENCES dbo.Ram(Id), 
	CONSTRAINT FK_ProductVariant_Storage FOREIGN KEY (Storage_id) REFERENCES Storage(Id),
	CONSTRAINT FK_ProductVariant_Sale FOREIGN KEY (sale_id) REFERENCES Sale(Id)
);
GO
ALTER TABLE ProductVariant ADD  DEFAULT ((1)) FOR status
GO
ALTER TABLE ProductVariant ADD  DEFAULT ((1)) FOR sale_id
GO
CREATE TABLE [dbo].[ProductImage](
	[Id] [bigint] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[Url] [varchar](max) NOT NULL,
	[Product_Id] [int] NOT NULL,
	[Ram_Id] [int] NOT NULL,
	CONSTRAINT FK_ProductImage_Product FOREIGN KEY (Product_Id) REFERENCES Product(Id),
	CONSTRAINT FK_ProductImage_RAM FOREIGN KEY (Ram_Id) REFERENCES RAM(Id)
);
CREATE TABLE [dbo].[Security](
	[question_Id] [bigint],
	[account_Id] [bigint] NOT NULL PRIMARY KEY,
	[answer] [ntext] NOT NULL,
	)
GO

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
INSERT INTO ProductVariant (product_id, RAM_id, Storage_id, quantity, variant_price,  sale_id, status)
VALUES 
(1, 1, 1, 10, 999.99,  1,1),
(2, 2, 2, 5, 2074.99,  1, 1),
(3, 1, 1, 20, 1299.99,  1,1),
(4, 1, 2, 8, 1524.99, 1, 1),
(5, 1, 2, 12, 1824.99,1, 1);

SET IDENTITY_INSERT [dbo].[ProductImage] ON
INSERT INTO [ProductImage] ([Id],[Url], [Product_Id], [Ram_Id])
VALUES 
(1, N'DellXPS13.jpg', 1,2),
(2, N'MacBookPro14.jpg',1, 2),
(3, N'HPSpectrex360.jpg', 1,2),
(4, N'AsusROGZephyrusG14.jpg',1, 2),
(5, N'LenovoThinkPadX1Carbon.jpg',1, 2);
SET IDENTITY_INSERT [dbo].[ProductImage] OFF


/****** Object:  Table [dbo].[Blog]    Script Date: 7/25/2023 8:47:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](max) NULL,
	[categoryId] [int] NOT NULL,
	[userId] [bigint] NOT NULL,
	[status] [int] NULL,
	[coverImg] [varchar](max) NULL,
	[date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BlogCategory]    Script Date: 7/25/2023 8:47:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BlogCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SubBlog]    Script Date: 7/25/2023 8:47:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubBlog](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](max) NULL,
	[content] [nvarchar](max) NULL,
	[img] [nvarchar](max) NULL,
	[blogId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Blog] ADD  DEFAULT ((1)) FOR [status];
ALTER TABLE [dbo].[Blog] ADD  DEFAULT (getdate()) FOR [date];
ALTER TABLE [dbo].[BlogCategory] ADD  DEFAULT ((1)) FOR [status];
GO
ALTER TABLE [dbo].[Blog]  WITH CHECK ADD FOREIGN KEY([categoryId])
REFERENCES [dbo].[BlogCategory] ([id])
GO
ALTER TABLE [dbo].[Blog]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[SubBlog]  WITH CHECK ADD FOREIGN KEY([blogId])
REFERENCES [dbo].[Blog] ([id])

INSERT INTO [dbo].[Account] ([username], [password], [role_Id], [isActive]) VALUES
('john_doe', 'password123', 1, 1),  
('jane_smith', 'password456', 2, 1),
('emily_johnson', 'password789', 2, 1), 
('linda_davis', 'password999', 1, 1);  

INSERT INTO [User] ([id], [fullname], [phone], [email], [address], [gender]) VALUES 
(2, N'John Doe', '1234567890', 'johndoe@example.com', N'123 Main St, New York, NY', 1),
(3, N'Jane Smith', '0987654321', 'janesmith@example.com', N'456 Park Ave, Los Angeles, CA', 0),
(4, N'Emily Johnson', '1231231234', 'emilyj@example.com', N'789 Broadway, San Francisco, CA', 0),
(5, N'Michael Brown', '4564564567', 'michaelb@example.com', N'101 Elm St, Chicago, IL', 1),
(6, N'Linda Davis', '7897897890', 'lindad@example.com', N'202 Maple Ave, Houston, TX', 0);

GO
SET IDENTITY_INSERT [dbo].[Blog] ON 

INSERT [dbo].[Blog] ([id], [title], [categoryId], [userId], [status], [coverImg], [date]) VALUES (5, N'Dell XPS 13: Review and Performance Insights', 2, 2, 1, N'DellXPS13.jpg', CAST(N'2023-07-25' AS Date))
INSERT [dbo].[Blog] ([id], [title], [categoryId], [userId], [status], [coverImg], [date]) VALUES (6, N'MacBook Pro 14 vs. Dell XPS 13: Which One Should You Buy?', 3, 2, 1, N'MacBookPro14.jpg', CAST(N'2023-07-25' AS Date))
INSERT [dbo].[Blog] ([id], [title], [categoryId], [userId], [status], [coverImg], [date]) VALUES (7, N'How to Optimize Your HP Spectre x360 for Best Battery Life', 4, 2, 1, N'HPSpectrex360.jpg', CAST(N'2023-07-25' AS Date))
INSERT [dbo].[Blog] ([id], [title], [categoryId], [userId], [status], [coverImg], [date]) VALUES (8, N'Asus ROG Zephyrus G14: Gaming Laptop of the Year?', 1, 2, 1, N'AsusROGZephyrusG14.jpg', CAST(N'2023-07-25' AS Date))
INSERT [dbo].[Blog] ([id], [title], [categoryId], [userId], [status], [coverImg], [date]) VALUES (9, N'Lenovo ThinkPad X1 Carbon: A Business Laptop for Professionals', 5, 2, 1, N'LenovoThinkPadX1Carbon.jpg', CAST(N'2023-07-25' AS Date))

SET IDENTITY_INSERT [dbo].[Blog] OFF
GO
SET IDENTITY_INSERT [dbo].[BlogCategory] ON 

GO
SET IDENTITY_INSERT [dbo].[BlogCategory] ON 

INSERT [dbo].[BlogCategory] ([id], [categoryName], [status]) VALUES (1, N'News', 1)
INSERT [dbo].[BlogCategory] ([id], [categoryName], [status]) VALUES (2, N'Reviews', 1)
INSERT [dbo].[BlogCategory] ([id], [categoryName], [status]) VALUES (3, N'Updates and Features', 1)
INSERT [dbo].[BlogCategory] ([id], [categoryName], [status]) VALUES (4, N'Tips', 1)
INSERT [dbo].[BlogCategory] ([id], [categoryName], [status]) VALUES (5, N'Tricks', 1)
SET IDENTITY_INSERT [dbo].[BlogCategory] OFF
GO

SET IDENTITY_INSERT [dbo].[BlogCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[SubBlog] ON 

INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (3, N'Dell XPS 13: Review and Performance Insights', N'The Dell XPS 13 continues to be one of the best ultrabooks on the market. With a sleek design, powerful performance, and stunning display, it’s a top choice for both work and entertainment.', N'DellXPS13.jpg', 5)
INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (4, N'Dell XPS 13: Design and Build Quality', N'The XPS 13 features a sturdy aluminum body, near bezel-less display, and an impressive keyboard. It’s lightweight, making it perfect for users on the go.', N'DellXPS13.jpg', 5)

INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (5, N'MacBook Pro 14 vs. Dell XPS 13: Display and Performance Comparison', N'Both the MacBook Pro 14 and the Dell XPS 13 offer incredible displays, but how do they compare in real-world performance? Find out which one is better for your needs.', N'MacBookPro14.jpg', 6)
INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (6, N'MacBook Pro 14: M1 Pro Chip Benchmarks', N'The M1 Pro chip inside the MacBook Pro 14 delivers industry-leading performance for both creative professionals and casual users alike. See how it compares to its rivals.', N'MacBookPro14.jpg', 6)

INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (7, N'How to Optimize Your HP Spectre x360 for Best Battery Life', N'The HP Spectre x360 offers great battery life, but there are a few tricks you can use to make it even better. Learn how to extend your usage throughout the day.', N'HPSpectrex360.jpg', 7)
INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (8, N'Tips for HP Spectre x360 Performance', N'Boost your HP Spectre x360 performance with these tips. From managing your startup programs to keeping your drivers up to date, these small changes can make a big difference.', N'HPSpectrex360.jpg', 7)

INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (9, N'Asus ROG Zephyrus G14: Gaming Laptop of the Year?', N'The Asus ROG Zephyrus G14 is hailed as one of the best gaming laptops of 2023. With top-notch specs and a unique design, this laptop is perfect for serious gamers.', N'AsusROGZephyrusG14.jpg', 8)
INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (10, N'Asus ROG Zephyrus G14: Best Features for Gamers', N'From its impressive cooling system to its customizable AniMe Matrix display, discover why the Asus ROG Zephyrus G14 stands out in the crowded gaming laptop market.', N'AsusROGZephyrusG14.jpg', 8)

INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (11, N'Lenovo ThinkPad X1 Carbon: The Ultimate Business Laptop', N'The Lenovo ThinkPad X1 Carbon combines durability, power, and portability, making it a top choice for business professionals.', N'LenovoThinkPadX1Carbon.jpg', 9)
INSERT [dbo].[SubBlog] ([id], [title], [content], [img], [blogId]) VALUES (12, N'Lenovo ThinkPad X1 Carbon: Security and Productivity Features', N'With a range of security features and productivity tools, the ThinkPad X1 Carbon is designed for business professionals who need a reliable and secure laptop.', N'LenovoThinkPadX1Carbon.jpg', 9)

SET IDENTITY_INSERT [dbo].[SubBlog] OFF
