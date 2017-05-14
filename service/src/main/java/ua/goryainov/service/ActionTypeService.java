package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.ActionDaoImpl;
import ua.goryainov.hibernate.dao.ActionTypeDaoImpl;
import ua.goryainov.hibernate.model.Action;
import ua.goryainov.hibernate.model.ActionType;

public class ActionTypeService {
	private static ActionTypeDaoImpl actionTypeDao;

	public ActionTypeService() {
		actionTypeDao = new ActionTypeDaoImpl();
	}

	public void persist(ActionType entity) {
		actionTypeDao.openCurrentSessionwithTransaction();
		actionTypeDao.persist(entity);
		actionTypeDao.closeCurrentSessionwithTransaction();
	}

	public void update(ActionType entity) {
		actionTypeDao.openCurrentSessionwithTransaction();
		actionTypeDao.update(entity);
		actionTypeDao.closeCurrentSessionwithTransaction();
	}

	public ActionType findById(Integer Id) {
		actionTypeDao.openCurrentSession();
		ActionType actions = actionTypeDao.findById(Id);
		actionTypeDao.closeCurrentSession();
		return actions;
	}
	public void delete(Integer Id) {
		actionTypeDao.openCurrentSessionwithTransaction();
		ActionType actionType = actionTypeDao.findById(Id);
		actionTypeDao.delete(actionType);
		actionTypeDao.closeCurrentSessionwithTransaction();
	}

	public List<ActionType> findAll() {
		actionTypeDao.openCurrentSession();
		List<ActionType> actionTypes = actionTypeDao.findAll();
		actionTypeDao.closeCurrentSession();
		return actionTypes;
	}
	
	public void deleteAll() {
		actionTypeDao.openCurrentSessionwithTransaction();
		actionTypeDao.deleteAll();
		actionTypeDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public ActionTypeDaoImpl actionTypeDao() {
		return actionTypeDao;
	}
}

