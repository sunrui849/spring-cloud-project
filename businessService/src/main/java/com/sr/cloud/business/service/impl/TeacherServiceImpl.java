package com.sr.cloud.business.service.impl;

import com.sr.cloud.business.dao.TeacherMapper;
import com.sr.cloud.business.domain.po.TeacherPO;
import com.sr.cloud.business.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public TeacherPO queryById(long l) {
        return teacherMapper.queryById(l);
    }

    @Override
    public TeacherPO queryById2(long l) {
        return teacherMapper.selectById(l);
    }
}
