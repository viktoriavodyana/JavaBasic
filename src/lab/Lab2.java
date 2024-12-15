package lab;

public class Lab2 implements LabInterface {
    private final String value;
    private final char startChar;
    private final char endChar;

    public Lab2(String valueString, char startChar, char endChar) {
        this.value = valueString;
        this.startChar = startChar;
        this.endChar = endChar;
    }

    @Override
    public void execute() {
        System.out.println("Речення: " + value + " Перша літера: " + startChar + " Остання літера: " + endChar);
        String result = removeLongestSubstring(value, startChar, endChar);
        System.out.println("Результат: " + result);
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C3 = " + scoreBookNumber % 3);
        System.out.println("C17 = " + scoreBookNumber % 17);
    }

    public String getValue() {
        return value;
    }

    private static String removeLongestSubstring(String text, char startChar, char endChar) {
        String[] sentences = text.split("(?<=\\.)");
        String result = "";

        for (String sentence : sentences) {
            sentence = removeSubstring(sentence, startChar, endChar);
            result += sentence + " ";
        }

        return result.trim();
    }

    private static String removeSubstring(String sentence, char startChar, char endChar) {
        int startIdx = sentence.indexOf(startChar);
        int endIdx = sentence.lastIndexOf(endChar);

        if (startIdx != -1 && endIdx != -1 && endIdx > startIdx) {
            return sentence.substring(0, startIdx) + sentence.substring(endIdx + 1);
        }
        return sentence;
    }
}
