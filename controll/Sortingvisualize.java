/*
This is a class to do the sorting algorithm visualization
Algorithms are in the package "algo"
 */
package controll;

import sortalgo.*;
import sound.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import javafx.scene.control.Alert;
import javafx.scene.image.*;
import javafx.geometry.Insets;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import java.util.List;
import javafx.application.Application;

public class Sortingvisualize extends Application{
    private Random random = new Random(5);
    private double speed = 300;
    private int choosesort;
    private HBox hb = new HBox(20);
    private HBox menuhb = new HBox(20);
    private HBox slidebox = new HBox(5);
    private HBox selectionMenu = new HBox(40);
    private ArrayList<StackPane> list = new ArrayList<>();
    private BorderPane borderPane = new BorderPane();
    private Button sort = makeButton("SORT");
    private Button reset = makeButton("RESET");
    private Text slow = makeText("SLOW");
    private Text fast = makeText("FAST");
    private Text bubbleSort = makeText("Bubble Sort");
    private Text selectionSort = makeText("Selection Sort");
    private Text insertionSort = makeText("Insertion Sort");
    private Text shellSort = makeText("Shell Sort");
    private Text quickSort = makeText("Quick Sort");
    private Slider choosespeed = new Slider(100, 3000, 300);
    private Label author = new Label("Author");
    private AnchorPane bottomPane = new AnchorPane();
    private Soundplayer sp = new Soundplayer();

    public void start(Stage primaryStage) throws Exception {
        setCenterView();
        setBottomView();
        setTopView();

        handleBubbleSort();
        handleSelectionSort();
        handleInsertionSort();
        handleShellSort();
        handleQuickSort();
        handleButtonSort();
        reset.setOnAction(this::handleButtonReset);

        Scene scene = new Scene(borderPane, 800, 400);
        scene.getStylesheets().add("visualizationstyle.css");
        primaryStage.setTitle("SortingVisualization");
        primaryStage.getIcons().add(new Image("file:pic/smile.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    void setCenterView(){
        hb.setAlignment(Pos.CENTER);
        list = setList(list);
        hb.getChildren().addAll(list);
        borderPane.setCenter(hb);
    }

    void setBottomView() {
        sort.getStyleClass().add("button");
        reset.getStyleClass().add("button");
        slow.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        slow.setFill(Color.LIGHTCORAL);
        fast.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
        fast.setFill(Color.CORAL);
        choosespeed.valueProperty().addListener((
                ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) -> {
            sp.makesound("sound/speed.mp3");
            speed = (double)new_val;
        });
        slidebox.getChildren().addAll(fast, choosespeed, slow);
        setAuthor();
        menuhb.getChildren().addAll(slidebox, sort, reset);
        menuhb.setPadding(new Insets(20, 20, 20, 20));
        bottomPane.getChildren().addAll(menuhb,author);
        borderPane.setBottom(bottomPane);
    }

    void setAuthor(){
        author.getStyleClass().add("alertstyle");
        author.setLayoutX(730);
        author.setLayoutY(30);
        author.setOnMouseClicked(event -> {
            sp.makesound("sound/alert.mp3");
            author.getStyleClass().add("authorclicked");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AuthorInformation:");
            alert.setContentText("by Hui Yan");
            Image smile = new Image("file:pic/information.png",20,20,false,false);
            ImageView iv = new ImageView(smile);
            alert.setGraphic(iv);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:pic/information.png"));
            alert.show();
        });
    }

    void setTopView(){
        selectionMenu.setAlignment(Pos.CENTER);
        bubbleSort.getStyleClass().add("topmenu");
        selectionSort.getStyleClass().add("topmenu");
        insertionSort.getStyleClass().add("topmenu");
        shellSort.getStyleClass().add("topmenu");
        quickSort.getStyleClass().add("topmenu");
        selectionMenu.getChildren().addAll(bubbleSort, selectionSort, insertionSort, shellSort, quickSort);
        borderPane.setTop(selectionMenu);
    }

    // handle the top menus
    // when you choose one, its style changes and the others back to original shapes
    void handleBubbleSort(){
        bubbleSort.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            choosesort = 0;
            bubbleSort.getStyleClass().clear();
            selectionSort.getStyleClass().clear();
            insertionSort.getStyleClass().clear();
            shellSort.getStyleClass().clear();
            quickSort.getStyleClass().clear();
            bubbleSort.getStyleClass().add("topmenuselected");
            selectionSort.getStyleClass().add("topmenu");
            insertionSort.getStyleClass().add("topmenu");
            shellSort.getStyleClass().add("topmenu");
            quickSort.getStyleClass().add("topmenu");
        });
    }

    void handleSelectionSort(){
        selectionSort.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            choosesort = 1;
            bubbleSort.getStyleClass().clear();
            selectionSort.getStyleClass().clear();
            insertionSort.getStyleClass().clear();
            shellSort.getStyleClass().clear();
            quickSort.getStyleClass().clear();
            bubbleSort.getStyleClass().add("topmenu");
            selectionSort.getStyleClass().add("topmenuselected");
            insertionSort.getStyleClass().add("topmenu");
            shellSort.getStyleClass().add("topmenu");
            quickSort.getStyleClass().add("topmenu");
        });
    }

    void handleInsertionSort(){
        insertionSort.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            choosesort = 2;
            bubbleSort.getStyleClass().clear();
            selectionSort.getStyleClass().clear();
            insertionSort.getStyleClass().clear();
            shellSort.getStyleClass().clear();
            quickSort.getStyleClass().clear();
            bubbleSort.getStyleClass().add("topmenu");
            selectionSort.getStyleClass().add("topmenu");
            insertionSort.getStyleClass().add("topmenuselected");
            shellSort.getStyleClass().add("topmenu");
            quickSort.getStyleClass().add("topmenu");
        });
    }

    void handleShellSort(){
        shellSort.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            choosesort = 3;
            bubbleSort.getStyleClass().clear();
            selectionSort.getStyleClass().clear();
            insertionSort.getStyleClass().clear();
            shellSort.getStyleClass().clear();
            quickSort.getStyleClass().clear();
            bubbleSort.getStyleClass().add("topmenu");
            selectionSort.getStyleClass().add("topmenu");
            insertionSort.getStyleClass().add("topmenu");
            shellSort.getStyleClass().add("topmenuselected");
            quickSort.getStyleClass().add("topmenu");
        });
    }

    void handleQuickSort(){
        quickSort.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            choosesort = 4;
            bubbleSort.getStyleClass().clear();
            selectionSort.getStyleClass().clear();
            insertionSort.getStyleClass().clear();
            shellSort.getStyleClass().clear();
            quickSort.getStyleClass().clear();
            bubbleSort.getStyleClass().add("topmenu");
            selectionSort.getStyleClass().add("topmenu");
            insertionSort.getStyleClass().add("topmenu");
            shellSort.getStyleClass().add("topmenu");
            quickSort.getStyleClass().add("topmenuselected");
        });
    }

    void handleButtonSort(){
        sort.setOnAction(event -> {
            sp.makesound("sound/sort.mp3");
            SequentialTransition sq = new SequentialTransition();
            Bubblesort bs = new Bubblesort();
            Selectionsort ss = new Selectionsort();
            Insertionsort is = new Insertionsort();
            Shellsort shs = new Shellsort();
            Quicksort qs = new Quicksort();
            int[] arr;
            switch (choosesort) {
                case 0:
                    arr = resetArray(list);
                    sq = bs.bubblesort(arr, list,speed);
                    break;
                case 1:
                    arr = resetArray(list);
                    sq = ss.selectionsort(arr, list,speed);
                    break;
                case 2:
                    arr = resetArray(list);
                    sq = is.insertionsort(arr, list,speed);
                    break;
                case 3:
                    arr = resetArray(list);
                    sq = shs.shellsort(arr, list,speed);
                    break;
                case 4:
                    arr = resetArray(list);
                    sq = qs.quicksort(arr, 0, arr.length - 1, list, speed,sq);
                    break;
                default:
                    break;
            }
            sort.setDisable(true);
            choosespeed.setDisable(true);
            sq.play();
            sq.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sort.setDisable(false);
                }
            });
            sort.setDisable(false);
            choosespeed.setDisable(false);
        });
    }

    // re-generate random numbers when clicking the button "reset"
    void handleButtonReset(ActionEvent e){
        sp.makesound("sound/reset.mp3");
        hb.getChildren().clear();
        list.clear();
        list = setList(list);
        hb.getChildren().addAll(list);
    }

    // The most important thing in this method is
    // combining the rectangles with the numbers on them
    // for transition in the future
    ArrayList<StackPane> setList(ArrayList<StackPane> l){
        for (int i = 0; i < 12; i++) {
            int num = random.nextInt(10);
            Rectangle rectangle = new Rectangle(40, (num * 10) + 50);
            rectangle.setFill(Color.FIREBRICK);
            rectangle.setOpacity(0.9);
            Text text = new Text(String.valueOf(num));
            text.getStyleClass().add("textstyle");
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
            stackPane.setId(String.valueOf(num));
            stackPane.getChildren().addAll(rectangle, text);
            l.add(stackPane);
        }
        return l;
    }

    int[] resetArray(List<StackPane> list){
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(list.get(i).getId());
        }
        return arr;
    }

    private Button makeButton(String str){
        Button button = new Button(str);
        return button;
    }

    private Text makeText(String str){
        Text text = new Text(str);
        return text;
    }
}
