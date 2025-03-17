package cn.stronger.we.drools.controller;


import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunRequest;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunResponse;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesEditRequest;
import cn.stronger.we.drools.service.DroolsRulesCoreService;
import cn.stronger.we.logs.MethodCustomLog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsCoreController.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@RestController
@RequestMapping(value = "drools/core")
public class DroolsCoreController {

    @Resource
    private DroolsRulesCoreService droolsRulesCoreService;

    @PostMapping(value = "/run.do")
    @MethodCustomLog(msg = "规则执行")
    public RestResult<DroolsRulesRunResponse> run(@RequestBody @Validated DroolsRulesRunRequest request){
        return droolsRulesCoreService.fireRule(request);
    }
}
