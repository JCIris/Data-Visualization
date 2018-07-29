/*
This is the main menu for you to choose
from visualizing sorting algorithms or Queue or Stack.
 */
package controll;

import datastruct.*;
import javafx.scene.shape.ArcType;
import sound.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;

public class Mainmenu extends Application{
    private Sortingvisualize sv = new Sortingvisualize();
    private Queuevisualize qv = new Queuevisualize();
    private Stackvisualize stv = new Stackvisualize();
    private Stage firststage = new Stage();
    private Stage newStage1 = new Stage();
    private Stage newStage2 = new Stage();
    private Stage newStage3 = new Stage();
    private BorderPane firstborderPane = new BorderPane();
    private HBox starthb = new HBox(100);
    private Rectangle sb1 = new Rectangle(150,80);
    private Rectangle sb2 = new Rectangle(150,80);
    private Rectangle sb3 = new Rectangle(150,80);
    private Text sortVisualize = new Text("Sort\nVisualization");
    private Text queueVisualize = new Text("Queue\nVisualization");
    private Text stackVisualize = new Text("Stack\nVisualization");
    private StackPane sp1 = new StackPane();
    private StackPane sp2 = new StackPane();
    private StackPane sp3 = new StackPane();
    private Label author = new Label("Author");
    private FlowPane bottomPane = new FlowPane();
    private FlowPane topPane = new FlowPane();
    private Label topGuide = new Label("Choose What You Want to Visualize");
    private Soundplayer sp = new Soundplayer();
    private Canvas topCanvas = new Canvas(800,65);
    private GraphicsContext gc = topCanvas.getGraphicsContext2D();

    public void start(Stage primaryStage) throws Exception {
        startScene();
        handlesp();
    }

    void startScene(){
        drawCanvas();
        topGuide.getStyleClass().add("topguide");
        topPane.getChildren().addAll(topGuide,topCanvas);
        topPane.setAlignment(Pos.CENTER);
        firstborderPane.setTop(topPane);
        sb1.getStyleClass().add("startselectionbox");
        sb2.getStyleClass().add("startselectionbox");
        sb3.getStyleClass().add("startselectionbox");
        sortVisualize.getStyleClass().add("textstyle");
        queueVisualize.getStyleClass().add("textstyle");
        stackVisualize.getStyleClass().add("textstyle");
        starthb.setAlignment(Pos.CENTER);
        sp1.getChildren().addAll(sb1,sortVisualize);
        sp2.getChildren().addAll(sb2,queueVisualize);
        sp3.getChildren().addAll(sb3,stackVisualize);
        starthb.getChildren().addAll(sp1,sp2,sp3);
        firstborderPane.setCenter(starthb);
        setAuthor();
        bottomPane.getChildren().addAll(author);
        bottomPane.setAlignment(Pos.BOTTOM_RIGHT);
        firstborderPane.setBottom(bottomPane);
        Scene sc = new Scene(firstborderPane,800,400);
        sc.getStylesheets().add("visualizationstyle.css");
        firststage.setTitle("MainMenu");
        firststage.getIcons().add(new Image("file:pic/smile.png"));
        firststage.setResizable(false);
        firststage.setScene(sc);
        firststage.show();
    }

    // draw some simple pictures
    void drawCanvas(){
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(5);
        gc.strokeOval(40, 5, 50, 50);
        gc.fillOval(50,15,10,10);
        gc.fillOval(70,15,10,10);
        gc.strokeArc(55,25,20,20,225,90, ArcType.OPEN);
        gc.strokeArc(620,10,25,25,80,180,ArcType.OPEN);
        gc.fillOval(635,7,10,10);
        gc.fillArc(600,25,50,50,0,180,ArcType.CHORD);
        gc.fillOval(590,45,70,10);
        gc.strokeLine(600,55,595,60);
        gc.strokeLine(615,55,612,60);
        gc.strokeLine(635,55,638,60);
        gc.strokeLine(650,55,655,60);
    }

    // handle the author label
    // an alert box pops when clicking the label
    void setAuthor(){
        author.getStyleClass().add("alertstyle");
        author.setOnMouseClicked(event -> {
            sp.makesound("sound/alert.mp3");
            author.getStyleClass().add("authorclicked");
            setAlert("AuthorInformation:", "by Hui Yan");
        });
    }

    void setAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        Image smile = new Image("file:pic/information.png",20,20,false,false);
        ImageView iv = new ImageView(smile);
        alert.setGraphic(iv);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:pic/information.png"));
        alert.show();
    }

    // handle the choice boxes
    // new windows open when clicking the boxes
    void handlesp(){
        sp1.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            try {
                sv.start(newStage1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newStage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                sv = new Sortingvisualize();
            }
        });

        sp2.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            try {
                qv.start(newStage2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newStage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                qv = new Queuevisualize();
            }
        });

        sp3.setOnMouseClicked(event -> {
            sp.makesound("sound/click.mp3");
            try {
                stv.start(newStage3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        newStage3.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stv = new Stackvisualize();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
