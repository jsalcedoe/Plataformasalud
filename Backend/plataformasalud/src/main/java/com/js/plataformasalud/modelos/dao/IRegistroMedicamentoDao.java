package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;

public interface IRegistroMedicamentoDao extends JpaRepository<RegistroMedicamento, Long> {

}
