# Mini-project: 𝑘-NN
The goal is to implement the 𝑘-NN classifier. The program should take 3 arguments:
k: positive natural number being the k-NN hyperparameter.
train-set: name of the file containing the training set in csv format.
test-set: name of the file containing the test set.

## Requirements:
* The program should apply 𝑘-NN classifier based on the train set to each vector from
the test set and produce the accuracy (proportion of correctly classified examples
from the test set).
* The program should additionally provide a simple interface (not necessarily graphical) to enable the user to input single vectors to be classified.
* Test the program using training data in iris.data and test data in iris.test.data.
* Important: the program should be able to load any dataset (in a format similar
to iris.data), with an arbitrary number of dimensions/classes.
* Optionally: prepare a graph (excel, python, etc.) showing the accuracy vs the
value of 𝑘.
* Optionally: also classify examples in the WDBC dataset provided in the files
wdbc.data and wdbc.test.data.
