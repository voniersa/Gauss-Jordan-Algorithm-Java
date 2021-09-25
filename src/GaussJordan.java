//Erstellt von Sascha Vonier

import java.util.Scanner;

public class GaussJordan
{
	public void Eingabe() //wird aufgerufen, wenn der Benutzer sein eigenes LGS l�sen will
	{
		GaussJordan gj = new GaussJordan(); //Objekt der Klasse Gausjordan wird erstellt
		double[] row1 = {0, 0, 0, 0};
		double[] row2 = {0, 0, 0, 0};
		double[] row3 = {0, 0, 0, 0};
		String[] variableVector = {"", "", ""};
		System.out.println("Koeffizientenmatrix:\n");
		for(int i = 1; i <= 3; i++) //die vorderen drei row-Array-Eintr�ge werden bef�llt (Koeffizientenmatirx)
		{
			for(int j = 1; j <= 3; j++)
			{
				System.out.println("\nGib den Wert f�r x" + i + "," + j + " ein:");
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

		//der hintere row-Array-Eintrag wird bef�llt (Ergebnisvektor)
		System.out.println("\n\nErgebnisvektor:\n\nGib den Wert f�r b1 ein:");
		row1[3] = gj.readNumber();
		System.out.println("\nGib den Wert f�r b2 ein:");
		row2[3] = gj.readNumber();
		System.out.println("\nGib den Wert f�r b3 ein:");
		row3[3] = gj.readNumber();

		//die Werte des Variablenvektors werden vom Benutzer benannt
		System.out.println("\n\nVariablenvektor:");
		for(int i = 1; i <= 3; i++)
		{
			System.out.print("\n\nWie soll Wert " + i + " des Variablenvektors benannt werden:\n");
			variableVector[i-1] = gj.readString();
			if(variableVector[i-1].equals("")) //Abfrage ob der Benutzer etwas eingegeben hat
			{
				System.out.print("\nGib bitte etwas ein, z.B. x1, x2, x3 oder x, y, z:\n");
				i--;
			}
		}
		System.out.println("\n\nSind ihre Eingaben korrekt? [J/n]:\n\n");
		System.out.println(row1[0] + " " + variableVector[0] + " + " + row1[1] + " " + variableVector[1] + " + " + row1[2] + " " + variableVector[2] + " = " + row1[3] + "\n");
		System.out.println(row2[0] + " " + variableVector[0] + " + " + row2[1] + " " + variableVector[1] + " + " + row2[2] + " " + variableVector[2] + " = " + row2[3] + "\n");
		System.out.println(row3[0] + " " + variableVector[0] + " + " + row3[1] + " " + variableVector[1] + " + " + row3[2] + " " + variableVector[2] + " = " + row3[3] + "\n");
		String string = gj.readString();

		if(string.equals("j") || string.equals("J") || string.equals("")) //Abfrage ob der Benutzer fortfahren m�chte
		{
			rechnen(row1, row2, row3);
		}
		else
		{
			System.out.println("\nProgramm wird beendet...");
			System.exit(0);
		}
	}

	/*Beschreibung: 	Erm�glicht eine Nutzereingabe, die auf Zahlen beschr�nkt ist, wird solange ausgef�hrt, bis der Benutzer eine richtige Zahl eingibt.
	Parameter: 			-
	Returnwert: 		Gibt die eingegebene Zahl zur�ck*/
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
				System.out.println("\n" + input + " ist keine Zahl, probiere es nochmal!");
			}
		}
		return d;
	}

	/*Beschreibung: 	Erm�glicht eine Nutzereingabe.
	Parameter: 			-
	Returnwert: 		Gibt den eingegebenen Text des Benutzers zur�ck*/
	private String readString()
	{
		Scanner scan = new Scanner( System.in );
		return scan.nextLine();
	}

	/*Beschreibung: 	Wendet das Gaus Jordan Verfahren beim LGS an
	Parameter: 			rowx (Zeile1), rowy (Zeile2), rowz (Zeile3)
	Returnwert: 		Gibt ein Array mit 4 Eintr�gen zur�ck. Der 4. Eintrag vom Array gibt an, ob es eine, keine oder unendliche L�sungen gab.
						Bei einer L�sung stehen in den ersten drei Arrayeintr�gen die Werte */
	public double[] rechnen(double[] rowx, double[] rowy, double[] rowz) 
	{
		GaussJordan gj = new GaussJordan(); //Objekt der Klasse Gausjordan wird erstellt
		double[] row1 = rowx;
		double[] row2 = rowy;
		double[] row3 = rowz;
		double[] ergebnis = {0, 0, 0, 0};
		String[] variableVector = {"x", "y", "z"};
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
				if(row1[0] == 0 && row2[0] != 0) //Abfrage ob die erste Zeile mit einer Anderen getauscht werden muss
				{
					double[] placeholder = row1;
					row1 = row2;
					row2 = placeholder;
				} else if(row1[0] == 0 && row3[0] != 0)
				{
					double[] placeholder = row1;
					row1 = row3;
					row3 = placeholder;
				} else if(row1[0] == 0 && row2[0] == 0 && row3[0] == 0)
				{
					System.out.println("\nEs gibt keine L�sung.\n");
					ergebnis[3] = 3;
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
				if(row2[1] == 0 && row3[1] != 0) //Abfrage ob die zweite Zeile mit der Dritten getauscht werden muss
				{
					double[] placeholder = row2;
					row2 = row3;
					row3 = placeholder;
				} else if(row2[1] == 0 && row3[1] == 0)
				{
					System.out.println("\nEs gibt keine L�sung.\n");
					i = 9;
					ergebnis[3] = 3;
					break;
				}

				row2 = gj.getTheOne(row2, 2);
				break;
			case 4:						
				row3 = gj.getZeroAndColumn2IsMultiplier(row3, row2);

				switch (gj.checkResult(row1, row2, row3)) //An dieser Stelle im Programm ist das Eliminationsverfahren beendet. Schaut man sich nun das Gleichungssystem an kann man genau sagen, wieviele L�sungen es gibt.
				{
				case 2: //wird reingesprungen, wenn das LGS unendlich viele L�sungen hat
					gj.printLine(row1, row2, row3);
					System.out.println("\nEs gibt unendlich viele L�sungen.\n");
					ergebnis[3] = 2;
					i = 9;
					break;
				case 3: //wird reingesprungen, wenn das LGS keine L�sung hat
					gj.printLine(row1, row2, row3);
					System.out.println("\nEs gibt keine L�sung.\n");
					ergebnis[3] = 3;
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

		if(ergebnis[3] == 0)
		{
			System.out.println("Ergebnis:\n" + variableVector[0] + ": " + row1[3] + ", " + variableVector[1] + ": " + row2[3] + ", " + variableVector[2] + ": " + row3[3] + "\n");
			ergebnis[0] = row1[3];
			ergebnis[1] = row2[3];
			ergebnis[2] = row3[3];
			ergebnis[3] = 1;
		}
		return ergebnis;
	}

	/*Beschreibung: 	Stellt die 3 Zeilen des LGS's dar
	Parameter: 			row1 (Zeile1), row2 (Zeile2), row3 (Zeile3)
	Returnwert: 		-*/
	private void printLine(double[] row1, double[] row2, double[] row3)
	{
		System.out.println(row1[0] + " | " + row1[1] + " | " + row1[2] + " || " + row1[3]);
		System.out.println(row2[0] + " | " + row2[1] + " | " + row2[2] + " || " + row2[3]);
		System.out.println(row3[0] + " | " + row3[1] + " | " + row3[2] + " || " + row3[3]);
		System.out.println();
	}

	/*Beschreibung: 	�berpr�ft, ob es keine, eine eindeutige oder unendlich viele L�sungen gibt. Daf�r stellt die Funktion die Stufenform mithilfe des Eliminationsverfahren her.
	Parameter: 			row1 (Zeile1), row2 (Zeile2), row3 (Zeile3)
	Returnwert: 		Gibt eine Integerzahl zur�ck, die darstellt, wieviele L�sungen es gibt (siehe Legende weiter unten)*/
	public int checkResult(double[] row1, double[] row2, double[] row3)
	{
		//Returnwert = 1 -> es gibt eine eindeutige L�sung (keine Zeile ist komplett null)
		//Returnwert = 2 -> es gibt unendlich viele L�sungen (eine Zeile ist komplett null, sowie der jeweilige Ergebnisvektoreintrag)
		//Returnwert = 3 -> es gibt keine L�sung (eine Zeile ist komplett null, der jeweilige Ergebnisvektoreintrag aber nicht)
				
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

	/*Beschreibung: 	Erzeugt die ben�tigte 1 in einer vorher ausgew�hlten Zeile in der Diagonale.
	Parameter: 			row (Zeile in der die Eins entstehen soll), whichColumn (gibt an, in welcher Spalte die 1 enstehen soll)
	Returnwert: 		Gibt die neue Zeile mit der entstandenden Eins zur�ck*/
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

	/*Beschreibung: 	Erzeugt eine Null in der ersten Spalte einer vorher ausgew�hlten Zeile.
	Parameter: 			someRow (Zeile in der eine Null erzeugt werden soll), anotherRow (Zeile, mit der multipliziert wird)
	Returnwert: 		Gibt die neue Zeile mit der entstandenden Null zur�ck*/
	public double[] getZeroAndColumn1IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[1] = someRow[1] - (someRow[0] * anotherRow[1]);
		someRow[2] = someRow[2] - (someRow[0] * anotherRow[2]);
		someRow[3] = someRow[3] - (someRow[0] * anotherRow[3]);
		someRow[0] = someRow[0] - (someRow[0] * anotherRow[0]);

		return someRow;
	}

	/*Beschreibung: 	Erzeugt eine Null in der zweiten Spalte einer vorher ausgew�hlten Zeile.
	Parameter: 			someRow (Zeile in der eine Null erzeugt werden soll), anotherRow (Zeile, mit der multipliziert wird)
	Returnwert: 		Gibt die neue Zeile mit der entstandenden Null zur�ck*/
	public double[] getZeroAndColumn2IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[0] = someRow[0] - (someRow[1] * anotherRow[0]);
		someRow[2] = someRow[2] - (someRow[1] * anotherRow[2]);
		someRow[3] = someRow[3] - (someRow[1] * anotherRow[3]);
		someRow[1] = someRow[1] - (someRow[1] * anotherRow[1]);

		return someRow;
	}

	/*Beschreibung: 	Erzeugt eine Null in der dritten Spalte einer vorher ausgew�hlten Zeile.
	Parameter: 			someRow (Zeile in der eine Null erzeugt werden soll), anotherRow (Zeile, mit der multipliziert wird)
	Returnwert: 		Gibt die neue Zeile mit der entstandenden Null zur�ck*/
	public double[] getZeroAndColumn3IsMultiplier(double[] someRow, double[] anotherRow)
	{
		someRow[0] = someRow[0] - (someRow[2] * anotherRow[0]);
		someRow[1] = someRow[1] - (someRow[2] * anotherRow[1]);
		someRow[3] = someRow[3] - (someRow[2] * anotherRow[3]);
		someRow[2] = someRow[2] - (someRow[2] * anotherRow[2]);

		return someRow;
	}
}