package com.casebbs.dao;

import java.util.List;
import java.util.Map;

import com.casebbs.core.MyBatisRepository;
import com.casebbs.model.Article;
@MyBatisRepository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> loadMessageListByType(Map<String, Object> map);

	List<Article> loadMessageListByDate(Map<String, Object> map);
}