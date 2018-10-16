package main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import utils.fct;

public class Main {

	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args) {
		
		String filepath = "src/resources/images/euro-coins.jpg";
		
		Mat myImg = Imgcodecs.imread(filepath);
		
		if(myImg.dataAddr() == 0){
			System.out.println("Image can't be read -> " + filepath);
		}else{
			fct fct = new fct();
			
			
			Mat circles = new Mat();
			Mat canny = new Mat();
			//Imgproc.Canny(originalImage, canny,10 , 50, aperture, false);
			Imgproc.cvtColor( myImg, canny, Imgproc.COLOR_BGR2GRAY);
			Imgproc.blur(canny, canny, new Size(3,3));
			
			Mat image = myImg.clone();
			
			//Imgproc.HoughCircles(canny, circles,Imgproc.CV_HOUGH_GRADIENT, 1, lowThreshold);
			Imgproc.HoughCircles(canny, circles,Imgproc.CV_HOUGH_GRADIENT, 1, canny.rows()/8, 200, 134, 0, 0 );
			System.out.println(circles.size());
			for( int i=0;i<circles.cols();i++){
				
				Point center = new Point( circles.get(0,i)[0], circles.get(0, i)[1]);
				int radius = (int) Math.round(circles.get(0, i)[2]);
				
				System.out.println("radius : " + Math.floor(radius));
				
				Imgproc.circle( image, center, radius, new Scalar(0,255,0),3);//radius, color)

				
			}
			
			fct.show(image);
		}
		
		
	}

}
