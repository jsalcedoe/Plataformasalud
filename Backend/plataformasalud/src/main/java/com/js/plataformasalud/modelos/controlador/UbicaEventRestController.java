package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.js.plataformasalud.modelos.entidades.UbicacionEvent;
import com.js.plataformasalud.modelos.servicios.IUbicaEventServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class UbicaEventRestController {
	
	@Autowired
	private IUbicaEventServiceImpl ubicaeventservice;
	
	@GetMapping("/ubicaeventos")
	public List<UbicacionEvent> index(){
		return ubicaeventservice.findAll();
	}
	
	@GetMapping("/ubicaeventos/{idubieventpac}")
	@ResponseStatus(code = HttpStatus.OK)
	public UbicacionEvent mostrar(@PathVariable Long idubieventpac) {
		return ubicaeventservice.findById(idubieventpac);
	}
	
	@PostMapping("/ubicaeventos")
	@ResponseStatus(HttpStatus.CREATED)
	public UbicacionEvent crear (@RequestBody UbicacionEvent ubicaevent) {
		return ubicaeventservice.save(ubicaevent);
	}
	
	@PutMapping("/ubicaeventos/{idubieventpac}")
	public UbicacionEvent editar(@RequestBody UbicacionEvent ubicaevent, @PathVariable Long idubieventpac) {
		
		UbicacionEvent ubicaeventActual = ubicaeventservice.findById(idubieventpac);
		
		ubicaeventActual.setFechafinubieventpac(ubicaevent.getFechafinubieventpac());
		ubicaeventActual.setFechainiubieventpac(ubicaevent.getFechainiubieventpac());
		ubicaeventActual.setEvento(ubicaevent.getEvento());
		ubicaeventActual.setUbicacion(ubicaevent.getUbicacion());
		ubicaeventActual.setEstado(ubicaevent.getEstado());
		return null;
	}

}
