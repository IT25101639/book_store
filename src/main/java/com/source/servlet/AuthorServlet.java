package com.source.servlet;

import com.source.dao.AuthorDAO;
import com.source.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {

    private final AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String message = null;
        String messageType = "success";

        try {
            if ("add".equals(action)) {
                String name = request.getParameter("authorName");
                String email = request.getParameter("email");
                Author author = new Author();
                author.setAuthorName(name);
                author.setEmail(email);
                authorDAO.addAuthor(author);
                message = "Author \"" + name + "\" was added successfully.";

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("authorName");
                String email = request.getParameter("email");
                Author author = new Author(id, name, email);
                authorDAO.updateAuthor(author);
                message = "Author \"" + name + "\" was updated successfully.";
            }
        } catch (SQLException e) {
            message = "Database error: " + e.getMessage();
            messageType = "error";
        }

        // Show list with flash message
        try {
            List<Author> authors = authorDAO.getAllAuthors();
            request.setAttribute("authors", authors);
        } catch (SQLException e) {
            // ignore secondary error
        }
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        request.getRequestDispatcher("author_list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = authorDAO.getAuthorById(id);
                String name = author != null ? author.getAuthorName() : "Author";
                authorDAO.deleteAuthor(id);

                List<Author> authors = authorDAO.getAllAuthors();
                request.setAttribute("authors", authors);
                request.setAttribute("message", "\"" + name + "\" was deleted successfully.");
                request.setAttribute("messageType", "success");
                request.getRequestDispatcher("author_list.jsp").forward(request, response);
                return;

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Author author = authorDAO.getAuthorById(id);
                if (author == null) {
                    response.sendRedirect("AuthorServlet");
                    return;
                }
                request.setAttribute("editAuthor", author);
                request.getRequestDispatcher("author_form.jsp").forward(request, response);
                return;

            } else if ("new".equals(action)) {
                // Show blank add form
                request.getRequestDispatcher("author_form.jsp").forward(request, response);
                return;
            }

            // Default: show the list
            List<Author> authors = authorDAO.getAllAuthors();
            request.setAttribute("authors", authors);
            request.getRequestDispatcher("author_list.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
