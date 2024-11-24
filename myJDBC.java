import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class myJDBC {
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;
    private ResultSet results;

    public myJDBC(String dburl, String username, String password) {
        this.DBURL = dburl;
        this.USERNAME = username;
        this.PASSWORD = password;
    }
    public myJDBC() {
        this.DBURL = "jdbc:mysql://localhost:3306/MOVIE_THEATRE";
        this.USERNAME = "root";
        this.PASSWORD = "password";
    }

    public void initializeConnection() {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            this.dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
            System.out.println("Connection to the database successful!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Please make sure the JDBC driver is properly added to the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }


    String getDburl() {
        return this.DBURL;
    }

    String getUsername() {
        return this.USERNAME;
    }

    String getPassword() {
        return this.PASSWORD;
    }

    public void displayMovies() {
        try {
            Statement statement = this.dbConnect.createStatement();
            String query = "SELECT * FROM MOVIE";
            this.results = statement.executeQuery(query);
            System.out.println("All Movies: ");

            while(this.results.next()) {
                System.out.println("Movie ID: " + this.results.getInt("MovieID"));
                System.out.println("Movie: " + this.results.getString("Title"));
                System.out.println("\n");
            }

            statement.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
    }


}

