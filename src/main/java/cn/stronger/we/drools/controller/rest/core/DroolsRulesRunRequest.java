package cn.stronger.we.drools.controller.rest.core;


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
 * @class DroolsRulesRunRequest.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DroolsRulesRunRequest extends AbstractAdminRequest<DroolsRulesRunResponse> {

    /**
     * 创建成功返回：规则ID
     */
    @CusNotEmpty(message = "规则ID")
    private Long ruleId;
}
