package com.oi.db.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int id;
    private String username;
    private String email;
    private int age;
    private Timestamp createdAt;
}
