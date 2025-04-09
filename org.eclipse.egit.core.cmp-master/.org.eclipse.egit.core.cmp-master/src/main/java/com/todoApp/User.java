package com.todoApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "user_name", length = 50)
    private String userName;

    @Nationalized
    @Column(name = "password", length = 100)
    private String password;

/*
 TODO [Reverse Engineering] create field to map the 'created_time' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "created_time", columnDefinition = "timestamp not null")
    private Object createdTime;
*/
}