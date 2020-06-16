package com.darus.apispringboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.darus.apispringboot.models.entity.Perfil;
import com.darus.apispringboot.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, String>{
	
	@Query("from Perfil")
	public List<Perfil> findAllPerfiles();
	
	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.email =?1")
	public Usuario login(String email);
	
}