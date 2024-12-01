import java.io.*;
import java.util.*;

/**
 * Project: Voynich Analyzer
 * Purpose Details: Analyzing the Voynich Manuscript text for frequency and cryptanalysis
 * Author: Aayudh Nandiwdekar
 * Date Developed: December 1, 2024
 * Last Date Changed: December 1, 2024
 * Revision: 1
 */
public class VoynichAnalyzer {

    private String text;

    // Constructor that loads the text from a file
    public VoynichAnalyzer(String filePath) throws IOException {
        this.text = readFile(filePath);
    }

    // Reads the file content and returns as a String
    private String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    // Count frequency of characters in the text
    private Map<Character, Integer> getCharacterFrequency() {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '.') { // Include letters, digits, and periods
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
        }
        return frequencyMap;
    }

    // Print the frequency analysis results
    public void printFrequencyAnalysis() {
        Map<Character, Integer> frequencyMap = getCharacterFrequency();
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Character Frequency Analysis:");
        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method to execute the analysis
    public void performAnalysis() {
        System.out.println("Performing Voynich Manuscript Analysis on Folio 93v...");
        printFrequencyAnalysis();
    }

    // Main method to run the program
    public static void main(String[] args) {
        try {
            // Specify the path to the folio93v.txt file
            VoynichAnalyzer analyzer = new VoynichAnalyzer("folio93v.txt");
            analyzer.performAnalysis();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
