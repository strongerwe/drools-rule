package cn.stronger.we.drools.core.template;

import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.core.DiscriminantModeEnums;
import cn.stronger.we.drools.core.check.FieldsCheckDTO;
import cn.stronger.we.drools.core.check.FieldsRuleCheckDTO;
import cn.stronger.we.drools.core.vo.EmrHomePageCheckVO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrPageHomeDroolsEmrRulesTemplate.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Slf4j
@Getter
public class EmrPageHomeDroolsRulesTemplate implements DroolsRulesTemplate<EmrHomePageCheckVO> {

    private final EmrHomePageCheckVO data;
    private final EmrHomePageCheckVO eqData;
    private final List<FieldsCheckDTO> rules;
    private final List<FieldsRuleCheckDTO> results = new ArrayList<>();
    private static Integer RUN_SIZE = 0;

    public EmrPageHomeDroolsRulesTemplate(final EmrHomePageCheckVO data,
                                          final EmrHomePageCheckVO eqData,
                                          final List<FieldsCheckDTO> rules) {
        this.data = data;
        this.eqData = eqData;
        this.rules = rules;
    }

    @Override
    public Class<EmrHomePageCheckVO> getClazz() {
        return EmrHomePageCheckVO.class;
    }

    @Override
    public FieldsCheckDTO runningCheck() {
        return rules.get(RUN_SIZE);
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
        log.debug("checkEmpty()|病案首页ID:{}|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                data.getHomePageId(), runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), notEmpty, notEmpty ? "校验通过" : "校验不通过");
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
        log.debug("checkEquals()|病案首页ID:{}|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                data.getHomePageId(), runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), doEquals, doEquals ? "校验通过" : "校验不通过");
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
        log.debug("checkLength()|病案首页ID:{}|规则字段[{}]|规则编码[{}]|checkResult:[{}]{}",
                data.getHomePageId(), runningCheck().getField(), fieldsRuleCheckDTO.getRuleCode(), checkLength, checkLength ? "校验通过" : "校验不通过");
        return !checkLength;
    }

    @Override
    public boolean checkTime() {
        // TODO 校验时效
        return false;
    }

    @Override
    public boolean checkEnd() {
        if (RUN_SIZE < rules.size() - 1) {
            RUN_SIZE++;
            return true;
        }
        return false;
    }

    @Override
    public void processing(String type) {
        this.results.add(runningCheck().getFieldsRuleCheckMap().get(type));
    }
}
