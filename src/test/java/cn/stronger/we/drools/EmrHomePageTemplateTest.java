package cn.stronger.we.drools;


import cn.stronger.we.commons.utils.JsonTools;
import cn.stronger.we.drools.core.DiscriminantModeEnums;
import cn.stronger.we.drools.core.check.FieldsCheckDTO;
import cn.stronger.we.drools.core.check.FieldsRuleCheckDTO;
import cn.stronger.we.drools.core.vo.EmrHomePageCheckVO;
import cn.stronger.we.drools.core.template.CommonDroolsRulesTemplate;
import cn.stronger.we.drools.core.template.EmrPageHomeDroolsRulesTemplate;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class EmrHomePageTemplateTest.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
public class EmrHomePageTemplateTest {

    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession("templateKieSession");
        // 设置全局变量，名称和类型必须和规则文件中定义的全局变量名称对应
        EmrPageHomeDroolsRulesTemplate template = getTemplate();
        kieSession.insert(template);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("test()执行结果:" + JsonTools.toJson(template.getResults()));
    }

    @Test
    public void commonTest() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession("templateKieSession");
        // 设置全局变量，名称和类型必须和规则文件中定义的全局变量名称对应
        CommonDroolsRulesTemplate<EmrHomePageCheckVO> template1 = getTemplate2();
        kieSession.insert(template1);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("commonTest()执行结果:" + JsonTools.toJson(template1.getResults()));
    }

    public EmrPageHomeDroolsRulesTemplate getTemplate() {
        return new EmrPageHomeDroolsRulesTemplate(getData(), getEqData(), getRules());
    }

    public CommonDroolsRulesTemplate<EmrHomePageCheckVO> getTemplate2() {
        return new CommonDroolsRulesTemplate<>(getData(), getEqData(), EmrHomePageCheckVO.class, getRules());
    }

    public List<FieldsCheckDTO> getRules() {
        List<FieldsCheckDTO> rules = new ArrayList<>();

        FieldsCheckDTO check = new FieldsCheckDTO();
        check.setField("paymentMethod");
        Map<String, FieldsRuleCheckDTO> map = new HashMap<>();
        FieldsRuleCheckDTO fieldsRuleCheckDTO = new FieldsRuleCheckDTO();
        fieldsRuleCheckDTO.setCheckField("paymentMethod");
        fieldsRuleCheckDTO.setCheckMode(DiscriminantModeEnums.EMPTY.getMode());
        fieldsRuleCheckDTO.setRuleCode("RULE0001");
        map.put(DiscriminantModeEnums.EMPTY.getMode(), fieldsRuleCheckDTO);
        check.setFieldsRuleCheckMap(map);
        rules.add(check);

        FieldsCheckDTO check2 = new FieldsCheckDTO();
        check2.setField("idCard");
        Map<String, FieldsRuleCheckDTO> map2 = new HashMap<>();
        FieldsRuleCheckDTO fieldsRuleCheckDTO2 = new FieldsRuleCheckDTO();
        fieldsRuleCheckDTO2.setCheckField("idCard");
        fieldsRuleCheckDTO2.setRuleCode("RULE0002");
        fieldsRuleCheckDTO2.setCheckMode(DiscriminantModeEnums.EQUALS.getMode());
        map2.put(DiscriminantModeEnums.EQUALS.getMode(), fieldsRuleCheckDTO2);

        FieldsRuleCheckDTO fieldsRuleCheckDTO3 = new FieldsRuleCheckDTO();
        fieldsRuleCheckDTO3.setCheckField("idCard");
        fieldsRuleCheckDTO3.setRuleCode("RULE0003");
        fieldsRuleCheckDTO3.setCheckMode(DiscriminantModeEnums.EMPTY.getMode());
        map2.put(DiscriminantModeEnums.EMPTY.getMode(), fieldsRuleCheckDTO3);

        FieldsRuleCheckDTO fieldsRuleCheckDTO4 = new FieldsRuleCheckDTO();
        fieldsRuleCheckDTO4.setRuleCode("RULE0004");
        fieldsRuleCheckDTO4.setCheckLength(20);
        fieldsRuleCheckDTO4.setCheckField("idCard");
        fieldsRuleCheckDTO4.setCheckMode(DiscriminantModeEnums.LENGTH.getMode());
        map2.put(DiscriminantModeEnums.LENGTH.getMode(), fieldsRuleCheckDTO4);

        check2.setFieldsRuleCheckMap(map2);
        rules.add(check2);
        return rules;
    }

    public EmrHomePageCheckVO getData() {
        EmrHomePageCheckVO dto = new EmrHomePageCheckVO();
        dto.setHomePageId("1");
        dto.setAddressCode("1000102");
        dto.setIdCard("12345678901234567");
        dto.setHealthCard("healthCard");
        dto.setPaymentMethod("PaymentMethod");
        return dto;
    }

    public EmrHomePageCheckVO getEqData() {
        EmrHomePageCheckVO dto = new EmrHomePageCheckVO();
        dto.setHomePageId("1");
        dto.setAddressCode("1000102");
        dto.setIdCard("12345678901234567822");
        dto.setHealthCard("healthCard222");
        dto.setPaymentMethod("PaymentMethod222");
        return dto;
    }
}
