package blog.sys.service;

import blog.sys.dao.BlogDao;
import blog.sys.pojo.Blog;
import blog.sys.pojo.BlogVo;
import blog.sys.pojo.Num;
import blog.sys.pojo.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 09:37
 */
public class BlogService {
    BlogDao blogDao = new BlogDao();

    /**
     * 查询博客列表（不包含已删除的），并进行分页处理
     * @param pageNum 页码，如果为空，默认为1
     * @param pageSize 每页的条数
     * @return 分页对象
     */
    public PageBean<BlogVo> findAll(int pageNum,int pageSize) {
        try {
            PageBean<BlogVo> pageBean = new PageBean<>();
            pageBean.setPageNum(pageNum);
            pageBean.setPageSize(pageSize);
            int total = blogDao.queryTotal();
            pageBean.setTotal(total);
            List<BlogVo> beanList = blogDao.findAll(pageNum,pageSize);
            pageBean.setBeanList(beanList);
            return pageBean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询博客总编辑次数
     */
    public int querySum() {
        try {
            return blogDao.querySum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据博客id查询博客信息
     * @param bid 博客id
     * @return 博客信息
     */
    public BlogVo queryByBid(int bid) {
        try {
            return blogDao.queryByBid(bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改博客
     * @param blog 要修改的博客对象
     */
    public void update(Blog blog) {
        try {
            blogDao.update(blog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据博客id删除博客
     * @param bid 博客id
     */
    public void delete(int bid) {
        try {
            blogDao.updateDeleted(bid,1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有已删除的博客信息，分页显示
     * @param pageNum 页码
     * @param pageSize 每页的记录数
     * @return 分页对象
     */
    public PageBean<BlogVo> findRecycle(int pageNum,int pageSize) {
        try {
            PageBean<BlogVo> pageBean = new PageBean<>();
            pageBean.setPageNum(pageNum);
            pageBean.setPageSize(pageSize);
            int total = blogDao.findRecycleTotal();
            pageBean.setTotal(total);
            List<BlogVo> beanList = blogDao.findRecycle(pageNum,pageSize);
            pageBean.setBeanList(beanList);
            return pageBean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据博客id恢复博客信息
     * @param bid 博客id
     */
    public void recovery(int bid) {
        try {
            blogDao.updateDeleted(bid,0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加博客信息
     * @param blog 要添加的博客对象
     */
    public void add(Blog blog) {
        try {
            blogDao.add(blog);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Num> queryCount() {
        try {
            return blogDao.queryCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
