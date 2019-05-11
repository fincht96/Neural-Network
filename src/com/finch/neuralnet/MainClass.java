package com.finch.neuralnet;

public class MainClass {

	public static void main(String[] args) {
		
		int inputNodes = 3;
		int hiddenNodes = 3;
		int outputNodes = 3;
		
		double learningRate = 0.3;
		
		
		/* create instance of neural net */
		NeuralNet nn = new NeuralNet();
		nn.init(inputNodes, hiddenNodes, outputNodes, learningRate);

		
		Matrix m1 = new Matrix(2,2);
		Matrix m2 =	new Matrix(2,1);
		
		for(int row = 0; row < 2; ++row)
		{
			for(int column = 0; column < 2; ++column)
			{
				m1.setElement(row,column, 1.0);
			}
		}
		
		
		for(int row = 0; row < 2; ++row)
		{
			for(int column = 0; column < 1; ++column)
			{
				m2.setElement(row,column, 0.4);
			}
		}
		
		Matrix result = Matrix.multiply(m1, m2);
		
		
		
		//Matrix.print(result);
		
		
		//mResult = Matrix.multiply(m1,m2);
		


		

		
	}

}
