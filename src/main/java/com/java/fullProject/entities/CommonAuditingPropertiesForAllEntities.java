package com.java.fullProject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*Here we are keeping all the common Audit related properties, We can extend this super class in those
entities which need the auditing properties, It's better to keep the Auditing related properties here
rather than repeating them in all the required entities, We will mark our Base entity as
@MappedSuperclass and not @Entity,and if we are keeping the common entities here then we have to mark
this with @EntityListeners(AuditingEntityListener.class) and remove @EntityListeners(AuditingEntityListener.class)
from that entity class, see Product entity for more information*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/*This annotation helps with @CreatedBy filed below to get the Auditing
related properties like @CreatedBy, @LastModifiedBy,@LastModifiedDate, etc. If wew are using common
class to hold the auditing entities then we will put this annotation here or else we will put the annotation
in specific entity where auditing is required but not here*/
@Data
public class CommonAuditingPropertiesForAllEntities {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
/* This annotation is provided by hibernate,it will get the timestamp automatically when the field
 was updated.*/
    @Column(insertable = false)
    private LocalDateTime lastUpdated;

    @CreatedBy
  /*This annotation is used to keep track of the person who have created the record, to get the name
  of the person I have to create a new class called ProductEntityCreatedBy, check it for more info.
  1- use @EntityListeners(AuditingEntityListener.class) on top of the  class,
  2- use @EnableJpaAuditing(auditorAwareRef = "productEntryCreatedBy") on top of main class, here
  auditorAwareRef takes the bean of our class that implements AuditorAware*/
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
  /*This annotation is used to keep track of the person who have updated the record, to get the name
  of the person I have to create a new class called ProductEntityCreatedBy, check it for more info.
  1- use @EntityListeners(AuditingEntityListener.class) on top of the  class,
  2- use @EnableJpaAuditing(auditorAwareRef = "productEntryCreatedBy") on top of main class, here
  auditorAwareRef takes the bean of our class that implements AuditorAware*/
    @Column(insertable = false)
    private String UpdatedBy;
}
