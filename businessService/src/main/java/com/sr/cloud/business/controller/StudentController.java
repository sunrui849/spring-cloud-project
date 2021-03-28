package com.sr.cloud.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sr.cloud.business.entity.StudentEntity;
import com.sr.cloud.business.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sr
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @RequestMapping("/01")
    public void test01(){
        System.out.println(service.getById(3));;

        QueryWrapper<StudentEntity> wrapper = new QueryWrapper();
        wrapper.like("user_name", "2");
        List<StudentEntity> list = service.list(wrapper);
        System.out.println(list);

        IPage<StudentEntity> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        IPage<StudentEntity> page2 = service.page(page);
        System.out.println(page2);

    }
}
