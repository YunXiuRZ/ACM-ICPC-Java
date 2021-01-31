import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double[] M = new double[91];
		M['C'] = 12.01;
		M['H'] = 1.008;
		M['O'] = 16.00;
		M['N'] = 14.01;
		int T = scanner.nextInt();
		scanner.nextLine();
		for(int i = 0; i < T; i++) {
			double result = 0;
			char[] formula = scanner.nextLine().toCharArray();
			int nCount = 0, nTotal = 1;
			char elementBuf = 0;
			for(char c : formula) {
				if(c >= 65) {
					if(nTotal == 0)
						nTotal = 1;
					result+=M[elementBuf]*nTotal;
					nCount = 0;
					nTotal = 0;
					elementBuf = c;
				}else {
					nTotal*=10;
					nTotal += c - '0';
					nCount++;
				}
			}
			if(nTotal == 0)
				nTotal = 1;
			result+=M[elementBuf]*nTotal;
			System.out.printf("%.3f\n", result);
		}
	}

}
