package com.darus.apispringboot.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
}