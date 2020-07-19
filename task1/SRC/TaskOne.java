package task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskOne {
	public static void main(String[] args) throws FileNotFoundException {
		int result;
		int averageArr;
		int percentile;
		ArrayList<Integer> arr = new ArrayList<>();

		File file = new File(args[0]);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()){
			arr.add(Integer.parseInt(scanner.next()));
		}
		scanner.close();

		bubbleSort(arr);

		averageArr = average(arr);

		percentile = percentile(arr, 90);

		result = sumArrayFromAverageToPercentile(arr, averageArr, percentile);
		System.out.println(result);
	}

	public static void bubbleSort(ArrayList<Integer> numbers) {
		int n = numbers.size();
		int temp = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (numbers.get(j - 1) > numbers.get(j)) {
					temp = numbers.get(j - 1);
					numbers.set(j-1, numbers.get(j));
					numbers.set(j, temp);
				}
			}
		}
	}

	public static int average(List<Integer> numbers){
		int sum = 0;
		for (Integer x:	numbers) {
			sum += x;
		}
		return sum/numbers.size();
	}

	public static int percentile(ArrayList<Integer> numbers, double percentile) {
		int index = (int) Math.ceil(percentile / 100.0 * numbers.size());
		return numbers.get(index-1);
	}

	public static int sumArrayFromAverageToPercentile(List<Integer> numbers, int avarage, int percentile){
		int sum = 0;
		for (Integer i:	numbers) {
			if (i > avarage && i <= percentile)
				sum += i;
		}
		return sum;
	}
}
