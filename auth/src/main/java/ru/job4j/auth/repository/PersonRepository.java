package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.auth.domain.Person;

/**
 * @author madrabit on 01.10.2020
 * @version 1$
 * @since 0.1
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
