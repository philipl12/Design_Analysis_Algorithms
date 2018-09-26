import java.io.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        int currVal, nextVal, count, r, c;
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

            for (r = 0; r < value.numRows; r++) {
                c = 0;
                count = 0;
                currVal = inFile.nextInt();
                out.write(r + " " + c + " " + currVal + " ");
                count++;
                for (c = 0; c < value.numCols;) {
                    c++;
                    nextVal = inFile.nextInt();
                    if (nextVal == currVal) count++;
                    else {
                        out.write(count + "\n");
                        currVal = nextVal;
                        count = 1;
                        out.write(r + " " + c + " " + currVal + " ");
                    }
                }
            }

/*
            while (inFile.hasNext()) {
                //System.out.println("in line 26");
                c = 0;
                count = 0;
                currVal = inFile.nextInt();
                out.write(r + " " + c + " " + currVal);
                count++;
                System.out.println(count);

                  c++;
                  nextVal = inFile.nextInt();
                  if (nextVal == currVal) count++;
                  else {
                      out.write(count + "\n");
                      currVal = nextVal;
                      count = 1;
                      out.write(r + " " + c + " " + currVal);
                  }
                //System.out.println("in line 44");
                r++;
            }
*/
            inFile.close();
            out.close();
        } catch (Exception e) {
            System.out.println("No file");
        }
    }
}
