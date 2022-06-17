import java.util.Arrays;
import java.util.Random;
public class ShellSort {
    public static final int ARRAY_SIZE = 50000;
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        int[] checkArray = new int[ARRAY_SIZE];

        for(int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }

        System.arraycopy(array, 0, checkArray, 0, ARRAY_SIZE);
        System.out.println("Start user sort");
        long time = System.currentTimeMillis();
        ShellSort(array);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis()-time) / 1000d);
        System.out.println("Start java lib sort");
        time = System.currentTimeMillis();
        Arrays.sort(checkArray);
        System.out.format("sorted: %.3f seconds\r\n", (System.currentTimeMillis()-time) / 1000d);
        System.out.println("Check result");
        for(int i = 0; i < array.length; i++) {
            if(array[i] != checkArray[i]) {
                throw new RuntimeException("Error in index: "+i);
            }
        }
        System.out.println("OK");

    }

    public static void ShellSort(int[] array) {
        int h = 1;
        while (h*3 < array.length)
            h = h * 3 + 1;

        while(h >= 1) {
            hSort(array, h);
            h = h/3;
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static void hSort(int[] array, int h) {
        int length = array.length;
        for (int i = h; i < length; i++) {
            for (int j = i; j >= h; j = j - h) {
                if (array[j] < array[j - h])
                    swap(array, j, j - h);
                else
                    break;
            }
        }
    }
}
