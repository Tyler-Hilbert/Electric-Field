package electric.field;


import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The view that displays the electric field.
 * 
 * @author Tyler
 */
public class View {
    
    Canvas canvas = new Canvas(ElectricField.WIDTH, ElectricField.HEIGHT);
    GridPane grid = new GridPane();
    
    Label forceLabel = new Label();
    Label distanceLabel = new Label();
    
    final static public int drawnChargeSize = 30; // The size the charge will be drawn
    
    public View(Stage primaryStage) {
        Group root = new Group();
        root.getChildren().add(canvas);
        
        // Setup grid
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
               
        grid.add(forceLabel, 0, 0);
        grid.add(distanceLabel, 0, 1);
        
        root.getChildren().add(grid);
        
       
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("Electric field");
        primaryStage.setResizable(false);
    }
    

    public void drawCharges(ArrayList<Charge> charges){
        reset(); 
        
        GraphicsContext gc = getGC();
        
        for (Charge c : charges) {
            if (c.isPositive())
                gc.setFill(Color.BLACK);
            else
                gc.setFill(Color.RED);
            gc.fillOval(c.getLocation().getX() - drawnChargeSize/2, c.getLocation().getY() - drawnChargeSize/2, drawnChargeSize, drawnChargeSize);
        }
    }
    
    /**
     * Resets the graphics context
     */
    private void reset() {
        GraphicsContext gc = getGC();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawForce(String formattedForce, String formattedDistance) {
        forceLabel.setText("Force: " + formattedForce);
        distanceLabel.setText("Distance: " + formattedDistance + "m");
    }
    
    private ScrollBar setupScrollBar () {
        ScrollBar sc = new ScrollBar();
        
        sc.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            System.out.println(new_val);
        });  
        
        return sc;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    private GraphicsContext getGC() {
        return canvas.getGraphicsContext2D();
    }
}
