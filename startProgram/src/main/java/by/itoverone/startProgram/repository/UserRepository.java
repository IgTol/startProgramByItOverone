package by.itoverone.startProgram.repository;

import by.itoverone.startProgram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer integer);
    Optional<User> findByName(String name);
}
