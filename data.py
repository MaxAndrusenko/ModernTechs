import matplotlib.pyplot as plt
from sklearn.datasets import fetch_california_housing
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score

# Завантаження даних
housing = fetch_california_housing()
X = housing.data[:, 5].reshape(-1, 1)  # Одна з ознак (median income)
y = housing.target

# Побудова моделі лінійної регресії
model = LinearRegression()
model.fit(X, y)

# Прогнозування значень на основі моделі
predicted = model.predict(X)

# Визначення показників якості моделі
mse = mean_squared_error(y, predicted)
r2 = r2_score(y, predicted)
print(f"mse {mse} r2 {r2}")
# Відображення даних та регресійної лінії
plt.scatter(X, y, color='blue', label='Actual data')
plt.plot(X, predicted, color='red', linewidth=3, label='Regression line')
plt.xlabel('Median Income')
plt.ylabel('House Value')
plt.title('California Housing Prices\nMSE: {:.2f}, R-squared: {:.2f}'.format(mse, r2))
plt.legend()
plt.show()
