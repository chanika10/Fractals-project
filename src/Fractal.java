import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.image.BufferedImage;

//inherits JPanel 

public class Fractal extends JPanel
{ 	
	
	private int fractal=0;
	private double real_low;
	private double real_high;
	private double com_low;
	private double com_high;
	private int iterations;
	private int bound;
	private float c;
	private int x;
	private int y;
	static final int w=800;
	static final int h=800;
	
	
	Mandelbrot mandelbrot=null;
	Julia julia=null;
	
	//Mandelbrot constructor 
	public Fractal(double rl,double rh,double cl,double ch,int iter)
	{
		fractal=1;
		this.real_low = rl;
		this.real_high = rh;
		this.com_low = cl;
		this.com_high = ch;
		this.iterations = iter;
		
		
		mandelbrot = new Mandelbrot(real_low, real_high, com_low, com_high);
		
		// setting the size of the panel
		setPreferredSize(new Dimension(800,800));
	}
	
	//Julia constructor 
	public Fractal(double x,double y,int iterations)
	{
		fractal=2;
		this.real_low = x;
		this.com_low = y;
		this.iterations = iterations;
		
		julia = new Julia(x,y);
		
		// setting the size of the panel
		setPreferredSize(new Dimension(800,800));
	}
	
	
	 
	//call paintComponent from parent class 
	public void paint(Graphics g) 
	{ 
		
		Color color1=null;
		Color color2=null;
		
		float c2=0;
		float c3=0;
		int i=0;
		
			while (i<=w)
		{	
			for(int j=0;j<=h;j++)
			{	
				//if it is Mandelbrot set
				if(fractal==1)
				{	
					//taking the complex values
					mandelbrot.calculateMandelbrot(i,j); 	
					
					//checking the boundary
					this.bound = mandelbrot.mandelBoundary(mandelbrot.getX(),mandelbrot.getY(),iterations);
					
					//calculating the color
					this.c=((float)mandelbrot.getX()*20.0f/(float)x);
					
					c2=((float)mandelbrot.getY()*20.0f/(float)y);
					color1= Color.getHSBColor(c,1.0f,1.0f);
					color2= Color.getHSBColor(c2,1.0f,1.0f);
				}
				//if it is Julia set
				else if(fractal==2)
				{	
					//taking the complex values
					julia.calculateJulia(i,j);	
					
					//checking the boundary
					this.bound = julia.juliaBoundary(julia.getX(),julia.getY(),iterations);
					
					//calculating the color
					this.c = ((float)julia.getIter()*10.0f/(float)iterations);
					color1 = Color.getHSBColor(c,1.0f,1.0f);
				}	
				//assigning colors according to the bound
				if(bound==-1)
				{	 		
					print((Graphics2D)g,color2,i,j); 
				}	 
				else if(bound==0)
				{
					print((Graphics2D)g,color1,i,j);
				}
				else if(bound==1)
				{
					print((Graphics2D)g,color1.BLACK,i,j);
				}
			}
			i++	;			
		}	
			
	}	

	//print the points on the canvas with the given color
	private static void print(Graphics2D fr, Color color,double x,double y) 
	{
	    fr.setColor(color); 
	    fr.draw(new Line2D.Double(x,y,x,y)); 
	}	
	
	//printing the instructions for the order of inputs
	public static void printingOrderMandelbrot()
	{
		System.out.println("Use the below order for Mandelbrot set:");
    	System.out.println("java Fractal Mandelbrot reallowlimit realhighlimit comlowlimit comhighlimit iterations");
		System.out.println("	OR");
		System.out.println("java Fractal Mandelbrot reallowlimit realhighlimit comlowlimit comhighlimit");
    	System.exit(0);
	}
	
	public static void printingOrderJulia()
	{
		System.out.println("Use the below order for Julia set:");
    	System.out.println("java Fractal Julia x y iterations");
		System.out.println("	OR");
		System.out.println("java Fractal Julia x y");
    	System.exit(0);
	}
	
	public static void main(String[]args)
	{	
		
			JFrame f= new JFrame("Canvas");  
			f.setSize(w,h);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null); 
			f.setVisible(true); 
			
			double real_low=0;
	    	double real_high=0;
	    	double com_low=0;
	   		double com_high=0;
	   		int iterations=0;
				
		//checking the arguments	
	    if(args.length>=1)
	    {	
			//checking whether the Mandelbrot set or julia set
	    	if(args[0].equals("Mandelbrot"))
	    	{
	    					
	    		if(args.length==1) //default value for Mandelbrot set
	    		{
					//if zero arguments are given
	    			real_low=-1;
	    			real_high=1;
	    			com_low=-1;
	    			com_high=1;
	    			iterations=1000;
	    		}
				else if(args.length==5){	//if four arguments are given
					real_low= Double.parseDouble(args[1]);
	    			real_high = Double.parseDouble(args[2]);
	    			com_low = Double.parseDouble(args[3]);
	    			com_high= Double.parseDouble(args[4]);
	    			iterations = 1000;
				}
	    		else if(args.length==6)		//if five arguments are given
	    		{
	    			real_low= Double.parseDouble(args[1]);
	    			real_high = Double.parseDouble(args[2]);
	    			com_low = Double.parseDouble(args[3]);
	    			com_high= Double.parseDouble(args[4]);
	    			iterations = Integer.parseInt(args[5]);
	    		}
	    		else
	    		{
	    			System.out.println("\nError in no.of inputs");
	    			printingOrderMandelbrot();
	    		}
	    		// set the content of the frame
	    		f.setContentPane(new Fractal(real_low,real_high,com_low,com_high,iterations));
	    	}	
	    	else if(args[0].equals("Julia"))
	    	{
	    		
	    		
				
	    		if(args.length==1) //default value for julia set
	    		{
					//if zero arguments are given
	    			real_low=-0.4;
	    			com_low=0.6;
	    			iterations=1000;
	    		}
				else if(args.length==3)//if two arguments are given
	    		{	
	    			real_low = Double.parseDouble(args[1]);
	    			com_low = Double.parseDouble(args[2]);
	    			iterations = 1000;
	    		}
				
	    		else if(args.length==4)//if three arguments are given
	    		{	
	    			real_low = Double.parseDouble(args[1]);
	    			com_low = Double.parseDouble(args[2]);
	    			iterations = Integer.parseInt(args[3]);
	    		}
	    		else
	    		{
	    			System.out.println("\nError in no.of arguments");
	    			printingOrderJulia();
	    		}
	    		
	    		// set the content of the frame
	    		f.setContentPane(new Fractal(real_low,com_low,iterations));
	    	}
	    	else
	    	{
	    		System.out.println("\nError in the Arguments for the Fractal Type");
		    	printingOrderMandelbrot();
				printingOrderJulia();
				
	    	}
			
	    	
			f.pack(); 
			
	    }	
	    else //if the no.of arguments are wrong
	    {
	    	System.out.println("\nNot enough arguments");
	    	printingOrderMandelbrot();
			printingOrderJulia();
	    }
	}
}
