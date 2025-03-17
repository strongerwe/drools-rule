package cn.stronger.we.drools.controller;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import cn.stronger.we.drools.controller.rest.rules.*;
import cn.stronger.we.drools.service.DroolsRulesService;
import cn.stronger.we.logs.MethodCustomLog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRuleController.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@RestController
@RequestMapping(value = "drools/rules")
public class DroolsRuleController {

    @Resource
    private DroolsRulesService droolsRulesService;

    @GetMapping("/page")
    @MethodCustomLog(msg = "列表查询规则")
    public RestResult<AdminPageResponse<DroolsRulesView>> page(@Validated DroolsRulesQueryRequest request) {
        return droolsRulesService.page(request);
    }

    @PostMapping
    @MethodCustomLog(msg = "新增规则")
    public RestResult<DroolsRulesAddResponse> add(@RequestBody @Validated DroolsRulesAddRequest request) {
        return droolsRulesService.add(request);
    }

    @PutMapping
    @MethodCustomLog(msg = "更新规则")
    public RestResult<DroolsRulesAddResponse> edit(@RequestBody @Validated DroolsRulesEditRequest request) {
        return droolsRulesService.edit(request);
    }
}
