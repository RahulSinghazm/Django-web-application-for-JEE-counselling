import java.util.*;
import java.io.*;

/**
* <h1>Seat-Allocation Project</h1>
* The HelloWorld program implements an application that
* simply displays "Hello World!" to the standard output.
* <p>
* Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
* 
*
* @author  byters Group 11
* @version 1.0
* @since   2014-10-18
*/

public class Main
{
	/**
	* Creates objects and starts the whole algorithm
	*
	* @param args Unused
	*/
	public static void main(String[] args) {
			GaleShapleyAdmission start1 = new GaleShapleyAdmission();
			start1.startAlgorithm();
			//System.out.println();
			MeritOrderAdmission start2 = new MeritOrderAdmission();
			start2.startAlgorithm();
		}
}