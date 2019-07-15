
public class Sorts {
	public static void bubbleSort(int[] a) {
		int max = a[0], n = a.length;
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n - i - 1; i++) {
				if (a[max] > a[j]) {
					int temp = a[max];
					a[max] = a[j];
					a[j] = temp;
				}
			}
		}
		
		//printArray(a);
	}
	
	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
