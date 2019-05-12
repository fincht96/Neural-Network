package com.finch.neuralnet;

import java.util.ArrayList;

public class NeuralNet {

	/* weight matrix links between layers */
	private Matrix mWIH, mWHO;		
	
	private int numInputNodes, numHiddenNodes, numOutputNodes;
	
	private double learningRate;
	
	
	private static double rand(double minVal, double maxVal)
	{
		return (Math.random() * ((maxVal - minVal))) + minVal;
	}
	
	
	
	private Matrix activationFunction(Matrix m)
	{
		
		assert (m.getNumColumns() == 1) : "Matrix > 1 column";
		
		/* create a new matrix with same dimensions as m */
		Matrix resultMatrix = new Matrix(m.getNumRows(), 1);
		
		
		/* iterates through each element in m matrix */
		for(int r = 0; r < m.getNumRows(); r++)
		{
			/* grabs element from m matrix and passes through sigmoid */
			double newVal =  (1 / (1 + Math.exp(-m.getElement(r, 0))));
			
			/* assigns new element in the result matrix */
			resultMatrix.setElement(r, 0, newVal);
		}
		
			
		return resultMatrix;
	}
	
	
	
	
	private Matrix calcDeltaWeights(ColumnVector layerOutputErrors, ColumnVector layerOutputs, Matrix mPrevLayerOutputs)
	{
		Matrix deltaWHO = Matrix.multiply(learningRate, Matrix.dotProduct(
				
						ColumnVector.multiply(layerOutputErrors, layerOutputs, ColumnVector.subtract(1.0, layerOutputs)).toMatrix()
						, 
						Matrix.transpose(mPrevLayerOutputs)));
		
		return deltaWHO;
	}
	
	
	
	
	/**
	 * Set number of input, hidden, output nodes and learning rate. Also create and randomly initialise
	 * the weight matrices between the layers
	 */
	public void init(int numInputNodes, int numHiddenNodes, int numOutputNodes, double learningRate)
	{
		/* assign specified values number of nodes and learning rate */
		this.numInputNodes = numInputNodes; 
		this.numHiddenNodes = numHiddenNodes;
		this.numOutputNodes = numOutputNodes;
		this.learningRate = learningRate;
		
		
		
		/* create input->hidden weight matrix and init with random weights */
		mWIH = new Matrix(this.numHiddenNodes, this.numInputNodes);
		
		
		for(int row = 0; row < this.numHiddenNodes; ++row)
		{
			for(int column = 0; column < this.numInputNodes; ++column)
			{
				mWIH.setElement(row,column, rand(-0.5,0.5));
			}
		}
		
		
		/* create hidden->output weight matrix and init with random weights */
		mWHO = new Matrix(this.numOutputNodes, this.numHiddenNodes);
		
		for(int row = 0; row < this.numOutputNodes; ++row)
		{
			for(int column = 0; column < this.numHiddenNodes; ++column)
			{
				mWHO.setElement(row,column, rand(-0.5,0.5));
			}
		}
		
		
		
	}
	
	
	/**
	 * Refine the weights after being given a training set to learn from
	 */
	public void train(ArrayList<Double> inputs, ArrayList<Double> targets)
	{
		
		assert (inputs.size() == numInputNodes) : "# inputs != size of input layer "; 
		assert (targets.size() == numOutputNodes) : "# targets != size of output layer "; 
		
		
		/* create a single column target matrix and fills with target data */
		Matrix mTargetData = new Matrix(targets.size(), 1);
		
		for(int r = 0; r < mTargetData.getNumRows(); r++)
		{
			mTargetData.setElement(r, 0, targets.get(r));
		}
		
		
		/* create a single column input matrix and fill with input data */
		Matrix mInputLayerOutputs = new Matrix(inputs.size(), 1);
		
		for(int r = 0; r < mInputLayerOutputs.getNumRows(); r++)
		{
			mInputLayerOutputs.setElement(r, 0, inputs.get(r));
		}
		
		
		
		
		/* calculate signal inputs into hidden layer */
		Matrix mHiddenLayerInputs = Matrix.dotProduct(mWIH, mInputLayerOutputs);
		
		
		
		/* calculate signals emerging from hidden layer */
		Matrix mHiddenLayerOutputs = activationFunction(mHiddenLayerInputs);
		
		

		/* calculate signals into final output layer */
		Matrix mOutputLayerInputs = Matrix.dotProduct(mWHO, mHiddenLayerOutputs);
		
		
		
		/* calculates signals emerging from output layer */
		Matrix mOutputLayerOutputs = activationFunction(mOutputLayerInputs);


		
		
		
		
		

		/* calculate the error between target data and net output data */
		Matrix mOutputErrors = Matrix.subtractMatrix(mTargetData, mOutputLayerOutputs);
		
		
		
		/* calculate the errors in the hidden layer by backpropogating the output errors */
		Matrix mHiddenErrors = Matrix.dotProduct(Matrix.transpose(mWHO), mOutputErrors);
		
		
		
		
		
		
		
		/* update weights between hidden and output layers */
		Matrix deltaWHO = calcDeltaWeights(mOutputErrors.toColumnVector(), mOutputLayerOutputs.toColumnVector(), mHiddenLayerOutputs);
		mWHO = Matrix.addMatrix(mWHO, deltaWHO);
	
		
		/* update weights between input and hidden layers */
		Matrix deltaWIH = calcDeltaWeights(mHiddenErrors.toColumnVector(), mHiddenLayerOutputs.toColumnVector(), mInputLayerOutputs);
		mWIH = Matrix.addMatrix(mWIH, deltaWIH); 
		
		
	}
	
	
	/**
	 * Give an answer from output nodes after being given an input
	 */
	public Matrix query(ArrayList<Double> inputs)
	{
	
		
		assert (inputs.size() == numInputNodes) : "# inputs != size of input layer "; 
		
		
		
		/* create a single column input matrix and fill with input data */
		Matrix mInputLayer = new Matrix(inputs.size(), 1);
		
		for(int r = 0; r < mInputLayer.getNumRows(); r++)
		{
			mInputLayer.setElement(r, 0, inputs.get(r));
		}
		
		
		
		
		/* calculate signal inputs into hidden layer */
		Matrix mHiddenLayerInputs = Matrix.dotProduct(mWIH, mInputLayer);
		
		
		
		/* calculate signals emerging from hidden layer */
		Matrix mHiddenLayerOutputs = activationFunction(mHiddenLayerInputs);
		
		

		/* calculate signals into final output layer */
		Matrix mOutputLayerInputs = Matrix.dotProduct(mWHO, mHiddenLayerOutputs);
		
		
		
		/* calculates signals emerging from output layer */
		Matrix mOutputLayerOutputs = activationFunction(mOutputLayerInputs);
		
		
		/* returns the output layer output matrix */
		return mOutputLayerOutputs;
		
	}
	
	

}
