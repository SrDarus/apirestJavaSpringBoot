package com.darus.apispringboot.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	@Id
	@Column(nullable=false, unique=true)
//	@NotEmpty
	@Email
	private  String email;
	@Column(unique = true, length = 20)
	private  String rut;
	@Column(nullable=false)
	@NotEmpty
	private  String nombre;
	private  String apellido;
	@Column(length = 60)
	private String password;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private  Date fechaNacimiento;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private  Date fechaCreacion;
	private String foto;
	@Column(nullable=false)
	private Boolean enabled;
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(
		name="usuarios_roles", 
		joinColumns= @JoinColumn(name="email"),
		inverseJoinColumns= @JoinColumn(name="id_role"),
		uniqueConstraints= {	@UniqueConstraint(columnNames= {"email", " id_role"})}
	)
	private List<Role> roles;
	
	/*@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}*/

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
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
