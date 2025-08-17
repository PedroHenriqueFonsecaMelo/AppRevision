package app.revision.dao.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import app.revision.dao.entidades.produto.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
