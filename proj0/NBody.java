public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		Body [] planets = new Body[numPlanets];
		for (int i = 0; i < 5; i++) {
			planets[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	}

	public static void main(String args[]) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		
		// draw the background
		StdDraw.enableDoubleBuffering();
		// set the scale of canvas to the size of universe, i.e. the universe goes from lower left (-radius, -radius) to upper right (radius, radius)	
		StdDraw.setScale(-radius, radius);
		/* Clears the drawing window. */
		StdDraw.clear();
		// position the background at the center (0, 0) of the canvas
		StdDraw.picture(0, 0, "images/starfield.jpg");
		/* draw bodies on canvas */
		for (Body body : bodies) {
			body.draw();
		}
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
	}
}
