public class NBody {

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        //start to draw universe
        StdDraw.setScale(-radius, radius);
        //enabled for smooth animation
        StdDraw.enableDoubleBuffering();

        double time = 0.0d;

        while (time < T) {

            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the background image
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();

            StdDraw.pause(10);

            time += dt;

        }


        //print out text information after finishing
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }

    public static double readRadius(String fileName) {

        In in = new In(fileName);
        // consume first value and ignore
        in.readDouble();
        return in.readDouble();

    }

    public static Planet[] readPlanets(String fileName) {

        In in = new In(fileName);
        // get number of planets
        int len = in.readInt();
        // read radius and discard
        in.readDouble();

        Planet[] planets = new Planet[len];

        for (int i = 0; i < len; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }

        // why does not this work??
//        for (Planet planet : planets) {
//            planet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
//                    in.readDouble(), in.readDouble(), in.readString());
//
//        }

        return planets;

    }

}
