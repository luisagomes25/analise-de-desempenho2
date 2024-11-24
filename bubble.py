import time
import psutil  # type: ignore
import os

def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr

def measure_memory_and_time(file_path):
    # Verifica se o arquivo existe
    if not os.path.exists(file_path):
        raise FileNotFoundError(f"Arquivo não encontrado: {file_path}")

    # Leitura do arquivo
    with open(file_path, "r") as file:
        numbers = [int(line.strip()) for line in file]

    # Medir o tempo inicial
    start_time = time.time()

    # Algoritmo Bubble Sort
    sorted_numbers = bubble_sort(numbers)

    # Medir o tempo final
    end_time = time.time()
    execution_time_ms = (end_time - start_time) * 1000  # Em milissegundos

    # Gravar a saída ordenada
    with open("output.txt", "w") as file:
        for num in sorted_numbers:
            file.write(f"{num}\n")

    # Medir memória usada
    process = psutil.Process(os.getpid())
    memory_usage_kb = process.memory_info().rss / 1024  # Em KB

    return execution_time_ms, memory_usage_kb

if __name__ == "__main__":
    input_file = "input.txt"
    
    try:
        execution_time, memory_usage = measure_memory_and_time(input_file)
        print(f"Tempo de execução: {execution_time:.2f} ms")
        print(f"Uso de memória: {memory_usage:.2f} KB")
    except FileNotFoundError as e:
        print(e)
        print("Por favor, verifique se o arquivo input.txt está no local correto.")

