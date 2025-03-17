package cn.stronger.we.drools.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesDTO.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class DroolsRulesDTO implements Serializable {
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
}
