import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		while(true) {
			String input = scanner.nextLine();
			if(input.equals("Z"))
				break;
			count++;
			System.out.printf("Puzzle #%d:\n", count);
			int L = input.length();
			char[][] puzzle = new char[L][L];
			puzzle[0] = input.toCharArray();
			for(int i = 1; i < L; i++) {
				input = scanner.nextLine();
				System.arraycopy(input.toCharArray(), 0, puzzle[i], 0, input.length());
				if(input.length() != L)
					puzzle[i][L-1] = ' ';
			}
			
			int[] blankPosition = null;
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < L; j++) {
					if(puzzle[i][j] == ' ') {
						blankPosition = new int[]{i, j};
						break;
					}
				}
			}
			boolean flag = true;
			O:
			while(true) {
			char[] con = scanner.nextLine().toCharArray();
			for(char c : con) {
				if(c == '0')
					break O;
				int[] targetPosition;
				switch(c){
				case 'A':
					targetPosition = new int[] {blankPosition[0]-1, blankPosition[1]};
					try {
						puzzle[blankPosition[0]][blankPosition[1]] = 
								puzzle[targetPosition[0]][targetPosition[1]];
						puzzle[targetPosition[0]][targetPosition[1]] = ' ';
						blankPosition = targetPosition;
					}catch(Exception e) {
						flag = false;
						System.out.println("This puzzle has no final configuration.");
						break O;
					}
					break;
				case 'B':
					targetPosition = new int[] {blankPosition[0]+1, blankPosition[1]};
					try {
						puzzle[blankPosition[0]][blankPosition[1]] = 
								puzzle[targetPosition[0]][targetPosition[1]];
						puzzle[targetPosition[0]][targetPosition[1]] = ' ';
						blankPosition = targetPosition;
					}catch(Exception e) {
						flag = false;
						System.out.println("This puzzle has no final configuration.");
						break O;
					}
					break;
				case 'L':
					targetPosition = new int[] {blankPosition[0], blankPosition[1]-1};
					try {
						puzzle[blankPosition[0]][blankPosition[1]] = 
								puzzle[targetPosition[0]][targetPosition[1]];
						puzzle[targetPosition[0]][targetPosition[1]] = ' ';
						blankPosition = targetPosition;
					}catch(Exception e) {
						flag = false;
						System.out.println("This puzzle has no final configuration.");
						break O;
					}
					break;
				case 'R':
					targetPosition = new int[] {blankPosition[0], blankPosition[1]+1};
					try {
						puzzle[blankPosition[0]][blankPosition[1]] = 
								puzzle[targetPosition[0]][targetPosition[1]];
						puzzle[targetPosition[0]][targetPosition[1]] = ' ';
						blankPosition = targetPosition;
					}catch(Exception e) {
						flag = false;
						System.out.println("This puzzle has no final configuration.");
						break O;
					}
					break;
				}
			}
			}
			if(flag) {
				StringBuilder sb = new StringBuilder();
				for(char[] ca : puzzle) {
					for(char c : ca) {
						sb.append(c);
						sb.append(" ");
					}
					System.out.println(sb);
					sb.delete(0, sb.length());
				}
			}
			System.out.println("");
		}
	}

}
