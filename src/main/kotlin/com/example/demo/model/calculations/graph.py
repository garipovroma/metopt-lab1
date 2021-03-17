import numpy as np
from matplotlib import pyplot as plt

n = 3
labels = ["Метод Дихотомии", "Метод Золотого сечения", "Метод Фибоначчи", "Метод Парабол", "Метод Брента"]
osx = []
osy = []
with open("stat.txt", "r") as f:
    osx = eval(f.readline())
    for i in range(n):
        osy.append(eval(f.readline()))
        
for i in range(n):
    plt.plot(osx, osy[i], label=labels[i])
plt.savefig("stat.png")
