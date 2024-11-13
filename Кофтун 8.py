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




#2

from random import*
def pro(n):
    k=1
    for i in n:
        k*=i
    return k

def sr_ar(n):
    k=0
    for i in n:
        k+=i
    return k/len(n)

m1=[randint(1,10) for i in range(randint(3,10))]
m2=[randint(1,10) for i in range(randint(3,10))]
m3=[randint(1,10) for i in range(randint(3,10))]
print('Первый:',m1)
print('Произведение элеменов первого '+str(pro(m1))+', а их среднее арифметическое -','{:.2f}'.format(sr_ar(m1)))
print()
print('Второй:',m2)
print('Произведение элеменов второго  '+str(pro(m2))+', а их среднее арифметическое -','{:.2f}'.format(sr_ar(m2)))
print()
print('Третий:',m3)
print('Произведение элеменов третьего  '+str(pro(m3))+', а их среднее арифметическое -','{:.2f}'.format(sr_ar(m3)))
