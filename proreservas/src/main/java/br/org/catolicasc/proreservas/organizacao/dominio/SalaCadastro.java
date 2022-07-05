package br.org.catolicasc.proreservas.organizacao.dominio;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SalaCadastro {
			
		@Id
		@GeneratedValue
		private Long id;
		
		private String bloco;
		private String descricaoSala;
		private String codigoSala;
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
			SalaCadastro other = (SalaCadastro) obj;
			return Objects.equals(id, other.id);
		}
		
	}
	
