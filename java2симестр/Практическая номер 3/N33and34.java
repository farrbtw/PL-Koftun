import java.util.Scanner;

public class n33n34 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число палочек: ");
        long n = scanner.nextLong();
        long tanyaSticks = 0;
        boolean tanyaTurn = true;
        
        while (n > 0) {
            if (n % 2 == 1) {
                // Нечетное количество - берем 1 палочку
                if (tanyaTurn) {
                    tanyaSticks += 1;
                }
                n -= 1;
            } else {
                // Четное количество - выбираем оптимальный ход
                long half = n / 2;
                if (half == 1) {
                    // Если половина равна 1, то выбор между 1 и 1 (без разницы)
                    if (tanyaTurn) {
                        tanyaSticks += 1;
                    }
                    n -= 1;
                } else {
                    // Берем половину, если это выгодно
                    if (tanyaTurn) {
                        tanyaSticks += half;
                    }
                    n = half;
                }
            }
            tanyaTurn = !tanyaTurn;
        }
        
        System.out.println(tanyaSticks);
    }
}
