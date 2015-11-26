package com.casebbs.dao;

import java.util.List;

import com.casebbs.core.MyBatisRepository;
import com.casebbs.model.ArticleItemAttchs;
@MyBatisRepository
public interface ArticleItemAttchsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleItemAttchs record);

    int insertSelective(ArticleItemAttchs record);

    ArticleItemAttchs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleItemAttchs record);

    int updateByPrimaryKey(ArticleItemAttchs record);

	List<ArticleItemAttchs> loadMessageItemAttchsById(Integer id);
}