package com.casebbs.dao;

import java.util.List;

import com.casebbs.core.MyBatisRepository;
import com.casebbs.model.ArticleItem;
@MyBatisRepository
public interface ArticleItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleItem record);

    int insertSelective(ArticleItem record);

    ArticleItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleItem record);

    int updateByPrimaryKey(ArticleItem record);

	List<ArticleItem> loadMessageItemById(Integer id);
}