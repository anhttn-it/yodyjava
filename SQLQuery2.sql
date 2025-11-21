create database YODYJAVA1;

use YODYJAVA1;


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
(N'Lê Minh',    N'Huy',    'huylm112025@gmail.com',   '2000-11-05', N'Số 5, Nguyễn Văn Cừ, Long Biên, Hà Nội', 0, '1', 1);

create table NHA_CUNG_CAP (
	MaNhaCungCap int identity(1,1) primary key,
	Ten nvarchar(50) not null,
	Email varchar(100) not null
);

INSERT INTO NHA_CUNG_CAP (Ten, Email)
VALUES
(N'Công ty ABC', 'abc@gmail.com'),
(N'Công ty DEF', 'def@gmail.com'),
(N'Công ty GHI', 'ghi@gmail.com');

create table SAN_PHAM (
	MaSanPham int identity(1,1) primary key,
	TenSanPham nvarchar(255) not null,
	MaNCC int not null,
	TrangThaiSanPham  nvarchar(255) not null,
	GiamGia DECIMAL(5,2) NOT NULL DEFAULT 0,
	foreign key (MaNCC) references NHA_CUNG_CAP(MaNhaCungCap),
	CONSTRAINT CHK_GiamGia CHECK (GiamGia >= 0 AND GiamGia <= 100)
);


INSERT INTO SAN_PHAM
(TenSanPham, MaNCC, TrangThaiSanPham, GiamGia)
VALUES
(N'Áo Vest Nữ', 1, N'Còn hàng', 1.1),
(N'Quần Âu Nam', 2,N'Còn hàng', 2.7),
(N'Váy Maxi Voan', 3, N'Hết hàng', 3.4);

create table BIEN_THE_SAN_PHAM (
	ma int identity(1,1) primary key,
	MaSanPham int,
	MauSac nvarchar(50) not null,
	KichCo int not null,
	foreign key (MaSanPham) references San_pham(MaSanPham)
)

insert into BIen_the_san_pham (MaSanPham, MauSac,KichCo) Values 
(3,N'Xanh',36),
(2,N'Đỏ',37);

create table MUA_HANG (
	MaMuaHang int identity(1,1) primary key,
	NgayMua datetime not null default getdate(),
	MaNCC int not null,
	MaNV int not null,
	TongTien DECIMAL(12,2) DEFAULT 0,
	GhiChu NVARCHAR(255),
	foreign key (MaNCC) references NHA_CUNG_CAP(MaNhaCungCap),
	foreign key (MaNV) references NHAN_VIEN(MaNhanVien)
)

INSERT INTO MUA_HANG (NgayMua, MaNCC, MaNV, TongTien) VALUES
('2025-10-25 10:30:00', 3, 1,0),
('2025-10-26 14:45:00', 2, 3,0),
('2025-10-27 09:15:00', 1, 2,0);

CREATE TABLE CHI_TIET_MUA_HANG (
    IdChiTiet INT IDENTITY(1,1) PRIMARY KEY,
    MaMuaHang INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(6,2) NOT NULL,
	MaBienThe int not null,
    FOREIGN KEY (MaMuaHang) REFERENCES MUA_HANG(MaMuaHang),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
	FOREIGN KEY (MaBienThe) REFERENCES BIEN_THE_SAN_PHAM(ma),
	UNIQUE(MaMuaHang, MaBienThe)
);

INSERT INTO CHI_TIET_MUA_HANG (MaMuaHang, MaSanPham, SoLuong, DonGia, MaBienThe) VALUES
(4, 3, 200, 350.00,4),
(5, 2, 100, 145.00,5);

create table KHACH_HANG (
	MaKhachHang int identity(1,1) primary key,
	TenKhachHang nvarchar(100) not null,
	DiaChi nvarchar(255) not null,
	SoDienThoai varchar(10) not null,
	GioiTinh nvarchar(10) not null,
	LoaiKhachHang tinyint default 0
);

INSERT INTO KHACH_HANG (TenKhachHang, DiaChi, SoDienThoai, GioiTinh, LoaiKhachHang) VALUES
(N'Nguyễn Thị Hồng Anh', N'Số 15, Ngõ 40, P. Cầu Giấy, Hà Nội', '0987123456', N'Nam', 1),
(N'Trần Văn Bảo', N'Đường 3/2, Q. Ninh Kiều, Cần Thơ', '0912345678', N'Nữ', 1);

create table BAN_HANG (
	MaBanHang int identity(1,1) primary key,
	NgayDatHang datetime not null default getdate(),
	MaKhachHang int not null,
	MaNV int not null,
	TongTien DECIMAL(12,2) DEFAULT 0,
	GhiChu NVARCHAR(255),
	foreign key (MaKhachHang) references KHACH_HANG(MaKhachHang),
	foreign key (MaNV) references NHAN_VIEN(MaNhanVien)
);
INSERT INTO BAN_HANG (NgayDatHang, MaKhachHang, MaNV, TongTien) VALUES
('2025-10-15 11:20:00', 1, 2,0), -- Lê Thanh Duy (Đã giao)
('2025-10-16 15:40:00', 2, 2,0); -- Cao Ngọc Diệp (Chờ xác nhận)

create table CHI_TIET_BAN_HANG (
	MaBanHangChiTiet int identity(1,1) primary key,
	MaBanHang int not null,
	MaSanPham int not null,
	SoLuong int not null,
	DonGia decimal(6,2) not null,
	MaBienThe int not null,
	foreign key (MaBanHang) references BAN_HANG(MaBanHang),
	foreign key (MaSanPham) references SAN_PHAM(MaSanPham),
	FOREIGN KEY (MaBienThe) REFERENCES BIEN_THE_SAN_PHAM(ma),
	UNIQUE (MaBanHang, MaBienThe)
);

INSERT INTO CHI_TIET_BAN_HANG (MaBanHang, MaSanPham, SoLuong, DonGia, MaBienThe) VALUES
(3, 3, 1, 990.00,4),  -- Áo Khoác Da (DH 1)
(2, 2, 1, 520.00,5);

CREATE TABLE PHIEU_NHAP (
    MaPhieuNhap INT IDENTITY(1,1) PRIMARY KEY,
    NgayNhap DATETIME NOT NULL DEFAULT GETDATE(),
    MaNCC INT NOT NULL,            -- Nhà cung cấp
    MaNV INT NOT NULL,             -- Nhân viên thực hiện nhập
    TongTien DECIMAL(12,2) DEFAULT 0,
    GhiChu NVARCHAR(255),
	MaMuaHang INT NULL
    FOREIGN KEY (MaNCC) REFERENCES NHA_CUNG_CAP(MaNhaCungCap),
    FOREIGN KEY (MaNV) REFERENCES NHAN_VIEN(MaNhanVien),
	FOREIGN KEY (MaMuaHang) REFERENCES MUA_HANG(MaMuaHang)
);

INSERT INTO PHIEU_NHAP (NgayNhap, MaNCC, MaNV, TongTien, GhiChu)
VALUES
('2025-11-05 09:00:00', 3, 1,0, N'Nhập hàng tháng 11'),
('2025-11-06 10:30:00', 1, 2,0, N'Nhập thêm áo khoác');

CREATE TABLE CHI_TIET_PHIEU_NHAP (
    MaChiTietNhap INT IDENTITY(1,1) PRIMARY KEY,
    MaPhieuNhap INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(10,2) NOT NULL,
	MaBienThe INT not NULL,
    FOREIGN KEY (MaPhieuNhap) REFERENCES PHIEU_NHAP(MaPhieuNhap),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
	FOREIGN KEY (MaBienThe) REFERENCES BIEN_THE_SAN_PHAM(ma),
	UNIQUE (MaPhieuNhap, MaBienThe)
);

INSERT INTO CHI_TIET_PHIEU_NHAP (MaPhieuNhap, MaSanPham, SoLuong, DonGia, MaBienThe)
VALUES
(3, 3, 100, 900.00,4),   -- Áo khoác da
(4, 2, 200, 320.00,5); 

CREATE TABLE PHIEU_XUAT (
    MaPhieuXuat INT IDENTITY(1,1) PRIMARY KEY,
    NgayXuat DATETIME NOT NULL DEFAULT GETDATE(),
    MaKH INT NOT NULL,             -- Khách hàng
    MaNV INT NOT NULL,             -- Nhân viên xuất
    TongTien DECIMAL(12,2) DEFAULT 0,
    GhiChu NVARCHAR(255),
	MaBanHang INT NULL
    FOREIGN KEY (MaKH) REFERENCES KHACH_HANG(MaKhachHang),
    FOREIGN KEY (MaNV) REFERENCES NHAN_VIEN(MaNhanVien),
	FOREIGN KEY (MaBanHang) REFERENCES BAN_HANG(MaBanHang)
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
	MaBienThe INT not NULL,
    FOREIGN KEY (MaPhieuXuat) REFERENCES PHIEU_XUAT(MaPhieuXuat),
    FOREIGN KEY (MaSanPham) REFERENCES SAN_PHAM(MaSanPham),
	FOREIGN KEY (MaBienThe) REFERENCES BIEN_THE_SAN_PHAM(ma),
	UNIQUE (MaPhieuXuat, MaBienThe)
);

INSERT INTO CHI_TIET_PHIEU_XUAT (MaPhieuXuat, MaSanPham, SoLuong, DonGia,MaBienThe)
VALUES
(1, 5, 1, 150.00,5),
(2, 3, 5, 950.00,4);


CREATE OR ALTER TRIGGER trg_UpdateTongTienPhieuXuat
ON CHI_TIET_PHIEU_Xuat
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật tổng tiền cho tất cả các phiếu bị ảnh hưởng
    UPDATE px
    SET px.TongTien = ISNULL(ct.TongTienTinh, 0)
    FROM PHIEU_XUAT px
    JOIN (
        SELECT i.MaPhieuXuat, SUM(i.SoLuong * i.DonGia * (1 - s.GiamGia/100)) AS TongTienTinh
        FROM CHI_TIET_PHIEU_XUAT i
        JOIN SAN_PHAM s ON i.MaSanPham = s.MaSanPham
        WHERE i.MaPhieuXuat IN (
            SELECT MaPhieuXuat FROM inserted
            UNION
            SELECT MaPhieuXuat FROM deleted
        )
        GROUP BY i.MaPhieuXuat
    ) ct ON px.MaPhieuXuat = ct.MaPhieuXuat;
END;


CREATE OR ALTER TRIGGER trg_UpdateTongTienPhieuNhap
ON CHI_TIET_PHIEU_NHAP
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE pn
    SET pn.TongTien = ISNULL(ct.TongTienTinh, 0)
    FROM PHIEU_NHAP pn
    JOIN (
        SELECT i.MaPhieuNhap, SUM(i.SoLuong * i.DonGia * (1 - s.GiamGia/100)) AS TongTienTinh
        FROM CHI_TIET_PHIEU_Nhap i
        JOIN SAN_PHAM s ON i.MaSanPham = s.MaSanPham
        WHERE i.MaPhieuNhap IN (
            SELECT MaPhieuNHAP FROM inserted
            UNION
            SELECT MaPhieuNhap FROM deleted
        )
        GROUP BY i.MaPhieuNhap
    ) ct ON pn.MaPhieuNhap = ct.MaPhieuNhap;
END;



CREATE OR ALTER TRIGGER trg_UpdateTongTienMuaHang
ON CHI_TIET_MUA_HANG
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE mua
    SET mua.TongTien = ISNULL(ct.TongTienTinh, 0)
    FROM MUA_HANG mua
    JOIN (
        SELECT i.MaMuaHang, SUM(i.SoLuong * i.DonGia * (1 - s.GiamGia/100)) AS TongTienTinh
        FROM CHI_TIET_MUA_HANG i
        JOIN SAN_PHAM s ON i.MaSanPham = s.MaSanPham
        WHERE i.MaMuaHang IN (
            SELECT MaMuaHang FROM inserted
            UNION
            SELECT MaMuaHang FROM deleted
        )
        GROUP BY i.MaMuaHang
    ) ct ON mua.MaMuaHang = ct.MaMuaHang;
END;

CREATE OR ALTER TRIGGER trg_UpdateTongTienBanHang
ON CHI_TIET_BAN_HANG
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE ban
    SET ban.TongTien = ISNULL(ct.TongTienTinh, 0)
    FROM BAN_HANG ban
    JOIN (
        SELECT i.MaBanHang, SUM(i.SoLuong * i.DonGia * (1 - s.GiamGia/100)) AS TongTienTinh
        FROM CHI_TIET_Ban_HANG i
        JOIN SAN_PHAM s ON i.MaSanPham = s.MaSanPham
        WHERE i.MaBanHang IN (
            SELECT MaBanHang FROM inserted
            UNION
            SELECT MaBanHang FROM deleted
        )
        GROUP BY i.MaBanHang
    ) ct ON ban.MaBanHang = ct.MaBanHang;
END;
