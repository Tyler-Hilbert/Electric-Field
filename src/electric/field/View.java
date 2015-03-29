package electric.field;


import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The view that displays the electric field.
 * 
 * @author Tyler
 */
public class View {
    
    Canvas canvas = new Canvas(ElectricField.WIDTH, ElectricField.HEIGHT);
    
    final static public int drawnChargeSize = 30; // The size the charge will be drawn
    
    public View(Stage primaryStage) {
        Group root = new Group();
        root.getChildren().add(canvas);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("Electric field");
    }
    
    /**
     * Draws the charges on the view
     * 
     * @param locations the locations of the charges
     */
    public void drawCharges(ArrayList<Point2D> locations) {
        reset(); 
        
        GraphicsContext gc = getGC();
        gc.setFill(Color.BLACK);
        
        for (Point2D p : locations) {
            gc.fillOval(p.getX() - drawnChargeSize/2, p.getY() - drawnChargeSize/2, drawnChargeSize, drawnChargeSize);
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
    
    /**
     * Writes the force to upper left corner
     * @param formattedForce the formatted force between 2 charges
     */
    public void drawForce(String formattedForce, String formattedDistance) {
        GraphicsContext gc = getGC();
        
        gc.setFill(Color.BLACK);
        
        
        String forceText = "Force: " + formattedForce + "N";
        gc.fillText(forceText, 20, 20);
        
        String distanceText = "Distance: " + formattedDistance + "m";
        gc.fillText(distanceText, 20, 40);
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    private GraphicsContext getGC() {
        return canvas.getGraphicsContext2D();
    }
}
