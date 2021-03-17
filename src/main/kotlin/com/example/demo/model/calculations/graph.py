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

plt.figure(figsize=(12, 10), dpi=100)

for i in range(n):
    plt.plot(osx[::-1], osy[i], label=labels[i])
    
plt.xscale('log')
plt.legend()
plt.savefig("stat.png")
