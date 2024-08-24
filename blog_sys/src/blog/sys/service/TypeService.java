package blog.sys.service;

import blog.sys.dao.TypeDao;
import blog.sys.pojo.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-17 10:19
 */
public class TypeService {
    TypeDao typeDao = new TypeDao();

    /**
     * 查询所有博客类别
     */
    public List<Type> findAll() {
        try {
            return typeDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
