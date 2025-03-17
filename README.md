# drools-rule

基于drools自定义配置规则引擎且实现数据库存储与动态刷新

## 表结构SQL

```mysql
    CREATE TABLE `drools_rules`
    (
        `id`               bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
        `rule_id`          bigint(20)   NOT NULL COMMENT '规则ID',
        `rule_name`        varchar(30)  NOT NULL COMMENT '规则名称',
        `rule_class`       varchar(10)  NOT NULL DEFAULT 'none' COMMENT '规则分类',
        `kie_base_name`    varchar(255) NOT NULL COMMENT 'kbaseName',
        `kie_package_name` varchar(255) NOT NULL COMMENT 'kbase目录名称',
        `rule_content`     mediumtext   NOT NULL COMMENT '规则内容(.drl代码内容)',
        `remark`           varchar(300) NOT NULL DEFAULT '' COMMENT '备注说明(规则说明)',
        `creator`          varchar(32)  NOT NULL DEFAULT '' COMMENT '创建者',
        `create_time`      datetime     NOT NULL COMMENT '创建时间',
        `updator`          varchar(32)  NOT NULL DEFAULT '' COMMENT '更新人',
        `update_time`      datetime     NOT NULL COMMENT '更新时间',
        `is_deleted`       tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否删除',
        PRIMARY KEY (`id`),
        UNIQUE KEY `idx_rule_id` (`rule_id`)
    ) ENGINE = InnoDB
      DEFAULT CHARSET = utf8mb4 COMMENT ='规则表';
```

## 测试示例

#### 新增规则
```shell
    curl --location 'http://127.0.0.1:9001/drools/rules' \
    --header 'Content-Type: application/json' \
    --data '{
        "ruleName": "rule_test",
        "ruleClass": "test",
        "kieBaseName": "rule_test",
        "kiePackageName": "test",
        "remark": "测试rules",
        "ruleContent": "package test\nglobal java.lang.StringBuilder rResult\nrule \"rule_test\"\nwhen\neval(true)\nthen\nSystem.out.println(\"规则：rule_test触发...\");\nrResult.append(\"rule_test\");\n\nend"
    }'
```

#### 更新规则
```shell
    curl --location --request PUT 'http://127.0.0.1:9001/drools/rules' \
    --header 'Content-Type: application/json' \
    --data '{
        "ruleId": 2503179001001,
        "ruleName": "rule_test",
        "ruleClass": "test",
        "kieBaseName": "rule_test",
        "kiePackageName": "test",
        "remark": "测试rules",
        "ruleContent": "package test\nglobal java.lang.StringBuilder rResult\nrule \"rule_test\"\nwhen\neval(true)\nthen\nSystem.out.println(\"规则：rule_test222222触发...\");\nrResult.append(\"rule_test22222\");\n\nend"
    }'
```

#### 执行规则
```shell
    curl --location 'http://127.0.0.1:9001/drools/core/run.do' \
    --header 'Content-Type: application/json' \
    --data '{
        "ruleId": 2503179001001
    }'
```
> 规则执行结果：
```json
    {
      "code": "0",
      "message": "success",
      "data": {
        "resultInfo": "rule_test22222"
      },
      "frameBizExecute": null,
      "frameRequestId": null
    }
```