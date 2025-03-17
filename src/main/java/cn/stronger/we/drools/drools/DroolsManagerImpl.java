package cn.stronger.we.drools.drools;


import cn.stronger.we.commons.framework.RestResult;
import cn.stronger.we.commons.utils.StringTools;
import cn.stronger.we.drools.controller.rest.core.DroolsRulesRunResponse;
import cn.stronger.we.drools.gateway.dto.DroolsRulesDTO;
import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.drools.compiler.kie.builder.impl.KieContainerImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class DroolsManagerImpl.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc do what?
 */
@Component
@Slf4j
public class DroolsManagerImpl implements DroolsManager {

    // 此类本身就是单例的
    private final KieServices kieServices = KieServices.get();
    // kie文件系统，需要缓存，如果每次添加规则都是重新new一个的话，则可能出现问题。即之前加到文件系统中的规则没有了
    private final KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    // 可以理解为构建 kmodule.xml
    private final KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
    // 需要全局唯一一个，如果每次加个规则都新创建一个，那么旧需要销毁之前创建的kieContainer，如果此时有正在使用的KieSession，则可能有问题
    private KieContainer kieContainer;

    @Override
    public void addOrUpdateRule(DroolsRulesDTO rule) {
        // 获取kbase的名称
        String kieBaseName = rule.getKieBaseName();
        // 判断该kbase是否存在
        boolean existsKieBase = checkExistsKieBase(kieBaseName);
        // 该对象对应kmodule.xml中的kbase标签
        KieBaseModel kieBaseModel = null;
        if (!existsKieBase) {
            // 创建一个kbase
            kieBaseModel = kieModuleModel.newKieBaseModel(kieBaseName);
            // 不是默认的kieBase
            kieBaseModel.setDefault(false);
            // 设置该KieBase需要加载的包路径
            kieBaseModel.addPackage(rule.getKiePackageName());
            // 设置kieSession
            kieBaseModel.newKieSessionModel(kieBaseName + "-session")
                    // 不是默认session
                    .setDefault(false);
        } else {
            // 获取到已经存在的kbase对象
            kieBaseModel = kieModuleModel.getKieBaseModels().get(kieBaseName);
            // 获取到packages
            List<String> packages = kieBaseModel.getPackages();
            if (!packages.contains(rule.getKiePackageName())) {
                kieBaseModel.addPackage(rule.getKiePackageName());
                log.info("DroolsManagerImpl.addOrUpdateRule|kieBase:{}|ruleId:{}|添加一个新的包:{}", kieBaseName, rule.getRuleId(), rule.getKiePackageName());
            } else {
                kieBaseModel = null;
            }
        }
        String file = "src/main/resources/" + rule.getKiePackageName() + "/" + rule.getRuleId() + ".drl";
        log.debug("DroolsManagerImpl.addOrUpdateRule|加载虚拟规则文件:{}", file);
        kieFileSystem.write(file, rule.getRuleContent());
        if (kieBaseModel != null) {
            String kmoduleXml = kieModuleModel.toXML();
            log.debug("DroolsManagerImpl.addOrUpdateRule|加载kmodule.xml:[\n{}]", kmoduleXml);
            kieFileSystem.writeKModuleXML(kmoduleXml);
        }
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        // 通过KieBuilder构建KieModule下所有的KieBase
        kieBuilder.buildAll();
        // 获取构建过程中的结果
        Results results = kieBuilder.getResults();
        // 获取错误信息
        List<Message> messages = results.getMessages(Message.Level.ERROR);
        if (null != messages && !messages.isEmpty()) {
            for (Message message : messages) {
                log.error(message.getText());
            }
            throw new RuntimeException("加载规则出现异常");
        }
        // KieContainer只有第一次时才需要创建，之后就是使用这个
        if (null == kieContainer) {
            kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        } else {
            // 实现动态更新
            ((KieContainerImpl) kieContainer).updateToKieModule((InternalKieModule) kieBuilder.getKieModule());
        }
    }

    @Override
    public boolean checkExistsKieBase(String kieBaseName) {
        if (null == kieContainer) {
            return false;
        }
        if (StringTools.isEmpty(kieBaseName)) {
            return false;
        }
        Collection<String> kieBaseNames = kieContainer.getKieBaseNames();
        if (kieBaseNames.contains(kieBaseName)) {
            return true;
        }
        log.debug("DroolsManagerImpl.checkExistsKieBase|KieBase[{}]不存在！需要创建KieBase", kieBaseName);
        return false;
    }

    @Override
    public void deleteDroolsRule(DroolsRulesDTO rule) {
        if (null == rule) {
            throw new IllegalArgumentException("DroolsManagerImpl.deleteDroolsRule|入参参数[rule]为空！");
        }
        if (checkExistsKieBase(rule.getKieBaseName())) {
            KieBase kieBase = kieContainer.getKieBase(rule.getKieBaseName());
            kieBase.removeRule(rule.getKiePackageName(), rule.getRuleName());
            log.info("DroolsManagerImpl.deleteDroolsRule|删除kieBase:[{}]包:[{}]下的规则:[{}]|ruleId:{}", rule.getKieBaseName(), rule.getKiePackageName(), rule.getRuleName(), rule.getRuleId());
        }
    }

    @Override
    public RestResult<DroolsRulesRunResponse> fireRule(DroolsRulesDTO rule) {
        KieSession kieSession = kieContainer.newKieSession(rule.getKieBaseName() + "-session");
        StringBuilder result = new StringBuilder();
        kieSession.setGlobal("rResult", result);
        kieSession.fireAllRules();
        kieSession.dispose();
        return RestResult.success(new DroolsRulesRunResponse(result.toString()));
    }
}
