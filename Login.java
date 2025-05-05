package PhoneBook;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JLabel label, label1, label2;
    JTextField textField;
    JPasswordField passwordField;
    JButton button1, button2, button3;
    String pin;

    Login() {
        super("PhoneBook Application");
        getContentPane().setBackground(new Color(100, 149, 237));

        ImageIcon i1 = new ImageIcon("photo.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(340, 25, 100, 100);
        add(image);

        label = new JLabel("Welcome to PhoneBook Application");
        label.setForeground(Color.white);
        label.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        label.setBounds(210, 125, 450, 40);
        add(label);

        label1 = new JLabel("Username : ");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setBounds(170, 190, 375, 30);
        add(label1);

        textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setBounds(325, 190, 230, 30);
        add(textField);

        label2 = new JLabel("Password : ");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setBounds(170, 205, 390, 120);
        add(label2);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(325, 250, 230, 30);
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        add(passwordField);

        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 15));
        button1.setForeground(Color.white);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300, 300, 100, 30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("Cancel");
        button2.setFont(new Font("Arial", Font.BOLD, 15));
        button2.setForeground(Color.white);
        button2.setBackground(Color.BLACK);
        button2.setBounds(450, 300, 100, 30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 15));
        button3.setForeground(Color.white);
        button3.setBackground(Color.BLACK);
        button3.setBounds(360, 350, 150, 30);
        button3.addActionListener(this);
        add(button3);

        setLayout(null);
        setSize(850, 480);
        setLocation(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Login();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            String username = textField.getText();
            String pin = new String(passwordField.getPassword());
            this.pin = pin;

            if (username.equals("Sahil") && pin.equals("2004")) {
                new PhoneBook();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == button2) {
            textField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == button3) {
            new Signup().setVisible(true);
            setVisible(false);
        }
    }
}
