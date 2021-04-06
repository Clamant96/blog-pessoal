package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	// Busca todos os titulos com base nos caracters informados (Containing = LIKE do SLQ) = findAllByTituloContainingIgnoreCase
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	
	public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);
	
	//public List<Postagem> findByDataStartDateAfter(String Date);

}
