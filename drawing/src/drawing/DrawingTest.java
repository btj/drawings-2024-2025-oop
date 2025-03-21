package drawing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

sealed abstract class Shape permits Circle, Polygon {}

final class Circle extends Shape { // Circle is subclass of Shape, Shape is superclass of Circle
	Point center;
	int radius;
	
	Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
}

final class Polygon extends Shape { // Polygon is a subclass of Shape, Shape is the superclass of Polygon
	Point[] vertices;
	
	Polygon(Point[] vertices) {
		this.vertices = vertices;
	}
}

class Drawing {
	Shape[] shapes;
	
	Drawing(Shape[] shapes) {
		this.shapes = shapes;
	}
	
	String toSVG() {
		String result = "<svg xmlns='http://www.w3.org/2000/svg'"
                + " stroke='black' fill='transparent'>";
		for (Shape shape : shapes) {
			switch (shape) {
			case Circle circle -> {
				//if (shape instanceof Circle circle) { // pattern match
				//Circle circle = (Circle)shape; // Type cast; if it fails at run time, a ClassCastException is thrown
				// If the shape is a Circle
				result += "<circle cx=\"" + circle.center.x + "\" cy=\"" + circle.center.y + "\" r=\"" + circle.radius + "\" />";
			}
			case Polygon polygon -> {
				// If the shape is a Polygon
				result += "<polygon points=\"";
				for (Point p : polygon.vertices) {
					result += " " + p.x + " " + p.y;
				}
				result += "\" />";
			}
			//default -> throw new AssertionError("Should never happen");
			}
		}
		result += "</svg>";
		return result;
	}
}

class DrawingTest {

	@Test
	void test() {
		Drawing myDrawing = new Drawing(new Shape[] {
				new Circle(new Point(0, 0), 5),
				new Polygon(new Point[] {new Point(0, 0), new Point(5, 0), new Point(0, 5)})
		});
		assertEquals("<svg xmlns='http://www.w3.org/2000/svg' stroke='black' fill='transparent'><circle cx=\"0\" cy=\"0\" r=\"5\" /><polygon points=\" 0 0 5 0 0 5\" /></svg>", myDrawing.toSVG());
		Shape myShape = new Circle(new Point(0, 0), 5);
		myShape = new Polygon(new Point[] {new Point(0, 0), new Point(5, 0), new Point(0, 5)});
		
		Object o = Integer.valueOf(10);
		o = Boolean.valueOf(false);
		boolean b = ((Boolean)o).booleanValue();
		
		o = new int[] {10, 20, 30};
		if (o instanceof int[]) {
			
		}
		
		Circle[] myCircles = {new Circle(new Point(0, 0), 5), new Circle(new Point(0, 0), 5)};
		Shape[] myShapes = myCircles;
		Shape firstShape = myShapes[0];
		// myShapes[0] = new Polygon(new Point[] {new Point(0, 0), new Point(5, 0), new Point(0, 5)}); // Throws ArrayStoreException
	}

}
