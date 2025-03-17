package cn.stronger.we.drools.gateway.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DoorsRules.class
 * @department Platform R&D
 * @date 2025/3/14
 * @desc drools_rules表实体
 */
@Setter
@Getter
@TableName(value = "drools_rules")
public class DroolsRules implements Serializable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则分类
     */
    private String ruleClass;

    /**
     * kbaseName
     */
    private String kieBaseName;

    /**
     * kbase目录名称
     */
    private String kiePackageName;

    /**
     * 规则内容
     */
    private String ruleContent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updator;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean isDeleted;

}
