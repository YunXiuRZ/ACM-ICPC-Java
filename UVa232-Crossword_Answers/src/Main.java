import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashSet;
public class Main {

	final static int[][] checkStart = new int[][] {{-1, 0}, {0, -1}};
	static String[][] map;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TreeMap<String, Integer[]> tm = new TreeMap<String, Integer[]>();
		tm.put("Across", new Integer[] {0, 1});
		tm.put("Down", new Integer[] {1, 0});
		int puzzleCount = 0;
		String[] input = scanner.nextLine().split(" ");
		do {
			System.out.printf("puzzle #%d:\n", ++puzzleCount);
			int R = Integer.parseInt(input[0]),
				C = Integer.parseInt(input[1]);
			map = new String[R][C];
			for(int i = 0; i < R; i++) 
				map[i] = scanner.nextLine().split("");
			TreeMap<Integer, Point> start = new TreeMap<Integer, Point>();
			int startCount = 0;
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					Point p = new Point(i, j);
					if(getPoint(p).equals("*"))
						continue;
					for(int[] n : checkStart) {
						Point checkPoint = new Point(p.x+n[0], p.y+n[1]);
						if(!avaliblePoint(checkPoint) || getPoint(checkPoint).equals("*")){
							start.put(++startCount, p);
							break;
						}
					}
				}
			}
			
			for(String direction : tm.keySet()) {
				System.out.println(direction);
				HashSet<Point> hasOutput = new HashSet<Point>();
				Integer[] movement = tm.get(direction);
				for(Integer key : start.keySet()) {
					Point startPoint = start.get(key);
					if(hasOutput.contains(startPoint))
						continue;
					System.out.printf("%3d.", key);
					Point detectPoint = startPoint.clone();
					do {
						System.out.print(getPoint(detectPoint));
						hasOutput.add(detectPoint.clone());
						detectPoint.move(movement[0], movement[1]);
					}while(avaliblePoint(detectPoint) && !isBlack(detectPoint));
					System.out.println("");
				}
			}
			input = scanner.nextLine().split(" ");
			if(!input[0].equals("0"))
				System.out.println("");
		}while(!input[0].equals("0"));
	}
	
	public static String getPoint(Point p){
		return map[p.x][p.y];
	}

	public static boolean avaliblePoint(Point p) {
		try {
			String s = map[p.x][p.y];
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isBlack(Point p) {
		return map[p.x][p.y].equals("*");
	}
}

class Point{
	
	int x;
	int y;	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(int xm, int ym) {
		x+=xm;
		y+=ym;
	}
	
	@Override
	public boolean equals(Object o) {
		Point p = (Point)o;
		return this.x == p.x && this.y == p.y;
	}
	
	@Override
	public int hashCode() {
		return x+y;
	}
	
	@Override
	public Point clone() {
		return new Point(x, y);
	}
}