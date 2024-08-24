package blog.sys.servlet;

import blog.sys.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 恢复博客
 * @author yangjinda
 * @datetime: 2024-08-17 11:17
 */
@WebServlet("/RecoveryBlogServlet")
public class RecoveryBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        //String num = req.getParameter("pageNum");
        //int pageNum = (num==null || num.trim().isEmpty()) ? 1 : Integer.parseInt(num);
        if (bid != null && !bid.trim().isEmpty()) {
            blogService.recovery(Integer.parseInt(bid));
            //req.setAttribute("pageNum",pageNum);
            resp.sendRedirect(req.getContextPath()+"/RecycleBlogServlet");
        }
    }

}
