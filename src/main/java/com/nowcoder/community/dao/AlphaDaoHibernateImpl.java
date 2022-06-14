package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * 下面bean的默认名字是类AlphaDaoHibernateImpl的首字母小写：alphaDaoHibernateImpl
 * 注解@Repository("alphaDaoHibernate")就是给bean改名成：alphaDaoHibernate
 * @author QiaoWeiBo
 * @date 2022/6/14 12:13 PM
 */

@Repository("alphaDaoHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
