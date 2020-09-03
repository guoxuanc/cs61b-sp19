public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static int readNumPlanets(String filename) {
		In in = new In(filename);
		int numPlanets = in.readInt();
		return numPlanets;
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		Body [] planets = new Body[numPlanets];
		for (int i = 0; i < numPlanets; i++) {
			planets[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
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
		double time = 0;
		int numPlanets = readNumPlanets(filename);
		while (time <= T) {
			/* Clears the drawing window. */
			StdDraw.clear();
			// position the background at the center (0, 0) of the canvas
			StdDraw.picture(0, 0, "images/starfield.jpg");

			double[] xForces = new double[numPlanets];
			double[] yForces = new double[numPlanets];
			
			for (int i=0; i<numPlanets; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int j=0; j<numPlanets; j++) {
				bodies[j].update(dt, xForces[j], yForces[j]);
			}

			/* draw bodies on canvas */
			for (Body body : bodies) {
				body.draw();
			}
			/* Shows the drawing to the screen, and waits 2000 milliseconds. */
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}
		// print out the Universe when timeout
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
}
