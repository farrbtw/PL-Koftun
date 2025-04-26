import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class n37 {
    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 20;
    private static final int MAX_NUMBER = 9; // числа от 0 до 9
    
    private int[] secretCode;
    private int attempts;
    private Scanner scanner;
    
    public n37() {
        scanner = new Scanner(System.in);
        generateSecretCode();
        attempts = 0;
    }
    
    private void generateSecretCode() {
        Random random = new Random();
        secretCode = new int[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            secretCode[i] = random.nextInt(MAX_NUMBER + 1);
        }
    }
    
    public void play() {
        System.out.println("Угадайте 4-значный код (числа от 0 до 9). У вас " + MAX_ATTEMPTS + " попыток.");
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Попытка " + (attempts + 1) + ": Введите 4 числа через пробел: ");
            int[] guess = readGuess();
            
            if (guess == null) {
                continue;
            }
            
            int matches = countMatches(guess);
            System.out.println("Совпадений: " + matches);
            
            if (matches == CODE_LENGTH) {
                System.out.println("Поздравляем! Вы угадали код за " + (attempts + 1) + " попыток!");
                return;
            }
            
            attempts++;
        }
        
        System.out.println("Вы исчерпали все попытки. Загаданный код был: " + Arrays.toString(secretCode));
    }
    
    private int[] readGuess() {
        String[] parts = scanner.nextLine().split(" ");
        if (parts.length != CODE_LENGTH) {
            System.out.println("Пожалуйста, введите ровно " + CODE_LENGTH + " числа.");
            return null;
        }
        
        int[] guess = new int[CODE_LENGTH];
        try {
            for (int i = 0; i < CODE_LENGTH; i++) {
                guess[i] = Integer.parseInt(parts[i]);
                if (guess[i] < 0 || guess[i] > MAX_NUMBER) {
                    System.out.println("Числа должны быть от 0 до " + MAX_NUMBER + ".");
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, вводите только целые числа.");
            return null;
        }
        
        return guess;
    }
    
    private int countMatches(int[] guess) {
        int count = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess[i] == secretCode[i]) {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        n37 game = new n37();
        game.play();
    }
}
