package com.casebbs.dao;

import com.casebbs.core.MyBatisRepository;
import com.casebbs.model.ArticleType;
@MyBatisRepository
public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}