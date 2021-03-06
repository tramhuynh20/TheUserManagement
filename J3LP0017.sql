USE [master]
GO
/****** Object:  Database [J3.L.P0017]    Script Date: 6/20/2021 11:05:41 PM ******/
CREATE DATABASE [J3.L.P0017]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'J3.L.P0017', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\J3.L.P0017.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'J3.L.P0017_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\J3.L.P0017_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [J3.L.P0017] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3.L.P0017].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3.L.P0017] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [J3.L.P0017] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [J3.L.P0017] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [J3.L.P0017] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [J3.L.P0017] SET ARITHABORT OFF 
GO
ALTER DATABASE [J3.L.P0017] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [J3.L.P0017] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [J3.L.P0017] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [J3.L.P0017] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [J3.L.P0017] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [J3.L.P0017] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [J3.L.P0017] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [J3.L.P0017] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [J3.L.P0017] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [J3.L.P0017] SET  DISABLE_BROKER 
GO
ALTER DATABASE [J3.L.P0017] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [J3.L.P0017] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [J3.L.P0017] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [J3.L.P0017] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [J3.L.P0017] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [J3.L.P0017] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [J3.L.P0017] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [J3.L.P0017] SET RECOVERY FULL 
GO
ALTER DATABASE [J3.L.P0017] SET  MULTI_USER 
GO
ALTER DATABASE [J3.L.P0017] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [J3.L.P0017] SET DB_CHAINING OFF 
GO
ALTER DATABASE [J3.L.P0017] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [J3.L.P0017] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [J3.L.P0017] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'J3.L.P0017', N'ON'
GO
USE [J3.L.P0017]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/20/2021 11:05:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 6/20/2021 11:05:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[userID] [nvarchar](10) NOT NULL,
	[userName] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[phoneNumber] [nvarchar](50) NOT NULL,
	[imageURL] [nvarchar](500) NOT NULL,
	[password] [nvarchar](500) NOT NULL,
	[createDate] [date] NOT NULL,
	[rank] [int] NULL,
	[status] [bit] NOT NULL,
	[statusPromotion] [bit] NOT NULL,
	[promotionDate] [date] NULL,
	[roleID] [int] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (1, N'Admin')
GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (2, N'User')
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Khoi', N'Nguyen Le Anh Khoi', N'khoi@gmail.com', N'0969938977', N'CaptureFAP.png', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2020-03-02' AS Date), 0, 0, 0, CAST(N'2021-06-19' AS Date), 2)
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Minh', N'Cao Ba Minh', N'minh@gmail.com', N'07675009991', N'CaptureFAP.png', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2021-01-01' AS Date), 7, 1, 1, CAST(N'2021-06-18' AS Date), 2)
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Tai', N'Pham Viet Tai', N'tai@gmail.com', N'0394274444', N'CaptureFAP.png', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2020-03-05' AS Date), 0, 1, 0, CAST(N'2021-06-20' AS Date), 2)
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Tram', N'Huynh Thi Khanh Tram', N'tram@gmail.com', N'0961117387', N'tui.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2021-06-08' AS Date), 0, 1, 0, CAST(N'2021-06-09' AS Date), 1)
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Tung', N'Huynh Thanh Tung', N'tung@gmail.com', N'0877596396', N'CaptureFAP.png', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2019-12-18' AS Date), 5, 1, 1, CAST(N'2020-12-18' AS Date), 2)
GO
INSERT [dbo].[User] ([userID], [userName], [email], [phoneNumber], [imageURL], [password], [createDate], [rank], [status], [statusPromotion], [promotionDate], [roleID]) VALUES (N'Tuyen', N'Nguyen Thi Thanh Tuyen', N'tuyen@gmail.com', N'0878529555', N'CaptureFAP.png', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', CAST(N'2020-01-30' AS Date), 3, 1, 1, CAST(N'2021-01-30' AS Date), 2)
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
USE [master]
GO
ALTER DATABASE [J3.L.P0017] SET  READ_WRITE 
GO
