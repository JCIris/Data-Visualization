/*
This is a class to show how Stack works.
Click "PushIn" and "PopOut" to see elements be
pushed in and popped out of the Stack in a LIFO way.
 */
package datastruct;

import sound.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import java.util.Stack;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Stackvisualize extends Application{
    private Stack<Integer> stack = new Stack<Integer>();

    void push(Stack<Integer> s, Integer i)
    {
        s.push(i);
    }

    void pop(Stack<Integer> s)
    {
        s.pop();
    }

    private BorderPane borderPane = new BorderPane();
    private FlowPane centerPane = new FlowPane();
    private FlowPane bottomPane = new FlowPane();
    private Button pushin = makeButton("PushIn");
    private Button popout = makeButton("PopOut");
    private MenuBar menuBar = new MenuBar();
    private Menu helpMenu = new Menu("Help");
    private MenuItem helpItem = new MenuItem("?Help");
    private MenuItem authorItem = new MenuItem("Author");
    private Soundplayer sp = new Soundplayer();

    @Override
    public void start(Stage primaryStage) throws Exception {
        pushin.setOnMouseClicked(this::handlePushInStack);
        popout.setOnMouseClicked(this::handlePopOutStack);
        setTopView();
        setCenterView();
        setBottomView();

        Scene scene = new Scene(borderPane, 500, 640);
        scene.getStylesheets().add("visualizationstyle.css");
        primaryStage.setTitle("StackVisualization");
        primaryStage.getIcons().add(new Image("file:pic/smile.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void handlePushInStack(MouseEvent event)
    {
        int number;
        if (stack.size() == 10){
            return;
        }
        while (true)
        {
            Random num = new Random();
            int randomnum = num.nextInt(20);
            if (!stack.contains(randomnum))
            {
                number = randomnum;
                this.push(stack,randomnum);
                break;
            }
        }

        if (centerPane.getChildren().size() > 0)
        {
            StackPane stackLast = (StackPane) centerPane.getChildren().get(0);
            ((Rectangle) stackLast.getChildren().get(0)).setFill(Color.FIREBRICK);
            ((Rectangle) stackLast.getChildren().get(0)).setStroke(Color.LIME);
        }

        addNode(number);
        sp.makesound("sound/in.mp3");
    }

    void handlePopOutStack(MouseEvent event)
    {
        if (stack.size() == 0){
            return;
        }
        this.pop(stack);
        removeNode();

        sp.makesound("sound/out.mp3");

        if (centerPane.getChildren().size() > 0)
        {
            StackPane stackLast = (StackPane) centerPane.getChildren().get(0);
            ((Rectangle) stackLast.getChildren().get(0)).setFill(Color.FIREBRICK);
            ((Rectangle) stackLast.getChildren().get(0)).setStroke(Color.LIME);
        }
    }

    void addNode(int number)
    {
        Rectangle rect = new Rectangle();
        rect.setFill(Color.FIREBRICK);
        rect.setStroke(Color.LIME);
        rect.setWidth(50);
        rect.setHeight(50);

        Text text = new Text();
        text.setText(Integer.toString(number));
        text.getStyleClass().add("textstyle");

        StackPane element = new StackPane();
        element.getChildren().addAll(rect, text);
        centerPane.getChildren().add(0,element);

        FadeTransition ft = new FadeTransition(Duration.millis(200),
                centerPane.getChildren().get(0));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

    }

    void removeNode()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(500), centerPane.getChildren().get(0));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> centerPane.getChildren().remove(0));
        ft.play();
    }

    // handle the menubar on the top
    void setTopView(){
        helpItem.setOnAction(actionEvent -> {
            setAlert("About", "Click 'PushIn' and 'PopOut' to learn how stack works", "help");
            sp.makesound("sound/alert.mp3");
        });
        authorItem.setOnAction(actionEvent -> {
            setAlert("AuthorInformation:", "by Hui Yan", "information");
            sp.makesound("sound/alert.mp3");
        });
        helpMenu.getItems().addAll(helpItem,new SeparatorMenuItem(), authorItem);
        menuBar.getMenus().addAll(helpMenu);
        borderPane.setTop(menuBar);
    }

    void setCenterView()
    {
        centerPane.setPrefWrapLength(50);
        centerPane.setAlignment(Pos.BOTTOM_CENTER);
        centerPane.setPadding(new Insets(10, 200, 5, 200));
        borderPane.setCenter(centerPane);
    }

    void setBottomView()
    {
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(25, 5, 25, 5));
        bottomPane.setHgap(50);
        bottomPane.getChildren().addAll(pushin,popout);
        borderPane.setBottom(bottomPane);
    }

    void setAlert(String title, String content, String picture) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Image smile = new Image("file:pic/" + picture + ".png",20,20,false,false);
        ImageView iv = new ImageView(smile);
        alert.setGraphic(iv);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:pic/" + picture + ".png"));
        alert.show();
    }

    private Button makeButton(String str){
        Button button = new Button(str);
        return button;
    }

    private void claim(boolean b){
        if(!b) throw new Error("Stack Test"+testNumber+"fails");
        testNumber++;
    }
    private int testNumber = 1;

    // testing 1-9
    private void run(){
        Stackvisualize testsv = new Stackvisualize();
        Stack<Integer> teststack = new Stack<Integer>();
        testsv.push(teststack,5);
        claim(teststack.get(0) == 5);
        testsv.push(teststack,10);
        claim(teststack.get(0) == 5);
        claim(teststack.get(1) == 10);
        testsv.push(teststack,17);
        claim(teststack.get(0) == 5);
        claim(teststack.get(1) == 10);
        claim(teststack.get(2) == 17);
        testsv.pop(teststack);
        claim(teststack.get(0) == 5);
        claim(teststack.get(1) == 10);
        claim(teststack.size() == 2);
        System.out.println("\nAll Stack Tests Passed!");
    }

    public static void main(String[] args){
        Stackvisualize msv = new Stackvisualize();
        msv.run();
    }
}
