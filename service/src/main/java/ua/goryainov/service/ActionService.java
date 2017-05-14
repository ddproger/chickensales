package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.ActionDaoImpl;
import ua.goryainov.hibernate.model.Action;

public class ActionService {
	private static ActionDaoImpl actionDao;

	public ActionService() {
		actionDao = new ActionDaoImpl();
	}

	public void persist(Action entity) {
		actionDao.openCurrentSessionwithTransaction();
		actionDao.persist(entity);
		actionDao.closeCurrentSessionwithTransaction();
	}

	public void update(Action entity) {
		actionDao.openCurrentSessionwithTransaction();
		actionDao.update(entity);
		actionDao.closeCurrentSessionwithTransaction();
	}

	public Action findById(Integer Id) {
		actionDao.openCurrentSession();
		Action actions = actionDao.findById(Id);
		actionDao.closeCurrentSession();
		return actions;
	}
	public void delete(Integer Id) {
		actionDao.openCurrentSessionwithTransaction();
		Action action = actionDao.findById(Id);
		actionDao.delete(action);
		actionDao.closeCurrentSessionwithTransaction();
	}

	public List<Action> findAll() {
		actionDao.openCurrentSession();
		List<Action> actions = actionDao.findAll();
		actionDao.closeCurrentSession();
		return actions;
	}
	public List<Action> findByUser(int userId) {
		actionDao.openCurrentSession();
		List<Action> actions = actionDao.findByUser(userId);
		actionDao.closeCurrentSession();
		return actions;
	}

	public void deleteAll() {
		actionDao.openCurrentSessionwithTransaction();
		actionDao.deleteAll();
		actionDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public ActionDaoImpl actionDao() {
		return actionDao;
	}
}

