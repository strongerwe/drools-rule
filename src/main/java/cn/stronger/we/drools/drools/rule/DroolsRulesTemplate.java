package cn.stronger.we.drools.drools.rule;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DroolsRulesTemplate.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc 规则校验模版
 */
public interface DroolsRulesTemplate<T> {

    T getData();

    T getEqData();

    Class<T> getClazz();

    /**
     * 加分
     *
     * @param type type
     */
    void addSource(String type);

    /**
     * 更新比对字段
     */
    void resetFields();

    /**
     * 校验size
     *
     * @return boolean
     */
    boolean checkSize();

    /**
     * 是否结束
     *
     * @return boolean
     */
    boolean isEnd();

    /**
     * 空值校验
     *
     * @return boolean
     */
    boolean checkEmpty();

    /**
     * 一致性校验
     *
     * @return boolean
     */
    boolean checkEquals();

    /**
     * 长度校验
     *
     * @return boolean
     */
    boolean checkLength();

    /**
     * 失效校验
     *
     * @return boolean
     */
    boolean checkTimeout();

    /**
     * 自定义校验
     *
     * @return boolean
     */
    boolean checkCustom();

    /**
     * 获取得分
     *
     * @return {@link Integer }
     */
    Integer getScore();

    /**
     * 根据字段名获取数据
     *
     * @param fieldsName fieldsName
     * @param data       data
     * @return {@link String }
     */
    default String getDataByFieldsName(String fieldsName, T data) {
        try {
            Field field = ReflectionUtils.findField(getClazz(), fieldsName);
            assert field != null;
            ReflectionUtils.makeAccessible(field);
            return (String) field.get(data);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
