package br.org.catolicasc.proreservas.organizacao.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaCadastroRepositorio extends JpaRepository<SalaCadastro, Long> {
	
	SalaCadastroRepositorio findById(int idSala);
	
	@Query("select s from Sala s where s.bloco = ?1")
	List<SalaCadastro> findByBloco(String bloco);
	
}
