package blog.sys.servlet;

import blog.sys.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除博客
 * @author yangjinda
 * @datetime: 2024-08-17 10:33
 */
@WebServlet("/DeleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        String num = req.getParameter("pageNum");
        int pageNum = (num==null || num.trim().isEmpty()) ? 1 : Integer.parseInt(num);
        if (bid != null && !bid.trim().isEmpty()) {
            blogService.delete(Integer.parseInt(bid));
            req.setAttribute("pageNum",pageNum);
            req.getRequestDispatcher("/ShowBlogServlet").forward(req,resp);
        }
    }

}
