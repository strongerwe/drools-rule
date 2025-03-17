package cn.stronger.we.drools.service;

import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import cn.stronger.we.drools.controller.rest.rules.*;
import cn.stronger.we.drools.service.cmd.rules.DroolsRulesAddCmdExe;
import cn.stronger.we.drools.service.cmd.rules.DroolsRulesEditCmdExe;
import cn.stronger.we.drools.service.cmd.rules.DroolsRulesPageQryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesServiceImpl.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Service
public class DroolsRulesServiceImpl implements DroolsRulesService {

    @Resource
    private DroolsRulesAddCmdExe droolsRulesAddCmdExe;
    @Resource
    private DroolsRulesEditCmdExe droolsRulesEditCmdExe;
    @Resource
    private DroolsRulesPageQryExe droolsRulesPageQryExe;

    @Override
    public RestResult<AdminPageResponse<DroolsRulesView>> page(DroolsRulesQueryRequest request) {
        return droolsRulesPageQryExe.execute(request);
    }

    @Override
    public RestResult<DroolsRulesAddResponse> add(DroolsRulesAddRequest request) {
        return droolsRulesAddCmdExe.execute(request);
    }

    @Override
    public RestResult<DroolsRulesAddResponse> edit(DroolsRulesEditRequest request) {
        return droolsRulesEditCmdExe.execute(request);
    }

}
