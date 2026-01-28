package org.ml.mldj.mgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ApplicationTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void initPassword() {
        String password = "1234";
        password = passwordEncoder.encode(password);
        System.out.println("password:" + password);

    }
}
