//Erstellt von Thorben B�er

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class GaussJordanTest {

	private GaussJordan gj = new GaussJordan(); //Objekt der Klasse GaussJordan wird erzeugt

	//hier werden zun�chst die einzelen Rechen Funktionen getestet
	@Test
	public void getZeroAndColumn1IsMultiplierTest() 
	{
		double[] row2 = { 4, 3, 2, 1 }; // some
		double[] row1 = { 1, 2, 3, 4 }; // another
		double[] ergebnis = { 0, -5, -10, -15 }; // -3 -1 1 3

		assertTrue(Arrays.equals(gj.getZeroAndColumn1IsMultiplier(row2, row1), ergebnis));
		assertTrue(row2[0] == 0);
	}

	@Test
	public void getZeroAndColumn2IsMultiplierTest() {
		double[] row2 = { 0, 3, 2, 1 }; // some
		double[] row1 = { 0, 1, 2, 3 }; // another
		double[] ergebnis = { 0, 0, -4, -8 };

		assertTrue(Arrays.equals(gj.getZeroAndColumn2IsMultiplier(row2, row1), ergebnis));
		assertTrue(row2[1] == 0);
	}

	@Test
	public void getZeroAndColumn3IsMultiplierTest() {
		double[] row2 = { 0, 0, 1, 2 }; // some
		double[] row1 = { 1, 2, 3, 4 }; // another
		double[] ergebnis = { 1, 2, 0, -2 };

		assertTrue(Arrays.equals(gj.getZeroAndColumn3IsMultiplier(row1, row2), ergebnis));
		assertTrue(row1[2] == 0);
	}

	@Test
	public void getTheOneTest() {		
		int zahl1 = 1;
		double[] row1 = {2, 4, 6, 8 };
		double[] ergebnis1 = { 1, 2, 3, 4 };
		assertTrue(Arrays.equals(gj.getTheOne(row1, zahl1), ergebnis1));
		assertTrue(ergebnis1[zahl1-1] == 1);

		int zahl2 = 2;
		double[]row2 = {2, 4, 6, 8};
		double[] ergebnis2 = {0.5, 1, 1.5, 2 };
		assertTrue(Arrays.equals(gj.getTheOne(row2, zahl2), ergebnis2));
		assertTrue(ergebnis2[zahl2-1] == 1);

		int zahl3 = 3;
		double[]row3 = {4, 6, 8, 10};
		double[] ergebnis3 = { 0.5, 0.75, 1, 1.25 };
		assertTrue(Arrays.equals(gj.getTheOne(row3, zahl3), ergebnis3));
		assertTrue(ergebnis3[zahl3-1] == 1);
	}

	@Test
	public void checkResultTest()
	{
		//Usecase: Es gibt eine eindeutige L�sung
		double[] row1 = {1, 2, 3, 4};
		double[] row2 = {0, 4, 3, 2};
		double[] row3 = {0, 0, 5, 9};
		int ergebnis = 1;
		assertEquals(gj.checkResult(row1, row2, row3), ergebnis);

		//Usecase: Es gibt unendlich viele L�sungen
		double[] rowA = {1, 2, 3, 4};
		double[] rowB = {0, 4, 3, 2};
		double[] rowC = {0, 0, 0, 0};
		int ergebnisABC = 2;
		assertEquals(gj.checkResult(rowA, rowB, rowC), ergebnisABC);

		//Usecase: Es gibt keine L�sung.
		double[] rowX = {1, 2, 3, 4};
		double[] rowY = {0, 4, 3, 2};
		double[] rowZ = {0, 0, 0, 6};
		int ergebnisXYZ = 3;
		assertEquals(gj.checkResult(rowX, rowY, rowZ), ergebnisXYZ);
	}

	//Bei den folgenden Tests werden die 3 Usecases getestet: 1 L�sung, unendlich viele L�sungen oder keine L�sung.
	@Test //Usecase: das LGS hat 1 L�sung
	public void eineLoesung1()
	{
		double ergebnis = 1;
		double[] rowx = {-3,  5, 6, -8};
		double[] rowy = { 9, -9, 3, -6};
		double[] rowz = { 9, -5, 6, -4};
		double[] ergebnisLGS = gj.rechnen(rowx, rowy, rowz);

		assertTrue(ergebnisLGS[0] == 2);
		assertTrue(ergebnisLGS[1] == 2);
		assertTrue(ergebnisLGS[2] == -2);
		assertTrue(ergebnisLGS[3] == ergebnis);
		Trennstriche();
	}

	@Test //Usecase: das LGS hat 1 L�sung (LGS wurde mit anderen Werten gef�llt)
	public void eineLoesung2()
	{
		double ergebnis = 1;
		double[] rowx = { 2, -9, -3,  0};
		double[] rowy = {-2,  1,  3, -8};
		double[] rowz = { 1,  7, -2,  0};
		double[] ergebnisLGS = gj.rechnen(rowx, rowy, rowz);

		assertTrue(ergebnisLGS[0] == 39);
		assertTrue(ergebnisLGS[1] == 1);
		assertTrue(ergebnisLGS[2] == 23);
		assertTrue(ergebnisLGS[3] == ergebnis);
		Trennstriche();
	}

	@Test //Usecase: das LGS hat unendlich viele L�sungen
	public void unendlichLoesungen1()
	{
		double ergebnis = 2;
		double[] rowx = {1, 2, 3, 3};
		double[] rowy = {4, 5, 6, 6};
		double[] rowz = {7, 8, 9, 9};
		assertTrue((gj.rechnen(rowx, rowy, rowz))[3] == ergebnis);	
		Trennstriche();
	}

	@Test //Usecase: das LGS hat unendlich viele L�sungen (LGS wurde mit anderen Werten gef�llt)
	public void unendlichLoesungen2()
	{
		double ergebnis = 2;
		double[] rowx = {-9, 1, -6, -8};
		double[] rowy = { 3, 0, -2,  7};
		double[] rowz = {-3, 0,  2, -7};
		assertTrue((gj.rechnen(rowx, rowy, rowz))[3] == ergebnis);	
		Trennstriche();
	}

	@Test //Usecase: das LGS hat keine L�sung
	public void keineLoesung1()
	{
		double ergebnis = 3;
		double[] rowx = {1, 2, 3, 2};
		double[] rowy = {4, 5, 6, 1};
		double[] rowz = {7, 8, 9, 2};
		assertTrue((gj.rechnen(rowx, rowy, rowz))[3] == ergebnis);	
		Trennstriche();
	}

	@Test //Usecase: das LGS hat keine L�sung (LGS wurde mit anderen Werten gef�llt)
	public void keineLoesung2()
	{
		double ergebnis = 3;
		double[] rowx = {-1, -2,  0,  0};
		double[] rowy = { 4, -1, -2, -8};
		double[] rowz = { 7, -4, -4, -4};
		assertTrue((gj.rechnen(rowx, rowy, rowz))[3] == ergebnis);	
		Trennstriche();
	}

	@Test //L�sst den Benutzer ein eigenes LGS eingeben, welches vom Programm wenn m�glich gel�st wird
	public void eigenesLGS()
	{
		System.out.println("Wollen Sie ein eigenes LGS l�sen lassen? (J/n)");
		Scanner scan = new Scanner( System.in );
		String string = scan.nextLine();
		if(string.equals("j") || string.equals("J") || string.equals(""))
		{
			gj.Eingabe();
		}
		Trennstriche();
	}
	
	
	public void Trennstriche()
	{
		System.out.println("------------------------------------");
	}
}