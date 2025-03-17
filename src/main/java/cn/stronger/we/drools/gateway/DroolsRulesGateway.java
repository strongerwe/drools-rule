package cn.stronger.we.drools.gateway;

import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DoorsRulesGateway.class
 * @department Platform R&D
 * @date 2025/3/14
 * @desc do what?
 */
public interface DroolsRulesGateway extends IService<DroolsRules> {

    /**
     * 查询规则列表
     *
     * @param qry qry
     * @return {@link List }<{@link DroolsRules }>
     */
    List<DroolsRules> list(DroolsRulesQry qry);

    /**
     * 根据规则ID获取规则信息
     *
     * @param ruleId ruleId
     * @return {@link DroolsRules }
     */
    DroolsRules getByRuleId(Long ruleId);

    /**
     * lambdaQueryWrapper
     *
     * @return {@link LambdaQueryWrapper}<{@link DroolsRules}>
     */
    default LambdaQueryWrapper<DroolsRules> lambdaQueryWrapper() {
        return new QueryWrapper<DroolsRules>().lambda();
    }
}
