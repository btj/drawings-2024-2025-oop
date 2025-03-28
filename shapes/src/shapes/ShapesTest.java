package shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

sealed abstract class Shape permits Circle, Rectangle {
	static double totalCircumference(Shape[] shapes) {
		double result = 0;
		for (Shape shape : shapes) {
			switch (shape) {
			case Circle c -> {
				result += 2 * Math.PI * c.radius;
			}
			case Rectangle r -> {
				result += 2 * (r.width + r.height);
			}
			}
		}
		return result;
	}
	static double totalArea(Shape[] shapes) {
		double result = 0;
		for (Shape shape : shapes) {
			switch (shape) {
			case Circle c -> {
				result += Math.PI * c.radius * c.radius;
			}
			case Rectangle r -> {
				result += r.width * r.height;
			}
			}
		}
		return result;
	}
}

final class Circle extends Shape {
	double radius;
	Circle(double radius) { this.radius = radius; }
}

final class Rectangle extends Shape {
	double width;
	double height;
	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
}

class ShapesTest {

	@Test
	void test() {
		Circle myCircle = new Circle(1);
		Rectangle myRectangle = new Rectangle(2, 3);
		Shape[] myShapes = {
				myCircle,
				myRectangle
		};
		assertEquals(2*Math.PI + 10,
				Shape.totalCircumference(myShapes));
		assertEquals(Math.PI + 6,
				Shape.totalArea(myShapes));
	}

}
