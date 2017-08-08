package bookingcar.models;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class NoticeDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public Serializable save(Notice notice) {
		Serializable save = getSession().save(notice);
		return save;
	}
	@SuppressWarnings("unchecked")
	public List<Notice> find(){
		return getSession().createQuery("from Notice").list();
	}
} // class UserDao
