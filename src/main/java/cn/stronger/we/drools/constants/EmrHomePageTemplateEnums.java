package cn.stronger.we.drools.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @enum DroolsRuleTemplateEnums.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Getter
@AllArgsConstructor
public enum EmrHomePageTemplateEnums {
    EMPTY_ID_CARD("empty", "idCard", 1),
    EQUALS_ID_CARD("equals", "idCard", 1),
    EMPTY_ADDRESS_CODE("empty", "addressCode", 1),
    EQUALS_ADDRESS_CODE("equals", "addressCode", 10),
    EMPTY_HEALTH_CARD("empty", "healthCard", 1),
    EQUALS_HEALTH_CARD("equals", "healthCard", 10),
    EMPTY_PAYMENT_METHOD("empty", "paymentMethod", 1),
    EQUALS_PAYMENT_METHOD("equals", "paymentMethod", 10),
    ;
    private final String type;
    private final String fields;
    private final Integer score;

    final static Map<String, Integer> MAP = new ConcurrentHashMap<>();

    static {
        Arrays.stream(EmrHomePageTemplateEnums.values()).forEach(item -> MAP.put(item.getType() + item.getFields(), item.getScore()));
    }

    public static Integer getScore(String type, String fields) {
        return MAP.get(type + fields);
    }

    public static List<String> fieldsList() {
        return Arrays.stream(EmrHomePageTemplateEnums.values()).map(EmrHomePageTemplateEnums::getFields).distinct().collect(Collectors.toList());
    }

    public static Set<String> emptyFieldsList() {
        return Arrays.stream(EmrHomePageTemplateEnums.values()).filter(item -> "empty".equals(item.getType())).map(EmrHomePageTemplateEnums::getFields).collect(Collectors.toSet());
    }

    public static Set<String> equalsFieldsList() {
        return Arrays.stream(EmrHomePageTemplateEnums.values()).filter(item -> "equals".equals(item.getType())).map(EmrHomePageTemplateEnums::getFields).collect(Collectors.toSet());
    }
}
