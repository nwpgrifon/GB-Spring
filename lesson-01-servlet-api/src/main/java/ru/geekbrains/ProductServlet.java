package ru.geekbrains;

import com.sun.jdi.LongValue;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        var idParameter = req.getParameter("id");

        if (idParameter != null) {
            Product byId = productRepository.findById(Long.parseLong(idParameter));
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Name</th>");
            wr.println("</tr>");
            wr.println("<tr>");
            wr.println("<td>" + byId.getId() + "</td>");
            wr.println("<td>" + byId.getName() + "</td>");
            wr.println("</tr>");
            wr.println("</table>");

        } else if (req.getPathInfo() == null || req.getPathInfo().equals("")) {
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id<th>");
            wr.println("<th>Name</th>");
            wr.println("</tr>");

            for (Product product :
                    productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td>" + product.getId() + "</td>");
                String foo = "product?id=" + product.getId();
                wr.println("<td>  <a href = " + foo + ">"  + product.getName() + "</a> </td>");
                wr.println("</tr>");
            }
            wr.println("</table>");
        }
    }
}
