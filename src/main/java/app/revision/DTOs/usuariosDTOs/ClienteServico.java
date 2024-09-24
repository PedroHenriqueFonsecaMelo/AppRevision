package app.revision.DTOs.usuariosDTOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.revision.entidades.usuario.Cliente;
import app.revision.repository.EntidadesRepository.UsuarioRepository.ClienteRepository;

@Service
public class ClienteServico {
    @Autowired
    private ClienteRepository ClienteRepository;

    public List<Cliente> listAll() {
        return ClienteRepository.findAll();
    }

}
