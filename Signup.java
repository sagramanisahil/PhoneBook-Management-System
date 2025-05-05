package PhoneBook;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame implements ActionListener {
    JTextField textField, textField2, textField3, textField4;
    JButton button4;

    Signup(){
        super("Sign Up");

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0x00B4D8), 0, getHeight(), new Color(0x9C84C3));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);

        ImageIcon i1 = new ImageIcon("Image.jpg");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(190, 50, 80, 80);
        panel.add(image);

        JLabel label2 = new JLabel("Create an Account");
        label2.setBounds(140, 140, 250, 30);
        label2.setFont(new Font("Arial", Font.BOLD, 22));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel label3 = new JLabel("Username:");
        label3.setBounds(50, 210, 250, 25);
        label3.setFont(new Font("Arial", Font.PLAIN, 16));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBounds(200, 210, 230, 30);
        textField.setBorder(BorderFactory.createLineBorder(new Color(0x9C84C3), 2));
        panel.add(textField);

        JLabel label5 = new JLabel("Email Address:");
        label5.setBounds(50, 270, 250, 25);
        label5.setFont(new Font("Arial", Font.PLAIN, 16));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        textField2 = new JTextField(15);
        textField2.setFont(new Font("Arial", Font.PLAIN, 16));
        textField2.setBounds(200, 270, 230, 30);
        textField2.setBorder(BorderFactory.createLineBorder(new Color(0x9C84C3), 2));
        panel.add(textField2);

        JLabel label4 = new JLabel("Password:");
        label4.setBounds(50, 330, 250, 25);
        label4.setFont(new Font("Arial", Font.PLAIN, 16));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        textField3 = new JTextField(15);
        textField3.setFont(new Font("Arial", Font.PLAIN, 16));
        textField3.setBounds(200, 330, 230, 30);
        textField3.setBorder(BorderFactory.createLineBorder(new Color(0x9C84C3), 2));
        panel.add(textField3);

        JLabel label6 = new JLabel("Confirm Password:");
        label6.setBounds(50, 390, 250, 25);
        label6.setFont(new Font("Arial", Font.PLAIN, 16));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        textField4 = new JTextField(15);
        textField4.setFont(new Font("Arial", Font.PLAIN, 16));
        textField4.setBounds(200, 390, 230, 30);
        textField4.setBorder(BorderFactory.createLineBorder(new Color(0x9C84C3), 2));
        panel.add(textField4);

        button4 = new JButton("Next");
        button4.setFont(new Font("Arial", Font.BOLD, 16));
        button4.setForeground(Color.WHITE);
        button4.setBackground(new Color(0x9C84C3));
        button4.setBounds(150, 460, 150, 40);
        button4.setBorder(BorderFactory.createLineBorder(new Color(0x9C84C3), 2));
        button4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button4.addActionListener(this);
        panel.add(button4);

        add(panel);
        setSize(500, 550);
        setLocation(400, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = textField.getText();
        String email = textField2.getText();
        String password = textField3.getText();
        String confirmPassword = textField4.getText();

        try {
            if (name.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setVisible(false);
                new Login();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
