package com.casebbs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.casebbs.core.ListResult;
import com.casebbs.core.Result;
import com.casebbs.model.Article;
import com.casebbs.model.ArticleAttchs;
import com.casebbs.model.ArticleItem;
import com.casebbs.model.ArticleItemAttchs;
import com.casebbs.service.CaseBBSService;

/**
 * 平台页面初始化控制器
 * 
 * @author lq
 * 
 */
@Controller
@RequestMapping("/caseBBS")
public class CaseBBSController {

	@Autowired
	CaseBBSService caseBBSService;

	/**
	 * web跳转到日历页面
	 * 
	 * @param request
	 * @param userName
	 *            用户姓名
	 * @param userId
	 *            用户id
	 * @param organId
	 *            机构id
	 * @param organName
	 *            机构名称
	 * @param userMark
	 *            用户标识，用于判断是否是版主登录，版主登录，具有删除功能
	 * @return
	 */
	@RequestMapping(value = "/gotoIndex.do", produces = "application/json;charset=UTF-8")
	public ModelAndView gotoIndex(
			HttpServletRequest request,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "userName", required = true) String userName)
			throws Exception {
		int years = 0;
		int month = 0;
		String dates = "";
		if (date != null && !date.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(date);
			Calendar cld = Calendar.getInstance();
			cld.setTime(d);
			years = cld.get(Calendar.YEAR);
			month = cld.get(Calendar.MONTH) + 1;
			dates = date;
		} else {
			Calendar c = Calendar.getInstance();
			years = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH) + 1;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dates = sdf.format(new Date());
		}
		ModelAndView mv = new ModelAndView("/calendar"); 
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		Integer userMark = 2;
		if(userName.equals("admin")){
			userMark = 1;
		}
		
		mv.addObject("userName", userName); 
		mv.addObject("userMark", userMark);
		mv.addObject("years", years);
		mv.addObject("month", month);
		mv.addObject("date", dates);
		return mv;
	}

	/**
	 * web跳转到帖子列表
	 * 
	 * @param request
	 * @param date
	 *            日期
	 * @param userName
	 *            用户姓名
	 * @param userId
	 *            用户id
	 * @param organId
	 *            机构id
	 * @param organName
	 *            机构名称
	 * @param userMark
	 *            用户标识，用于判断是否是版主登录，版主登录，具有删除功能
	 * @return
	 */
	@RequestMapping(value = "/gotoArticleList.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView gotoArticleList(
			HttpServletRequest request,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "userName", required = true) String userName, 
			@RequestParam(value = "userMark", required = true) String userMark)
			throws Exception {
		int years = 0;
		int month = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		Calendar cld = Calendar.getInstance();
		cld.setTime(d);
		years = cld.get(Calendar.YEAR);
		month = cld.get(Calendar.MONTH) + 1;

		ModelAndView mv = new ModelAndView("/articalList"); 
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		mv.addObject("userName", userName); 
		mv.addObject("userMark", userMark);
		mv.addObject("years", years);
		mv.addObject("month", month);
		if (title != null) {
			title = new String(title.getBytes("ISO-8859-1"), "UTF-8");
			mv.addObject("title", title);
		}
		mv.addObject("date", date);
		return mv;
	}

	/**
	 * web跳转到帖子详细信息
	 * 
	 * @param request
	 * @param id
	 *            帖子id
	 * @return
	 */
	@RequestMapping(value = "/gotoArticleInfo.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView gotoArticleInfo(
			HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "userName", required = true) String userName, 
			@RequestParam(value = "userMark", required = true) String userMark)
			throws Exception {
		ModelAndView mv = new ModelAndView("/articalInfo");
		mv.addObject("msgId", id); 
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		mv.addObject("userName", userName); 
		mv.addObject("userMark", userMark);
		mv.addObject("date", date);
		return mv;
	}

	/**
	 * 根据月份，获取日历，并加载备勤汇总数据信息
	 * 
	 * @param date
	 *            日期：2014-12-01
	 * @param orgId
	 *            组织机构id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCalender.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getCalender(
			@RequestParam(value = "date", required = false) String date,
			HttpServletRequest request) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		Calendar c = Calendar.getInstance();

		c.setTime(d);
		int totalDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String beginMonth = "";
		if (month < 10) {
			beginMonth = "0" + month;
		} else {
			beginMonth = "" + month;
		}
		String beginYmd = year + beginMonth + "01";
		String endYmd = year + beginMonth + totalDays;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginYmd", Integer.parseInt(beginYmd));
		map.put("endYmd", Integer.parseInt(endYmd));
		List<Article> list = caseBBSService.loadMessageListByDate(map);
		String result = "[";
		for (int i = 1; i <= totalDays; i++) {
			c.set(Calendar.DAY_OF_MONTH, i);
			Date dates = c.getTime();
			Calendar cld = Calendar.getInstance();
			cld.setTime(dates);
			String week = getWeekOfDate(dates);
			Integer dtd = 0;
			if (i < 10) {
				String dt = year + beginMonth + "0" + i;
				dtd = Integer.parseInt(dt);
			} else {
				String dt = year + beginMonth + i + "";
				dtd = Integer.parseInt(dt);
			}
			result += "{\"y\":\"" + year + "\",\"m\":\"" + month
					+ "\",\"d\":\"" + i + "\",\"week\":\"" + week
					+ "\",\"totalpolice\":\"" + getTotal(dtd, list) + "\"},";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1) + "]";
		}
		return result;
	}

	/**
	 * 根据日期、组织，获取报备类型列表
	 * 
	 * @param date
	 * @param orgId
	 * @return
	 */
	private String getTotal(Integer dtd, List<Article> list) {
		try {
			String result = "";
			if (list.size() > 0) {
				for (Article s : list) {
					if (dtd.equals(s.getDateNo())) {
						result += "<li style='color:wheat'>";
						if (s.getTypeId() == 1) {
							result += "盗抢机动车：" + s.getTotalCount();
						} else if (s.getTypeId() == 2) {
							result += "盗窃车内物品：" + s.getTotalCount();
						} else if (s.getTypeId() == 3) {
							result += "入室盗窃：" + s.getTotalCount();
						} else if (s.getTypeId() == 4) {
							result += "抢劫、抢夺：" + s.getTotalCount();
						} else if (s.getTypeId() == 5) {
							result += "诈骗：" + s.getTotalCount();
						}else if (s.getTypeId() == 6) {
							result += "故障报送：" + s.getTotalCount();
						} else if (s.getTypeId() == 7) {
							result += "工作需求：" + s.getTotalCount();
						}

						result += "</li>";
					}
				}
				if (result.length() == 0) {
					result = "<li class='nobaobei' style='display: list-item;'>还没有新帖发布哦！快去发帖吧！</li>";
				}
			} else {
				result = "<li class='nobaobei' style='display: list-item;'>没有新帖发布</li>";
			}
			return result;
		} catch (Exception ex) {
			return " 喔噢！获取失败了！ ";
		}
	}

	/**
	 * 根据日期获得星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysCode[intWeek];
	}

	@RequestMapping(value = "/getMessageList.do")
	@ResponseBody
	public String getMessageList(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "param", required = true) Integer param) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// * page == 0 ? 1 : page; map.put("pageStart", (page - 1) * rows);
			map.put("typeid", param);
			if (title.length() > 0 && !"".equals(title)) {
				String tt = new String(title.getBytes("ISO-8859-1"), "UTF-8");
				map.put("title", tt);
			} else {
				String startTime = date + " 00:00:00";
				String endTime = date + " 23:59:00";
				map.put("startTime", startTime);
				map.put("endTime", endTime);
			}
			ListResult<Article> rs = caseBBSService.loadMessageListByType(map);
			return rs.toJson();
		} catch (Exception ex) {
			ListResult<Article> rs = new ListResult<Article>(0, null);
			return rs.toJson();
		}
	}

	@RequestMapping({ "/getMessageInfoById.do" })
	@ResponseBody
	public String getMessageInfoById(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			boolean isSuccess = false;
			Article atInfo = new Article();
			atInfo = caseBBSService.selectByPrimaryKey(id);
			if (atInfo != null) {
				isSuccess = true;
			}
			Result<Article> result = new Result<Article>(atInfo, isSuccess,
					"查询数据成功");
			return result.toJson();
		} catch (Exception ex) {
			Result<Article> result = new Result<Article>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/getMessageItemById.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMessageItemById(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			List<ArticleItem> list = new ArrayList<ArticleItem>();
			list = caseBBSService.loadMessageItemById(id);
			boolean isSuccess = true;
			ListResult<ArticleItem> result = new ListResult<ArticleItem>(list,
					isSuccess, "查询数据成功");
			return result.toJson();
		} catch (Exception ex) {
			ListResult<ArticleItem> result = new ListResult<ArticleItem>(null,
					false, ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/saveMessageItem.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveMessageItem(HttpServletRequest request, ArticleItem item) {
		try {
			boolean isSuccess = false;
			String msg = "";
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//
			// 设置日期格式
			// String createTime = df.format(new Date());
			item.setCreateTime(new Date());
			int itemId = 0;
			itemId = caseBBSService.insertItem(item);
			if (itemId > 0) {
				isSuccess = true;
				msg = "保存成功";
			}
			Result<ArticleItem> result = new Result<ArticleItem>(item,
					isSuccess, msg);
			return result.toJson();
		} catch (Exception ex) {
			Result<ArticleItem> result = new Result<ArticleItem>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/saveMessage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String saveMessage(HttpServletRequest request, Article message) {
		try {
			boolean isSuccess = false;
			String msg = "";
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
			String createTime = df.format(new Date());
			Integer data = Integer.parseInt(createTime);
			message.setIsused(true);
			message.setDateNo(data);
			message.setCreateTime(new Date());
			int itemId = 0;
			itemId = caseBBSService.insert(message);
			if (itemId > 0) {
				//message.setId(itemId);
				isSuccess = true;
				msg = "保存成功";
			}
			Result<Article> result = new Result<Article>(message, isSuccess,
					msg);
			return result.toJson();
		} catch (Exception ex) {
			Result<Article> result = new Result<Article>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/deleteMessage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMessage(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			boolean isSuccess = false;
			String msg = "";
			Article art = new Article();
			art = caseBBSService.selectByPrimaryKey(id);
			if (art != null) {
				art.setIsused(false);
				caseBBSService.updateByPrimaryKey(art);
				isSuccess = true;
				msg = "删除成功";
			}
			Result<Article> result = new Result<Article>(art, isSuccess, msg);
			return result.toJson();
		} catch (Exception ex) {
			Result<Article> result = new Result<Article>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/cancelHostMessage.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String cancelHostMessage(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			boolean isSuccess = false;
			String msg = "";
			Article art = new Article();
			art = caseBBSService.selectByPrimaryKey(id);
			if (art != null) {
				art.setIshost(false);
				caseBBSService.updateByPrimaryKey(art);
				isSuccess = true;
				msg = "取消置顶成功";
			}
			Result<Article> result = new Result<Article>(art, isSuccess, msg);
			return result.toJson();
		} catch (Exception ex) {
			Result<Article> result = new Result<Article>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/getMessageAttchs.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMessageAttchs(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer id) {
		List<ArticleAttchs> list = new ArrayList<ArticleAttchs>();
		try {
			if(id==null){
				ListResult<ArticleAttchs> result = new ListResult<ArticleAttchs>(list,
						true, "");
				return result.toJson();
			}else{
			list = caseBBSService.loadMessageAttchsById(id);
			boolean isSuccess = true;
			ListResult<ArticleAttchs> result = new ListResult<ArticleAttchs>(list,
					isSuccess, "查询数据成功");
			return result.toJson();
			}
		} catch (Exception ex) {
			ListResult<ArticleAttchs> result = new ListResult<ArticleAttchs>(list,
					false, ex.getMessage());
			return result.toJson();
		}
	}

	@RequestMapping(value = "/getMessageItemAttchs.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMessageItemAttchs(HttpServletRequest request,
			@RequestParam(value = "id", required = false) Integer id) {
		List<ArticleItemAttchs> list = new ArrayList<ArticleItemAttchs>();
		try {
			if(id==null){
				ListResult<ArticleItemAttchs> result = new ListResult<ArticleItemAttchs>(list,
						true, "");
				return result.toJson();
			}else{
			list = caseBBSService.loadMessageItemAttchsById(id);
			boolean isSuccess = true;
			ListResult<ArticleItemAttchs> result = new ListResult<ArticleItemAttchs>(list,
					isSuccess, "查询数据成功");
			return result.toJson();
			}
		} catch (Exception ex) {
			ListResult<ArticleItemAttchs> result = new ListResult<ArticleItemAttchs>(list,
					false, ex.getMessage());
			return result.toJson();
		}
	}
	@RequestMapping(value = "/deleteMessageAttch.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMessageAttch(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Integer id) {
		try {
			boolean isSuccess = false;
			String msg = "";
			//ArticleAttchs art = new ArticleAttchs();
			//art = caseBBSService.loadMessageAttchsInfoById(id);
			//if (art != null) { 
				caseBBSService.deleteAttchById(id);
				isSuccess = true;
				msg = "删除成功";
			//}
			Result<ArticleAttchs> result = new Result<ArticleAttchs>(null, isSuccess, msg);
			return result.toJson();
		} catch (Exception ex) {
			Result<ArticleAttchs> result = new Result<ArticleAttchs>(null, false,
					ex.getMessage());
			return result.toJson();
		}
	}
	
}
