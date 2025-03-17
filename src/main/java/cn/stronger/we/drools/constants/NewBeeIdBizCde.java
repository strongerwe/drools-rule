package cn.stronger.we.drools.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @enum NewBeeIdBizCde.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 编号规则枚举（用到leaf叫号器的）
 */
@AllArgsConstructor
@Getter
public enum NewBeeIdBizCde {
    /**
     * 规则ID
     */
    DROOLS_RULE_ID("规则ID", "drools-rules", "规则ID（Long类型）");

    private final String idRule;
    private final String idBizCde;
    private final String desc;
}
