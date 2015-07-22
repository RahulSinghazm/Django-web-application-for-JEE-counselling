import java.io.*;
import java.util.*;

/**
* <h2>MeritList for GaleShapley</h2>
* This class contains HashMap for storing ranks of candidates against their ID<br>
* It is taken care of for de-reservation i.e. OBC list have GE, SC-PD list have SC, etc.<p>
* It also contains a method {@link #compareRank(Candidate,Candidate,int)} for comparing ranks which is used under VirtualProgramme
* @see HashMap
*/
public class MeritList_task1{

	//data members
	private HashMap<String, Integer> rankList;

	//constructor
	/**
	* Constructor for initializing empty ranklist
	*/
	public MeritList_task1(){
		rankList = new HashMap<String, Integer>();
	}

	//copy constructor
	/**
	* Constructor for initializing copied ranklist
	* @param temp MeritList to be copied
	*/
	public MeritList_task1(MeritList_task1 temp){
		rankList = new HashMap<String, Integer>(temp.rankList);
	}

	//Function for adding candidate
	public void addCandidate(String newID, int rank){
		rankList.put(newID,rank);
	}

	//Function for getting rank
	/**
	* Gets rank of Candidate from the ranklist
	* @param candidateID ID to be searched for rank
	* @return int Rank of the Candidate, -1 if not present
	*/
	public int getRank(String candidateID){
		//if the candidate is not present in the merit list, return -1 else return the rank
		if(rankList.containsKey(candidateID)==false){
			return -1;
		}
		return rankList.get(candidateID);
	}

	//Function for comparing ranks
	//0 means p1's rank is better than p2's rank
	//1 means p2's rank is better than p1's rank
	//2 means both have same rank
	/**
	* Compares rank of two provided candidates:<br>
	* 0 means p1's rank is better than p2's rank<br>
	* 1 means p2's rank is better than p1's rank<br>
	* 2 means both have same rank
	* @param p1 First Candidate
	* @param p2 Second Candidate
	* @param case_ category case to consider
	* @return int either 0,1 or 2
	*/
	public int compareRank(Candidate p1, Candidate p2, int case_){
		if(case_==0 || case_==2 || case_==3){
			if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) 
				return 0;
			else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) 
				return 2;
			else 
				return 1;
		}
		else if(case_==1){
			if((p1.getCategory().equals("OBC") && p1.getPDStatus()==false) && !(p2.getCategory().equals("OBC") && p2.getPDStatus()==false)){
				return 0;
			}
			else if(!(p1.getCategory().equals("OBC") && p1.getPDStatus()==false) && (p2.getCategory().equals("OBC") && p2.getPDStatus()==false)){
				return 1;
			}
			else{
				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) 
					return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) 
					return 2;
				else 
					return 1;
			}
		}
		else if(case_==4){
			if((p1.getCategory().equals("GE") && p1.getPDStatus()==true) && !(p2.getCategory().equals("GE") && p2.getPDStatus()==true)){
				return 0;
			}
			else if(!(p1.getCategory().equals("GE") && p1.getPDStatus()==true) && (p2.getCategory().equals("GE") && p2.getPDStatus()==true)){
				return 1;
			}
			else{
				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) 
					return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) 
					return 2;
				else 
					return 1;
			}
		}
		else if(case_==5){
			if((p1.getCategory().equals("OBC") && p1.getPDStatus()==true) && !(p2.getCategory().equals("OBC") && p2.getPDStatus()==true)){
				return 0;
			}
			else if(!(p1.getCategory().equals("OBC") && p1.getPDStatus()==true) && (p2.getCategory().equals("OBC") && p2.getPDStatus()==true)){
				return 1;
			}
			else{
				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) 
					return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) 
					return 2;
				else 
					return 1;
			}
		}
		else if(case_==6 || case_==7){
			if(p1.getPDStatus()==true && p2.getPDStatus()==false){
				return 0;
			}
			else if(p1.getPDStatus()==false && p2.getPDStatus()==true){
				return 1;
			}
			else{
				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) 
					return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) 
					return 2;
				else 
					return 1;
			}	
		}
		else return -1;
	}
	/**Idea::Add the sort function here. Use comparator. For example for meritList[1]{OBC and non-PD}, first comparision:on category. 
	oBC student should be higher up. Second comparision(which will be done when first compariosion gives equals(that is if both 
	candidates belong to the same category)), then compare by their rank. 
	This way the merit list will automatically place all OBC students above GE students.
	@Pranjal: Note that now we don't need to increment the rank of GE students by "Number of OBC Students" .This is much cleaner solution. 
	Also note that the we need 8 diferent sort functions for the 8 different merit lists.
	Regarding the design of function: 
		return type: void
		paramter: int index (from 0 to 7)
		body: based on the value of "index" implement the corresponding sorting. 

	*/
}