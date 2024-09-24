package app.revision.repository.EntidadesRepository.CarrinhoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.entidades.carrinho.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
