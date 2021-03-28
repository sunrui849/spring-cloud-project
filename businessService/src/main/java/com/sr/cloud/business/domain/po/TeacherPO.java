package com.sr.cloud.business.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("teacher")// 映射数据库表名
public class TeacherPO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private Integer age;
    private String email;
}
