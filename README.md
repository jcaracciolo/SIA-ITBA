# SIA-ITBA
Cursada 2018 SIA - GRUPO 1

# Informed search
 To play the game go to https://jayisgames.com/games/lightforce/games/chainreaction.php

## Import project with maven, after that everything should work. Make sure to have Kotlin plugin installed

## Generate a board
	Go to chainReaction -> utils -> BoardGenerator 
	Look for the main function and change the parameters above
	Run the main function and look for the file at the path

## Run a board
	Go to engine -> Main
	Pick a searcher
	Pick an heuristic if the searcher needs it
	Run the main function inside (Make sure to change the searcher)

## Run with graphic interface
	Go to chainReaction -> utils -> ui -> Drawer
	Pick a searcher
	Pick an heuristic if the searcher needs it
	Run the main function inside
	Disclaimer (Only max of 7 shapes are supported as of yet)

## Generate a Jar
	`mvn clean package`

# Neural Network

The Neural Network amlgorithm is inside the path
`Redes Neuronales/TP1.2/`. Make sure you are inside that folder when running the commands.

`cd Redes\ Neuronales/TP1.2/`

To run the neural network training algorithm just run 
`octave startLearning`

To config the parameters use the `config` file


### Size of each input
global inputSize = 2;

### Size of each output
global outputSize = 1;

### Size of the hiddenLayers given by an array, each element is the size of that layer
global layersSize = [28];

### Learing Rato
global learningRate = 0.2;

### Alpha of momentum
global alphaMomentum = 0.8;

### Eta adaptative positive and negative adjustment
global etaPositiveAdjustment = 0.1;
global etaNegativeAdjustment = 0.1;

### Amount of times error must be reducing to re-adapt the eta
global maxEtaErrorReductions = 3;

### True for batch learning method, false for incremental
global batch = true;

### Error on which to stop trainig
global errorGoal = 0.001;

### Epsilon used to calculate the % correctnes of the testing phase
global validateEpsilon = 0.1;

### Path to the trainer file, functions must be implemented
global trainerPath = 'Trainer/TerrainTrainer';

### Path to the activation function file, functions must be implemented
global activationFunctionPath = 'ActivationFunction/tanH';

### Percentage of sample used for training the algorithm
global trainingPercentage = 0.8;

### File from witch to retrieve the data (if applicable)
global dataFile = 'terrain01.data';

### Boolean to define if weigths should be outputed
global outputOn = true;

### File to output the weigths at the end
global weightsPath = 'weights.data';

### Should display graphs
global visual = true;


To plot the neural network terrain just run 
`octave plotNeuralNetwork`


Make sure the weigthsPath is correctly set