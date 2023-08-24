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

import com.js.plataformasalud.modelos.entidades.ProcedimientoQuirurgico;
import com.js.plataformasalud.modelos.servicios.IProcedimientoQuirurgicoServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ProcedimientoQuirurgicoRestController {
	
	
	private IProcedimientoQuirurgicoServiceImpl qxservice;
	
	@GetMapping("/procedimientosqx")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcedimientoQuirurgico> index(){
		return (List<ProcedimientoQuirurgico>) qxservice.findAll();
	}
	
	@GetMapping("/procedimientosqx/{idqx}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProcedimientoQuirurgico Mostrar(@PathVariable Long idqx) {
		return qxservice.findById(idqx);
	}
	
	@PostMapping("/procedimientosqx")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProcedimientoQuirurgico crear(@RequestBody ProcedimientoQuirurgico qx) {
		return qxservice.save(qx);
	}
	
	@PutMapping("/procedimientosqx/{idqx}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProcedimientoQuirurgico actualizar (@RequestBody ProcedimientoQuirurgico qx, @PathVariable Long idqx) {
		ProcedimientoQuirurgico qxact = qxservice.findById(idqx);
		
		qxact.setAnestesia(qx.getAnestesia());
		qxact.setComplicqx(qx.getComplicqx());
		qxact.setConducqx(qx.getComplicqx());
		qxact.setDescqx(qx.getDescqx());
		qxact.setEventqx(qx.getEventqx());
		qxact.setFechaprocqx(qx.getFechaprocqx());
		qxact.setHallaqx(qx.getHallaqx());
		qxact.setHorafinprocqx(qx.getHorafinprocqx());
		qxact.setHorainicioprocqx(qx.getHorainicioprocqx());
		qxact.setMuespato(qx.getMuespato());
		qxact.setQx(qx.getQx());
		qxact.setTipoherida(qx.getTipoherida());
		
		return qxservice.save(qxact);
	}

}
