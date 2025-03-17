package cn.stronger.we.drools.service.cmd.rules;


import cn.hutool.core.collection.CollUtil;
import cn.stronger.we.commons.framework.AbstractApiExe;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesQueryRequest;
import cn.stronger.we.drools.controller.rest.rules.DroolsRulesView;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesPageQryExe.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Component
public class DroolsRulesPageQryExe extends AbstractApiExe<DroolsRulesQueryRequest, AdminPageResponse<DroolsRulesView>> {

    @Resource
    private DroolsRulesGateway droolsRulesGateway;

    @Override
    public RestResult<AdminPageResponse<DroolsRulesView>> execute(DroolsRulesQueryRequest request) {
        validate(request);
        PageHelper.startPage(request.getPageNum(), request.getPageSize(), request.getPage());
        DroolsRulesQry qry = DroolsRulesCvt.cvt(request);
        List<DroolsRules> droolsRulesList = droolsRulesGateway.list(qry);
        PageInfo<DroolsRules> page = new PageInfo<>(droolsRulesList);
        if (CollUtil.isEmpty(droolsRulesList)) {
            return RestResult.success(new AdminPageResponse<>(page, new ArrayList<>()));
        }
        List<DroolsRulesView> list = droolsRulesList.stream().map(DroolsRulesCvt::cvtPageView).collect(Collectors.toList());
        return RestResult.success(new AdminPageResponse<>(page, list));
    }

    @Override
    public void validate(DroolsRulesQueryRequest request) {
        request.validatePageSize();
    }
}
