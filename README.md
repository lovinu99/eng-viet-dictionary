# eng-viet dictionary
	[java] csv, swing, collection


# introduce
	Ứng dụng sử dụng Swing để tạo giao diện
	dùng thư viện xml và dom để đọc ghi file csv
	org.w3c.dom
	javax.xml

# directory structure
	• Source: chứa mã nguồn
	• release: chứa file thực thi và các tài nguyên cần thiết

# use
=>Chức năng:
	tra từ:
		dùng ô text bên trái để nhập từ, sau dó chọn chế độ anhviet hoặc vietanh-> bấm tra từ
	Thêm từ:
		dùng ô bên trái để nhập từ cần thêm, ô bên phải nhập nghĩa, sau đó chọn chế độ(danh sách cần thêm vào) và nhấn nút thêm
	Xóa từ:
		dùng ô bên trái để nhập từ cần xóa, chọn chế độ(danh sách cần xóa ), bấm nút xóa để xóa
	Xem danh sách:
		chọn chế độ(danh sách), bấm nút Xem danh sach
	Thống kê:
		nhập hai khoảng thời gian theo format: "năm-tháng-ngày"+ xuống dòng +"năm-tháng-ngày"
		sau đó chọn danh sách "lịch sử" bấm nút thống kê
		ví dụ:
		2020-05-14
		2020-05-16
	Lưu dữ liệu:
		bấm nút thoát X góc trên phải, bấm Yes để lưu và thoát, NO để thoát
	Sắp xếp tăng giảm danh sách yêu thích:
		chọn danh sách yêu thích, chọn nút xếp tăng hoặc giảm

=>cấu trúc Lưu trữ:
	yeuthich.xml:
		lưu dữ liệu danh sách yêu thích theo cấu trúc tương tự file Anh_Viet.xml
	history.xmxl:
		lưu lịch sử tra cứu theo ngày theo cấu trúc
			<ditionary>
				<record>
					<date><date>
					<word></word>
					.....
					<word></word>


# note
để tiện cho test, đọc bằng vòng for i=i+20
nếu thầy cần thì có thể sửa lại tại: file myGUI line 171,172


