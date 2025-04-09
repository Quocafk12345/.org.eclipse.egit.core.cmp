package com.todoApp.Entities;

import com.todoApp.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SharedTask {
    private long id;
    private long taskId;
    private String ownerUsername;
    private String colleagueUsername;
    private Timestamp createdAt;
}