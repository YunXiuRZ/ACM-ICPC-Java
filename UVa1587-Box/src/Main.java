import java.util.Scanner;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			Rectangle[] rectangles = new Rectangle[6];
			for(int i = 0; i < 6; i++) {
				int n1 = scanner.nextInt(),
					n2 = scanner.nextInt();
				rectangles[i] = new Rectangle(n1, n2);
			}
			Arrays.sort(rectangles);
			if(!(rectangles[0].equals(rectangles[1])&&
			   rectangles[2].equals(rectangles[3])&&
			   rectangles[4].equals(rectangles[5])))
				System.out.println("IMPOSSIBLE");
			else {
				int[] edgs = new int[3];
				edgs[0] = rectangles[0].x;
				edgs[1] = rectangles[4].x;
				edgs[2] = rectangles[5].y;
				
				boolean flag = true;
				Rectangle[] check = new Rectangle[] {
						new Rectangle(edgs[0], edgs[1]),
						new Rectangle(edgs[0], edgs[2]),
						new Rectangle(edgs[1], edgs[2])
				};
				Arrays.sort(check);
				for(int i = 0; i < 3; i++) {
					if(!check[i].equals(rectangles[i*2])) {
						flag = false;
						break;
					}
				}
				if(flag)
					System.out.println("POSSIBLE");
				else
					System.out.println("IMPOSSIBLE");
			}
		}
	}

}

class Rectangle implements Comparable{
	
	int x;
	int y;
	
	public Rectangle(int x, int y) {
		if(y < x) {
			this.x = y;
			this.y = x;
		}else {
			this.x = x;
			this.y = y;
		}
	}
	
	@Override
	public int compareTo(Object o) {
		Rectangle r = (Rectangle)o;
		if(this.x == r.x)
			return this.y - r.y;
		return this.x - r.x;
	}
	
	@Override
	public boolean equals(Object o) {
		Rectangle r = (Rectangle)o;
		return this.x == r.x && this.y == r.y;
	}
}