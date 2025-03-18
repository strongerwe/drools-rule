package cn.stronger.we.drools.service;


import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunRequest;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunResponse;
import cn.stronger.we.drools.gateway.dto.DroolsRulesDTO;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DroolsRulesCoreService.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public interface DroolsRulesCoreService {

    /**
     * 添加或更新规则
     *
     * @param droolsRulesDTO droolsRulesDTO
     */
    void addOrUpdateRule(DroolsRulesDTO droolsRulesDTO);

    /**
     * 删除规则
     *
     * @param rule rule
     */
    void deleteDroolsRule(DroolsRulesDTO rule);

    /**
     * 执行规则
     *
     * @param request request
     * @return {@link RestResult }<{@link String }>
     */
    RestResult<DroolsRulesRunResponse> fireRule(DroolsRulesRunRequest request);
}
