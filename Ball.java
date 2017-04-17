package game;

import java.awt.*;

public class Ball {
	private Block block;
	private Pad pad;
	private Point location;
	private int diameter;
	public int dx;
	private int dy;
	private int width;
	private int height;
	public boolean ballMove = false;
	public Ball(Canvas screen,Pad pad,Block block){
	
		this.pad = pad;
		this.block = block;
		
		width = screen.getWidth();
		height = screen.getHeight();
		location = new Point(pad.location.x + (pad.size.x - diameter)/2 ,pad.location.y - pad.size.y);
		dx = 5;
		dy = -5;
		diameter = 20;
	}
	
	private void wallBounced(){
		if((location.x > width - diameter) || (location.x < 0)){
			dx = -dx;
		}
		if(location.y < 0){
			dy = -dy;
		}
		if(location.y > height){
			for(int i = 0;i<1000000000;i++){
				
			}
			ballMove = false;
		}
	}
	
	private boolean Bounce(Point block_location, Point block_size){
		return ((location.x > block_location.x - diameter) && (location.x <block_location.x + block_size.x) &&
		(location.y > block_location.y - diameter) && (location.y < block_location.y + block_size.y));
		
	}
	
	private void blockBounced(){
		Point local1,local2,local3,size1,size2,size3;
		for(int i=0;i<block.num;i++){
			local1 = block.location[i];
			size1 = new Point(block.size.x * 1 / 10 ,block.size.y);
			
			local2 = new Point(block.location[i].x + block.size.x * 1/ 10,block.location[i].y);
			size2 = new Point(block.size.x * 8 / 10 ,block.size.y);
			
			local3 = new Point(block.location[i].x + block.size.x * 9/ 10,block.location[i].y);
			size3 = new Point(block.size.x * 1 /10,block.size.y);
			
			if(Bounce(local2,size2)){
				dy = -dy;
				block.exist[i] = false;
			}else if(Bounce(local1,size1)){
				if(dx > 0){
					dx = -dx;
				}else{
					dy = -dy;
				}
				block.exist[i] = false;
			}else if(Bounce(local3,size3)){
				if(dx < 0){
					dx = -dx;
				}else{
					dy = -dy;
				}
				block.exist[i] = false;
			}
			
		}
		
	}
	
	private void padBounced(){
		Point local1,local2,local3,size1,size2,size3;
		
		local1 = pad.location;
		size1 = new Point(pad.size.x * 1/7 ,pad.size.y /4);
		
		local2 = new Point(pad.location.x + pad.size.x /7,pad.location.y);
		size2 = new Point(pad.size.x * 5/7,pad.size.y  /4);
		
		local3 = new Point(pad.location.x + pad.size.x *6/7,pad.location.y);
		size3 =new Point(pad.size.x * 1/7 ,pad.size.y  /4);
		
		if(dy > 0){
			if(Bounce(local2,size2)){
				dy = -dy;
			
			}
			else if(Bounce(local1,size1)){
			
				if(dx > 0){
					dx = -dx;
				}
				dy = -dy;
			}else if(Bounce(local3,size3)){
			
				if(dx < 0){
					dx = -dx;
				}
				dy = -dy;
			}
		}
	}
	
	public void setAgain(){
		location.setLocation(pad.location.x + (pad.size.x - diameter) / 2, pad.location.y - pad.size.y);
		dx = 5;
		dy = -5;
		
	}
	

	public void update(){
		
		if(ballMove){
			location.x += dx;
			location.y += dy;
			wallBounced();
			blockBounced();
			padBounced();
		}else{
			setAgain();
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(location.x, location.y, diameter, diameter);
	}
}
