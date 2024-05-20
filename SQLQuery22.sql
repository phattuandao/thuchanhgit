CREATE DATABASE QuanLyCuaHang2;
USE QuanLyCuaHang2;

CREATE TABLE khachhang (
    MAKHACHHANG varchar(50) PRIMARY KEY,
    TENKHACHHANG nvarchar(50) NOT NULL,
    DIACHI nvarchar(50),
    EMAIL nvarchar(50), 
    DIENTHOAI varchar(10) CHECK (LEN(DIENTHOAI) <= 10)
);

CREATE TABLE nhacungcap (
    MACONGTY varchar(50) PRIMARY KEY,
    TENCONGTY nvarchar(50) NOT NULL,
    DIACHI nvarchar(50), 
    DIENTHOAI varchar(10) CHECK (LEN(DIENTHOAI) <= 10)
);
CREATE TABLE loaihang (
    MALOAIHANG varchar(50) PRIMARY KEY,
    TENLOAIHANG nvarchar(50)
);
CREATE TABLE mathang (
    MAHANG varchar(50) PRIMARY KEY,
    TENHANG nvarchar(50) NOT NULL,
    MACONGTY varchar(50),
    MALOAIHANG varchar(50),
    SOLUONG int, 
    DONVITINH nvarchar(50),
    GIAHANG float,
    FOREIGN KEY (MACONGTY) REFERENCES nhacungcap(MACONGTY),
    FOREIGN KEY (MALOAIHANG) REFERENCES loaihang(MALOAIHANG)
);
CREATE TABLE nhanvien (
    MANHANVIEN varchar(50) PRIMARY KEY,
    HO nvarchar(50),
    NGAYSINH date,
    NGAYLAMVIEC date, 
    DIACHI nvarchar(50),
    DIENTHOAI varchar(10),
    LUONGCOBAN float
);
CREATE TABLE dondathang (
    SOHOADON varchar(50) PRIMARY KEY,
    MAKHACHHANG varchar(50),
    MANHANVIEN varchar(50),
    NGAYDATHANG date,
    NGAYCHUYENHANG date,
    NOIGIAOHANG nvarchar(50),
    FOREIGN KEY (MAKHACHHANG) REFERENCES khachhang(MAKHACHHANG),
    FOREIGN KEY (MANHANVIEN) REFERENCES nhanvien(MANHANVIEN)
);

CREATE TABLE chitietdonhang (
    SOHOADON varchar(50),
    MAHANG varchar(50),
    GIABAN float,
    SOLUONG float,
    MUCGIAMGIA float,
    PRIMARY KEY (SOHOADON, MAHANG),
    FOREIGN KEY (SOHOADON) REFERENCES dondathang(SOHOADON),
    FOREIGN KEY (MAHANG) REFERENCES mathang(MAHANG)
);

INSERT INTO khachhang (MAKHACHHANG, TENKHACHHANG, DIACHI, EMAIL, DIENTHOAI)
VALUES
    ('KH001', N'Nguyễn Văn A', N'123 Đường ABC, Quận XYZ', 'nguyenvana@example.com', '1234567890'),
    ('KH002', N'Trần Thị B', N'456 Đường XYZ, Quận ABC', 'tranthib@example.com', '0987654321'),
    ('KH003', N'Lê Văn C', N'789 Đường DEF, Quận UVW', 'levanc@example.com', '0123456789'),
	('KH004', N'Phạm Thị D', N'101 Đường UVW, Quận DEF', 'phamthid@example.com', '9876543210'),
	('KH005', N'Huỳnh Văn E', N'202 Đường MNO, Quận PQR', 'huynhvane@example.com', '5678901234'),
	('KH006', N'Võ Thị F', N'303 Đường STU, Quận GHI', 'vothif@example.com', '4567890123'),
	('KH007', N'Ngô Văn G', N'404 Đường JKL, Quận MNO', 'ngovang@example.com', '3456789012'),
	('KH008', N'Đặng Thị H', N'505 Đường PQR, Quận STU', 'dangthih@example.com', '2345678901'),
	('KH009', N'Bùi Văn I', N'606 Đường GHI, Quận JKL', 'buivani@example.com', '1234567890'),
	('KH010', N'Phan Thị K', N'707 Đường MNO, Quận PQR', 'phanthik@example.com', '0123456789')

-- Thêm dữ liệu vào bảng nhacungcap
INSERT INTO nhacungcap (MACONGTY, TENCONGTY, DIACHI, DIENTHOAI)
VALUES
    ('NCC001', N'Công ty A', N'789 Đường DEF, Quận UVW',  '1234567890'),
    ('NCC002', N'Công ty B', N'123 Đường ABC, Quận XYZ',  '0987654321'),
    ('NCC003', N'Công ty C', N'456 Đường XYZ, Quận ABC', '0123456789'),
    ('NCC004', N'Công ty D', N'101 Đường UVW, Quận DEF', '9876543210'),
	('NCC005', N'Công ty E', N'202 Đường MNO, Quận PQR', '5678901234'),
	('NCC006', N'Công ty F', N'303 Đường STU, Quận GHI', '4567890123'),
	('NCC007', N'Công ty G', N'404 Đường JKL, Quận MNO', '3456789012'),
	('NCC008', N'Công ty H', N'505 Đường PQR, Quận STU', '2345678901'),
	('NCC009', N'Công ty I', N'606 Đường GHI, Quận JKL', '1234567890'),
	('NCC010', N'Công ty K', N'707 Đường MNO, Quận PQR','0123456789')

-- Thêm dữ liệu vào bảng loaihang
INSERT INTO loaihang (MALOAIHANG, TENLOAIHANG)
VALUES
    ('LH001', N'Điện thoại'),
    ('LH002', N'Laptop'),
    ('LH003', N'Tivi'),
	('LH004', N'Đồ gia dụng'),
	('LH005', N'Máy tính bảng'),
	('LH006', N'Điều hòa'),
	('LH007', N'Máy ảnh'),
	('LH008', N'Loa Bluetooth'),
	('LH009', N'Quạt điện'),
	('LH010', N'Ổ cắm điện')
    -- Thêm dữ liệu tiếp theo tương tự cho 7 loại hàng khác...

-- Thêm dữ liệu vào bảng mathang
INSERT INTO mathang (MAHANG, TENHANG, MACONGTY, MALOAIHANG, SOLUONG, DONVITINH, GIAHANG)
VALUES
    ('MH011', N'iPhone 12', 'NCC001', 'LH001', 50, N'Chiếc', 20000000),
    ('MH012', N'Macbook Pro', 'NCC002', 'LH002', 30, N'Chiếc', 40000000),
    ('MH013', N'Samsung QLED 4K', 'NCC003', 'LH003', 20, N'Chiếc', 25000000),
	('MH014', N'Sony PlayStation 5', 'NCC001', 'LH004', 10, N'Chiếc', 8000000),
	('MH015', N'iPad Pro', 'NCC005', 'LH002', 15, N'Chiếc', 15000000),
	('MH016', N'LG Smart TV', 'NCC006', 'LH003', 25, N'Chiếc', 12000000),
	('MH017', N'Canon EOS R6', 'NCC007', 'LH001', 5, N'Chiếc', 30000000),
	('MH018', N'JBL Flip 5', 'NCC008', 'LH002', 50, N'Chiếc', 2000000),
	('MH019', N'Panasonic Electric Fan', 'NCC003', 'LH009', 40, N'Chiếc', 1000000),
	('MH020', N'Xiaomi Power Strip', 'NCC010', 'LH010', 100, N'Chiếc', 300000),
	('MH021', N'iPhone 12', 'NCC001', 'LH001', 50, N'Chiếc', 20000000),
    ('MH022', N'Macbook Pro', 'NCC002', 'LH002', 30, N'Chiếc', 40000000),
    ('MH023', N'Samsung QLED 4K', 'NCC003', 'LH003', 20, N'Chiếc', 25000000),
	('MH024', N'Sony PlayStation 5', 'NCC004', 'LH004', 10, N'Chiếc', 8000000),
	('MH025', N'iPad Pro', 'NCC005', 'LH002', 15, N'Chiếc', 15000000),
	('MH026', N'LG Smart TV', 'NCC006', 'LH005', 25, N'Chiếc', 12000000),
	('MH027', N'Canon EOS R6', 'NCC007', 'LH005', 5, N'Chiếc', 30000000),
	('MH028', N'JBL Flip 5', 'NCC008', 'LH005', 50, N'Chiếc', 2000000),
	('MH029', N'Panasonic Electric Fan', 'NCC009', 'LH005', 40, N'Chiếc', 1000000),
	('MH030', N'Xiaomi Power Strip', 'NCC010', 'LH004', 100, N'Chiếc', 300000)
    -- Thêm dữ liệu tiếp theo tương tự cho 7 mặt hàng khác...

-- Thêm dữ liệu vào bảng nhanvien
INSERT INTO nhanvien (MANHANVIEN, HO, NGAYSINH, NGAYLAMVIEC, DIACHI, DIENTHOAI, LUONGCOBAN)
VALUES
    ('NV001', N'Nguyễn', '1990-01-01', '2020-01-01', N'123 Đường ABC, Quận XYZ', '1234567890', 10000000),
    ('NV002', N'Trần', '1995-05-05', '2021-01-01', N'456 Đường XYZ, Quận ABC', '0987654321', 12000000),
    ('NV003', N'Lê', '1988-10-10', '2019-01-01', N'789 Đường DEF, Quận UVW', '0123456789', 15000000),
	('NV004', N'Phạm', '1992-03-15', '2022-01-01', N'101 Đường UVW, Quận DEF', '9876543210', 11000000),
	('NV005', N'Huỳnh', '1998-07-20', '2023-01-01', N'202 Đường MNO, Quận PQR', '5678901234', 13000000),
	('NV006', N'Võ', '1991-12-25', '2024-01-01', N'303 Đường STU, Quận GHI', '4567890123', 14000000),
	('NV007', N'Ngô', '1993-06-30', '2022-01-01', N'404 Đường JKL, Quận MNO', '3456789012', 12500000),
	('NV008', N'Đặng', '1996-09-05', '2023-01-01', N'505 Đường PQR, Quận STU', '2345678901', 13500000),
	('NV009', N'Bùi', '1994-02-10', '2024-01-01', N'606 Đường GHI, Quận JKL', '1234567890', 11500000),
	('NV010', N'Phan', '1997-08-15', '2023-01-01', N'707 Đường MNO, Quận PQR', '0123456789', 12500000)
    -- Thêm dữ liệu tiếp theo tương tự cho 7 nhân viên khác...

-- Thêm dữ liệu vào bảng dondathang
INSERT INTO dondathang (SOHOADON, MAKHACHHANG, MANHANVIEN, NGAYDATHANG, NGAYCHUYENHANG, NOIGIAOHANG)
VALUES
    ('DH001', 'KH001', 'NV001', '2024-01-01', '2024-01-02', N'123 Đường ABC, Quận XYZ'),
    ('DH002', 'KH002', 'NV002', '2024-01-02', '2024-01-03', N'456 Đường XYZ, Quận ABC'),
    ('DH003', 'KH003', 'NV003', '2024-01-03', '2024-01-04', N'789 Đường DEF, Quận UVW'),
	('DH004', 'KH004', 'NV004', '2024-01-04', '2024-01-05', N'101 Đường UVW, Quận DEF'),
	('DH005', 'KH005', 'NV005', '2024-01-05', '2024-01-06', N'202 Đường MNO, Quận PQR'),
	('DH006', 'KH006', 'NV006', '2024-01-06', '2024-01-07', N'303 Đường STU, Quận GHI'),
	('DH007', 'KH007', 'NV007', '2024-01-07', '2024-01-08', N'404 Đường JKL, Quận MNO'),
	('DH008', 'KH008', 'NV008', '2024-01-08', '2024-01-09', N'505 Đường PQR, Quận STU'),
	('DH009', 'KH009', 'NV009', '2024-01-09', '2024-01-10', N'606 Đường GHI, Quận JKL'),
	('DH010', 'KH010', 'NV010', '2024-01-10', '2024-01-11', N'707 Đường MNO, Quận PQR')


    -- Thêm dữ liệu tiếp theo tương tự cho 7 đơn đặt hàng khác...
INSERT INTO chitietdonhang (SOHOADON, MAHANG, GIABAN, SOLUONG, MUCGIAMGIA)
VALUES
('DH001', 'MH011', 20000000, 2, 0.1),
('DH001', 'MH015', 12000000, 1, 0.05),
('DH001', 'MH013', 40000000, 1, 0),
('DH001', 'MH017', 300000, 5, 0),

('DH002', 'MH012', 40000000, 1, 0),
('DH002', 'MH014', 8000000, 1, 0),
('DH002', 'MH016', 2000000, 3, 0),

('DH003', 'MH018', 8000000, 2, 0),
('DH003', 'MH020', 12000000, 1, 0.05),
('DH003', 'MH019', 15000000, 1, 0.05),
('DH003', 'MH021', 30000000, 1, 0.1),

('DH004', 'MH023', 30000000, 1, 0.1),
('DH004', 'MH025', 2000000, 3, 0),

('DH005', 'MH027', 1000000, 2, 0),
('DH005', 'MH026', 300000, 5, 0),
('DH005', 'MH030', 25000000, 2, 0.3),

('DH006', 'MH029', 30000000, 1, 0.1),
('DH006', 'MH015', 2000000, 3, 0),
('DH006', 'MH016', 12000000, 1, 0.05),

('DH007', 'MH026', 12000000, 1, 0.05),
('DH007', 'MH025', 15000000, 1, 0.05),
('DH007', 'MH023', 25000000, 2, 0.3),

('DH009', 'MH018', 2000000, 3, 0),
('DH009', 'MH028', 300000, 5, 0),
('DH009', 'MH019', 1000000, 2, 0),

('DH010', 'MH011', 20000000, 2, 0.1),
('DH010', 'MH027', 30000000, 1, 0.1),
('DH010', 'MH013', 300000, 5, 0),
('DH010', 'MH023', 25000000, 2, 0.3),
('DH010', 'MH016', 12000000, 1, 0.05);

-- Danh sách họ tên của nhân viên sinh vào tháng 3 hoặc tháng 10:
SELECT HO AS HO_TEN
FROM NHANVIEN
WHERE MONTH(NGAYSINH) IN (3, 10);

-- Danh sách khách hàng có địa chỉ không thuộc quận Đống Đa hoặc quận Hoàng Mai
SELECT *
FROM KHACHHANG
WHERE DIACHI NOT LIKE '%Đống Đa%' AND DIACHI NOT LIKE '%Hoàng Mai%';

-- Tính tổng số tiền của từng hóa đơn xuất
SELECT ddh.SOHOADON, SUM(cd.GIABAN * cd.SOLUONG * (1 - cd.MUCGIAMGIA)) AS TongTienHoaDon
FROM dondathang ddh
JOIN chitietdonhang cd ON ddh.SOHOADON = cd.SOHOADON
GROUP BY ddh.SOHOADON
ORDER BY ddh.SOHOADON;


-- Tính tổng số tiền mà mỗi khách hàng đã mua hàng
SELECT kh.MAKHACHHANG, kh.TENKHACHHANG, SUM(cd.GIABAN * cd.SOLUONG * (1 - cd.MUCGIAMGIA)) AS TongTienMuaHang
FROM khachhang kh
JOIN dondathang ddh ON kh.MAKHACHHANG = ddh.MAKHACHHANG
JOIN chitietdonhang cd ON ddh.SOHOADON = cd.SOHOADON
GROUP BY kh.MAKHACHHANG, kh.TENKHACHHANG
ORDER BY TongTienMuaHang DESC;


-- Tên mặt hàng nào chỉ được mua đúng một lần trong năm 2014
SELECT mh.TENHANG
FROM mathang mh
JOIN chitietdonhang cd ON mh.MAHANG = cd.MAHANG
JOIN dondathang ddh ON cd.SOHOADON = ddh.SOHOADON
WHERE YEAR(ddh.NGAYDATHANG) = 2014
GROUP BY mh.TENHANG
HAVING COUNT(DISTINCT ddh.SOHOADON) = 1;

--  Danh sách các tên mặt hàng đã được mua trong năm 2014 với tổng tiền trên 10 triệu
SELECT mh.TENHANG, SUM(cd.GIABAN * cd.SOLUONG * (1 - cd.MUCGIAMGIA)) AS TongTien
FROM mathang mh
JOIN chitietdonhang cd ON mh.MAHANG = cd.MAHANG
JOIN dondathang ddh ON cd.SOHOADON = ddh.SOHOADON
WHERE YEAR(ddh.NGAYDATHANG) = 2014
GROUP BY mh.TENHANG
HAVING SUM(cd.GIABAN * cd.SOLUONG * (1 - cd.MUCGIAMGIA)) > 10000000
ORDER BY TongTien DESC;

-- Những nhân viên có cùng ngày tháng năm sinh.
SELECT n1.MANHANVIEN, n1.HO, n1.NGAYSINH, n2.MANHANVIEN AS MANHANVIEN2, n2.HO AS HO2
FROM nhanvien n1, nhanvien n2
WHERE n1.NGAYSINH = n2.NGAYSINH AND n1.MANHANVIEN < n2.MANHANVIEN;

-- Khách hàng có tổng tiền mua hàng cao nhất
SELECT TOP 1 kh.MAKHACHHANG, kh.TENKHACHHANG, SUM(cd.GIABAN * cd.SOLUONG * (1 - cd.MUCGIAMGIA)) AS TongTien
FROM khachhang kh
JOIN dondathang ddh ON kh.MAKHACHHANG = ddh.MAKHACHHANG
JOIN chitietdonhang cd ON ddh.SOHOADON = cd.SOHOADON
GROUP BY kh.MAKHACHHANG, kh.TENKHACHHANG
ORDER BY TongTien DESC;






