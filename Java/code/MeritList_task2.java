import java.io.*;
import java.util.*;

/**
* <h2>MeritList for MeritOrder</h2>
* This class contains an array list of candidates which are later sorted according to rank using :
* {@link #sortList()} method
*/
public class MeritList_task2{
	
	//data members
	private ArrayList<Candidate> rankList;
	private int meritListIndex;
	
	//constructor
	/**
	*Constructor which initializes empty ranklist(for CML)
	*/
	public MeritList_task2(){
		rankList = new ArrayList<Candidate>();
	}
	/**
	* Constructor with a parameter to initialize empty the ranklist(for Category wise list)
	* @param index To set the index of the category rank which is to be used to compare
	*/
	public MeritList_task2(int index){
		rankList = new ArrayList<Candidate>();
		meritListIndex = index;
	}

	//Functions for accessing data members
	public int getMeritListIndex(){
		return meritListIndex;
	}
	public ArrayList<Candidate> getRankList(){
		return rankList;
	}

	//Function for adding candidate
	public void addCandidate(Candidate newCandidate){
		rankList.add(newCandidate);
	}

	//Function for appending lists
	/**
	* Appends the ranklist provided, at the end of current ranklist
	* @param list The ranklist to be added
	*/
	public void appendList(ArrayList<Candidate> list){
		for (ListIterator<Candidate> current = list.listIterator(); current.hasNext(); )
		{
			addCandidate(current.next());
		}
	}

	//Function for sorting lists
	/**
	* Uses modified built-in sort method to arrange list of candidates in increasing order of rank<p>
	* <pre><code>
	* Collections.sort(rankList, new Comparator<Candidate>() {
	*        {@literal @}Override
	*        public int compare(Candidate candidate1, Candidate candidate2)
	* </code></pre>
	*/
	public void sortList(){
		Collections.sort(rankList, new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate candidate1, Candidate candidate2)
	        {
	        	if(candidate1.getRank(getMeritListIndex()) < candidate2.getRank(getMeritListIndex()))
	        		return -1;
	        	else
	        		return 1;
	        }
    	});
	}
}