package com.js.plataformasalud.modelos.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEquipoQxDao;
import com.js.plataformasalud.modelos.dao.IProcedimientoDescripcionQXDao;
import com.js.plataformasalud.modelos.dao.IProcedimientoQuirurgicoDao;
import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgicaDTO;
import com.js.plataformasalud.modelos.entidades.EquipoQx;
import com.js.plataformasalud.modelos.entidades.ProcedimientoDescripcionQX;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IDescripcionQuirurgicaDTOServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(IDescripcionQuirurgicaDTOServiceImpl.class);

    private final IProcedimientoQuirurgicoDao descripcionQuirurgicaDao;
    private final IProcedimientoDescripcionQXDao procedimientoDescripcionQxDao;
    private final IEquipoQxDao equipoQxDao;

    @Transactional
    public DescripcionQuirurgica save(DescripcionQuirurgicaDTO dto) {
        // Verificar si el DTO o sus listas son nulos
        if (dto == null) {
            logger.error("El objeto DescripcionQuirurgicaDTO es nulo");
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        logger.debug("DTO recibido: {}", dto);

        // Guardar la DescripcionQuirurgica
        DescripcionQuirurgica descripcionQuirurgica = dto.getDescripcionQuirurgica();
        if (descripcionQuirurgica == null) {
            logger.error("La DescripcionQuirurgica en el DTO es nula");
            throw new IllegalArgumentException("La DescripcionQuirurgica no puede ser nula");
        }
        logger.debug("DescripcionQuirurgica a guardar: {}", descripcionQuirurgica);

        DescripcionQuirurgica savedDescripcion = descripcionQuirurgicaDao.save(descripcionQuirurgica);
        logger.debug("DescripcionQuirurgica guardada: {}", savedDescripcion);

        // Guardar los procedimientos asociados
        if (dto.getProcedimientos() != null) {
            for (ProcedimientoDescripcionQX procedimiento : dto.getProcedimientos()) {
                if (procedimiento == null) {
                    logger.warn("Procedimiento en la lista es nulo, se omitirá este registro.");
                    continue;
                }
                procedimiento.setDescqx_fk(savedDescripcion);
                logger.debug("Guardando procedimiento: {}", procedimiento);
                procedimientoDescripcionQxDao.save(procedimiento);
                logger.debug("Procedimiento guardado: {}", procedimiento);
            }
        } else {
            logger.warn("La lista de procedimientos en el DTO es nula");
        }

        // Guardar el equipo quirúrgico asociado
        if (dto.getEquipoQx() != null) {
            for (EquipoQx equipo : dto.getEquipoQx()) {
                if (equipo == null) {
                    logger.warn("Equipo quirúrgico en la lista es nulo, se omitirá este registro.");
                    continue;
                }
                equipo.setDesqx_fk(savedDescripcion);
                logger.debug("Guardando miembro del equipo quirúrgico: {}", equipo);
                equipoQxDao.save(equipo);
                logger.debug("Miembro del equipo quirúrgico guardado: {}", equipo);
            }
        } else {
            logger.warn("La lista de equipo quirúrgico en el DTO es nula");
        }

        return savedDescripcion;
    }

}

