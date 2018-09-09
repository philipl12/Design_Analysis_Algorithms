import java.io.*;

class Main {

    public static void main(String[] args) {
        int letters[] = new int[256];
        int myChar;

        for (int i = 0; i < 256; i++) {
            letters[i] = 0;
        }

        String inputFile = args[0], outputFile = args[1];
        File readIt = new File(inputFile), writeIt = new File(outputFile);
        try {
            BufferedReader inStyle = new BufferedReader(new FileReader(readIt));
            BufferedWriter outStyle = new BufferedWriter(new FileWriter(writeIt));
            while((myChar = inStyle.read()) != -1) {
                for (int j = 0; j < 256; j++) {
                    if (myChar == j) {
                        letters[j]++;
                        break;
                    }
                }
            }
            for (int k = 33; k < 256; k++) {
                if (letters[k] > 0) {
                    outStyle.write((char)k + "   #" + letters[k] + '\n');
                }
            }
            inStyle.close();
            outStyle.close();
        } catch (Exception e) {
            System.out.println("No file");
        }
    }
}
