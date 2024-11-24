package atividade2;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class merche {

    // Método para realizar o Merge Sort
    public static void mergeSort(List<Integer> arr) {
        if (arr.size() < 2) {
            return; // Lista já está ordenada
        }

        int mid = arr.size() / 2;

        // Dividir a lista
        List<Integer> left = new ArrayList<>(arr.subList(0, mid));
        List<Integer> right = new ArrayList<>(arr.subList(mid, arr.size()));

        // Ordenar cada metade
        mergeSort(left);
        mergeSort(right);

        // Mesclar as metades ordenadas
        merge(arr, left, right);
    }

    private static void merge(List<Integer> arr, List<Integer> left, List<Integer> right) {
        int i = 0, j = 0, k = 0;

        // Mesclando os dois arrays
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k++, left.get(i++));
            } else {
                arr.set(k++, right.get(j++));
            }
        }

        // Adicionando os elementos restantes
        while (i < left.size()) {
            arr.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            arr.set(k++, right.get(j++));
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

        // Realizar o Merge Sort
        mergeSort(numbers);

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
