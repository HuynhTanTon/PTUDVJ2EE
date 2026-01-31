# Demo Quản lý sách – Spring Boot + Thymeleaf

## Cấu trúc thư mục

```
Bai2/
├── src/main/java/com/example/bai2/
│   ├── Controller/
│   │   ├── BookController.java   # Controller Thymeleaf (routes /books)
│   │   └── HomeController.java  # Trang chủ /
│   ├── model/
│   │   └── Book.java            # Entity: id, title, author (+ validation)
│   ├── repository/
│   │   ├── BookRepository.java       # Interface
│   │   └── InMemoryBookRepository.java  # Implement in-memory + dữ liệu mẫu
│   ├── service/
│   │   ├── BookService.java         # Interface
│   │   └── BookServiceImpl.java    # Implement
│   └── Bai2Application.java
└── src/main/resources/
    └── templates/
        ├── books.html       # Danh sách sách
        ├── add-book.html    # Thêm sách mới
        ├── edit-book.html   # Sửa sách
        └── home.html        # Trang chủ
```

## Cách chạy

1. Mở terminal tại thư mục **Bai2**:
   ```bash
   cd E:\J2EE\PTUDVJ2EE\Bai2
   ```
2. Chạy ứng dụng:
   ```bash
   .\mvnw spring-boot:run
   ```
3. Đợi log: `Tomcat started on port 8080`.

## URL để test (localhost:8080)

| Mô tả              | Method | URL                    | Kết quả                    |
|--------------------|--------|------------------------|----------------------------|
| Trang chủ          | GET    | http://localhost:8080/ | home.html                  |
| Danh sách sách     | GET    | http://localhost:8080/books | books.html (bảng + 5 sách mẫu) |
| Form thêm sách     | GET    | http://localhost:8080/books/add | add-book.html          |
| Gửi form thêm      | POST   | http://localhost:8080/books/add | Thêm xong → redirect /books |
| Form sửa sách      | GET    | http://localhost:8080/books/edit/1 | edit-book.html (load sách id=1) |
| Gửi form sửa       | POST   | http://localhost:8080/books/edit/1 | Cập nhật xong → redirect /books |
| Xóa sách           | GET    | http://localhost:8080/books/delete/1 | Xóa id=1 → redirect /books |

## Dữ liệu mẫu (khi vào /books)

- 1 – Java Core – James Gosling  
- 2 – Spring Boot in Action – Craig Walls  
- 3 – Docker Deep Dive – Nigel Poulton  
- 4 – Clean Code – Robert C. Martin  
- 5 – Thymeleaf Tutorial – Pivotal  

## Validation

- **Tiêu đề** và **Tác giả**: không được để trống.
- Lỗi hiển thị ngay dưới ô input (Thymeleaf: `th:errors`, `#fields.hasErrors`).
