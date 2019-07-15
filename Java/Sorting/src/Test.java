import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		PrintStream out = new PrintStream(new File("output.txt"));
		PrintStream console = System.out;
		
		System.setOut(out);
		
		int[] numTestCases = {10, 100};
		
		
		try {
			for (int i = 0; i < numTestCases.length; i++) {
				int[] testArray = new int[numTestCases[i]];
				
				for (int j = 0; j < testArray.length; j++) {
					
					testArray[j] = rand.nextInt(numTestCases[i]);
					System.out.print(testArray[j] + " ");
					
				}
				System.out.println();
				Sorts.bubbleSort(testArray);
				
				for (int h = 0; h < testArray.length; h++) {
					System.out.print(testArray[h] + " ");
				}
				System.out.println();
			}
			
			out.close();
		}
		catch (Exception e) {
			System.out.println("Ran into issues");
		}
		

	}

}
