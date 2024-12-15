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

        myText.removeLongestSubstring(startChar, endChar);
        System.out.println("Текст після видалення підрядка: " + myText);
    }

    @Override
    public void printVariant() {
        System.out.println("Номер заліковки: " + scoreBookNumber);
        System.out.println("C3 = " + scoreBookNumber % 3);
        System.out.println("C17 = " + scoreBookNumber % 17);
    }

    // Базовий абстрактний клас для всіх текстових елементів
    abstract class TextElement {
        protected String content;

        public TextElement(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    // Клас для представлення букви
    class Letter extends TextElement {
        public Letter(char letter) {
            super(String.valueOf(letter));
        }
    }

    // Клас для представлення знаку пунктуації
    class Punctuation extends TextElement {
        public Punctuation(char punct) {
            super(String.valueOf(punct));
        }
    }

    // Клас для представлення слова
    class Word extends TextElement {
        private List<TextElement> elements;

        public Word(String word) {
            super(word);
            this.elements = new ArrayList<>();
            parseWord(word);
        }

        private void parseWord(String word) {
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    elements.add(new Letter(c));
                } else if (!Character.isWhitespace(c)) {
                    elements.add(new Punctuation(c));
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (TextElement element : elements) {
                sb.append(element.toString());
            }
            return sb.toString();
        }
    }

    // Клас для представлення речення
    class Sentence extends TextElement {
        private List<Word> words;

        public Sentence(String sentence) {
            super(sentence);
            this.words = new ArrayList<>();
            parseSentence(sentence);
        }

        private void parseSentence(String sentence) {
            String[] wordArray = sentence.trim().split("\\s+");
            for (String word : wordArray) {
                if (!word.isEmpty()) {
                    words.add(new Word(word));
                }
            }
        }

        public void removeLongestSubstring(char a, char b) {
            int maxLength = 0;
            String longestSubstring = "";

            // Знаходимо найдовшу підстроку між символами a і b
            for (Word word : words) {
                String wordStr = word.toString();
                int startIndex = wordStr.indexOf(a);
                while (startIndex != -1) {
                    int endIndex = wordStr.indexOf(b, startIndex + 1);
                    if (endIndex != -1) {
                        String substring = wordStr.substring(startIndex, endIndex + 1);
                        if (substring.length() > maxLength) {
                            maxLength = substring.length();
                            longestSubstring = substring;
                        }
                    }
                    startIndex = wordStr.indexOf(a, startIndex + 1);
                }
            }

            // Видаляємо знайдену підстроку з кожного слова
            if (!longestSubstring.isEmpty()) {
                for (int i = 0; i < words.size(); i++) {
                    String wordStr = words.get(i).toString();
                    wordStr = wordStr.replace(longestSubstring, "");
                    words.set(i, new Word(wordStr));
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.size(); i++) {
                sb.append(words.get(i).toString());
                if (i < words.size() - 1) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }

    // Головний клас для представлення тексту
    class Text extends TextElement {
        private List<Sentence> sentences;

        public Text(String text) {
            super(text);
            this.sentences = new ArrayList<>();
            parseText(text);
        }

        private void parseText(String text) {
            String[] sentenceArray = text.split("[.!?]+");
            for (String sentence : sentenceArray) {
                if (!sentence.trim().isEmpty()) {
                    sentences.add(new Sentence(sentence.trim()));
                }
            }
        }

        public void removeLongestSubstring(char a, char b) {
            for (Sentence sentence : sentences) {
                sentence.removeLongestSubstring(a, b);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sentences.size(); i++) {
                sb.append(sentences.get(i).toString());
                if (i < sentences.size() - 1) {
                    sb.append(". ");
                }
            }
            return sb.toString();
        }
    }
}
