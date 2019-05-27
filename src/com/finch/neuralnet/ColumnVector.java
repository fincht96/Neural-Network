package com.finch.neuralnet;

public class ColumnVector {

	Double list[];
	
	public int getLength()
	{
		return list.length;
	}
	
	
	public void setElement(int index, Double val)
	{
		list[index] = val;
	}
	
	public Double getElement(int index)
	{
		return list[index];
	}
	
	
	
	public ColumnVector(int size)
	{
		list = new Double[size];
	}
	
	public ColumnVector(ColumnVector cv)
	{
		this.list = cv.list;
	}
	
	

	public Matrix toMatrix()
	{
		Matrix m = new Matrix(this.getLength(), 1);
		
		for(int index = 0; index < m.getNumRows(); index++)
		{
			m.setElement(index, 0, this.getElement(index));
		}
		
		return m;
		
	}
	
	
	
	private static ColumnVector multiply(ColumnVector cv1, ColumnVector cv2)
	{
		ColumnVector resultCv = new ColumnVector(cv1.getLength());
		
		for(int i = 0; i < cv1.getLength(); i++)
		{
			resultCv.setElement(i, cv1.getElement(i) * cv2.getElement(i)); 
		}
		
		return resultCv;
	}
	
	
	
	public static ColumnVector multiply(ColumnVector ...inputs)
	{
		ColumnVector resultCv = inputs[0];
		
		
		for(int i = 1; i < inputs.length; i++)
		{
			resultCv = multiply(resultCv, inputs[i]);
		}
		
	
		return resultCv;
		
	}
	

	
	public static ColumnVector subtract(double val, ColumnVector cv)
	{
		ColumnVector resultCv = new ColumnVector(cv.getLength());
		
		
		for(int i = 0; i < resultCv.getLength(); i++)
		{
			resultCv.setElement(i, val - cv.getElement(i));
		}
		
		return resultCv;
		
	}
	
	
	
}
