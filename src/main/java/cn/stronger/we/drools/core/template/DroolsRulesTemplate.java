package cn.stronger.we.drools.core.template;

import cn.stronger.we.drools.core.check.FieldsCheckDTO;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface DroolsEmrRulesTemplate.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc 病历规则模版
 */
public interface DroolsRulesTemplate<T> {

    /**
     * 获取校验数据
     *
     * @return {@link T }
     */
    T getData();

    /**
     * 获取比对数据
     *
     * @return {@link T }
     */
    T getEqData();

    /**
     * 获取Clazz()
     *
     * @return {@link Class }<{@link T }>
     */
    Class<T> getClazz();

    /**
     * 获取当前运行的check
     *
     * @return {@link FieldsCheckDTO }
     */
    FieldsCheckDTO runningCheck();

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
    boolean checkTime();

    /**
     * 校验是否结束规则
     *
     * @return boolean
     */
    boolean checkEnd();

    /**
     * 结果处理
     *
     * @param type type
     */
    void processing(String type);

    /**
     * 根据字段名获取数据
     *
     * @param data data
     * @return {@link String }
     */
    default String getDataByFieldsName(T data) {
        try {
            Field field = ReflectionUtils.findField(this.getClazz(), this.runningCheck().getField());
            assert field != null;
            ReflectionUtils.makeAccessible(field);
            return (String) field.get(data);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
