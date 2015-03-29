package electric.field;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 * The main model for the all the charges.
 * 
 * @author Tyler
 */
public class Charges {
    final static double k = 8.9875517873681764 * Math.pow(10, 9); // Constant k, Coulomb's constant, in electrostatics formulas
    Charge c1, c2; // The 2 charges
    
    public Charges() {
        c1 = new Charge(25 * Math.pow(10, -6), 300, 300);
        c2 = new Charge(25 * Math.pow(10, -6), 400, 400);
    }
        
        
    /**
     * Calculates the electrostatic force between the charges
     * uses formula electrostatic force = k * charge1 * charge2 / distance^2 
     *  or fe = k*q1*q2/r^2
     * 
     * @return the electrostatic force between the charges formatted to 3 decimal places
     */
    public String getForce () {
        double distance = getDistance();
        double force = k * c1.getCharge() * c2.getCharge() / Math.pow(distance, 2);
        
        DecimalFormat decimalFormat = new DecimalFormat(".###");
        return decimalFormat.format(force);
    }
    
    /**
     * @return An arraylist of the locations of the 
     */
    public ArrayList<Point2D> getLocations() {
        ArrayList<Point2D> lst = new ArrayList<Point2D>();
        lst.add(c1.getLocation());
        lst.add(c2.getLocation());
        return lst;
    }
    
    public void updateCharge(Point2D newLocation) {
        c1.setLocation(newLocation);
    }
    
    /**
     * @return The distance between the points in meters
     */
    private double getDistance() {
        return c1.getLocation().distance(c2.getLocation()) / ElectricField.SCALE; 
    }
    
    /**
     * @return The distance between the charges formatted
     */
    public String getDistanceFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat(".###");
        return decimalFormat.format(getDistance());
    }
    
    public boolean clickedOn (double x, double y) {
        return c1.clickedOn(x, y);
    }
}
