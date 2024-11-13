#1
def calculate_steps(number):
  steps = 0
  while number > 0:
    sum_of_digits = sum(int(digit) for digit in str(number))
    number -= sum_of_digits
    steps += 1
  return steps

number = 77777
steps = calculate_steps(number)
print(f"Для числа {number} требуется {steps} шагов.")
