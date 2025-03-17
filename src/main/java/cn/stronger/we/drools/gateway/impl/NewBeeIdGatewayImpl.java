package cn.stronger.we.drools.gateway.impl;


import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.drools.constants.NewBeeIdBizCde;
import cn.stronger.we.drools.gateway.NewBeeIdGateway;
import cn.stronger.we.leaf.client.BambooLeafApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class NewBeeIdGatewayImpl.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Slf4j
@Component
public class NewBeeIdGatewayImpl implements NewBeeIdGateway {

    @Resource
    private BambooLeafApiClient bambooLeafApiClient;

    @Override
    public Long newRuleId() {
        return newLongId(NewBeeIdBizCde.DROOLS_RULE_ID.getIdBizCde());
    }

    private Long newLongId(String bizCode) {
        try {
            RestResult<String> stringRestResult = bambooLeafApiClient.newId(bizCode);
            return Long.parseLong(stringRestResult.getData());
        } catch (Exception e) {
            log.error("NewBeeIdGatewayImpl.newLongId()|{}|调用leaf获取编号出现异常", bizCode, e);
            throw new CustomException("NewBeeIdGatewayImpl.newLongId()|" + bizCode + "|调用leaf获取编号出现异常(编号规则不存在或规则配置格式错误：非Long类型结果返回)");
        }
    }

    private String newStringId(String bizCode) {
        try {
            RestResult<String> stringRestResult = bambooLeafApiClient.newId(bizCode);
            return stringRestResult.getData();
        } catch (Exception e) {
            log.error("NewBeeIdGatewayImpl.newStringId()|{}|调用leaf获取编号出现异常", bizCode, e);
            throw new CustomException("NewBeeIdGatewayImpl.newStringId()|" + bizCode + "|调用leaf获取编号出现异常(编号规则不存在或规则配置格式错误：非Long类型结果返回)");
        }
    }
}
