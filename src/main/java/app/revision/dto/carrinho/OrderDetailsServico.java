package app.revision.dto.carrinho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.dao.entidades.carrinho.OrderDetails;
import app.revision.dao.repository.carrinho.OrderDetailsRepository;

@Service
public class OrderDetailsServico {
    @Autowired
    private OrderDetailsRepository ClienteRepository;

    public List<OrderDetails> listAll() {
        return ClienteRepository.findAll();
    }
}
