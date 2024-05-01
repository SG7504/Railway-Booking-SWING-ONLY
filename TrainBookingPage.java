import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainBookingPage extends JPanel {
    private JLabel usernameLabel, passwordLabel, originLabel, destinationLabel, classLabel, quotaLabel, filterLabel, sortLabel, dateLabel;
    private JTextField usernameField, passwordField;
    private JComboBox<String> originComboBox, destinationComboBox, classComboBox, quotaComboBox, filterComboBox, sortComboBox, dateComboBox;
    private JButton bookButton;

    public TrainBookingPage() {
        Font labelFont = new Font("Arial", Font.PLAIN, 25);
        Font comboBoxFont = new Font("Arial", Font.PLAIN, 20);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        originLabel = new JLabel("Origin Station:");
        originLabel.setFont(labelFont);
        destinationLabel = new JLabel("Destination Station:");
        destinationLabel.setFont(labelFont);
        classLabel = new JLabel("Class:");
        classLabel.setFont(labelFont);
        quotaLabel = new JLabel("Quota:");
        quotaLabel.setFont(labelFont);
        filterLabel = new JLabel("Train Filter:");
        filterLabel.setFont(labelFont);
        sortLabel = new JLabel("Sort By:");
        sortLabel.setFont(labelFont);
        dateLabel = new JLabel("Date:");
        dateLabel.setFont(labelFont);

        usernameField = new JTextField(10);
        usernameField.setFont(comboBoxFont);
        passwordField = new JPasswordField(10);
        passwordField.setFont(comboBoxFont);

        String[] stations = {"SELECT", "Bhubaneswar (BBS)", "New Delhi (NDLS)", "Howrah (HWH)", "Mumbai Central (MMCT)", "Chennai Central (MAS)", "Brahmapur (BAM)", "Cuttack (CTC)", "Sealdah (SDAH)"};
        originComboBox = new JComboBox<>(stations);
        originComboBox.setFont(comboBoxFont);
        destinationComboBox = new JComboBox<>(stations);
        destinationComboBox.setFont(comboBoxFont);

        String[] classes = {"ANY", "1st AC (1A)", "2nd AC (2A)", "3rd AC (3A)", "Sleeper (SL)", "AC Chair Car (CC)", "3rd AC Economy (3E)", "Second Seating (2S)", "General (GS)"};
        classComboBox = new JComboBox<>(classes);
        classComboBox.setFont(comboBoxFont);

        String[] quotas = {"General", "Tatkal", "Ladies", "Senior Citizen"};
        quotaComboBox = new JComboBox<>(quotas);
        quotaComboBox.setFont(comboBoxFont);

        String[] filters = {"ANY", "Rajdhani", "Shatabdi", "Duronto", "Superfast", "Mail", "Special", "Vande Bharat", "Humsafar", "Garib Rath", "Sampark Kranti", "Amrit Bharat"};
        filterComboBox = new JComboBox<>(filters);
        filterComboBox.setFont(comboBoxFont);

        String[] sorts = {"Lowest duration", "Highest duration", "Earliest Arrival", "Latest Arrival", "Earliest Departure", "Latest Departure"};
        sortComboBox = new JComboBox<>(sorts);
        sortComboBox.setFont(comboBoxFont);

        String[] dates = {"SELECT","01/05/2024", "02/05/2024", "03/05/2024"}; // Example dates, replace with your actual dates
        dateComboBox = new JComboBox<>(dates);
        dateComboBox.setFont(comboBoxFont);

        bookButton = new JButton("Book Now");
        bookButton.setFont(labelFont);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(9, 2));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(originLabel);
        inputPanel.add(originComboBox);
        inputPanel.add(destinationLabel);
        inputPanel.add(destinationComboBox);
        inputPanel.add(quotaLabel);
        inputPanel.add(quotaComboBox);
        inputPanel.add(dateLabel);
        inputPanel.add(dateComboBox);
        inputPanel.add(classLabel);
        inputPanel.add(classComboBox);
        inputPanel.add(sortLabel);
        inputPanel.add(sortComboBox);
        inputPanel.add(filterLabel);
        inputPanel.add(filterComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(bookButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add booking logic here
                System.out.println("Passenger Name: Sparsh Guha");
                System.out.println("22896/Puri - Howrah Vande Bharat Express");
                System.out.println("Food opted: VEG");
                System.out.println("Price: 1295 INR");
                System.out.println("Coach C2, Seat No: 72, Aisle");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Train Booking Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            TrainBookingPage bookingPage = new TrainBookingPage();
            frame.add(bookingPage);

            // Set the size of the frame to occupy the whole screen
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(screenSize.width, screenSize.height);

            frame.setVisible(true);
        });
    }
}
