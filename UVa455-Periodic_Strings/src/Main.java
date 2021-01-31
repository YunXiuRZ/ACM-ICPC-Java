import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		scanner.nextLine();
		for(int t = 0; t < T; t++) {
			scanner.nextLine();
			char[] input = scanner.nextLine().toCharArray();
			int sl = input.length;
			O:
			for(int i = 1; i <= sl; i++) {
				if(sl%i != 0)
					continue;
				int re = sl/i;
				for(int j = 1; j < re; j++) {
					for(int k = 0; k < i; k++) {
						int index = (j-1)*i + k;
						if(input[index] != input[index + i])
							continue O;
					}
				}
				System.out.println(i);
				break O;
			}
			if(t != T-1)
				System.out.println("");
		}
	}

}
