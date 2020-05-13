package com.darus.apispringboot.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darus.apispringboot.models.dao.IUsuarioDao;
import com.darus.apispringboot.models.entity.Usuario;

@Service
public class UsuarioServicesImp implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Usuario findById(String email) {
		return usuarioDao.findById(email).orElse(null);
	}
	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}
	@Override
	@Transactional
	public void delete(String email) {
		usuarioDao.deleteById(email);
	}

}
