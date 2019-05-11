package com.finch.neuralnet;

import java.util.ArrayList;

public class NeuralNet {

	/* weight matrix links between layers */
	private Matrix mWIH, mWHO;		
	
	private int mNumInputNodes, mNumHiddenNodes, mNumOutputNodes;
	
	private double mLearningRate;
	
	
	
	
	
	private static double rand(double minVal, double maxVal)
	{
		return (Math.random() * ((maxVal - minVal))) + minVal;
	}
	
	
	
	private static Matrix activationFunction(Matrix m)
	{
		
		assert (m.getNumColumns() == 1) : "Matrix > 1 column";
		
		/* Create a new matrix with same dimensions as m */
		Matrix resultMatrix = new Matrix(m.getNumRows(), 1);
		
		
		/* Iterates through each element in m matrix */
		for(int r = 0; r < m.getNumRows(); r++)
		{
			/* Grabs element from m matrix and passes through sigmoid */
			double newVal =  (1 / (1 + Math.exp(-m.getElement(r, 0))));
			
			/* Assigns new element in the result matrix */
			resultMatrix.setElement(r, 0, newVal);
		}
		
			
		return resultMatrix;
	}
	
	
	
	/**
	 * Set number of input, hidden, output nodes and learning rate. Also create and randomly initialise
	 * the weight matrices between the layers
	 */
	public void init(int numInputNodes, int numHiddenNodes, int numOutputNodes, double learningRate)
	{
		/* assign specified values number of nodes and learning rate */
		mNumInputNodes = numInputNodes; 
		mNumHiddenNodes = numHiddenNodes;
		mNumOutputNodes = numOutputNodes;
		mLearningRate = learningRate;
		
		
		
		/* create input->hidden weight matrix and init with random weights */
		mWIH = new Matrix(numHiddenNodes, numInputNodes);
		
		
		for(int row = 0; row < numHiddenNodes; ++row)
		{
			for(int column = 0; column < numInputNodes; ++column)
			{
				mWIH.setElement(row,column, rand(-0.5,0.5));
			}
		}
		
		
		/* create hidden->output weight matrix and init with random weights */
		mWHO = new Matrix(numOutputNodes, numHiddenNodes);
		
		for(int row = 0; row < numOutputNodes; ++row)
		{
			for(int column = 0; column < numHiddenNodes; ++column)
			{
				mWHO.setElement(row,column, rand(-0.5,0.5));
			}
		}
		
		
		
	}
	
	
	/**
	 * Refine the weights after being given a training set to learn from
	 */
	public void train()
	{
		
	}
	
	
	/**
	 * Give an answer from output nodes after being given an input
	 */
	public Matrix query(ArrayList<Double> inputs)
	{
		/* create a single column input matrix from input data */
		Matrix inputLayer = new Matrix(inputs.size(), 1);
		
		
		
		/* calculate signal inputs into hidden layer */
		Matrix hiddenLayerInputs = Matrix.multiply(mWIH, inputLayer);
		
		
		
		/* calculate signals emerging from hidden layer */
		Matrix hiddenLayerOutputs = activationFunction(hiddenLayerInputs);
		
		

		/* calculate signals into final output layer */
		Matrix OutputLayerInputs = Matrix.multiply(mWHO, hiddenLayerOutputs);
		
		
		
		/* calculates signals emerging from output layer */
		Matrix OutputLayerOutputs = activationFunction(OutputLayerInputs);
		
		
		/* returns the output layer output matrix */
		return OutputLayerOutputs;
		
	}
	
	

}
