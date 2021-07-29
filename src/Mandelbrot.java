public class Mandelbrot
{
	
	private double real_low;
	private double real_high;
	private double com_low;
	private double com_high;
	private static double x;
	private static double y;
	private int iter;
	static final int w=800;
	static final int h=800;
	
	
	//Constructor Mandelbrot
	public Mandelbrot(double rl,double rh,double cl,double ch)
	{
		this.real_low=rl;
		this.real_high=rh;
		this.com_low=cl;
		this.com_high=ch;
	}
	
	
		
	//calculating the complex number within the region of interest 
	public void calculateMandelbrot(int i, int j)
	{
		
		x=((i*(real_high-real_low))/800f)-Math.abs(real_low);
		y=((j*(com_high-com_low))/800f)-Math.abs(com_low);
					
				
	}
	
	//Checking the coordinates are within the set of Mandelbrot
	public int mandelBoundary(double x,double y,int iterations)		
	{
		
			double cx = x; //real part of c
			double cy =y;//imaginary part of c
			
			int i=0;
			iter=0;
			
			for(i=0;i<iterations;i++) {
				
				double nx = x*x - y*y + cx; //real part of Zn+1
				double ny= 2*x*y +cy;	//imaginary part of Zn+1
							
				x = nx;
				y = ny;
				
				iter++;
				
				if(x*x + y*y > 2) {//ABS(Zn>2)				 
				return 0;
				}
			}
			
			if(i==iterations)return 1;
			
			return -1;
		
		
	}
	
	//returns x coordinate
	public double getX()
	{
		return x;
	}
	//returns y coordinate
	public double getY()
	{
		return y;
	}
	//returns the number of iterations took
	public int getIter()
	{
		return iter;
	}
}