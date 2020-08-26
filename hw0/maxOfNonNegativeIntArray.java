public class maxOfNonNegativeIntArray{
	public static int max(int[] arr){
		int max = -1;
		for (int i = 0; i < arr.length; i++){
			if (arr[i] > max){
				max = arr[i];
			}
		}
		return max;
	}

	public static void main(String args[]){
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
		System.out.println(max(numbers));
	}
}
