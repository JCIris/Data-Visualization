package sortalgo;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.*;
import javafx.scene.text.*;

public class Bubblesort{
    public SequentialTransition bubblesort(int[] arr, ArrayList<StackPane> list, double speed){
        Swap sw = new Swap();
        SequentialTransition st = new SequentialTransition();
        int temp;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 1; j < arr.length - i; j++){
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    st.getChildren().add(sw.swap(list.get(j - 1), list.get(j), list, speed));
                }
            }
        }
        return st;
    }

    private void claim(boolean b){
        if(!b) throw new Error("sortalgo.Bubblesort Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-10
    private void run(){
        Bubblesort testbs = new Bubblesort();
        int[] testarr = new int[]{4, 2, 7, 2, 8, 17, 2, 12, 9, 10};
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
        testbs.bubblesort(testarr,testlist,testspeed);
        claim(testarr[0] == 2);
        claim(testarr[1] == 2);
        claim(testarr[2] == 2);
        claim(testarr[3] == 4);
        claim(testarr[4] == 7);
        claim(testarr[5] == 8);
        claim(testarr[6] == 9);
        claim(testarr[7] == 10);
        claim(testarr[8] == 12);
        claim(testarr[9] == 17);
        System.out.print("Sorted Array: ");
        for(int j=0; j < testarr.length; j++) {
            System.out.print(testarr[j]+" ");
        }
        System.out.println("\nAll sortalgo.Bubblesort Tests Passed!");
    }

    public static void main(String[] args){
        Bubblesort mbs = new Bubblesort();
        mbs.run();
    }
}