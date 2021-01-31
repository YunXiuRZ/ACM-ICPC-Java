import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			int n1 = scanner.nextInt(),
				n2 = scanner.nextInt();
			System.out.printf("%d/%d = %d.", n1, n2, n1/n2);
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			int k = n1%n2;
			while(true){
				if(numbers.contains(k))
					break;
				numbers.add(k);
				k*=10;
				k%=n2;
			}
			int count = 0, c = 0;;
			boolean flag = false;
			for(Integer n : numbers) {
				if(n == k) {
					System.out.print("(");
					flag = true;
				}
				if(!flag)
					c++;
				count++;
				if(count <= 50)
					System.out.print(n*10/n2);
				if(count == 50) {
					System.out.print("...");
				}
			}
			System.out.println(")");
			System.out.printf("   %d = number of digits in repeating cycle\n\n", count-c);
		}
	}

}
