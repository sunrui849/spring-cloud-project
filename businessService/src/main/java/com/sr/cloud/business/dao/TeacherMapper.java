package com.sr.cloud.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sr.cloud.business.domain.po.TeacherPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<TeacherPO> {
    TeacherPO queryById(Long id);
}
