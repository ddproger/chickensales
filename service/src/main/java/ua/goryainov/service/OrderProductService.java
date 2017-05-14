package ua.goryainov.service;

import java.util.List;


import ua.goryainov.hibernate.dao.OrderProductDaoImpl;
import ua.goryainov.hibernate.model.OrderProduct;

public class OrderProductService {
	private static OrderProductDaoImpl orderProductDao;

	public OrderProductService() {
		orderProductDao = new OrderProductDaoImpl();
	}

	public void persist(OrderProduct entity) {
		orderProductDao.openCurrentSessionwithTransaction();
		orderProductDao.persist(entity);
		orderProductDao.closeCurrentSessionwithTransaction();
	}

	public void update(OrderProduct entity) {
		orderProductDao.openCurrentSessionwithTransaction();
		orderProductDao.update(entity);
		orderProductDao.closeCurrentSessionwithTransaction();
	}

	public OrderProduct findById(Integer Id) {
		orderProductDao.openCurrentSession();
		OrderProduct orderProduct = orderProductDao.findById(Id);
		orderProductDao.closeCurrentSession();
		return orderProduct;
	}
	public void delete(Integer Id) {
		orderProductDao.openCurrentSessionwithTransaction();
		OrderProduct orderProduct = orderProductDao.findById(Id);
		orderProductDao.delete(orderProduct);
		orderProductDao.closeCurrentSessionwithTransaction();
	}

	public List<OrderProduct> findAll() {
		orderProductDao.openCurrentSession();
		List<OrderProduct> orderProducts = orderProductDao.findAll();
		orderProductDao.closeCurrentSession();
		return orderProducts;
	}

	
	public void deleteAll() {
		orderProductDao.openCurrentSessionwithTransaction();
		orderProductDao.deleteAll();
		orderProductDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public OrderProductDaoImpl orderProductDao() {
		return orderProductDao;
	}
}

