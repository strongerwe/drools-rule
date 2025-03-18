package cn.stronger.we.drools.core;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @enum DiscriminantModeEnums.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc 判别方式
 */
@Getter
@AllArgsConstructor
public enum DiscriminantModeEnums {
    EMPTY("empty", "缺项控制", "缺项控制"),
    EQUALS("equals", "一致控制", "一致控制"),
    LENGTH("length", "长度控制", "长度控制"),
    TIME("time", "时间控制", "时间控制"),
    RECORDS("records", "记录控制", "记录控制"),
    DRL("drl", "DRL规则", "DRL规则"),
    SQL("sql", "SQL脚本", "SQL脚本");
    private final String mode;
    private final String name;
    private final String desc;
}
