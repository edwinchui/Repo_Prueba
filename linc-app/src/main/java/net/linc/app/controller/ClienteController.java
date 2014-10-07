package net.linc.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.linc.app.data.ClienteDao;
import net.linc.app.model.Cliente;

@Named("mbCliente")
@ConversationScoped
public class ClienteController implements Serializable {

	private Cliente cliente;
	private LinkedHashMap<String, Object> expedido;
	private List<Cliente> listaClientes;

	@Inject
	private ClienteDao daoCliente;

	@Inject
	private Conversation conversacion;

	private static final long serialVersionUID = 1L;

	public void inicio() {
		if (FacesContext.getCurrentInstance().isPostback())
			return;

		if (this.conversacion.isTransient()) {
			this.conversacion.begin();

			this.cliente = new Cliente();
			this.listaClientes = this.daoCliente.selClientesTodos();

			this.expedido = new LinkedHashMap<String, Object>();
			this.expedido.put("LA PAZ", "LP");
			this.expedido.put("COCHABAMBA", "CB");
			this.expedido.put("SANTA CRUZ", "SC");

		}
	}

	public void guardar() {
		this.cliente.setEstado("AC");
		this.daoCliente.regCliente(this.cliente);

		this.cliente = new Cliente();
		this.listaClientes = this.daoCliente.selClientesTodos();

		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Registro Correcto",
								"El registro de los datos del cliente se han registrado correctamente"));
	}
	
	public void cancelar() {
		this.cliente = new Cliente();
	}
	
	public void eliminar() {
		this.cliente.setEstado("AN");

		this.daoCliente.actCliente(this.cliente);
		this.listaClientes = this.daoCliente.selClientesTodos();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Actulización Correcta",
						"El cliente se ha eliminado correctamente."));
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LinkedHashMap<String, Object> getExpedido() {
		return expedido;
	}

	public void setExpedido(LinkedHashMap<String, Object> expedido) {
		this.expedido = expedido;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

}
