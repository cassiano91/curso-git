package br.com.unimeduberaba.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.unimeduberaba.domain.Commit;

public class CommitDAO {

	public List<Commit> buscarTodos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("package");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Commit> query = (CriteriaQuery<Commit>) builder.createQuery(Commit.class);
		Root<Commit> from = (Root<Commit>) query.from(Commit.class);

		if (from != null) {
			query.select(from);
		}

		List<Commit> lista = manager.createQuery(query).getResultList();
		manager.close();

		return lista;
	}

	public Integer salvar(Commit commit) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("package");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		manager.merge(commit);

		manager.getTransaction().commit();

		manager.close();

		return commit.getId();
	}

	public Commit buscarUltimoPorAmbiente(String ambiente) {
		System.out.println(ambiente.toUpperCase().trim());
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("package");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Commit> query = (CriteriaQuery<Commit>) builder.createQuery(Commit.class);
		Root<Commit> from = (Root<Commit>) query.from(Commit.class);

		query.select(from).distinct(true);

		Predicate predicate = builder.equal(builder.upper(from.get("ambiente")), ambiente.toUpperCase().trim());
		query.where(predicate);

		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		orders.add(builder.desc(from.get("id")));
		query.orderBy(orders);

		List<Commit> lista = manager.createQuery(query).setMaxResults(1).getResultList();
		manager.close();

		if (lista.isEmpty()) {
			return null;
		}

		return lista.get(0);
	}
}
