package com.darus.apispringboot.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.darus.apispringboot.models.entity.Producto;
import com.darus.apispringboot.models.entity.Usuario;
import com.darus.apispringboot.models.services.IUploadFileService;
import com.darus.apispringboot.models.services.IUsuarioService;

//@CrossOrigin(origins = { "http://localhost:4200", "*"})
@CrossOrigin(origins = {"https://app-angular-material-820c0.web.app", "*"}) 
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IUploadFileService uploadFileService;
	

	// private final Logger log =
	// LoggerFactory.getLogger(UsuarioRestController.class);


	@GetMapping("/usuario/{email}")
	public ResponseEntity<?> show(@PathVariable String email) {
//		System.out.println("email"+ email);
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.findById(email);
			if (usuario == null) {
				System.out.print(usuario);
				response.put("status", 404);
				response.put("result", null);
				response.put("message", "Not Found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("status", 200);
			response.put("result", usuario);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			// response.put("message", "ERROR:
			// ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Secured("ROLE_ADMIN")
	@GetMapping("/usuarios")
	public ResponseEntity<?> findAll() {
		List<Usuario> usuarioList = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioList = usuarioService.findAll();
			response.put("status", 200);
			response.put("result", usuarioList);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/usuarios/page/{page}")
	public ResponseEntity<?> findAll(@PathVariable Integer page) {
		Page<Usuario> usuarioList = null;
		Map<String, Object> response = new HashMap<>();
		try {
			PageRequest pageable = PageRequest.of(page, 10);
			usuarioList = usuarioService.findAll(pageable);
			response.put("status", 200);
			response.put("result", usuarioList);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		Usuario newUsuario = null;
		Usuario buscarUsuario = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			/*
			 * List<String> errors = new ArrayList<>(); for(FieldError err:
			 * result.getFieldErrors()) { errors.add(err.getDefaultMessage()); }
			 */
			List<String> errors = result.getFieldErrors().stream()
					.map(error -> error.getField() + "-" + error.getDefaultMessage()).collect(Collectors.toList());
			response.put("status", 400);
			response.put("result", errors);
			response.put("message", "Bad Request");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		try {
			buscarUsuario = usuarioService.findById(usuario.getEmail());
			if (buscarUsuario == null) {
				usuario.setFechaCreacion(new Date());
				System.out.println("Fecha nacimiento" + usuario.getFechaNacimiento());
				// usuario.setFechaNacimiento(new
				// SimpleDateFormat("yyyy-MM-dd").format(usuario.fechaNacimiento));
				newUsuario = usuarioService.save(usuario);
			} else {
				response.put("status", 409);
				response.put("result", null);
				response.put("message", "Conflict");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			// response.put("message", "ERROR:
			// ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("status", 200);
		response.put("result", newUsuario);
		response.put("message", "OK");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@PutMapping(value="/usuario/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result,
			@PathVariable String email) {
		System.out.println(usuario.getEmail());
		Usuario usuarioDB = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(error -> "Error: " + error.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("status", 400);
			response.put("result", errors);
			response.put("message", "Bad Request");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		try {
			usuarioDB = usuarioService.findById(email); 
			System.out.println("get rut: "+usuarioDB.getRut());
			
			usuarioDB.setRut(usuario.getRut());
			usuarioDB.setNombre(usuario.getNombre());
			usuarioDB.setApellido(usuario.getApellido());
			usuarioDB.setFechaNacimiento(usuario.getFechaNacimiento());
			
			usuario = usuarioService.save(usuarioDB);
			// usuarioDB.setFechaCreacion(usuario.getFechaCreacion());
			response.put("status", 200);
			response.put("result", usuario);
			response.put("message", "OK");
			// response.put("message", "ERROR:
			// ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			// response.put("message", "ERROR:
			// ".concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("usuario/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable String email) {
		Map<String, Object> response = new HashMap<>();
		try {
			Usuario usuario = usuarioService.findById(email);
			String nombreFotoAnterior = usuario.getFoto();
			boolean result = uploadFileService.aliminar(nombreFotoAnterior);
			usuarioService.delete(email);
			response.put("status", 200);
			response.put("result", result);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("status", 500);
			response.put("result", e);
			response.put("message", "Internal Server Error");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("usuario/img/subirFoto")
	public ResponseEntity<?> upload(@RequestParam("imagen") MultipartFile imagen, @RequestParam("email") String email) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(email);
		if (!imagen.isEmpty()) {
//			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
//			log.info(rutaArchivo.toString());
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(imagen);
			} catch (IOException e) {
				response.put("status", 405);
				response.put("result", e.getMessage());
				response.put("message", "Archivo no corresponde");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior = usuario.getFoto();
			uploadFileService.aliminar(nombreFotoAnterior);
			usuario.setFoto(nombreArchivo);
			usuarioService.save(usuario);
			response.put("status", 200);
			response.put("result", usuario);
			response.put("message", "OK");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			response.put("status", 405);
			response.put("result", null);
			response.put("message", "Archivo dañado o formato no corresponde");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("usuario/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		System.out.println("nombre foto: "+nombreFoto);
		Resource recurso = null;
		try {
			recurso = uploadFileService.obtenerFoto(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, httpHeader, HttpStatus.OK);
	}
	
	@GetMapping("usuario/img/carousel/{nombreFoto:.+}")
	public ResponseEntity<Resource> carousel(@PathVariable String nombreFoto) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.obtenerFotoCarousel("carousel/"+nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, httpHeader, HttpStatus.OK);
	}
	
	@GetMapping("/productos")
	public ResponseEntity<?> findAllProductos() {
		List<Producto> productos= null;
		Map<String, Object> response = new HashMap<>();
		try {
			productos = usuarioService.findAllProductos();
			response.put("status", 200);
			response.put("result", productos);
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
