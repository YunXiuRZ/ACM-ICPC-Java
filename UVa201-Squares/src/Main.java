import java.util.Scanner;
public class Main {

	static boolean[][] H;
	static boolean[][] V;
	static int[][] HL;
	static int[][] VL;
	static int[] squares;
	
	public static void main(String[] args) {
		int count = 0;
		Scanner scanner = new Scanner(System.in);
		String[] input;
		do {
			
			int N = scanner.nextInt();
			scanner.nextLine();
			int M = scanner.nextInt();
			scanner.nextLine();
			System.out.printf("Problem #%d\n\n", ++count);
			
			H = new boolean[N+1][N+1];
			V = new boolean[N+1][N+1];
			for(; M > 0; M--) {
				input = scanner.nextLine().split(" ");
				char c = input[0].charAt(0);
				int i = Integer.parseInt(input[1]),
					j = Integer.parseInt(input[2]);
				switch(c) {
				case 'H':
					H[i][j] = true;
					break;
				case 'V':
					V[j][i] = true;
					break;
				}
			}
			
			squares = new int[N+1];
			HL = new int[N+1][N+1];
			VL = new int[N+1][N+1];
			for(int i = N; i > 0; i--) {
				for(int j = N; j > 0; j--) {
					if(H[i][j]) 
						if(j == N) 
							HL[i][j] = 1;
						else
							HL[i][j] = HL[i][j+1]+1;
					
					if(V[i][j])
						if(i == N)
							VL[i][j] = 1;
						else
							VL[i][j] = VL[i+1][j]+1;
				}
			}
			
			int result = 0;
			for(int i = 1; i < N; i++) 
				for(int j = 1; j < N; j++) 
					dotHasSquare(i, j);
			boolean flag = false;
			for(int i = 1; i <= N; i++) {
				if(squares[i] == 0)
					continue;
				flag = true;
				System.out.printf("%d square (s) of size %d\n", squares[i], i);
			}
			if(!flag)
				System.out.println("No completed squares can be found.");
			if(scanner.hasNext())
				System.out.printf("\n**********************************\n\n");
			
		}while(scanner.hasNext());
	}
	
	public static boolean canBeSquare(int x, int y, int edge) {
		if(HL[x+edge][y] >= edge && VL[x][y+edge] >= edge)
			return true;
		return false;
	}

	public static void dotHasSquare(int x, int y) {
		int edgeMax = HL[x][y] > VL[x][y]? VL[x][y] : HL[x][y];
		for(int i = 1; i <= edgeMax; i++) 
			if(canBeSquare(x, y, i))
				squares[i]++;
	}
}
