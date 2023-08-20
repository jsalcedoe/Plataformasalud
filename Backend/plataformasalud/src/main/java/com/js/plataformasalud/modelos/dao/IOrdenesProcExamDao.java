package com.js.plataformasalud.modelos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.plataformasalud.modelos.entidades.OrdenesProcExam;

public interface IOrdenesProcExamDao extends JpaRepository<OrdenesProcExam, Long> {

}
