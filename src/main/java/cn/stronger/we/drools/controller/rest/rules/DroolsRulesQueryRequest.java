package cn.stronger.we.drools.controller.rest.rules;


import cn.stronger.we.commons.framework.request.AbstractAdminPageRequest;
import cn.stronger.we.commons.framework.request.AbstractAdminRequest;
import cn.stronger.we.commons.framework.response.AdminPageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsRulesQueryRequest.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 规则查询请求入参
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DroolsRulesQueryRequest extends AbstractAdminPageRequest<AdminPageResponse<DroolsRulesView>> {

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则分类
     */
    private String ruleClass;

    /**
     * kbaseName
     */
    private String kieBaseName;

    /**
     * kbase目录名称
     */
    private String kiePackageName;

    @Override
    public Boolean getPage() {
        return Boolean.TRUE;
    }
}
