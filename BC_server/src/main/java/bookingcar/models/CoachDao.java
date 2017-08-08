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
public class CoachDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public Serializable save(Coach coach) {
		Serializable save = getSession().save(coach);
		return save;
	}
	public Coach login(Coach coach) {
		return (Coach) getSession().createQuery("from Coach where coachphone =:coachphone and coachpassword =:coachpassword").setParameter("coachphone", coach.getCoachphone()).setParameter("coachpassword", coach.getCoachpassword())
				.uniqueResult();
	}
	public Coach notices(int id) {
		return (Coach) getSession().createQuery("from Coach where coachid =:coachid").setParameter("coachid", id)
				.uniqueResult();
	}
/*	public List<Coach> login(Coach coach) {
		return getSession().createQuery("from Coach").list();
	}*/

/*
	public void delete(User user) {
		getSession().delete(user);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		return (User) getSession().createQuery("from User where email = :email").setParameter("email", email)
				.uniqueResult();
	}
	public User getById(long id) {
		return (User) getSession().load(User.class, id);
	}
	public void update(User user) {
		getSession().update(user);
		return;
	}
*/
} // class UserDao
