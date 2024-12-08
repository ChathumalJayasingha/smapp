package com.chathumal.smapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "follow")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Follow extends SuperEntity implements Serializable {
    @TableGenerator(name="tblflw", initialValue= 70000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="tblflw")
    private int fid;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user",unique = false)
    private User user;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "follow", unique = false)
    private User flwusr;

}
