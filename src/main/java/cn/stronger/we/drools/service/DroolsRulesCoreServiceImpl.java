package cn.stronger.we.drools.service;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.validator.ParamCheck;
import cn.stronger.we.drools.constants.DroolsResultErrCode;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunRequest;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunResponse;
import cn.stronger.we.drools.drools.DroolsManager;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesDTO;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesCoreServiceImpl.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Service
public class DroolsRulesCoreServiceImpl implements DroolsRulesCoreService {

    @Resource
    private DroolsManager droolsManager;

    @Resource
    private DroolsRulesGateway droolsRulesGateway;

    @Override
    public void addOrUpdateRule(DroolsRulesDTO droolsRulesDTO) {
        droolsManager.addOrUpdateRule(droolsRulesDTO);
    }

    @Override
    public RestResult<DroolsRulesRunResponse> fireRule(DroolsRulesRunRequest request) {
        DroolsRules byRuleId = droolsRulesGateway.getByRuleId(request.getRuleId());
        ParamCheck.notNull(byRuleId, DroolsResultErrCode.DROOLS_RULE_IS_EXISTS);
        DroolsRulesDTO droolsRulesDTO = DroolsRulesCvt.cvt(byRuleId);
        return droolsManager.fireRule(droolsRulesDTO);
    }
}
