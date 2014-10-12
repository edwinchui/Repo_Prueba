package net.linc.app.data;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.linc.app.model.Cliente;
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

	public List<Prestamo> selPrestamosTodos() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Prestamo> query = builder.createQuery(Prestamo.class);
		Root<Prestamo> prestamoRoot = query.from(Prestamo.class);

		query.select(prestamoRoot)
				.where(builder.equal(prestamoRoot.get("estado"), "AC"))
				.orderBy(builder.desc(prestamoRoot.get("fechaRegistro")));

		return this.entityManager.createQuery(query).getResultList();
	}

	public void actPrestamo(Prestamo prestamo) {
		this.entityManager.merge(prestamo);
	}

	public List<Prestamo> selPrestamosPorNombreCliente(String nombreCliente) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Prestamo> query = builder.createQuery(Prestamo.class);
		Root<Prestamo> prestamoRoot = query.from(Prestamo.class);
		Root<Cliente> clienteRoot = query.from(Cliente.class);

		List<Predicate> listaPredicadosOr = new ArrayList<Predicate>();

		Predicate predicadoCliente = builder.equal(prestamoRoot.get("cliente"),
				clienteRoot);

		listaPredicadosOr.add(builder.like(
				builder.upper(clienteRoot.<String> get("nombre")), "%"
						+ nombreCliente.toUpperCase() + "%"));

		listaPredicadosOr.add(builder.like(
				builder.upper(clienteRoot.<String> get("apellidos")), "%"
						+ nombreCliente.toUpperCase() + "%"));

		query.select(prestamoRoot).where(
				builder.and(predicadoCliente),
				builder.or(listaPredicadosOr
						.toArray(new Predicate[listaPredicadosOr.size()])));

		return this.entityManager.createQuery(query).getResultList();
	}
}
