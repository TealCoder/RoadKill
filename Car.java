import java.awt.*;
import java.net.*;
import java.applet.*;
import java.io.*;

class Car extends Applet implements Runnable {

	static Image car;
	static Image gascan;
	static Image dd;
	static Image dp;
	static Image d;
	static Image p;
	static Image u;
	static Image ucar;
	static Image backdrop;
	static Image bc;
	static Image ow;
	static Image f;
	static Image doo;
	static Image df;
	static Image flame;
	static Image ddoo;
	static Image cardie;
	static Image cardead;
	static Image tg;
	static Image b;
	static Image oc;
	static Image otgc;
	static Image offscreen;

	Graphics g;

	int laser;
	int enem1;
	int enem2;
	int enem3;
	int enem4;
	int ocx;
	int ocy;
	int otgx;
	int otgy;
	int by;
	int bx;
	int bonus;
	int tgx;
	int tgy;
	int speed;
	int area = 0;
	int flight;
	int xpos;
	int ypos;
	int score;
	int condition;
	int gas;
	int misc;
	int killx;
	int killy;
	int bombx;
	int bomby;
	int stuffx;
	int stuffy;
	int missilx;
	int missily;
	int bombh;
	int killh;
	int boomx=0;
	int boomy=0;
	int kaboomx=300;
	int blamx=300;
	int blamy=300;
	int xmove;
	int ymove;
	int whyGodWhy=300;
	int bcx=300;
	int bcy=300;
	int owx=0;
	int owy=0;
	int oowy=0;
	int oowx=0;
	int dx=0;
	int dy=0;
	int fx;
	int fy=300;
	int dfx=300;
	int dfy;
	int ddx;
	int gasx;
	int gasy;
	int ddy=300;
	

	public void init() {
		resize(300,300);
		car = getImage(getCodeBase(),"images/car.gif");
		dd = getImage(getCodeBase(),"images/deaddeer.gif");
		d = getImage(getCodeBase(),"images/deer.gif");
		dp = getImage(getCodeBase(),"images/deadperson.gif");
		p = getImage(getCodeBase(),"images/person.gif");
		u = getImage(getCodeBase(),"images/upgrade.gif");
		bc = getImage(getCodeBase(),"images/oncomingcar.gif");
		ow = getImage(getCodeBase(),"images/ow.gif");
		f = getImage(getCodeBase(),"images/frog.gif");
		df = getImage(getCodeBase(),"images/deadfrog.gif");
		doo = getImage(getCodeBase(),"images/dog.gif");
		ddoo = getImage(getCodeBase(),"images/deaddog.gif");
		gascan = getImage(getCodeBase(),"images/gascan.gif");
		flame = getImage(getCodeBase(),"images/flamingcar.gif");
		cardie = getImage(getCodeBase(),"images/carboom.gif");
		cardead = getImage(getCodeBase(),"images/carboomed.gif");
		tg = getImage(getCodeBase(),"images/tailingcar.gif");
		b = getImage(getCodeBase(),"images/bonus.gif");
		repaint();
	}

	public void Start() {
		area = 1;
		flight = 100;
		xpos = 150;
		ypos = 150;
		score = 0;
		killy = 0;
		killx = 150;
		missilx = 400;
		missily = 0;
		bombx = 0;
		bomby = 350;
		stuffx = 0;
		stuffy = 300;
		bcy=600;
		bcx=100;
		condition=100;
		gas=1000;
		speed=5;
		score=0;
		gasx=1;
		gasy=500;
		tgy=-30;
		by=300;
		bonus=0;
		ocy=300;
		otgy=-50;
		enem1=10;
		enem2=10;
		enem3=10;
		enem4=10;
		repaint();
		run();
	}

	public void run() {
			if (gas <= 0 && condition> 0) System.err.println("You ran out of gas!");
			if (condition <= 0 && gas <= 0) System.err.println("You are not invincible!");
			gas--;
			flight++;
			if (flight >= 499) {bx=25 + (int)Math.floor(Math.random() * 250); by=-100; flight = 0; if (condition<=0 && gas<=0) area=0;}
			if (flight >= 150) {bonus=0; if (gas>0 && speed>5+score/500) speed--;}
			stuffy += speed;
			fy+=speed;
			gasy+=speed;
			dfy+=speed;
			if (otgx>0) {otgy-=3.5+Math.abs(speed-5);} else {otgy+=speed;}
			if (tgx>0) {tgy-=3.5+Math.abs(speed-5);} else {tgy+=speed;}
			if (ocx>0) {ocy+=speed+7;} else {ocy+=speed;}
			by+=speed;
			dy+=speed;
			ddy+=speed;
			bomby += speed;
			killy += speed-2;
			boomy += speed;
			whyGodWhy += speed;
			blamy += speed;
			if (gas>0) {
				xpos += xmove;
				ypos += ymove;
			}
			if (xpos <= 5) xpos = 10;
			if (xpos >= 275) xpos = 270;
			if (ypos <= 5) ypos = 10;
			if (ypos >= 275) ypos = 275;
			if (bcx>0) {bcy+=8+speed;} else {bcy+=speed;}
			if (gas>2500) gas=2500;
			if (speed<(5+Math.floor(score/500))) {
				speed++;}
			if (gas<=0) { gas=0; speed=0; }
			owy=0;
			if (condition<=0){ if (gas>10) gas=10; condition=0;}
			if (stuffx > xpos - 25 && stuffx < xpos + 25 && stuffy < ypos + 40 && stuffy > ypos - 30) {
				condition = 100;
				gas+=100;
				score+=10;
				stuffy+=300;
			}
			if (bx > xpos - 25 && bx < xpos + 25 && by < ypos + 40 && by > ypos - 30) {
				if (Math.random()<.3) {
					bonus=15; } else	{
					if (Math.random()<.3) {
						speed = speed*3; } else {
					laser=1;}
				}
				by=300;
			}
			if (gasx > xpos - 25 && gasx < xpos + 25 && gasy < ypos + 40 && gasy > ypos - 30) {
				gas += 500;
				score+=10;
				gasy+=300;
			}

			if ((killx > xpos - 25 && killx < xpos + 25 && killy < ypos + 40 && killy > ypos -10)) {
				score += 100;
				whyGodWhy = killy;
				kaboomx = killx;
				killy = 1300;
				condition-=2;
			}

			if ((bombx > xpos - 35 && bombx < xpos + 35 && bomby < ypos + 35 && bomby > ypos - 10)) {
				score += 50;
				boomy = bomby;
				boomx = bombx;
				bomby = 1300;
				condition-=3;
			}
			if (killx > bcx - 25 && killx < bcx + 25 && killy < bcy + 40 && killy > bcy -10) {
				whyGodWhy = killy;
				kaboomx = killx;
				killy = 1300;
			}
			if (dx > bcx - 25 && dx < bcx + 25 && dy < bcy + 40 && dy > bcy -10) {
				ddy = dy;
				ddx = dx;
				dy = 1300;
			}
			if (bombx > bcx - 35 && bombx < bcx + 35 && bomby < bcy + 35 && bomby > bcy - 10) {
				boomy = bomby;
				boomx = bombx;
				bomby = 1300;
			}
			if (killx > tgx - 25 && killx < tgx + 25 && killy < tgy + 40 && killy > tgy -10) {
				whyGodWhy = killy;
				kaboomx = killx;
				killy = 1300;
			}
			if (dx > tgx - 25 && dx < tgx + 25 && dy < tgy + 40 && dy > tgy -10) {
				ddy = dy;
				ddx = dx;
				dy = 1300;
			}
			if (bombx > tgx - 35 && bombx < tgx + 35 && bomby < tgy + 35 && bomby > tgy - 10) {
				boomy = bomby;
				boomx = bombx;
				bomby = 1300;
			}
			if (tgx > xpos - 37 && tgx < xpos + 37 && tgy < ypos + 37 && tgy > ypos - 37) {
				int test = (Math.abs(tgx-xpos)+Math.abs(tgy-ypos));
				if (test!=0) condition-=50/test+2;
				owy = (tgy + ypos) / 2;
				owx = (tgx + xpos) / 2;
				tgy +=2;
				if (xpos>tgx) {xpos+=1; tgx-=1;}
				if (xpos<tgx) {xpos-=1; tgx+=1;}
				enem3--;
				if (enem3<=0) {tgx-=1000; score+=250;}
				ypos-=2;
			}

			if (ocx > xpos - 37 && ocx < xpos + 37 && ocy < ypos + 37 && ocy > ypos - 37) {
				int test = (Math.abs(ocx-xpos)+Math.abs(ocy-ypos));
				if (test!=0) condition-=50/test+5;
				owy = (ocy + ypos) / 2;
				owx = (ocx + xpos) / 2;
				ocy -=8;
				if (xpos>ocx) {xpos+=2; ocx-=1;}
				if (xpos<ocx) {xpos-=2; ocx+=1;}
				enem2--;
				ypos+=3;
				speed-=2;
				if (enem2<=0) {ocx-=1000; score+=250;}
			}
			if (otgx > xpos - 37 && otgx < xpos + 37 && otgy < ypos + 37 && otgy > ypos - 37) {
				int test = (Math.abs(otgx-xpos)+Math.abs(otgy-ypos));
				if (test!=0) condition-=50/test+2;
				owy = (otgy + ypos) / 2;
				owx = (otgx + xpos) / 2;
				otgy +=2;
				enem4--;
				if (xpos>otgx) {xpos+=1; otgx-=1;}
				if (xpos<otgx) {xpos-=1; otgx+=1;}
				ypos-=3;
				if (enem4<=0) {otgx-=1000; score+=250;}
			}

			if (bcx > xpos - 37 && bcx < xpos + 37 && bcy < ypos + 37 && bcy > ypos - 37) {
				int test = (Math.abs(bcx-xpos)+Math.abs(bcy-ypos));
				if (test!=0) condition-=50/test+5;
				owy = (bcy + ypos) / 2;
				owx = (bcx + xpos) / 2;
				bcy -=8;
				enem1--;
				if (xpos>bcx) {xpos+=2; bcx-=1;}
				if (xpos<bcx) {xpos-=2; bcx+=1;}
				ypos+=3;
				speed-=2;
				if (enem1<=0){bcx-=1000; score+=250;}
			}

			if ((fx > xpos - 25 && fx < xpos + 25 && fy < ypos + 30 && fy > ypos -10)) {
				score += 10;
				dfy = fy;
				dfx = fx;
				fy = 1300;
			}
			if ((fx > bcx - 25 && fx < bcx + 25 && fy < bcy + 30 && fy > bcy -10)) {
				dfy = fy;
				dfx = fx;
				fy = 1300;
			}
			if ((fx > tgx - 25 && fx < tgx + 25 && fy < tgy + 30 && fy > tgy -10)) {
				dfy = fy;
				dfx = fx;
				fy = 1300;
			}
			if ((fx > ocx - 25 && fx < ocx + 25 && fy < ocy + 30 && fy > ocy -10)) {
				dfy = fy;
				dfx = fx;
				fy = 1300;
			}
			if ((fx > otgx - 25 && fx < otgx + 25 && fy < otgy + 30 && fy > otgy -10)) {
				dfy = fy;
				dfx = fx;
				fy = 1300;
			}
			if ((dx > xpos - 35 && dx < xpos + 35 && dy < ypos + 35 && dy > ypos - 10)) {
				score += 25;
				ddy = dy;
				ddx = dx;
				dy = 1300;
				condition--;
			}
			if (killx > ocx - 25 && killx < ocx + 25 && killy < ocy + 40 && killy > ocy -10) {
				whyGodWhy = killy;
				kaboomx = killx;
				killy = 1300;
			}
			if (dx > ocx - 25 && dx < ocx + 25 && dy < ocy + 40 && dy > ocy -10) {
				ddy = dy;
				ddx = dx;
				dy = 1300;
			}
			if (bombx > ocx - 35 && bombx < ocx + 35 && bomby < ocy + 35 && bomby > ocy - 10) {
				boomy = bomby;
				boomx = bombx;
				bomby = 1300;
			}
			if (killx > otgx - 25 && killx < otgx + 25 && killy < otgy + 40 && killy > otgy -10) {
				whyGodWhy = killy;
				kaboomx = killx;
				killy = 1300;
			}
			if (dx > otgx - 25 && dx < otgx + 25 && dy < otgy + 40 && dy > otgy -10) {
				ddy = dy;
				ddx = dx;
				dy = 1300;
			}
			if (bombx > otgx - 35 && bombx < otgx + 35 && bomby < otgy + 35 && bomby > otgy - 10) {
				boomy = bomby;
				boomx = bombx;
				bomby = 1300;
			}
			if (killy < ypos) {
				if (killx > xpos) killx += 2;
				if (killx < xpos) killx -= 2;
			}
			if (bomby > 1800) {
				bomby = 0;
				bombx = 25 + (int)Math.floor(Math.random() * 250);
			}
			if (bcy>300 && bcy>(1200-speed*30)) {
				bcy=0;
				bcx=100;
				enem1=15;
			}
			if ((tgy<0 && tgy<(-700+40*speed)) || tgy>400) {
				tgy=330;
				tgx=170;
				enem3=20;
			}
			if (ocy>300 && ocy>(1500-speed*30)) {
				ocy=0;
				ocx=20;
				enem2=15;
			}
			if ((otgy<0 && otgy<(-1000+40*speed)) || otgy>400) {
				otgy=330;
				otgx=240;
				enem4=20;
			}
			if (killy > 1650) {
				killy = 0;
				killx = 25 + (int)Math.floor(Math.random() * 250);
			}
			if (fy > 1500) {
				fy = 0;
				fx = 25 + (int)Math.floor(Math.random() * 250);
			}
			if (dy > 1700) {
				dy = 0;
				dx = 25 + (int)Math.floor(Math.random() * 250);
			}
			if (gasy > 1000) {
				gasy=0;
				gasx= 25 + (int)Math.floor(Math.random() * 250);
			}
			if (stuffy>1500) {
			 stuffy= 0; stuffx = 25 + (int)Math.floor(Math.random() * 250);}
			if (xmove > 0) xmove -= .1;
			else xmove += .1;
			if (ymove < 0) ymove++;
			if (ymove>0) ymove --;
			repaint();
	}

	public void update(Graphics r) {
		paint(r);
	}

	public void paint(Graphics l) {
		offscreen=createImage(300,300);
		g = offscreen.getGraphics();
		if (area == 1)  {
			g.setColor(Color.gray);
			g.fillRect(0,0,300,300);
			if (flight%20==0) {
				g.setColor(new Color(Color.gray.getRed(),Color.gray.getGreen()+5,Color.gray.getBlue()));
				g.setFont(new Font("TimesRoman",Font.BOLD,36));
				g.drawString("You are happy.",10,(flight*speed)%300);
			}
			g.setFont(new Font("TimesRoman",Font.BOLD,36));
			g.drawString("You are happy.",10,(flight*speed)%300);
			g.setColor(Color.white);
			g.drawLine(1,0,1,300);
			g.drawLine(299,0,299,300);
			g.drawLine(70,0,70,300);
			g.drawLine(232,0,232,300);
			g.setColor(Color.yellow);
			g.drawLine(147,0,147,300);
			g.drawLine(154,0,154,300);
			g.drawImage(gascan, gasx, gasy,this);
			g.drawImage(d, bombx, bomby, this);
			g.drawImage(p, killx, killy, this);
			g.drawImage(u, stuffx, stuffy, this);
			g.drawImage(dd, boomx, boomy, this);
			g.drawImage(dp, kaboomx,whyGodWhy, this);
			g.drawImage(doo,dx,dy,this);
			g.drawImage(f,fx,fy,this);
			g.drawImage(ddoo,ddx,ddy,this);
			g.drawImage(df,dfx,dfy,this);
			if (bcx>0) {
			g.drawImage(bc, bcx,bcy, this);} else {
			g.drawImage(cardead, bcx+1000,bcy, this); }
			if (ocx>0) {
			g.drawImage(bc, ocx,ocy, this);} else {
			g.drawImage(cardead, ocx+1000,ocy, this); }
			if (tgx>0) {
			g.drawImage(tg, tgx,tgy, this);} else {
			g.drawImage(cardead, tgx+1000,tgy, this); }
			if (otgx>0) {
			g.drawImage(tg, otgx,otgy, this);} else {
			g.drawImage(cardead, otgx+1000,otgy, this); }
			g.drawImage(b,bx,by,this);
			if (condition>20) {
				g.drawImage(car, xpos, ypos, this);} else {
				if (condition>0) {
					g.drawImage(flame, xpos,ypos,this); } else {
					if (gas>0) { g.drawImage(cardie,xpos,ypos,this);  flight=400;} else {
					g.drawImage(cardead,xpos,ypos,this);}
				}
			}
			if (owy!=0) g.drawImage(ow, owx, owy, this);
			if (laser<0) {
				for (int misc=1;misc<100; misc++) {
					if (misc%4==0) g.setColor(Color.red);
					if (misc%4==1) g.setColor(Color.green);
					if (misc%4==2) g.setColor(Color.blue);
					if (misc%4==3) g.setColor(Color.green);
					g.fillRect(xpos+10,0,20,ypos);
					g.fillRect(xpos+10,ypos+40,20,300-ypos-40);
					l.drawImage(offscreen,0,0,this);
					pause(5);
				}
				if (Math.abs(bcx-xpos)<20) {bcx-=1000; score+=125;}
				if (Math.abs(ocx-xpos)<20) {ocx-=1000; score+=125;}
				if (Math.abs(tgx-xpos)<20) {tgx-=1000; score+=125;}
				if (Math.abs(otgx-xpos)<20) {otgx-=1000; score+=125;}
				if (Math.abs(fx-xpos)<20) {fx=300; score+=5;}
				if (Math.abs(bombx-xpos)<20) {bombx=300; score+=25;}
				if (Math.abs(dx-xpos)<20) {dx=300; score+=10;}
				if (Math.abs(killx-xpos)<20) {killx=300; score+=50;}
				score+=100;
				laser=0;
			}
			g.setColor(Color.red);
			g.setFont(new Font("TimesRoman",Font.PLAIN,12));
			g.drawString("Score:     " + score,3,65);
			g.drawString("Condition: "+condition+"%",3,21);
			g.drawString("Gas:       "+gas/50,3,43);
			if (laser>0) {
			g.setColor(Color.red);
			if (Math.random()>.3) g.setColor(Color.green);
			if (Math.random()>.3) g.setColor(Color.yellow);
			g.drawString("LASER",3,87);
			}
			l.drawImage(offscreen,0,0,this);
			pause(60);
			run();
		}

		if (area==4) {
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman",Font.BOLD,36));
			g.drawString("Paused",100,170);
			l.drawImage(offscreen,0,0,this);
		}

		if (area==5) {
				g.setColor(Color.black);
				g.fillRect(0,0,400,400);
			for (int cred=1; cred<600; cred++) {
				g.setFont(new Font("TimesRoman",Font.BOLD,18));
				g = offscreen.getGraphics();
				g.setColor(Color.black);
				g.fillRect(0,0,400,400);
				g.setColor(Color.green);
				g.drawString("Designer: Chaz", 50, 300-cred);
				g.drawString("Programer: Sean Head", 50, 320-cred);
				g.drawString("Website: Julian Helder", 50, 340-cred);
				g.drawString("Artwork: Chaz", 50, 360-cred);
				g.drawString("Assistent artist: Sean Head", 50, 380-cred);
				g.drawString("Assistent programer: Chaz", 50, 400-cred);
				g.drawString("Testers:", 50, 420-cred);
				g.drawString("Chaz", 100, 440-cred);
				g.drawString("Nathan James", 100, 460-cred);
				g.drawString("Sean Head", 100, 480-cred);
				g.drawString("Andrew Jones", 100, 500-cred);
				g.drawString("Jenny Guerber", 100, 520-cred);
				g.drawString("Julian Helder", 100, 540-cred);
				g.drawString("Some guy", 100, 560-cred);
				g.drawString("Many Unlisted", 100, 580-cred);
				g.drawString("Something else: Julean Helder", 50, 600-cred);
				g.drawString("", 50, 580-cred);
				l.drawImage(offscreen,0,0,this);
				pause(50);
			}
			area=0;
		}

		if (area == 0) {
			setBackground(Color.black);
			g.setColor(Color.black);
			g.fillRect(0,0,400,400);
			g.setFont(new Font("TimesRoman",Font.BOLD,18));
			g.setColor(Color.red);
			g.drawString("The Game is simple; use the", 10, 20);
			g.drawString("arrow keys to manuever the", 10, 40);
			g.drawString("car, and try to avoid", 10, 60);
			g.drawString("or run over things,", 10, 80);
			g.drawString("depending on your", 10, 100);
			g.drawString("morals. You'll have to", 10, 120);
			g.drawString("click on the applet", 10, 140);
			g.drawString("b4 it will register", 10, 160);
			g.drawString("your keystrokes.", 10, 180);
			g.drawString("Space: Start/Restart game", 10, 200);
			g.drawString("Esc: Pause/Unpause Game", 10, 220);
			g.drawString("Enter: Credits", 10, 240);
			g.drawString("If you have a laser, keypad-0",10,260);
			g.drawString("fires it.",10,280); 
			l.drawImage(offscreen,0,0,this);
		}
	}

	public boolean keyDown(Event evt, int test) {
		switch(test) {
			case Event.DOWN:
				ymove = 7+bonus;
				xmove = 0;
				break;
			case Event.UP:
				ymove = -7-bonus;
				xmove = 0;
				break;
			case Event.LEFT:
				xmove = -7-bonus;
				ymove = 0;
				break;
			case Event.RIGHT:
				xmove = 7+bonus;
				ymove = 0;
				break;
			case 48:
				if (laser==1) laser=-1;
				break;
			case 1025:
				if (laser==1) laser=-1;
				break;
			case 32:
				Start();
				break;
			case 10:
				area=5;
				repaint();
				break;
			case 27:
				if (area==1) {
					area=4; } else {
				if (area==4)
					area=1;}
				repaint();
				break;
			default:
				break;
		}

		return true;
	}
	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {}
	}
}