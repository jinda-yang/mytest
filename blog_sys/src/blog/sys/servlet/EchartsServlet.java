package blog.sys.servlet;

import blog.sys.pojo.Num;
import blog.sys.service.BlogService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjinda
 * @datetime: 2024-08-20 16:33
 */
@WebServlet("/echarts")
public class EchartsServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Num> numList = blogService.queryCount();
        JSONArray jsonArray = JSONArray.fromObject(numList);
        resp.getWriter().write(jsonArray.toString());
    }
}
