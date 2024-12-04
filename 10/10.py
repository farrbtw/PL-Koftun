with open('Koftun_y-243-vvod.txt', 'r') as file:
    lines = file.readlines()

mat = []
for line in lines:
    p = line.replace('[', '').replace(']', '').replace(',', '').strip()
    if p:
        row = list(map(int, p.split()))
        mat.append(row)

k = int(input('Ввести число k: '))

c = 0
list_1 = []
for i in range(len(mat)):
    for j in range(len(mat[i])):
        if mat[i][j] % k == 0:
            c += 1
            list_1.append(mat[i][j])

list_2 = []
for i in range(len(mat)):
    for j in range(len(mat[i])):
        list_2.append(abs(mat[i][j]))
mx = max(list_2)

for i in range(len(mat)):
    for j in range(len(mat[i])):
        if mat[i][j] == mx:
            str_ = i
            stolb = j
mat.pop(str_)
for i in range(len(mat)):
    mat[i].pop(stolb)

with open('Koftun_y-243-vivod.txt', 'w', encoding='utf-8') as file:
    file.write(f'Число элементов, кратных k - {c}, из которых {max(list_1)} является наибольшим \n\n')
    file.write('Матрица порядка n-1: \n')
    for row in mat:
        file.write(f'{row} \n')
