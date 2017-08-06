package br.com.desafio.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.desafio.models.Role;

@LocalBean
@Stateless
public class RoleService extends GenericService<Role, Long> {

	public RoleService() {
		super(Role.class);
	}

	public List<Role> findAll() {
		TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r", Role.class);
		return query.getResultList();
	}
}
