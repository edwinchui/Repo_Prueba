package net.linc.app.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.linc.app.model.Cliente;

/**
 * Session Bean implementation class ClienteDao
 */
@Stateless
@LocalBean
public class ClienteDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void regCliente(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public List<Cliente> selClientesTodos() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
		Root<Cliente> clienteRoot = query.from(Cliente.class);

		query.select(clienteRoot)
		.where(builder.equal(clienteRoot.get("estado"), "AC"))
		.orderBy(builder.asc(clienteRoot.get("nombre")));
		
		return this.entityManager.createQuery(query).getResultList();
	}

	public void actCliente(Cliente cliente) {
		this.entityManager.merge(cliente);
	}
}
