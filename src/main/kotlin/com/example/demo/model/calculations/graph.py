import numpy as np
from matplotlib import pyplot as plt

n = 5
labels = ["Метод Дихотомии", "Метод Золотого сечения", "Метод Фибоначчи", "Метод Парабол", "Метод Брента"]
osx = []
osy = []
with open("stat.txt", "r") as f:
    osx = eval(f.readline())
    for i in range(n):
        osy.append(eval(f.readline()))
        
print(osx)
print(osy)

plt.figure(figsize=(7, 6), dpi=200)

for i in range(n):
    plt.plot(osx, osy[i], label=labels[i])
    
plt.gca().invert_xaxis()
plt.xlabel("Точность")
plt.ylabel("Количество вычислений функции f(x)")
plt.xscale('log')
plt.legend()
plt.savefig("stat.png")
