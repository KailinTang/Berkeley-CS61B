public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        final double G = 6.67E-11;
        double distance = this.calcDistance(p);
        return G * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double distance = this.calcDistance(p);
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = this.calcDistance(p);
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double xNetForce = 0;
        for (Planet p : planets) {
            if (this.equals(p) == true) {
                continue;
            }
            xNetForce += this.calcForceExertedByX(p);
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double yNetForce = 0;
        for (Planet p : planets) {
            if (this.equals(p) == true) {
                continue;
            }
            yNetForce += this.calcForceExertedByY(p);
        }
        return yNetForce;
    }

    public void update(double dt, double fX, double fY) {
        double accelerationX = fX / this.mass;
        double accelerationY = fY / this.mass;
        this.xxVel = this.xxVel + accelerationX * dt;
        this.yyVel = this.yyVel + accelerationY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        String fileName = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, fileName);
    }

}