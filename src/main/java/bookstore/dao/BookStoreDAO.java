package bookstore.dao;

import com.bookstore.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookStoreDAO {

    private static final Logger logger = LoggerFactory.getLogger(BookStoreDAO.class);

    public Connection dbConnection(){
        Connection con =  null;

        String url = "jdbc:mysql://localhost:3306/book_store_01";
        String user = "root";
        String password = "Th4nuj4";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Database connection established successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        Connection con = dbConnection();
        String query = "select * from book";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                bookList.add(new Book(id, title, author, publisher, price, stock));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  bookList;
    }
}
