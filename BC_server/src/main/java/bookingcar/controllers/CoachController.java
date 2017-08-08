package bookingcar.controllers;

import bookingcar.models.Coach;
import bookingcar.models.CoachDao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/coach")
public class CoachController {

	@Autowired
	private CoachDao _coachDao;
	/*	@RequestMapping(value = "/delete")

	@ResponseBody
	public String delete(long id) {
		try {
			User user = new User(id);
			_userDao.delete(user);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "User succesfully deleted!";
	}

	@RequestMapping(value = "/get-by-email")

	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		try {
			User user = _userDao.getByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}
*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> create(@RequestBody Coach coach) {
		Serializable save = null;
		try {
			save = _coachDao.save(coach);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("coach", save);
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> login(@RequestBody Coach coach) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(_coachDao.login(coach)!=null){
			try {
				map.put("coach", _coachDao.login(coach).getCoachid());
				map.put("phone", _coachDao.login(coach).getCoachphone());
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}else{
			map.put("coach", 0);
		}
		
		return map;
	}
} // class UserController
