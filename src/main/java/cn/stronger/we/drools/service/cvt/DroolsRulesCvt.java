package cn.stronger.we.drools.service.cvt;


import cn.stronger.we.drools.controller.rest.rules.DroolsRulesAddRequest;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesEditRequest;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesQueryRequest;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesView;
import cn.stronger.we.drools.gateway.dto.DroolsRulesDTO;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;

import java.util.Date;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesCvt.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public class DroolsRulesCvt {

    public static DroolsRulesQry cvt(DroolsRulesQueryRequest request) {
        DroolsRulesQry qry = new DroolsRulesQry();
        qry.setRuleName(request.getRuleName());
        qry.setRuleClass(request.getRuleClass());
        qry.setKieBaseName(request.getKieBaseName());
        qry.setKiePackageName(request.getKiePackageName());
        qry.setRuleId(request.getRuleId());
        return qry;
    }

    public static DroolsRulesDTO cvt(DroolsRules droolsRules) {
        return DroolsRulesDTO.builder()
                .ruleId(droolsRules.getRuleId())
                .ruleName(droolsRules.getRuleName())
                .ruleClass(droolsRules.getRuleClass())
                .kieBaseName(droolsRules.getKieBaseName())
                .kiePackageName(droolsRules.getKiePackageName())
                .ruleContent(droolsRules.getRuleContent()).build();
    }

    public static DroolsRulesView cvtPageView(DroolsRules droolsRules){
        DroolsRulesView view = new DroolsRulesView();
        view.setRemark(droolsRules.getRemark());
        view.setRuleClass(droolsRules.getRuleClass());
        view.setRuleContent(droolsRules.getRuleContent());
        view.setRuleName(droolsRules.getRuleName());
        view.setRuleId(droolsRules.getRuleId());
        view.setKieBaseName(droolsRules.getKieBaseName());
        view.setKiePackageName(droolsRules.getKiePackageName());
        return view;
    }

    public static DroolsRulesDTO cvt(DroolsRulesAddRequest request) {
        DroolsRulesDTO droolsRulesDTO = new DroolsRulesDTO();
        droolsRulesDTO.setRuleClass(request.getRuleClass());
        droolsRulesDTO.setRuleContent(request.getRuleContent());
        droolsRulesDTO.setRuleName(request.getRuleName());
        droolsRulesDTO.setKieBaseName(request.getKieBaseName());
        droolsRulesDTO.setKiePackageName(request.getKiePackageName());
        return droolsRulesDTO;
    }

    public static DroolsRules cvtEntity(DroolsRulesAddRequest request){
        DroolsRules droolsRules = new DroolsRules();
        droolsRules.setRuleClass(request.getRuleClass());
        droolsRules.setRuleContent(request.getRuleContent());
        droolsRules.setRuleName(request.getRuleName());
        droolsRules.setKieBaseName(request.getKieBaseName());
        droolsRules.setKiePackageName(request.getKiePackageName());
        droolsRules.setRemark(request.getRemark());
        droolsRules.setCreator(request.getLoginUserId());
        droolsRules.setUpdator(request.getLoginUserId());
        droolsRules.setCreateTime(new Date());
        droolsRules.setUpdateTime(new Date());
        return droolsRules;
    }

    public static DroolsRulesDTO cvt(DroolsRulesEditRequest request) {
        DroolsRulesDTO droolsRulesDTO = new DroolsRulesDTO();
        droolsRulesDTO.setRuleClass(request.getRuleClass());
        droolsRulesDTO.setRuleContent(request.getRuleContent());
        droolsRulesDTO.setRuleName(request.getRuleName());
        droolsRulesDTO.setKieBaseName(request.getKieBaseName());
        droolsRulesDTO.setKiePackageName(request.getKiePackageName());
        droolsRulesDTO.setRuleId(request.getRuleId());
        return droolsRulesDTO;
    }
}
