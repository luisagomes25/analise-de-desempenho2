import time
import psutil  # type: ignore
import os

# Função para realizar o Merge Sort
def merge_sort(arr):
    if len(arr) < 2:
        return arr

    mid = len(arr) // 2

    # Dividir a lista
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])

    # Mesclar as metades ordenadas
    return merge(left, right)

def merge(left, right):
    sorted_arr = []
    i = j = 0

    # Mesclando os dois arrays
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            sorted_arr.append(left[i])
            i += 1
        else:
            sorted_arr.append(right[j])
            j += 1

    # Adicionando os elementos restantes
    sorted_arr.extend(left[i:])
    sorted_arr.extend(right[j:])
    return sorted_arr

# Função principal para medir tempo e uso de memória
def measure_memory_and_time(file_path):
    # Leitura do arquivo
    with open(file_path, "r") as file:
        numbers = [int(line.strip()) for line in file]

    # Medir o tempo inicial
    start_time = time.time()

    # Ordenar com Merge Sort
    sorted_numbers = merge_sort(numbers)

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
    execution_time, memory_usage = measure_memory_and_time(input_file)
    print(f"Tempo de execução: {execution_time:.2f} ms")
    print(f"Uso de memória: {memory_usage:.2f} KB")
