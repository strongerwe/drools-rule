package cn.stronger.we.drools.drools;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunResponse;
import cn.stronger.we.drools.gateway.dto.DroolsRulesDTO;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DroolsManager.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public interface DroolsManager {

    /**
     * 添加/更新规则
     *
     * @param rule rule
     */
    void addOrUpdateRule(DroolsRulesDTO rule);

    /**
     * 校验规则
     *
     * @param kieBaseName kieBaseName
     */
    boolean checkExistsKieBase(String kieBaseName);

    /**
     * 删除规则
     *
     * @param rule rule
     */
    void deleteDroolsRule(DroolsRulesDTO rule);

    /**
     * 执行规则
     *
     * @param rule rule
     * @return {@link RestResult }<{@link String }>
     */
    RestResult<DroolsRulesRunResponse> fireRule(DroolsRulesDTO rule);
}
