create database YODYJAVA;

use YODYJAVA;

CREATE TABLE NHAN_VIEN (
    MaNhanVien INT IDENTITY(1,1) PRIMARY KEY,
    Ho NVARCHAR(50) NOT NULL,
    Ten NVARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    NgaySinh DATE NOT NULL,
    DiaChi NVARCHAR(255) NOT NULL,
    GioiTinh TINYINT NOT NULL,
    MatKhau VARCHAR(255) NOT NULL,
    VaiTro TINYINT NOT NULL,
    TrangThai TINYINT NOT NULL DEFAULT 1,
    NgayTao DATE NOT NULL DEFAULT GETDATE() ,
	XoaTaiKhoan TINYINT NOT NULL DEFAULT 0
);
INSERT INTO NHAN_VIEN (Ho, Ten, Email, NgaySinh, DiaChi, GioiTinh, MatKhau, VaiTro)
VALUES
(N'Nguyễn Văn', N'Luận',  'vanluandvlp@gmail.com', '1999-03-15', N'Trên trời Hà Nội', 0, '1', 2),
(N'Nguyễn Đình', N'Tuấn',  'tuannd112025@gmail.com', '1999-03-15', N'Số 10, Phố Hàng Bài, Hoàn Kiếm, Hà Nội', 0, '1', 1),
(N'Trần Thị',   N'Hoa',   'hoatt112025@gmail.com',   '2001-07-22', N'Ngõ 72, phố Nguyễn Trãi, Thanh Xuân, Hà Nội', 1, '1', 0),
(N'Lê Minh',    N'Huy',    'huylm112025@gmail.com',   '2000-11-05', N'Số 5, Nguyễn Văn Cừ, Long Biên, Hà Nội', 0, '1', 1),
(N'Phạm Thị',   N'Lan',    'lanpt112025@gmail.com',   '2002-02-18', N'Khu đô thị Cầu Giấy, Cầu Giấy, Hà Nội', 1, '1', 1),
(N'Vũ Đức',     N'Anh',    'anhvd112025@gmail.com',   '1998-12-09', N'Chung cư Times City, Hai Bà Trưng, Hà Nội', 0, '1', 0),
(N'Đỗ Thị',     N'Hồng',   'hongdt112025@gmail.com',  '2003-05-30', N'Số 27, Phố Hàng Gai, Hoàn Kiếm, Hà Nội', 1, '1', 0),
(N'Bùi Văn',    N'Sơn',    'sonbv112025@gmail.com',   '1999-09-12', N'Ngõ 123, Phố Bạch Mai, Hai Bà Trưng, Hà Nội', 0, '1', 0),
(N'Phan Thị',   N'Vân',    'vanpt112025@gmail.com',   '2004-06-03', N'Số 88, Đường Láng, Đống Đa, Hà Nội', 1, '1', 0),
(N'Võ Minh',    N'Quân',   'quanvm112025@gmail.com',  '2001-10-28', N'Phố Trần Hưng Đạo, Hoàn Kiếm, Hà Nội', 0, '1', 1),
(N'Hoàng Thị',  N'Ngọc',   'ngocht112025@gmail.com',  '1999-04-20', N'Tổ 5, Phường Cầu Giấy, Quận Cầu Giấy, Hà Nội', 1, '1', 1);

create table NHA_CUNG_CAP (
	MaNhaCungCap int identity(1,1) primary key,
	Ten nvarchar(50) not null,
	Email varchar(100) not null
);

INSERT INTO NHA_CUNG_CAP (Ten, Email)
VALUES
(N'Công ty ABC', 'abc@gmail.com'),
(N'Công ty DEF', 'def@gmail.com'),
(N'Công ty GHI', 'ghi@gmail.com'),
(N'Công ty JKL', 'jkl@gmail.com'),
(N'Công ty MNO', 'mno@gmail.com'),
(N'Công ty PQR', 'pqr@gmail.com'),
(N'Công ty STU', 'stu@gmail.com'),
(N'Công ty VWX', 'vwx@gmail.com'),
(N'Công ty YZA', 'yza@gmail.com'),
(N'Công ty BCD', 'bcd@gmail.com'),
(N'Công ty EFG', 'efg@gmail.com'),
(N'Công ty HIJ', 'hij@gmail.com'),
(N'Công ty KLM', 'klm@gmail.com'),
(N'Công ty NOP', 'nop@gmail.com'),
(N'Công ty QRS', 'qrs@gmail.com');

create table SAN_PHAM (
	MaSanPham int identity(1,1) primary key,
	TenSanPham nvarchar(255) not null,
	DonGia decimal(6,2) not null,
	MaNCC int not null,
	TrangThaiSanPham tinyint default 1,
	GiamGia DECIMAL(5,2) NOT NULL DEFAULT 0,
	foreign key (MaNCC) references NHA_CUNG_CAP(MaNhaCungCap),
	CONSTRAINT CHK_GiamGia CHECK (GiamGia >= 0 AND GiamGia <= 100)
);

INSERT INTO SAN_PHAM
(TenSanPham, DonGia, MaNCC, TrangThaiSanPham, GiamGia)
VALUES
(N'Áo Vest Nữ', 890, 10, 1, 1.1),
(N'Quần Âu Nam', 520, 6, 1, 2.7),
(N'Váy Maxi Voan', 450, 1, 1, 3.4),
(N'Áo Polo Nam', 280, 13, 1, 1.9),
(N'Quần Short Vải', 190, 4, 1, 3.1),
(N'Áo Khoác Da', 990, 11, 1, 1.5),
(N'Chân Váy Jean', 360, 7, 1, 2.3),
(N'Áo Sweater Nữ', 310, 15, 1, 2.0),
(N'Quần Legging Thể Thao', 140, 2, 1, 3.0);

create table BIEN_THE_SAN_PHAM (
	ma int primary key,
	MaSanPham int,
	MauSac varchar(50) not null,
	KichCo int not null,
	SoLuong int not null,
	foreign key (MaSanPham) references San_pham(MaSanPham)
)

create table MUA_HANG (
	MaMuaHang int identity(1,1) primary key,
	NgayMua datetime not null default getdate(),
	MaNCC int not null,
	MaNV int not null,
	TrangThai tinyint not null default 0;
	foreign key (MaNCC) references NHA_CUNG_CAP(MaNhaCungCap),
	foreign key (MaNV) references NHAN_VIEN(MaNhanVien)
);

INSERT INTO MUA_HANG (NgayNhap, MaNCC, MaNV) VALUES
('2025-10-25 10:30:00', 3, 1),
('2025-10-26 14:45:00', 8, 3),
('2025-10-27 09:15:00', 1, 2),
('2025-10-28 11:00:00', 5, 4),
('2025-10-29 16:20:00', 10, 5),
('2025-10-30 08:50:00', 2, 1),
('2025-10-31 13:35:00', 7, 3),
('2025-11-01 17:00:00', 4, 2),
('2025-11-01 10:05:00', 9, 4),
('2025-11-02 11:55:00', 6, 5),
('2025-10-20 15:10:00', 1, 1),
('2025-10-23 09:40:00', 3, 3);


DROP TABLE CHI_TIET_MUA_HANG
CREATE TABLE CHI_TIET_MUA_HANG (
    IdChiTiet INT IDENTITY(1,1) PRIMARY KEY,
    MaMuaHang INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(6,2) NOT NULL,
    FOREIGN KEY (MaMuaHang) REFERENCES MUA_HANG(MaMuaHang),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
	UNIQUE(MaMuaHang, MaSanPham)
);

INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, SoLuong, DonGia) VALUES
(1, 4, 150, 290.00),
(1, 7, 200, 350.00),
(2, 1, 100, 145.00),
(2, 5, 120, 180.00),
(3, 8, 80, 280.00),
(3, 3, 110, 420.00),
(4, 9, 300, 130.00),
(4, 2, 70, 850.00),
(5, 6, 50, 950.00),
(5, 8, 150, 320.00),
(6, 4, 180, 295.00),
(6, 5, 250, 185.00),
(7, 3, 90, 410.00),
(7, 7, 130, 345.00),
(8, 2, 60, 840.00),
(8, 8, 100, 275.00),
(9, 6, 80, 960.00),
(10, 9, 200, 135.00),
(11, 1, 150, 140.00),
(12, 4, 120, 300.00);

create table KHACH_HANG (
	MaKhachHang int identity(1,1) primary key,
	TenKhachHang nvarchar(100) not null,
	DiaChi nvarchar(255) not null,
	SoDienThoai varchar(10) not null,
	GioiTinh tinyint,
	LoaiKhachHang tinyint default 0
);


INSERT INTO KHACH_HANG (TenKhachHang, DiaChi, SoDienThoai, GioiTinh, LoaiKhachHang) VALUES
(N'Nguyễn Thị Hồng Anh', N'Số 15, Ngõ 40, P. Cầu Giấy, Hà Nội', '0987123456', 0, 1),
(N'Trần Văn Bảo', N'Đường 3/2, Q. Ninh Kiều, Cần Thơ', '0912345678', 1, 1),
(N'Lê Thanh Duy', N'22/5 Hẻm 12, P. Thảo Điền, TP. HCM', '0909876543', 1, 0),
(N'Phạm Hải Yến', N'Tòa S1, KĐT Vinhomes Ocean Park, Gia Lâm', '0388990011', 0, 0),
(N'Hoàng Minh Đức', N'56 Trần Phú, P. Hải Châu, Đà Nẵng', '0966778899', 1, 1);


create table BAN_HANG (
	MaBanHang int identity(1,1) primary key,
	NgayDatHang datetime not null default getdate(),
	MaKhachHang int not null,
	TrangThai tinyint not null default 0,
	MaNV int not null,
	foreign key (MaKhachHang) references KHACH_HANG(MaKhachHang),
	foreign key (MaNV) references NHAN_VIEN(MaNhanVien)
);


INSERT INTO BAN_HANG (NgayDatHang, MaKhachHang, TrangThai, MaNV) VALUES
('2025-10-15 11:20:00', 3, 2,1), -- Lê Thanh Duy (Đã giao)
('2025-10-16 15:40:00', 2, 2,2), -- Bùi Mai Phương (Đã giao)
('2025-10-18 09:30:00', 1, 1,1), -- Nguyễn Thị Hồng Anh (Đã xác nhận)
('2025-10-20 10:00:00', 1, 1,2), -- Hoàng Minh Đức (Đã xác nhận)
('2025-10-21 17:05:00', 1, 0,1); -- Cao Ngọc Diệp (Chờ xác nhận)

create table CHI_TIET_BAN_HANG (
	MaBanHangChiTiet int identity(1,1) primary key,
	MaBanHang int not null,
	MaSanPham int not null,
	SoLuong int not null,
	DonGia decimal(6,2) not null,
	foreign key (MaBanHang) references BAN_HANG(MaBanHang),
	foreign key (MaSanPham) references SAN_PHAM(MaSanPham),
	UNIQUE(MaBanHang, MaSanPham)
);

INSERT INTO CHI_TIET_BAN_HANG (MaBanHang, MaSanPham, SoLuong, DonGia) VALUES
(11, 6, 1, 990.00),  -- Áo Khoác Da (DH 1)
(12, 3, 1, 520.00),  -- Quần Âu Nam (DH 2)
(13, 7, 2, 360.00),  -- Chân Váy Jean (DH 4)
(13, 6, 1, 990.00),  -- Áo Khoác Da (DH 5)
(11, 1, 2, 150.00);  -- Túi Đeo Chéo (DH 6)

CREATE TABLE PHIEU_NHAP (
    MaPhieuNhap INT IDENTITY(1,1) PRIMARY KEY,
    NgayNhap DATETIME NOT NULL DEFAULT GETDATE(),
    MaNCC INT NOT NULL,            -- Nhà cung cấp
    MaNV INT NOT NULL,             -- Nhân viên thực hiện nhập
    TongTien DECIMAL(12,2) DEFAULT 0,
    GhiChu NVARCHAR(255),
    FOREIGN KEY (MaNCC) REFERENCES NHA_CUNG_CAP(MaNhaCungCap),
    FOREIGN KEY (MaNV) REFERENCES NHAN_VIEN(MaNhanVien)
);
INSERT INTO PHIEU_NHAP (NgayNhap, MaNCC, MaNV, GhiChu)
VALUES
('2025-11-05 09:00:00', 3, 1, N'Nhập hàng tháng 11'),
('2025-11-06 10:30:00', 5, 2, N'Nhập thêm áo khoác');

CREATE TABLE CHI_TIET_PHIEU_NHAP (
    MaChiTietNhap INT IDENTITY(1,1) PRIMARY KEY,
    MaPhieuNhap INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10,2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia) PERSISTED,   -- Cột tính toán tự động
    FOREIGN KEY (MaPhieuNhap) REFERENCES PHIEU_NHAP(MaPhieuNhap),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
    --UNIQUE (MaPhieuNhap, MaSanPham)
);

INSERT INTO CHI_TIET_PHIEU_NHAP (MaPhieuNhap, MaSanPham, SoLuong, DonGia)
VALUES
(1, 6, 100, 900.00),   -- Áo khoác da
(1, 7, 200, 320.00),   -- Chân váy jean
(2, 1, 150, 120.00);   -- Túi đeo chéo

CREATE TABLE PHIEU_XUAT (
    MaPhieuXuat INT IDENTITY(1,1) PRIMARY KEY,
    NgayXuat DATETIME NOT NULL DEFAULT GETDATE(),
    MaKH INT NOT NULL,             -- Khách hàng
    MaNV INT NOT NULL,             -- Nhân viên xuất
    TongTien DECIMAL(12,2) DEFAULT 0,
    GhiChu NVARCHAR(255),
    FOREIGN KEY (MaKH) REFERENCES KHACH_HANG(MaKhachHang),
    FOREIGN KEY (MaNV) REFERENCES NHAN_VIEN(MaNhanVien)
);
INSERT INTO PHIEU_XUAT (NgayXuat, MaKH, MaNV, GhiChu)
VALUES
('2025-11-06 15:00:00', 1, 3, N'Xuất hàng cho khách lẻ'),
('2025-11-06 16:30:00', 2, 4, N'Xuất hàng buôn');

CREATE TABLE CHI_TIET_PHIEU_XUAT (
    MaChiTietXuat INT IDENTITY(1,1) PRIMARY KEY,
    MaPhieuXuat INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10,2) NOT NULL,
    ThanhTien AS (SoLuong * DonGia) PERSISTED,
    FOREIGN KEY (MaPhieuXuat) REFERENCES PHIEU_XUAT(MaPhieuXuat),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
   -- UNIQUE (MaPhieuXuat, MaSanPham)
);

INSERT INTO CHI_TIET_PHIEU_XUAT (MaPhieuXuat, MaSanPham, SoLuong, DonGia)
VALUES
(1, 7, 2, 360.00),
(1, 1, 1, 150.00),
(2, 6, 5, 950.00);



ALTER TABLE PHIEU_NHAP ADD MaMuaHang INT NULL
FOREIGN KEY (MaMuaHang) REFERENCES MUA_HANG(MaMuaHang);
ALTER TABLE PHIEU_XUAT ADD MaBanHang INT NULL
FOREIGN KEY (MaBanHang) REFERENCES BAN_HANG(MaBanHang);

SELECT name 
FROM sys.key_constraints
WHERE type = 'UQ' AND parent_object_id = OBJECT_ID('CHI_TIET_PHIEU_NHAP');

--UQ__CHI_TIET__DBDC9B78196CF2A4

ALTER TABLE CHI_TIET_PHIEU_NHAP
DROP CONSTRAINT UQ__CHI_TIET__DBDC9B78196CF2A4;

ALTER TABLE CHI_TIET_PHIEU_NHAP
ADD CONSTRAINT UQ_CTPN UNIQUE (MaPhieuNhap, MaBienThe);

SELECT name 
FROM sys.key_constraints
WHERE type = 'UQ' AND parent_object_id = OBJECT_ID('CHI_TIET_PHIEU_XUAT');
--UQ__CHI_TIET__E968C1E1DC87805D

ALTER TABLE CHI_TIET_PHIEU_XUAT
DROP CONSTRAINT UQ__CHI_TIET__E968C1E1DC87805D;

ALTER TABLE CHI_TIET_PHIEU_XUAT
ADD CONSTRAINT UQ_CTPX UNIQUE (MaPhieuXuat, MaBienThe);