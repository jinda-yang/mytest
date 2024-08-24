package blog.sys.servlet;

import blog.sys.service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangjinda
 * @datetime: 2024-08-19 11:55
 */
@WebServlet("/MultiDelBlogServlet")
public class MultiDelBlogServlet extends HttpServlet {
    BlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("bids");
        String[] bids = ids.split("-");
        for (String bid : bids) {
            blogService.delete(Integer.parseInt(bid));
        }
        resp.sendRedirect(req.getContextPath()+"/ShowBlogServlet");
    }
}
