package cn.stronger.we.drools.core.template;

import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.core.DiscriminantModeEnums;
import cn.stronger.we.drools.core.check.FieldsCheckDTO;
import cn.stronger.we.drools.core.check.FieldsRuleCheckDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrDroolsEmrRulesTemplate.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Slf4j
@Getter
public class CommonDroolsRulesTemplate<T> implements DroolsRulesTemplate<T> {

    private final T data;
    private final T eqData;
    private final Class<T> clazz;
    private final List<FieldsCheckDTO> rules;
    private final List<FieldsRuleCheckDTO> results = new ArrayList<>();
    private Integer runSize = 0;

    public CommonDroolsRulesTemplate(final T data,
                                     final T eqData,
                                     final Class<T> clazz,
                                     final List<FieldsCheckDTO> rules) {
        this.data = data;
        this.eqData = eqData;
        this.clazz = clazz;
        this.rules = rules;
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public FieldsCheckDTO runningCheck() {
        return rules.get(runSize);
    }

    @Override
    public boolean checkEmpty() {
        FieldsRuleCheckDTO fieldsRuleCheckDTO = runningCheck().getFieldsRuleCheckMap().get(DiscriminantModeEnums.EMPTY.getMode());
        if (null == fieldsRuleCheckDTO) {
            // 没有规则直接不参与计算返回false
            return false;
        }
        String checkFieldData = getDataByFieldsName(data);
        boolean notEmpty = StringTools.isNotEmpty(checkFieldData);
        log.debug("checkEmpty()|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), notEmpty, notEmpty ? "校验通过" : "校验不通过");
        // 非真：是因为扣分操作
        return !notEmpty;
    }

    @Override
    public boolean checkEquals() {
        FieldsRuleCheckDTO fieldsRuleCheckDTO = runningCheck().getFieldsRuleCheckMap().get(DiscriminantModeEnums.EQUALS.getMode());
        if (null == fieldsRuleCheckDTO) {
            // 没有规则直接不参与计算返回false
            return false;
        }
        String checkFieldData = getDataByFieldsName(data);
        String checkEqFieldData = getDataByFieldsName(eqData);
        boolean doEquals = StringTools.isNotEmpty(checkFieldData) && StringTools.isNotEmpty(checkEqFieldData) && checkFieldData.equals(checkEqFieldData);
        log.debug("checkEquals()|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), doEquals, doEquals ? "校验通过" : "校验不通过");
        // 非真：是因为扣分操作
        return !doEquals;
    }

    @Override
    public boolean checkLength() {
        FieldsRuleCheckDTO fieldsRuleCheckDTO = runningCheck().getFieldsRuleCheckMap().get(DiscriminantModeEnums.LENGTH.getMode());
        if (null == fieldsRuleCheckDTO) {
            // 没有规则直接不参与计算返回false
            return false;
        }
        String checkFieldData = getDataByFieldsName(data);
        boolean checkLength = StringTools.isNotEmpty(checkFieldData) && checkFieldData.length() > fieldsRuleCheckDTO.getCheckLength();
        log.debug("checkLength()|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), checkLength, checkLength ? "校验通过" : "校验不通过");
        return !checkLength;
    }

    @Override
    public boolean checkTime() {
        // TODO 校验时效
        return false;
    }

    @Override
    public boolean checkEnd() {
        if (runSize < rules.size() - 1) {
            runSize++;
            return true;
        }
        return false;
    }

    @Override
    public void processing(String type) {
        this.results.add(runningCheck().getFieldsRuleCheckMap().get(type));
    }
}
