package ru.job4j.auth.domain;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author madrabit on 02.10.2020
 * @version 1$
 * @since 0.1
 */

public class Employee {
    private int id;
    private String name;
    private int inn;
    private LocalDate hiringDate;

    @Transient
    List<Person> persons;

    public static Employee of(int id, String name, int inn, LocalDate hiringDate, List<Person> persons) {
        Employee e = new Employee();
        e.id = id;
        e.name = name;
        e.inn = inn;
        e.hiringDate = hiringDate;
        e.persons = persons;
        return e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id
                && inn == employee.inn
                && Objects.equals(name, employee.name)
                && Objects.equals(hiringDate, employee.hiringDate)
                && Objects.equals(persons, employee.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inn, hiringDate, persons);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", inn=").append(inn);
        sb.append(", hiringDate=").append(hiringDate);
        sb.append(", persons=").append(persons);
        sb.append('}');
        return sb.toString();
    }
}
