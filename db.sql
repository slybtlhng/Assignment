USE [master]
GO
/****** Object:  Database [JOURNEYTOTHEWEST]    Script Date: 9/5/2020 11:10:07 AM ******/
CREATE DATABASE [JOURNEYTOTHEWEST]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'JOURNEYTOTHEWEST', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\JOURNEYTOTHEWEST.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'JOURNEYTOTHEWEST_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\JOURNEYTOTHEWEST_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JOURNEYTOTHEWEST].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ARITHABORT OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET  ENABLE_BROKER 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET RECOVERY FULL 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET  MULTI_USER 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'JOURNEYTOTHEWEST', N'ON'
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET QUERY_STORE = OFF
GO
USE [JOURNEYTOTHEWEST]
GO
/****** Object:  UserDefinedFunction [dbo].[getRoleName]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create function [dbo].[getRoleName] (@userID as varchar(10))
returns VARCHAR(100)
AS
BEGIN
	return (SELECT roleName FROM tblUser WHERE userID = @userID)
END
GO
/****** Object:  Table [dbo].[tbl_Flower]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Flower](
	[ID] [varchar](13) NOT NULL,
	[Location] [varchar](100) NULL,
	[FlowerName] [varchar](100) NULL,
	[Description] [varchar](500) NULL,
	[StartTime] [datetime] NULL,
	[EndTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCastRegistration]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCastRegistration](
	[castID] [varchar](10) NULL,
	[partID] [varchar](10) NULL,
	[directorID] [varchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblNotification]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblNotification](
	[notiNo] [int] IDENTITY(1,1) NOT NULL,
	[notiID] [varchar](10) NOT NULL,
	[notiDescription] [nvarchar](100) NOT NULL,
	[userID] [varchar](10) NULL,
	[dateCreate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[notiID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPart]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPart](
	[partNo] [int] IDENTITY(1,1) NOT NULL,
	[partID] [varchar](10) NOT NULL,
	[partName] [nvarchar](100) NOT NULL,
	[partDescription] [nvarchar](100) NULL,
	[tribulationID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[partID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProperties]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProperties](
	[propNo] [int] IDENTITY(1,1) NOT NULL,
	[propID] [varchar](10) NOT NULL,
	[propName] [nvarchar](100) NOT NULL,
	[propImage] [nvarchar](max) NOT NULL,
	[propDescription] [nvarchar](100) NOT NULL,
	[propQuantity] [int] NOT NULL,
	[propStatus] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[propID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTribulation]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTribulation](
	[tribulationNo] [int] IDENTITY(1,1) NOT NULL,
	[tribulationID] [varchar](10) NOT NULL,
	[adminID] [varchar](10) NULL,
	[directorID] [varchar](10) NULL,
	[tribulationName] [nvarchar](100) NOT NULL,
	[tribulationDescription] [nvarchar](100) NOT NULL,
	[tribulationAddress] [nvarchar](100) NOT NULL,
	[tribulationStartTime] [date] NOT NULL,
	[tribulationEndTime] [date] NULL,
	[tribulationCount] [tinyint] NULL,
	[toolCount] [int] NOT NULL,
	[tribulationFile] [nvarchar](max) NOT NULL,
	[tribulationStatus] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tribulationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTribulation_Properties]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTribulation_Properties](
	[tribulationID] [varchar](10) NULL,
	[propID] [varchar](10) NULL,
	[propQuantity] [int] NOT NULL,
	[directorID] [varchar](10) NULL,
	[timeStart] [datetime] NULL,
	[timeEnd] [datetime] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 9/5/2020 11:10:07 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[userNo] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](10) NOT NULL,
	[roleName] [varchar](100) NOT NULL,
	[userName] [nvarchar](100) NOT NULL,
	[userPass] [varchar](100) NOT NULL,
	[userFullName] [nvarchar](100) NOT NULL,
	[userImage] [nvarchar](max) NOT NULL,
	[userDescription] [nvarchar](max) NOT NULL,
	[userEmail] [varchar](50) NOT NULL,
	[userPhone] [varchar](10) NOT NULL,
	[userStatus] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0189', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0021', N'PART0184', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0028', N'PART0182', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0028', N'PART0183', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0188', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0021', N'PART0187', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0202', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0021', N'PART0204', N'U0032')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0168', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0167', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0021', N'PART0165', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0006', N'PART0221', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0021', N'PART0219', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0037', N'PART0216', N'U0003')
INSERT [dbo].[tblCastRegistration] ([castID], [partID], [directorID]) VALUES (N'U0028', N'PART0214', N'U0003')
SET IDENTITY_INSERT [dbo].[tblNotification] ON 

INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (1, N'N0001', N'Admin Update your profile', N'U0033', CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (2, N'N0002', N'Admin Choose you for Tribulation Escape1', N'U0032', CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (3, N'N0003', N'director3 Add you to Part Pigsy of Escape1', N'U0006', CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (4, N'N0004', N'director3 Add you to Part White Dragon Horse of Escape1', N'U0021', CAST(N'2020-07-21' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (5, N'N0005', N'Admin Choose you for Tribulation Escape4', N'U0032', CAST(N'2020-07-23' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (6, N'N0006', N'Ngo Tan Ducc Add you to Part White Dragon Horse of Test1', N'U0006', CAST(N'2020-07-23' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (7, N'N0007', N'Ngo Tan Ducc Add you to Part Sandy of Test1', N'U0006', CAST(N'2020-07-23' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (8, N'N0008', N'Ngo Tan Ducc Add you to Part Monekey King of Test1', N'U0021', CAST(N'2020-07-23' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (9, N'N0009', N'Admin Choose you for Tribulation Woman World', N'U0003', CAST(N'2020-07-28' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (10, N'N0010', N'Ngo Tan Ducc Add you to Part The beauty3 of Woman World', N'U0006', CAST(N'2020-07-28' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (11, N'N0011', N'Ngo Tan Ducc Add you to Part The beauty1 of Woman World', N'U0021', CAST(N'2020-07-28' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (12, N'N0012', N'Ngo Tan Ducc Add you to Part Pigsy of Woman World', N'U0037', CAST(N'2020-07-28' AS Date))
INSERT [dbo].[tblNotification] ([notiNo], [notiID], [notiDescription], [userID], [dateCreate]) VALUES (13, N'N0013', N'Ngo Tan Ducc Add you to Part Tang Seng of Woman World', N'U0028', CAST(N'2020-07-28' AS Date))
SET IDENTITY_INSERT [dbo].[tblNotification] OFF
SET IDENTITY_INSERT [dbo].[tblPart] ON 

INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (164, N'PART0164', N'Tang Seng', N'The teacher', N'T0030')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (165, N'PART0165', N'Monekey King', N'The monkey', N'T0030')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (166, N'PART0166', N'Pigsy', N'The pig', N'T0030')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (167, N'PART0167', N'Sandy', N'The person', N'T0030')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (168, N'PART0168', N'White Dragon Horse', N'The horse', N'T0030')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (182, N'PART0182', N'Tang Seng', N'The teacher', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (183, N'PART0183', N'Monekey King', N'The monkey', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (184, N'PART0184', N'Pigsy', N'The pig', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (185, N'PART0185', N'Sandy', N'The person', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (186, N'PART0186', N'White Dragon Horse', N'The horse', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (187, N'PART0187', N'Monster1', N'The monster who want to eat Tang Seng', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (188, N'PART0188', N'Monster2', N'The monster who want to eat Tang Seng', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (189, N'PART0189', N'Monster3', N'The monster who want to eat Tang Seng', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (190, N'PART0190', N'Bodhisattva', N'Who want to help everybody', N'T0033')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (200, N'PART0200', N'Tang Seng', N'The teacher', N'T0035')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (201, N'PART0201', N'Monekey King', N'The monkey', N'T0035')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (202, N'PART0202', N'Pigsy', N'The pig', N'T0035')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (203, N'PART0203', N'Sandy', N'The person', N'T0035')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (204, N'PART0204', N'White Dragon Horse', N'The horse', N'T0035')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (205, N'PART0205', N'Tang Seng', N'The teacher', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (206, N'PART0206', N'Monekey King', N'The monkey', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (207, N'PART0207', N'Pigsy', N'The pig', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (208, N'PART0208', N'Sandy', N'The person', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (209, N'PART0209', N'White Dragon Horse', N'The horse', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (210, N'PART0210', N'Monster1', N'The monster who want to eat Tang Seng', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (211, N'PART0211', N'Monster2', N'The monster who want to eat Tang Seng', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (212, N'PART0212', N'Monster3', N'The monster who want to eat Tang Seng', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (213, N'PART0213', N'Bodhisattva', N'Who want to help everybody', N'T0036')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (214, N'PART0214', N'Tang Seng', N'The teacher', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (215, N'PART0215', N'Monekey King', N'The monkey', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (216, N'PART0216', N'Pigsy', N'The pig', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (217, N'PART0217', N'Sandy', N'The person', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (218, N'PART0218', N'The queen', N'Who want to marry TangSeng', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (219, N'PART0219', N'The beauty1', N'Who want to eat TangSeng', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (220, N'PART0220', N'The beauty2', N'Who want to eat TangSeng', N'T0037')
INSERT [dbo].[tblPart] ([partNo], [partID], [partName], [partDescription], [tribulationID]) VALUES (221, N'PART0221', N'The beauty3', N'Who want to eat TangSeng', N'T0037')
SET IDENTITY_INSERT [dbo].[tblPart] OFF
SET IDENTITY_INSERT [dbo].[tblProperties] ON 

INSERT [dbo].[tblProperties] ([propNo], [propID], [propName], [propImage], [propDescription], [propQuantity], [propStatus]) VALUES (4, N'PROP0004', N'Camera', N'Camera.jpg', N'This is camera', 15, 1)
INSERT [dbo].[tblProperties] ([propNo], [propID], [propName], [propImage], [propDescription], [propQuantity], [propStatus]) VALUES (7, N'PROP0007', N'Sword', N'Sword.jpg', N'To kill somebody', 10, 1)
INSERT [dbo].[tblProperties] ([propNo], [propID], [propName], [propImage], [propDescription], [propQuantity], [propStatus]) VALUES (10, N'PROP0010', N'Roi', N'Roi.jpg', N'', 35, 1)
INSERT [dbo].[tblProperties] ([propNo], [propID], [propName], [propImage], [propDescription], [propQuantity], [propStatus]) VALUES (11, N'PROP0011', N'Clapper board', N'Clapper board.jpg', N'', 100, 1)
INSERT [dbo].[tblProperties] ([propNo], [propID], [propName], [propImage], [propDescription], [propQuantity], [propStatus]) VALUES (12, N'PROP0012', N'Gay', N'Gay.jpg', N'', 980, 1)
SET IDENTITY_INSERT [dbo].[tblProperties] OFF
SET IDENTITY_INSERT [dbo].[tblTribulation] ON 

INSERT [dbo].[tblTribulation] ([tribulationNo], [tribulationID], [adminID], [directorID], [tribulationName], [tribulationDescription], [tribulationAddress], [tribulationStartTime], [tribulationEndTime], [tribulationCount], [toolCount], [tribulationFile], [tribulationStatus]) VALUES (30, N'T0030', N'U0001', N'U0003', N'Test1', N'Test1', N'studio2', CAST(N'2020-07-01' AS Date), CAST(N'2020-07-30' AS Date), 1, 0, N'Desert.txt', 1)
INSERT [dbo].[tblTribulation] ([tribulationNo], [tribulationID], [adminID], [directorID], [tribulationName], [tribulationDescription], [tribulationAddress], [tribulationStartTime], [tribulationEndTime], [tribulationCount], [toolCount], [tribulationFile], [tribulationStatus]) VALUES (33, N'T0033', N'U0001', N'U0032', N'Escape', N'escape room', N'studio1', CAST(N'2020-07-01' AS Date), CAST(N'2020-07-31' AS Date), 10, 0, N'Escape.txt', 1)
INSERT [dbo].[tblTribulation] ([tribulationNo], [tribulationID], [adminID], [directorID], [tribulationName], [tribulationDescription], [tribulationAddress], [tribulationStartTime], [tribulationEndTime], [tribulationCount], [toolCount], [tribulationFile], [tribulationStatus]) VALUES (35, N'T0035', N'U0001', N'U0032', N'Escape1', N'Escape1', N'studio1', CAST(N'2020-07-01' AS Date), CAST(N'2020-07-31' AS Date), 10, 0, N'Desert.txt', 1)
INSERT [dbo].[tblTribulation] ([tribulationNo], [tribulationID], [adminID], [directorID], [tribulationName], [tribulationDescription], [tribulationAddress], [tribulationStartTime], [tribulationEndTime], [tribulationCount], [toolCount], [tribulationFile], [tribulationStatus]) VALUES (36, N'T0036', N'U0001', N'U0032', N'Escape4', N'', N'escape', CAST(N'2020-07-01' AS Date), CAST(N'2020-07-31' AS Date), 15, 0, N'Escape.txt', 1)
INSERT [dbo].[tblTribulation] ([tribulationNo], [tribulationID], [adminID], [directorID], [tribulationName], [tribulationDescription], [tribulationAddress], [tribulationStartTime], [tribulationEndTime], [tribulationCount], [toolCount], [tribulationFile], [tribulationStatus]) VALUES (37, N'T0037', N'U0001', N'U0003', N'Woman World', N'', N'studio3', CAST(N'2020-07-01' AS Date), CAST(N'2020-07-31' AS Date), 10, 0, N'The kingdom of a woman.txt', 1)
SET IDENTITY_INSERT [dbo].[tblTribulation] OFF
INSERT [dbo].[tblTribulation_Properties] ([tribulationID], [propID], [propQuantity], [directorID], [timeStart], [timeEnd]) VALUES (N'T0033', N'PROP0004', 50, N'U0032', CAST(N'2020-07-01T00:00:00.000' AS DateTime), CAST(N'2020-07-31T00:00:00.000' AS DateTime))
INSERT [dbo].[tblTribulation_Properties] ([tribulationID], [propID], [propQuantity], [directorID], [timeStart], [timeEnd]) VALUES (N'T0033', N'PROP0004', 70, N'U0032', CAST(N'2020-07-01T00:00:00.000' AS DateTime), CAST(N'2020-07-31T00:00:00.000' AS DateTime))
INSERT [dbo].[tblTribulation_Properties] ([tribulationID], [propID], [propQuantity], [directorID], [timeStart], [timeEnd]) VALUES (N'T0030', N'PROP0010', 15, N'U0003', CAST(N'2020-07-01T00:00:00.000' AS DateTime), CAST(N'2020-07-30T00:00:00.000' AS DateTime))
INSERT [dbo].[tblTribulation_Properties] ([tribulationID], [propID], [propQuantity], [directorID], [timeStart], [timeEnd]) VALUES (N'T0030', N'PROP0012', 20, N'U0003', CAST(N'2020-07-01T00:00:00.000' AS DateTime), CAST(N'2020-07-30T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[tblUser] ON 

INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (1, N'U0001', N'Admin', N'admin', N'admin', N'Nguyen Thi Kim Hang', N'admin.jpg', N'This is the only admin', N'hangntk@fpt.edu.vn', N'0919799125', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (3, N'U0003', N'Director', N'director1', N'director1', N'Ngo Tan Ducc', N'director1.jpg', N'This is the first director', N'ducnt@fpt.edu.vn', N'0919799124', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (4, N'U0004', N'Director', N'director2', N'director2', N'Duong Chau Chau', N'userAva.jpg', N'this is director 2', N'chauchau@gmail.com', N'0972638392', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (6, N'U0006', N'Cast', N'cast3', N'cast3', N'Duong Mich', N'cast3.jpg', N'This is cast 3', N'michtyty@gmail.com', N'0183729238', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (9, N'U0009', N'Director', N'hang', N'hang', N'Nguyen Nguyen', N'hang.jpg', N'', N'hang@fpt.edu.vn', N'0919799122', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (21, N'U0021', N'Cast', N'hihihi', N'11111', N'hihiiiih', N'hihihi.jpg', N'', N'hihi@hiih.com', N'0919799178', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (24, N'U0024', N'Cast', N'hihi', N'12345', N'hihiih', N'userAva.jpg', N'', N'hihih@gigi.com', N'0917294282', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (28, N'U0028', N'Cast', N'test1', N'test1', N'Nguyen Thi Hu Hu', N'test1.jpg', N'hihi', N'huhu@gmail.com', N'0918274829', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (30, N'U0030', N'Cast', N'test2', N'test2', N'test2222222222', N'test2.jpg', N'this is cast 2', N'test2@fpt.vn', N'0247293792', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (32, N'U0032', N'Director', N'director3', N'director3', N'director3', N'userAva.jpg', N'This is director 3', N'director3@gmail.com', N'0284038278', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (33, N'U0033', N'Director', N'director4', N'director4', N'director4', N'userAva.jpg', N'This is director4
', N'director4@gmail.com', N'0284927482', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (34, N'U0034', N'Cast', N'cast4', N'cast4', N'cast4', N'cast4.jpg', N'This is cast 4', N'cast4@gmail.com', N'0284298492', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (35, N'U0035', N'Director', N'director5', N'director5', N'director5', N'director5.jpg', N'', N'director5@gmail.com', N'0284892842', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (36, N'U0036', N'Cast', N'maisan', N'máian', N'Mai', N'maisan.jpg', N'', N'maisan@fpt.edi.vn', N'0284942798', 1)
INSERT [dbo].[tblUser] ([userNo], [userID], [roleName], [userName], [userPass], [userFullName], [userImage], [userDescription], [userEmail], [userPhone], [userStatus]) VALUES (37, N'U0037', N'Cast', N'testActor', N'testActor', N'Test Actor', N'testActor.jpg', N'', N'testactor@fpt.edu.vn', N'0182747829', 1)
SET IDENTITY_INSERT [dbo].[tblUser] OFF
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tblTribu__EDD824FBC2716E26]    Script Date: 9/5/2020 11:10:08 AM ******/
ALTER TABLE [dbo].[tblTribulation] ADD UNIQUE NONCLUSTERED 
(
	[tribulationName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tblUser__66DCF95CF09ADCC1]    Script Date: 9/5/2020 11:10:08 AM ******/
ALTER TABLE [dbo].[tblUser] ADD UNIQUE NONCLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tblUser__D54ADF5566AD2090]    Script Date: 9/5/2020 11:10:08 AM ******/
ALTER TABLE [dbo].[tblUser] ADD UNIQUE NONCLUSTERED 
(
	[userEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__tblUser__E19C9691AF6BF6F9]    Script Date: 9/5/2020 11:10:08 AM ******/
ALTER TABLE [dbo].[tblUser] ADD UNIQUE NONCLUSTERED 
(
	[userPhone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblNotification] ADD  DEFAULT ('1') FOR [notiID]
GO
ALTER TABLE [dbo].[tblPart] ADD  DEFAULT ('1') FOR [partID]
GO
ALTER TABLE [dbo].[tblProperties] ADD  DEFAULT ('1') FOR [propID]
GO
ALTER TABLE [dbo].[tblProperties] ADD  DEFAULT ((0)) FOR [propQuantity]
GO
ALTER TABLE [dbo].[tblTribulation] ADD  DEFAULT ('1') FOR [tribulationID]
GO
ALTER TABLE [dbo].[tblTribulation] ADD  DEFAULT ((0)) FOR [toolCount]
GO
ALTER TABLE [dbo].[tblTribulation_Properties] ADD  DEFAULT ((0)) FOR [propQuantity]
GO
ALTER TABLE [dbo].[tblUser] ADD  DEFAULT ('1') FOR [userID]
GO
ALTER TABLE [dbo].[tblCastRegistration]  WITH CHECK ADD FOREIGN KEY([castID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblCastRegistration]  WITH CHECK ADD FOREIGN KEY([directorID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblCastRegistration]  WITH CHECK ADD FOREIGN KEY([partID])
REFERENCES [dbo].[tblPart] ([partID])
GO
ALTER TABLE [dbo].[tblNotification]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tblPart]  WITH CHECK ADD FOREIGN KEY([tribulationID])
REFERENCES [dbo].[tblTribulation] ([tribulationID])
GO
ALTER TABLE [dbo].[tblTribulation]  WITH CHECK ADD FOREIGN KEY([adminID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblTribulation]  WITH CHECK ADD FOREIGN KEY([directorID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblTribulation_Properties]  WITH CHECK ADD FOREIGN KEY([directorID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblTribulation_Properties]  WITH CHECK ADD FOREIGN KEY([propID])
REFERENCES [dbo].[tblProperties] ([propID])
GO
ALTER TABLE [dbo].[tblTribulation_Properties]  WITH CHECK ADD FOREIGN KEY([tribulationID])
REFERENCES [dbo].[tblTribulation] ([tribulationID])
GO
ALTER TABLE [dbo].[tblCastRegistration]  WITH CHECK ADD  CONSTRAINT [castRegister] CHECK  (([dbo].[getRoleName]([castID]) like 'Cast'))
GO
ALTER TABLE [dbo].[tblCastRegistration] CHECK CONSTRAINT [castRegister]
GO
ALTER TABLE [dbo].[tblCastRegistration]  WITH CHECK ADD  CONSTRAINT [directorRegistForCast] CHECK  (([dbo].[getRoleName]([directorID])='Director'))
GO
ALTER TABLE [dbo].[tblCastRegistration] CHECK CONSTRAINT [directorRegistForCast]
GO
ALTER TABLE [dbo].[tblProperties]  WITH CHECK ADD CHECK  (([propQuantity]>=(0)))
GO
ALTER TABLE [dbo].[tblTribulation]  WITH CHECK ADD  CONSTRAINT [adminCreater] CHECK  (([dbo].[getRoleName]([adminID]) like 'Admin'))
GO
ALTER TABLE [dbo].[tblTribulation] CHECK CONSTRAINT [adminCreater]
GO
ALTER TABLE [dbo].[tblTribulation]  WITH CHECK ADD CHECK  (([toolCount]>=(0)))
GO
ALTER TABLE [dbo].[tblTribulation]  WITH CHECK ADD  CONSTRAINT [directorRegist] CHECK  (([dbo].[getRoleName]([directorID]) like 'Director'))
GO
ALTER TABLE [dbo].[tblTribulation] CHECK CONSTRAINT [directorRegist]
GO
ALTER TABLE [dbo].[tblTribulation_Properties]  WITH CHECK ADD CHECK  (([propQuantity]>=(0)))
GO
ALTER TABLE [dbo].[tblTribulation_Properties]  WITH CHECK ADD  CONSTRAINT [directorRegistToolForTribulation] CHECK  (([dbo].[getRoleName]([directorID]) like 'Director'))
GO
ALTER TABLE [dbo].[tblTribulation_Properties] CHECK CONSTRAINT [directorRegistToolForTribulation]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD CHECK  (([userEmail] like '%__@__%.%__'))
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD CHECK  ((len([userName])>=(4)))
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD CHECK  ((len([userPass])>=(4)))
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD CHECK  ((len([userPhone])=(10)))
GO
/****** Object:  Trigger [dbo].[setNotificationID]    Script Date: 9/5/2020 11:10:08 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[setNotificationID] ON [dbo].[tblNotification]
For INSERT
AS
BEGIN
	DECLARE @notiID VARCHAR(10)='N'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT notiNo FROM dbo.tblNotification WHERE notiID=(SELECT notiID FROM inserted))),4) 
	UPDATE dbo.tblNotification SET notiID=@notiID WHERE notiID=(SELECT notiID FROM inserted)
END
GO
ALTER TABLE [dbo].[tblNotification] ENABLE TRIGGER [setNotificationID]
GO
/****** Object:  Trigger [dbo].[setPartID]    Script Date: 9/5/2020 11:10:08 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[setPartID] ON [dbo].[tblPart]
For INSERT
AS
BEGIN
	DECLARE @partID VARCHAR(10)='PART'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT partNo FROM dbo.tblPart WHERE partID=(SELECT partID FROM inserted))),4) 
	UPDATE dbo.tblPart SET partID=@partID WHERE partID=(SELECT partID FROM inserted)
END
GO
ALTER TABLE [dbo].[tblPart] ENABLE TRIGGER [setPartID]
GO
/****** Object:  Trigger [dbo].[setpropID]    Script Date: 9/5/2020 11:10:08 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[setpropID] ON [dbo].[tblProperties]
For INSERT
AS
BEGIN
	DECLARE @propID VARCHAR(10)='PROP'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT propNo FROM dbo.tblProperties WHERE propID=(SELECT propID FROM inserted))),4) 
	UPDATE tblProperties SET propID=@propID WHERE propID=(SELECT propID FROM inserted)
END
GO
ALTER TABLE [dbo].[tblProperties] ENABLE TRIGGER [setpropID]
GO
/****** Object:  Trigger [dbo].[setTribulationID]    Script Date: 9/5/2020 11:10:08 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[setTribulationID] ON [dbo].[tblTribulation]
For INSERT
AS
BEGIN
	DECLARE @tribulationID VARCHAR(10)='T'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT tribulationNo FROM dbo.tblTribulation WHERE tribulationID=(SELECT tribulationID FROM inserted))),4) 
	UPDATE dbo.tblTribulation SET tribulationID=@tribulationID WHERE tribulationID=(SELECT tribulationID FROM inserted)
END
GO
ALTER TABLE [dbo].[tblTribulation] ENABLE TRIGGER [setTribulationID]
GO
/****** Object:  Trigger [dbo].[setUserID]    Script Date: 9/5/2020 11:10:08 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[setUserID] ON [dbo].[tblUser]
For INSERT
AS
BEGIN
	DECLARE @userID VARCHAR(10)='U'+RIGHT('0000'+CONVERT(VARCHAR(10),(SELECT userNo FROM dbo.tblUser WHERE userID=(SELECT userID FROM inserted))),4) 
	UPDATE tblUser SET userID=@userID WHERE userID=(SELECT userID FROM inserted)
END
GO
ALTER TABLE [dbo].[tblUser] ENABLE TRIGGER [setUserID]
GO
USE [master]
GO
ALTER DATABASE [JOURNEYTOTHEWEST] SET  READ_WRITE 
GO
