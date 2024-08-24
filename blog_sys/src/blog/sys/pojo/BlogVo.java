package blog.sys.pojo;

import lombok.Data;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 09:29
 */
@Data
public class BlogVo {
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
    private String count;
    /**
     * 博客类型id
     */
    private Integer tid;
    /**
     * 是否删除，1已删除；0未删除
     */
    private int deleted;
    /**
     * 类型名称
     */
    private String tname;
}
