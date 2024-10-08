package Vue;

import Controller.Exceptions.Exception_text;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.AbstractBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class RoundedBorder extends AbstractBorder {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.WHITE); // Set border color
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // Draw rounded rectangle border
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius); // Set border insets
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = radius; // Set border insets
        return insets;
    }
}
public class Admin_Login extends JFrame {
    public Admin_Login() {
        Main.charger();
        // Set up the frame
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);


        // Create the background panel
        ImageIcon backgroundImage = new ImageIcon("src/Vue/img.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        // Create the blue panel in the center
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(4, 19, 78, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(300, 150));

// Add this line to make the panel transparent
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(360, 170));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Code field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel codeLabel = new JLabel("Admin ID:");
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        mainPanel.add(codeLabel, gbc);

        gbc.gridx = 1;
        JTextField codeField = new JTextField(20); // Use JTextField instead of JPasswordField
        mainPanel.add(codeField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Set gridwidth to 2 to make it span two columns
        //gbc.anchor = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.EAST;// Center horizontally
        JButton loginButton = new JButton("Se connecter");
        loginButton.setBackground(new Color(6, 83, 138)); // Deep blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(110, 30));
        loginButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 10; // Adjust the radius as needed
        loginButton.setBorder(new RoundedBorder(borderRadius));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Vérifier si le mot de passe respecte les critères
                    Boolean validé = Controller.Admin.Login(codeField.getText(), passwordField.getText(), "2024");
                    if(validé){
                        JOptionPane.showMessageDialog(null, "Accès réussi", "Accès réussi", JOptionPane.INFORMATION_MESSAGE);
                    }
                    dispose();
                    Admin_view secondWindow = new Admin_view();
                    secondWindow.setVisible(true);
                } catch (Exception_text ex) {
                    // Afficher un message d'erreur si le mot de passe est invalide
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Vous devez entrer quelque chose !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(loginButton, gbc);
        // Add the blue panel to the center of the background panel
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundLabel.add(mainPanel, gbc);

        // Add the background panel to the frame
        add(backgroundLabel);
    }
}
