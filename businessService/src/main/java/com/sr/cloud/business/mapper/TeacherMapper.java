package com.sr.cloud.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sr.cloud.business.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
    TeacherEntity queryById(Long id);
}
