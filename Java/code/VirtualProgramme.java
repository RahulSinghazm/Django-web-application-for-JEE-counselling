import java.util.*;
import java.io.*;

/**
* <h2>Virtual Programme for a Program</h2>
* This class is a virtual program for a particular category of an institute program i.e contains quota of that category.<p>
* For GaleShapley:<br>
* The program contains {@link #receiveApplication(Candidate,HashMap)} method for recieving application<br>
* and two methods {@link #filter(HashMap)} and {@link #fFilter(HashMap)} for filtering the candidates who applied<br>
* in normal and F case respectively.<p>
* For MeritOrder:<br>
* The program contains two methods {@link #checkApplication(Candidate)} and {@link #checkFApplication(Candidate)} for selecting the candidate who applied<br>
* in normal and F case respectively.
* It also have {@link #dereserveSeats(ArrayList)} method for dereserving seats after Phase-I.
*/
public class VirtualProgramme  
{
	private String programID;
	private String instiID;
	private String category;	
	private Boolean pdStatus;
	private int quota;
	private int meritListIndex;
	private int seatsFilled;
	private ArrayList<Candidate> waitList;
	private ArrayList<Candidate> waitListForeign;
	private ArrayList<Candidate> waitListDS;
	private ArrayList<Candidate> tempList;
	private MeritList_task1 meritList;

	/**
	* This is a copy constructor
	* @param prog Program to be copied
	*/
	public VirtualProgramme(VirtualProgramme prog) {
		programID = prog.programID ;
		instiID = prog.instiID;
		category = prog.category ;
		pdStatus = prog.pdStatus ;
		quota = prog.quota ;
		meritListIndex = prog.meritListIndex ;
		seatsFilled = prog.seatsFilled ;
		waitList = new ArrayList<Candidate>(prog.waitList) ;
		waitListForeign = new ArrayList<Candidate>(prog.waitListForeign) ;
		waitListDS = new ArrayList<Candidate>(prog.waitListDS) ;
		tempList = new ArrayList<Candidate>(prog.tempList) ;
		meritList = new MeritList_task1(prog.meritList) ;
	}

	/**
	* This is a constructor for GaleShapley class as it requires meritlist in it
	* @param category_ category of program
	* @param pdStatus_ PD Status of program
	* @param quota_ Total seats in program
	* @param recievedList An array of all the 8 meritlists (But we store only the relevent)
	* @param programID_ ID of program
	* @param instiID_ ID of the institute that provides this program
	*/
	public VirtualProgramme(String category_ , Boolean pdStatus_ , int quota_, MeritList_task1[] recievedList, String programID_, String instiID_)
	{
		category = category_;
		pdStatus = pdStatus_;
		programID = programID_;
		instiID = instiID_;

		if(pdStatus == false)
		{
			if(category == "GE")
			{
				meritListIndex = 0;
			}
			if(category == "OBC")
			{
				meritListIndex = 1;
			}
			if(category == "SC")
			{
				meritListIndex = 2;
			}
			if(category == "ST")
			{
				meritListIndex = 3;
			}
		}
		else
		{
			if(category == "GE_PD")
			{
				meritListIndex = 4;
			}
			if(category == "OBC_PD")
			{
				meritListIndex = 5;
			}
			if(category == "SC_PD")
			{
				meritListIndex = 6;
			}
			if(category == "ST_PD")
			{
				meritListIndex = 7;
			}
		}

		quota = quota_;
		//list of candidates who have applied for that programme in 1 iteration
		tempList = new ArrayList<Candidate>();
		//list of candidates who have been wait listed after filtering
		waitList = new ArrayList<Candidate>();
		waitListDS = new ArrayList<Candidate>();
		waitListForeign = new ArrayList<Candidate>();

		meritList = new MeritList_task1(recievedList[meritListIndex]);
		seatsFilled = 0 ;
	}

	/**
	* This is a constructor for MeritOrder class as it do not require meritlist in it
	* @param category_ category of program
	* @param pdStatus_ PD Status of program
	* @param quota_ Total seats in program
	* @param programID_ ID of program
	* @param instiID_ ID of the institute that provides this program
	*/
	public VirtualProgramme(String category_ , Boolean pdStatus_ , int quota_, String programID_, String instiID_)
	{
		category = category_;
		pdStatus = pdStatus_;
		programID = programID_;
		instiID = instiID_;

		if(pdStatus == false)
		{
			if(category == "GE")
			{
				meritListIndex = 0;
			}
			if(category == "OBC")
			{
				meritListIndex = 1;
			}
			if(category == "SC")
			{
				meritListIndex = 2;
			}
			if(category == "ST")
			{
				meritListIndex = 3;
			}
		}
		else
		{
			if(category == "GE_PD")
			{
				meritListIndex = 4;
			}
			if(category == "OBC_PD")
			{
				meritListIndex = 5;
			}
			if(category == "SC_PD")
			{
				meritListIndex = 6;
			}
			if(category == "ST_PD")
			{
				meritListIndex = 7;
			}
		}

		quota = quota_;
		//list of candidates who have applied for that programme in 1 iteration
		tempList = new ArrayList<Candidate>();
		//list of candidates who have been wait listed after filtering
		waitList = new ArrayList<Candidate>();
		waitListDS = new ArrayList<Candidate>();
		waitListForeign = new ArrayList<Candidate>();

		meritList = new MeritList_task1();
		seatsFilled = 0 ;
	}

	public String getProgramID(){
		return programID;
	}
	public String getInstiID(){
		return instiID;
	}
	public String getCategory() {
		return category ;
	}
	public int getMeritListIndex(){
		return meritListIndex;
	}
	public int getSeatsFilled(){
		return seatsFilled;
	}
	public int getQuota(){
		return quota;
	}
	public void setQuota(int x){
		quota=x;
	}

	/** @debug: maybe you can pass tempId(string) ,instead of Candidate*/
	/**
	* Receives application of the candidate and check if he can apply for the program.(for GaleShapley)<br>
	* If he can't, he is added to the rejectionList (its a hashmap)
	* @param newCandidate Candidate which applied
	* @param rejectionList Common rejection list for all programs
	* @see MeritList_task1#getRank(String)
	*/
	public void receiveApplication(Candidate newCandidate, HashMap<String , Candidate> rejectionList)	
	{	//check if the candidate is present in the merit list, which is available in gale-shapley class.
		if(meritList.getRank(newCandidate.getUniqueID())!=-1)
		{
			tempList.add(newCandidate);
		}
		else
		{
			rejectionList.put(newCandidate.getUniqueID(), newCandidate);	//otherwise add the candidate to the rejection list for that iteration of the gale sharpley algorithm.
		}
	}

	//Idea::We can Implement treemap too
	/**
	* Uses modified built-in sort method to arrange list of candidates in increasing order of rank
	* @param rankList List to be sorted
	* @see MeritList_task1#compareRank(Candidate,Candidate,int)
	*/
	public void sortList(ArrayList<Candidate> rankList){
		Collections.sort(rankList, new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate candidate1, Candidate candidate2)
	        {
	        	if(meritList.compareRank(candidate1, candidate2, meritListIndex) == 0)
	        		return -1;
	        	else
	        		return 1;
	        }
    	});
	}

	/**
	* Filters application provided according to qouta and meritlist and rejected candidates added to rejectionlist.(for GaleShapley)
	* @param rejectionList Common rejection list for all programs
	* @see #sortList(ArrayList)
	*/
	public HashMap<String , Candidate> filter(HashMap<String , Candidate> rejectionList)
	{
		sortList(tempList) ;
		if(quota > 0) {
			waitList.clear() ;
			int max_size = Math.min(quota , tempList.size()) ;
			waitList.addAll(tempList.subList(0, max_size)) ;
			int i ;
			for(i = max_size ; i < tempList.size() ; i++) {
				if(meritList.compareRank(waitList.get(waitList.size()-1), tempList.get(i), meritListIndex)==2) {
					waitList.add(tempList.get(i)) ;
				}
				else {
					break ;
				}
			}
			for( ; i < tempList.size() ; i++) {
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}
		else {
			for(int i=0; i<tempList.size(); i++){
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}

		tempList.clear();
		return rejectionList;
	}

	/**
	* Filters application provided by F candidates according to qouta and meritlist and rejected candidates added to rejectionlist.(for GaleShapley)
	* @param rejectionList Common rejection list for all programs
	* @see #sortList(ArrayList)
	*/
	public HashMap<String , Candidate> fFilter(HashMap<String , Candidate> rejectionList)
	{
		sortList(tempList) ;
		if(quota>0){
			int i ;
			for(i = 0 ; i < tempList.size() ; i++) {
				if(waitList.size()<quota){
					waitList.add(tempList.get(i)) ;
				}
				else if(meritList.compareRank(waitList.get(waitList.size()-1), tempList.get(i), meritListIndex)==2) {
					waitList.add(tempList.get(i)) ;
				}
				else {
					break ;
				}
			}
			for( ; i < tempList.size() ; i++) {
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}
		else {
			for(int i=0; i<tempList.size(); i++){
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}

		tempList.clear();
		return rejectionList;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/**********************************************************Functions for MeritOrder(Specific)******************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	/**
	* Checks if the applicant gets selected or not provided he is elligible and seat is present.(for MeritOrder)
	* @param candidate
	* @return Boolean <code>true</code> if waitlisted else <code>false</code>
	*/
	public Boolean checkApplication(Candidate candidate){
		if(quota>0 && candidate.getRank(meritListIndex)>0){
			if(seatsFilled<quota){
				waitList.add(candidate);
				seatsFilled++;
				return true;
			}
			else if(waitList.get(waitList.size()-1).getRank(meritListIndex)==candidate.getRank(meritListIndex)){
				waitList.add(candidate);
				seatsFilled++;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	/**
	* Checks if the F-applicant gets selected or not provided he is elligible and seat is present.(for MeritOrder)
	* @param candidate
	* @return Boolean <code>true</code> if waitlisted else <code>false</code>
	*/
	public Boolean checkFApplication(Candidate candidate){
		if(quota>0 && candidate.getRank(meritListIndex)>0){
			if(seatsFilled<quota){
				waitList.add(candidate);
				seatsFilled++;
				return true;
			}
			else if(waitList.get(waitList.size()-1).getRank(meritListIndex)>=candidate.getRank(meritListIndex)){
				waitList.add(candidate);
				seatsFilled++;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	/**
	* Gives the seats remaining vacant for de-reservation and reduces the qouta
	* @return int no. of seats vacant
	*/
	public int getDiff(){
		if(seatsFilled<quota){
			int temp = quota - seatsFilled;
			setQuota(seatsFilled);
			return temp;
		}
		else
			return 0;
	}

	/**
	* Dereserves seat if possible for GE, SC or ST whatever the case
	* @param progList A list of all category programs for this program
	* @see #getDiff()
	*/
	public void dereserveSeats(ArrayList<VirtualProgramme> progList){
		int vacancy=0;
		if(meritListIndex==0)
			vacancy = progList.get(1).getDiff() + progList.get(4).getDiff() + progList.get(5).getDiff();
		if(meritListIndex==2)
			vacancy = progList.get(6).getDiff();
		if(meritListIndex==3)
			vacancy = progList.get(7).getDiff();
		//there is one doubt, should it be
		//setQuota(quota+vacancy);
		setQuota(seatsFilled+vacancy);
	}
}