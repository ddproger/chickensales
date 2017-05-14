package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.StatusDaoImpl;
import ua.goryainov.hibernate.model.Status;

public class StatusService {
	private static StatusDaoImpl statusDao;

	public StatusService() {
		statusDao = new StatusDaoImpl();
	}

	public void persist(Status entity) {
		statusDao.openCurrentSessionwithTransaction();
		statusDao.persist(entity);
		statusDao.closeCurrentSessionwithTransaction();
	}

	public void update(Status entity) {
		statusDao.openCurrentSessionwithTransaction();
		statusDao.update(entity);
		statusDao.closeCurrentSessionwithTransaction();
	}

	public Status findById(Integer Id) {
		statusDao.openCurrentSession();
		Status statuses = statusDao.findById(Id);
		statusDao.closeCurrentSession();
		return statuses;
	}
	public void delete(Integer Id) {
		statusDao.openCurrentSessionwithTransaction();
		Status status = statusDao.findById(Id);
		statusDao.delete(status);
		statusDao.closeCurrentSessionwithTransaction();
	}

	public List<Status> findAll() {
		statusDao.openCurrentSession();
		List<Status> statuses = statusDao.findAll();
		statusDao.closeCurrentSession();
		return statuses;
	}

	public void deleteAll() {
		statusDao.openCurrentSessionwithTransaction();
		statusDao.deleteAll();
		statusDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public StatusDaoImpl statusDao() {
		return statusDao;
	}
}

