import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		scanner.nextLine();
		for(int i = 0; i < T; i++) {
			char[] input = scanner.nextLine().toCharArray();
			int result = 0, OCount = 0;
			for(char c : input)
				if(c == 'O')
					result += ++OCount;
				else
					OCount = 0;
			System.out.println(result);
		}
	}

}
