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
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	private CoachDao _coachDao;

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
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}else{
			map.put("coach", 0);
		}
		
		return map;
	}
} // class UserController
