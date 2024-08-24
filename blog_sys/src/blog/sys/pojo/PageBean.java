package blog.sys.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 14:06
 */
@Data
public class PageBean<T> {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int total;
    /**
     * 当前页的数据
     */
    private List<T> beanList;

    /**
     * 总页数
     */
    public int getPageCount() {
        return total%pageSize==0 ? total/pageSize : total/pageSize+1;
    }
}
