package net.linc.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Cliente {
	
	@Id
	@SequenceGenerator(name = "CLIENTE_GENERADOR_ID", sequenceName = "cliente_sec", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_GENERADOR_ID")
	private long id;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "cliente")
	@Column(nullable = true)
	private List<Prestamo> prestamos;
	
	@Column(length = 50)
	@NotNull
	private String nombre;
	
	@Column(length = 50)
	@NotNull
	private String apellidos;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	
	@Column(name = "documento_identidad", length = 8)
	@NotNull
	private String documentoIdentidad;
	
	@NotNull
	private String expedido;
	
	@Column(length = 150)
	@Size(min = 10, max = 150)
	@NotNull
	private String direcion;
	
	@NotNull
	private String telefono;
	
	@Column(name = "correo_electronico", length = 50)
	@NotNull
	private String correoElectronico;
	
	@Column(length = 2)
	@NotNull
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getExpedido() {
		return expedido;
	}

	public void setExpedido(String expedido) {
		this.expedido = expedido;
	}

	public String getDirecion() {
		return direcion;
	}

	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}	
}
