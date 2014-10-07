package net.linc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "correo_electronico"))
public class Usuario {

	@Id
	private long id;

	@OneToOne
	private Cuenta datosCuenta;

	@NotNull
	private String nombres;

	@NotNull
	private String apellidos;

	@NotNull
	@Column(name = "correo_electronico")
	private String correoElectronico;

	@NotNull
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public Cuenta getDatosCuenta() {
		return datosCuenta;
	}

	public void setDatosCuenta(Cuenta datosCuenta) {
		this.datosCuenta = datosCuenta;
	}

}
