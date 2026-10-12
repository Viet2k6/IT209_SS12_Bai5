\# Báo cáo chẩn đoán và khắc phục sự cố CI/CD



\## 1. Lỗi Gradle Wrapper thiếu quyền thực thi



\*\*Nguyên nhân:\*\* Trên GitHub Actions Runner sử dụng Linux, tệp `gradlew` có thể không có quyền thực thi, khiến lệnh chạy Gradle báo lỗi `Permission denied`.



\*\*Cách khắc phục:\*\* Thêm bước cấp quyền trước khi chạy Gradle Test:



```bash

chmod +x gradlew

```



Sau đó thực thi:



```bash

./gradlew test bootJar --no-daemon

```



\## 2. Lỗi xác thực Docker Registry



\*\*Nguyên nhân:\*\* Tên biến Secret được khai báo sai hoặc không trùng với tên Secret trong GitHub Repository.



\*\*Cách khắc phục:\*\* Sử dụng đúng biến `REGISTRY\_TOKEN` trong bước đăng nhập GHCR:



```yaml

\- name: Login to GitHub Container Registry

&#x20; uses: docker/login-action@v3

&#x20; with:

&#x20;   registry: ghcr.io

&#x20;   username: ${{ github.actor }}

&#x20;   password: ${{ secrets.REGISTRY\_TOKEN }}

```



Repository cần có Secret `REGISTRY\_TOKEN` hợp lệ với quyền đẩy Docker Image lên GitHub Container Registry.



\## 3. Lỗi Docker Build Context



\*\*Nguyên nhân:\*\* Docker Build không tìm thấy `Dockerfile` do khai báo sai thư mục context hoặc đường dẫn Dockerfile.



\*\*Cách khắc phục:\*\* Cấu hình đúng trong bước `docker/build-push-action`:



```yaml

with:

&#x20; context: .

&#x20; file: ./Dockerfile

&#x20; push: true

```



Trong bài này, `Dockerfile` nằm ở thư mục gốc của repository nên sử dụng `context: .` và `file: ./Dockerfile`.



\## 4. Kiểm soát thứ tự các Job



Job `docker-build` sử dụng `needs: test`, đảm bảo chỉ build và đẩy Docker Image khi Job `test` thành công. Nếu Gradle Test thất bại, Job build sẽ bị bỏ qua.



\## 5. Kết luận



Workflow đã được bổ sung các bước sửa quyền Gradle Wrapper, sử dụng đúng Secret và xác định chính xác Docker Build Context. Kết quả cuối cùng được xác minh bằng log GitHub Actions sau khi chạy pipeline.



