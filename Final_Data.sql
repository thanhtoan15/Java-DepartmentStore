USE [master]
GO
/****** Object:  Database [FARMSHOP]    Script Date: 11/22/2022 12:25:43 PM ******/
CREATE DATABASE [FARMSHOP]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FARMSHOP', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\FARMSHOP.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'FARMSHOP_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\FARMSHOP_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [FARMSHOP] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FARMSHOP].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FARMSHOP] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FARMSHOP] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FARMSHOP] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FARMSHOP] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FARMSHOP] SET ARITHABORT OFF 
GO
ALTER DATABASE [FARMSHOP] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [FARMSHOP] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FARMSHOP] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FARMSHOP] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FARMSHOP] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FARMSHOP] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FARMSHOP] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FARMSHOP] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FARMSHOP] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FARMSHOP] SET  ENABLE_BROKER 
GO
ALTER DATABASE [FARMSHOP] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FARMSHOP] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FARMSHOP] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FARMSHOP] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FARMSHOP] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FARMSHOP] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FARMSHOP] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FARMSHOP] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FARMSHOP] SET  MULTI_USER 
GO
ALTER DATABASE [FARMSHOP] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FARMSHOP] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FARMSHOP] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FARMSHOP] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [FARMSHOP] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [FARMSHOP] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [FARMSHOP] SET QUERY_STORE = OFF
GO
USE [FARMSHOP]
GO
/****** Object:  Table [dbo].[CTHDNhap]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHDNhap](
	[SoPhieu] [int] NOT NULL,
	[MaSP] [nvarchar](10) NOT NULL,
	[SoLuong] [int] NULL,
	[GiaNhap] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SoPhieu] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTHDXuat]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHDXuat](
	[SoPhieu] [int] NOT NULL,
	[MaSP] [nvarchar](10) NOT NULL,
	[SoLuong] [int] NULL,
	[GiamGia] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[SoPhieu] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HDNhap]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HDNhap](
	[SoPhieu] [int] IDENTITY(1,1) NOT NULL,
	[NgayNhap] [date] NULL,
	[MaNV] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[SoPhieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HDXuat]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HDXuat](
	[SoPhieu] [int] IDENTITY(1,1) NOT NULL,
	[MaNV] [nvarchar](10) NULL,
	[MaKH] [nvarchar](10) NULL,
	[ThanhTien] [float] NULL,
	[NgayXuat] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[SoPhieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKH] [nvarchar](10) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[SDT] [nvarchar](12) NULL,
	[Email] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiHang]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiHang](
	[MaLH] [nvarchar](10) NOT NULL,
	[TenLH] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [nvarchar](10) NOT NULL,
	[TenNCC] [nvarchar](50) NULL,
	[SDT] [nvarchar](12) NULL,
	[Email] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](10) NOT NULL,
	[HoTen] [nvarchar](50) NULL,
	[SDT] [nvarchar](12) NULL,
	[Email] [nvarchar](50) NULL,
	[VaiTro] [bit] NULL,
	[MatKhau] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[MaSP] [nvarchar](10) NOT NULL,
	[MaLH] [nvarchar](10) NULL,
	[TenSP] [nvarchar](50) NULL,
	[GiaBan] [float] NULL,
	[HinhSP] [nvarchar](100) NULL,
	[MaNCC] [nvarchar](10) NULL,
	[SoLuong] [int] NULL,
	[Don_Vi] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (1, N'SP001', 100, 35000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (1, N'SP002', 1, 0)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (1, N'SP003', 1, 0)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (1, N'SP004', 1, 0)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (1, N'SP005', 1, 0)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (2, N'SP002', 100, 36000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (3, N'SP003', 100, 23000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (4, N'SP004', 100, 25000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (5, N'SP005', 100, 30000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (6, N'SP006', 100, 2000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (7, N'SP007', 100, 1500)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (8, N'SP008', 100, 1000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (9, N'SP009', 100, 1800)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (10, N'SP010', 100, 1800)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (11, N'SP011', 100, 2700)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (12, N'SP012', 100, 3000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (13, N'SP013', 100, 2700)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (14, N'SP014', 100, 2800)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (15, N'SP015', 100, 35000)
INSERT [dbo].[CTHDNhap] ([SoPhieu], [MaSP], [SoLuong], [GiaNhap]) VALUES (16, N'SP016', 100, 35000)
GO
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (1, N'SP001', 10, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (2, N'SP002', 2, 10)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (3, N'SP005', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (4, N'SP016', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (5, N'SP018', 5, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (6, N'SP020', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (7, N'SP030', 10, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (8, N'SP014', 1, 50)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (9, N'SP015', 800, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (10, N'SP021', 12, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (11, N'SP022', 18, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (12, N'SP001', 1, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (12, N'SP002', 2, 10)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (12, N'SP003', 100, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (12, N'SP005', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP001', 1, 20)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP002', 2, 10)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP005', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP008', 1, 30)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP011', 1, 21)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (13, N'SP014', 1, 50)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (14, N'SP002', 1, 2)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (14, N'SP004', 1, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (14, N'SP005', 2, 0)
INSERT [dbo].[CTHDXuat] ([SoPhieu], [MaSP], [SoLuong], [GiamGia]) VALUES (15, N'SP002', 1, 0)
GO
SET IDENTITY_INSERT [dbo].[HDNhap] ON 

INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (1, CAST(N'2022-11-12' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (2, CAST(N'2022-11-12' AS Date), N'NV04')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (3, CAST(N'2022-11-13' AS Date), N'NV05')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (4, CAST(N'2022-11-14' AS Date), N'NV06')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (5, CAST(N'2022-11-15' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (6, CAST(N'2022-11-16' AS Date), N'NV04')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (7, CAST(N'2022-11-17' AS Date), N'NV05')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (8, CAST(N'2022-11-18' AS Date), N'NV06')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (9, CAST(N'2022-12-14' AS Date), N'NV04')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (10, CAST(N'2022-11-15' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (11, CAST(N'2022-11-13' AS Date), N'NV04')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (12, CAST(N'2022-11-14' AS Date), N'NV05')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (13, CAST(N'2022-12-15' AS Date), N'NV04')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (14, CAST(N'2022-11-12' AS Date), N'NV06')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (15, CAST(N'2022-11-16' AS Date), N'NV06')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (16, CAST(N'2022-12-15' AS Date), N'NV06')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (18, CAST(N'2022-12-22' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (19, CAST(N'2022-11-22' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (20, CAST(N'2022-11-22' AS Date), N'NV03')
INSERT [dbo].[HDNhap] ([SoPhieu], [NgayNhap], [MaNV]) VALUES (21, CAST(N'2022-11-22' AS Date), N'NV03')
SET IDENTITY_INSERT [dbo].[HDNhap] OFF
GO
SET IDENTITY_INSERT [dbo].[HDXuat] ON 

INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (1, N'NV03', N'KH01', 350000, CAST(N'2021-11-12' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (2, N'NV03', N'KH02', 360000, CAST(N'2021-11-13' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (3, N'NV03', N'KH03', 750000, CAST(N'2021-11-14' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (4, N'NV03', N'KH04', 60000, CAST(N'2021-11-16' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (5, N'NV03', N'KH05', 75000, CAST(N'2021-11-11' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (6, N'NV03', N'KH06', 10000, CAST(N'2021-11-12' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (7, N'NV03', N'KH07', 125000, CAST(N'2021-11-13' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (8, N'NV03', N'KH08', 450000, CAST(N'2021-11-15' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (9, N'NV03', N'KH11', 1680000, CAST(N'2021-11-15' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (10, N'NV03', N'KH10', 60000, CAST(N'2021-11-18' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (11, N'NV03', N'KH16', 90000, CAST(N'2021-11-16' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (12, N'NV03', N'KH23', 2402800, CAST(N'2021-11-14' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (13, N'NV01', N'KH06', 111443, CAST(N'2022-11-18' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (14, N'NV01', N'KH07', 35280, CAST(N'2022-11-18' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (15, N'NV01', N'KH01', 0, CAST(N'2022-11-19' AS Date))
INSERT [dbo].[HDXuat] ([SoPhieu], [MaNV], [MaKH], [ThanhTien], [NgayXuat]) VALUES (16, N'NV01', N'KH01', 4000, CAST(N'2022-11-01' AS Date))
SET IDENTITY_INSERT [dbo].[HDXuat] OFF
GO
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH01', N'Nguyễn Chí Thanh', N'0987564755', N'Shino@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH02', N'Phạm Trình Ân Phúc', N'0975664758', N'Phuc@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH03', N'Nguyễn Thành Nam', N'0987465773', N'Nam@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH04', N'Trương Thanh Toàn', N'0985546374', N'Toan@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH05', N'Hồ Thanh Kiệt', N'0980967581', N'Kiet@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH06', N'Trần Hà Thanh Nhã', N'0981166574', N'Nha@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH07', N'Lương Thị Thuý Lành', N'09854736453', N'Lanh@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH08', N'Nguyễn Chí Thanh', N'0384449114', N'Thanh@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH09', N'Nguyễn Tuấn Anh', N'0987573425', N'tuananhnguyen@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH10', N'Đỗ Minh Tiến', N'0987564835', N'tiendo@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH11', N'Nguyễn Đắc Trà Giang', N'0989574854', N'giangnguyen@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH12', N'Đỗ Minh Tiến', N'0987564835', N'tiendo@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH13', N'Đỗ Đoàn Kiều Oanh', N'0987564837', N'oanhdo@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH14', N'Tạ Minh Phàm', N'0986564756', N'phamta@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH15', N'Lê Yến Nhi', N'0986475648', N'nhilo@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH16', N'Đinh Ngọc Minh Nhật', N'09854637453', N'nhatdinh@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH17', N'Úy Thiện Nhân', N'09854637463', N'nhonUy@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH18', N'Trương Phong Hào', N'09853646352', N'haotruong@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH19', N'Nguyễn Công Anh', N'0981165736', N'anhnguyen@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH20', N'Trương Hoài Anh', N'09874658374', N'anhtruong@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH21', N'Ngô Thanh Dũng', N'0987465773', N'dungngu@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH22', N'Đỗ Trọng Phát', N'0985546374', N'photdo@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH23', N'Huỳnh Văn Vọng', N'0986574866', N'vonghuynh@gmail.com')
INSERT [dbo].[KhachHang] ([MaKH], [HoTen], [SDT], [Email]) VALUES (N'KH24', N'HelloWorl', N'12344', N'Thanh@gmail.com')
GO
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH001', N'Gạo')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH002', N'Rau')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH003', N'Củ')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH004', N'Quả')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH005', N'Sữa')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH006', N'cà Phê')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH007', N'Chè')
INSERT [dbo].[LoaiHang] ([MaLH], [TenLH]) VALUES (N'LH08', N'123')
GO
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC001', N'Quang Linh Farm', N'0513551', N'QLFarm@gmail.com', N' Bailundo ,huyện Huambo, Angola')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC002', N'Gạo FAS', N'0906996700', N'GaoFas@gmail.com', N'Số 18 Ngõ 152, tổ 26 phố Thúy Lĩnh, P. Lĩnh Nam, Q. Hoàng Mai, Hà Nội')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC003', N'Đà Lạt GAP', N'0836854368', N'DaLatGap@gmail.com', N' Quốc lộ 20, đèo Prenn, P.3, TP. Đà Lạt')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC004', N'thực phẩm đồng xanh', N'0919687928', N'ThucPhamDongXanh@gmail.com', N'34/23 Hoàng Ngọc Phách, P. Phú Thọ Hoà, Q.Tân Phú, TP.HCM')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC005', N'rau sạch Vân Nội', N'0937367819', N'rausachVN@gmail.com', N' Xóm Ðầm, Vân Nội, Ðông Anh, Hà Nội')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC006', N'V-Organic', N'0794525625', N'V-Organic@gmail.com', N'Số 07, Đường 2.2, Gamuda Garden, Hoàng Mai, Hà Nội')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC007', N'vinamilk organic', N'0794525625', N'vinamilk@gmail.com', N'Tu Tra, huyện Đơn Dương, Tỉnh Lâm Đồng.')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [SDT], [Email], [DiaChi]) VALUES (N'NCC008', N'Cầu Đất Farm', N'02866600516', N'CauDatFarm@gmail.com', N' Thôn Cầu Đất, xã Xuân Trường, phường 12, Thành phố Đà Lạt, Lâm Đồng')
GO
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV01', N'Nguyễn Chí Thanh', N'0384449114', N'Thanhncps21156@fpt.edu.vn', 0, N'123456')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV02', N'Phạm Trình Ân Phúc', N'0325656298', N'phucptaps25002@fpt.edu.vn', 0, N'123456')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV03', N'Nguyễn Thành Nam', N'0378948419', N'namntps23848@fpt.edu.vn', 1, N'123456')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV04', N'Trương Thanh Toàn', N'0383710766', N'toanttps21007@fpt.edu.vn', 1, N'123456')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV05', N'Hồ Thanh Kiệt ', N'0833338817', N'kiethtps22319@fpt.edu.vn', 1, N'123456')
INSERT [dbo].[NhanVien] ([MaNV], [HoTen], [SDT], [Email], [VaiTro], [MatKhau]) VALUES (N'NV06', N'Nguyễn Huy Hoàng', N'0375288493', N'hoangnhps20141@fpt.edu.vn', 1, N'123456')
GO
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP001', N'LH001', N'Gạo ST25', 35000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Gao-ST25.jpg', N'NCC002', 200, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP002', N'LH001', N'Gạo ST24', 36000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Gao_ST24.jpg', N'NCC002', 200, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP003', N'LH001', N'Gạo Thơm Thái', 23000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Gao_ThomThai.jpg', N'NCC002', 100, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP004', N'LH001', N'Gạo thơm Lài Miên', 25000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Gao_ThomLaiMien.jpg', N'NCC002', 100, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP005', N'LH002', N'Cải bó xôi', 1500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Cai_bo_Xoi.jpg', N'NCC003', 100, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP006', N'LH002', N' Bông cải xanh', 1500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Bong_Cai_xanh.png', N'NCC004', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP007', N'LH002', N' Rau Muống', 500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Rau_Muong.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP008', N'LH002', N' Rau Muống', 500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Rau_Muong.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP009', N'LH002', N' Rau Cải xanh', 1000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Cai_Xanh.png', N'NCC006', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP010', N'LH002', N' Rau Đay', 700, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Rau_day.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP011', N'LH003', N'Củ cả trắng', 1700, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Cu_cai_trang.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP012', N'LH003', N'Củ Cà Rốt', 1200, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Ca_Rot.png.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP013', N'LH003', N'Củ Khoai Lang', 1500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Khoai_lang.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP014', N'LH003', N'Củ Hành Tây', 1500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Hanh_Tay.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP015', N'LH003', N'Củ Khoai Mỡ', 2100, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Khoai_mo.jpg', N'NCC005', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP016', N'LH004', N'Trái Xoài', 30000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\xoai.jpg', N'NCC005', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP017', N'LH004', N'Trái Thớm', 20000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Thom.jpg', N'NCC005', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP018', N'LH004', N'Trái Bơ', 15000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\qua_bo.jpg', N'NCC005', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP019', N'LH004', N'Dâu Tây', 50000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Dau_tay.jpg', N'NCC006', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP020', N'LH005', N'Sữa Vinamilk Organic', 5000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\sua.jpg', N'NCC007', 50, N'Hộp')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP021', N'LH005', N'Sữa Tươi Vinamilk Organic', 5000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\sua_Tuoi.jpg', N'NCC007', 50, N'Hộp')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP022', N'LH005', N'Sữa Vinamilk ADM', 5000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\VNM.jpg', N'NCC007', 50, N'Hộp')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP023', N'LH005', N'Sữa Vinamilk Flex', 5000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\SN_Product_Flex.jpg', N'NCC007', 50, N'Hộp')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP024', N'LH006', N'Cà phê Arabica', 250000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\ca-phe-arabica.jpg', N'NCC008', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP025', N'LH006', N' Cà phê Cherry', 250000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\cafe-cherry.jpg', N'NCC008', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP026', N'LH006', N' Cà phê Chồn', 350000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\Ca_Phe_Chon.jpg', N'NCC008', 50, N'KG')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP027', N'LH007', N' Trà Xanh', 15000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\tra_xanh.jpg', N'NCC008', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP028', N'LH007', N' Trà Long Tĩnh', 25000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\tra-long-tinh.jpg', N'NCC008', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP029', N'LH007', N' Trà Sencha Nhật', 35000, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\sencha.jpg', N'NCC008', 50, N'G')
INSERT [dbo].[SanPham] ([MaSP], [MaLH], [TenSP], [GiaBan], [HinhSP], [MaNCC], [SoLuong], [Don_Vi]) VALUES (N'SP030', N'LH007', N' Trà Đen', 12500, N'D:\STUDYYYYY\PRO1041_DU_AN_1\project\img_Data\black-tea.jpg', N'NCC008', 50, N'G')
GO
ALTER TABLE [dbo].[CTHDXuat] ADD  DEFAULT ((0)) FOR [GiamGia]
GO
ALTER TABLE [dbo].[CTHDNhap]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[CTHDNhap]  WITH CHECK ADD FOREIGN KEY([SoPhieu])
REFERENCES [dbo].[HDNhap] ([SoPhieu])
GO
ALTER TABLE [dbo].[CTHDXuat]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SanPham] ([MaSP])
GO
ALTER TABLE [dbo].[CTHDXuat]  WITH CHECK ADD FOREIGN KEY([SoPhieu])
REFERENCES [dbo].[HDXuat] ([SoPhieu])
GO
ALTER TABLE [dbo].[HDNhap]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HDXuat]  WITH CHECK ADD FOREIGN KEY([MaKH])
REFERENCES [dbo].[KhachHang] ([MaKH])
GO
ALTER TABLE [dbo].[HDXuat]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaLH])
REFERENCES [dbo].[LoaiHang] ([MaLH])
GO
ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
GO
/****** Object:  StoredProcedure [dbo].[DoanhThu_Nam]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[DoanhThu_Nam](@year INT)
AS begin
SELECT SUM(a.ThanhTien) AS 'sum'
FROM dbo.HDXuat a
WHERE YEAR(a.NgayXuat) = @year
END
GO
/****** Object:  StoredProcedure [dbo].[DoanhThu_Ngay]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- doanh thu theo ngày 
CREATE   PROC [dbo].[DoanhThu_Ngay](@day INT)
AS begin
SELECT SUM(a.ThanhTien) AS 'SUM'
FROM dbo.HDXuat a
WHERE DAY(a.NgayXuat) = @day
END
GO
/****** Object:  StoredProcedure [dbo].[DoanhThu_Thang]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[DoanhThu_Thang](@month INT)
AS begin
SELECT SUM(a.ThanhTien) AS 'sum'
FROM dbo.HDXuat a
WHERE MONTH(a.NgayXuat) = @month
END
GO
/****** Object:  StoredProcedure [dbo].[DonNhap_NCC_Max]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[DonNhap_NCC_Max](@month INT)
AS begin
SELECT TOP 3 sp.MaNCC , c.TenNCC,COUNT(a.SoPhieu)AS 'SL' 
FROM dbo.CTHDNhap a LEFT JOIN dbo.SanPham sp ON a.MaSP = sp.MaSP
LEFT JOIN dbo.NhaCungCap c ON sp.MaNCC = c.MaNCC
LEFT JOIN dbo.HDNhap hdn ON a.SoPhieu = hdn.SoPhieu
WHERE MONTH(hdn.NgayNhap) = @month
GROUP BY sp.MaNCC , c.TenNCC
ORDER BY COUNT(a.SoPhieu) DESC
END
GO
/****** Object:  StoredProcedure [dbo].[SL_DonNhap]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[SL_DonNhap] (@month INT)
AS BEGIN
	SELECT COUNT(SoPhieu)AS 'SL'	
	FROM dbo.HDNhap
	WHERE MONTH(NgayNhap) = @month
END
GO
/****** Object:  StoredProcedure [dbo].[SL_DonXuat]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[SL_DonXuat] (@month INT)
AS BEGIN
	SELECT COUNT(SoPhieu)AS 'SL'
	FROM dbo.HDXuat
	WHERE MONTH(NgayXuat) = @month
END
GO
/****** Object:  StoredProcedure [dbo].[SP_muaMax]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[SP_muaMax](@month INT)
AS BEGIN
SELECT TOP 3  a.MaSP,c.MaLH,c.TenSP,c.GiaBan,COUNT(a.MaSP)AS 'sl'  
FROM dbo.CTHDXuat a LEFT JOIN dbo.HDXuat b ON a.SoPhieu = b.SoPhieu 
	LEFT JOIN dbo.SanPham c ON a.MaSP = c.MaSP
WHERE MONTH(b.NgayXuat) = 11
GROUP BY a.MaSP, c.TenSP , c.GiaBan , c.MaLH 
ORDER BY COUNT(a.MaSP) DESC
END 
GO
/****** Object:  StoredProcedure [dbo].[update_gia]    Script Date: 11/22/2022 12:25:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[update_gia](@soPhieu INT )
AS BEGIN 
UPDATE dbo.HDXuat  
SET ThanhTien = ( SELECT SUM((ct.SoLuong*sp.GiaBan)*((100-ct.GiamGia)/100)) AS 'thanh_Tien' 
				FROM dbo.CTHDXuat ct INNER JOIN dbo.SanPham sp ON ct.MaSP = sp.MaSP			
			WHERE ct.SoPhieu =dbo.HDXuat.SoPhieu
			GROUP BY ct.SoPhieu
)
WHERE dbo.HDXuat.SoPhieu = @soPhieu
end
GO
USE [master]
GO
ALTER DATABASE [FARMSHOP] SET  READ_WRITE 
GO
