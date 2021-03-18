import numpy as np
from matplotlib import pyplot as plt

plt.figure(figsize=(7, 6), dpi=200)

f = lambda x: (x - 1) * (x - 2) * (x - 3) * (x - 5) * (x - 9) * (x - 10)

left = 0
right = 10
osx = np.linspace(left, right, 200)
osy = f(osx)

points = [(4.188842854553222, -188.1868674515756), (4.189201534807236, -188.18675962171977), (4.1886688271387875, -188.186897025231), (4.188223553273002, -188.18690505958259) ,(4.1888427735186164, -188.18686746880417)]

pointsx = [i[0] for i in points]
pointsy = [i[1] for i in points]

plt.plot(osx, osy, label='f')
plt.scatter(pointsx, pointsy, color='red', label='Найденный экстремум')

min_x = 9.5756
min_y = f(min_x)
plt.scatter(min_x, min_y, color='orange', label='Экстремум')
plt.xlabel("x")
plt.ylabel("f(x)")
plt.title("(x - 1) * (x - 2) * (x - 3) * (x - 5) * (x - 9) * (x - 10)")
plt.legend()

plt.savefig("multimodal.png")
