package app.revision.dto.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.repository.usuario.ClienteRepository;

@Service
public class ClienteServico {
    @Autowired
    private ClienteRepository ClienteRepository;

    public List<Cliente> listAll() {
        return ClienteRepository.findAll();
    }

}
