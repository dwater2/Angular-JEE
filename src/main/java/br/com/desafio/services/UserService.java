package br.com.desafio.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.desafio.models.User;

@LocalBean
@Stateless
public class UserService extends GenericService<User, String> {

	public UserService() {
		super(User.class);
	}

	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	public List<User> findAll(User user) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT u FROM User u where 1 = 1 ");
		
		if (user.getName() != null && !user.getName().isEmpty()) {
			consulta.append(" and name like :pName");
		}
		if (user.getSituation() != null && !user.getSituation().isEmpty()) {
			consulta.append(" and situation = :pSituation");
		}
		if (user.getProfile() != null) {
			consulta.append(" and profile = :pProfile");
		}
		TypedQuery<User> query = em.createQuery(consulta.toString(), User.class);
		if (user.getName() != null && !user.getName().isEmpty()) {
			query.setParameter("pName", "%" + user.getName() + "%");
		}
		if (user.getSituation() != null && !user.getSituation().isEmpty()) {
			query.setParameter("pSituation", user.getSituation());
		}
		if (user.getProfile() != null) {
			query.setParameter("pProfile", user.getProfile());
		}

		return query.getResultList();
	}
}
