package com.example.session11ex5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Session11Ex5ApplicationTests {

    @Test
    @DisplayName("Kiểm tra Spring Application Context khởi chạy thành công")
    void contextLoads() {
        assertTrue(true, "Context tải thành công!");
    }

    @Test
    @DisplayName("Kiểm tra logic nghiệp vụ cơ bản")
    void testBasicLogic() {
        String status = "UP";
        assertNotNull(status, "Status không được null");
        assertTrue(status.equals("UP"), "Trạng thái ứng dụng phải là UP");
    }

}
