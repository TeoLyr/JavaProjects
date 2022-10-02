package gr.aueb.projects.projects;

public class ParkedCarsAppDemo {
    public static void main(String[] args) {
        int[][] arr = {{1012, 1136}, {1317, 1417}, {1015, 1020}, {1024, 1330}, {1100, 1400}, {1105, 1316}, {1019, 1130}};
        int[][] parkedCars = new int[14][2];

        for (int i = 0; i < arr.length; i++) {
            parkedCars[i][0] = arr[i][0];
            parkedCars[i][1] = 1;
            parkedCars[i + arr.length][0] = arr[i][1];
            parkedCars[i + arr.length][1] = 0;
        }

        for (int i = parkedCars.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (parkedCars[j][0] > parkedCars[j + 1][0]) {
                    swap(parkedCars, j, j+1);
                }
            }
        }

        for (int i = 0; i < parkedCars.length; i++) {
            System.out.println(parkedCars[i][0] + ": " + parkedCars[i][1]);
        }

        System.out.println(maxCars(parkedCars));


    }


    public static void swap(int[][] arr, int i, int j){
        int temp = arr[i][0];
        arr[i][0] = arr[j][0];
        arr[j][0] = temp;

        int temp1 = arr[i][1];
        arr[i][1] = arr[j][1];
        arr[j][1] = temp1;
    }

    public static int maxCars(int[][] cars){
        int max = 0, count = 0;

        for (int i = 0; i < cars.length; i++){
            if (cars[i][1] == 1){
              count++;
              if (count > max){
                  max = count;
              }
            }
            else {
                count--;
            }
        }
        return max;
    }
}
