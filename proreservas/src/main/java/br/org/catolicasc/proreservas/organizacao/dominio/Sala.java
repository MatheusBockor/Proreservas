package br.org.catolicasc.proreservas.organizacao.dominio;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sala {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String bloco;
	private String descricaoSala;
	private String codigoSala;
	private int situacaoSala;
	private String titularReserva;
	private String data;
	private int mes;
	
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTitularReserva() {
		return titularReserva;
	}
	public void setTitularReserva(String titularReserva) {
		this.titularReserva = titularReserva;
	}
	public int getSituacaoSala() {
		return situacaoSala;
	}
	public void setSituacaoSala(int situacaoSala) {
		this.situacaoSala = situacaoSala;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getDescricaoSala() {
		return descricaoSala;
	}
	public void setDescricaoSala(String descricaoSala) {
		this.descricaoSala = descricaoSala;
	}
	public String getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return Objects.equals(id, other.id);
	}

}
