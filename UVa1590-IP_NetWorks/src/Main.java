import java.util.Scanner;
public class Main {

	static String[] IPB;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int M = scanner.nextInt();
		scanner.nextLine();
		int[][] IP = new int[M][4];
		char[] input;
		for(int i = 0; i < M; i++) {
			input = scanner.nextLine().toCharArray();
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for(char c : input) {
				if(c == '.') {
					IP[i][count++] = Integer.parseInt(sb.toString());
					sb.delete(0, sb.length());
					continue;
				}
				sb.append(c);
			}
			IP[i][count++] = Integer.parseInt(sb.toString());
		}
			
		IPB = new String[M];
		for(int i = 0; i < M; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < 4; j++) {
				int unit = (int)Math.pow(2, 7);
				int part = IP[i][j];
				while(unit >= 1) {
					sb.append(part/unit);
					part%=unit;
					unit/=2;
				}
			}
			IPB[i] = sb.toString();
		}
		
		int n = 1;
		for(; n <= 32; n++) {
			if(!sameLevel(n-1)) {
				n--;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) 
			sb.append(IPB[0].charAt(i));
		while(sb.length() < 32)
			sb.append(0);
		
		for(int i = 0; i < 4; i++) {
			System.out.print(toDemical(sb.substring(i*4, (i+1)*4)));
			if(i != 3)
				System.out.print(".");
		}
		
		
		
		return ;
	}

	public static boolean sameLevel(int n) {
		char check = IPB[0].charAt(n);
		for(String s : IPB) 
			if(s.charAt(n) != check)
				return false;
		return true;
	}
	
	public static String toDemical(String s) {
		int unit = (int)Math.pow(2, 7);
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(s);
		while(unit >= 1) {
			sb.append(n/unit);
			n%= unit;
			unit*=2;
		}
		return sb.toString();
	}
}
