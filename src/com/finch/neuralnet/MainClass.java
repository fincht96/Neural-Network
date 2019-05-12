package com.finch.neuralnet;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		
		
		/* defines {2,3,1} neural net with learning rate of 0.3 */
		int inputNodes = 2;
		int hiddenNodes = 12;
		int outputNodes = 1;
		
		double learningRate = 0.3;
		
		
		/* create instance of neural net */
		NeuralNet nn = new NeuralNet();
		nn.init(inputNodes, hiddenNodes, outputNodes, learningRate);

		
		ArrayList<Double> inputs = new ArrayList<Double>();
		inputs.add(0.0);
		inputs.add(0.0);
	
		
		ArrayList<Double> targets = new ArrayList<Double>();
		targets.add(0.0);
		
		
		
		
		/* passes input data to matrix and gets result */
		Matrix.print(nn.query(inputs));
		
		
		System.out.println("\n\n");
		
		
		for(int i = 0; i < 100000; i++)
		{
			inputs.set(0, 0.0);
			inputs.set(1, 0.0);
			targets.set(0, 0.0);
			
			nn.train(inputs, targets);
				
			inputs.set(0, 0.0);
			inputs.set(1, 1.0);
			targets.set(0, 1.0);
			
			nn.train(inputs, targets);
			
			inputs.set(0, 1.0);
			inputs.set(1, 0.0);
			targets.set(0, 1.0);
			
			nn.train(inputs, targets);

			inputs.set(0, 1.0);
			inputs.set(1, 1.0);
			targets.set(0, 0.0);
			
			nn.train(inputs, targets);
			
			
		}
		
		
	
		
		
		
		inputs.set(0, 0.0);
		inputs.set(1, 0.0);
		System.out.println("i1: " + 0 + "\ti2:"  + 0);
		Matrix.print(nn.query(inputs));
		System.out.println("\n");
		
		
		inputs.set(0, 1.0);
		inputs.set(1, 0.0);
		System.out.println("i1: " + 1 + "\ti2:"  + 0);
		Matrix.print(nn.query(inputs));
		System.out.println("\n");
		
		inputs.set(0, 0.0);
		inputs.set(1, 1.0);
		System.out.println("i1: " + 0 + "\ti2:"  + 1);
		Matrix.print(nn.query(inputs));
		System.out.println("\n");
		
		inputs.set(0, 1.0);
		inputs.set(1, 1.0);
		System.out.println("i1: " + 1 + "\ti2:"  + 1);
		Matrix.print(nn.query(inputs));
		System.out.println("\n");
	}

}
