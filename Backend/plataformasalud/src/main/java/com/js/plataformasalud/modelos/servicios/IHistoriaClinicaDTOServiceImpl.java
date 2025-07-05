package com.js.plataformasalud.modelos.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDiagnosticoHistoriaClinicaDao;
import com.js.plataformasalud.modelos.dao.IHistoriaClinicaDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoHistoriaClinica;
import com.js.plataformasalud.modelos.entidades.HistoriaClinica;
import com.js.plataformasalud.modelos.entidades.HistoriaClinicaDTO;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IHistoriaClinicaDTOServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(IHistoriaClinicaDTOServiceImpl.class);
	private final IHistoriaClinicaDao hcdao;
	private final IDiagnosticoHistoriaClinicaDao dxhcdao;
	
    @Transactional
    public HistoriaClinica save(HistoriaClinicaDTO dto) {
        // Verificar si el DTO o sus listas son nulos
        if (dto == null) {
            logger.error("El objeto HistoriaClinicaDTO es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.debug("DTO recibido: {}", dto);

        // Guardar la Historia Clinica
        
        HistoriaClinica hc = dto.getHcdto();
        if (hc == null) {
            logger.error("La Historia Clinica en el DTO es nula");
            throw new IllegalArgumentException("La Historia no puede ser nula");
        }
        logger.debug("Historia Clinica a guardar: {}", hc);

        HistoriaClinica savehc = hcdao.save(hc);
        logger.debug("Historia Clinica guardada: {}", savehc);
        
     /* Guardar los diagnosticos asociados
        if (dto.getDxhcdto() != null) {
            for (DiagnosticoHistoriaClinica dxhcpac : dto.getDxhcdto()) {
                if (dxhcpac == null) {
                    logger.warn("Diagnosticos en la lista es nulo, se omitirá este registro.");
                    continue;
                }
                dxhcpac.setHcpac_fk(savehc);
                logger.debug("Guardando Diagnostico: {}", dxhcpac);
                dxhcdao.save(dxhcpac);
                logger.debug("Diagnostico guardado: {}", dxhcpac);
            }
        } else {
            logger.warn("La lista de diagnosticos en el DTO es nula");
        }  */
        
     // Manejo de diagnósticos
        if (dto.getDxhcdto() != null) {
            // 1. Obtener diagnósticos existentes
            List<DiagnosticoHistoriaClinica> diagnosticosExistentes = 
                dxhcdao.findByHcpacFkId(savehc.getIdhcpac());
            
            // 2. Eliminar diagnósticos que no están en la nueva lista
            List<Long> idsNuevosDiagnosticos = dto.getDxhcdto().stream()
                .filter(dx -> dx.getIddxhcpac() != null)
                .map(DiagnosticoHistoriaClinica::getIddxhcpac)
                .collect(Collectors.toList());
            
            diagnosticosExistentes.stream()
                .filter(dx -> !idsNuevosDiagnosticos.contains(dx.getIddxhcpac()))
                .forEach(dxhcdao::delete);
            
            // 3. Guardar/Actualizar nuevos diagnósticos
            for (DiagnosticoHistoriaClinica dxhcpac : dto.getDxhcdto()) {
                if (dxhcpac == null) continue;
                
                dxhcpac.setHcpac_fk(savehc);
                
                // Si tiene ID, es una actualización; si no, es nuevo
                if (dxhcpac.getIddxhcpac() != null) {
                    // Verificar que existe antes de actualizar
                    dxhcdao.findById(dxhcpac.getIddxhcpac()).ifPresent(existing -> {
                        // Actualizar campos necesarios
                        existing.setDxhcpac_fk(dxhcpac.getDxhcpac_fk());
                        existing.setTypdxhcpac_fk(dxhcpac.getTypdxhcpac_fk());
                        existing.setEstdxhcpac(dxhcpac.getEstdxhcpac());
                        dxhcdao.save(existing);
                    });
                } else {
                    dxhcdao.save(dxhcpac);
                }
            }
        }
        return savehc;
    }
    
    @Transactional(readOnly = true)
    public HistoriaClinicaDTO findByIdhcpac(Long idhcpac) {
        HistoriaClinica hcpac = hcdao.findById(idhcpac)
            .orElseThrow(() -> new RuntimeException("Historia Clinica no encontrada con id: " + idhcpac));
        
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setHcdto(hcpac);
        dto.setDxhcdto(dxhcdao.findByHcpacFkId(idhcpac));
        
        return dto;
    }



}
