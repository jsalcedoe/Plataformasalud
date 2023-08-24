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

import com.js.plataformasalud.modelos.entidades.TipoAnestesia;
import com.js.plataformasalud.modelos.servicios.ITipoAnestesiaServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoAnestesiaRestController {
	
	
	private ITipoAnestesiaServiceImpl tipanestservice;
	
	@GetMapping("/tipoanestesias")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoAnestesia> index(){
		return tipanestservice.findAll();
	}
	
	@GetMapping("/tipoanestesias/{idtipanest}")
	@ResponseStatus(code = HttpStatus.OK)
	public TipoAnestesia mostrar (@PathVariable Long idtipanest ) {
		return tipanestservice.findById(idtipanest);
	}
	
	@PostMapping("/tipoanestesias")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TipoAnestesia crear (@RequestBody TipoAnestesia anest) {
		return tipanestservice.save(anest);
	}
	
	@PutMapping("/tipoanestesias/{idtipanest}")
	@ResponseStatus(code = HttpStatus.OK)
	public TipoAnestesia actualizar (@RequestBody TipoAnestesia anest,@PathVariable Long idtipanest) {
		TipoAnestesia tipanestAct = tipanestservice.findById(idtipanest);
		
		tipanestAct.setDesctipanest(anest.getDesctipanest());
		tipanestAct.setNomtipanest(anest.getNomtipanest());
		
		return tipanestservice.save(tipanestAct);
	}

}
