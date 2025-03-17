package cn.stronger.we.drools.controller.rest.rules;


import cn.stronger.we.commons.framework.request.AbstractAdminRequest;
import cn.stronger.we.commons.validator.CusNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesAddRequest.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 新增规则请求入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DroolsRulesAddRequest extends AbstractAdminRequest<DroolsRulesAddResponse> {

    /**
     * 规则名称
     */
    @CusNotEmpty(message = "规则名称")
    private String ruleName;

    /**
     * 规则分类
     */
    @CusNotEmpty(message = "规则分类")
    private String ruleClass;

    /**
     * kbaseName
     */
    @CusNotEmpty(message = "kbase名称")
    private String kieBaseName;

    /**
     * kbase目录名称
     */
    @CusNotEmpty(message = "kbase目录名称")
    private String kiePackageName;

    /**
     * 规则内容
     */
    @CusNotEmpty(message = "规则内容")
    private String ruleContent;

    /**
     * 备注
     */
    private String remark;
}
