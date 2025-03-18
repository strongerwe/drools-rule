package cn.stronger.we.drools.core.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrHomePageCheckVO.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EmrHomePageCheckVO implements Serializable {
    /**
     * 首页ID
     */
    private String homePageId;
    /**
     * 病历记录ID
     */
    private String recordId;
    /**
     * 患者住院ID
     */
    private String inpatientId;
    private String paymentMethod;
    private String healthCard;
    private String name;
    private String genderCode;
    private String age;
    private String birthplaceCode;
    private String nativeCode;
    private String ethnicCode;
    private String idCard;
    private String jobCode;
    private String maritalCode;
    private String addressCode;
    private String phone;
    private String addressPostcode;
    private String householdCode;
    private String householdPostcode;
    private String companyCode;
    private String companyPhone;
    private String companyPostcode;
    private String contactName;
    private String contactRelationCode;
    private String contactPlace;
    private String contactPhone;
    private String selfContactName;
    private String admissionWay;
}
