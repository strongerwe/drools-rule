package cn.stronger.we.drools.service;


import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import cn.stronger.we.drools.controller.rest.rules.*;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DroolsRulesService.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public interface DroolsRulesService {

    RestResult<AdminPageResponse<DroolsRulesView>> page(DroolsRulesQueryRequest request);

    RestResult<DroolsRulesAddResponse> add(DroolsRulesAddRequest request);

    RestResult<DroolsRulesAddResponse> edit(DroolsRulesEditRequest request);

    default Long newRuleId(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[4];
        secureRandom.nextBytes(bytes);
        return Long.parseLong(new BigInteger(bytes).toString(16), 16);
    }
}
