package controller;

import java.io.File;
import com.asprise.ocr.Ocr;



public class ImageToText {
	

		public String getNumberPlate(String FileLocation){
			String[] args = {"Smart Parking","Test"};
			Ocr.setUp(); // one time setup
			Ocr ocr = new Ocr();
			ocr.startEngine("eng", Ocr.SPEED_FASTEST);
			
			String s = ocr.recognize(new File[] {new File(FileLocation)},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT, args); 
			
		//	System.out.println("Result: " + s);
			
			
			
			ocr.stopEngine();
			
			
			return s;
		}

}
