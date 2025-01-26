package com.example.JPAdemo.entity;

import jakarta.persistence.*;


@MappedSuperclass
    //MappedSuperclass- no need of @Entity in this case, (for inheritance employee needs to be entity )
    //MappedSuperclass will have 2 separate tables for child classes, supr class will not be entity, it will run 2 separate select queries on db tables
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    //SINGLE_TABLE: is default type, here data for both full time and part time will be stored in single employee table, it will have null values for NA columns
    //Single table is used when we need good performance
    //DTYPE table will give subtype of rowfor SINGLE_TABLE, following DiscriminatorColumn can change name of this column
//@DiscriminatorColumn(name="Employee_type")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    //TABLE_PER_CLASS stores data in table per concret class, at the time of select it will union both table selects
//@Inheritance(strategy = InheritanceType.JOINED)
    //JOINED - it have table for all entities, employee table will have common data, part time and full time table have theire own specific data, join query is use to run select
    //joined is used when data integrety and quality matters allot
public abstract class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
