package com.springboot.mydemo.dao;

import com.springboot.mydemo.bean.ScheduleCron;
import com.springboot.mydemo.bean.ScheduleCronExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleCronMapper {
    long countByExample(ScheduleCronExample example);

    int deleteByExample(ScheduleCronExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleCron record);

    int insertSelective(ScheduleCron record);

    List<ScheduleCron> selectByExample(ScheduleCronExample example);

    ScheduleCron selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleCron record, @Param("example") ScheduleCronExample example);

    int updateByExample(@Param("record") ScheduleCron record, @Param("example") ScheduleCronExample example);

    int updateByPrimaryKeySelective(ScheduleCron record);

    int updateByPrimaryKey(ScheduleCron record);
}