package cn.stronger.we.drools.constants;


import cn.stronger.we.commons.framework.ResultErrCodeI;
import lombok.AllArgsConstructor;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @enum DroolsResultErrCode.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@AllArgsConstructor
public enum DroolsResultErrCode implements ResultErrCodeI {
    DROOLS_RULE_NOT_RUNNING("drools-4000","规则不在运行区，请检查规则Id是否正确！"),
    DROOLS_RULE_NOT_EXISTS("drools-40001", "规则不存在"),
    DROOLS_RULE_NAME_IS_EXISTS("drools-40002", "规则名称已存在，请修改后重新提交！"),
    DROOLS_RULE_KIE_NAME_IS_EXISTS("drools-40003", "规则[kieBaseName]已存在，请修改后重新提交！"),
    DROOLS_RULE_IS_EXISTS("drools-40004", "规则不存在，请重新输入！"),

    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
