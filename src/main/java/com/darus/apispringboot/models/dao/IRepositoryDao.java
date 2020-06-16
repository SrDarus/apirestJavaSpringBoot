package com.darus.apispringboot.models.dao;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.darus.apispringboot.models.entity.Usuario;

public interface IRepositoryDao extends CrudRepository<Usuario, String>{

	public Usuario findByEmail(String email);
	
//	@Query("select u from Usuario u where u.email =?1")
//	public Usuario login(String email);
}
