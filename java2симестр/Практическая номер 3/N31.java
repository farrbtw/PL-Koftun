import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n31 {
    public static Integer chooseBestSum(int maxDistance, int citiesToVisit, List<Integer> distances) {
        // Если невозможно выбрать нужное количество городов
        if (citiesToVisit > distances.size()) {
            return null;
        }
        
        // Генерируем все комбинации и находим максимальную подходящую сумму
        List<List<Integer>> combinations = generateCombinations(distances, citiesToVisit);
        int maxSum = -1;
        
        for (List<Integer> combo : combinations) {
            int sum = combo.stream().mapToInt(Integer::intValue).sum();
            if (sum <= maxDistance && sum > maxSum) {
                maxSum = sum;
            }
        }
        
        return maxSum == -1 ? null : maxSum;
    }
    
    // Метод для генерации всех комбинаций из k элементов
    private static List<List<Integer>> generateCombinations(List<Integer> list, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsHelper(list, k, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinationsHelper(List<Integer> list, int k, int start, 
                                                 List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < list.size(); i++) {
            current.add(list.get(i));
            generateCombinationsHelper(list, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        List<Integer> distances = Arrays.asList(50, 55, 57, 58, 60);
        Integer result = chooseBestSum(175, 3, distances);
        System.out.println(result); 
    }
}
