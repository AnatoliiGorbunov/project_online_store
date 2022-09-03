package ru.geekbrains.project_online_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.project_online_store.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username like :username")
    List<Order> findByUsername(String username);
}
