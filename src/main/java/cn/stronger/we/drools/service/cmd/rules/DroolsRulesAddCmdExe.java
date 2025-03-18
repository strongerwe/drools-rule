package cn.stronger.we.drools.service.cmd.rules;

import cn.stronger.we.commons.framework.AbstractApiExe;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.drools.constants.DroolsResultErrCode;
import cn.stronger.we.drools.constants.LockTemplates;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesAddRequest;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesCmdResponse;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.NewBeeIdGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.DroolsRulesCoreService;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import cn.stronger.we.redis.lock.CusRedissonLock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesAddCmdExe.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Component
public class DroolsRulesAddCmdExe extends AbstractApiExe<DroolsRulesAddRequest, DroolsRulesCmdResponse> {

    @Resource
    private DroolsRulesGateway droolsRulesGateway;
    @Resource
    private NewBeeIdGateway newBeeIdGateway;
    @Resource
    private DroolsRulesCoreService droolsRulesCoreService;

    @Override
    @CusRedissonLock(keyTemplate = LockTemplates.ADD_RULE_LOCK, suffix = "#request.ruleName")
    @Transactional(rollbackFor = Exception.class)
    public RestResult<DroolsRulesCmdResponse> execute(DroolsRulesAddRequest request) {
        validate(request);
        DroolsRules droolsRules = DroolsRulesCvt.cvtEntity(request);
        Long ruleId = newBeeIdGateway.newRuleId();
        droolsRules.setRuleId(ruleId);
        droolsRulesGateway.save(droolsRules);
        droolsRulesCoreService.addOrUpdateRule(DroolsRulesCvt.cvt(droolsRules));
        return RestResult.success(new DroolsRulesCmdResponse(ruleId));
    }

    @Override
    public void validate(DroolsRulesAddRequest request) {
        // ruleName是否已存在
        DroolsRulesQry droolsRulesQry = new DroolsRulesQry();
        droolsRulesQry.setRuleName(request.getRuleName());
        List<DroolsRules> exists = droolsRulesGateway.list(droolsRulesQry);
        ParamCheck.isEmpty(exists, DroolsResultErrCode.DROOLS_RULE_NAME_IS_EXISTS);
        // kieBaseName是否已存在
        droolsRulesQry = new DroolsRulesQry();
        droolsRulesQry.setKieBaseName(request.getKieBaseName());
        ParamCheck.isEmpty(exists, DroolsResultErrCode.DROOLS_RULE_KIE_NAME_IS_EXISTS);
    }
}
