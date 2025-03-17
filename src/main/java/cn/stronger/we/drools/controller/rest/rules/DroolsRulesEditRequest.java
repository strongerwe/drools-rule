package cn.stronger.we.drools.controller.rest.rules;


import cn.stronger.we.commons.validator.CusNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesEditRequest.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 更新规则请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DroolsRulesEditRequest extends DroolsRulesAddRequest {

    /**
     * 规则ID
     */
    @CusNotEmpty(message = "规则ID")
    private Long ruleId;
}
