package org.example;

import javax.swing.*;
import java.awt.*;

public class LibraryGUI {
    private final Library library = Library.getInstance();

    public LibraryGUI() {
        JFrame frame = new JFrame("Library Document Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titleLabel = new JLabel("Library Document Menu", JLabel.CENTER);
        frame.add(titleLabel);

        String[] buttonLabels = {
                "Add Document",
                "Remove Document",
                "Update Document",
                "Exit"
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton("[" + i + "] " + buttonLabels[i]);
            int index = i;
            button.addActionListener(e -> handleMenuOption(index));
            frame.add(button);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMenuOption(int option) {
        switch (option) {
            case 0 -> addDocument();
            case 1 -> removeDocument();
            case 2 -> updateDocument();
            case 3 -> System.exit(0);
            default -> JOptionPane.showMessageDialog(null, "Invalid option");
        }
    }

    private void addDocument() {
        String title = JOptionPane.showInputDialog("Enter title:");
        String authors = JOptionPane.showInputDialog("Enter authors:");
        String category = JOptionPane.showInputDialog("Enter category:");
        String isbn = JOptionPane.showInputDialog("Enter ISBN:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));

        Book book = new Book(title, authors, category, isbn, quantity);
        library.addItem(book);
        JOptionPane.showMessageDialog(null, "Document added.");
    }

    private void removeDocument() {
        String isbn = JOptionPane.showInputDialog("Enter ISBN to remove:");
        var item = library.findItemByIsbn(isbn);
        if (item != null) {
            library.removeItem(item);
            JOptionPane.showMessageDialog(null, "Document removed.");
        } else {
            JOptionPane.showMessageDialog(null, "Document not found.");
        }
    }

    private void updateDocument() {
        String isbn = JOptionPane.showInputDialog("Enter ISBN to update:");
        var item = library.findItemByIsbn(isbn);
        if (item != null) {
            item.setTitle(JOptionPane.showInputDialog("New title:"));
            item.setDescription(JOptionPane.showInputDialog("New description:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("New quantity:"));
            item.setQuantity(quantity);
            JOptionPane.showMessageDialog(null, "Document updated.");
        } else {
            JOptionPane.showMessageDialog(null, "Document not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryGUI::new);
    }
}
