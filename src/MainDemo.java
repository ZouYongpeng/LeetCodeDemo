import java.util.Arrays;
import java.util.Comparator;

public class MainDemo {
    public static void main(String[] args) {
        String s = "asdf";
        int[][] array = new int[3][2];
        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return 0;
            }
        });
        int[] a = new int[3];
        
    }
}
