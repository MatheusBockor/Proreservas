package br.org.catolicasc.proreservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.org.catolicasc.proreservas.organizacao.dominio.Sala;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaCadastro;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaCadastroRepositorio;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaRepositorio;
import br.org.catolicasc.proreservas.seguranca.dominio.Role;
import br.org.catolicasc.proreservas.seguranca.dominio.Usuario;
import br.org.catolicasc.proreservas.seguranca.dominio.UsuarioRepositorio;

@Component
public class PopulacaoInicialBanco implements CommandLineRunner {
	
	@Autowired
	private SalaRepositorio salaRepo;
	
	@Autowired
	private SalaCadastroRepositorio salaCadastroRepo;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@Autowired
	private PasswordEncoder encoder;
		
	@Override
	public void run(String... args) throws Exception{
		
		SalaCadastro s1 = new SalaCadastro();
		s1.setBloco("B");
		s1.setCodigoSala("B102");
		s1.setDescricaoSala("Sala de Informática");
			
		SalaCadastro s2 = new SalaCadastro();
		s2.setBloco("H");
		s2.setCodigoSala("H102");
		s2.setDescricaoSala("Sala de Informática");
		
		SalaCadastro s3 = new SalaCadastro();
		s3.setBloco("D");
		s3.setCodigoSala("D102");
		s3.setDescricaoSala("Sala de Informática");
	
		SalaCadastro s4 = new SalaCadastro();
		s4.setBloco("F");
		s4.setCodigoSala("C102");
		s4.setDescricaoSala("Sala de Informática");
		
		Usuario u1 = new Usuario();
		u1.setUsername("matheus@email.com");
		u1.setPassword(encoder.encode("123456"));
		u1.setRole(Role.ADMIN.getNome());
		
		Usuario u2 = new Usuario();
		u2.setUsername("user@email.com");
		u2.setPassword(encoder.encode("user1"));
		u2.setRole(Role.USER.getNome());
		
		salaCadastroRepo.deleteAll();
		
		salaCadastroRepo.save(s1);
		salaCadastroRepo.save(s2);
		salaCadastroRepo.save(s3);
		salaCadastroRepo.save(s4);
		usuarioRepo.save(u1);
		usuarioRepo.save(u2);
		
		//Gambis abaixo
		
		for (int a = 1;a<5;a++) {
		for (int i = 1;i<32;i++) {
			
			String bloco;
			bloco="B";
			
			Sala sala = new Sala();
			if (a==2) {
				bloco="H";
			}
			if (a==3) {
				bloco="D";
			}
			if (a==4) {
				bloco="F";
			}
			sala.setBloco(bloco);
			sala.setCodigoSala(bloco+"102");
			sala.setDescricaoSala("Sala de Informática");
			sala.setData(i+"/06/2022");
			sala.setMes(6);
			salaRepo.save(sala);
		}}
		
		
	}
	
}
