package ua.goryainov.hibernate.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.OrderProduct;
import ua.goryainov.hibernate.model.TopUser;
import ua.goryainov.hibernate.model.User;

public class UserDaoImpl implements TablesDao<User, Integer> {
	private Session currentSession;
	private Transaction currentTransaction;

	public UserDaoImpl() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(User entity) {
		getCurrentSession().save(entity);
	}

	public void update(User entity) {
		getCurrentSession().update(entity);
	}



	public void delete(User entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
		return users;
	}
	public List<TopUser> findTop() {
		List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
		List<TopUser> topUser = new ArrayList<>();
		long cash = 0;
		for (User user:users) {
			for (Commission orders: user.getOrders()) {
				for (OrderProduct od : orders.getOrderProduct()){
					cash += od.getPrice()*od.getCount();
				}
			}
			topUser.add(new TopUser(user.getName(),user.getEDRPOU(),user.getMail(),user.getTel1(),user.getTel2(),user.getDeliveryAdress(),cash));
		}
		return topUser;

	}

	public void deleteAll() {
		List<User> entityList = findAll();
		for (User entity : entityList) {
			delete(entity);
		}
	}
	public User findById(Integer id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}


}