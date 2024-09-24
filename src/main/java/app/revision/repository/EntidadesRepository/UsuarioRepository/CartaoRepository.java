package app.revision.repository.EntidadesRepository.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.revision.entidades.usuario.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}
