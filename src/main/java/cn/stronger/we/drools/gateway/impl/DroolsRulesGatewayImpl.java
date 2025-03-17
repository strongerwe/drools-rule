package cn.stronger.we.drools.gateway.impl;


import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.gateway.mapper.DroolsRulesMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DoorsRulesGatewayImpl.class
 * @department Platform R&D
 * @date 2025/3/15
 * @desc DoorsGatewayImpl
 */
@Component
public class DroolsRulesGatewayImpl extends ServiceImpl<DroolsRulesMapper, DroolsRules> implements DroolsRulesGateway {

    @Override
    public List<DroolsRules> list(DroolsRulesQry query) {
        if (null == query) {
            throw new CustomException("DroolsRulesGatewayImpl.list|查询参数不能为空!");
        }
        LambdaQueryWrapper<DroolsRules> lambdaQueryWrapper = lambdaQueryWrapper()
                .ne(null != query.getId(), DroolsRules::getId, query.getId())
                .eq(null != query.getRuleId(), DroolsRules::getRuleId, query.getRuleId())
                .eq(StringTools.isNotEmpty(query.getRuleClass()), DroolsRules::getRuleClass, query.getRuleClass())
                .eq(StringTools.isNotEmpty(query.getKieBaseName()), DroolsRules::getKieBaseName, query.getKieBaseName())
                .eq(StringTools.isNotEmpty(query.getKiePackageName()), DroolsRules::getKiePackageName, query.getKiePackageName());
        if (StringTools.isNotEmpty(query.getRuleName())) {
            lambdaQueryWrapper.nested(wrapper -> wrapper
                    .likeRight(DroolsRules::getRuleName, query.getRuleName()));
        }
        lambdaQueryWrapper
                .orderByDesc(DroolsRules::getUpdateTime)
                .orderByDesc(DroolsRules::getCreateTime)
                .orderByAsc(DroolsRules::getRuleId);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public DroolsRules getByRuleId(Long ruleId) {
        if (null == ruleId) {
            throw new CustomException("DroolsRulesGatewayImpl.getByRuleId|查询参数不能为空!");
        }
        LambdaQueryWrapper<DroolsRules> lambdaQueryWrapper = lambdaQueryWrapper().eq(DroolsRules::getRuleId, ruleId);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }
}
