package ua.goryainov.service;

import java.util.List;


import ua.goryainov.hibernate.dao.UserDaoImpl;
import ua.goryainov.hibernate.model.User;

public class UserService {
	private static UserDaoImpl userDao;

	public UserService() {
		userDao = new UserDaoImpl();
	}

	public void persist(User entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.persist(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public void update(User entity) {
		userDao.openCurrentSessionwithTransaction();
		userDao.update(entity);
		userDao.closeCurrentSessionwithTransaction();
	}

	public User findById(Integer Id) {
		userDao.openCurrentSession();
		User user = userDao.findById(Id);
		userDao.closeCurrentSession();
		return user;
	}
	public void delete(Integer Id) {
		userDao.openCurrentSessionwithTransaction();
		User user = userDao.findById(Id);
		userDao.delete(user);
		userDao.closeCurrentSessionwithTransaction();
	}

	public List<User> findAll() {
		userDao.openCurrentSession();
		List<User> users = userDao.findAll();
		userDao.closeCurrentSession();
		return users;
	}

	public void deleteAll() {
		userDao.openCurrentSessionwithTransaction();
		userDao.deleteAll();
		userDao.closeCurrentSessionwithTransaction();
	}
	public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}
	public UserDaoImpl userDao() {
		return userDao;
	}
}

