package app.revision.repository.EntidadesRepository.UsuarioRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.revision.entidades.usuario.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Query Method
    List<Cliente> findByNomeContaining(String nome);

    // Query Method
    Cliente findByNome(String nome);

    // Query Override
    @Query("SELECT u FROM Cliente u WHERE u.nome LIKE %:nome%")
    List<Cliente> filtrarPorNome(@Param("nome") String nome);

    // Query Override
    @Query("SELECT u FROM Cliente u WHERE u.id = id")
    List<Cliente> filtrarPorId(@Param("cliente_id") String id);

}
