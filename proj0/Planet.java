public class Planet{
   public double xxPos;
   public double yyPos;
   public double xxVel;
   public double yyVel;
   public double mass;
   public String imgFileName;
   private static final double G=6.67e-11;
  
  public Planet (double xP,double yP,double xV,
  	            double yV,double m,String img){
    this.xxPos=xP;
    this.yyPos=yP;
    this.xxVel=xV;
    this.yyVel=yV;
    this.mass=m;
    this.imgFileName=img;}

  public Planet (Planet p){
    this.xxPos=p.xxPos;
    this.yyPos=p.yyPos;
    this.xxVel=p.xxVel;
    this.yyVel=p.yyVel;
    this.mass=p.mass;
    this.imgFileName=p.imgFileName;
  }

  public double calcDistance(Planet p){
  double r;
  r=Math.sqrt(
    (this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)
      +(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos)
      );
  return r;
  }

  public double calcForceExertedBy(Planet m){
  double f=(G*this.mass*m.mass)/(this.calcDistance(m)*this.calcDistance(m));
  return f;
  }
  
  public double calcForceExertedByX(Planet m){
  double fx=this.calcForceExertedBy(m)*(m.xxPos-this.xxPos)/this.calcDistance(m);
  return fx;}
 
  public double calcForceExertedByY(Planet m){
  double fy=this.calcForceExertedBy(m)*(m.yyPos-this.yyPos)/this.calcDistance(m);
  return fy;}

  public double calcNetForceExertedByX(Planet[] allPlanets){
  double netFx=0;
  for(int i=0;i<allPlanets.length;i+=1){
  if(allPlanets[i].equals(this)){
      continue;}
  else{
       netFx+=this.calcForceExertedByX(allPlanets[i]);
      }
  }
  return netFx;}

  public double calcNetForceExertedByY(Planet[] allPlanets){
  double netFy=0;
  for(int i=0;i<allPlanets.length;i+=1){
    if(allPlanets[i].equals(this)){
        continue;}
    else{
        netFy+=this.calcForceExertedByY(allPlanets[i]);
      }
  }
  return netFy;}

  
  public void update(double dt,double fX,double fY){
  double aX=fX/this.mass;
  double aY=fY/this.mass;
  this.xxVel=this.xxVel+dt*aX;
  this.yyVel=this.yyVel+dt*aY;
  this.xxPos=this.xxPos+dt*this.xxVel;
  this.yyPos=this.yyPos+dt*this.yyVel;
  }

  public void draw(){
  
  StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);

  StdDraw.show();
  }

}