package cn.stronger.we.drools.gateway.impl;

import cn.stronger.we.commons.exception.CustomException;
import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.gateway.FieldsRuleCheckGateway;
import cn.stronger.we.drools.gateway.dto.FieldsRuleCheckQry;
import cn.stronger.we.drools.gateway.entity.FieldsRuleCheck;
import cn.stronger.we.drools.gateway.mapper.FieldsRuleCheckMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class FieldsRuleCheckGatewayImpl.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc FieldsRuleCheckGatewayImpl
 */
@Component
public class FieldsRuleCheckGatewayImpl extends ServiceImpl<FieldsRuleCheckMapper, FieldsRuleCheck> implements FieldsRuleCheckGateway {

    @Override
    public List<FieldsRuleCheck> list(FieldsRuleCheckQry qry) {
        if (null == qry) {
            throw new CustomException("FieldsRuleCheckGatewayImpl.list|查询参数不能为空!");
        }
        LambdaQueryWrapper<FieldsRuleCheck> lambdaQueryWrapper = lambdaQueryWrapper()
                .eq(StringTools.isNotEmpty(qry.getEmrClass()), FieldsRuleCheck::getEmrClass, qry.getEmrClass())
                .eq(FieldsRuleCheck::getIsEnabled, qry.getIsEnabled());
        lambdaQueryWrapper.orderByAsc(FieldsRuleCheck::getSortNum);
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
