package in.accountantconnect.dao;

import in.accountantconnect.common.CommonException;
import in.accountantconnect.domain.Accountant;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class TrackingDao implements BaseDao{
private SessionFactory sessionFactory;
	
	public Object save(Object obj) throws CommonException{
		Object newObj = sessionFactory.getCurrentSession().save(obj);
		if(newObj != null) {
			return newObj;
		}
		else
			throw new CommonException();

	}
	public void update(Object obj) throws CommonException{
		sessionFactory.getCurrentSession().update(obj);
	}
	public Object delete(Object obj) throws CommonException{
		return null;
	}	
	
	public Object readById(int id) throws CommonException{
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Accountant.class);
			criteria.add(Restrictions.eq("trackid", id));
		try{
			return criteria.uniqueResult();
		}catch (HibernateException e) {
			throw new CommonException();
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

}
