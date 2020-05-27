package com.darus.apispringboot.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.darus.apispringboot.models.entity.Usuario;

@Service
public class UploadFileServiceImp implements IUploadFileService {

	
	private final static String DIRECTORIO_UPLOAD = "uploads";
	
	@Override
	public Resource obtenerFoto(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		if (!recurso.exists() && !recurso.isOpen()) {
			recurso = null;
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString() + "_"
				+ archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean aliminar(String nombreFoto) {
		if (nombreFoto== null || nombreFoto.length() > 0) {
			Path rutaFotoAnterior = getPath(nombreFoto);
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto);
	}

}
