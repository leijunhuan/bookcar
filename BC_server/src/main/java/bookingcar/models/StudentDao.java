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
public class StudentDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public Serializable save(Student student) {
		Serializable save = getSession().save(student);
		return save;
	}
	public Student notices(String studentphone,String studentname) {
		return (Student) getSession().createQuery("from Student where studentphone =:studentphone and studentname =:studentname").setParameter("studentphone", studentphone).setParameter("studentname", studentname)
				.uniqueResult();
	}

}
