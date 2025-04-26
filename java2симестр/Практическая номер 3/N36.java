import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class n36 {

    public static void main(String[] args) {
        try {
            String referenceText = getFishText(10000);
            Map<Character, Double> referenceFrequencies = calculateFrequencies(referenceText);
            
            
            String encryptedText = "Khoor, zruog! L oryh fdqvirxpb."; // "Hello, world! I love cryptography."
            
            // 4. Определяем ключ шифра
            int key = findCaesarKey(encryptedText, referenceFrequencies);
            System.out.println("Найденный ключ: " + key);
            
            // 5. Расшифровываем текст
            String decryptedText = decryptCaesar(encryptedText, key);
            System.out.println("Расшифрованный текст: " + decryptedText);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод для получения текста с fish-text.ru
    private static String getFishText(int charCount) throws Exception {
        URL url = new URL("https://fish-text.ru/get?&number=" + charCount);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        
        // Парсим JSON ответ (упрощенно, без библиотек)
        String text = response.toString();
        text = text.substring(text.indexOf("\"text\":\"") + 8);
        text = text.substring(0, text.indexOf("\"}"));
        text = text.replace("\\n", "\n").replace("\\\"", "\"");
        
        return text.toLowerCase(); // Приводим к нижнему регистру
    }

    // Метод для расчета частот букв в тексте
    private static Map<Character, Double> calculateFrequencies(String text) {
        Map<Character, Double> frequencies = new HashMap<>();
        int totalLetters = 0;
        
        // Инициализируем счетчики для всех букв
        for (char c = 'а'; c <= 'я'; c++) {
            frequencies.put(c, 0.0);
        }
        frequencies.put('ё', 0.0);
        
        // Подсчитываем буквы
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                if (lowerC >= 'а' && lowerC <= 'я' || lowerC == 'ё') {
                    frequencies.put(lowerC, frequencies.get(lowerC) + 1);
                    totalLetters++;
                }
            }
        }
        
        // Переводим в проценты
        if (totalLetters > 0) {
            for (char c : frequencies.keySet()) {
                frequencies.put(c, (frequencies.get(c) / totalLetters) * 100);
            }
        }
        
        return frequencies;
    }

    // Метод для определения ключа шифра Цезаря
    private static int findCaesarKey(String encryptedText, Map<Character, Double> referenceFrequencies) {
        Map<Character, Double> encryptedFrequencies = calculateFrequencies(encryptedText);
        
        double minDifference = Double.MAX_VALUE;
        int bestKey = 0;
        
        // Проверяем все возможные ключи (1-32 для русского алфавита)
        for (int key = 1; key <= 32; key++) {
            double difference = 0;
            
            // Для каждой буквы вычисляем разницу частот
            for (char c : referenceFrequencies.keySet()) {
                char encryptedChar = (char)(((c - 'а' - key + 32) % 32) + 'а');
                double refFreq = referenceFrequencies.get(c);
                double encFreq = encryptedFrequencies.getOrDefault(encryptedChar, 0.0);
                difference += Math.abs(refFreq - encFreq);
            }
            
            // Находим ключ с минимальной разницей
            if (difference < minDifference) {
                minDifference = difference;
                bestKey = key;
            }
        }
        
        return bestKey;
    }

    // Метод для расшифровки текста с известным ключом
    private static String decryptCaesar(String text, int key) {
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'а' : 'А';
                int offset = (c - base - key + 32) % 32;
                result.append((char)(base + offset));
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}
