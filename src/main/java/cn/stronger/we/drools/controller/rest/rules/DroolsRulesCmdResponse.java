package cn.stronger.we.drools.controller.rest.rules;


import cn.stronger.we.commons.framework.response.SuperResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesAddResponse.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 操作response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DroolsRulesCmdResponse implements SuperResponse {
    /**
     * 创建成功返回：规则ID
     */
    private Long ruleId;
}
