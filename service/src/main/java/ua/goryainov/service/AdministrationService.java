package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.AdministrationDaoImpl;
import ua.goryainov.hibernate.model.Administration;

public class AdministrationService {
	private static AdministrationDaoImpl administrationDao;

	public AdministrationService() {
		administrationDao = new AdministrationDaoImpl();
	}

	public void persist(Administration entity) {
		administrationDao.openCurrentSessionwithTransaction();
		administrationDao.persist(entity);
		administrationDao.closeCurrentSessionwithTransaction();
	}

	public void update(Administration entity) {
		administrationDao.openCurrentSessionwithTransaction();
		administrationDao.update(entity);
		administrationDao.closeCurrentSessionwithTransaction();
	}

	public Administration findById(String login) {
		administrationDao.openCurrentSession();
		Administration admin = administrationDao.findById(login);
		administrationDao.closeCurrentSession();
		return admin;
	}
	public void delete(String login) {
		administrationDao.openCurrentSessionwithTransaction();
		Administration admin = administrationDao.findById(login);
		administrationDao.delete(admin);
		administrationDao.closeCurrentSessionwithTransaction();
	}

	public List<Administration> findAll() {
		administrationDao.openCurrentSession();
		List<Administration> admins = administrationDao.findAll();
		administrationDao.closeCurrentSession();
		return admins;
	}

	public void deleteAll() {
		administrationDao.openCurrentSessionwithTransaction();
		administrationDao.deleteAll();
		administrationDao.closeCurrentSessionwithTransaction();
	}

	public AdministrationDaoImpl adminDao() {
		return administrationDao;
	}
}

