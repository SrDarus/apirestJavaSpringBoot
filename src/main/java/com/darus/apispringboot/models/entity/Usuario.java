package com.darus.apispringboot.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
	@Id
	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email
	public String email;
	public String rut;
	@Column(nullable=false)
	public int perfil;
	@Column(nullable=false)
	@NotEmpty
	public String nombre;
	public String apellido;
	@Column(nullable=false)
	@Size(min=4, max=20)
	public String password;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	public Date fechaNacimiento;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	public Date fechaCreacion;
	public String foto;
	
	/*@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}*/

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}


	public int getPerfil() {
		return perfil;
	}


	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
