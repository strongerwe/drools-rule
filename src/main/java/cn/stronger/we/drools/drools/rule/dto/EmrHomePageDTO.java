package cn.stronger.we.drools.drools.rule.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrHomePageDTO.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EmrHomePageDTO implements Serializable {

    /**
     * 患者首页ID
     */
    private Long pageId;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 患者ID
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 性别
     */
    private String genderCode;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 健康卡号
     */
    private String healthCard;

    /**
     * 医疗付款方式
     */
    private String paymentMethod;

    /**
     * 出生地
     */
    private String birthplaceCode;

    /**
     * 籍贯
     */
    private String nativeCode;

    /**
     * 民族
     */
    private String ethnicCode;

    /**
     * 职业
     */
    private String jobCode;

    /**
     * 婚姻状况
     */
    private String maritalCode;

    /**
     * 手机号
     */
    private String phone;
    /**
     * 现居住地址
     */
    private String addressCode;

    /**
     * 地址邮编号码
     */
    private String addressPostcode;

    /**
     * 户口所在地
     */
    private String householdCode;

    /**
     * 户口所在地邮编号码
     */
    private String householdPostcode;
}
