import java.io.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        String chStr;
        int prob = 0;
        LList listHead = new LList();

        String inputFile = args[0], outputFile = args[1];
        File readIt = new File(inputFile), writeIt = new File(outputFile);
        try {
            Scanner inScan = new Scanner(readIt);
            BufferedWriter outWrite = new BufferedWriter(new FileWriter(writeIt));
            while(inScan.hasNextLine()) {
                chStr = inScan.next();
                prob = inScan.nextInt();
                LNode newNode = new LNode(chStr, prob);
                listHead.insertOneNode(listHead, newNode);
                Object next = listHead.getNext();
                while(next != null) {
                    System.out.println(next.getProb() + ' ' + next.getChar());
                }

            }

            inScan.close();
            outWrite.close();
        } catch (Exception e) {
            System.out.println("No file");
        }
    }
}
