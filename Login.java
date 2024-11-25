
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class Login extends JFrame implements ActionListener , MouseListener{
    private String username;
    private String password;

    private JTextField usrInput;
    private JTextField pwInput;

    private JLabel title;
    private JLabel usrLabel;
    private JLabel pwLabel;

    public Login(){
        super("ACMEPLEX");
        setupGUI();
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setupGUI(){

        title = new JLabel("Welcome to AcmePlex!");
        usrLabel  = new JLabel("Username:");
        pwLabel  = new JLabel("Password:");

        usrInput = new JTextField(null, 15);
        pwInput = new JTextField(null, 15);


        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(title);
        clientPanel.add(usrLabel);
        clientPanel.add(usrInput);
        clientPanel.add(pwLabel);
        clientPanel.add(pwInput);
        submitPanel.add(submitInfo); // adding the submit button to submit panel

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    public void actionPerformed(ActionEvent event){
        username = usrInput.getText();
        password = pwInput.getText();

        // check database if valid username and password !!!
        JOptionPane.showMessageDialog(this, "Logged in! ");
    }

    public void mouseClicked(MouseEvent event){
    }
    public void mouseEntered(MouseEvent event){
    }
    public void mouseExited(MouseEvent event){
    }
    public void mousePressed(MouseEvent event){
    }
    public void mouseReleased(MouseEvent event){
    }
}
