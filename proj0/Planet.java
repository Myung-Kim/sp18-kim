public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    // It is good practice to declare any constants as a ‘static final’ variable in your class, and to use that variable anytime you wish to use the constant.
    public static final double constG = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        // x and y distances
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        // returns the result
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        // get the distance first
        double distance = calcDistance(p);
        return (constG * p.mass * this.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);

        return (force * (p.xxPos - this.xxPos)) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);

        return (force * (p.yyPos - this.yyPos)) / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets) {

        double netForce = 0.0d;
        for (Planet planet : planets) {
            // exclude this planet itself
            if (!this.equals(planet)){
                netForce += calcForceExertedByX(planet);
            }
        }

        return netForce;

    }

    public double calcNetForceExertedByY(Planet[] planets) {

        double netForce = 0.0d;
        for (Planet planet : planets) {
            // exclude this planet itself
            if (!this.equals(planet)){
                netForce += calcForceExertedByY(planet);
            }
        }

        return netForce;

    }

    public void update(double dt, double fX, double fY) {
        // calculate net acceleration force
        double aNetX = fX/this.mass;
        double aNetY = fY/this.mass;
        // update velocity
        this.xxVel = xxVel + dt * aNetX;
        this.yyVel = yyVel + dt * aNetY;
        // update position
        this.xxPos = xxPos + dt * xxVel;
        this.yyPos = yyPos + dt * yyVel;

    }


    public void draw(){
        //start to draw universe

        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
//        StdDraw.show();

    }


}
