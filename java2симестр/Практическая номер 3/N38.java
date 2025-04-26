import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class n38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод количества игроков
        System.out.print("Введите количество игроков: ");
        int playersCount = scanner.nextInt();
        
        // Ввод номера игрока, с которого начинается игра
        System.out.print("Введите номер первого игрока: ");
        int startPlayer = scanner.nextInt();
        
        // Проверка корректности ввода
        if (startPlayer < 1 || startPlayer > playersCount) {
            System.out.println("Ошибка: номер первого игрока должен быть от 1 до " + playersCount);
            return;
        }
        
        List<Integer> result = playGame(playersCount, startPlayer);
        
        // Вывод списка выбывших игроков
        System.out.println("Список выбывших игроков: " + result);
        
        // Победитель - последний элемент в списке
        int winner = result.get(result.size() - 1);
        System.out.println("Победитель: " + winner);
    }

    public static List<Integer> playGame(int playersCount, int startPlayer) {
        List<Integer> players = new ArrayList<>();
        for (int i = 1; i <= playersCount; i++) {
            players.add(i);
        }

        List<Integer> result = new ArrayList<>();
        int currentIndex = players.indexOf(startPlayer);
        if (currentIndex == -1) {
            throw new IllegalArgumentException("Start player not found in the list");
        }

        while (!players.isEmpty()) {
            currentIndex = (currentIndex + 2) % players.size(); // Перемещаемся на 2 позиции (т.е. каждый третий)
            int removedPlayer = players.remove(currentIndex);
            result.add(removedPlayer);
        }

        return result;
    }
}
