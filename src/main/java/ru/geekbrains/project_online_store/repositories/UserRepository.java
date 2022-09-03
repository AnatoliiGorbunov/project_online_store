package ru.geekbrains.project_online_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.project_online_store.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
