package cn.stronger.we.drools.core;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @enum ScoringTypeEnums.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc 计分类型
 */
@Getter
@AllArgsConstructor
public enum ScoringTypeEnums {
    /**
     * 单项否决：病历等级：丙
     */
    VETO(0, "veto", "单项否决"),
    /**
     * 分值扣减：扣减分值
     */
    SCORE(1, "score", "分值扣减");

    private final int type;
    private final String name;
    private final String desc;
}
