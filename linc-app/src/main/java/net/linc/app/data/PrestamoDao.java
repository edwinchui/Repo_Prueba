package net.linc.app.data;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.linc.app.model.Prestamo;

/**
 * Session Bean implementation class CuentaDao
 */
@Stateless
@LocalBean
public class PrestamoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void regPrestamo(Prestamo prestamo) {
		this.entityManager.persist(prestamo);
	}
	
}
