package com.example.venky;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.mapping.Join;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
public class Employee {

    @Id
    private Integer id;
    private int k;
    private String name;
    private String dept;
    private Float salary;
    private Integer status;
    /*@OneToMany
    Join("")
    List<Subj> listSubject*/

}
