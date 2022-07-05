package br.org.catolicasc.proreservas.organizacao.controle;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.org.catolicasc.proreservas.organizacao.dominio.Sala;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaRepositorio;

@Controller
public class SalaControle {
	
	private SalaRepositorio salaRepo;
	
	public SalaControle(SalaRepositorio salaRepo) {
		this.salaRepo = salaRepo;
	}
	
	@GetMapping("/login")
	public String login(Principal principal) {
		if (principal != null) {
			return "redirect:/home";
		}
		return "/login";
	}
	
	@GetMapping("/home")
	public String home(Principal principal) {
		if (principal != null) {
			return "/home";
		}
		return "/login";
	}
	
	@GetMapping("/reservas")
	public String salasDisponiveis(Model model) {
		
		model.addAttribute("listaSalas", salaRepo.findBysituacaoSala(0));
		return "reservas/index";
		
	}
		
	@GetMapping("/reservas/minhasReservas")
	public String minhasReservas(Model model, Principal principal) {
		
		model.addAttribute("listaSalas", salaRepo.findByMinhasReservas(1, principal.getName()));
		return "reservas/minhasReservas";
		
	}
	
	@GetMapping("/reservas/reservar/{id}")
	public String reservarSala(@PathVariable("id") long id, Model model, Principal principal) {
		
		Optional<Sala> salaOpt = salaRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
			
		System.out.println("seu nome é:");
		System.out.println(principal.getName());
		
		Sala s = salaOpt.get();
		
		s.setSituacaoSala(1);
		s.setTitularReserva(principal.getName());
		
		salaRepo.save(s);
		
		return "redirect:/reservas/minhasReservas";
		
	}
	
	@GetMapping("/reservas/reservarMes/{id}")
	public String reservarMes(@PathVariable("id") long id, Model model, Principal principal) {
		
		Optional<Sala> salaOpt = salaRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
					
		Sala s = salaOpt.get();
		
		List<Sala> sala = salaRepo.findByCodSala(0, s.getCodigoSala());
				
		for(int i=0;i<31;i++) {
			Sala r = sala.get(i);
			
			if(r.getSituacaoSala()==0) {
				r.setSituacaoSala(1);
				r.setTitularReserva(principal.getName());
				
				salaRepo.save(r);
			}
			
		}
		
		return "redirect:/reservas/minhasReservas";
		
	}
	
	
	@GetMapping("/reservas/cancelarReserva/{id}")
	public String cancelarReserva(@PathVariable("id") long id, Model model, Principal principal) {
		
		Optional<Sala> salaOpt = salaRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
		
		Sala s = salaOpt.get();
		
		s.setSituacaoSala(0);
		s.setTitularReserva("");
		
		salaRepo.save(s);
		
		return "redirect:/reservas/minhasReservas";
		
	}
	
	@GetMapping("/reservas/cancelarReservaMes/{id}")
	public String cancelarReservaMes(@PathVariable("id") long id, Model model, Principal principal) {
		
		Optional<Sala> salaOpt = salaRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
					
		Sala s = salaOpt.get();
		
		List<Sala> sala = salaRepo.findByCodSala(1, s.getCodigoSala());
				
		for(int i=0;i<31;i++) {
			Sala r = sala.get(i);
			
			if(r.getSituacaoSala()==1) {
				r.setSituacaoSala(0);
				r.setTitularReserva("");
				
				salaRepo.save(r);
			}
			
		}
		
		return "redirect:/reservas/minhasReservas";
		
	}
	
	@GetMapping("/reservas/reservasFiltro")
	public String reservasFiltro(Model model, Sala sala) {
		
		if ((sala.getData()!="")&&(sala.getBloco()=="")&&(sala.getCodigoSala()=="")){
		model.addAttribute("listaSalas", salaRepo.findBySalasFiltro(0, sala.getData()));
		}
		
		if ((sala.getBloco()!="")&&(sala.getData()=="")&&(sala.getCodigoSala()=="")){
			model.addAttribute("listaSalas", salaRepo.findByBloco(0, sala.getBloco()));
			}
		
		if ((sala.getCodigoSala()!="")&&(sala.getData()=="")&&(sala.getBloco()=="")){
			model.addAttribute("listaSalas", salaRepo.findByCodSala(0, sala.getCodigoSala()));
			}
		
		if ((sala.getBloco()=="")&&(sala.getData()=="")&&(sala.getCodigoSala()=="")){
		model.addAttribute("listaSalas", salaRepo.findBysituacaoSala(0));
		}
		
		return "reservas/reservasFiltro";
		
	}
	
}
