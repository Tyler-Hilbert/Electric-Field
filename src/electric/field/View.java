package electric.field;


import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The view that displays the electric field.
 * 
 * @author Tyler
 */
public class View {
    
    final private static int WIDTH = 800; // Width of the drawable area
    final private static int HEIGHT = 800; // Height of the drawable area
    
    private Charges charges;
    
    private int draggingIndex = -1; // The index of the charge being dragged. -1 means no charge is being dragged.

    
    private Canvas canvas;
    private GridPane grid = new GridPane();
    
    private Label forceLabel = new Label();
    private Label distanceLabel = new Label();
    private TextField chargeLabel1;
    private TextField chargeLabel2;
    
    final static public int drawnChargeSize = 30; // The size the charge will be drawn
    
    public View(Stage primaryStage, Charges charges) {
        this.charges = charges;
        
        canvas = new Canvas(WIDTH, HEIGHT);
        addCanvasListeners();
        
        // Set up gui
        Group root = new Group();
        root.getChildren().add(canvas);
        
        // Setup grid
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Add update charge listeners
        chargeLabel1 = new TextField();
        chargeLabel1.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.ENTER) {
                updateCharges();
            }
        });
        
        chargeLabel2 = new TextField();
        chargeLabel2.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.ENTER) {
                updateCharges();
            }
        });
       
               
        grid.add(forceLabel, 0, 0);
        grid.add(distanceLabel, 0, 1);
        grid.add(chargeLabel1, 0, 2);
        grid.add(chargeLabel2, 0, 3);
        
        root.getChildren().add(grid);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("Electric field");
        primaryStage.setResizable(false);
        
        canvas.requestFocus();
    }
    
    private void addCanvasListeners() {
        // Sets the draggingIndex if charge was pressed
        canvas.setOnMousePressed((MouseEvent me) -> {
            // Update charges in case a new charge was typed but not entered
            updateCharges();
            
            draggingIndex = charges.clickedOn(me.getX(), me.getY());
            me.consume();
        });
        
        // Reset draggingIndex to -1 to show nothing is being dragged
        canvas.setOnMouseReleased((MouseEvent event) -> {
            draggingIndex = -1;
            event.consume();
        }); 

        // Moves the charge that draggingIndex is set on
        canvas.setOnMouseDragged((MouseEvent me) -> {
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
                charges.setChargeLocation(draggingIndex, chargeLocation);
                
                update();
            }
        });
    }
    
    /**
     * Update charges to what's entered in the TextField.
     */
    private void updateCharges() {
        try {
            double newCharge = Double.parseDouble(chargeLabel1.getText());
            charges.setCharge(0, newCharge);
        } catch (Exception ex) {
            // Revert to the past charge
            chargeLabel1.setText(Double.toString(charges.getCharges().get(0).getCharge()));
            ex.printStackTrace();
        }

        try {
            double newCharge = Double.parseDouble(chargeLabel2.getText());
            charges.setCharge(1, newCharge);
        } catch (Exception ex) {
            // Revert to the past charge
            chargeLabel2.setText(Double.toString(charges.getCharges().get(1).getCharge()));
            ex.printStackTrace();
        } 
        
        update();
    }
    
    /**
     * Updates the gui
     */
    public void update(){
        reset();
        drawCharges();
        
        forceLabel.setText("Force: " + charges.getForce());
        distanceLabel.setText("Distance: " + charges.getDistanceFormatted() + "m");
        chargeLabel1.setText(Double.toString(charges.getCharges().get(0).getCharge()));
        chargeLabel2.setText(Double.toString(charges.getCharges().get(1).getCharge()));
    }
    
    private void drawCharges(){
        GraphicsContext gc = getGC();
        
        ArrayList<Charge> chargesList = charges.getCharges();
        for (Charge c : chargesList) {
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

    
    private GraphicsContext getGC() {
        return canvas.getGraphicsContext2D();
    }
}
