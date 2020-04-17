/*
 * Code by : Shahid Dhariwala
 * LinkedIn : https://www.linkedin.com/in/shahiddhariwala/
 * Twitter : https://twitter.com/shahiddhariwala
 */

/*
 * You are given an N*N sudoku grid (N is a multiple of 3). Solve the sudoku and
 * print the solution.
 * 
 * To learn more about sudoku, go to this link Sudoku-Wikipedia.
 * Input Format
 * 
 * First line contains a single integer N. Next N lines contains N integers
 * each, where jth integer int ith line denotes the value at ith row and jth
 * column in sudoku grid. This value is 0, if the cell is empty.
 * 
 * Constraints
 * N=9 Solution exists for input matrix.
 * 
 * Output Format
 * Print N lines containing N integers each, where jth integer int ith line
 * denotes the value at ith row and jth column in sudoku grid, such that all
 * cells are filled.
 * 
 * Sample Input
 * 9
 * 5 3 0 0 7 0 0 0 0
 * 6 0 0 1 9 5 0 0 0
 * 0 9 8 0 0 0 0 6 0
 * 8 0 0 0 6 0 0 0 3
 * 4 0 0 8 0 3 0 0 1
 * 7 0 0 0 2 0 0 0 6
 * 0 6 0 0 0 0 2 8 0
 * 0 0 0 4 1 9 0 0 5
 * 0 0 0 0 8 0 0 7 9
 * 
 * Sample Output
 * 5 3 4 6 7 8 9 1 2
 * 6 7 2 1 9 5 3 4 8
 * 1 9 8 3 4 2 5 6 7
 * 8 5 9 7 6 1 4 2 3
 * 4 2 6 8 5 3 7 9 1
 * 7 1 3 9 2 4 8 5 6
 * 9 6 1 5 3 7 2 8 4
 * 2 8 7 4 1 9 6 3 5
 * 3 4 5 2 8 6 1 7 9
 */

package oldClassics;

import java.util.Scanner;

public class Program_To_Solve_Soduko
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int sudokugrid[][] = new int[num][num];
		takeInputMatrix(sudokugrid, sc);
		boolean sudokuAnswer = solveSoduko(sudokugrid, num);
		if (sudokuAnswer == true)
		{
			printMatrix(sudokugrid);
		} else
		{
			System.out.println("No Solution");
		}

		sc.close();
	}

	// as we know sudo grid of num is divisble of 3 , we will always have num%3 as
	// {0,1,2} this are our 3x3 sudoku squares regions
	// every sudoko solution follows
	// 1. We cant have same integer in given row
	// 2. We cant have same integer in given column
	// 3. We cant have same intger in current region
	private static boolean solveSoduko(int[][] sudokugrid, int num)
	{
		int col = -1;
		int row = -1;
		boolean isEmpty = true;
		for (int i = 0; i < sudokugrid.length; i++)
		{
			for (int j = 0; j < sudokugrid[i].length; j++)
			{
				if (sudokugrid[i][j] == 0)
				{
					row = i;
					col = j;
					// we still have some remaining
					// missing values in Sudoku
					isEmpty = false;
					break;
				}
			}
			if (isEmpty == false)
			{
				break;
			}
		}
		// no empty space left
		if (isEmpty)
		{
			return true;
		}
		// now we have cell where we have to insert some value
		for (int n = 1; n <= num; n++)
		{
			if (isItSafeToKeepNumber(sudokugrid, row, col, n))
			{
				sudokugrid[row][col] = n;
				if (solveSoduko(sudokugrid, num))
				{
					return true;
				} else
				{
					sudokugrid[row][col] = 0;
				}
			}
		}

		return false;
	}

	private static boolean isItSafeToKeepNumber(int[][] sudokugrid, int row, int col, int possibleNumber)
	{

		// lets check row
		for (int i = 0; i < sudokugrid[row].length; i++)
		{
			if (sudokugrid[row][i] == possibleNumber)
			{
				return false;
			}
		}
		// lets check column
		for (int i = 0; i < sudokugrid.length; i++)
		{
			if (sudokugrid[i][col] == possibleNumber)
			{
				return false;
			}
		}
		// lets check the region, i.e zone every zone has unique cordinates
		int regionX = row / 3;
		int regionY = col / 3;
		// we have region coordinates (regionX,regionY) we know evey sudoku matrix has 9
		// region
		// now we have to find starting cordinate and end cordinates of region in sudoku grid
		int lenthOfRegion = sudokugrid.length / 3;
		//
		int startIndexX = regionX * lenthOfRegion;
		int startIndexY = regionY * lenthOfRegion;
		//
		int endIndexX = startIndexX + lenthOfRegion;
		int endIndexY = startIndexY + lenthOfRegion;

		for (int i = startIndexX; i < endIndexX; i++)
		{
			for (int j = startIndexY; j < endIndexY; j++)
			{
				if (sudokugrid[i][j] == possibleNumber)
				{
					return false;
				}
			}
		}
		return true;
	}

	private static void takeInputMatrix(int[][] sudokugrid, Scanner sc)
	{
		for (int i = 0; i < sudokugrid.length; i++)
		{
			for (int j = 0; j < sudokugrid[i].length; j++)
			{
				sudokugrid[i][j] = sc.nextInt();
			}
		}
	}

	private static void printMatrix(int[][] sudokuAnswer)
	{
		for (int i = 0; i < sudokuAnswer.length; i++)
		{
			for (int j = 0; j < sudokuAnswer[i].length; j++)
			{
				System.out.print(sudokuAnswer[i][j] + " ");
			}
			System.out.println();
		}

	}
}

// https://github.com/shahiddhariwala
