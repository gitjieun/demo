package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class JndiConnection {

//    public String testConnection(String id) {
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        String result = ""; // 문자열 초기화
//
//        log.info("######### testConnection #########");
//
//        try {
//            Context ctx = new InitialContext(); // JNDI 초기 컨텍스트 생성
//            DataSource ds = (DataSource) ctx.lookup(id); // datasource 찾기
//
//            con = ds.getConnection(); // 연결 얻기
//            stmt = con.createStatement(); // Statement 생성
//
//            rs = stmt.executeQuery("select sysdate from dual"); // 쿼리 실행
//
//
//            while (rs.next()) {
//                result = rs.getString(1);
//                log.info("현재 날짜: " + result);
//            }
//
//        }
//
//
//        catch (NamingException e) {
//            e.printStackTrace();
//            log.error("JNDI Lookup Failed: " + e.getMessage());
//        }
//
//        catch (SQLException e) {
//            e.printStackTrace();
//            log.error("SQL 실행 Failed: " + e.getMessage());
//        }
//
//
//        finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.error("자원 해제 실패: " + e.getMessage());
//            }
//        }
//        return result; // 최종 결과 반환
//    }

    public String testConnection(String id) {
        String result = ""; // 문자열 초기화

        log.info("######### testConnection #########");

        try {
            Context ctx = new InitialContext(); // JNDI 초기 컨텍스트 생성
            DataSource ds = (DataSource) ctx.lookup(id); // datasource 찾기

            // try-with-resources 문법으로 리소스 관리
            try (Connection con = ds.getConnection();
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("select sysdate from dual")) {

                while (rs.next()) {
                    result = rs.getString(1);
                    log.info("현재 날짜: " + result);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                log.error("SQL 실행 Failed: " + e.getMessage());
            }

        } catch (NamingException e) {
            e.printStackTrace();
            log.error("JNDI Lookup Failed: " + e.getMessage());
        }

        return result; // 최종 결과 반환
    }
}

