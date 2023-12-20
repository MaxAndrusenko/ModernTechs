package com.example.webapp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.Driver;
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO cars (name, price, description) VALUES (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);

            preparedStatement.executeUpdate();

            connection.close();

            // Після успішного додавання десерту перенаправлення на головну сторінку або іншу сторінку
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            // Обробка винятків SQL
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

