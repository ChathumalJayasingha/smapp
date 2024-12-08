package com.chathumal.smapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends SuperEntity implements Serializable {
    @TableGenerator(name="tbl", initialValue= 20000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tbl")
    private int id;
    private String name;
    private String address;
    @Column(unique=true)
    private String contact;
    @Column(unique=true)
    private String email;
    private String password;
    @Column(columnDefinition = "TINYINT NOT NULL DEFAULT 0")
    private boolean fulacs;
}
