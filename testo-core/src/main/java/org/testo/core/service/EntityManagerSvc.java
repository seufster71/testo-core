package org.testo.core.service;

import javax.persistence.EntityManager;

public interface EntityManagerSvc {

	EntityManager getInstance();
	EntityManager getMain();
	EntityManager getMulti();
}
