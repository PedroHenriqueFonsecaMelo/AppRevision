package app.revision.dao.repository.carrinho;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.carrinho.Order;
import app.revision.dao.entidades.usuario.Cliente;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCliente(Cliente cliente);

}
