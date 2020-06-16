package com.darus.apispringboot.models.services;

import com.darus.apispringboot.models.entity.Usuario;

public interface IRepositoryService {

	public Usuario findByEmail(String email);
}
