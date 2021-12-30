public class NBody{
	
    public static double readRadius(String file){
    In in=new In(file);
    
    int number = in.readInt();
    double Radius = in.readDouble();
    return Radius;
    }

    public static Planet[] readPlanets(String file){
    In in=new In(file);

    int number = in.readInt();
    double Radius=in.readDouble();
    
    Planet[] planets=new Planet[number];
    
    for(int i=0;i<number;i+=1){
    double xP=in.readDouble();
    double yP=in.readDouble();
    double xV=in.readDouble();
    double yV=in.readDouble();
    double m=in.readDouble();
    String img=in.readString();
    planets[i]=new Planet(xP,yP,xV,yV,m,img);
    }
    return planets;
 }

   public static void main(String[] args){  

   double T=Double.parseDouble(args[0]); 
   double dt=Double.parseDouble(args[1]);
   String filename=args[2];
   
   double radius=NBody.readRadius(filename);
   Planet[] planets=NBody.readPlanets(filename);
   
   double size=radius;
   StdDraw.setScale(-size,size);
   StdDraw.clear();
   StdDraw.picture(0,0,"images/starfield.jpg");
   
   /*Draw all of the planets*/
   StdDraw.show();
   
   for(int i=0;i<planets.length;i+=1){
   planets[i].draw();
   }
   
   /* Enable double buffering*/
   StdDraw.enableDoubleBuffering();
   
   /* Create a time variable and set it to 0. 
   Set up a loop to loop until this time variable is T.
   For each time through the loop, do the following:*/   
   for(double time=0;time<=T;time+=dt){  
   /* Create an xForces array and yForces array*/
   double[] xForces=new double[planets.length];
   double[] yForces=new double[planets.length];
   /* Calculate the net x and y forces for each planet, 
   storing these in the xForces and yForces arrays respectively*/
   for(int i=0;i<planets.length;i+=1){
   xForces[i]=planets[i].calcNetForceExertedByX(planets);
   yForces[i]=planets[i].calcNetForceExertedByY(planets);
   }
   /* Call update on each of the planets. 
   This will update each planet’s position, velocity, and acceleration*/
   for(int i=0;i<planets.length;i+=1){
   planets[i].update(dt,xForces[i],yForces[i]);
   }
   /* Draw the background image*/
   StdDraw.clear();
   StdDraw.picture(0,0,"images/starfield.jpg");   
   /* Draw all of the planets*/
   for(int i=0;i<planets.length;i+=1){
   planets[i].draw();
   }  
   /* Show the offscreen buffer */
   StdDraw.show();
   /* Pause the animation for 10 milliseconds */
   StdDraw.pause(10);  
   }

   /* print out the final state of the universe in the same format as the input */
   StdOut.printf("%d\n", planets.length);
   StdOut.printf("%.2e\n", radius);
   for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);}
   }
}