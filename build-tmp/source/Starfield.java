import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle[] stars = new Particle[50];
public void setup()
{
	background(0);
	size(300,300);
	noStroke();
	stars[0] = new OddballParticle();
	for (int i = 1; i < stars.length; i++)
	{
		stars[i] = new NormalParticle(150, 150);
	}
}

public void draw()
{
	background(0);
	for (int i = 1; i < stars.length; i++)
	{
		stars[i].move();
		stars[i].show();
	}
	stars[0].show();
}

public void mousePressed()
{
	stars[0].move();
	for (int i = 1; i < stars.length; i++) {
		((NormalParticle)(stars[i])).setSpawn(stars[0].getX(), stars[0].getY());
	}
}

class NormalParticle implements Particle
{
	private double m_X, m_Y, m_Speed, m_Angle, m_SpawnX, m_SpawnY;
	int pC;

	NormalParticle(double nsX, double nsY)
	{
		pC = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		m_X = nsX;
		m_Y = nsY;
		m_SpawnX = nsX;
		m_SpawnY = nsY;
		m_Speed = Math.random()*7+3;
		m_Angle = Math.toRadians(Math.random()*360);
	}

	public void move()
	{
		m_X += Math.cos(m_Angle)*m_Speed;
		m_Y += Math.sin(m_Angle)*m_Speed;
		if(m_X > 300 || m_X < 0 || m_Y > 300 || m_Y < 0)
		{
			m_X = m_SpawnX;
			m_Y = m_SpawnY;
			m_Angle = Math.toRadians(Math.random()*360);
		}
	}

	public void show()
	{
		fill(pC);
		ellipse((float)m_X, (float)m_Y, 4, 4);
	}

	public void setSpawn(double nX, double nY)
	{
		m_SpawnX = nX;
		m_SpawnY = nY;
	}

	public double getX(){return m_X;}
	public double getY(){return m_Y;}
}

interface Particle
{
	public void show();
	public void move();
	public double getX();
	public double getY();
}

class OddballParticle implements Particle
{
	private double m_X, m_Y;

	OddballParticle()
	{
		m_X = 150;
		m_Y = 150;
	}

	public void move()
	{
		m_X = mouseX;
		m_Y = mouseY;
	}

	public void show()
	{
		fill(0);
		ellipse((float)m_X, (float)m_Y, 4, 4);
	}

	public double getX(){return m_X;}
	public double getY(){return m_Y;}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
