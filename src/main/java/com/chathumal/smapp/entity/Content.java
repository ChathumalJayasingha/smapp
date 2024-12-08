package com.chathumal.smapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "content")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content extends SuperEntity implements Serializable {
    @TableGenerator(name="tblcon", initialValue= 50000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tblcon")
    private int cid;
    private String content;
    @ManyToOne(targetEntity = User.class)
    private User user;

}
