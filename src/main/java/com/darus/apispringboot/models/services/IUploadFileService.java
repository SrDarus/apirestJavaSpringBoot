package com.darus.apispringboot.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource obtenerFoto(String nombreFoto) throws MalformedURLException;
	public String copiar(MultipartFile archivo) throws IOException;
	public boolean aliminar(String nombreFoto);
	public Path getPath(String nombreFoto);

	public Resource obtenerFotoCarousel(String nombreFoto) throws MalformedURLException;
	public String copiarCarousel(MultipartFile archivo) throws IOException;
	public boolean aliminarCarousel(String nombreFoto);
	public Path getPathCarousel(String nombreFoto);
}