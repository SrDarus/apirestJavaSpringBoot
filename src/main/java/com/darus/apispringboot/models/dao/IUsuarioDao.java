package com.darus.apispringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{
	
}
