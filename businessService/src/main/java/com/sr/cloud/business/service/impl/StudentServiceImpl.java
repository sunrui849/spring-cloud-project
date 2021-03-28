package com.sr.cloud.business.service.impl;

import com.sr.cloud.business.entity.StudentEntity;
import com.sr.cloud.business.mapper.StudentDao;
import com.sr.cloud.business.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sr
 * @since 2021-03-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {

}
