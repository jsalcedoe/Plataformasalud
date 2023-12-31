package com.js.plataformasalud.modelos.controlador;

import java.util.List;

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

import com.js.plataformasalud.modelos.entidades.GrupoMedIns;
import com.js.plataformasalud.modelos.servicios.IGrupoMedInsServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class GrupoMedInsRestController {
	
	
	private IGrupoMedInsServiceImpl gmedins;
	
	@GetMapping("/gruposmedins")
	@ResponseStatus(code = HttpStatus.OK)
	public List<GrupoMedIns> index(){
		return gmedins.findAll();
	}
	
	@GetMapping("/gruposmedins/{idgrupmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public GrupoMedIns mostrar(@PathVariable Long idgrupmedins) {
		return gmedins.findById(idgrupmedins);
	}
	
	@PostMapping("/gruposmedins")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GrupoMedIns crear (@RequestBody GrupoMedIns grupomedins) {
		return gmedins.save(grupomedins);
	}
	
	@PutMapping("/gruposmedins/{idgrupmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public GrupoMedIns actualiza (@RequestBody GrupoMedIns grupomedins,@PathVariable Long idgrupmedins) {
		GrupoMedIns medinsActual = gmedins.findById(idgrupmedins);
		
		medinsActual.setNomgrupmedins(grupomedins.getNomgrupmedins());
		
		return gmedins.save(medinsActual);
	}

}
