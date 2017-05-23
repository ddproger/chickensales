package ua.goryainov.hibernate.dao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import ua.goryainov.hibernate.model.Commission;
import ua.goryainov.hibernate.model.OrderProduct;
import ua.goryainov.hibernate.model.TopUser;
import ua.goryainov.hibernate.model.User;

import javax.persistence.TemporalType;

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
		List<User> users = (List<User>) getCurrentSession().createQuery("from User order by rating asc").list();
		int count=0;
		for (User user : users) {
			if(count<2){
				user.setGroup(1);
			}else if(count<5)
			{
				user.setGroup(2);
			}else if (count<10){
				user.setGroup(3);
			}count++;
		}
		return users;
	}
	public List<User> findTop() {
		List<User> users = new LinkedList<>(getCurrentSession().createQuery("from User order by rating").list());
		users.sort(new Comparator<User>() {
			@Override
			public int compare(User user, User t1) {
				if(user.getRating()<t1.getRating())return 1; else return -1;
			}
		});
		return users;
	}

	public List<User> findTop(String from, String to) {
		//Object users =  getCurrentSession().createQuery("select u from OrderProduct o inner join o.commission as c inner join c.user as u group u, u.orderList").list();
		//Object users =  getCurrentSession().createQuery("select u from User u right join u.orderList").list();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//		Date fromDate=null;
//		String toDate=null;
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = sf.parse(from);
			toDate =sf.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String hql= "select u from Commission c inner join c.user as u where c.date BETWEEN :frmdate and :todate group by u order by u.rating desc";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("frmdate", fromDate);
		query.setParameter("todate", toDate);
		List<User> users = (List<User>) query.list();
		//List<User> users = (List<User>) getCurrentSession().createQuery("select u from Commission c inner join c.user as u where c.date BETWEEN"+fromDate+" and "+toDate+" group by u").list();
		long rating = 0;
		int count=0;
		for (User user : users) {
			if(count<2){
				user.setGroup(1);
			}else if(count<5)
			{
				user.setGroup(2);
			}else if (count<10){
				user.setGroup(3);
			}count++;
			for (Commission commission : user.getOrders())
				for (OrderProduct orderProduct : commission.getOrderProduct()) {
					rating +=orderProduct.getCount()*orderProduct.getPrice();
				}
				user.setRating(rating);
			rating = 0;
		}
		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(User user, User t1) {
				if(user.getRating()>t1.getRating())return -1;else return 1;
			}
		});
		return users;
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