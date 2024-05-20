--1/ Tạo database
--2/ Tạo bảng 
--3/ Cấu trúc của bảng
--4/ Các thao tác với dữ liệu
--a. thêm (chèn dữ liệu vào bảng)	
 Insert into <Tên_bảng> Values (các giá trị) 
 --ví dụ:
 Insert into loaisach Values('LS003',N'Hệ thống thông tin')
 select * from loaisach

 ----Cách 2:
 Insert into <Tên bảng>(tên cột) Values(giá trị tương ứng)
 -- ví dụ
  Insert loaisach(Maloai,Tenloai) values('LS004',N'Mạng truyền thông')
 ---b. Sửa dữ liệu
 Update <tên bảng> Set <cot1>=<gt1>,<cot2>=<gt2>,....
 Where <đk sửa> 
 --Ví dụ: Sửa tên loại và mô tả của loại sách có mã là LS002
 Update loaisach Set Tenloai=N'Mạng máy tính', Mota=N'Điện tử'
 Where maloai='LS002'
 select*from loaisach
 ---c. Xóa dữ liệu trong bảng
 -- Xóa toàn bộ dữ liệu trong bảng
 Delete <tên bảng>
 --Xóa theo điều kiện
 Delete <tên bảng> Where <điều kiện>
 --ví dụ: xóa loại sách có mã là LS002
 Delete Loaisach where Maloai='LS002'
 ---d. Tìm kiếm dữ liệu
 Select <ds cột muốn hiển thị>
 From <tên bảng>
 Where <điều kiện>
 --vd: hiển thị loại sách mà tên loại chứa chữ 'thông'
 --- và mô tả  chứa chữ 'c'
 Select*From LoaiSach 
 Where TenLoai like N'%thông%' and MoTa like '%c%'