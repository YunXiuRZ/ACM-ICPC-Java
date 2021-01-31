import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input;
		while(scanner.hasNext()) {
			input = scanner.nextLine().split(" ");
			char[] s = input[0].toCharArray(),
				   t = input[1].toCharArray();
			int k = 0;
			boolean result = true;
			for(int i = 0; i < s.length; i++) {
				try {
					while(s[i] != t[k])
						k++;
					k++;
				}catch(ArrayIndexOutOfBoundsException e) {
					result = false;
				}
			}
			if(result)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}

}
