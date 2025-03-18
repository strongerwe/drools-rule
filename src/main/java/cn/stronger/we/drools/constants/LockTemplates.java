package cn.stronger.we.drools.constants;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface LockTemplates.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public interface LockTemplates {

    String ADD_RULE_LOCK="lock:rules:add:{ruleName}";
    String EDIT_RULE_LOCK="lock:rules:edit:{ruleName}";
    String DEL_RULE_LOCK="lock:rules:del:{ruleName}";
}
