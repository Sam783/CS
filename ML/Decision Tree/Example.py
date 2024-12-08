from sklearn.tree import DecisionTreeClassifier
from sklearn import tree
import matplotlib.pyplot as plt

X = [[0,1],[1,0],[1,1],[0,0]]
y = [0,1,1,0]

dtc = DecisionTreeClassifier(criterion='entropy')
dtc.fit(X,y)

tree.plot_tree(dtc,filled=True)
plt.show()
