package TestDesign_creation_factory;

public class UseFactory {
	public static void main(String[] args) {
        // Create ShapeFactory object
        ShapeFactory shapeFactory = new ShapeFactory();
 
        // Get and draw a Circle
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();
 
        // Get and draw a Rectangle
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();
 
        // Get and draw a Square
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
