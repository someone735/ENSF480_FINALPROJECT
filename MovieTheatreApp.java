import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MovieTheatreApp extends JFrame {
    private static final CardLayout cardLayout = new CardLayout();
    private JPanel cards;  // Main container to hold different panels (like cards)
    private InitialPanel initialPanel;
    private LoginPanel loginPanel;
    private SignupPanel signupPanel;
    private MovieListPanel movieListPanel;

    public MovieTheatreApp() {
        super("ACMEPLEX");

        // Set up the main container with CardLayout
        cards = new JPanel(cardLayout);

        // Create and add the login panel to the CardLayout container
        loginPanel = new LoginPanel(this);
        movieListPanel = new MovieListPanel();
        initialPanel = new InitialPanel(this);
        signupPanel = new SignupPanel(this);

        cards.add(loginPanel, "Login");
        cards.add(movieListPanel, "Movies");
        cards.add(initialPanel, "Initial");
        cards.add(signupPanel, "Signup");

        this.add(cards);

        cardLayout.show(cards, "Initial");
        // Basic frame setup
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void switchToMovieList() {
        // Switch to the Movie List panel after successful login
        cardLayout.show(cards, "Movies");
    }
    public void switchToLogin() {
        // Switch to the Movie List panel after successful login
        cardLayout.show(cards, "Login");
    }
    public void switchToRegister() {
        // Switch to the Movie List panel after successful login
        cardLayout.show(cards, "Signup");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MovieTheatreApp().setVisible(true);
        });
    }
}

class InitialPanel extends JPanel {

    private MovieTheatreApp app;

    public InitialPanel(MovieTheatreApp app) {
        this.app = app;


        JButton loginButton = new JButton("login");
        JButton registerButton = new JButton("sign up");
        JButton guestButton = new JButton("continue as guest");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.switchToLogin();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.switchToRegister();
            }
        });

        this.setLayout(new FlowLayout());
        this.add(loginButton);
        this.add(registerButton);
        this.add(guestButton);
    }
}

class LoginPanel extends JPanel {
    private JTextField usrInput;
    private JPasswordField pwInput;

    private MovieTheatreApp app;

    public LoginPanel(MovieTheatreApp app) {
        this.app = app;

        JLabel usrLabel = new JLabel("Username:");
        JLabel pwLabel = new JLabel("Password:");

        usrInput = new JTextField(15);
        pwInput = new JPasswordField(15);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usrInput.getText();
                String password = new String(pwInput.getPassword());

                // Simulate a successful login for now
                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(app, "Logged in successfully!");
                    app.switchToMovieList();  // Switch to the movie list panel
                } else {
                    JOptionPane.showMessageDialog(app, "Invalid username or password.");
                }
            }
        });

        this.setLayout(new FlowLayout());
        this.add(usrLabel);
        this.add(usrInput);
        this.add(pwLabel);
        this.add(pwInput);
        this.add(submitButton);
    }
}
// havent implemented signup panel yet..
class SignupPanel extends JPanel {
    private RegisteredUser RU;
    private JTextField usrInput;
    private JPasswordField pwInput;
    private JPasswordField pwConfirmInput;
    private JTextField fnameInput;
    private JTextField lnameInput;
    private JTextField emailInput;
    private JTextField addressInput;

    private MovieTheatreApp app;

    public SignupPanel(MovieTheatreApp app) {
        this.app = app;

        JLabel usrLabel = new JLabel("Username:");
        JLabel pwLabel = new JLabel("Password:");
        JLabel pwConfirmLabel = new JLabel("Confirm Password:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel fnameLabel = new JLabel("First Name:");
        JLabel lnameLabel = new JLabel("Last Name:");
        JLabel addressLabel = new JLabel("Address:");

        usrInput = new JTextField(15);
        pwInput = new JPasswordField(15);
        pwConfirmInput = new JPasswordField(15);
        fnameInput = new JTextField(15);
        lnameInput = new JTextField(15);
        emailInput = new JTextField(30);
        addressInput = new JTextField(50);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int safe = 1;
                String username = usrInput.getText();
                String pw = new String(pwInput.getPassword());
                String pwConfirm = new String(pwConfirmInput.getPassword());
                String fname = new String(fnameInput.getText());
                String lname = new String(lnameInput.getText());
                String email = new String(emailInput.getText());
                String address = new String(addressInput.getText());

                if (!pw.equals(pwConfirm)) {
                    safe = 0;
                    JOptionPane.showMessageDialog(app, "Passwords do not match.");
                }
                // DO A CHECK TO MAKE SURE THE USERNAME IS NOT ALREADY IN DB
                // AFTER CHECK MAKE THE RU AND SET THEIR INFO

                if (safe == 1) {
                    // set RU stuff maybe and add them to db?
                    // switch to a diff panel
                    app.switchToMovieList();
                }

            }
        });

        this.setLayout(new FlowLayout());
        this.add(fnameLabel);
        this.add(fnameInput);
        this.add(lnameLabel);
        this.add(lnameInput);
        this.add(emailLabel);
        this.add(emailInput);
        this.add(addressLabel);
        this.add(addressInput);
        this.add(usrLabel);
        this.add(usrInput);
        this.add(pwLabel);
        this.add(pwInput);
        this.add(pwConfirmLabel);
        this.add(pwConfirmInput);
        this.add(submitButton);
    }
}


class MovieListPanel extends JPanel {
    public MovieListPanel() {
        // This is where the movie list UI will go
        JLabel title = new JLabel("Movie List:");
        String[] movieTitles = {"Movie 1", "Movie 2", "Movie 3", "Movie 4"};
        JList<String> movieList = new JList<>(movieTitles);

        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(new JScrollPane(movieList), BorderLayout.CENTER);
    }
}
