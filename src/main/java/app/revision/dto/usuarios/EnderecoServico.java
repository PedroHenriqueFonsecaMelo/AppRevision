package app.revision.dto.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.dao.entidades.usuario.Endereco;
import app.revision.dao.repository.usuario.EnderecoRepository;

@Service
public class EnderecoServico {

    @Autowired
    private EnderecoRepository EnderecoRepository;

    public List<Endereco> listAll() {
        return EnderecoRepository.findAll();
    }

}
