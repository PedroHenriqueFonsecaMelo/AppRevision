package app.revision.DTOs.usuariosDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.entidades.usuario.Cartao;
import app.revision.repository.EntidadesRepository.UsuarioRepository.CartaoRepository;

@Service
public class CartaoServico {

    @Autowired
    private CartaoRepository CartaoRepository;

    public List<Cartao> listAll() {
        return CartaoRepository.findAll();
    }

}
