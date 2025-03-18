package cn.stronger.we.drools.gateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class FieldsRuleCheckQry.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FieldsRuleCheckQry implements Serializable {

    private String emrClass;

    private Boolean isEnabled;
}
