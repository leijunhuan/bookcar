package bookingcar.controllers;

import bookingcar.models.Coach;
import bookingcar.models.CoachDao;
import bookingcar.models.Notice;
import bookingcar.models.NoticeDao;
import bookingcar.models.Student;
import bookingcar.models.StudentDao;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	private NoticeDao _noticeDao;
	@Autowired
	private CoachDao _coachDao;
	@Autowired
	private StudentDao _studentDao;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> create(@RequestBody HashMap<String, Object> maps) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Serializable save = null;
		try {
			save = _noticeDao.save(new Notice(1,  _coachDao.notices(Integer.valueOf((String) maps.get("coachid"))),
					_studentDao.notices(maps.get("studentphone").toString(), maps.get("studentname").toString()),
					format.parse(maps.get("starttime").toString()), format.parse(maps.get("endtime").toString()),0));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("notice", save);
		return map;
	}
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object>  find() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HashMap<String, Object> map2 = new HashMap<String, Object>();	
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i =0 ;i<_noticeDao.find().size();i++){
			Notice notice =_noticeDao.find().get(i);
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("noticeid",notice.getNoticeid());
			map1.put("coachid",notice.getCoach().getCoachid());
			map1.put("studentphone",notice.getStudent().getStudentphone());
			map1.put("studentname",notice.getStudent().getStudentname());
			map1.put("starttime",format.format(notice.getStarttime()));
			map1.put("endtime",format.format(notice.getEndtime()));
			map1.put("readrecord",notice.getReadrecord());
			list.add(map1);
		}		
		map2.put("notice", list);
		return map2;
	}
}
