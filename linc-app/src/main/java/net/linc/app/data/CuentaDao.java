package net.linc.app.data;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.linc.app.model.Cuenta;

/**
 * Session Bean implementation class CuentaDao
 */
@Stateless
@LocalBean
public class CuentaDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    public Cuenta selCuentaPorNombreUsuario(String nombreUsuario) {
		CriteriaBuilder builder;
		CriteriaQuery<Cuenta> criteria;
		Root<Cuenta> cuentaRoot;
		try {
			builder = this.entityManager.getCriteriaBuilder();

			criteria = builder.createQuery(Cuenta.class);
			cuentaRoot = criteria.from(Cuenta.class);

			criteria.select(cuentaRoot).where(
					builder.equal(cuentaRoot.get("nombreUsuario"),
							nombreUsuario));

			return this.entityManager.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
