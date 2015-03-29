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
    
    private int draggingIndex = -1; // The index of the charge being dragged. -1 means no charge is being dragged.

    @Override
    public void start(Stage primaryStage) {
        // Instantiate components
        View view = new View(primaryStage);
        Controller controller = new Controller(new Charges(), view);
        

        Canvas canvas = view.getCanvas();
        
        // Sets the draggingIndex if charge was pressed
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                draggingIndex = controller.clickedOn(me.getX(), me.getY());
                me.consume();
            }
        });
        
        // Reset draggingIndex to -1 to show nothing is being dragged
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                draggingIndex = -1;
                event.consume();
            }
        }); 

        // Moves the charge that draggingIndex is set on
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {       
                if (draggingIndex != -1) {
                    double x = me.getX();
                    double y = me.getY();
                    
                    // Make sure x and y are within the screen
                    if (x < 0)
                        x = 0;
                    if (x > WIDTH) 
                        x = WIDTH;
                    if (y < 0)
                        y = 0;
                    if (y > HEIGHT)
                        y = HEIGHT;

                    Point2D chargeLocation = new Point2D(x, y);
                    controller.updateCharge(draggingIndex, chargeLocation);
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
