package electric.field;

import javafx.geometry.Point2D;

/**
 * Is a point of charge in the electric field
 * 
 * @author Tyler
 */
public class Charge {
    
    private double charge; // Magnitude of charge
    private Point2D location = new Point2D(400, 400); // Location of the charge
    
    
    /**
     * @param c the charge of the point
     * @param l the location of the charge point
     */
    public Charge (double c, Point2D l) {
        charge = c;
        location = l;
    }
    
    /**
     * @param c the charge of the point
     * @param x the x location of the charge
     * @param y the y location of the charge
     */
    public Charge (double c, double x, double y) {
        charge = c;
        location = new Point2D(x, y);
    }
 
    
    /**
     * @return the location of the charge point
     */
    public Point2D getLocation() {
        return location;
    }
    
    /**
     * @return the charges magnitude
     */
    public double getCharge() {
        return charge;
    }
    
    
    public void setLocation(Point2D location) {
        this.location = location;
    }
    
    /**
     * Checks if the charge was clicked on by checking if x is in between the left and right side
     * and if y is between the top and bottom of the location.
     * @return if the charge was clicked on
     */
    public boolean clickedOn(double x, double y) {
        return  
            location.getX() - View.drawnChargeSize <= x && location.getX() + View.drawnChargeSize >= x
            && location.getY() - View.drawnChargeSize <= y && location.getY() + View.drawnChargeSize >= y;
    }
}
