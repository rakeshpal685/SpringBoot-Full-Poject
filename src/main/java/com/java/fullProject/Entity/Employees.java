package com.java.fullProject.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "employees")
public class Employees {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "messageid")
  private int message_id;

  @Column(name = "status")
  private String status;

  @Column(name = "version_id")
  private int version_id;

  @Column(name = "date_created")
  private Timestamp date_created;

  @Column(name = "date_updated")
  private Timestamp date_updated;
}
