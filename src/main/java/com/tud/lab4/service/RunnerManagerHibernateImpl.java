package com.tud.lab4.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tud.lab4.tp.*;

@Component
@Transactional
public class RunnerManagerHibernateImpl implements RunnerManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addRunner(Runner runner) {
		runner.setId(null);
		sessionFactory.getCurrentSession().save(runner);

	}

	public void deleteRunner(Runner runner) {
		sessionFactory.getCurrentSession().delete(runner);

	}

	@SuppressWarnings("unchecked")
	public List<Runner> getAllRunners() {
		return sessionFactory.getCurrentSession().getNamedQuery("runner.all")
				.list();
	}

	public void updateRunner(Runner runner, String newName) {
		runner.setFirstName(newName);
		sessionFactory.getCurrentSession().update(runner);
	}

	public Runner runnerById(Long id) {
		return (Runner) sessionFactory.getCurrentSession()
				.get(Runner.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Runner> runnerByLastName(String lastName) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("SELECT r FROM RUNNER r WHERE r.LASTNAME = :lastName")
				.setParameter("lastName", lastName).list();
	}

}
