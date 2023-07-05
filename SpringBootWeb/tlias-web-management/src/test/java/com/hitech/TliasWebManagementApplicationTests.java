package com.hitech;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

    @Test
    public void genJwt() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "tom");

        String jwt = Jwts.builder()
                .setClaims(claims) // 自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "itheima") // 签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 有效期
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void parseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("itheima") // 指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjg4MzU0NzE5LCJ1c2VybmFtZSI6InRvbSJ9.3q9fwPnGiDe0KxzKHkGgYVt7X3FtxC9CvZTbfKENkn8")
                .getBody();
        System.out.println(claims);
    }

}
