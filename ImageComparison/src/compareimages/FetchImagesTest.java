package compareimages;

import java.io.IOException;

import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class FetchImagesTest extends TestCase {

	@Test
	public void testGetXYofImage() {
		FetchImages getim = new FetchImages();
		
		int xy[] = {0,0};
		try {
			xy = getim.getXYofImage("E:\\c.png", "E:\\e.png", 5, 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int ExpectedArrayValues[] = {109, 134};
		
		assertArrayEquals(ExpectedArrayValues, xy);
	}

}
