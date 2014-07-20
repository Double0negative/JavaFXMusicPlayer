package org.mcsg.music.visualize;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Random;

import javax.swing.JFrame;

public class Visualizer extends JFrame{

	Graphics2D g;
	boolean drawing = true;
	boolean resize;
	Random rand = new Random();

	public Visualizer(){
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);
		this.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
				resize = true;
			}
			public void componentResized(ComponentEvent e) {
				resize = true;
			}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		this.setVisible(true);
		g = (Graphics2D) this.getGraphics();
	}

	int r=rand.nextInt(255);
	int gc=rand.nextInt(255);
	int b=rand.nextInt(255);
	int a=rand.nextInt(255);

	int x1 = rand.nextInt(WIDTH);
	int x2 = rand.nextInt(WIDTH);

	int y1 = rand.nextInt(HEIGHT);
	int y2 = rand.nextInt(HEIGHT);

	public void startDraw(){


		new Thread(){
			public void run(){


				r=rand.nextInt(255);
				gc=rand.nextInt(255);
				b=rand.nextInt(255);
				a=rand.nextInt(255);

				x1 = rand.nextInt(getWidth());
				x2 = rand.nextInt(getWidth());

				y1 = rand.nextInt(getHeight());
				y2 = rand.nextInt(getHeight());

				while(drawing){
					if(resize){
						try{ sleep(100); } catch (Exception e){}

						g.setColor(Color.BLACK);
						g.fillRect(0, 0, getWidth(), getHeight());
						resize = false;
					}
					r += rand.nextInt(7) - 3;
					b += rand.nextInt(7) - 3;
					gc += rand.nextInt(7) - 3;
					a += rand.nextInt(7) - 3;

					r = r < 0 ? 0 : r > 255 ? 255 : r; 
					b = b < 0 ? 0 : b > 255 ? 255 : b; 
					gc = gc < 0 ? 0 : gc > 255 ? 255 : gc; 
					a = a < 0 ? 0 : a > 255 ? 255 : a; 


					Color c = new Color(r, gc,b, a);
					g.setColor(c);

					x1 += rand.nextInt(13) - 6;
					x2 += rand.nextInt(13) - 6;
					y1 += rand.nextInt(13) - 6;
					y2 += rand.nextInt(13) - 6;

					x1 = x1 > getWidth() ? getWidth() : x1 < 0 ? 0 : x1;
					x2 = x2 > getWidth() ? getWidth() : x2 < 0 ? 0 : x2;
					y1 = y1 > getHeight() ? getHeight(): y1 < 0 ? 0 : y1;
					y2 = y2 > getHeight() ? getHeight() : y2 < 0 ? 0 : y2;
					g.drawLine(x1, y1, x2, y2);
					//try{ sleep(1); } catch (Exception e){}
				}
			}
		}.start();
	}

	public void stop(){
		drawing = false;
	}

}
