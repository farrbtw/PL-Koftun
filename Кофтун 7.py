n = int(input("Введите количество элемнтов массива: "))
a = [float(input(f"Введите {i + 1}-й элемент массива"))for i in range(n)]
min = min(a, key=abs)
print(f"Минимальный по модулю: {min}")
print("Массив в обратном порядке", a[::-1])
