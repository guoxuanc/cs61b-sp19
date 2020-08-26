public class DrawingATriangle{
	public static void drawTriangle(int N){
		int n = 1;
		while (n < N + 1){
			for (int i=0; i<n; i++){
				System.out.print("*");
			}
			System.out.println("");
			n += 1;
		}
	}

	public static void main(String args[]){
		drawTriangle(10);
	}
}
