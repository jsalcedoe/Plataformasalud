package com.js.plataformasalud.modelos.servicios;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.js.plataformasalud.modelos.dao.IDiagnosticoEvolucionDao;
import com.js.plataformasalud.modelos.dao.IEvolucionEventoDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoEvolucion;
import com.js.plataformasalud.modelos.entidades.EvolucionEvento;
import com.js.plataformasalud.modelos.entidades.EvolucionEventoDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IEvolucionEventoDTOServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(IEvolucionEventoDTOServiceImpl.class);
    private final IEvolucionEventoDao evoeventdao;
    private final IDiagnosticoEvolucionDao dxevodao;
    
    @Transactional
    public EvolucionEvento save(EvolucionEventoDTO dto) {
        // Verificar si el DTO o sus listas son nulos
        if (dto == null) {
            logger.error("El objeto EvolucionEventoDTO es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.debug("DTO recibido: {}", dto);

        // Guardar la Evolucion Clinica
        
        EvolucionEvento evo = dto.getEvoeventdto();
        if (evo == null) {
            logger.error("La Evolucion Clinica en el DTO es nula");
            throw new IllegalArgumentException("La Evolucion no puede ser nula");
        }
        logger.debug("Evolucion Clinica a guardar: {}", evo);

        EvolucionEvento saveevo = evoeventdao.save(evo);
        logger.debug("Evolucion Clinica guardada: {}", saveevo);
        
     // Manejo de diagnósticos
        if (dto.getDxevendto() != null) {
            // 1. Obtener diagnósticos existentes
            List<DiagnosticoEvolucion> diagnosticosExistentes = 
            		dxevodao.findByEvoFkId(saveevo.getIdevol());
            
            // 2. Eliminar diagnósticos que no están en la nueva lista
            List<Long> idsNuevosDiagnosticos = dto.getDxevendto().stream()
                .filter(dx -> dx.getIddxevopac() != null)
                .map(DiagnosticoEvolucion::getIddxevopac)
                .collect(Collectors.toList());
            
            diagnosticosExistentes.stream()
                .filter(dx -> !idsNuevosDiagnosticos.contains(dx.getIddxevopac()))
                .forEach(dxevodao::delete);
            
            // 3. Guardar/Actualizar nuevos diagnósticos
            for (DiagnosticoEvolucion dxevo : dto.getDxevendto()) {
                if (dxevo == null) continue;
               
                dxevo.setEvopac_fk(saveevo);
                
                // Si tiene ID, es una actualización; si no, es nuevo
                if (dxevo.getIddxevopac() != null) {
                    // Verificar que existe antes de actualizar
                	dxevodao.findById(dxevo.getIddxevopac()).ifPresent(existing -> {
                        // Actualizar campos necesarios
                		
                		existing.setDxevopac_fk(dxevo.getDxevopac_fk());
                		existing.setTypdxevopac_fk(dxevo.getTypdxevopac_fk());
                		existing.setEstdxevopac(dxevo.getEstdxevopac());
                		dxevodao.save(existing);
                    });
                } else {
                	dxevodao.save(dxevo);
                }
            }
        }
        return saveevo;
    }
    @Transactional(readOnly = true)
    public EvolucionEventoDTO findByEvoFkId(Long idevol) {
        EvolucionEvento evo = evoeventdao.findById(idevol)
            .orElseThrow(() -> new RuntimeException("Evolucion Clinica no encontrada con id: " + idevol));
        
        EvolucionEventoDTO dto = new EvolucionEventoDTO();        
        dto.setEvoeventdto(evo);
        dto.setDxevendto(dxevodao.findByEvoFkId(idevol));
        
        return dto;
    }
    
}


