package cn.stronger.we.drools.drools;


import cn.hutool.core.collection.CollUtil;
import cn.stronger.we.drools.gateway.DroolsRulesGateway;
import cn.stronger.we.drools.gateway.dto.DroolsRulesQry;
import cn.stronger.we.drools.gateway.entity.DroolsRules;
import cn.stronger.we.drools.service.cvt.DroolsRulesCvt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsConfig.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Slf4j
@Configuration
public class DroolsConfiguration {

    @Resource
    private DroolsRulesGateway droolsRulesGateway;

    @Bean
    public DroolsManager beanDroolsManager() {
        DroolsManagerImpl droolsManager = new DroolsManagerImpl();
        List<DroolsRules> list = droolsRulesGateway.list(new DroolsRulesQry());
        if (CollUtil.isNotEmpty(list)) {
            log.info(">>>>>>>> new DroolsManager()|初始化Drools规则{}条!", list.size());
            list.forEach(i -> droolsManager.addOrUpdateRule(DroolsRulesCvt.cvt(i)));
        } else {
            log.warn(">>>>>>>> new DroolsManager()|初始化Drools规则未查询到启用规则，请检查初始化规则是否存在!");
        }
        return droolsManager;
    }
}
