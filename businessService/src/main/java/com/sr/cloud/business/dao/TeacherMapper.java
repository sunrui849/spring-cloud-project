package com.sr.cloud.business.dao;

import com.sr.cloud.business.domain.po.TeacherPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherPO queryById(Long id);
}
