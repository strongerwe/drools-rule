package cn.stronger.we.drools;


import cn.stronger.we.drools.constants.EmrHomePageTemplateEnums;
import cn.stronger.we.drools.drools.rule.dto.EmrHomePageDTO;
import cn.stronger.we.drools.drools.rule.template.EmrHomePageTemplate;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.List;

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
        EmrHomePageDTO dto = new EmrHomePageDTO();
        dto.setPageId(1L);
        dto.setAddressCode("1000102");
        dto.setIdCard("12345678901234567822");
        dto.setHealthCard("healthCard");
        dto.setPaymentMethod("healthCard");

        EmrHomePageDTO eDto = new EmrHomePageDTO();
        eDto.setPageId(1L);
        eDto.setAddressCode("1000102");
        eDto.setIdCard("12345678901234567822");
        eDto.setHealthCard("healthCard2");
        eDto.setPaymentMethod("healthCard2");
        EmrHomePageTemplate emrHomePageTemplate = new EmrHomePageTemplate(dto, eDto, EmrHomePageTemplateEnums.fieldsList(), EmrHomePageTemplateEnums.emptyFieldsList(), EmrHomePageTemplateEnums.equalsFieldsList());


        KieSession kieSession = kieClasspathContainer.newKieSession("defaultKieSession");
        // 设置全局变量，名称和类型必须和规则文件中定义的全局变量名称对应
        kieSession.insert(emrHomePageTemplate);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println("执行结果|最终得分为: " + emrHomePageTemplate.getFen());
    }
}
