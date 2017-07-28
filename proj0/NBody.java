public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int planetNum = Integer.parseInt(in.readLine());
        return Double.parseDouble(in.readLine());
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNum = Integer.parseInt(in.readLine());
        Planet[] planets = new Planet[planetNum];
        double radius = Double.parseDouble(in.readLine());
        int i = 0;
        while(in.hasNextLine()) {
            if (i == planetNum){
                break;
            }
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            i += 1;
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }

        int time = 0;
        while(time < T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show(10);
            time += dt;
        }
    }
}
