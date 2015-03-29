package electric.field;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Main class of the program; also interacts with the view.
 * Contains the action listeners
 * Instantiates the controller
 * @author Tyler
 */
public class ElectricField extends Application {
    final static int WIDTH = 800; // Width of the drawable area
    final static int HEIGHT = 800; // Height of the drawable area
    final static int SCALE = 100; // The scale of which the px is drawn to m
    
    
    private boolean dragging = false; 

    @Override
    public void start(Stage primaryStage) {
        // Instantiate components
        View view = new View(primaryStage);
        Controller controller = new Controller(new Charges(), view);
        
        // Add action listener
        Canvas canvas = view.getCanvas();
        
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (controller.clickedOn(me.getX(), me.getY())) {
                    dragging = true;
                }
                me.consume();
            }
        });
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                dragging = false;
                event.consume();
            }
        }); 

        
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {       
                if (dragging) {
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    Point2D chargeLocation = new Point2D(me.getX(), me.getY());
                    controller.updateCharge(chargeLocation);
                }
            }
        });
        
        controller.drawCharges();
        controller.drawForce();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
