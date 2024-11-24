import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class bubble {

    // Método para realizar o Bubble Sort
    public static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // Troca de elementos
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    // Método principal para medir tempo e executar a lógica
    public static void measureMemoryAndTime(String filePath) throws IOException {
        // Ler números do arquivo
        List<Integer> numbers = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(filePath))) {
            numbers.add(Integer.parseInt(line.trim()));
        }

        // Medir tempo inicial
        long startTime = System.nanoTime();

        // Realizar o Bubble Sort
        bubbleSort(numbers);

        // Medir tempo final
        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        // Escrever números ordenados em um arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (int num : numbers) {
                writer.write(num + "\n");
            }
        }

        // Obter o uso de memória
        long memoryUsageKb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

        // Exibir resultados
        System.out.printf("Tempo de execução: %.2f ms%n", executionTimeMs);
        System.out.printf("Uso de memória: %d KB%n", memoryUsageKb);
    }

    public static void main(String[] args) {
        String inputFile = "input.txt";

        try {
            measureMemoryAndTime(inputFile);
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
}
