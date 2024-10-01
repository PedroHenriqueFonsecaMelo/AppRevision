package app.revision.dao.repository.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.carrinho.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
