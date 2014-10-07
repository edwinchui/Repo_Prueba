package net.linc.app.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Prestamo {
	
	@Id
	@SequenceGenerator(name = "PRESTAMO_GENERADOR_ID", sequenceName = "prestamo_sec", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESTAMO_GENERADOR_ID")
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Cliente.class)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Garantia.class)
	@NotNull
	private List<Garantia> garantias;
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date fechaRegistro;
	
	@Column(name = "monto_credito", length = 5, precision = 2)
	@NotNull
	private BigDecimal montoCredito;
	
	@Column(name = "tipo_moneda", length = 5)
	@NotNull
	private String tipoMoneda;
	
	@Column(length = 2, precision = 1)
	@NotNull
	private BigDecimal interes;
	
	@Column(name = "tiempo_limite_pago")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date fechaLimitePago;
	
	@Column(name = "tipo_cambio", length = 2, precision = 2)
	@NotNull
	private BigDecimal tipoCambio;
	
	@Column(length = 2)
	@NotNull
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Garantia> getGarantias() {
		return garantias;
	}

	public void setGarantias(List<Garantia> garantias) {
		this.garantias = garantias;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public BigDecimal getMontoCredito() {
		return montoCredito;
	}

	public void setMontoCredito(BigDecimal montoCredito) {
		this.montoCredito = montoCredito;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaLimitePago() {
		return fechaLimitePago;
	}

	public void setFechaLimitePago(Date fechaLimitePago) {
		this.fechaLimitePago = fechaLimitePago;
	}
	
}
