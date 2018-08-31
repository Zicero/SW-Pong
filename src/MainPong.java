import java.awt.Color;
import java.util.Random;


public class MainPong {

	public static void main(String[] args) {
		// Setup EZ graphics system.
				EZ.initialize(700,500);    // Start up EZ and make a screen of 700 x 500 pixels in size

				EZ.setBackgroundColor(new Color(0, 0,0));  // Make the background black.
	
				EZImage backgroundPicture = EZ.addImage("milleniumfalcon.jpg", 450,200);
				EZImage probePicture = EZ.addImage("probe.png", 0,0);
				EZImage saberPicture = EZ.addImage("saber-blue.png", 0,0);
			
			
				EZSound saberSound = EZ.addSound("saberhum.wav");
				EZSound clashSound = EZ.addSound("clash.wav");
				EZSound impressiveSound = EZ.addSound("impressive.wav");
				saberSound.loop();
				
				int posX = 0;
				int posY = 200;
				int directionX = 5;
				int directionY = 5;
				int rotationAngle = 0;
				int saberX = 700/2;

				int bounces = 0;
				int score=0;
				EZText textbox= EZ.addText(350, 450, "The score is " + score, Color.YELLOW, 20 );

				
				while (score >= 0) {

					probePicture.translateTo(posX, posY); // Set the position of the probe.

					probePicture.rotateTo(rotationAngle); // Set the rotation angle of the probe.
										
					posX= posX+directionX;
					posY= posY+directionY;
					
					rotationAngle+=directionX;

					
					if (posX > 700 ) {
						directionX = -directionX;
					}
					if (posX < 0) {
						directionX = -directionX;
					}
					
		//			if ((posX > 700) || (posX < 0)) {
		//				directionX = -directionX;
		//			}
					
					if (posY > 600 ) {
						
						score-=5;
						textbox.setMsg("The score is "+ score);
						posY=0;
					}
					if (posY < 0) {
						directionY = -directionY;
					}
					
					if (EZInteraction.isKeyDown('d')) {
						saberX+=10;
					} else if (EZInteraction.isKeyDown('a')) {
						saberX-=10;
					}
					
					int halfSaberWidth = saberPicture.getWidth()/2;
					
					saberPicture.translateTo(saberX,480);
					
					if ((posX < saberX+halfSaberWidth) && (posX > saberX-halfSaberWidth) && (posY > 460) && (posY < 480)){
						
						score++;
						textbox.setMsg("The score is "+ score);
						directionY = -directionY;
						clashSound.play();
						bounces++;
						if (bounces == 3) {
							impressiveSound.play();
							bounces = 0;
						}
					}
					
					// Make sure to do this or else the graphics won't refresh
					EZ.refreshScreen();
				}
	}

}
