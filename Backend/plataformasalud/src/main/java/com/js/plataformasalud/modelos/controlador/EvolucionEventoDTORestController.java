package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.js.plataformasalud.modelos.entidades.EvolucionEvento;
import com.js.plataformasalud.modelos.entidades.EvolucionEventoDTO;
import com.js.plataformasalud.modelos.servicios.IEvolucionEventoDTOServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EvolucionEventoDTORestController {
	private final IEvolucionEventoDTOServiceImpl evodtoserv;
	
	// POST: Guardar la evolucion clínica con diagnósticos
	
    @PostMapping("/evolucionclinicacompleta")
    public ResponseEntity<?> saveEvolucionEventoDTO(@Valid @RequestBody EvolucionEventoDTO dto, BindingResult result) 
    {
        Map<String, Object> response = new HashMap<>();
        EvolucionEvento saveEvo;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            saveEvo = evodtoserv.save(dto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje", "Error inesperado al registrar la evolucion clínica");
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "¡La evolucion clínica ha sido creada con éxito!");
        response.put("EvolucionEvento", saveEvo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    

    // GET: Obtener historia clínica + diagnósticos por ID
    
    @GetMapping("/evolucionclinicacompleta/{idevol}")
    public ResponseEntity<?> getEvolucionEventoDTO(@PathVariable Long idevol) throws NoSuchElementException {
        Map<String, Object> response = new HashMap<>();
        EvolucionEventoDTO dto;

        try {
            dto = evodtoserv.findByEvoFkId(idevol);
        } catch (RuntimeException e) {
            response.put("mensaje", "No se encontró una evolucion clínica con el ID: " + idevol);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    // PUT: Editar evolucion clínica + diagnósticos por ID
    
    @PutMapping("/evolucionclinicacompleta/{idevol}")
    public ResponseEntity<?> updateEvolucionEventoDTO(@PathVariable Long idevol,@Valid @RequestBody EvolucionEventoDTO dto, BindingResult result)
    {
    	Map<String, Object> response = new HashMap<>();
    	if (result.hasErrors())
    	{
    		List<String> errors = result.getFieldErrors()
    		.stream()
    		.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
    		.collect(Collectors.toList());
    		response.put("errors", errors);
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    	try {
    			// Verificar existencia usando el servicio
    			EvolucionEventoDTO existingDto = evodtoserv.findByEvoFkId(idevol);
    			if (existingDto == null || existingDto.getEvoeventdto() == null)
    			{
    				response.put("mensaje", "No existe la evolucion clínica con ID: " + idevol);
    				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    			}

    	// Asignar ID
    	dto.getEvoeventdto().setIdevol(idevol);

    	// Asignar IDs a diagnósticos que pertenecen a esta HC
    	
    	if (dto.getDxevendto() != null) {
            
    		dto.getDxevendto().forEach(dx -> {
                if (dx.getEvopac_fk() == null || !dx.getEvopac_fk().getIdevol().equals(idevol)) {
                    
                	dx.setEvopac_fk(dto.getEvoeventdto());
                }
            });
        }

    	 EvolucionEvento updated = evodtoserv.save(dto);
         response.put("mensaje", "¡La evolucion clínica fue actualizada con éxito!");
         response.put("EvolucionEvento", updated);
         return new ResponseEntity<>(response, HttpStatus.OK);
     } catch (DataAccessException e) {
         response.put("mensaje", "Error al actualizar en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
     } catch (RuntimeException e) {
         response.put("mensaje", "No se encontró la evolucion clínica");
         response.put("error", e.getMessage());
         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
     }
 }
    
}
