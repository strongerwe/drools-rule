package cn.stronger.we.drools.core.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class FieldsCheck.class
 * @department Platform R&D
 * @date 2025/3/18
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FieldsCheckDTO implements Serializable {
    private String field;
    private Map<String, FieldsRuleCheckDTO> fieldsRuleCheckMap;
}
