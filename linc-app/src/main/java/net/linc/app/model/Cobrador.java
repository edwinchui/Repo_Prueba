package net.linc.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "correo_electronico"))
public class Cobrador {

	@Id
	@SequenceGenerator(name = "COBRADOR_GENERADOR_ID", sequenceName = "cobrador_sec", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COBRADOR_GENERADOR_ID")
	private long id;

	@OneToOne(cascade = CascadeType.PERSIST, targetEntity = Cuenta.class)
	@JoinColumn(name = "id_cuenta")
	private Cuenta datosCuenta;

	@NotNull
	@Column(length = 50)
	private String nombre;

	@NotNull
	@Column(length = 50)
	private String apellidos;

	@NotNull
	@Column(name = "documento_identidad", length = 8)
	private Integer documentoIdentidad;

	@NotNull
	@Column(length = 2)
	private String expedido;

	@NotNull
	@Size(min = 10, max = 150)
	private String direccion;

	@NotNull
	@Column(name = "telefono_celular", length = 8)
	private String telefonoCelular;

	@NotNull
	@Column(name = "correo_electronico", length = 50)
	private String correoElectronico;

	@NotNull
	@Column(length = 2)
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cuenta getDatosCuenta() {
		return datosCuenta;
	}

	public void setDatosCuenta(Cuenta datosCuenta) {
		this.datosCuenta = datosCuenta;
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

	public Integer getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(Integer documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getExpedido() {
		return expedido;
	}

	public void setExpedido(String expedido) {
		this.expedido = expedido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
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

}
