import java.io.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        int currVal = -1, nextVal = -1, count, r, c;
        RunLength value = new RunLength();


        String outFile = args[1];
        File writeIt = new File(outFile);
        try {
            Scanner inFile = new Scanner(new File(args[0]));
            BufferedWriter out = new BufferedWriter(new FileWriter(writeIt));

            String temp = inFile.nextLine();
            String[] tempArray = temp.split(" ");
            value.numRows = Integer.parseInt(tempArray[0]);
            value.numCols = Integer.parseInt(tempArray[1]);
            value.minVal = Integer.parseInt(tempArray[2]);
            value.maxVal = Integer.parseInt(tempArray[3]);

            out.write("Header information: ");
            out.write("Rows = " + value.numRows + ", Columns = " + value.numCols);
            out.write(", Min value = " + value.minVal + ", Max value = " + value.maxVal + "\n");

            for (r = 1; r <= value.numRows; r++) {
                count = 0;
                if(inFile.hasNext()) currVal = inFile.nextInt();

                out.write("(" + r + ", " + 1 + ", " + currVal + ", ");
                count++;

                for (c = 1; c < value.numCols;) {
                    c++;
                    if(inFile.hasNext()) nextVal = inFile.nextInt();
                    else { break; }
                    if (nextVal == currVal) count++;
                    else {
                        out.write(count + ")\n");
                        currVal = nextVal;
                        count = 1;
                        out.write("(" + r + ", " + c + ", " + currVal + ", ");
                    }

                }

                out.write(count + ")\n");
            }

            inFile.close();
            out.close();
        } catch (Exception e) {
            System.out.println("No file");
            e.printStackTrace();
        }
    }
}
