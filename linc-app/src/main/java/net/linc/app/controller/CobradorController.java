package net.linc.app.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.linc.app.data.CobradorDao;
import net.linc.app.data.CuentaDao;
import net.linc.app.model.Cobrador;
import net.linc.app.model.Cuenta;

import java.io.Serializable;

@Named("mbCobrador")
@ConversationScoped
public class CobradorController implements Serializable {

	@Inject
	private CobradorDao daoCobrador;

	@Inject
	private CuentaDao daoCuenta;

	@Inject
	private Conversation conversacion;

	private Cobrador cobrador;
	private List<Cobrador> listaCobradores;
	private LinkedHashMap<String, Object> expedidos;

	private static final long serialVersionUID = 1L;

	public void inicio() {
		if (FacesContext.getCurrentInstance().isPostback())
			return;

		if (this.conversacion.isTransient()) {
			this.conversacion.begin();

			this.cobrador = new Cobrador();

			this.expedidos = new LinkedHashMap<String, Object>();
			this.expedidos.put("LA PAZ", "LP");
			this.expedidos.put("COCHABAMBA", "CB");
			this.expedidos.put("SANTA CRUZ", "SC");

			this.listaCobradores = this.daoCobrador.selCobradoresTodos();
		}
	}

	public void guardar() {
		if(this.cobrador.getId() == 0l) {
			this.cobrador.setEstado("AC");
			this.cobrador.setDatosCuenta(this.generarCuenta());
	
			this.daoCobrador.regCobrador(cobrador);
	
			this.cobrador = new Cobrador();
			this.listaCobradores = this.daoCobrador.selCobradoresTodos();
		} else {
			this.daoCobrador.actCobrador(this.cobrador);
		}
		
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Registro Correcto",
								"El registro de los datos del cobrador se ha realizado correctamente."));
	}
	
	public void cancelar() {
		this.cobrador = new Cobrador();
	}
	
	public void eliminar() {
		this.cobrador.setEstado("AN");
		this.daoCobrador.actCobrador(cobrador);
		this.listaCobradores = this.daoCobrador.selCobradoresTodos();
	}
	
	private Cuenta generarCuenta() {
		Cuenta cuenta = new Cuenta();
		String nombreUsuario = "";
		int contador;

		nombreUsuario += String.valueOf(this.cobrador.getNombre().charAt(0))
				.toLowerCase();
		nombreUsuario += this.cobrador.getApellidos().split(" ")[0]
				.toLowerCase();

		contador = 0;
		do {
			if (contador > 0)
				cuenta = this.daoCuenta.selCuentaPorNombreUsuario(nombreUsuario
						+ String.valueOf(contador));
			else
				cuenta = this.daoCuenta
						.selCuentaPorNombreUsuario(nombreUsuario);

			contador++;
		} while (cuenta != null);
		
		if(contador != 0)
			nombreUsuario = nombreUsuario + contador;
		
		cuenta = new Cuenta();
		cuenta.setNombreUsuario(nombreUsuario);
		cuenta.setClave("1234567");
		cuenta.setEstado("AC");

		return cuenta;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public LinkedHashMap<String, Object> getExpedidos() {
		return expedidos;
	}

	public void setExpedidos(LinkedHashMap<String, Object> expedido) {
		this.expedidos = expedido;
	}

	public List<Cobrador> getListaCobradores() {
		return listaCobradores;
	}

	public void setListaCobradores(List<Cobrador> listaCobradores) {
		this.listaCobradores = listaCobradores;
	}

}
