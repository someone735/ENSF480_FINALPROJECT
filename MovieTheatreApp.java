import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MovieTheatreApp extends JFrame {
    private static myJDBC db;
    private static final CardLayout cardLayout = new CardLayout();
    private JPanel cards;  // main container to hold different panels (like cards)
    private InitialPanel initialPanel;
    private LoginPanel loginPanel;
    private SignupPanel signupPanel;
    private MovieListPanel movieListPanel;

    public MovieTheatreApp() {
        super("ACMEPLEX");

        // set up the main container with CardLayout
        cards = new JPanel(cardLayout);


        loginPanel = new LoginPanel(this, db);
        movieListPanel = new MovieListPanel(this, db);
        initialPanel = new InitialPanel(this);
        signupPanel = new SignupPanel(this);

        cards.add(loginPanel, "Login");
        cards.add(movieListPanel, "Movies");
        cards.add(initialPanel, "Initial");
        cards.add(signupPanel, "Signup");

        this.add(cards);

        cardLayout.show(cards, "Initial");
        // Basic frame setup
        setSize(800, 500);
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
//        String url = "";
//        String user = "";
//        String pw = "";
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your database url (e.g. jdbc:mysql://localhost:3306/MOVIE_THEATRE): ");
//        url = scanner.nextLine();
//        System.out.println("Enter your database username (e.g. root): ");
//        user = scanner.nextLine();
//        System.out.println("Enter your database password: ");
//        pw = scanner.nextLine();
//        //myJDBC db = new myJDBC(url, user, pw);
        db = new myJDBC("jdbc:mysql://localhost:3306/MOVIE_THEATRE", "root", "password");
        db.initializeConnection();
//
//        searchMovie(db, scanner);

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
        guestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.switchToMovieList();
            }
        });


        this.setLayout(new FlowLayout());
        this.add(loginButton);
        this.add(registerButton);
        this.add(guestButton);
    }
}

class LoginPanel extends JPanel {
    private int width = 100;
    private int height = 30;
    private JTextField usrInput;
    private JPasswordField pwInput;

    private MovieTheatreApp app;

    public LoginPanel(MovieTheatreApp app, myJDBC db) {
        this.app = app;

        JLabel usrLabel = new JLabel("Username:");
        JLabel pwLabel = new JLabel("Password:");

        usrInput = new JTextField(15);
        pwInput = new JPasswordField(15);
        usrInput.setPreferredSize(new Dimension(width, height));
        pwInput.setPreferredSize(new Dimension(width, height));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usrInput.getText();
                String password = new String(pwInput.getPassword());

                // Need to change/add to this stuff still
                if ("admin".equals(username) && "password".equals(password)) {
                    JOptionPane.showMessageDialog(app, "Logged in successfully!");
                    app.switchToMovieList();  // switch to the movie list panel
                } else {
                    JOptionPane.showMessageDialog(app, "Invalid username or password.");
                }
            }
        });

        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Sign In");
        headerPanel.add(title);

        this.add(headerPanel, BorderLayout.NORTH);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitButton);
        this.add(submitPanel, BorderLayout.PAGE_END);

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);


        int row = 0;
        addComponent(clientPanel, usrLabel, 0, row, gbc);
        addComponent(clientPanel, usrInput, 1, row++, gbc);

        addComponent(clientPanel, pwLabel, 0, row, gbc);
        addComponent(clientPanel, pwInput, 1, row++, gbc);

        this.add(clientPanel, BorderLayout.CENTER);

    }
    private void addComponent(JPanel panel, Component component, int gridx, int gridy, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }
}
// havent implemented signup panel yet..
class SignupPanel extends JPanel {
    private int width = 100;
    private int height = 30;
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
        // set fixed size text fields
        usrInput.setPreferredSize(new Dimension(width, height));
        pwInput.setPreferredSize(new Dimension(width, height));
        pwConfirmInput.setPreferredSize(new Dimension(width, height));
        fnameInput.setPreferredSize(new Dimension(width, height));
        lnameInput.setPreferredSize(new Dimension(width, height));
        emailInput.setPreferredSize(new Dimension(width, height));
        addressInput.setPreferredSize(new Dimension(width, height));

//        JPanel clientPanel = new JPanel();
//        clientPanel.setLayout(new FlowLayout());
//
//        JPanel submitPanel = new JPanel();
//        submitPanel.setLayout(new FlowLayout());

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

        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        JLabel title = new JLabel("Please Fill Out All Fields: ");
        headerPanel.add(title);
        this.add(headerPanel, BorderLayout.NORTH);

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);


        int row = 0;
        addComponent(clientPanel, fnameLabel, 0, row, gbc);
        addComponent(clientPanel, fnameInput, 1, row++, gbc);

        addComponent(clientPanel, lnameLabel, 0, row, gbc);
        addComponent(clientPanel, lnameInput, 1, row++, gbc);

        addComponent(clientPanel, emailLabel, 0, row, gbc);
        addComponent(clientPanel, emailInput, 1, row++, gbc);

        addComponent(clientPanel, addressLabel, 0, row, gbc);
        addComponent(clientPanel, addressInput, 1, row++, gbc);

        addComponent(clientPanel, usrLabel, 0, row, gbc);
        addComponent(clientPanel, usrInput, 1, row++, gbc);

        addComponent(clientPanel, pwLabel, 0, row, gbc);
        addComponent(clientPanel, pwInput, 1, row++, gbc);

        addComponent(clientPanel, pwConfirmLabel, 0, row, gbc);
        addComponent(clientPanel, pwConfirmInput, 1, row++, gbc);


        this.add(clientPanel, BorderLayout.CENTER);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitButton);

        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    // Utility method to add components to the GridBagLayout
    private void addComponent(JPanel panel, Component component, int gridx, int gridy, GridBagConstraints gbc) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }
}


class MovieListPanel extends JPanel {
    private ArrayList<String> movies;
    private boolean showAll = true;
    private String searchMovie;
    private JTextField searchInput;
    private DefaultListModel<String> listModel;
    private JList<String> movieList;
    private JButton showAllButton;

    public MovieListPanel(MovieTheatreApp app, myJDBC db) {
        // layout for the main panel
        this.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        showAllButton = new JButton("Show All");
        showAllButton.setVisible(false);
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAll = true;
                updateMovieList(db);
                showAllButton.setVisible(false);
            }
        });


        JLabel searchLabel = new JLabel("Search a Movie:");
        searchInput = new JTextField(30);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchMovie = searchInput.getText();
                showAll = false;
                updateMovieList(db);
                showAllButton.setVisible(true);
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchInput);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);


        this.add(searchPanel, BorderLayout.NORTH);

        //create list model for the JList
        listModel = new DefaultListModel<>();
        movieList = new JList<>(listModel);  // initialize the JList with the empty list model

        // scroll pane for the JList
        JScrollPane movieListScrollPane = new JScrollPane(movieList);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(movieListScrollPane, BorderLayout.CENTER);

        // initially load all movies
        updateMovieList(db);
    }


    private void updateMovieList(myJDBC db) {
        listModel.clear();  // clear existing list items

        if (showAll) {
            movies = db.displayMovies();
        } else {
            movies = db.searchMovie(searchMovie);
        }

        for (String movie : movies) {
            listModel.addElement(movie);
        }

        movieList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedMovie = movieList.getSelectedValue();
                // ummm still need to implemen6tt  stuff !!!
                System.out.println("Selected Movie: " + selectedMovie);
            }
        });
    }
}


