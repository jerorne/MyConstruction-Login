package com.iplacex.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementación del servlet para el inicio de sesión
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // URL de la base de datos, usuario y contraseña (ajustar según sea necesario)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MyConstruction";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    /**
     * Maneja la solicitud HTTP POST del formulario de inicio de sesión.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Recupera el nombre de usuario y la contraseña enviados
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Valida las credenciales del usuario
        if (validateUser(username, password)) {
            // Redirige a la página de bienvenida si es exitoso
            response.sendRedirect("welcome.jsp");
        } else {
            // Redirige de nuevo a la página de inicio de sesión con un indicador de error
            response.sendRedirect("index.jsp?error=true");
        }
    }

    /**
     * Valida las credenciales del usuario verificando la base de datos.
     * 
     * @param username El nombre de usuario a validar
     * @param password La contraseña a validar
     * @return true si el usuario es válido, false en caso contrario
     */
    private boolean validateUser(String username, String password) {
        // Consulta SQL para verificar las credenciales del usuario
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            // Establece los parámetros para la consulta preparada
            statement.setString(1, username);
            statement.setString(2, password);
            
            // Ejecuta la consulta y verifica si se encuentra una coincidencia
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
            
        } catch (Exception e) {
            // Registra la excepción (usar un logger en producción)
            e.printStackTrace();
        }
        
        // Retorna false si la validación falla
        return false;
    }
}
