import java.util.Scanner;
public class Main {

	static char[][] board = new char[8][8];
	static char my;
	static char army;
	static int[][] horizontal = new int[][] {
		{0, 1},
		{0, -1}
	};
	static int[][] vertical = new int[][] {
		{1, 0},
		{-1, 0}
	};
	static int[][]  diagonal = new int[][] {
		{1, 1},
		{1, -1},
		{-1, 1},
		{-1, -1}
	};
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input;
		int N = scanner.nextInt();
		scanner.nextLine();
		for(; N > 0; N--) {
			for(int i = 0; i < 8; i++) 
				board[i] = scanner.nextLine().toCharArray();
			my = scanner.nextLine().charAt(0);
			army = my == 'B'? 'W' : 'B';
			
			O:
			while(true) {
				input = scanner.nextLine();
				char c = input.charAt(0);
				switch(c) {
				case 'L':
					printLegal();
					break;
				case 'M':
					int x = input.charAt(1)-48,
						y = input.charAt(2)-48;
					step(x-1, y-1);
					printChessAmount();
					break;
				case 'Q':
					printBoard();
					break O;
				}
			}
			if(N != 1)
				System.out.println("");
		}
	}

	public static void turn() {
		army = my;
		my = my == 'B'? 'W' : 'B';
	}
	
	public static boolean isMyChess(int x, int y) {
		try {
			if(board[x][y] == my)
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	public static boolean isArmyChess(int x, int y) {
		try {
			if(board[x][y] == army)
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	public static boolean wayLegal(int x, int y, int xd, int yd) {
		int xp = x+xd,
			yp = y+yd;
		boolean flag = false;
		while(isArmyChess(xp, yp)) {
			flag = true;
			xp+=xd;
			yp+=yd;
		}
		if(isMyChess(xp, yp)) 
			if(flag)
				return true;
		
		return false;
	}
	
	public static boolean movementsLegal(int x, int y, int[][] movements) {
		for(int[] m : movements) 
			if(wayLegal(x, y, m[0], m[1]))
				return true;
		return false;
	}
	
	public static boolean horizontalLegal(int x, int y) {
		if(movementsLegal(x, y, horizontal))
			return true;
		return false;
	}
	
	public static boolean verticalLegal(int x, int y) {
		if(movementsLegal(x, y, vertical))
			return true;
		return false;
	}
	
	public static boolean diagonalLegal(int x, int y) {
		if(movementsLegal(x, y,  diagonal))
			return true;
		return false;
	}
	
	public static boolean positionLegal(int x, int y) {
		if(board[x][y] != '-')
			return false;
		if(horizontalLegal(x, y) || verticalLegal(x, y) || diagonalLegal(x, y))
			return true;
		return false;
	}
	
	public static void printLegal() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(positionLegal(i, j)) {
					sb.append(String.format("(%d,%d) ", i+1, j+1));
				}
			}
		}
		if(sb.length() == 0)
			System.out.println("No legal move.");
		else {
			sb.deleteCharAt(sb.length()-1);
			System.out.println(sb);
		}
	}

	public static void printChessAmount() {
		int b = 0,
			w = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] == 'B')
					b++;
				else if(board[i][j] == 'W')
					w++;
			}
		}
		
		System.out.printf("Black -%3d White -%3d\n", b, w);
	}

	public static void printBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) 
				System.out.print(board[i][j]);
			System.out.println("");
		}
	}

	public static void eatByTheWay(int x, int y, int xd, int yd) {
		int xp = x+xd,
			yp = y+yd;
		while(isArmyChess(xp, yp)) {
			board[xp][yp] = my;
			xp+=xd;
			yp+=yd;
		}
	}
	
	public static void eatHorizontally(int x, int y) {
		for(int[] m : horizontal) {
			if(wayLegal(x, y, m[0], m[1])) {
				eatByTheWay(x, y, m[0], m[1]);
			}
		}
	}
	
	public static void eatVertically(int x, int y) {
		for(int[] m : vertical) {
			if(wayLegal(x, y, m[0], m[1])) {
				eatByTheWay(x, y, m[0], m[1]);
			}
		}
	}

	public static void eatDiagonally(int x, int y) {
		for(int[] m : diagonal) {
			if(wayLegal(x, y, m[0], m[1])) {
				eatByTheWay(x, y, m[0], m[1]);
			}
		}
	}

	public static boolean hasLegalWay() {
		for(int i = 0; i < 8; i++) 
			for(int j = 0; j < 8; j++) 
				if(positionLegal(i, j)) 
					return true;
		return false;
	}
	
	public static void step(int x, int y) {
		if(!hasLegalWay())
			turn();
		board[x][y] = my;
		eatHorizontally(x, y);
		eatVertically(x, y);
		eatDiagonally(x, y);
		turn();
	}
}
