package in.ineuron.Test;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.Model.Student;
import in.ineuron.Util.HibernateUtil;

public class SaveOrUpdate {

	public static void main(String[] args) throws IOException {
		
		Session session=null;
		Transaction transaction=null;
		boolean flag = false;
		
		try {
		session=HibernateUtil.geSession();
		
		if(session !=null)
		{
			transaction = session.beginTransaction();
			if(transaction != null)
			{
				Student student = new Student();
				student.setSid(99);
				student.setSname("Galye");
				student.setSaddress("KKR");
				student.setSage(41);
				session.saveOrUpdate(student);
				
				flag=true;
				
			}
			
		}
		}catch (HibernateException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(flag) {
				transaction.commit();
			System.out.println("Object saved to database");
			System.in.read();
			
			}
			else {
				transaction.rollback();
				System.out.println("Object not saved to database");
			
			}
			System.in.read();
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
