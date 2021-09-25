import java.util.Scanner;

public class GaussJordan
{
	public void consoleInput()
	{
		GaussJordan gj = new GaussJordan();
		double[] row1 = {0, 0, 0, 0};
		double[] row2 = {0, 0, 0, 0};
		double[] row3 = {0, 0, 0, 0};
		String[] variablesVector = {"", "", ""};
		System.out.println("Coefficient matrix:\n");
		for(int i = 1; i <= 3; i++) //coefficient matrix is filled
		{
			for(int j = 1; j <= 3; j++)
			{
				System.out.println("\nEnter the value for x:" + i + "," + j);
				switch(i)
				{
				case 1:
					row1[j-1] = gj.readNumber();
					break;
				case 2:
					row2[j-1] = gj.readNumber();
					break;
				case 3:
					row3[j-1] = gj.readNumber();
				}
			}
		}

		//results vector is filled
		System.out.println("\n\nResults vector:\n\nEnter the value for b1:");
		row1[3] = gj.readNumber();
		System.out.println("\nEnter the value for b2:");
		row2[3] = gj.readNumber();
		System.out.println("\nEnter the value for b3:");
		row3[3] = gj.readNumber();

		//the values of the variable vector are named by the user
		System.out.println("\n\nVariables vector:");
		for(int i = 1; i <= 3; i++)
		{
			System.out.print("\n\nHow should value " + i + " of the variable vector be named?\n");
			variablesVector[i-1] = gj.readString();
			if(variablesVector[i-1].equals("")) //query whether the user has entered anything
			{
				System.out.print("\nPlease enter something like x1, x2, x3 or x, y, z:\n");
				i--;
			}
		}
		System.out.println("\n\nAre your entries correct? [Y/n]:\n\n");
		System.out.println(row1[0] + " " + variablesVector[0] + " + " + row1[1] + " " + variablesVector[1] + " + " + row1[2] + " " + variablesVector[2] + " = " + row1[3] + "\n");
		System.out.println(row2[0] + " " + variablesVector[0] + " + " + row2[1] + " " + variablesVector[1] + " + " + row2[2] + " " + variablesVector[2] + " = " + row2[3] + "\n");
		System.out.println(row3[0] + " " + variablesVector[0] + " + " + row3[1] + " " + variablesVector[1] + " + " + row3[2] + " " + variablesVector[2] + " = " + row3[3] + "\n");
		String string = gj.readString();

		if(string.equals("y") || string.equals("Y") || string.equals("")) //query whether the user wants to continue
		{
			rechnen(row1, row2, row3);
		}
		else
		{
			System.out.println("\nProgram is terminated...");
			System.exit(0);
		}
	}

	/*Description:		If user input is restricted to numbers, it will be carried out until the user enters a correct number.
	Parameters: 		-
	Return value: 		Returns the entered number*/
	private double readNumber()
	{
		Scanner scan = new Scanner( System.in );
		boolean isDouble = false;
		String input;
		double d = 1.0;

		while(!isDouble)
		{
			input = scan.nextLine();   	
			try {
				d = Double.parseDouble(input);
				isDouble = true;
			}catch(NumberFormatException e){
				System.out.println("\n" + input + " is not a number, please try again!");
			}
		}
		return d;
	}

	/*Description:		Allows user input.
	Parameters: 		-
	Return value: 		Returns the text entered by the user*/
	private String readString()
	{
		Scanner scan = new Scanner( System.in );
		return scan.nextLine();
	}

	/*Description:		Applies the gauss jordan method to the linear system
	Parameters: 		rowX, rowY, rowZ
	Return value: 		Returns an array with 4 entries. The 4th entry of the array indicates whether there was one, no or infinite solutions.
						In the case of one solution, the values are in the first three array entries*/
	public double[] rechnen(double[] rowX, double[] rowY, double[] rowZ)
	{
		GaussJordan gj = new GaussJordan(); //Objekt der Klasse Gausjordan wird erstellt
		double[] row1 = rowX;
		double[] row2 = rowY;
		double[] row3 = rowZ;
		double[] result = {0, 0, 0, 0};
		String[] variablesVector = {"x", "y", "z"};
		System.out.println("EINGABE:");
		gj.printLine(row1, row2, row3);	
		int i = 0;
		while(i <= 8)
		{
			switch(i)
			{
			case 0:
			default: break;
			case 1:
				if(row1[0] == 0 && row2[0] != 0) //Query whether the first line has to be swapped with another
				{
					double[] placeHolder = row1;
					row1 = row2;
					row2 = placeHolder;
				} else if(row1[0] == 0 && row3[0] != 0)
				{
					double[] placeHolder = row1;
					row1 = row3;
					row3 = placeHolder;
				} else if(row1[0] == 0 && row2[0] == 0 && row3[0] == 0)
				{
					System.out.println("\nThere is no solution.\n");
					result[3] = 3;
					i = 9;
					break;
				}

				row1 = gj.getTheOne(row1, 1);
				break;
			case 2:
				row2 = gj.getZeroAndColumn1IsMultiplier(row2, row1);
				row3 = gj.getZeroAndColumn1IsMultiplier(row3, row1);
				break;
			case 3:
				if(row2[1] == 0 && row3[1] != 0) //Query whether the second line has to be swapped with the third
				{
					double[] placeholder = row2;
					row2 = row3;
					row3 = placeholder;
				} else if(row2[1] == 0 && row3[1] == 0)
				{
					System.out.println("\nThere is no solution.\n");
					i = 9;
					result[3] = 3;
					break;
				}

				row2 = gj.getTheOne(row2, 2);
				break;
			case 4:						
				row3 = gj.getZeroAndColumn2IsMultiplier(row3, row2);

				switch (gj.checkResult(row1, row2, row3)) //At this point in the program, the elimination process is over. If you look at the linear system you can say exactly how many solutions there are.
				{
				case 2: //This is where a jump is made if the linear system has an infinite number of solutions
					gj.printLine(row1, row2, row3);
					System.out.println("\nThere are an infinite number of solutions.\n");
					result[3] = 2;
					i = 9;
					break;
				case 3: //This is where a jump is made if the linear system has no solution
					gj.printLine(row1, row2, row3);
					System.out.println("\nThere is no solution.\n");
					result[3] = 3;
					i = 9;
					break;
				}
				break;
			case 5:
				row3 = gj.getTheOne(row3, 3);
				break;
			case 6:
				row2 = gj.getZeroAndColumn3IsMultiplier(row2, row3);
				break;
			case 7:
				row1 = gj.getZeroAndColumn3IsMultiplier(row1, row3);
				break;
			case 8:
				row1 = gj.getZeroAndColumn2IsMultiplier(row1, row2);
				break;
			}
			i++;
		}

		if(result[3] == 0)
		{
			System.out.println("Result:\n" + variablesVector[0] + ": " + row1[3] + ", " + variablesVector[1] + ": " + row2[3] + ", " + variablesVector[2] + ": " + row3[3] + "\n");
			result[0] = row1[3];
			result[1] = row2[3];
			result[2] = row3[3];
			result[3] = 1;
		}
		return result;
	}

	/*Description:		Prints the 3 lines of the linear system
	Parameters: 		row1, row2, row3
	Return value: 		-*/
	private void printLine(double[] row1, double[] row2, double[] row3)
	{
		System.out.println(row1[0] + " | " + row1[1] + " | " + row1[2] + " || " + row1[3]);
		System.out.println(row2[0] + " | " + row2[1] + " | " + row2[2] + " || " + row2[3]);
		System.out.println(row3[0] + " | " + row3[1] + " | " + row3[2] + " || " + row3[3]);
		System.out.println();
	}

	/*Description:		Checks whether there are none, one clear, or an infinite number of solutions. For this, the function creates the step shape with the help of the elimination process.
	Parameters: 		row1, row2, row3
	Return value: 		Returns an integer that represents how many solutions there are (see legend below)*/
	public int checkResult(double[] row1, double[] row2, double[] row3)
	{
		//Return value = 1 -> there is a clear solution (no line is completely zero)
		//Return value = 2 -> there are an infinite number of solutions (one line is completely zero, as well as the associated results vector entry)
		//Return value = 3 -> there is no solution (one line is completely zero, but the associated results vector entry isn't)
				
		row1 = getTheOne(row1, 1);
		row2 = getZeroAndColumn1IsMultiplier(row2, row1);
		row3 = getZeroAndColumn1IsMultiplier(row3, row1);
		row2 = getTheOne(row2, 2);
		row3 = getZeroAndColumn2IsMultiplier(row3, row2);

		if(row1[0] == 0 && row1[1] == 0 && row1[2] == 0)
		{
			if(row1[3] == 0)
			{
				return 2;
			}else{
				return 3;
			}	
		}
		else if(row2[0] == 0 && row2[1] == 0 && row2[2] == 0)
		{
			if(row2[3] == 0)
			{
				return 2;
			}else{
				return 3;
			}	
		}
		else if(row3[0] == 0 && row3[1] == 0 && row3[2] == 0)
		{
			if(row3[3] == 0)
			{
				return 2;
			}else{
				return 3;
			}	
		}
		else{
			return 1;
		}
	}

	/*Description:		Generates the required 1 in a previously selected line on the diagonal
	Parameters: 		row (row in which the one should arise), whichColumn (indicates in which column the 1 should result)
	Return value: 		Returns the new line with the resulting one*/
	public double[] getTheOne(double[] row, int whichColumn)
	{
		double divisor = 1;
		switch(whichColumn)
		{
		case 1:
			divisor = row[0];
			break;
		case 2:
			divisor = row[1];
			break;
		case 3:
			divisor = row[2];
			break;
		}
		row[0] = row[0] / divisor;
		row[1] = row[1] / divisor;
		row[2] = row[2] / divisor;
		row[3] = row[3] / divisor;
		return row;
	}

	/*Description:		Creates a zero in the first column of a previously selected row.
	Parameters: 		someRow (line in which a zero should be generated), anotherRow (line which is multiplied)
	Return value: 		Returns the new line with the resulting zero*/
	public double[] getZeroAndColumn1IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[1] = someRow[1] - (someRow[0] * anotherRow[1]);
		someRow[2] = someRow[2] - (someRow[0] * anotherRow[2]);
		someRow[3] = someRow[3] - (someRow[0] * anotherRow[3]);
		someRow[0] = someRow[0] - (someRow[0] * anotherRow[0]);

		return someRow;
	}

	/*Description:		Creates a zero in the second column of a previously selected row.
	Parameters: 		someRow (line in which a zero should be generated), anotherRow (line which is multiplied)
	Return value: 		Returns the new line with the resulting zero*/
	public double[] getZeroAndColumn2IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[0] = someRow[0] - (someRow[1] * anotherRow[0]);
		someRow[2] = someRow[2] - (someRow[1] * anotherRow[2]);
		someRow[3] = someRow[3] - (someRow[1] * anotherRow[3]);
		someRow[1] = someRow[1] - (someRow[1] * anotherRow[1]);

		return someRow;
	}

	/*Description:		Creates a zero in the third column of a previously selected row.
	Parameters: 		someRow (line in which a zero should be generated), anotherRow (line which is multiplied)
	Return value: 		Returns the new line with the resulting zero*/
	public double[] getZeroAndColumn3IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[0] = someRow[0] - (someRow[2] * anotherRow[0]);
		someRow[1] = someRow[1] - (someRow[2] * anotherRow[1]);
		someRow[3] = someRow[3] - (someRow[2] * anotherRow[3]);
		someRow[2] = someRow[2] - (someRow[2] * anotherRow[2]);

		return someRow;
	}
}