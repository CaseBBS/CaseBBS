package com.casebbs.service;

import java.util.List;
import java.util.Map;

import com.casebbs.core.ListResult;
import com.casebbs.model.Article;
import com.casebbs.model.ArticleAttchs;
import com.casebbs.model.ArticleItem;

public interface CaseBBSService {

	ListResult<Article> loadMessageListByType(Map<String, Object> map);

	List<Article> loadMessageListByDate(Map<String, Object> map);

	Article selectByPrimaryKey(Integer id);

	List<ArticleItem> loadMessageItemById(Integer id);

	int insert(Article item);
	int insertItem(ArticleItem item);

	void updateByPrimaryKey(Article art);

	List<ArticleAttchs> loadMessageAttchsById(Integer id);

	void insertAttchMents(ArticleAttchs attch);

	void deleteAttchById(Integer id);

}
