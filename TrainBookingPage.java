import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class EnhancedTrainBookingPage extends JPanel {
    private JLabel usernameLabel, passwordLabel, originLabel, destinationLabel, classLabel, quotaLabel, filterLabel, sortLabel, dateLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> originComboBox, destinationComboBox, classComboBox, quotaComboBox, filterComboBox, sortComboBox, dateComboBox;
    private JButton bookButton;
    private JTextArea outputArea;

    public EnhancedTrainBookingPage() {
        setLayout(new BorderLayout());
        initializeComponents();
        layoutComponents();
        addListeners();
    }

    private void initializeComponents() {
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        usernameLabel = createLabel("Username:", labelFont);
        passwordLabel = createLabel("Password:", labelFont);
        originLabel = createLabel("Origin Station:", labelFont);
        destinationLabel = createLabel("Destination Station:", labelFont);
        classLabel = createLabel("Class:", labelFont);
        quotaLabel = createLabel("Quota:", labelFont);
        filterLabel = createLabel("Train Filter:", labelFont);
        sortLabel = createLabel("Sort By:", labelFont);
        dateLabel = createLabel("Date:", labelFont);

        usernameField = createTextField(fieldFont);
        passwordField = createPasswordField(fieldFont);

        String[] stations = {"SELECT", "Bhubaneswar (BBS)", "New Delhi (NDLS)", "Howrah (HWH)", "Mumbai Central (MMCT)", "Chennai Central (MAS)", "Brahmapur (BAM)", "Cuttack (CTC)", "Sealdah (SDAH)"};
        originComboBox = createComboBox(stations, fieldFont);
        destinationComboBox = createComboBox(stations, fieldFont);

        String[] classes = {"ANY", "1st AC (1A)", "2nd AC (2A)", "3rd AC (3A)", "Sleeper (SL)", "AC Chair Car (CC)", "3rd AC Economy (3E)", "Second Seating (2S)", "General (GS)"};
        classComboBox = createComboBox(classes, fieldFont);

        String[] quotas = {"General", "Tatkal", "Ladies", "Senior Citizen"};
        quotaComboBox = createComboBox(quotas, fieldFont);

        String[] filters = {"ANY", "Rajdhani", "Shatabdi", "Duronto", "Superfast", "Mail", "Special", "Vande Bharat", "Humsafar", "Garib Rath", "Sampark Kranti", "Amrit Bharat"};
        filterComboBox = createComboBox(filters, fieldFont);

        String[] sorts = {"Lowest duration", "Highest duration", "Earliest Arrival", "Latest Arrival", "Earliest Departure", "Latest Departure"};
        sortComboBox = createComboBox(sorts, fieldFont);

        dateComboBox = createComboBox(generateDates(), fieldFont);

        bookButton = new JButton("Book Now");
        bookButton.setFont(labelFont);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    }

    private void layoutComponents() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        addComponent(inputPanel, usernameLabel, usernameField, gbc, 0);
        addComponent(inputPanel, passwordLabel, passwordField, gbc, 1);
        addComponent(inputPanel, originLabel, originComboBox, gbc, 2);
        addComponent(inputPanel, destinationLabel, destinationComboBox, gbc, 3);
        addComponent(inputPanel, quotaLabel, quotaComboBox, gbc, 4);
        addComponent(inputPanel, dateLabel, dateComboBox, gbc, 5);
        addComponent(inputPanel, classLabel, classComboBox, gbc, 6);
        addComponent(inputPanel, sortLabel, sortComboBox, gbc, 7);
        addComponent(inputPanel, filterLabel, filterComboBox, gbc, 8);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(bookButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    bookTicket();
                } else {
                    JOptionPane.showMessageDialog(EnhancedTrainBookingPage.this,
                            "Please fill all required fields correctly.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateInputs() {
        return !usernameField.getText().trim().isEmpty() &&
                passwordField.getPassword().length > 0 &&
                !originComboBox.getSelectedItem().equals("SELECT") &&
                !destinationComboBox.getSelectedItem().equals("SELECT") &&
                !originComboBox.getSelectedItem().equals(destinationComboBox.getSelectedItem()) &&
                !dateComboBox.getSelectedItem().equals("SELECT");
    }

    private void bookTicket() {
        Random random = new Random();
        String[] trains = {"12261/Mumbai CSMT-Howrah AC Duronto Express", "12302/New Delhi-Howrah Rajdhani Express"};
        String[] coaches = {"A1", "A2", "B1", "B2"};
        String[] berths = {"Lower", "Middle", "Upper", "Side Lower", "Side Upper"};

        StringBuilder output = new StringBuilder();
        output.append("Booking Details:\n");
        output.append("Passenger Name: ").append(usernameField.getText()).append("\n");
        output.append("Train: ").append(trains[random.nextInt(trains.length)]).append("\n");
        output.append("Class: ").append(classComboBox.getSelectedItem()).append("\n");
        output.append("Date: ").append(dateComboBox.getSelectedItem()).append("\n");
        output.append("From: ").append(originComboBox.getSelectedItem())
              .append(" To: ").append(destinationComboBox.getSelectedItem()).append("\n");
        output.append("Quota: ").append(quotaComboBox.getSelectedItem()).append("\n");
        output.append("Food opted: ").append(random.nextBoolean() ? "VEG" : "NON-VEG").append("\n");
        output.append("Price: ").append(random.nextInt(4000) + 1000).append(" INR\n");
        output.append("Coach ").append(coaches[random.nextInt(coaches.length)])
              .append(", Seat No: ").append(random.nextInt(72) + 1)
              .append(", ").append(berths[random.nextInt(berths.length)]).append("\n");

        outputArea.setText(output.toString());
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Font font) {
        JTextField field = new JTextField(15);
        field.setFont(font);
        return field;
    }

    private JPasswordField createPasswordField(Font font) {
        JPasswordField field = new JPasswordField(15);
        field.setFont(font);
        return field;
    }

    private JComboBox<String> createComboBox(String[] items, Font font) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(font);
        return comboBox;
    }

    private void addComponent(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private String[] generateDates() {
        String[] dates = new String[31];
        dates[0] = "SELECT";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i < 31; i++) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dates[i] = sdf.format(cal.getTime());
        }
        return dates;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Enhanced Train Booking Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new EnhancedTrainBookingPage());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

