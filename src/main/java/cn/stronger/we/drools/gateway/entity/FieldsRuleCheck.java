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
 * @class FieldsRuleCheck.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc 字段规则校验表
 */
@Setter
@Getter
@TableName(value = "fields_rule_check")
public class FieldsRuleCheck implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 病历类型
     */
    private String emrClass;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 缺陷类别
     */
    private String defectClass;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 校验字段
     */
    private String checkField;

    /**
     * 判别方式
     */
    private String checkMode;

    /**
     * 校验长度
     */
    private Integer checkLength;

    /**
     * 单项否决
     */
    private String checkLevel;

    /**
     * 规则分值
     */
    private Long checkScore;

    /**
     * 判决类型(0.单项否决;1.扣减分值)
     */
    private Integer scoringType;

    /**
     * 规则说明
     */
    private String ruleRemark;

    /**
     * 缺陷提醒消息
     */
    private String warnMsg;

    /**
     * 排序编号
     */
    private Integer sortNum;

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
    private String updater;

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
