create database ASM_DuAnMau
go
	use ASM_DuAnMau
go
-----------------Nhân viên--------------------------------------
	create table NhanVien(
		MaNV varchar(10) primary key not null,
		MatKhau varchar(20) not null,
		HoTen nvarchar(50) not null,
		VaiTro bit not null,
	)
	insert into NhanVien(MaNV,MatKhau,HoTen,VaiTro) values
	('hieunt','12345',N'Nguyễn Trung Hiếu',0),
	('NoPT','12345',N'Phạm Thị Nở',1),
	('TeoNV','12345',N'Phạm Văn Tèo',0);
	insert into NhanVien values('ph11817','12345',N'Trung Hiếu',1);

	select * from NhanVien
	-------------------- Chuyên đề -----------------------
	create table ChuyenDe(
		MaCD varchar(10) primary key not null,
		TenCD nvarchar(50) null,
		HocPhi money null,
		thoiLuong TINYINT null,
		logo nvarchar(50) null,
		MoTa nvarchar(200) null,
	)
	insert into ChuyenDe (MaCD,TenCD,HocPhi,thoiLuong,logo,MoTa) values
	('JAV01',N'Lập trình java',3000,50,N'a2.jpg',N'không mô tả'),
	('SOF201',N'Dự án mẫu',2500,90,N'a3.jpg',N'không mô tả'),
	('SOF1014',N'Cơ sở dữ liệu',1500,75,N'a4.jpg',N'không mô tả'),
	('SER01',N'Xây dựng trang WEB',3500,90,N'a3.jpg',N'không mô tả');

	update ChuyenDe set TenCD=?,HocPhi=?,thoiLuong=?,logo=?,MoTa=? where MaCD=?
	delete from ChuyenDe where MaCD=?
	select * from ChuyenDe
	----------delete ChuyenDe
	---------------------KHóa H?c ------------------
	create table KhoaHoc(
		MaKH int Identity primary key not null,
		MaCD varchar(10) null,
		HocPhi money null,
		ThoiLuong TINYINT null,
		NgayKhaiGiang date null,
		GhiChu nvarchar(200),
		MaNV varchar(10) not null,
		NgayTao date not null,
		constraint FK_MaCD foreign key(MaCD) references ChuyenDe(MaCD),
		constraint FK_MaNV_KH foreign key(MaNV) references NhanVien(MaNV),
	)

	insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKhaiGiang,GhiChu,MaNV,NgayTao) values
	('SER01',2000,90,'2020-01-25',N'Ghi chú 234ds','ph11817','2018-04-19'),
	('SOF201',2500,70,'2020-10-15',N'Ghi chú 1','NoPT','2019-09-04'),
	('JAV01',3000,50,'2019-09-04',N'Ghi chú 2','hieunt','2020-01-25');
	insert into KhoaHoc values('SOF1014',2000,90,'2020-01-25',N'Ghi chú 3','hieunt','2018-04-19');
	insert into KhoaHoc values('SER01',2000,90,'2020-01-25',N'Test','hieunt','2018-04-19');
	select * from KhoaHoc
	---------------------- Ngu?i H?c ------------------------------
	create table NguoiHoc(
		MaNH varchar(10) primary key not null,
		HoTen nvarchar(50) null,
		NgSinh date not null,
		gt nvarchar(10) not null,
		SDT varchar(10) null,
		Email varchar(50) null,
		GhiChu nvarchar(200) null,
		MaNV varchar(10) not null,
		NgayDK date,
		constraint FK_MaNV_NH foreign key(MaNV) references NhanVien(MaNV),
	)

	insert into NguoiHoc(MaNH,HoTen,NgSinh,gt,SDT,Email,GhiChu,MaNV,NgayDK) values
	('NH001',N'Nguyễn Mạnh Dũng','2001-09-30',N'Nam','0975833151','dungnm@gmail.com',N'Ghi chú nguời học','hieunt','2017-11-02'),
	('NH015',N'Trần Văn Ðạt','2001-10-15',N'Nam','0368851510','dattv@gmail.com',N'Ghi chú nguời học','hieunt','2015-10-15'),
	('NH010',N'Hoàng THị Hiền','2001-03-14',N'Nữ','0975833151','hienht@gmail.com',N'Ghi chú nguời học','NoPT','2020-10-10');
	update NguoiHoc set NgayDK='2020-01-13' where MaNH='NH015'
	select * from NguoiHoc

	update NguoiHoc set HoTen=?,NgSinh=?,gt=?,SDT=?,Email=?,GhiChu=? where MaNH=?
	
	----------------------- H?c viên -----------------------------
	create table HocVien(
		MaHV int Identity primary key not null,
		MaKH int not null,
		MaNH varchar(10) not null,
		DiemTB float,
		constraint FK_MaKH foreign key(MaKH) references KhoaHoc(MaKH),
		constraint FK_MaNH foreign key(MaNH) references NguoiHoc(MaNH),
	)

	insert into HocVien(MaKH,MaNH,DiemTB) values
	(1,'NH001',9),
	(2,'NH010',7.5);
	insert into HocVien values(1,'NH015',8.5);
	update HocVien set DiemTB =? where MaHV=?
	use ASM_DuAnMau
	select * from HocVien
	----------------------- g?i b?ng ---------------------------------
	select * from NhanVien
	select * from ChuyenDe
	select * from KhoaHoc
	select * from HocVien
	select * from NguoiHoc
	

	--select * from NhanVien where MaNV=? and MatKhau=?

	update NhanVien set MatKhau=?, HoTen=? ,VaiTro=? where MaNV=?

	delete from NhanVien where MaNV=?


	--------------------------------------------------------------------------------

	go
	create proc sp_LuongNguoiHoc
		as begin
			select YEAR(NgayDK),COUNT(MaNH),min(NgayDK),max(NgayDK) from NguoiHoc group by YEAR(NgayDK)
		end

		exec sp_LuongNguoiHoc
------------------------------------------------
		Go
	create proc sp_DiemChuyenDe
		as begin
			select TenCD,COUNT(hv.MaHV),MIN(hv.DiemTB),MAX(hv.DiemTB),AVG(DiemTB) 
			from HocVien as hv join KhoaHoc as kh on hv.MaKH= kh.MaKH 
			join ChuyenDe as cd on kh.MaCD=cd.MaCD group by TenCD
		end




---------------------------------CÁC CÂU SELECT CẦN THIẾT---------------------------------------------------

	select YEAR(NgayDK) from NguoiHoc as nh left outer join HocVien as hv on nh.MaNH=hv.MaNH

	select DISTINCT(year(NgayKhaiGiang)) from KhoaHoc

	

	GO
	----------------------------------------------------------------------------
	create proc Sp_DoanhThu(@year int)
		as begin
			select cd.TenCD,kh.MaKH,COUNT(MaHV),SUM(kh.HocPhi),min(kh.HocPhi),max(kh.HocPhi),AVG(kh.HocPhi) from HocVien as hv 
			join KhoaHoc as kh on hv.MaKH=kh.MaKH join ChuyenDe as cd on kh.MaCD = cd.MaCD 
			where YEAR(kh.NgayKhaiGiang)=@year group by YEAR(NgayKhaiGiang), cd.TenCD,kh.MaKH
		end


		exec Sp_DoanhThu 2020

		-----------------------------------------------------------------------------------
		create proc Sp_BangDiem(@MaKH int)
			as begin
				select nh.MaNH,nh.HoTen,hv.DiemTB from HocVien as hv join NguoiHoc as nh on 
				hv.MaNH=nh.MaNH where hv.MaKH=@MaKH order by hv.DiemTB desc
			end

			exec Sp_BangDiem 1
		-------------------------------------------------------------------------------------
	Go
		create proc Sp_XoaNguoiHoc(@MaNH varchar(10))
		as 
			begin			
						declare @Ma varchar(10)
						select @Ma=MaNH from NguoiHoc where MaNH=@MaNH
						--------------------
						delete HocVien where @Ma =MaNH
						delete NguoiHoc where @Ma=MaNH		
			end

			exec Sp_XoaNguoiHoc 'NH00143'

			select * from NguoiHoc

			---------------------------------------------------

------------------------------------------------------------------------			select * from HocVien
		alter proc Sp_XoaChuyenDe(@MaCD varchar(10))
		as 
			begin
						declare @CD varchar(10)
						select @CD= MaCD from ChuyenDe where @MaCD=MaCD
						----------------
						declare @MaKH int 
						select @MaKH=MaKH  from KhoaHoc where MaCD=@MaCD
					
						--------------------
						delete HocVien where @MaKH = MaKH
						delete KhoaHoc where @CD =MaCD
						
						delete ChuyenDe where @CD=MaCD
			end

			exec Sp_XoaChuyenDe 'JAV013'
			select * from ChuyenDe

-----------------------------Xóa khóa học-------------------------------
Go
create proc Sp_XoaKhoaHoc(@MAKH int)
		as 
			begin
				declare @Ma int
				select @Ma=MaKH from KhoaHoc where MaKH=@MAKH
						--------------------
				delete HocVien where @Ma =HocVien.MaKH
				delete KhoaHoc where @Ma=KhoaHoc.MaKH
			end

			exec Sp_XoaKHoaHoc 17

			-------------------------------------------------------------
			GO


	exec XoaKhoaHoc 13
		select * from KhoaHoc
			select * from KhoaHoc
			select * from HocVien



			-----------------------------Xóa Nhân viên------------------------
Go
alter proc Sp_XoaNhanVien(@MaNV varchar(10))
	as 
		begin			
			declare @Ma varchar(10),@MaNH varchar(10)
			select @Ma=MaNV from NhanVien where MaNV=@MaNV
			select @MaNH=MaNH from NguoiHoc where MaNV=@MaNV
						--------------------
			delete HocVien where @MaNH=MaNH
			delete KhoaHoc where MaNV=@MaNV
						--------------------------
			delete NguoiHoc where @MaNV =NguoiHoc.MaNV
			delete NhanVien where @MaNV=NhanVien.MaNV
		end

			exec Sp_XoaNhanVien 'ph11817'
			use ASM_DuAnMau
			select * from NhanVien


			update NhanVien set MatKhau=? where MaNV=?



Go
{call Sp_XoaNhanVien ('NoPT123')}


select * from KhoaHoc where MaKH=?

select * from HocVien where DiemTB>=0 and DiemTB<=10

delete HocVien where MaHV=?

select * from NguoiHoc where HoTen like '%hi%'

update HocVien set DiemTB=5 where MaHV=4







