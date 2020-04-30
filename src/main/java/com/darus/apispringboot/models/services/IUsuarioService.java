package com.darus.apispringboot.models.services;

import java.util.List;

import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
}
