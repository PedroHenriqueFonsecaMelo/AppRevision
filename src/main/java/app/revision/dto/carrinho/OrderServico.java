package app.revision.dto.carrinho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.dao.entidades.carrinho.Order;
import app.revision.dao.repository.carrinho.OrderRepository;

@Service
public class OrderServico {
    @Autowired
    private OrderRepository ClienteRepository;

    public List<Order> listAll() {
        return ClienteRepository.findAll();
    }
}
