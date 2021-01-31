import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] input;
		while(scanner.hasNext()) {
			input = scanner.nextLine().toCharArray();
			int n1l = input.length;
			int[] n1 = new int[n1l];
			for(int i = 0; i < n1l; i++)
				n1[i] = input[i]-48;
			
			input = scanner.nextLine().toCharArray();
			int n2l = input.length;
			int[] n2 = new int[n2l];
			if(n1l > n2l) {
				n2 = n1;
				n2l = n1l;
				n1l = input.length;
				n1 = new int[n1l];
				for(int i = 0; i < n1l; i++)
					n1[i] = input[i]-48;
			}else
				for(int i = 0; i < n2l; i++)
					n2[i] = input[i]-48;
			
			
			int[] map = new int[100+ n1l+ n2l];
			for(int i = 0; i < n2l; i++)
				map[i + 100] = n2[i];
			
			int result = n1l + n2l;
			
			for(int i = 100-n1l; i < 100 + n2l; i++) {
				boolean flag = false;
				for(int j = 0; j < n1l; j++)
					map[i+j]+=n1[j];
				
				for(int n : map) {
					if(n > 3) {
						flag = true;
						break;
					}
				}
				
				for(int j = 0; j < n1l; j++)
					map[i+j]-=n1[j];
				
				if(!flag) {
					if(i >= 100 && i + n1l <= 100 + n2l)
						result = n2l;
					else if(i < 100) {
						int cur = n2l + Math.abs(100-i);
						if(result > cur)
							result = cur;
					}else {
						int cur = i = n1l - 100;
						if(result > cur)
							result = cur;
					}
				}
			}
			System.out.println(result);
		}
	}
	

}
