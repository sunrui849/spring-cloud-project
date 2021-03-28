package com.sr.cloud.business.service;

import com.sr.cloud.business.domain.po.TeacherPO;

public interface TeacherService {
    TeacherPO queryById(long l);

    TeacherPO queryById2(long l);
}
