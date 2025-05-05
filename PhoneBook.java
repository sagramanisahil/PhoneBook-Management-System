package PhoneBook;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class PhoneBook implements ActionListener {
    JFrame frame;
    JPanel panelA, panelB, panelC, panelD;
    JButton BView, Bsearch, Bsave, Bdelete, home;
    JLabel toplabel, firstname, lastname, phoneno;
    JTextField firstName, lastName, contact, searchInitial;
    public HashMap<Character, HashMap<String, String>> phoneBook = new HashMap<>();
    private final String FILE_PATH = "phonebook_data.txt";

    PhoneBook() {
        frame = new JFrame("PHONE BOOK");
        loadContactsFromFile();

        toplabel = new JLabel("PHONEBOOK MANAGEMENT SYSTEM");
        toplabel.setFont(new Font("Arial", Font.BOLD, 35));
        toplabel.setForeground(new Color(0, 128, 0));

        firstname = new JLabel("First Name: ");
        lastname = new JLabel("Last Name: ");
        phoneno = new JLabel("Contact No: ");

        firstname.setFont(new Font("Arial", Font.BOLD, 16));
        lastname.setFont(new Font("Arial", Font.BOLD, 16));
        phoneno.setFont(new Font("Arial", Font.BOLD, 16));

        firstname.setForeground(new Color(0, 128, 0));
        lastname.setForeground(new Color(0, 128, 0));
        phoneno.setForeground(new Color(0, 128, 0));

        BView = new JButton("View All");
        Bsearch = new JButton("Search");
        Bsave = new JButton("Save");
        Bdelete = new JButton("Delete");
        //Bedit = new JButton("Edit");
        home = new JButton("Home");

        BView.addActionListener(this);
        Bsearch.addActionListener(this);
        Bsave.addActionListener(this);
        Bdelete.addActionListener(this);
        //Bedit.addActionListener(this);
        home.addActionListener(this);

        firstName = new JTextField(15);
        lastName = new JTextField(15);
        contact = new JTextField(15);
        searchInitial = new JTextField(10);

        panelA = new JPanel(new FlowLayout());
        panelA.setBackground(Color.decode("#B2E6CE"));
        panelA.add(toplabel);

        panelD = new JPanel(new GridLayout(1, 5, 10, 10));
        panelD.setBackground(Color.decode("#C6E2B5"));
        panelD.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelD.add(home);
        panelD.add(Bsearch);
        panelD.add(BView);
        //panelD.add(Bedit);
        panelD.add(Bdelete);

        panelB = new JPanel(new GridBagLayout());
        panelB.setBackground(Color.decode("#B2E6CE"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panelB.add(firstname, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelB.add(firstName, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panelB.add(lastname, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelB.add(lastName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panelB.add(phoneno, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelB.add(contact, gbc);

        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.CENTER;
        panelB.add(Bsave, gbc);

        // Main Container
        panelC = new JPanel(new BorderLayout(10, 10));
        panelC.setBackground(Color.decode("#B2E6CE"));
        panelC.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
        panelC.add(panelD, BorderLayout.NORTH);
        panelC.add(panelB, BorderLayout.CENTER);

        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout(10, 10));
        c.add(panelA, BorderLayout.NORTH);
        c.add(panelC, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setVisible(true);
    }
    public void insertContact(String firstName, String lastName, String contact) {
        char initial = Character.toUpperCase(firstName.charAt(0));
        phoneBook.putIfAbsent(initial, new HashMap<>());
        phoneBook.get(initial).put(firstName + " " + lastName, contact);
    }

    public listt obj= new listt();
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Bsearch) {

            String initial = JOptionPane.showInputDialog(frame, "Enter initial (A-Z):");
            if (initial != null && !initial.trim().isEmpty()) {
                char searchChar = initial.trim().toUpperCase().charAt(0);
                if (searchChar >= 'A' && searchChar <= 'Z' && phoneBook.containsKey(searchChar)) {
                    displaySearchResults(searchChar);
                } else {
                    JOptionPane.showMessageDialog(frame, "No contacts found with initial " + searchChar);
                }
            }
        } else if(e.getSource() == BView) {
            viewAll(obj.root);

        } else if (e.getSource() == Bsave) {
            //obj= new listt(firstName.getText().trim(),lastName.getText().trim(),contact.getText().trim());
           String firstNameInput=firstName.getText().trim();
           String lastNameInput=lastName.getText().trim();
           String contactInput=contact.getText().trim();

            if (!firstNameInput.isEmpty() && !lastNameInput.isEmpty() && !contactInput.isEmpty()) {

                obj.insert(firstNameInput,lastNameInput,contactInput);
                insertContact(firstNameInput, lastNameInput, contactInput);
                saveContactsToFile();
                firstName.setText("");
                lastName.setText("");
                contact.setText("");

                JOptionPane.showMessageDialog(frame, "Contact Saved:\n" + firstNameInput + " " + lastNameInput + " - " + contactInput);
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
            }
        }
        else if(e.getSource()== Bdelete){
            deleteContact();
        }
    }
    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            saveBSTToFile(writer, obj.root); // Traverse the BST to save contacts
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving contacts: " + e.getMessage());
        }
    }

    public void saveBSTToFile(BufferedWriter writer, Person root) throws IOException {
        if (root == null) {
            return;
        }
        saveBSTToFile(writer, root.left); // Left subtree
        writer.write(root.firstname + "," + root.lastname + "," + root.contactno);
        writer.newLine();
        saveBSTToFile(writer, root.right); // Right subtree
    }


    public void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String contact = parts[2];
                    obj.insert(firstName, lastName, contact); // Insert into BST
                    insertContact(firstName, lastName, contact); // Update HashMap
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved contacts found. Starting fresh.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading contacts: " + e.getMessage());
        }
    }

    public void viewAll(Person root) {
        JFrame viewFrame = new JFrame("All Contacts");
        viewFrame.setSize(600, 400);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        viewFrame.setLayout(new BorderLayout(10, 10));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> performSearch(searchField.getText().trim()));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Contact");

        JTable contactTable = new JTable(tableModel);
        contactTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contactTable.setRowHeight(25);
        contactTable.setDefaultEditor(Object.class, null);


        JScrollPane tableScrollPane = new JScrollPane(contactTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 250));

        tableModel.setRowCount(0);
        print(tableModel,root);


        viewFrame.add(tableScrollPane, BorderLayout.CENTER);

        viewFrame.setVisible(true);
    }

    public void print(DefaultTableModel tableModel, Person root) {


       if(root==null)
       {
        return;
       }
       print(tableModel,root.right);
       System.out.println(root.firstname + root.lastname + root.contactno);
       tableModel.addRow(new Object[]{root.firstname, root.lastname, root.contactno});
       print(tableModel, root.left);

    }

    public void performSearch(String searchText) {
        System.out.println("Searching for: " + searchText);
    }

    public void displaySearchResults(char searchChar) {
        JFrame resultFrame = new JFrame("Search Results");
        resultFrame.setSize(600, 400);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Contact");

        JTable contactTable = new JTable(tableModel);
        contactTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contactTable.setRowHeight(25);
        contactTable.setDefaultEditor(Object.class, null);

        JScrollPane tableScrollPane = new JScrollPane(contactTable);
        tableScrollPane.setPreferredSize(new Dimension(500, 250));

        HashMap<String, String> contacts = phoneBook.get(searchChar);
        if (contacts != null && !contacts.isEmpty()) {
            for (String name : contacts.keySet()) {
                String[] nameParts = name.split(" ");
                String fName = nameParts[0];
                String lName = nameParts.length > 1 ? nameParts[1] : "";
                String phone = contacts.get(name);
                tableModel.addRow(new Object[]{fName, lName, phone});
            }
        } else {
            tableModel.addRow(new Object[]{"No contacts found for initial: " + searchChar, "", ""});
        }

        resultFrame.add(tableScrollPane, BorderLayout.CENTER);
        resultFrame.setVisible(true);
    }
    public void deleteContact() {
        String firstNameInput = JOptionPane.showInputDialog(frame, "Enter First Name:");
        String lastNameInput = JOptionPane.showInputDialog(frame, "Enter Last Name:");

        if (firstNameInput != null && lastNameInput != null &&
                !firstNameInput.trim().isEmpty() && !lastNameInput.trim().isEmpty()) {
            char initial = Character.toUpperCase(firstNameInput.charAt(0));
            if (phoneBook.containsKey(initial)) {
                String fullName = firstNameInput + " " + lastNameInput;
                if (phoneBook.get(initial).containsKey(fullName)) {
                    phoneBook.get(initial).remove(fullName);
                    obj.delete(firstNameInput, lastNameInput);
                    saveContactsToFile();
                    JOptionPane.showMessageDialog(frame, "Contact deleted successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Contact not found.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please provide valid names.");
        }
    }


    public static void main(String[] args) {
        new PhoneBook();
    }
}
