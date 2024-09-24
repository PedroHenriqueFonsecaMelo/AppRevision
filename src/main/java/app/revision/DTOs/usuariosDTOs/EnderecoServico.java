package app.revision.DTOs.usuariosDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.entidades.usuario.Endereco;
import app.revision.repository.EntidadesRepository.UsuarioRepository.EnderecoRepository;

@Service
public class EnderecoServico {

    @Autowired
    private EnderecoRepository EnderecoRepository;

    public List<Endereco> listAll() {
        return EnderecoRepository.findAll();
    }

}
