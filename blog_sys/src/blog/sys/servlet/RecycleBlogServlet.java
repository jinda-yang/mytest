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
 * 查看回收站
 * @author yangjinda
 * @datetime: 2024-08-17 11:10
 */
@WebServlet("/RecycleBlogServlet")
public class RecycleBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("pageNum");
        int pageNum = (num == null || num.trim().isEmpty()) ? 1 :Integer.parseInt(num);
        int pageSize = 4;
        PageBean<BlogVo> pageBean = blogService.findRecycle(pageNum,pageSize);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("/jsp/show_recycle.jsp").forward(req,resp);
    }

}
