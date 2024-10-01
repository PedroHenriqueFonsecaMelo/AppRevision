package app.revision.dao.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.usuario.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}
