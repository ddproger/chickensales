package ua.goryainov.service;

import java.util.List;

import ua.goryainov.hibernate.dao.ProductDaoImpl;
import ua.goryainov.hibernate.model.Product;

public class ProductService {
	private static ProductDaoImpl productDao;

	public ProductService() {
		productDao = new ProductDaoImpl();
	}

	public void persist(Product entity) {
		productDao.openCurrentSessionwithTransaction();
		productDao.persist(entity);
		productDao.closeCurrentSessionwithTransaction();
	}

	public void update(Product entity) {
		productDao.openCurrentSessionwithTransaction();
		productDao.update(entity);
		productDao.closeCurrentSessionwithTransaction();
	}

	public Product findById(Integer Id) {
		productDao.openCurrentSession();
		Product products = productDao.findById(Id);
		productDao.closeCurrentSession();
		return products;
	}
	public void delete(Integer Id) {
		productDao.openCurrentSessionwithTransaction();
		Product product = productDao.findById(Id);
		productDao.delete(product);
		productDao.closeCurrentSessionwithTransaction();
	}

	public List<Product> findAll() {
		productDao.openCurrentSession();
		List<Product> products = productDao.findAll();
		productDao.closeCurrentSession();
		return products;
	}

	public void deleteAll() {
		productDao.openCurrentSessionwithTransaction();
		productDao.deleteAll();
		productDao.closeCurrentSessionwithTransaction();
	}
	/*public User verifyUser(String login, String pass){
		List<User> users = findAll();
		for(User user:users){
			if(user.getLogin().equals(login)&&user.getPassword().equals(pass))
				return user;
		}
		return null;
	}*/
	public ProductDaoImpl productDao() {
		return productDao;
	}
}

