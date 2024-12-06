package com.chathumal.smapp.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public abstract class SuperEntity {
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadt;
}
