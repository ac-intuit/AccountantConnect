package in.accountantconnect.dao;

import in.accountantconnect.common.CommonException;

import org.hibernate.SessionFactory;


public interface BaseDao {
	public Object save(Object obj) throws CommonException;
	public Object readById(int id) throws CommonException;
	public void update(Object obj) throws CommonException;
	public Object delete(Object obj) throws CommonException;
	
	public SessionFactory getSessionFactory();
}
