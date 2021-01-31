import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		scanner.nextLine();
		for(int t = 0; t < T; t++) {
			int N = scanner.nextInt();
			scanner.nextLine();
			int[] numberCount = new int[10];
			for(int i = 1; i <= N; i++) {
				char[] number = String.valueOf(i).toCharArray();
				for(char c : number) 
					numberCount[c-48]++;
			}
			StringBuilder sb = new StringBuilder();
			for(int n : numberCount) {
				sb.append(n);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			System.out.println(sb);
		}
	}

}
