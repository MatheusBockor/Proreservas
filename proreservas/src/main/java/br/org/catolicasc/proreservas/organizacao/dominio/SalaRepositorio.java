package br.org.catolicasc.proreservas.organizacao.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepositorio extends JpaRepository<Sala, Long> {
	
	List<Sala> findBysituacaoSala(int situacao);
	
	SalaRepositorio findById(int idSala);
	
	@Query("select s from Sala s where s.situacaoSala = ?1 AND s.titularReserva = ?2")
	List<Sala> findByMinhasReservas(int situacao, String titularReserva);
	
	@Query("select s from Sala s where s.situacaoSala = ?1 AND s.data = ?2")
	List<Sala> findBySalasFiltro(int situacao, String data);
	
	@Query("select s from Sala s where s.situacaoSala = ?1 AND s.bloco = ?2")
	List<Sala> findByBloco(int situacao, String bloco);
	
	@Query("select s from Sala s where s.situacaoSala = ?1 AND s.codigoSala = ?2")
	List<Sala> findByCodSala(int situacao, String codigoSala);
	
	@Query("select s from Sala s where s.situacaoSala = ?1 AND s.codigoSala = ?2 AND s.bloco = ?3 AND s.mes = ?4")
	List<Sala> findByMes(int situacao, String codigoSala, String bloco, int mes);
	
	
}
