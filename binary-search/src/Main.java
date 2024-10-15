public class Main {

    static int binary_search(int[] arr, int item) {
        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            int guess = arr[mid];
            if (guess == item)
                return mid;
            else if (guess < item)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        System.out.println(binary_search(arr, 3));
        System.out.println(binary_search(arr, 10));
    }
}