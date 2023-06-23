package com.example.jpa.entities;
/*
CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    age INTEGER,
    phone TEXT,
    email TEXT,
    created_at timestamp default (datetime('now')
);
 */

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
// db 의 테이블로써 하고싶다면?
@Entity // 데이터베이스 테이블의 레코드를 나타냄
@Table(name = "students")
public class StudentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username", nullable = false)
    // unique 는 t or f 로 설정가능
    // null 은 nullable t or f 로 설정가능
    private String name;
    private Integer age;
//    @Column(unique = true)
    private String phone;
    private String email;

    private Instant createdAt;
}
