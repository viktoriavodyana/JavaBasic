package lab;

import java.util.ArrayList;
import java.util.List;

public class Lab4 implements LabInterface {
    private final String value;
    private final char startChar;
    private final char endChar;

    public Lab4(String valueString, char startChar, char endChar) {
        this.value = valueString;
        this.startChar = startChar;
        this.endChar = endChar;
    }

    @Override
    public void execute() {
        Text myText = new Text(value);

        myText.replaceTabsAndSpaces();  // Заміна табуляцій та пробілів

        myText.removeLongestSubstring(startChar, endChar);  // Видалення підрядка між літерами

        System.out.println("Текст після видалення підрядка: " + myText);
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C3 = " + scoreBookNumber % 3);
        System.out.println("C17 = " + scoreBookNumber % 17);
    }

    // Клас для літер
    class Letter {
        private char value;

        public Letter(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Клас для слова
    class Word {
        private List<Letter> letters;

        public Word(String word) {
            letters = new ArrayList<>();
            for (char c : word.toCharArray()) {
                letters.add(new Letter(c));
            }
        }

        public List<Letter> getLetters() {
            return letters;
        }

        public String getWord() {
            String word = "";
            for (Letter letter : letters) {
                word += letter.getValue();  // Не використовуємо StringBuilder
            }
            return word;
        }

        @Override
        public String toString() {
            return getWord();
        }
    }

    // Клас для розділових знаків
    class Punctuation {
        private char value;

        public Punctuation(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Клас для речення
    class Sentence {
        private List<Word> words;
        private List<Punctuation> punctuationMarks;

        public Sentence(String sentence) {
            words = new ArrayList<>();
            punctuationMarks = new ArrayList<>();
            String[] sentenceParts = sentence.split(" ");

            for (String part : sentenceParts) {
                if (part.matches("[\\p{L}\\p{N}]+")) {
                    words.add(new Word(part));
                } else if (part.matches("[\\p{Punct}\\p{IsPunctuation}]")) {
                    punctuationMarks.add(new Punctuation(part.charAt(0)));
                }
            }
        }

        public List<Word> getWords() {
            return words;
        }

        public List<Punctuation> getPunctuationMarks() {
            return punctuationMarks;
        }

        @Override
        public String toString() {
            String sentence = "";
            for (Word word : words) {
                sentence += word.getWord() + " ";  // Не використовуємо StringBuilder
            }
            for (Punctuation punctuation : punctuationMarks) {
                sentence += punctuation.getValue();
            }
            return sentence.trim();
        }

        // Метод для видалення найбільшого підрядка між двома літерами
        public void removeLongestSubstring(char start, char end) {
            String sentenceText = this.toString();
            int startIndex = sentenceText.indexOf(start);
            int endIndex = sentenceText.lastIndexOf(end);

            if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
                // Видаляємо найбільший підрядок між цими літерами
                String modifiedSentence = sentenceText.substring(0, startIndex + 1) +
                        sentenceText.substring(endIndex);
                // Оновлюємо речення
                this.words.clear();
                this.punctuationMarks.clear();
                String[] sentenceParts = modifiedSentence.split(" ");
                for (String part : sentenceParts) {
                    if (part.matches("[\\w]+")) {
                        words.add(new Word(part));
                    } else if (part.matches("[\\p{Punct}]")) {
                        punctuationMarks.add(new Punctuation(part.charAt(0)));
                    }
                }
            }
        }
    }

    // Клас для тексту
    class Text {
        private List<Sentence> sentences;

        public Text(String text) {
            this.sentences = new ArrayList<>();
            String[] sentenceParts = text.split("(?<=\\.)"); // Розділяємо текст на речення
            for (String part : sentenceParts) {
                sentences.add(new Sentence(part.trim()));
            }
        }

        public List<Sentence> getSentences() {
            return sentences;
        }

        @Override
        public String toString() {
            String text = "";
            for (Sentence sentence : sentences) {
                text += sentence.toString() + " ";  // Не використовуємо StringBuilder
            }
            return text.trim();
        }

        // Метод для заміни пробілів та табуляцій
        public void replaceTabsAndSpaces() {
            String replacedText = toString().replaceAll("\\s+", " "); // Заміна всіх пробілів і табуляцій на один пробіл
            System.out.println("Текст після заміни пробілів: " + replacedText);
        }

        // Метод для видалення найбільшого підрядка
        public void removeLongestSubstring(char start, char end) {
            for (Sentence sentence : sentences) {
                sentence.removeLongestSubstring(start, end);
            }
        }
    }
}
