package task4;

public class TaskFour {
	public static void main(String[] args) {
		int result;
		if (args[0] == null || args[1] == null){
			System.out.println("Мало аргументов");
			return;
		}
		String one = args[0] + "`";
		String two = args[1] + "`";
		result = compareStrings(one, two, 0, 0);
		if (result == 1)
			System.out.println("OK");
		else
			System.out.println("KO");

	}

	static int compareStrings(String str1, String str2, int indexOne, int indexTwo) {
		char s1 = 0;
		char s2 = 0;
			s1 = str1.charAt(indexOne);
			s2 = str2.charAt(indexTwo);
			if (s1 == '`' && s2 == '*')
				return compareStrings(str1, str2, indexOne, indexTwo + 1);
			if (s1 != '`' && s2 == '*')
				return (compareStrings(str1, str2, indexOne + 1, indexTwo)) + (compareStrings(str1, str2, indexOne, indexTwo + 1));
			if (s1 == s2 && s2 != '`')
				return (compareStrings(str1, str2, indexOne + 1, indexTwo + 1));
			if (s1 == '`' && s2 == '`')
				return 1;
		return 0;
	}
}
