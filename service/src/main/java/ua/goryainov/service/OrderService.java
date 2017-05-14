package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.OrderDaoImpl;
import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.OrderProduct;

public class OrderService {
	private static OrderDaoImpl orderDao;

	public OrderService() {
		orderDao = new OrderDaoImpl();
	}

	public void persist(Commission entity) {
		orderDao.openCurrentSessionwithTransaction();
		orderDao.persist(entity);
		orderDao.closeCurrentSessionwithTransaction();
	}

	public void update(Commission entity) {
		orderDao.openCurrentSessionwithTransaction();
		orderDao.update(entity);
		orderDao.closeCurrentSessionwithTransaction();
	}

	public Commission findById(Integer Id) {
		orderDao.openCurrentSession();
		Commission order = orderDao.findById(Id);
		orderDao.closeCurrentSession();
		return order;
	}
	public void delete(Integer Id) {
		orderDao.openCurrentSessionwithTransaction();
		Commission order = orderDao.findById(Id);
		orderDao.delete(order);
		orderDao.closeCurrentSessionwithTransaction();
	}

	public List<Commission> findAll() {
		orderDao.openCurrentSession();
		List<Commission> orders = orderDao.findAll();
		orderDao.closeCurrentSession();
		return orders;
	}
	public List<Commission> findByUser(int userId) {
		orderDao.openCurrentSession();
		List<Commission> orders = orderDao.findByUser(userId);
		orderDao.closeCurrentSession();
		return orders;
	}
	public void deleteAll() {
		orderDao.openCurrentSessionwithTransaction();
		orderDao.deleteAll();
		orderDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public OrderDaoImpl orderDao() {
		return orderDao;
	}
}

