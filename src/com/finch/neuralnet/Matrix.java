package com.finch.neuralnet;

public class Matrix {

	private Double mM[][]; 
	
	private int mNumRows;
	private int mNumColumns;
	
	
	
	
	public ColumnVector toColumnVector()
	{
		assert (this.mNumColumns == 1) : "Matrix has > 1 column";
		
		ColumnVector cv = new ColumnVector(this.mNumRows);
		
		for(int r = 0; r < this.getNumRows(); r++)
		{
			cv.setElement(r, this.getElement(r, 0));
		}
	
		return cv;
	}
	
	
	
	
	
	/**
	 * Prints out Matrix object
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
	 * Multiplies Matrix m by some double value and returns the result
	 * @param val
	 * @param m
	 * @return
	 */
	public static Matrix multiply(double val, Matrix m)
	{
		Matrix resultMatrix = new Matrix(m.getNumRows(), m.getNumColumns());
		
		
		for(int r = 0; r < m.getNumRows(); r++)
		{
			for(int c = 0; c < m.getNumColumns(); c++)
			{
				double newElementValue = val * m.getElement(r, c);
				resultMatrix.setElement(r, c, newElementValue);
			}
		}
		
		
		return resultMatrix;
	}
	
	
	
	/**
	 * Performs dot product between Matrix m1 by Matrix m2 and returns the result
	 * @param m1
	 * @param m2
	 * @return 
	 */
	public static Matrix dotProduct(Matrix m1, Matrix m2)
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
	 * Adds Matrix m1 and Matrix m2 and returns a result matrix
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static Matrix addMatrix(Matrix m1, Matrix m2)
	{
		assert (m1.getNumRows() == m2.getNumRows()) 
		&& (m1.getNumColumns() == m2.getNumColumns()) : "Matrices not equal sizes";
		
		Matrix resultMatrix = new Matrix(m1.getNumRows(), m1.getNumColumns());
		
		for(int r = 0; r < resultMatrix.getNumRows(); r++)
		{
			for(int c = 0; c < resultMatrix.getNumColumns(); c++)
			{
				double m1Element = m1.getElement(r, c);
				double m2Element = m2.getElement(r, c);
				
				resultMatrix.setElement(r, c, m1Element + m2Element);
			}
		}
		
		
		return resultMatrix;
	}
	
	
	/**
	 * Subtracts Matrix m1 from Matrix m2, returns a result Matrix
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static Matrix subtractMatrix(Matrix m1, Matrix m2)
	{
		assert (m1.getNumRows() == m2.getNumRows()) 
				&& (m1.getNumColumns() == m2.getNumColumns()) : "Matrices not equal sizes";
		
		
		Matrix resultMatrix = new Matrix(m1.getNumRows(), m1.getNumColumns());
		
				
		for(int r = 0; r < resultMatrix.getNumRows(); r++)
		{
			for(int c = 0; c < resultMatrix.getNumColumns(); c++)
			{
				double m1Element = m1.getElement(r, c);
				double m2Element = m2.getElement(r, c);
				
				resultMatrix.setElement(r, c, m1Element - m2Element);
			}
		}
		
		
		return resultMatrix;
	}
	
	
	/**
	 * Transposes the matrix m
	 * @param m
	 * @return
	 */
	public static Matrix transpose(Matrix m)
	{
		Matrix transposeMatrix = new Matrix(m.getNumColumns(), m.getNumRows());
		
		for(int r = 0; r < transposeMatrix.getNumRows(); r++)
		{
			for(int c = 0; c < transposeMatrix.getNumColumns(); c++)
			{
				double originalElement = m.getElement(c, r);
				transposeMatrix.setElement(r, c, originalElement);
			}
		}
		return transposeMatrix;
	}
	
	
	
	
	
	/**
	 * Constructor specifies size of matrix and initialises each element to 0
	 * @param numRows
	 * @param numColumns
	 */
	public Matrix(int numRows, int numColumns)
	{
		assert (numRows > 0) || (numColumns > 0) : "# Rows and Columns must be > 0"; 
		
		mNumRows = numRows;
		mNumColumns = numColumns;
		mM = new Double[numRows][numColumns];
		
		
		/* iterates through each element setting to 0.0 */
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numColumns; c++)
			{
				setElement(r,c,0.0);
			}
			
		}
		
		
	}
	
	/**
	 * Matrix copy constructor
	 * @param m
	 */
	public Matrix(Matrix m)
	{
		this.mM = new Double[m.getNumRows()][m.getNumColumns()];
		
		// copies each element of matrix array
		for(int r=0; r < m.getNumRows(); r++)
		{
			  for(int c=0; c < m.getNumColumns(); c++)
			  {
				  this.mM[r][c]=m.mM[r][c];
			  }
		}

		
		
		
		this.mNumColumns = m.mNumColumns;
		this.mNumRows = m.mNumRows;
	}
	
	
	/**
	 * Returns number of columns in matrix
	 * @return
	 */
	public int getNumColumns()
	{
		return mNumColumns;
	}
	
	/**
	 * Returns number of rows in matrix
	 * @return
	 */
	public int getNumRows()
	{
		return mNumRows;
	}
	
	
	
	/**
	 * Returns value of specified element in matrix
	 * @param rowPos
	 * @param colPos
	 * @return
	 */
	public Double getElement(int rowPos, int colPos)
	{
		return mM[rowPos][colPos];
	}
	
	
	/**
	 * Sets value of specified element in matrix
	 * @param rowPos
	 * @param colPos
	 * @param val
	 */
	public void setElement(int rowPos, int colPos, Double val)
	{
		mM[rowPos][colPos] = val;
	}
	

	
	
	
}
