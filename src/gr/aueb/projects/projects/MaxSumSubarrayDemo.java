package gr.aueb.projects.projects;

public class MaxSumSubarrayDemo {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSub = arr[0];
        int localSub = arr[0];
        int localMinPos = 0, localMaxPos = 0, minPos = 0, maxPos = 0;

        for (int i = 0; (i + 1) < arr.length; i++){
            if (localSub < arr[i+1]){
                localSub = arr[i + 1];
                localMinPos= i + 1;
                localMaxPos = localMinPos;
                System.out.println(localSub + "("+localMinPos+","+localMaxPos+")");
            } else {
                localSub += arr[i + 1];
                localMaxPos++;
                System.out.println(localSub + "("+localMinPos+","+localMaxPos+")");
            }
            if (localSub > maxSub){
                maxSub = localSub;
                minPos = localMinPos;
                maxPos = localMaxPos;
            }
        }

        System.out.printf("Το άθροισμα του μέγιστου υποπίνακα είναι %d στις θέσεις (%d,%d)", maxSub, minPos, maxPos);
    }
}
