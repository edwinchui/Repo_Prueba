package net.linc.app.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Garantia {

	@Id
	@SequenceGenerator(name = "GARANTIA_GENERADOR_ID", sequenceName = "cliente_sec", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GARANTIA_GENERADOR_ID")
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Prestamo.class)
	@JoinColumn(name = "id_prestamo")
	private Prestamo prestamo;
	
	@Column(name = "tipo_garantia", length = 5)
	private String tipoGarantia;

	@Column(name = "valor_garantia", length = 5, precision = 2)
	private BigDecimal valorGarantia;

	@Column(length = 250)
	private String descripcion;

	@Column(length = 2)
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoGarantia() {
		return tipoGarantia;
	}

	public void setTipoGarantia(String tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}

	public BigDecimal getValorGarantia() {
		return valorGarantia;
	}

	public void setValorGarantia(BigDecimal valorGarantia) {
		this.valorGarantia = valorGarantia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
