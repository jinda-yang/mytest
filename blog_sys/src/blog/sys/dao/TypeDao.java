package blog.sys.dao;

import blog.sys.pojo.Type;
import blog.sys.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 10:20
 */
public class TypeDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * 查询所有博客类别
     */
    public List<Type> findAll() throws SQLException {
        String sql = "select * from type";
        return queryRunner.query(sql,new BeanListHandler<>(Type.class));
    }

}
