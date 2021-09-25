import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GaussJordanTest {

	private GaussJordan gj = new GaussJordan();

	//the individual calculation functions are tested first:
	@Test
	public void getZeroAndColumn1IsMultiplierTest() 
	{
		double[] row2 = { 4, 3, 2, 1 }; // some
		double[] row1 = { 1, 2, 3, 4 }; // another
		double[] ergebnis = { 0, -5, -10, -15 }; // -3 -1 1 3

		Assertions.assertArrayEquals(gj.getZeroAndColumn1IsMultiplier(row2, row1), ergebnis);
		Assertions.assertEquals(0, row2[0]);
	}

	@Test
	public void getZeroAndColumn2IsMultiplierTest() {
		double[] row2 = { 0, 3, 2, 1 }; // some
		double[] row1 = { 0, 1, 2, 3 }; // another
		double[] ergebnis = { 0, 0, -4, -8 };

		Assertions.assertArrayEquals(gj.getZeroAndColumn2IsMultiplier(row2, row1), ergebnis);
		Assertions.assertEquals(0, row2[1]);
	}

	@Test
	public void getZeroAndColumn3IsMultiplierTest() {
		double[] row2 = { 0, 0, 1, 2 }; // some
		double[] row1 = { 1, 2, 3, 4 }; // another
		double[] ergebnis = { 1, 2, 0, -2 };

		Assertions.assertArrayEquals(gj.getZeroAndColumn3IsMultiplier(row1, row2), ergebnis);
		Assertions.assertEquals(0, row1[2]);
	}

	@Test
	public void getTheOneTest() {		
		int zahl1 = 1;
		double[] row1 = {2, 4, 6, 8 };
		double[] ergebnis1 = { 1, 2, 3, 4 };
		Assertions.assertArrayEquals(gj.getTheOne(row1, zahl1), ergebnis1);
		Assertions.assertEquals(1, ergebnis1[zahl1 - 1]);

		int zahl2 = 2;
		double[]row2 = {2, 4, 6, 8};
		double[] ergebnis2 = {0.5, 1, 1.5, 2 };
		Assertions.assertArrayEquals(gj.getTheOne(row2, zahl2), ergebnis2);
		Assertions.assertEquals(1, ergebnis2[zahl2 - 1]);

		int zahl3 = 3;
		double[]row3 = {4, 6, 8, 10};
		double[] ergebnis3 = { 0.5, 0.75, 1, 1.25 };
		Assertions.assertArrayEquals(gj.getTheOne(row3, zahl3), ergebnis3);
		Assertions.assertEquals(1, ergebnis3[zahl3 - 1]);
	}

	@Test
	public void checkResultTest()
	{
		//Use case: There is a clear solution
		double[] row1 = {1, 2, 3, 4};
		double[] row2 = {0, 4, 3, 2};
		double[] row3 = {0, 0, 5, 9};
		int ergebnis = 1;
		Assertions.assertEquals(gj.checkResult(row1, row2, row3), ergebnis);

		//Use case: There are an infinite number of solutions
		double[] rowA = {1, 2, 3, 4};
		double[] rowB = {0, 4, 3, 2};
		double[] rowC = {0, 0, 0, 0};
		int ergebnisABC = 2;
		Assertions.assertEquals(gj.checkResult(rowA, rowB, rowC), ergebnisABC);

		//Use case: There is no solution
		double[] rowX = {1, 2, 3, 4};
		double[] rowY = {0, 4, 3, 2};
		double[] rowZ = {0, 0, 0, 6};
		int ergebnisXYZ = 3;
		Assertions.assertEquals(gj.checkResult(rowX, rowY, rowZ), ergebnisXYZ);
	}

	//In the following tests, this 3 use cases are tested: 1 solution, infinite solutions, or no solution
	@Test //Use case: the linear system has 1 solution
	public void oneSolution()
	{
		double ergebnis = 1;
		double[] rowx = {-3,  5, 6, -8};
		double[] rowy = { 9, -9, 3, -6};
		double[] rowz = { 9, -5, 6, -4};
		double[] ergebnisLGS = gj.rechnen(rowx, rowy, rowz);

		Assertions.assertEquals(2, ergebnisLGS[0]);
		Assertions.assertEquals(2, ergebnisLGS[1]);
		Assertions.assertEquals(-2, ergebnisLGS[2]);
		Assertions.assertEquals(ergebnisLGS[3], ergebnis);
		seperators();
	}

	@Test ////Use case: the linear system has 1 solution (other values)
	public void oneSolution2()
	{
		double ergebnis = 1;
		double[] rowx = { 2, -9, -3,  0};
		double[] rowy = {-2,  1,  3, -8};
		double[] rowz = { 1,  7, -2,  0};
		double[] ergebnisLGS = gj.rechnen(rowx, rowy, rowz);

		Assertions.assertEquals(39, ergebnisLGS[0]);
		Assertions.assertEquals(1, ergebnisLGS[1]);
		Assertions.assertEquals(23, ergebnisLGS[2]);
		Assertions.assertEquals(ergebnisLGS[3], ergebnis);
		seperators();
	}

	@Test //Use case: the linear system has an infinite number of solutions
	public void infiniteNumberOfSolutions()
	{
		double ergebnis = 2;
		double[] rowx = {1, 2, 3, 3};
		double[] rowy = {4, 5, 6, 6};
		double[] rowz = {7, 8, 9, 9};
		Assertions.assertEquals((gj.rechnen(rowx, rowy, rowz))[3], ergebnis);
		seperators();
	}

	@Test //Use case: the linear system has an infinite number of solutions (other values)
	public void infiniteNumberOfSolutions2()
	{
		double ergebnis = 2;
		double[] rowx = {-9, 1, -6, -8};
		double[] rowy = { 3, 0, -2,  7};
		double[] rowz = {-3, 0,  2, -7};
		Assertions.assertEquals((gj.rechnen(rowx, rowy, rowz))[3], ergebnis);
		seperators();
	}

	@Test //Use case: there is no solution
	public void noSolution()
	{
		double ergebnis = 3;
		double[] rowx = {1, 2, 3, 2};
		double[] rowy = {4, 5, 6, 1};
		double[] rowz = {7, 8, 9, 2};
		Assertions.assertEquals((gj.rechnen(rowx, rowy, rowz))[3], ergebnis);
		seperators();
	}

	@Test //Use case: there is no solution (other values)
	public void noSolution2()
	{
		double ergebnis = 3;
		double[] rowx = {-1, -2,  0,  0};
		double[] rowy = { 4, -1, -2, -8};
		double[] rowz = { 7, -4, -4, -4};
		Assertions.assertEquals((gj.rechnen(rowx, rowy, rowz))[3], ergebnis);
		seperators();
	}
	
	public void seperators()
	{
		System.out.println("------------------------------------");
	}
}