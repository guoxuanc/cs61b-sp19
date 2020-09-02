import java.lang.Math;

public class Body {
	/*
	 * The reason we call them by double letters, e.g. xxPos rather than xPos is to reduce the chance of typos.
	 */
	// Its current x position
	public double xxPos;
	// Its current y position
	public double yyPos;
	// Its current velocity in the x direction
	public double xxVel;
	// Its current velocity in the y direction
	public double yyVel;
	// Its mass
	public double mass;
	// The name of the file that corresponds to the image that depicts the body
	public String imgFileName;
	// Gravitational constant
	final static double G = 6.67e-11;

	// Constructor I
	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	// Constructor II
	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	// Helper Methods
	
	// calculates the distance between two Bodys
	/*
	 * @param: object a single Body object
	 *
	 * return: a double equal to the distance 
	 * between the supplied body and 
	 * the body that is doing the calculation
	 */
	public double calcDistance(Body object) {
		return Math.sqrt(Math.pow(this.xxPos - object.xxPos, 2) + Math.pow(this.yyPos - object.yyPos, 2));
	}

	// calculates the force exerted on this body by the given body
	public double calcForceExertedBy(Body object) {
	return G * this.mass * object.mass / Math.pow(this.calcDistance(object), 2);
	}

	// the force exerted in the X direction
	public double calcForceExertedByX(Body object) {
	double totalForce = this.calcForceExertedBy(object);
	double r = this.calcDistance(object);
	return totalForce * (object.xxPos - this.xxPos) / r;
	}

	// the force exerted in the Y direction
	public double calcForceExertedByY(Body object) {
	double totalForce = this.calcForceExertedBy(object);
	double r = this.calcDistance(object);
	return totalForce * (object.yyPos - this.yyPos) / r;
	}


	// the net X force exerted by all bodies in that array upon the current Body
	public double calcNetForceExertedByX(Body[] allBodys) {
		double totalNetForceOnX = 0.0;
		for (Body body : allBodys) {
			if (this.equals(body))
				continue;
			totalNetForceOnX += this.calcForceExertedByX(body);
		}
		return totalNetForceOnX;
	}

	// the net Y force exerted by all bodies in that array upon the current Body
	public double calcNetForceExertedByY(Body[] allBodys) {
		double totalNetForceOnY = 0.0;
		for (Body body : allBodys) {
			if (this.equals(body))
				continue;
			totalNetForceOnY += this.calcForceExertedByY(body);
		}
		return totalNetForceOnY;
	}


	// update the body’s position and velocity instance variables
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		// update the new velocity
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		// update the new Position
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	// draw itself on the canvas
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, imgFileName);
	}
}
