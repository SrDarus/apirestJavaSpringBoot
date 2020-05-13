package com.darus.apispringboot.models.services;

import java.util.List;

import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findById(String email);
	
	public List<Usuario> findAll();
	
	public Usuario save(Usuario usuario);
	
	public void delete(String email);
	
	
}
