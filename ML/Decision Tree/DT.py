from sklearn.tree import DecisionTreeClassifier
from sklearn import tree
import matplotlib.pyplot as plt
from sklearn.preprocessing import LabelEncoder
import pandas as pd

# df = pd.read_csv('dataset.csv')

# label_encoder = LabelEncoder()
# for col in df.columns:
#     if df[col].dtype == 'object':
#         df[col] = label_encoder.fit_transform(df[col])

# X = df.drop(['Buys Computer'], axis=1)
# y = df['Buys Computer']


# X : [Age,Income]
# Age : Youth=0, Middle-Age=1, Senior=2
# Income : Low=0, Medium=1, High=2

X = [
    [0,2],[0,2],[1,2],[2,1],[2,0],[2,0],[1,0],[0,1],[0,0],[2,1]
]
y = [0,0,1,1,1,0,1,0,1,1]

dtc = DecisionTreeClassifier(criterion='entropy')
dtc.fit(X,y)
tree.plot_tree(dtc,filled=True,feature_names=['Age','Income'],class_names=["No","Yes"])
plt.show()