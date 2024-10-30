m=int(input("Введите количество чисел "))
f=0
s=1
d=0
for i in range(m):
    print(s, end=' ')
    d=f
    f=s
    s+=d
