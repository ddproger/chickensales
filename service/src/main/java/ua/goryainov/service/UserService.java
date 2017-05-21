package ua.goryainov.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import ua.goryainov.hibernate.dao.UserDaoImpl;
import ua.goryainov.hibernate.model.TopUser;
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
	public List<User> findIndividum() {
		userDao.openCurrentSession();
		List<User> individumUser = new ArrayList<>();
		List<User> users = userDao.findAll();
		for (User user:users) {
			if(user.getEDRPOU().equals("")){
				individumUser.add(user);
			}
		}
		userDao.closeCurrentSession();
		return individumUser;
	}
	public List<User> findLegal() {
		userDao.openCurrentSession();
		List<User> individumUser = new ArrayList<>();
		List<User> users = userDao.findAll();
		for (User user:users) {
			if(!user.getEDRPOU().equals("")){
				individumUser.add(user);
			}
		}
		userDao.closeCurrentSession();
		return individumUser;
	}
	public List<User> findTop(){
		userDao.openCurrentSession();
		List<User> users = userDao.findTop();
		userDao.closeCurrentSession();
		return users;
	}
	public List<User> findTop(String from,String to){
		userDao.openCurrentSession();
		List<User> users = userDao.findTop(from,to);
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

