package cn.stronger.we.drools.service.cmd.rules;

import cn.stronger.we.commons.framework.AbstractApiExe;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.drools.constants.DroolsResultErrCode;
import cn.stronger.we.drools.constants.LockTemplates;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesCmdResponse;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesDelRequest;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.DroolsRulesCoreService;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import cn.stronger.we.redis.lock.CusRedissonLock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesDelCmdExe.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Component
public class DroolsRulesDelCmdExe extends AbstractApiExe<DroolsRulesDelRequest, DroolsRulesCmdResponse> {

    @Resource
    private DroolsRulesGateway droolsRulesGateway;
    @Resource
    private DroolsRulesCoreService droolsRulesCoreService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CusRedissonLock(keyTemplate = LockTemplates.DEL_RULE_LOCK, suffix = "#request.ruleId")
    public RestResult<DroolsRulesCmdResponse> execute(DroolsRulesDelRequest request) {
        DroolsRules rules = droolsRulesGateway.getByRuleId(request.getRuleId());
        ParamCheck.notNull(rules, DroolsResultErrCode.DROOLS_RULE_IS_EXISTS);
        // 移除规则
        droolsRulesCoreService.deleteDroolsRule(DroolsRulesCvt.cvt(rules));
        rules.setRuleName(rules.getRuleName() + "_Del");
        rules.setKieBaseName(rules.getKieBaseName() + "_Del");
        rules.setIsDeleted(Boolean.TRUE);
        rules.setUpdator(request.getLoginUserId());
        rules.setUpdateTime(new Date());
        droolsRulesGateway.updateById(rules);
        droolsRulesGateway.removeById(rules.getId());
        return RestResult.success(new DroolsRulesCmdResponse(rules.getRuleId()));
    }

    @Override
    public void validate(DroolsRulesDelRequest request) {

    }
}
