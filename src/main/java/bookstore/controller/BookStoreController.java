package bookstore.controller;

import bookstore.dao.BookStoreDAO;
import com.bookstore.dao.BookStoreDAO;
import com.bookstore.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class BookStoreController extends HttpServlet {

    BookStoreDAO bookStoreDAO = new BookStoreDAO();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getServletPath();

        switch (action) {
            case "/loginAdmin":
                break;
            case "/book/dashboard":
                bookDashboard(req,res);
                break;
        }

    }

    private void bookDashboard(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Book> bookList = new ArrayList<>();
        bookList = bookStoreDAO.getAllBooks();

        req.setAttribute("bookList", bookList);
        RequestDispatcher rd = req.getRequestDispatcher("bookDash.jsp");
        rd.forward(req,res);
    }

}
