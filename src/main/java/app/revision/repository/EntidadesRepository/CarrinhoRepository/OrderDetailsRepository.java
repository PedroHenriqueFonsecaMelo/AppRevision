package app.revision.repository.EntidadesRepository.CarrinhoRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.entidades.carrinho.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
