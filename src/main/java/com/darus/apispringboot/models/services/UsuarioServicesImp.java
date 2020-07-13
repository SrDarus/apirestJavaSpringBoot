package com.darus.apispringboot.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darus.apispringboot.models.dao.IFacturaDao;
import com.darus.apispringboot.models.dao.IProductoDao;
import com.darus.apispringboot.models.dao.IUsuarioDao;
import com.darus.apispringboot.models.entity.Factura;
import com.darus.apispringboot.models.entity.Producto;
import com.darus.apispringboot.models.entity.Usuario;

@Service
public class UsuarioServicesImp implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IFacturaDao facturaDao;
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
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
	
	/*
	 * FACTURA
	 * */
	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Factura> findAllFactura() {
		return (List<Factura>) facturaDao.findAll();
	}
	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		// TODO Auto-generated method stub
		return facturaDao.save(factura);
	}
	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		// TODO Auto-generated method stub
		facturaDao.deleteById(id);
	}
	
	/*
	 * PRODUCTOS
	 * */
	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAllProductos() {
		return (List<Producto>) productoDao.findAll();
	}

}
