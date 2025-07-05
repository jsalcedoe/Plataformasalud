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
import com.js.plataformasalud.modelos.entidades.HistoriaClinica;
import com.js.plataformasalud.modelos.entidades.HistoriaClinicaDTO;
import com.js.plataformasalud.modelos.servicios.IHistoriaClinicaDTOServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class HistoriaClinicaDTORestController {
	private final IHistoriaClinicaDTOServiceImpl hcpacserv;
	
	// POST: Guardar historia clínica con diagnósticos
    @PostMapping("/historiaclinicacompleta")
    public ResponseEntity<?> saveHistoriaClinicaDTO(@Valid @RequestBody HistoriaClinicaDTO dto, BindingResult result) 
    {
        Map<String, Object> response = new HashMap<>();
        HistoriaClinica savedHcpac;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            savedHcpac = hcpacserv.save(dto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            response.put("mensaje", "Error inesperado al registrar la historia clínica");
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "¡La historia clínica ha sido creada con éxito!");
        response.put("historiaClinica", savedHcpac);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET: Obtener historia clínica + diagnósticos por ID
    @GetMapping("/historiaclinicacompleta/{idhcpac}")
    public ResponseEntity<?> getHistoriaClinicaDTO(@PathVariable Long idhcpac) throws NoSuchElementException {
        Map<String, Object> response = new HashMap<>();
        HistoriaClinicaDTO dto;

        try {
            dto = hcpacserv.findByIdhcpac(idhcpac);
        } catch (RuntimeException e) {
            response.put("mensaje", "No se encontró una historia clínica con el ID: " + idhcpac);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    
    @PutMapping("/historiaclinicacompleta/{idhcpac}")
    public ResponseEntity<?> updateHistoriaClinicaDTO(@PathVariable Long idhcpac,
                                                    @Valid @RequestBody HistoriaClinicaDTO dto,
                                                    BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Verificar existencia usando el servicio
            HistoriaClinicaDTO existingDto = hcpacserv.findByIdhcpac(idhcpac);
            if (existingDto == null || existingDto.getHcdto() == null) {
                response.put("mensaje", "No existe la historia clínica con ID: " + idhcpac);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Asignar ID
            dto.getHcdto().setIdhcpac(idhcpac);
            
            // Asignar IDs a diagnósticos que pertenecen a esta HC
            if (dto.getDxhcdto() != null) {
                dto.getDxhcdto().forEach(dx -> {
                    if (dx.getHcpac_fk() == null || 
                        !dx.getHcpac_fk().getIdhcpac().equals(idhcpac)) {
                        dx.setHcpac_fk(dto.getHcdto());
                    }
                });
            }

            HistoriaClinica updated = hcpacserv.save(dto);
            response.put("mensaje", "¡La historia clínica fue actualizada con éxito!");
            response.put("historiaClinica", updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            response.put("mensaje", "No se encontró la historia clínica");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
	
	

}
