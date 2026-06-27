import java.util.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

public class Date_Calculator extends JFrame {

    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea resultArea;

    public Date_Calculator() {
        setTitle("Simple Date Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Start Date (YYYY-MM-DD): "));
        startDateField = new JTextField(15);
        startDateField.setText("2004-06-27");
        add(startDateField);

        add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField(15);
        endDateField.setText("2024-06-27"); // Default value
        add(endDateField);

        JButton calcButton = new JButton("Calculate");
        add(calcButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        calcButton.addActionListener(e -> runCalculations());

    }

    public void runCalculations() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDateField.getText(), formatter);
            LocalDate end = LocalDate.parse(endDateField.getText(), formatter);

            String res1 = calculateTotalDays(start, end);
            String res2 = calculateYearMonthDays(start, end);
            String res3 = calculateWeeksAndDays(start, end);

            resultArea.setText(res1 + "\n" + res2 + "\n" + res3);

        } catch (DateTimeParseException ex) {
            resultArea.setText("Error: Please use format YYYY-MM-DD (e.g., 2024-06-27)");
        }

    }

    // It calculates the total number of days between two dates
    public String calculateTotalDays(LocalDate startDate, LocalDate endDate) {

        Long TotalDays = ChronoUnit.DAYS.between(startDate, endDate);

        return "Total Days: " + TotalDays;

    }

    // This methods it calculates the Number of years and Months and Days
    public String calculateYearMonthDays(LocalDate startDate, LocalDate endDate) {

        // Number of Years
        Period YearPeriod = Period.between(startDate, endDate);
        int years = YearPeriod.getYears();

        // Number of Months
        Long TotalMonths = ChronoUnit.MONTHS.between(startDate, endDate);

        // Number of Days
        Long TotalDays = ChronoUnit.DAYS.between(startDate, endDate);

        return "Number of Years: " + years + "\nNumber of Months: " + TotalMonths + "\nNumber of Days: " + TotalDays;

    }

    public String calculateWeeksAndDays(LocalDate starDate, LocalDate endDate) {
        Long totalDays = ChronoUnit.DAYS.between(starDate, endDate);

        long weeks = totalDays / 7;
        long days = totalDays % 7;

        return "Number of Weeks: " + weeks;

    }

}
