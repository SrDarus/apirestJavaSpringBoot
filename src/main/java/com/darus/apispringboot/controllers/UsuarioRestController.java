package com.darus.apispringboot.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.darus.apispringboot.models.entity.Usuario;
import com.darus.apispringboot.models.services.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	/*
	@GetMapping("/usuario/obtenerUsuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}*/
	
	@GetMapping("/usuario/obtenerUsuario/{email}")
	public ResponseEntity<?> show(@PathVariable String email) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario= usuarioService.findById(email);
			if(usuario == null) {
				response.put("status", 404);
				response.put("result", null);
				response.put("message", "Not Found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("status", 200);
			response.put("result", usuario);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		catch(DataAccessException e) { 
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/usuario/obtenerUsuarios")
	public ResponseEntity<?> findAll(){
		List<Usuario> usuarioList = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioList = usuarioService.findAll();
			response.put("status", 200);
			response.put("result", usuarioList);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);			
		}
		catch(DataAccessException e) { 
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/usuario/obtenerUsuarios/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable Integer page){
		Page<Usuario> usuarioList = null;
		Map<String, Object> response = new HashMap<>();
		try {
			PageRequest pageable = PageRequest.of(page, 10);
			usuarioList = usuarioService.findAll(pageable);
			response.put("status", 200);
			response.put("result", usuarioList);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);			
		}
		catch(DataAccessException e) { 
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/usuario/registrarUsuario")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Usuario newUsuario = null;
		Usuario buscarUsuario = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			/*List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				errors.add(err.getDefaultMessage());
			}*/
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> error.getField()+"-"+error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("status", 400);
			response.put("result", errors);
			response.put("message", "Bad Request");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		try	{
			buscarUsuario = usuarioService.findById(usuario.email);
			if(buscarUsuario == null) {
				usuario.setFechaCreacion(new Date());

				System.out.println("Fecha nacimiento"+ usuario.fechaNacimiento);
				//usuario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").format(usuario.fechaNacimiento));
				newUsuario = usuarioService.save(usuario);	
			}else {
				response.put("status", 409);
				response.put("result", null);
				response.put("message", "Conflict");		
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}
		catch(DataAccessException e) {
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("status", 200);
		response.put("result", newUsuario);
		response.put("message", "OK");		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/usuario/actualizarUsuario/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable String email) {
		Usuario usuarioDB = null;
		Map<String, Object> response = new HashMap<>();	
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> "Error: "+ error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("status", 400);
			response.put("result", errors);
			response.put("message", "Bad Request");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		try {
			usuarioDB = usuarioService.findById(email);
			usuarioDB.setEmail(usuario.getEmail());
			usuarioDB.setApellido(usuario.getApellido());
			usuarioDB.setFechaNacimiento(usuario.getFechaNacimiento());
			usuarioDB.setNombre(usuario.getNombre());
			usuario =  usuarioService.save(usuarioDB);
			//usuarioDB.setFechaCreacion(usuario.getFechaCreacion());
			//usuarioDB.setPerfil(usuario.getPerfil());
			response.put("status", 200);
			response.put("result", usuario);
			response.put("message", "OK");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		catch(DataAccessException e) {
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("usuario/eliminarUsuario/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable String email) {
		Map<String, Object> response = new HashMap<>();
		try {

			Usuario usuario = usuarioService.findById(email);
			String nombreFotoAnterior = usuario.getFoto();
			if(nombreFotoAnterior  == null || nombreFotoAnterior .length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			usuarioService.delete(email);	
			response.put("status", 200);
			response.put("result", null);
			response.put("message", "OK");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		}
		catch(DataAccessException e) {
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			//response.put("message", "ERROR: ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("usuario/subirFoto")
	public ResponseEntity<?> upload(@RequestParam("imagen") MultipartFile imagen, @RequestParam("email") String email){
		Map<String, Object> response = new HashMap<>();
		try {
			Usuario usuario = usuarioService.findById(email);
			if(!imagen.isEmpty()) {
				String nombreArchivo = UUID.randomUUID().toString() + "_" +imagen.getOriginalFilename().replace(" ", "");
				Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
				try {
					Files.copy(imagen.getInputStream(), rutaArchivo);
					String nombreFotoAnterior = usuario.getFoto();
					if(nombreFotoAnterior  != null && nombreFotoAnterior.length() > 0) {
						System.out.print("****************************************");
						Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
						System.out.print(rutaFotoAnterior);
						File archivoFotoAnterior = rutaFotoAnterior.toFile();
						if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
							archivoFotoAnterior.delete();
						}
					}
					
					usuario.setFoto(nombreArchivo);	
					System.out.print("****************************************");
					System.out.print(usuario.getFoto());
					
					usuarioService.save(usuario);
					response.put("status", 200);
					response.put("result", usuario);
					response.put("message", "OK");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					
				}catch(IOException e) {
					response.put("status", 405);
					response.put("result", e.getMessage());
					response.put("message", "Archivo no corresponde");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {
				response.put("status", 405);
				response.put("result", null);
				response.put("message", "Archivo no corresponde");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		catch(DataAccessException e) {
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("usuario/img/{nombreFoto:.+}")
	public ResponseEntity<?> verFoto(@PathVariable String nombreFoto){
		Map<String, Object> response = new HashMap<>();
		try {
			Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto);
			Resource recurso = null;			
			try {
				recurso = new UrlResource(rutaArchivo.toUri());				
			} 
			catch(MalformedURLException e) {
				response.put("status", 500);
				response.put("result", null);
				response.put("message", "Internal Server Error");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if(!recurso.exists() && !recurso.isOpen()) {
				response.put("status", 404);
				response.put("result", null);
				response.put("message", "Not Found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			HttpHeaders httpHeaders = new HttpHeaders(); 
			httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename());
			response.put("status", 200);
			response.put("result", null);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}	

		catch(DataAccessException e) {
			response.put("status", 500);
			response.put("result", null);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
