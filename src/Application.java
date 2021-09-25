public class Application {
    public static void main (String[] args) throws Exception {
        GaussJordan gj = new GaussJordan();
        if(args.length == 0) {
            gj.consoleInput();
        } else if(args.length != 10) {
            System.out.println("The entered arguments are not correct.");
            System.out.println("\nProgram is terminated...");
            System.exit(0);
        } else {
            String[] arguments = collectArguments(args);
            String[] resultsVector = arguments[3].split(",");
            arguments[0] = arguments[0] + "," + resultsVector[0];
            arguments[1] = arguments[1] + "," + resultsVector[1];
            arguments[2] = arguments[2] + "," + resultsVector[2];
            double[] result = gj.calculate(
                    convertStringArrayToDoubleArray(arguments[0].split(","), 4),
                    convertStringArrayToDoubleArray(arguments[1].split(","), 4),
                    convertStringArrayToDoubleArray(arguments[2].split(","), 4)
            );
        }
    }

    private static String[] collectArguments(String[] args) throws Exception {
        if(args.length != 10) {
            throw new Exception("The program needs all required arguments");
        }

        String[] arguments = new String[5];
        for(int i = 0; i < args.length; i = i+2) {
            switch (args[i]) {
                case "-row1" -> arguments[0] = args[i + 1];
                case "-row2" -> arguments[1] = args[i + 1];
                case "-row3" -> arguments[2] = args[i + 1];
                case "-resultsVector" -> arguments[3] = args[i + 1];
                case "-naming" -> arguments[4] = args[i + 1];
                default -> throw new Exception("The program needs all required arguments");
            }
        }
        return arguments;
    }

    private static double[] convertStringArrayToDoubleArray(String[] array, int sizeOfArray) {
        double[] doubleArray = new double[sizeOfArray];
        for(int i = 0; i < sizeOfArray; i++) {
            doubleArray[i] = Double.parseDouble(array[i]);
        }
        return doubleArray;
    }
}
