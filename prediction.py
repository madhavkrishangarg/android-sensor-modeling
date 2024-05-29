import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from statsmodels.tsa.arima.model import ARIMA


data = pd.read_csv('accelerator_database-accelerator_table.csv')


X = data['x'].values
model_x = ARIMA(X, order=(5, 1, 0))
model_fit_x = model_x.fit()
output_x = model_fit_x.forecast(steps=100)


plt.plot(np.arange(len(X)), X, label='Actual x')
plt.plot(np.arange(len(X), len(X) + len(output_x)), output_x, label='Predicted x')
plt.xlabel('Time')
plt.ylabel('x angle')
plt.legend()
plt.title('Predicted vs Actual Values for x')
plt.show()


Y = data['y'].values
model_y = ARIMA(Y, order=(5, 1, 0))
model_fit_y = model_y.fit()
output_y = model_fit_y.forecast(steps=100)


plt.plot(np.arange(len(Y)), Y, label='Actual y')
plt.plot(np.arange(len(Y), len(Y) + len(output_y)), output_y, label='Predicted y')
plt.xlabel('Time')
plt.ylabel('y angle')
plt.legend()
plt.title('Predicted vs Actual Values for y')
plt.show()


Z = data['z'].values
model_z = ARIMA(Z, order=(5, 1, 0))
model_fit_z = model_z.fit()
output_z = model_fit_z.forecast(steps=100)


plt.plot(np.arange(len(Z)), Z, label='Actual z')
plt.plot(np.arange(len(Z), len(Z) + len(output_z)), output_z, label='Predicted z')
plt.xlabel('Time')
plt.ylabel('z angle')
plt.legend()
plt.title('Predicted vs Actual Values for z')
plt.show()
