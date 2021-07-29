public class Julia
{
	
	private double xin;
	private double yin;
	private double x;
	private double y;
	private double zx;
	private double zy;
	private int iter;
	private int width=800;
	private int height=800;
	
	
	//Constructor Julia
	public Julia(double x,double y)
	{
		xin=x;
		yin=y;
	}
	
	//calculating the complex number within the region of interest 
	public void calculateJulia(int i,int j)
	{
			
		zx=((i*2f)/800)-1;
		zy=((j*2f)/800)-1;
			
	}
	
	//Checking the coordinates are within the region of interest in set of Julia
	public int juliaBoundary(double x,double y,int iterations)		
	{
		
		x=xin;
		y=yin;
		iter=0;
		
		for(int i=iterations;i>=0;i--)
		{
			double nx=(zx*zx)-(zy*zy)+x; 	//real part of Zn+1
			double ny=(2*zx*zy)+y;			//imaginary part of Zn+1
			zx=nx;
			zy=ny;
			iter++;
			
			if((zx*zx)+(zy*zy)> 4) {
				return 0; //ABS(Zn>2)
			}
		
		}           
		return -1 ;
	}
	
	
	//returns x coordinates
	public double getX()
	{
		return x;
	}
	
	//returns y coordinates
	public double getY()
	{
		return y;
	}
	
	//returns the number of iterations took on the boundCheck
	public int getIter()
	{
		return iter;
	}
}