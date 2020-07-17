package ru.noorsoft.javaeducation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);

    User findById(long id);

    User deleteById(long id);

}