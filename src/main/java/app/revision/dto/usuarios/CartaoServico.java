package app.revision.dto.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.repository.usuario.CartaoRepository;

@Service
public class CartaoServico {

    @Autowired
    private CartaoRepository CartaoRepository;

    public List<Cartao> listAll() {
        return CartaoRepository.findAll();
    }

}
