package com.darus.apispringboot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darus.apispringboot.models.entity.Factura;
import com.darus.apispringboot.models.entity.Usuario;
import com.darus.apispringboot.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*"})
//@CrossOrigin(origins = {"https://app-angular-material-820c0.web.app", "*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/facturas")
	public ResponseEntity<?> findAll() {
		System.out.println("++++++++++++++++++++++++++++");
		List<Factura> facturas = null;
		Map<String, Object> response = new HashMap<>();
		try {
			facturas = usuarioService.findAllFactura();
			response.put("status", 200);
			response.put("result", facturas);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
