import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ============================================================
 *  WordCounterGUI.java : GUI (window) version of the Word Counter
 *  Run THIS file to open the window.
 *  It uses the same WordCounter class (basic + INNOVATION features).
 *
 *  OOPs concepts used: inheritance (extends JFrame), interface
 *  (implements ActionListener), object creation, encapsulation.
 * ============================================================
 */
public class WordCounterGUI extends JFrame implements ActionListener {

    // object of our WordCounter class (does all the counting work)
    private WordCounter counter;

    // GUI components
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JTextField searchField;
    private JButton basicButton;
    private JButton insightsButton;   // INNOVATION button
    private JButton searchButton;
    private JButton clearButton;

    // Constructor: builds the window
    public WordCounterGUI() {
        super("Word Counter + Smart Insights");
        counter = new WordCounter("");

        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // open in the centre of the screen
        setLayout(new BorderLayout(10, 10));

        // ----- Top: title -----
        JLabel title = new JLabel("WORD COUNTER  +  SMART INSIGHTS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ----- Centre: input box + buttons + output box -----
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // 1) input text area
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Type or paste your text here"));
        centerPanel.add(inputScroll);

        // 2) buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        basicButton = new JButton("Basic Report");
        insightsButton = new JButton("Smart Insights (INNOVATION)");
        insightsButton.setBackground(new Color(255, 200, 80));
        insightsButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchField = new JTextField(10);
        searchButton = new JButton("Search Word");
        clearButton = new JButton("Clear");

        // tell the buttons that this class will handle their clicks
        basicButton.addActionListener(this);
        insightsButton.addActionListener(this);
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(basicButton);
        buttonPanel.add(insightsButton);
        buttonPanel.add(new JLabel("Word:"));
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        centerPanel.add(buttonPanel);

        // 3) output text area (read only)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Result"));
        centerPanel.add(outputScroll);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // This method runs when any button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();

        // Clear button: reset everything
        if (clicked == clearButton) {
            inputArea.setText("");
            outputArea.setText("");
            searchField.setText("");
            return;
        }

        // For other buttons we need some text
        String text = inputArea.getText();
        if (text.trim().isEmpty()) {
            outputArea.setText("Please type or paste some text first.");
            return;
        }
        counter.setText(text); // give the text to the WordCounter object

        if (clicked == basicButton) {
            outputArea.setText(counter.getBasicReport());

        } else if (clicked == insightsButton) {
            // INNOVATION feature
            outputArea.setText(counter.getSmartInsights());

        } else if (clicked == searchButton) {
            String word = searchField.getText().trim();
            if (word.isEmpty()) {
                outputArea.setText("Please enter a word to search.");
            } else {
                int times = counter.countOccurrences(word);
                outputArea.setText("The word \"" + word + "\" appears " + times + " time(s).");
            }
        }
        outputArea.setCaretPosition(0); // scroll result to the top
    }

    // main method: starts the GUI
    public static void main(String[] args) {
        new WordCounterGUI();
    }
}