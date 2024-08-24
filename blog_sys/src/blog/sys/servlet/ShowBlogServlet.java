package blog.sys.servlet;

import blog.sys.pojo.BlogVo;
import blog.sys.pojo.PageBean;
import blog.sys.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 博客列表
 * @author yangjinda
 * @datetime: 2024-08-17 09:36
 */
@WebServlet("/ShowBlogServlet")
public class ShowBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("pageNum");
        int pageNum = (num == null || num.trim().isEmpty()) ? 1 : Integer.parseInt(num);
        int pageSize = 4;
        PageBean<BlogVo> pageBean = blogService.findAll(pageNum,pageSize);
        // 总编辑次数
        int sum = blogService.querySum();
        req.setAttribute("pageBean",pageBean);
        req.setAttribute("sum",sum);
        req.getRequestDispatcher("/jsp/show_blog.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
