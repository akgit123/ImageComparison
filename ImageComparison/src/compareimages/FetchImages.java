package compareimages;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.Math;

public class FetchImages {

	public static void main(String[] args) throws IOException {
		
		FetchImages getXY = new FetchImages();
		int xy[] = {15,21};
		xy = getXY.getXYofImage("E:\\c.png", "E:\\e.png", 5, 0);
		System.out.println(xy[0]);
		System.out.println(xy[1]);
		
	}
	
	public int[] getXYofImage(String BenchmarkImgPath, String ImgToComparePath, int ImageLocation, int SearchArea) throws IOException{
		int xy[] = {-1,-1};
		
		File BmImgPath  = new File(BenchmarkImgPath);
		File CmpImgPath = new File(ImgToComparePath);
		
	    BufferedImage BenchmarkImg = ImageIO.read(BmImgPath);
	    BufferedImage ImgToCmp     = ImageIO.read(CmpImgPath);
	  
	    int BmImgWidth  = BenchmarkImg.getWidth();
	    int BmImgHeight = BenchmarkImg.getHeight();
	    
	    int CmpImgWidth  = ImgToCmp.getWidth();
	    int CmpImgHeight = ImgToCmp.getHeight();
	    
	    int RGBvaluesOfBmImg  = 0;
	    int RGBvaluesOfCmpImg = 0;
	    int brk= 0;
	    int i  = 0;
	    int j  = 0;
	    
	    int Searchx      = 0;
	    int Searchy      = 0;
	    int SearchWidth  = 0;
	    int SearchHeight = 0;
	    
	    // the below code is written for improving the performance of the search.
	    if (SearchArea < 0 || SearchArea > 4)
	    {
	    	xy[0] = -3;
	    	xy[1] = -3;
	    	return xy;
	    }
	    else if(SearchArea == 0)
	    {
	    	Searchx      = 0;
	    	Searchy      = 0;
	    	SearchWidth  = BmImgWidth;
	    	SearchHeight = BmImgHeight;
	    }
	    else if(SearchArea == 1)
	    {
	    	Searchx      = 0;
	    	Searchy      = 0;
	    	SearchWidth  = BmImgWidth/2;
	    	SearchHeight = BmImgHeight/2;
	    }
	    else if(SearchArea == 2)
	    {
	    	Searchx      = BmImgWidth/2;
	    	Searchy      = 0;
	    	SearchWidth  = BmImgWidth;
	    	SearchHeight = BmImgHeight/2;
	    }
	    else if(SearchArea == 3)
	    {
	    	Searchx      = 0;
	    	Searchy      = BmImgHeight/2;
	    	SearchWidth  = BmImgWidth/2;
	    	SearchHeight = BmImgHeight;
	    }
	    else if(SearchArea == 4)
	    {
	    	Searchx      = BmImgWidth/2;
	    	Searchy      = BmImgHeight/2;
	    	SearchWidth  = BmImgWidth;
	    	SearchHeight = BmImgHeight;
	    }
	    
	    if(ImageLocation < 1 || ImageLocation > 9)
	    {
	    	xy[0] = -2;
	    	xy[1] = -2;
	    	return xy;
	    }
	    
	    for (i=Searchx; i<SearchWidth; i++) {
	    	for (j=Searchy; j<SearchHeight; j++) {
	    		RGBvaluesOfBmImg = BenchmarkImg.getRGB(i,j);
	    		RGBvaluesOfCmpImg = ImgToCmp.getRGB(0,0);
	    		if(RGBvaluesOfBmImg==RGBvaluesOfCmpImg){
	    			for (int a=0; a<CmpImgWidth; a++){
	    				for (int b=0; b<CmpImgHeight; b++){
	    					if(BenchmarkImg.getRGB(i+a,j+b) != ImgToCmp.getRGB(a,b)){
	    						brk=1;
	    						break;
	    					}
	    				}
	    				if(brk==1)
	    				{
	    					break;
	    				}
	    				else
	    				{
	    					if (CmpImgWidth>=12 && BmImgHeight>=12)
	    					{
		    					// this piece of code is written to avoid lengthy
		    					// if/else condition for each number from 1-9 received in
		    					// ImageLocation parameter.
	    						// this code will divide the whole comparison image 
	    						// into 9 equal boxes.
		    					float fl = ImageLocation;
		    					fl = fl/3;
		    					int height_scalar = (int) Math.ceil(fl);
		    					int width_scalar = ImageLocation-(3*(height_scalar-1));
		    					int widthx = CmpImgWidth / 3;
		    					int heighty = CmpImgHeight / 3;
		    					
		    					xy[0] = i + (widthx * width_scalar)-(widthx/2);
		    					xy[1] = j + (heighty * height_scalar)-(heighty/2);
	    					}
	    					else
	    					{
	    						// this else statement is used to ensure that the image
	    						// is divided into 9 parts only if the image size is
	    						// at-least 12x12 pixels.
	    						xy[0] = i + (CmpImgWidth/2);
	    						xy[1] = j + (BmImgHeight/2);
	    					}
	    					
	    	    			return xy;
	    				}
	    			}
	    			brk=0;
	    		}
			}
	    }
	    return xy;
	}	
}
