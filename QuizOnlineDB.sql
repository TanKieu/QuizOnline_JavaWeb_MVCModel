USE [master]
GO
/****** Object:  Database [QuizOnline]    Script Date: 21/02/2021 1:28:08 CH ******/
CREATE DATABASE [QuizOnline]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\QuizOnline.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QuizOnline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\QuizOnline_log.ldf' , SIZE = 1280KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QuizOnline] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnline] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnline] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuizOnline] SET DELAYED_DURABILITY = DISABLED 
GO
USE [QuizOnline]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 21/02/2021 1:28:08 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[email] [nvarchar](70) NOT NULL,
	[password] [nvarchar](70) NOT NULL,
	[name] [nvarchar](70) NULL,
	[role] [nchar](20) NOT NULL,
	[status] [nchar](10) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 21/02/2021 1:28:08 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[questionID] [varchar](20) NOT NULL,
	[question] [nvarchar](700) NOT NULL,
	[A] [nvarchar](700) NOT NULL,
	[B] [nvarchar](700) NOT NULL,
	[C] [nvarchar](700) NULL,
	[D] [nvarchar](700) NULL,
	[answer] [nchar](10) NOT NULL,
	[createDate] [date] NOT NULL,
	[SubjectID] [nvarchar](20) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[questionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz_details]    Script Date: 21/02/2021 1:28:08 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz_details](
	[QuizID] [nvarchar](70) NOT NULL,
	[questionID] [varchar](20) NOT NULL,
	[answer] [nchar](10) NOT NULL,
	[correctAnswer] [nchar](10) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz_Record]    Script Date: 21/02/2021 1:28:08 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz_Record](
	[QuizID] [nvarchar](70) NOT NULL,
	[email] [nvarchar](70) NOT NULL,
	[SubjectID] [nvarchar](20) NOT NULL,
	[result] [float] NOT NULL,
 CONSTRAINT [PK_Quiz_Record] PRIMARY KEY CLUSTERED 
(
	[QuizID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 21/02/2021 1:28:08 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[SubjectID] [nvarchar](20) NOT NULL,
	[Subject_name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[SubjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([email], [password], [name], [role], [status]) VALUES (N'kieunhattan2000@yahoo.com', N'ₔ뗸ɧᏩ闐窟㘑욠㠄찮嫧뛩㹡䌁댋', N'kieu tan', N'Admin               ', N'New       ')
INSERT [dbo].[Account] ([email], [password], [name], [role], [status]) VALUES (N'winnhat2000@gmail.com', N'ₔ뗸ɧᏩ闐窟㘑욠㠄찮嫧뛩㹡䌁댋', N'Win Kieu', N'Student             ', N'New       ')
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H001', N'Aggregate plans define output levels over a planning horizon of:', N'four to twelve weeks.', N'one to two years.', N'three to six months.', N'six to twelve months.', N'B         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H002', N'Which of the following is NOT one of the general decision options available in developing aggregate plans in the face of fluctuating demand?', N'Supplier changes', N'Production rate changes', N'Workforce changes', N'Inventory changes', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H003', N'Which of the following is the correct sequence for disaggregating an aggregate production plan?', N' Materials requirements planning (MRP), capacity requirements planning (CRP), and master production scheduling (MPS)', N'Master production scheduling (MPS), materials requirements planning (MRP), and capacity requirements planning (CRP)', N'Capacity requirements planning (CRP), materials requirements planning (MRP), and master production scheduling (MPS)', N'Master production scheduling (MPS), capacity requirements planning (CRP), and materials requirements planning (MRP)', N'B         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H004', N'The master production schedule (MPS) is usually determined by the known customer orders, also known as firm orders, in:', N'make-to-stock industries.', N'make-to-assemble industries.', N'assemble-to-order industries.', N'make-to-order industries.', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H005', N'Components can be any of the following EXCEPT:', N'raw materials.
', N'purchased parts.', N'manufactured parts.', N'end items.', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H006', N'Concepts closely associated with materials requirements planning (MRP) include all of the following EXCEPT:', N'dependent demand.', N'lot sizing.', N'labor resources.', N'time phasing.', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H007', N'Which of the following is NOT a hierarchical record that is based on dependent demand?', N'Bill of materials (BOM)', N'Bill of resources (BOR)', N'Bill of labor (BOL)', N'Bill of lading (BOL)
D', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H008', N'The process of using the logic of dependent demand to calculate the quantity and timing of orders for all subassemblies and components that go into and support the production of the end items is called:', N'master scheduling.', N'disaggregation.', N'MRP explosion.', N'load reporting.', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H009', N'The length of time buckets in materials requirements planning (MRP) is usually a ________ .', N'day', N'quarter', N'week', N'month', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H010', N'The periodic-order quantity (POQ) orders a quantity equal to the gross requirement quantity in one or more predetermined time periods:', N' minus the projected on-hand quantity of the previous time period.', N'minus the projected on-hand quantity of one or more predetermined time periods.', N'minus the projected on-hand quantity of the current time period.', N'minus the projected on-hand quantity of the next time period.
A

Lot-for-lot (LFL) is an', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H011', N'Lot-for-lot (LFL) is an order schedule that covers each time period''s:', N'gross requirements.', N'planned order releases.', N'scheduled receipts.', N'projected on-hand inventory.', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H012', N'Using the periodic-order quantity (POQ) lot sizing rule, the projected on-hand inventory will:', N' be a positive integer at the end of the POQ time interval.', N'be a negative integer at the end of the POQ time interval.', N'equal zero at the end of the POQ time interval.', N'equal infinite at the end of the POQ time interval.', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H013', N'The economical time interval used in the periodic-order quantity lot sizing approach is determined by dividing the economic order quantity by', N'annual demand.', N'number of orders.', N'on-hand inventory.', N'gross requirements.', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H014', N'An economic-based periodic-order quantity (POQ) model is best applied when:', N' inventory-carrying costs and setup/order costs are very high.', N'inventory-carrying costs are high and setup/order costs are low.', N'inventory-carrying costs and setup/order costs are moderate.', N' inventory-carrying costs are low and setup/order costs are high.', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H015', N' inventory-carrying costs are low and setup/order costs are high.', N'gross requirements.', N'capacity requirements.', N'scheduled receipts.', N'planned order receipts.', N'B         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H016', N'The words "product family," "budget allocation" and "long-term" fit best with which level of the generic framework for resource planning?', N'Aggregate planning Level 1', N'Disaggregation Level 2', N'Execution Level 3', N'Capacity requirements planning', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H017', N'Which of the following is not an aggregate planning decision option?', N'Pricing and promotions', N'Subcontracting', N'Layoffs', N'Building a new plant
', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H018', N'The primary output of an MRP system is a time-phased report that gives all of the following except', N'The facilities managers a detailed schedule for acquiring additional factory space', N'The accounting and financial functions production information that drives cash flow, budgets, and financial needs', N'The production managers a detailed schedule for manufacturing the product and controlling manufacturing inventories', N' The purchasing department a schedule for obtaining raw material and purchased items', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H019', N'A(n) ____ is a statement of how many finished items are to be produced and when they are to be produced.', N'A(n) ____ is a statement of how many finished items are to be produced and when they are to be produced.', N'Master Production Schedule', N'Material Requirements Plan', N'Capacity Requirements Plan', N'B         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H020', N'Assigning people to tasks, setting priorities for jobs and scheduling equipment fits best with which level of the generic framework for resource planning?', N'Aggregate planning Level 1', N'Disaggregation Level 2', N'Execution Level 3', N'Capacity requirements planning', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H021', N'The Lot-for-Lot (LFL) rule', N'Minimizes purchase or setup costs', N'Allows the firm to take advantage of quantity discounts (price breaks) by suppliers', N' Is best applied when inventory carrying costs are high and setup/order costs are low', N' Masks the true nature of dependent demand', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H022', N'An inventory item can be', N'An inventory item can be', N'Only a component', N'Both a parent and a component', N' Either a parent or a component, but not both', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H023', N'If forecast demand exceeds the total factory or supply capacity, managers might simply decide not to meet forecast demand. This decision would most likely be made at which planning level?', N'Aggregate planning Level 1', N'Disaggregation Level 2', N'Execution Level 3', N'Capacity requirements planning', N'A         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H024', N'The direct inputs to material requirements planning include all of the following except', N'Master Production Schedule', N'Inventory, SKU, and Transaction files', N'Bills of Material', N'Capacity Requirements Plan', N'D         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H025', N'The purpose of aggregate planning is to', N' Minimize the work force size', N'Maximize the production rate', N'Minimize the cost of meeting demand', N'Optimize the inventory level', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'H026', N'Capacity requirements are computed by multiplying the number of units scheduled for production at a work center by', N'The unit resource requirements minus the setup time', N' The unit resource requirements plus the setup time', N'The unit resource requirements and then adding in the setup time', N'The unit resource requirements and then subtracting the setup time', N'C         ', CAST(N'2021-01-25' AS Date), N'SubH', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'MBK001', N'In addition to fiscal policy, the other main tool used to affect aggregate demand is', N'trade policy.', N'industrial policy.', N'planning policy.', N'monetary policy.', N'd         ', CAST(N'2021-01-27' AS Date), N'MBK', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'MBK002', N'Nowadays, most observers believe that monetary policy', N' is less important than fiscal policy.', N' is more important than fiscal policy.', N'and fiscal policy are equally important.', N'and fiscal policy are both unimportant.', N'B         ', CAST(N'2021-01-28' AS Date), N'MBK', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'MBK003', N'Which of the following was a result of the Dodd-Frank Act?', N'restricted bank activities', N'established a new consumer protection agency', N'tougher regulations for banks', N'All of the above', N'd         ', CAST(N'2021-01-28' AS Date), N'MBK', 1)
INSERT [dbo].[Question] ([questionID], [question], [A], [B], [C], [D], [answer], [createDate], [SubjectID], [status]) VALUES (N'MBK004', N'In a market system, the most dangerous types of bankruptcies involve', N'industrial monopolies.', N'multinational firms.', N'employment agencies.', N'employment agencies.', N'd         ', CAST(N'2021-01-28' AS Date), N'MBK', 1)
INSERT [dbo].[Quiz_details] ([QuizID], [questionID], [answer], [correctAnswer]) VALUES (N'MBK2021-02-04', N'MBK003', N'A         ', N'd         ')
INSERT [dbo].[Quiz_details] ([QuizID], [questionID], [answer], [correctAnswer]) VALUES (N'MBK2021-02-04', N'MBK002', N'B         ', N'B         ')
INSERT [dbo].[Quiz_details] ([QuizID], [questionID], [answer], [correctAnswer]) VALUES (N'MBK2021-02-04', N'MBK004', N'          ', N'd         ')
INSERT [dbo].[Quiz_details] ([QuizID], [questionID], [answer], [correctAnswer]) VALUES (N'MBK2021-02-04', N'MBK001', N'C         ', N'd         ')
INSERT [dbo].[Quiz_Record] ([QuizID], [email], [SubjectID], [result]) VALUES (N'MBK2021-02-04', N'winnhat2000@gmail.com', N'MBK', 2)
INSERT [dbo].[Subject] ([SubjectID], [Subject_name]) VALUES (N'MBK', N'')
INSERT [dbo].[Subject] ([SubjectID], [Subject_name]) VALUES (N'SubH', N'Production Schedule')
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Subject] FOREIGN KEY([SubjectID])
REFERENCES [dbo].[Subject] ([SubjectID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Quiz_details]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_details_Question] FOREIGN KEY([questionID])
REFERENCES [dbo].[Question] ([questionID])
GO
ALTER TABLE [dbo].[Quiz_details] CHECK CONSTRAINT [FK_Quiz_details_Question]
GO
ALTER TABLE [dbo].[Quiz_details]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_details_Quiz_Record] FOREIGN KEY([QuizID])
REFERENCES [dbo].[Quiz_Record] ([QuizID])
GO
ALTER TABLE [dbo].[Quiz_details] CHECK CONSTRAINT [FK_Quiz_details_Quiz_Record]
GO
ALTER TABLE [dbo].[Quiz_Record]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Record_Account] FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Quiz_Record] CHECK CONSTRAINT [FK_Quiz_Record_Account]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline] SET  READ_WRITE 
GO
