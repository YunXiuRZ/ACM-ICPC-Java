import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input;
		int T = scanner.nextInt();
		scanner.nextLine();
		do {
			input = scanner.nextLine().split(" ");
			int M = Integer.parseInt(input[0]),
				N = Integer.parseInt(input[1]);
			int result = 0;
			String[][] dnas = new String[M][N];
			for(int i = 0; i < M; i++) 
				dnas[i] = scanner.nextLine().split("");
			
			for(int i = 0; i < N; i++) {
				int[] count = new int[92];
				for(int j = 0; j < M; j++) {
					count[dnas[j][i].charAt(0)]++;
				}
				int index = 0;
				for(int j = 0; j < 92; j++) 
					if(count[index] < count[j])
						index = j;
				System.out.print((char)index);
				result+= M-count[index];
			}
			System.out.printf("\n%d\n", result);
		}while(--T > 0);
	}

}
