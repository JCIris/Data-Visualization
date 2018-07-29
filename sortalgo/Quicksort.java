package sortalgo;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.scene.text.*;

public class Quicksort {
    int partition(int[] arr, int left, int right, ArrayList<StackPane> list, double speed, SequentialTransition st){
        Swap sw = new Swap();
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i < j){
            while (arr[i] < pivot && i<j)
                i++;
            while (arr[j] > pivot && i<j)
                j--;
            if (i <= j){
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                st.getChildren().add(sw.farswap(list.get(i), list.get(j), list, speed));
                i++;
                j--;
            }
        }
        return i;
    }

    public SequentialTransition quicksort(int arr[], int left, int right, ArrayList<StackPane> list, double speed, SequentialTransition st) {
        int index = partition(arr, left, right, list, speed, st);
        if (left < index-1)
            quicksort(arr, left, index-1, list, speed, st);
        if (index < right)
            quicksort(arr, index, right, list, speed, st);
        return st;
    }

    private void claim(boolean b){
        if(!b) throw new Error("sortalgo.Quicksort Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-10
    private void run(){
        Quicksort testqs = new Quicksort();
        int[] testarr = new int[]{5,4, 7, 0, 1, 15, 12, 11, 10,7};
        ArrayList<Text> testtext = new ArrayList<Text>(10);
        ArrayList<Rectangle> testrec = new ArrayList<Rectangle>(10);
        StackPane testsp = new StackPane();
        ArrayList<StackPane> testlist = new ArrayList<StackPane>();
        for(int i=0; i < testarr.length; i++) {
            testsp.setId(String.valueOf(testarr[i]));
            testtext.add(i,new Text(String.valueOf(testarr[i])));
            testrec.add(i,new Rectangle());
            testsp.getChildren().addAll(testrec.get(i), testtext.get(i));
            testlist.add(testsp);
        }
        double testspeed = 400;
        testqs.quicksort(testarr,0,9,testlist,testspeed,new SequentialTransition());
        claim(testarr[0] == 0);
        claim(testarr[1] == 1);
        claim(testarr[2] == 4);
        claim(testarr[3] == 5);
        claim(testarr[4] == 7);
        claim(testarr[5] == 7);
        claim(testarr[6] == 10);
        claim(testarr[7] == 11);
        claim(testarr[8] == 12);
        claim(testarr[9] == 15);
        System.out.print("Sorted Array: ");
        for(int j=0; j < testarr.length; j++) {
            System.out.print(testarr[j]+" ");
        }
        System.out.println("\nAll sortalgo.Quicksort Tests Passed!");
    }

    public static void main(String[] args){
        Quicksort mqs = new Quicksort();
        mqs.run();
    }
}
