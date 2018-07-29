package sortalgo;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.scene.text.*;

public class Selectionsort{
    public SequentialTransition selectionsort(int[] arr, ArrayList<StackPane> list, double speed) {
        Swap sw = new Swap();
        SequentialTransition st = new SequentialTransition();
        int temp;
        for(int i = 0; i < arr.length; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                st.getChildren().add(sw.farswap(list.get(i),list.get(minIndex),list,speed));
            }
        }
        return st;
    }

    private void claim(boolean b){
        if(!b) throw new Error("sortalgo.Selectionsort Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-10
    private void run(){
        Selectionsort testss = new Selectionsort();
        int[] testarr = new int[]{4, 1, 0, 2, 8, 17, 6, 12, 9, 5};
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
        testss.selectionsort(testarr,testlist,testspeed);
        claim(testarr[0] == 0);
        claim(testarr[1] == 1);
        claim(testarr[2] == 2);
        claim(testarr[3] == 4);
        claim(testarr[4] == 5);
        claim(testarr[5] == 6);
        claim(testarr[6] == 8);
        claim(testarr[7] == 9);
        claim(testarr[8] == 12);
        claim(testarr[9] == 17);
        System.out.print("Sorted Array: ");
        for(int j=0; j < testarr.length; j++) {
            System.out.print(testarr[j]+" ");
        }
        System.out.println("\nAll sortalgo.Selectionsort Tests Passed!");
    }

    public static void main(String[] args){
        Selectionsort mss = new Selectionsort();
        mss.run();
    }
}
