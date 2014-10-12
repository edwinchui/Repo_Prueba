package net.linc.app.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import net.linc.app.converter.ClienteConverter;
import net.linc.app.data.ClienteDao;
import net.linc.app.data.PrestamoDao;
import net.linc.app.model.Cliente;
import net.linc.app.model.Garantia;
import net.linc.app.model.Prestamo;

@Named("mbPrestamos")
@ConversationScoped
public class PrestamosController implements Serializable {

	private String cadenaBusqueda;

	private Cliente cliente;
	private Prestamo prestamo;
	private Garantia garantia;
	private LinkedHashMap<String, Object> tiposMoneda;
	private LinkedHashMap<String, Object> tiposGarantias;
	private List<Cliente> listaClientes;
	private List<Garantia> listaGarantias;
	private List<Prestamo> listaPrestamos;

	@Inject
	private ClienteDao daoCliente;

	@Inject
	private PrestamoDao daoPrestamo;

	@Inject
	private Conversation conversacion;

	private static final long serialVersionUID = 1L;

	public void inicio() {
		if (FacesContext.getCurrentInstance().isPostback())
			return;

		if (this.conversacion.isTransient()) {
			this.conversacion.begin();

			this.prestamo = new Prestamo();
			this.garantia = new Garantia();
			this.listaGarantias = new ArrayList<Garantia>();
			this.listaClientes = this.daoCliente.selClientesTodos();

			this.tiposMoneda = new LinkedHashMap<String, Object>();
			this.tiposMoneda.put("BOLIVIANOS", "BS");
			this.tiposMoneda.put("DOLARES", "SUS");

			this.tiposGarantias = new LinkedHashMap<String, Object>();
			this.tiposGarantias.put("DOCUMENTOS MINUTA", "DOCUMENTOS MINUTA");
			this.tiposGarantias.put("DOCUMENTOS PROPIEDAD",
					"DOCUMENTOS PROPIEDAD");
			this.tiposGarantias.put("JOYAS", "JOYAS");
		}
	}

	public void inicioListaCreditos() {
		if (FacesContext.getCurrentInstance().isPostback())
			return;

		if (this.conversacion.isTransient()) {
			this.conversacion.begin();

			this.listaPrestamos = this.daoPrestamo.selPrestamosTodos();
		}
	}

	public List<Cliente> filtrarCliente(String dato) {
		List<Cliente> filtroClientes = new ArrayList<Cliente>();

		for (Cliente cliente : this.listaClientes) {
			if (cliente.getNombre().toUpperCase().contains(dato.toUpperCase())
					|| cliente.getApellidos().toUpperCase()
							.contains(dato.toUpperCase())) {
				filtroClientes.add(cliente);
			}
		}

		return filtroClientes;
	}

	public void agregarGarantia() {
		this.listaGarantias.add(this.garantia);
		this.garantia = new Garantia();
	}

	public String guardar() {
		this.prestamo.setFechaRegistro(new Date());
		this.prestamo.setEstado("AC");
		this.prestamo.setGarantias(this.listaGarantias);
		this.prestamo.setCliente(this.cliente);

		this.daoPrestamo.regPrestamo(this.prestamo);
		this.prestamo = new Prestamo();
		this.cliente = new Cliente();
		
		this.conversacion.end();

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Registro Finalizado",
								"El registro del nuevo credito se ha realizado correctamente."));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);

		return "listaCreditos?faces-redirect=true";
	}

	public void eliminar() {
		this.prestamo.setEstado("AN");

		this.daoPrestamo.actPrestamo(this.prestamo);

		this.prestamo = new Prestamo();
		this.listaPrestamos = this.daoPrestamo.selPrestamosTodos();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Operación Exitosa",
						"El registro ha sido eliminado correctamente."));
	}

	public void busqueda() {
		this.listaPrestamos = new ArrayList<Prestamo>();

		this.listaPrestamos.addAll(this.daoPrestamo
				.selPrestamosPorNombreCliente(this.cadenaBusqueda));
	}

	public Converter getConvertirCliente() {
		return new ClienteConverter(this.listaClientes);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public LinkedHashMap<String, Object> getTiposMoneda() {
		return tiposMoneda;
	}

	public void setTiposMoneda(LinkedHashMap<String, Object> tiposMoneda) {
		this.tiposMoneda = tiposMoneda;
	}

	public List<Garantia> getListaGarantias() {
		return listaGarantias;
	}

	public void setListaGarantias(List<Garantia> listaGarantias) {
		this.listaGarantias = listaGarantias;
	}

	public LinkedHashMap<String, Object> getTiposGarantias() {
		return tiposGarantias;
	}

	public void setTiposGarantias(LinkedHashMap<String, Object> tiposGarantias) {
		this.tiposGarantias = tiposGarantias;
	}

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}

	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}

}
