package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskTwo {
	public static void main(String[] args) throws FileNotFoundException {
		boolean result = false;
		File file = new File(args[0]);
		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine();
		scanner.close();

		List<Integer> points = getDigitsFromLine(line);

		points.remove(0);
		points.remove(9);

		List<Integer> triangleOne = splitTriangles(points, 0, points.size()/2);
		List<Integer> triangleTwo = splitTriangles(points, points.size()/2, points.size());

		List<Double> abcDistanceTriangleOne = new ArrayList<>(3);
		List<Double> abcDistanceTriangleTwo = new ArrayList<>(3);

		abcDistanceTriangleOne = getNormalValuesAfterPoint(abcDistance(triangleOne));
		abcDistanceTriangleTwo = getNormalValuesAfterPoint(abcDistance(triangleTwo));

		result = compareTrianglesToSame(abcDistanceTriangleOne, abcDistanceTriangleTwo);
		if (result == false)
			System.out.println("Треугольники не подобны");
		else
			System.out.println("Треугольники подобны");
	}

	static List<Integer> getDigitsFromLine(String line){
		String digitStr = "";
		List<Integer> digits = new ArrayList<>();

		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i))) {
				digitStr += line.charAt(i);
			} else {
				if (!digitStr.isEmpty()) {
					digits.add(Integer.parseInt(digitStr));
					digitStr = "";
				}
			}
		}
		return digits;
	}

	static List<Integer> splitTriangles(List<Integer> list, int from, int till){
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++){
			if (i >= from && i < till)
				result.add(list.get(i));
		}
		return result;
	}

	static List<Double> abcDistance (List<Integer> xyzPoints){
		List<Double> abc = new ArrayList<>(3);
		//ab distance
		abc.add((double)((xyzPoints.get(3) - xyzPoints.get(0)) + (xyzPoints.get(4) - xyzPoints.get(1)) + (xyzPoints.get(5) - xyzPoints.get(2))));
		//bc distance
		abc.add((double)((xyzPoints.get(6) - xyzPoints.get(3)) + (xyzPoints.get(7) - xyzPoints.get(4)) + (xyzPoints.get(8) - xyzPoints.get(5))));
		//ca distance
		abc.add((double)((xyzPoints.get(0) - xyzPoints.get(6)) + (xyzPoints.get(1) - xyzPoints.get(7)) + (xyzPoints.get(2) - xyzPoints.get(8))));
		for (int i = 0; i < 3; i++)
			if (abc.get(i) < 0)
				abc.set(i, abc.get(i) * -1);
		for (int i = 0; i < 3; i++)
				abc.set(i, Math.sqrt(abc.get(i)));
		return abc;
	}

	static List<Double> getNormalValuesAfterPoint(List<Double> numbers){
		String[] strings = new String[3];
		for (int i = 0; i < numbers.size(); i++)
			strings[i] = "" + numbers.get(i);
		for (int i = 0; i < strings.length; i++){
			strings[i] = strings[i].substring(0,6);
			numbers.set(i, Double.parseDouble(strings[i]));
		}
		return numbers;
	}

	static boolean compareTrianglesToSame(List<Double> a, List<Double> b){
		boolean result = false;
		if (a.get(0) < b.get(0)){
			result = a.get(0)/b.get(0) == a.get(1)/b.get(1) && a.get(1)/b.get(1) == a.get(2)/b.get(2);
		} else {
			result = b.get(0)/a.get(0) == b.get(1)/a.get(1) && b.get(1)/a.get(1) == b.get(2)/a.get(2);
		}
		return result;
	}
}
