## Thông tin nhóm

| STT | Họ và tên      | MSSV    |
| --- | -------------- | ------- |
| 1   | Thái Thành Duy | 2210535 |
| 2   | Hàng Nhựt Long | 2210536 |

---

# Lab 1 – Khởi Tạo & Kiến Trúc Hệ Thống
## 1. Dữ liệu lớn (thêm ít nhất 10 sinh viên)

```sql
INSERT INTO students (id, name, email, age) VALUES
(3, 'Le Van C', 'c@example.com', 22),
(4, 'Pham Thi D', 'd@example.com', 19),
(5, 'Hoang Van E', 'e@example.com', 21),
(6, 'Nguyen Thi F', 'f@example.com', 23),
(7, 'Tran Van G', 'g@example.com', 20),
(8, 'Bui Thi H', 'h@example.com', 24),
(9, 'Do Van I', 'i@example.com', 18),
(10, 'Vu Thi K', 'k@example.com', 22),
(11, 'Dang Van L', 'l@example.com', 21),
(12, 'Ngo Thi M', 'm@example.com', 20);
```

Kết quả: thêm thành công nhiều bản ghi sinh viên phục vụ cho việc test.

---

## 2. Ràng buộc khóa chính (Primary Key)

### 2.1 Thử insert id trùng

```sql
INSERT INTO students (id, name, email, age)
VALUES (1, 'Test', 'dup@example.com', 30);
```

### 2.2 Kết quả
Thực thi thất bại.

```
Execution finished with errors.
Result: UNIQUE constraint failed: students.id
```

### 2.3 Giải thích

Cột `id` được khai báo:

```sql
id INTEGER PRIMARY KEY
```

`PRIMARY KEY` có hai tính chất:

* **NOT NULL**: không được để trống
* **UNIQUE**: không được trùng lặp

Mỗi sinh viên phải có một `id` duy nhất để định danh. Database chặn thao tác insert id trùng nhằm đảm bảo **toàn vẹn dữ liệu**, tránh nhầm lẫn khi truy vấn, cập nhật hoặc xóa dữ liệu.

---

## 3. Toàn vẹn dữ liệu (Constraints)

### 3.1 Thử insert name = NULL

```sql
INSERT INTO students (id, name, email, age)
VALUES (13, NULL, 'nullname@example.com', 25);
```

### 3.2 Kết quả
Thực thi thất bại.

```
Execution finished without errors.
Result: query executed successfully. 1 rows affected
```

### 3.3 Giải thích

Cột `name` được khai báo:

```sql
name TEXT
```

Không có ràng buộc `NOT NULL`, nên SQLite cho phép giá trị `NULL`.

### 5.4 Ảnh hưởng khi code Java đọc dữ liệu

Nếu trong Java có đoạn code:

```java
student.getName().toUpperCase();
```

Khi `name = null` sẽ gây ra **NullPointerException**, làm chương trình bị lỗi runtime. Ngoài ra, dữ liệu sinh viên không có tên cũng là dữ liệu không hợp lệ về mặt nghiệp vụ.

---

