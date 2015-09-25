package com.casebbs.dao;

import java.util.List;

import com.casebbs.core.MyBatisRepository;
import com.casebbs.model.ArticleAttchs;
@MyBatisRepository
public interface ArticleAttchsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleAttchs record);

    int insertSelective(ArticleAttchs record);

    ArticleAttchs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleAttchs record);

    int updateByPrimaryKey(ArticleAttchs record);

	List<ArticleAttchs> loadMessageAttchsById(Integer id);
}