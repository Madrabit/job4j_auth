package ru.job4j.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author madrabit on 02.10.2020
 * @version 1$
 * @since 0.1
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final String API = "http://localhost:8080//person/";
    private static final String API_ID = "http://localhost:8080//person/{id}";


    @Autowired
    private RestTemplate rest;

    //curl -i http://localhost:8080/employee/
    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> rsl = new ArrayList<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {
                }
        ).getBody();

        Employee employee = Employee.of(1, "Зам.дир", 4444, LocalDate.now(), persons);
        rsl.add(employee);
        return rsl;
    }

    // curl -H 'Content-Type: application/json' -X POST -d '{"login":"job4j@gmail.com","password":"123"}' http://localhost:8080/employee/
    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    // curl -i -H 'Content-Type: application/json' -X PUT -d '{"id":"1","login":"support@job4j.com","password":"123"}' http://localhost:8080/employee/
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        person.setEmployeeId(1);
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    // curl -i -X DELETE http://localhost:8080/employee/11
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }


}
