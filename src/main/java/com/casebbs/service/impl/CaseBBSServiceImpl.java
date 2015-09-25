package com.casebbs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

import com.casebbs.core.ListResult;
import com.casebbs.dao.ArticleAttchsMapper;
import com.casebbs.dao.ArticleItemMapper;
import com.casebbs.dao.ArticleMapper;
import com.casebbs.model.Article;
import com.casebbs.model.ArticleAttchs;
import com.casebbs.model.ArticleItem;
import com.casebbs.service.CaseBBSService;
@Service
public class CaseBBSServiceImpl  implements CaseBBSService {

	@Autowired ArticleMapper articleMapper;
	@Autowired ArticleItemMapper articleItemMapper;
	@Autowired ArticleAttchsMapper articleAttchsMapper;

	public ListResult<Article> loadMessageListByType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Article> ls =  articleMapper.loadMessageListByType(map);
		int total = ls.size();
		ListResult<Article> list = new ListResult<Article>(total,ls);
		return list;
	}  
	public List<Article> loadMessageListByDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return articleMapper.loadMessageListByDate(map);
	}
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}
	public List<ArticleItem> loadMessageItemById(Integer id) {
		// TODO Auto-generated method stub
		return articleItemMapper.loadMessageItemById(id);
	}
	public int insert(Article item) {
		// TODO Auto-generated method stub
		return articleMapper.insert(item);
	}
	public int insertItem(ArticleItem item) {
		// TODO Auto-generated method stub
		return articleItemMapper.insert(item);
	}
	public void updateByPrimaryKey(Article record) {
		// TODO Auto-generated method stub
		articleMapper.updateByPrimaryKey(record);
	}
	public List<ArticleAttchs> loadMessageAttchsById(Integer id) {
		// TODO Auto-generated method stub
		return articleAttchsMapper.loadMessageAttchsById(id);
	}
	public void insertAttchMents(ArticleAttchs attch) {
		// TODO Auto-generated method stub
		articleAttchsMapper.insert(attch);
	}
	public void deleteAttchById(Integer id) {
		// TODO Auto-generated method stub
		articleAttchsMapper.deleteByPrimaryKey(id);
	}
}
