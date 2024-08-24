package blog.sys.dao;

import blog.sys.pojo.Blog;
import blog.sys.pojo.BlogVo;
import blog.sys.pojo.Num;
import blog.sys.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 09:38
 */
public class BlogDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 分页查询所有博客信息（不包含已删除的）
     * @param pageNum 页码
     * @param pageSize 每页的记录数
     * @return 当前页的博客信息集合
     */
    public List<BlogVo> findAll(int pageNum,int pageSize) throws SQLException {
        String sql = "select b.bid,b.title,b.content,b.count,b.tid,b.deleted,t.tname " +
                "from blog b,type t where b.tid=t.tid and b.deleted=0 " +
                "order by b.count desc,b.bid limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(BlogVo.class),(pageNum-1)*pageSize,pageSize);
    }

    /**
     * 查询总编辑次数
     */
    public int querySum() throws SQLException {
        String sql = "select sum(count) from blog where deleted=0";
        Number o = queryRunner.query(sql, new ScalarHandler<>());
        if (o == null) {
            return 0;
        }
        return o.intValue();
    }

    /**
     * 根据博客id查询博客信息
     * @param bid 博客id
     * @return 博客信息
     */
    public BlogVo queryByBid(int bid) throws SQLException {
        String sql = "select b.bid,b.title,b.content,b.count,b.tid,b.deleted,t.tname " +
                "from blog b inner join type t on b.tid=t.tid " +
                "where b.bid=?";
        return queryRunner.query(sql,new BeanHandler<>(BlogVo.class),bid);
    }

    /**
     * 修改博客信息
     * @param blog 要修改的博客信息
     */
    public void update(Blog blog) throws SQLException {
        String sql = "update blog set title=?,content=?,count=?,tid=?,deleted=0 where bid=?";
        Object[] params = {blog.getTitle(),blog.getContent(),blog.getCount(),blog.getTid(),blog.getBid()};
        queryRunner.update(sql,params);
    }

    /**
     * 根据博客id逻辑删除博客信息
     * @param bid 博客id
     * @param deleted 0未删除，1已删除
     */
    public void updateDeleted(int bid,int deleted) throws SQLException {
        String sql = "update blog set deleted=? where bid=?";
        queryRunner.update(sql,deleted,bid);
    }

    /**
     * 分页查询所有博客信息，包含已删除的
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return 每页的博客信息集合
     */
    public List<BlogVo> findRecycle(int pageNum,int pageSize) throws SQLException {
        String sql = "select b.bid,b.title,b.content,b.count,b.tid,b.deleted,t.tname " +
                "from blog b,type t where b.tid=t.tid and b.deleted=1 " +
                "order by deleted desc,b.count desc,b.bid limit ?,?";
        return queryRunner.query(sql, new BeanListHandler<>(BlogVo.class),(pageNum-1)*pageSize,pageSize);
    }

    /**
     * 添加博客信息
     * @param blog 要添加的博客信息
     */
    public void add(Blog blog) throws SQLException {
        String sql = "insert into blog values(null,?,?,?,?,?)";
        Object[] params = {blog.getTitle(),blog.getContent(),blog.getCount(),blog.getTid(),blog.getDeleted()};
        queryRunner.update(sql,params);

    }

    /**
     * 查询未删除的博客总数
     */
    public int queryTotal() throws SQLException {
        String sql = "select count(*) from blog where deleted=0";
        Number o = queryRunner.query(sql, new ScalarHandler<>());
        if (o != null) {
            return o.intValue();
        }
        return 0;
    }

    /**
     * 查询博客总数，包含已删除的
     */
    public int findRecycleTotal() throws SQLException {
        String sql = "select count(*) from blog where deleted=1";
        Number o = queryRunner.query(sql, new ScalarHandler<>());
        if (o != null) {
            return o.intValue();
        }
        return 0;
    }

    public List<Num> queryCount() throws SQLException {
        String sql = "select t.tname name,count(*) num " +
                "from blog b inner join type t on b.tid=t.tid " +
                "where b.deleted=0 " +
                "group by t.tid";
        return queryRunner.query(sql,new BeanListHandler<>(Num.class));
    }
}
