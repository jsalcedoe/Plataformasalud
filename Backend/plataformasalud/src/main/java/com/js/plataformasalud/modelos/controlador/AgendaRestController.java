package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Agenda;
import com.js.plataformasalud.modelos.servicios.IAgendaServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")


public class AgendaRestController {
	
	private IAgendaServiceImpl agmedserv;

	public AgendaRestController(IAgendaServiceImpl agmedserv) {
		super();
		this.agmedserv = agmedserv;
	}
	
	@GetMapping("/agenda")
	public List<Agenda> index(){
		return agmedserv.findAll();
	}
	
	@GetMapping("/agenda/{idagmed}")
	public Agenda mostrar(@PathVariable Long idagmed) {
		return agmedserv.findById(idagmed);
	}
	
	@PostMapping("/agenda")
	public Agenda crear (@RequestBody Agenda agmed) {
		return agmedserv.save(agmed);
	}
	
	@PutMapping("/agenda/{idagmed}")
	public Agenda actualiza (@RequestBody Agenda agmed, @PathVariable Long idagmed) {
		Agenda AgendaActual = agmedserv.findById(idagmed);
		
		AgendaActual.setConsagmed(agmed.getConsagmed());
		AgendaActual.setCupscita(agmed.getCupscita());
		AgendaActual.setFechaagmed(agmed.getFechaagmed());
		AgendaActual.setHorafinagmed(agmed.getHorafinagmed());
		AgendaActual.setHorainiagmed(agmed.getHorainiagmed());
		AgendaActual.setIntervtempagmed(agmed.getIntervtempagmed());
		AgendaActual.setMediagmed(agmed.getMediagmed());
		AgendaActual.setTotalcitasagmed(agmed.getTotalcitasagmed());
		
		return agmedserv.save(AgendaActual);
	}

	
	

}