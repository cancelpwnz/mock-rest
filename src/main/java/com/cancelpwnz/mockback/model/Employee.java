package com.cancelpwnz.mockback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Schema( example = "{\"id\": 1, \"name\": \"John Doe\", \"age\": 30, \"married\": true, \"startWorkSt\": \"2022-01-01T00:00:00Z\", \"chief\": {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"John Smith\",\n" +
        "      \"age\": 22,\n" +
        "      \"married\": false,\n" +
        "      \"startWorkSt\": \"2021-01-01T00:00:00Z\",\n" +
        "      \"chief\": null\n" +
        "    }}")
public class Employee {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 18L)
    private Integer age;
    @NotNull
    private Boolean married;
    @NotNull
    private Instant startWorkSt;
    //@Schema(implementation = Employee.class)
    private Employee chief;

    public Employee() {
    }

    public Employee(Long id, String name, Integer age, Boolean married, Instant startWorkSt, Employee chief) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
        this.startWorkSt = startWorkSt;
        this.chief = chief;
    }


    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Employee setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getMarried() {
        return married;
    }

    public Employee setMarried(Boolean married) {
        this.married = married;
        return this;
    }

    public Instant getStartWorkSt() {
        return startWorkSt;
    }

    public Employee setStartWorkSt(Instant startWorkSt) {
        this.startWorkSt = startWorkSt;
        return this;
    }

    public Employee getChief() {
        return chief;
    }

    public Employee setChief(Employee chief) {
        this.chief = chief;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee1 = (Employee) o;
        return Objects.equals(id, employee1.id) &&
                Objects.equals(name, employee1.name) &&
                Objects.equals(age, employee1.age) &&
                Objects.equals(married, employee1.married) &&
                Objects.equals(startWorkSt, employee1.startWorkSt) &&
                Objects.equals(chief, employee1.chief);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, married, startWorkSt, chief);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", married=" + married +
                ", startWorkSt=" + startWorkSt +
                ", chief=" + chief +
                '}';
    }

}

