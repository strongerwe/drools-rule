package cn.stronger.we.drools.drools.rule.template;


import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.constants.EmrHomePageTemplateEnums;
import cn.stronger.we.drools.drools.rule.DroolsRulesTemplate;
import cn.stronger.we.drools.drools.rule.dto.EmrHomePageDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrHomePageTemplate.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmrHomePageTemplate implements DroolsRulesTemplate<EmrHomePageDTO> {

    private Integer fen;
    private Integer size;
    private String checkFields;
    private EmrHomePageDTO data;
    private EmrHomePageDTO eqData;
    private List<String> checkFieldsList;
    private Set<String> emptyFieldsList;
    private Set<String> eqFieldsList;
//    private List<String> lFieldsList;
//    private List<String> cFieldsList;

    public EmrHomePageTemplate(EmrHomePageDTO data,
                               EmrHomePageDTO eqData,
                               List<String> checkFieldsList,
                               Set<String> emptyFieldsList,
                               Set<String> eqFieldsList) {
        this.fen = 0;
        this.size = 0;
        this.data = data;
        this.eqData = eqData;
        this.checkFieldsList = checkFieldsList;
        this.emptyFieldsList = emptyFieldsList;
        this.eqFieldsList = eqFieldsList;
        this.checkFields = checkFieldsList.get(this.size);
    }


    @Override
    public Class<EmrHomePageDTO> getClazz() {
        return EmrHomePageDTO.class;
    }

    @Override
    public void addSource(String type) {
        fen += EmrHomePageTemplateEnums.getScore(type, getCheckFields());
    }

    @Override
    public void resetFields() {
        setCheckFields(checkFieldsList.get(size));
    }

    @Override
    public boolean checkSize() {
        size++;
        return size < checkFieldsList.size();
    }

    @Override
    public boolean isEnd() {
        return size < checkFieldsList.size();
    }

    @Override
    public boolean checkEmpty() {
        String checkData = getDataByFieldsName(getCheckFields(), getData());
        boolean b = emptyFieldsList.contains(getCheckFields()) && StringTools.isNotEmpty(checkData);
        log.debug("checkEmpty()|[{}]|empty?[{}]|checkResult:{}{}", getCheckFields(), checkData, b, b ? "|此项加分:" + EmrHomePageTemplateEnums.getScore("empty", getCheckFields()) : "|此项不加分！");
        return b;
    }

    @Override
    public boolean checkEquals() {
        String checkData = getDataByFieldsName(getCheckFields(), getData());
        String checkEqData = getDataByFieldsName(getCheckFields(), getEqData());
        boolean b = eqFieldsList.contains(getCheckFields()) && StringTools.isNotEmpty(checkData) && StringTools.isNotEmpty(checkEqData) && checkData.equals(checkEqData);
        log.debug("checkEquals()|[{}]|equals?[{}:::{}]|checkResult:{}{}", getCheckFields(), checkData, checkEqData, b, b ? "|此项加分:" + EmrHomePageTemplateEnums.getScore("equals", getCheckFields()) : "|此项不加分!");
        return b;
    }

    @Override
    public boolean checkLength() {
        // TODO 字段对比录入
//        String checkData = getDataByFieldsName(fieldsName, getData());
//        return StringTools.isNotEmpty(checkData) && checkData.length() >= length;
        return false;
    }

    @Override
    public boolean checkTimeout() {
        // TODO 校验时效
        return false;
    }

    @Override
    public boolean checkCustom() {
        // TODO 自定义校验
        return false;
    }

    @Override
    public Integer getScore() {
        return this.fen;
    }
}
