package app.revision.DTOs.carrinhoDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.entidades.carrinho.Order;
import app.revision.repository.EntidadesRepository.CarrinhoRepository.OrderRepository;

@Service
public class OrderServico {
    @Autowired
    private OrderRepository ClienteRepository;

    public List<Order> listAll() {
        return ClienteRepository.findAll();
    }
}
