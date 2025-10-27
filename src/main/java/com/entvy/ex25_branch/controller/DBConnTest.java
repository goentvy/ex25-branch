package com.entvy.ex25_branch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DBConnTest {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/dbconn")
    public String dbconn() {
        try(Connection conn = dataSource.getConnection()) {
            String result = conn.getMetaData().getURL();
            return "DB 연결 성공: " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "DB 연결 실패";
        }
    }
}
