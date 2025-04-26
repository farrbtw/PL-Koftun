import java.util.Scanner;

public class n39 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String text = scanner.nextLine();
        
        String transformedText = transformText(text);
        System.out.println("Результат:");
        System.out.println(transformedText);
    }

    public static String transformText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Обрабатываем слово с учетом возможных знаков препинания
            int punctuationPos = findPunctuationPosition(word);
            String coreWord = word.substring(0, punctuationPos);
            String punctuation = word.substring(punctuationPos);

            if (coreWord.length() > 0) {
                // Переносим первую букву в конец и добавляем "ауч"
                String transformed = coreWord.substring(1) + coreWord.charAt(0) + "ауч";
                result.append(transformed).append(punctuation).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

    private static int findPunctuationPosition(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                return i + 1;
            }
        }
        return 0;
    }
}
