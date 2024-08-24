package blog.sys.servlet;

import blog.sys.pojo.Blog;
import blog.sys.pojo.BlogVo;
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
 * 编辑功能，博客回显、修改博客
 * @author yangjinda
 * @datetime: 2024-08-17 10:08
 */
@WebServlet("/UpdateBlogServlet")
public class UpdateBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();
    TypeService typeService = new TypeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        String num = req.getParameter("pageNum");
        if (bid != null && !bid.trim().isEmpty()) {
            BlogVo blog = blogService.queryByBid(Integer.parseInt(bid));
            List<Type> types = typeService.findAll();
            req.setAttribute("blog",blog);
            req.setAttribute("types",types);
            req.setAttribute("pageNum",Integer.parseInt(num));
            req.getRequestDispatcher("/jsp/update_blog.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("bid");
        String num = req.getParameter("pageNum");
        int bid = 0;
        if (id != null && !id.trim().isEmpty()) {
            bid = Integer.parseInt(id);
        }
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String c = req.getParameter("count");
        int count = 0;
        if (c != null && !c.trim().isEmpty()) {
            count = Integer.parseInt(c)+1;
        }
        Integer tid = Integer.valueOf(req.getParameter("tid"));
        Blog blog = new Blog(bid,title,content,count,tid,0);
        blogService.update(blog);
        resp.sendRedirect(req.getContextPath()+"/ShowBlogServlet?pageNum="+Integer.parseInt(num));
    }
}
