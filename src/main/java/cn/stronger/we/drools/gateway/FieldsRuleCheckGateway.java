package cn.stronger.we.drools.gateway;

import cn.stronger.we.drools.gateway.dto.FieldsRuleCheckQry;
import cn.stronger.we.drools.gateway.entity.FieldsRuleCheck;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface FieldsRuleCheckGateway.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc 查询字段规则列表
 */
public interface FieldsRuleCheckGateway extends IService<FieldsRuleCheck> {

    /**
     * 根据QRY查询List
     *
     * @param qry qry
     * @return {@link List }<{@link FieldsRuleCheck }>
     */
    List<FieldsRuleCheck> list(FieldsRuleCheckQry qry);

    /**
     * lambdaQueryWrapper
     *
     * @return {@link LambdaQueryWrapper}<{@link FieldsRuleCheck}>
     */
    default LambdaQueryWrapper<FieldsRuleCheck> lambdaQueryWrapper() {
        return new QueryWrapper<FieldsRuleCheck>().lambda();
    }
}
