package cn.stronger.we.drools.gateway;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @interface NewBeeIdGateway.class
 * @department Platform R&D
 * @date 2025/3/17
 * @desc id生成接口
 */
public interface NewBeeIdGateway {

    /**
     * 生成规则ID
     *
     * @return {@link Long }
     */
    Long newRuleId();
}
