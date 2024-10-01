package app.revision.dao.repository.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.carrinho.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
