package com.java.fullProject.entities;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
/*This class is created so that we can get the name of the person who is logged in and put that name in
the property of the entities @CreatedBy column or @LastModifiedBy column. Here the entity is the Product and the column name is
createdBy or (CommonAuditingPropertiesForAllEntities class for common Auditing properties),
This class must implement AuditorAware, and we have to pass the <datatype> which is same
as the dataType of the field where we are injecting the value.*/
public class ProductEntryCreatedBy implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(username);
        // return Optional.of(System.getProperty("user.name")); 
    }
}
