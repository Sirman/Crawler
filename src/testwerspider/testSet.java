package testwerspider;
import java.util.Set;
import java.util.HashSet;

public class testSet 
{
	public static void main(String[] ags)
	{
		//Set visitedUrl = new HashSet();
		HashSet visitedUrl = new HashSet();
		visitedUrl.add(new String("Crawler"));
		visitedUrl.add(new String("Crawler"));
		visitedUrl.add(new Integer(1234567890));
		System.out.println(visitedUrl);
	}
}
