package com.hzit.dao;

import com.hzit.pojo.SecKill;

import java.util.List;

public interface SecKillMapper {
    int deleteByPrimaryKey(Integer msId);

    int insert(SecKill record);

    int insertSelective(SecKill record);

    SecKill selectByPrimaryKey(Integer msId);

    int updateByPrimaryKeySelective(SecKill record);

    int updateByPrimaryKey(SecKill record);

    List<SecKill> selectByUnStarted();

    List<SecKill> selectByEnd();
}