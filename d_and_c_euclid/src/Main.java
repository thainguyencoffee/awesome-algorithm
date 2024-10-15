import java.util.Arrays;

public class Main {

    static int euclid_algorithm(int a, int b) {
        if (b == 0) {
            return a;
        }
        return euclid_algorithm(b, (a % b));
    }

    static int sum(int[] arr) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) {
            return arr[0];
        }
        return arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));
    }

    /*
     * MẸO: Khi bạn viết một hàm đệ quy liên quan đến một mảng,
     * trường hợp cơ bản thường là một mảng trống hoặc một mảng
     * với một phần tử. Nếu bạn bí, hãy thử đó trước.
     * */

    static int count(Integer[] arr, int index) {
        if (arr[index] == null) return 0;
        return 1 + count(arr, index + 1);
    }

    static int max(int[] arr) {
        if (arr.length == 0) return -1;
        if (arr.length == 1) return arr[0];
        int a = max(Arrays.copyOfRange(arr, 1, arr.length));
        if (arr[0] > a)
            return arr[0];
        else
            return a;
    }

    static int binary_search(int[] arr, int target, int low, int high) {
        if (arr.length == 0) return -1;
        if (low >= high) return -1;

        int mid = (low + high) / 2;
        int guess = arr[mid];
        if (guess == target) return mid;
        if (guess > target) return binary_search(arr, target, low, mid -1);
        else return binary_search(arr, target, low + 1, high);
    }

    public static void main(String[] args) {
        // Toi co mot manh dat co D=1680m, R=640m
        // Toi muon chia manh dat thanh cac manh dat nho hon hinh vuong voi DK: cac hinh vuong la lon nhat
        int euclid = euclid_algorithm(1680, 640);
        assert euclid == 80;
        System.out.println("Output: " + euclid);

        int sum = sum(new int[]{1, 2, 3});
        assert sum == 6;
        System.out.println("Sum: " + sum);

        Integer[] list = new Integer[100];
        for (int i = 0; i < 5; i ++)
            list[i] = i;
        int length = count(list, 0);
        assert length == 5;
        System.out.println("length: " + length);

        int max = max(new int[]{1, 1200, 1200, 4, 5});
        assert max == 1200;
        System.out.println("Max: " + max);

        int indexResult = binary_search(new int[]{1, 2, 3, 4, 5, 6}, 5, 0, 5);
        assert indexResult == 4;
        System.out.println("Binary search: " +  indexResult);
    }
}