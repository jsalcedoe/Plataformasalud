package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.Consentimiento;

public interface IConsentimientoDao extends JpaRepository<Consentimiento, Long> {

}
