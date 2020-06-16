package com.darus.apispringboot.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.darus.apispringboot.models.entity.Usuario;
import com.darus.apispringboot.models.services.IRepositoryService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private IRepositoryService repositoryService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		System.out.println("AUTHENTICATION_NAME: "+authentication.getName());
		Map<String, Object> info = new HashMap<>();
		Usuario usuario = repositoryService.findByEmail(authentication.getName());

		info.put("email", usuario.getEmail());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("fechaNacimiento", usuario.getFechaNacimiento());
		info.put("foto", usuario.getFoto());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
	
}
