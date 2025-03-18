package cn.stronger.we.drools.service.cmd.rules;


import cn.stronger.we.commons.framework.AbstractApiExe;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.drools.constants.DroolsResultErrCode;
import cn.stronger.we.drools.constants.LockTemplates;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesCmdResponse;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesEditRequest;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.DroolsRulesCoreService;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import cn.stronger.we.redis.lock.CusRedissonLock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesEditCmdExe.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Component
public class DroolsRulesEditCmdExe extends AbstractApiExe<DroolsRulesEditRequest, DroolsRulesCmdResponse> {
    @Resource
    private DroolsRulesGateway droolsRulesGateway;
    @Resource
    private DroolsRulesCoreService droolsRulesCoreService;

    @Override
    @CusRedissonLock(keyTemplate = LockTemplates.EDIT_RULE_LOCK, suffix = "#request.ruleName")
    @Transactional(rollbackFor = Exception.class)
    public RestResult<DroolsRulesCmdResponse> execute(DroolsRulesEditRequest request) {
        DroolsRules rules = droolsRulesGateway.getByRuleId(request.getRuleId());
        validate(request, rules);
        setFields(request, rules);
        droolsRulesGateway.updateById(rules);
        droolsRulesCoreService.addOrUpdateRule(DroolsRulesCvt.cvt(rules));
        return RestResult.success(new DroolsRulesCmdResponse(rules.getRuleId()));
    }

    public void setFields(DroolsRulesEditRequest request, DroolsRules rules) {
        rules.setUpdator(request.getLoginUserId());
        rules.setUpdateTime(new Date());
        rules.setRemark(request.getRemark());
        rules.setRuleName(request.getRuleName());
        rules.setRuleContent(request.getRuleContent());
        rules.setKieBaseName(request.getKieBaseName());
        rules.setKiePackageName(request.getKiePackageName());
    }

    public void validate(DroolsRulesEditRequest request, DroolsRules rules) {
        ParamCheck.notNull(rules, DroolsResultErrCode.DROOLS_RULE_IS_EXISTS);
        // ruleName是否已存在
        DroolsRulesQry droolsRulesQry = new DroolsRulesQry();
        droolsRulesQry.setId(rules.getId());
        droolsRulesQry.setRuleName(request.getRuleName());
        List<DroolsRules> exists = droolsRulesGateway.list(droolsRulesQry);
        ParamCheck.isEmpty(exists, DroolsResultErrCode.DROOLS_RULE_NAME_IS_EXISTS);
        // kieBaseName是否已存在
        droolsRulesQry = new DroolsRulesQry();
        droolsRulesQry.setId(rules.getId());
        droolsRulesQry.setKieBaseName(request.getKieBaseName());
        ParamCheck.isEmpty(exists, DroolsResultErrCode.DROOLS_RULE_KIE_NAME_IS_EXISTS);
    }

    @Override
    public void validate(DroolsRulesEditRequest request) {

    }
}
