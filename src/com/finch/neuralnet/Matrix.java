package com.finch.neuralnet;

public class Matrix {

	private Double mM[][]; 
	
	private int mNumRows;
	private int mNumColumns;
	
	
	/**
	 * prints out Matrix object
	 * @param m
	 */
	public static void print(Matrix m)
	{
		for(int r = 0; r < m.getNumRows(); r++)
		{
			System.out.print("[ ");
			for(int c = 0; c < m.getNumColumns(); c++)
			{
				System.out.print(m.getElement(r, c) + " ");
			}
			System.out.print("]\n");
		}
	}
	
	
	/**
	 * Multiplies Matrix m1 by Matrix m2 and returns the result
	 * @param m1
	 * @param m2
	 * @return 
	 */
	public static Matrix multiply(Matrix m1, Matrix m2)
	{

		int numRowsM1 = m1.getNumRows();
		int numColsM1 = m1.getNumColumns();
		int numRowsM2 = m2.getNumRows();
		int numColsM2 = m2.getNumColumns();
		
		
		/* if # of columns in m1 != # of rows in m2 */
		assert numColsM1  == numRowsM2 : "# columns in m1 != # rows in m2";
		
		
		Matrix resultMatrix = new Matrix(numRowsM1, numColsM2);
		
		
		for(int r = 0; r < numRowsM1; r++) 
		{
			
            for (int c = 0; c < numColsM2; c++) 
            {
            	
                for (int k = 0; k < numColsM1; k++) 
                {
                	
                	double newElementValue = resultMatrix.getElement(r, c) + m1.getElement(r, k) * m2.getElement(k, c);
                	resultMatrix.setElement(r, c, newElementValue);
        
                }
                
            }
        }
		
		
		return resultMatrix;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor specifies size of matrix and initialises each element to 0
	 * @param numRows
	 * @param numColumns
	 */
	Matrix(int numRows, int numColumns)
	{
		assert (numRows > 0) || (numColumns > 0) : "# Rows and Columns must be > 0"; 
		
		mNumRows = numRows;
		mNumColumns = numColumns;
		mM = new Double[numRows][numColumns];
		
		
		/* Iterates through each element setting to 0.0 */
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numColumns; c++)
			{
				setElement(r,c,0.0);
			}
			
		}
		
		
	}
	
	
	
	/**
	 * gets number of columns in matrix
	 * @return
	 */
	int getNumColumns()
	{
		return mNumColumns;
	}
	
	/**
	 * gets number of rows in matrix
	 * @return
	 */
	int getNumRows()
	{
		return mNumRows;
	}
	
	
	
	/**
	 * Returns value of specified element in matrix
	 * @param rowPos
	 * @param colPos
	 * @return
	 */
	Double getElement(int rowPos, int colPos)
	{
		return mM[rowPos][colPos];
	}
	
	
	/**
	 * Sets value of specified element in matrix
	 * @param rowPos
	 * @param colPos
	 * @param val
	 */
	void setElement(int rowPos, int colPos, Double val)
	{
		mM[rowPos][colPos] = val;
	}
	

	
	
	
}
