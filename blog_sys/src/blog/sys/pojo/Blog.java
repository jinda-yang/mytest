package blog.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客实体类
 * @author yangjinda
 * @datetime: 2024-08-17 09:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    /**
     * 博客id
     */
    private Integer bid;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 打开次数
     */
    private int count;
    /**
     * 博客类型id
     */
    private Integer tid;
    /**
     * 是否删除，1已删除；0未删除
     */
    private int deleted;
}
