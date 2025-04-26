import java.util.Arrays;
import java.util.Comparator;

public class n35 {
    public static void main(String[] args) {
        String input = "56 65 74 100 99 68 86 180 90";
        String result = orderWeights(input);
        System.out.println(result);
    }

    public static String orderWeights(String weights) {
        if (weights.isEmpty()) return weights;
        
        // Разбиваем строку на массив чисел
        String[] numbers = weights.split(" ");
        
        // Создаем массив для хранения чисел и их сумм цифр
        NumberWithSum[] numberWithSums = new NumberWithSum[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String numStr = numbers[i];
            int sum = 0;
            for (char c : numStr.toCharArray()) {
                sum += Character.getNumericValue(c);
            }
            numberWithSums[i] = new NumberWithSum(numStr, sum, i);
        }
        
        // Сортируем с учетом суммы цифр и исходного порядка
        Arrays.sort(numberWithSums, new Comparator<NumberWithSum>() {
            @Override
            public int compare(NumberWithSum a, NumberWithSum b) {
                if (a.sum != b.sum) {
                    return Integer.compare(a.sum, b.sum);
                } else {
                    return Integer.compare(a.originalIndex, b.originalIndex);
                }
            }
        });
        
        // Собираем отсортированный результат
        StringBuilder sb = new StringBuilder();
        for (NumberWithSum nws : numberWithSums) {
            sb.append(nws.number).append(" ");
        }
        
        return sb.toString().trim();
    }
    
    static class NumberWithSum {
        String number;
        int sum;
        int originalIndex;
        
        NumberWithSum(String number, int sum, int originalIndex) {
            this.number = number;
            this.sum = sum;
            this.originalIndex = originalIndex;
        }
    }
}
