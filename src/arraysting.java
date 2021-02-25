public class arraysting {
    public static void main(String[] args) {
        String[][] arr = new String[5][1];


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                String k = "Hej";
                arr[i][j] = k;

                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
