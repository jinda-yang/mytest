package blog.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客类型实体类
 * @author yangjinda
 * @datetime: 2024-08-17 09:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    /**
     * 类型id
     */
    private Integer tid;
    /**
     * 类型名称
     */
    private String tname;
}
