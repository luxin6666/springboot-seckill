package com.hzit.dao;

import com.hzit.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}