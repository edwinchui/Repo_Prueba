package net.linc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "nombre_usuario"))
public class Cuenta {

	@Id
	@SequenceGenerator(name = "CUENTA_GENERADOR_ID", sequenceName = "cuenta_sec", allocationSize=0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUENTA_GENERADOR_ID")
	private long id;

	@NotNull
	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@NotNull
	private String clave;

	@NotNull
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
