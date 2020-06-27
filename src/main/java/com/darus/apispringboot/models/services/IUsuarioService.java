package com.darus.apispringboot.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(String email);
	
	public Usuario save(Usuario usuario);
	
	public void delete(String email);
	
}
