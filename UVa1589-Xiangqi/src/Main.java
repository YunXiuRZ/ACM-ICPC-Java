import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	static ArrayList<Chess> chesses = new ArrayList<Chess>();
	static BlackGeneral blackGeneral;
	static Chess[][] map = new Chess[11][10];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		do {
			chesses.clear();
			map = new Chess[11][10];
			int N = Integer.parseInt(input[0]),
				bgx = Integer.parseInt(input[1]),
				bgy = Integer.parseInt(input[2]);
			blackGeneral = new BlackGeneral(bgx, bgy);
			chesses.remove(blackGeneral);
			map[bgx][bgy] = null;
			for(int i = 0; i < N; i++) {
				input = scanner.nextLine().split(" ");
				char category = input[0].charAt(0);
				int x = Integer.parseInt(input[1]),
					y = Integer.parseInt(input[2]);
				
				switch(category) {
				case 'G':
					General g = new General(x, y);
					break;
				case 'R':
					Chariot r = new Chariot(x, y);
					break;
				case 'H':
					Horse h = new Horse(x, y);
					break;
				case 'C':
					Cannon c = new Cannon(x, y);
					break;
				}
			}
			
			if(blackWin() || blackGeneral.canAlive())
				System.out.println("NO");
			else
				System.out.println("YES");
			
			scanner.nextLine();
			input = scanner.nextLine().split(" ");
		}while(!input[0].equals("0"));
	}
	
	public static General getRedGeneral() {
		for(Chess c : chesses)
			if(c instanceof General)
				return (General)c;
		return null;
	}

	public static boolean blackWin() {
		if(hasChessOnWay(blackGeneral, getRedGeneral()) == 0)
			return true;
		return false;
	}
	
	public static boolean hasChess(int x, int y) {
		try {
			if(map[x][y].equals(null))
				return false;
			else
				return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static int hasChessOnWay(Chess start, Chess finish) {
		int xd = finish.x-start.x;
		int yd = finish.y-start.y;
		int result = -1;
		
		if(xd == 0) {
			result = 0;
			if(yd > 0) {
				for(int i = 1; i < yd; i++) 
					if(hasChess(start.x, start.y+i))
						result++;
			}else
				for(int i = -1; i > yd; i--)
					if(hasChess(start.x, start.y+i))
						result++;
			
		}else if(yd == 0){
			result = 0;
			if(xd > 0) {
				for(int i = 1; i < xd; i++)
					if(hasChess(start.x+i, start.y))
						result++;
			}else
				for(int i = -1; i > xd; i--)
					if(hasChess(start.x+i, start.y))
						result++;
			
		}
		return result;
	}
	

	static class Chess{
		
		int x;
		int y;
		
		public Chess(int x, int y) {
			this.x = x;
			this.y = y;
			chesses.add(this);
			map[x][y] = this;
		}
		
		public boolean canEatGeneral() {
			return false;
		}
		
		public boolean isPosition(int x, int y) {
			if(x == this.x && y == this.y)
				return true;
			return false;
		}
	}
	
	static class ChessEatStraight extends Chess {
		
		public ChessEatStraight(int x, int y) {
			super(x, y);
		}
		
		public boolean canEatGeneral() {
			if(ChessOnWay() == 0)
				return true;
			return false;
		}
		
		public int ChessOnWay() {
				return hasChessOnWay(this, blackGeneral);
			}
	}

	static class Horse extends Chess{
		
		int[][] movements = new int[][] {
			{1, 2}, {-1, 2},
			{2, 1}, {2, -1},
			{-2, -1}, {-2, 1},
			{-1, -2}, {1, -2}};
			
		int[][] hob = new int[][]{ 
			{0, 1}, {0, 1},
			{1, 0}, {1, 0},
			{-1, 0}, {-1, 0},
			{0, -1}, {0, -1}
		};
		
		int turnPointer = 0;
		
		public Horse(int x, int y) {
			super(x, y);
		}
		
		public boolean canEatGeneral() {
			
			turnPointer = 0;
			boolean result = false;
			
			for(int[] m : movements) {
				int dx = this.x+m[0],
					dy = this.y+m[1];
				if(blackGeneral.isPosition(dx, dy)) {
					if(!isHob())
						result = true;
					break;
				}
				turnPointer++;
			}
			return result;
		}
		
		public boolean isHob() {
			int[] h = hob[turnPointer];
			int dx = this.x + h[0],
				dy = this.y + h[1];
			if(hasChess(dx, dy))
				return true;
			return false;
		}
		
		
	}

	static class BlackGeneral extends Chess{
		
		int stepPointer = 0;
		int[][] steps = new int[][] {
				{1, 0}, {-1, 0},
				{0, 1}, {0, -1}
		};
		
		public BlackGeneral(int x, int y) {
			super(x, y);
		}
		
		public boolean canAlive() {
			stepPointer = 0;
			for(int i = 0; i < 4; i++) {
				boolean flag = true;
				if(move()) {
					for(Chess c : chesses) {
						if(this.isPosition(c.x, c.y)) {
							continue;
						}
						if(c.canEatGeneral()) {
							flag = false;
							break;
						}
					}
					back();
					if(flag)
						return true;
				}
				stepPointer++;
			}
			return false;
		}
		
		public boolean move() {
			int[] s = steps[stepPointer];
			x+=s[0];
			y+=s[1];
			if(legalPosition())
				return true;
			else {
				back();
				return false;
			}
		}
		
		public void back() {
			int[] s = steps[stepPointer];
			x-=s[0];
			y-=s[1];
		}
		
		public boolean legalPosition() {
			if(x < 1 || x > 3 || y < 4 || y > 6)
				return false;
			return true;
		}
	}
	
	static class General extends ChessEatStraight {
		
		public General(int x, int y) {
			super(x, y);
		}
		
		
	}
	
	static class Chariot extends ChessEatStraight {
		
		public Chariot(int x, int y) {
			super(x, y);
		}
		
	}
	
	static class Cannon extends ChessEatStraight{
		
		public Cannon(int x, int y) {
			super(x, y);
		}
		
		@Override
		public boolean canEatGeneral() {
			if(ChessOnWay() == 1)
				return true;
			return false;
		}
		
	}
	

}


