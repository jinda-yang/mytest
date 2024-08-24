package blog.sys.servlet;

import blog.sys.pojo.Blog;
import blog.sys.pojo.Type;
import blog.sys.service.BlogService;
import blog.sys.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 添加博客
 * @author yangjinda
 * @datetime: 2024-08-17 11:45
 */
@WebServlet("/AddBlogServlet")
public class AddBlogServlet extends HttpServlet {
    TypeService typeService = new TypeService();
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Type> types = typeService.findAll();
        req.setAttribute("types",types);
        req.getRequestDispatcher("/jsp/add_blog.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Integer tid = Integer.valueOf(req.getParameter("tid"));
        Blog blog = new Blog(null,title,content,1,tid,0);
        blogService.add(blog);
        resp.sendRedirect(req.getContextPath()+"/ShowBlogServlet");
    }
}
