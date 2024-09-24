package app.revision.DTOs.carrinhoDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.entidades.carrinho.OrderDetails;
import app.revision.repository.EntidadesRepository.CarrinhoRepository.OrderDetailsRepository;

@Service
public class OrderDetailsServico {
    @Autowired
    private OrderDetailsRepository ClienteRepository;

    public List<OrderDetails> listAll() {
        return ClienteRepository.findAll();
    }
}
