package net.linc.app.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.linc.app.model.Cobrador;

/**
 * Session Bean implementation class CobradorDao
 */
@Stateless
@LocalBean
public class CobradorDao {

    @PersistenceContext(name = "primary")
    private EntityManager entityManager;
    
    public void regCobrador(Cobrador cobrador) {
    	this.entityManager.persist(cobrador);
    }
    
    public void actCobrador(Cobrador cobrador) {
    	this.entityManager.merge(cobrador);
    }
    
    public List<Cobrador> selCobradoresTodos() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Cobrador> query = builder.createQuery(Cobrador.class);
		Root<Cobrador> cobradorRoot = query.from(Cobrador.class);

		query.select(cobradorRoot)
				.where(builder.equal(cobradorRoot.get("estado"), "AC"))
				.orderBy(builder.asc(cobradorRoot.get("nombre")));

		return this.entityManager.createQuery(query).getResultList();
	}

}
