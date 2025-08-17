package app.revision.dao.repository.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.entidades.usuario.Cliente;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    List<Cartao> findByCliente(Cliente cliente);

}
