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

    private WordCounter counter;

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JTextField searchField;
    private JButton basicButton;
    private JButton insightsButton;   
    private JButton searchButton;
    private JButton clearButton;

    public WordCounterGUI() {
        super("Word Counter + Smart Insights");
        counter = new WordCounter("");

        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("WORD COUNTER  +  SMART INSIGHTS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane inputScroll = new JScrollPane(inputArea);
        inputScroll.setBorder(BorderFactory.createTitledBorder("Type or paste your text here"));
        centerPanel.add(inputScroll);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        basicButton = new JButton("Basic Report");
        insightsButton = new JButton("Smart Insights (INNOVATION)");
        insightsButton.setBackground(new Color(255, 200, 80));
        insightsButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchField = new JTextField(10);
        searchButton = new JButton("Search Word");
        clearButton = new JButton("Clear");

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

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Result"));
        centerPanel.add(outputScroll);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();

        if (clicked == clearButton) {
            inputArea.setText("");
            outputArea.setText("");
            searchField.setText("");
            return;
        }

        String text = inputArea.getText();
        if (text.trim().isEmpty()) {
            outputArea.setText("Please type or paste some text first.");
            return;
        }
        counter.setText(text); 

        if (clicked == basicButton) {
            outputArea.setText(counter.getBasicReport());

        } else if (clicked == insightsButton) {
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
        outputArea.setCaretPosition(0); 
    }

    public static void main(String[] args) {
        new WordCounterGUI();
    }
}
