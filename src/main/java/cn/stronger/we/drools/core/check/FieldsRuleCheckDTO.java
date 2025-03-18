package cn.stronger.we.drools.core.check;

import cn.stronger.we.drools.core.DiscriminantModeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class FieldsRuleCheck.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FieldsRuleCheckDTO implements Serializable {

    /**
     * 校验字段
     */
    private String checkField;

    /**
     * 校验长度LENGTH("length", "长度控制", "长度控制")时必填
     */
    private Integer checkLength;

    /**
     * 判别方式
     */
    private String checkMode;

    /**
     * 校验编码
     */
    private String ruleCode;

}
