package com.darus.apispringboot.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.darus.apispringboot.models.dao.IRepositoryDao;
import com.darus.apispringboot.models.entity.Usuario;

@Service
public class RepositoryService implements UserDetailsService, IRepositoryService {

	private Logger logger = LoggerFactory.getLogger(RepositoryService.class);
	
	@Autowired
	private IRepositoryDao repositoryDao;

	
	@Override
	@Transactional(readOnly=true)	
	@PostMapping("login/token")
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		System.out.println("email: "+email);
		Usuario usuario = repositoryDao.findByEmail(email);
		if(usuario == null) {
			logger.error("********* usuario no existe");
			throw new UsernameNotFoundException("No existe el usuario "+ email);
		} 
		System.out.println("usuario email: "+usuario.getEmail());
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getEmail(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
 
	@Override
	@Transactional(readOnly=true)
	public Usuario findByEmail(String email) {
		Usuario usuario = repositoryDao.findByEmail(email);
		return usuario;
	}

}
