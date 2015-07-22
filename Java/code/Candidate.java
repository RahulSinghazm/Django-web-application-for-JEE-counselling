import java.io.*;
import java.util.*;
/**
* <h2>Structure for Candidate</h2>
*
* Contains information for each candidate along with their preference list.<p>
* This has two separate methods for adding preference for both the tasks:<br>
* {@link #addPreference_task1(ArrayList)} method and {@link #addPreference_task2(ArrayList)} method
*/
public class Candidate{
	
	//Personal Information
	private String uniqueID;
	private String category;
	private boolean pdStatus;
	private boolean dsStatus;
	private boolean nationality;
	private int[] rank;

	//Information regarding preferences
	private ArrayList<VirtualProgramme> preferenceList;
	private ArrayList<VirtualProgramme> dsPreferenceList;
	private ArrayList<VirtualProgramme> fPreferenceList;
	private int appliedUpto;
	private boolean isWaitListed;
	private VirtualProgramme waitListedFor; 
	//Functions to manipulate data members
	
	public String getUniqueID(){
		return uniqueID;
	}
	public String getCategory(){
		return category;
	}
	public void setCategory(String x){
		category=x;
	}
	public boolean getPDStatus(){
		return pdStatus;
	}
	public boolean getDSStatus(){
		return dsStatus;
	}
	public void setDSStatus(boolean x){
		dsStatus=x;
	}
	public boolean getNationality(){
		return nationality;
	}
	public int getAppliedUpto(){
		return appliedUpto;
	}
	public void setAppliedUpto(int x){
		appliedUpto = x;
	}
	public VirtualProgramme getWaitListedFor(){
		return waitListedFor;
	}
	public void setWaitListedFor(VirtualProgramme x){
		waitListedFor = x;
	}

	public VirtualProgramme getChoice(int i){
		return preferenceList.get(i);
	}
	public VirtualProgramme getDSChoice(int i){
		return dsPreferenceList.get(i);
	}
	public VirtualProgramme getFChoice(int i){
		return fPreferenceList.get(i);
	}

	/** 
	* Adds all the 8 category ranks
	* @param x Array of category ranks
	*/
	public void addRank(int[] x){
		for(int i=0;i<8;i++){
			rank[i]=x[i];
		}
	}
	public int getRank(int index){
		return rank[index];
	}

	//Constructor

	/**
	* This is the constructor for the class.<br>
	* It also initialises all the lists of program choices.
	* @param uniqueID Candidates ID
	* @param category Candidates category
	* @param pdStatus Candidates PD Status
	* @param dsStatus Candidates DS Status
	* @param nationality <code>true</code> if Indian else <code>false</code>
	*/


	public Candidate(String uniqueID, String category, boolean pdStatus, boolean dsStatus, boolean nationality){
		this.uniqueID=uniqueID;
		this.category=category;
		this.pdStatus=pdStatus;
		this.dsStatus=dsStatus;
		this.nationality=nationality;
		preferenceList = new ArrayList<VirtualProgramme>();
		dsPreferenceList = new ArrayList<VirtualProgramme>();
		fPreferenceList = new ArrayList<VirtualProgramme>();
		appliedUpto = 0;
		isWaitListed = false;
		rank = new int[8];
	}

	//Copy Constructor

	/**
	* This is the copy constructor
	* @param x The candidate to be copied.
	*/
	public Candidate(Candidate x){
		uniqueID=x.uniqueID;
		category=x.category;
		pdStatus=x.pdStatus;
		dsStatus=x.dsStatus;
		nationality=x.nationality;
		preferenceList = new ArrayList<VirtualProgramme>(x.preferenceList);    //I am not sure whether u need preferencelist, appliedupto or waitlistedfor too and whether this will work
		dsPreferenceList = new ArrayList<VirtualProgramme>(x.dsPreferenceList);
		fPreferenceList = new ArrayList<VirtualProgramme>(x.fPreferenceList);
		appliedUpto=x.appliedUpto;
		//waitListedFor = new VirtualProgramme(x.waitListedFor);
		isWaitListed=x.isWaitListed;
		addRank(x.rank);

	}

	//Function for adding preferences to the preference list

	/**
	* Adds program choices provided into a list with some minor changes required for GaleShapley(task1)<p>
	* Takes care of de-reservation, DS-list, F-list and de-reservation for F.
	* @param choice An ArrayList of a single VirtualProgramme for all the 8 categories
	*/
	public void addPreference_task1(ArrayList<VirtualProgramme> choice) {
		if(category.equals("GE")) { 
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(5));
			}
			else {
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(5));
			}
		}
		else if(category.equals("OBC")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(5));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(5));
			}
		}
		else if(category.equals("SC")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(2));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(5));
				preferenceList.add(choice.get(6));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(2));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(6));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(5));
			}
		}
		else if(category.equals("ST")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(3));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(5));
				preferenceList.add(choice.get(7));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(3));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(7));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(5));
			}
		}
		if(dsStatus){
			dsPreferenceList.add(choice.get(0));
		}
		if(!nationality){
			if(!pdStatus){
				fPreferenceList.add(choice.get(0));
				fPreferenceList.add(choice.get(1));
				fPreferenceList.add(choice.get(4));
				fPreferenceList.add(choice.get(5));
			}
			else{
				fPreferenceList.add(choice.get(0));
				fPreferenceList.add(choice.get(4));
				fPreferenceList.add(choice.get(1));
				fPreferenceList.add(choice.get(5));
			}
		}
	}

	/**
	* Gives program to which the Candidate applies
	* @return VirtualProgramme
	*/
	public VirtualProgramme currentVirtualProgramme(){
		return preferenceList.get(appliedUpto);
	}

	/**
	* Gives program to which the DS Candidate applies
	* @return VirtualProgramme
	*/
	public VirtualProgramme currentDSVirtualProgramme(){
		return dsPreferenceList.get(appliedUpto);
	}

	/**
	* Gives program to which the F Candidate applies
	* @return VirtualProgramme
	*/
	public VirtualProgramme currentFVirtualProgramme(){
		return fPreferenceList.get(appliedUpto);
	}

	/**
	* Gives whether Candidate is waiting for some program or rejected
	* @return boolean
	*/
	public boolean isWaitListedFor(){
		return isWaitListed;
	}

	/**
	* Sets whether Candidate is waiting for some program or rejected
	* @param p Required waiting status
	*/
	public void setWaitListedForBool(boolean p){
		isWaitListed=p;
	}
	//Function for finding the next Virtual Programme

	/**
	* Sets the pointer(index) to the next program in the preference list if present else to -1<br> 
	* It also changes the program its waiting for
	* @see #setWaitListedFor(VirtualProgramme)
	*/
	public void nextVirtualProgramme(){
		appliedUpto++;												/** @note to Pranjal: Maybe you should call the function "setWaitListedFor( preferenceList.get(appliedUpto))"
													so that when this function is called from galeShapley class, the current waitListed Programme also gets updated automatically*/
		if(appliedUpto < preferenceList.size() )
		{
			setWaitListedFor(preferenceList.get(appliedUpto));		//@anmol: I think this should be in VirtualProgramme as when applying to the next programme we can get rejected so only the
		}															//programme knows we are waitlisted or rejected
		//Reappear for JEE
		if(appliedUpto==preferenceList.size()){
			appliedUpto=-1; //-1 means reappear next year
		}
	}

	/**
	* Sets the pointer(index) to the next program in the DS-preference list if present else to -1<br> 
	* It also changes the program its waiting for
	*/
	public void nextDSVirtualProgramme(){
		appliedUpto++;												/** @note to Pranjal: Maybe you should call the function "setWaitListedFor( preferenceList.get(appliedUpto))"
													so that when this function is called from galeShapley class, the current waitListed Programme also gets updated automatically*/
		if(appliedUpto < dsPreferenceList.size() )
		{
			setWaitListedFor(dsPreferenceList.get(appliedUpto));		//@anmol: I think this should be in VirtualProgramme as when applying to the next programme we can get rejected so only the
		}															//programme knows we are waitlisted or rejected
		//Reappear for JEE
		if(appliedUpto==dsPreferenceList.size()){
			appliedUpto=-1; //-1 means reappear next year
		}
	}
	
	/**
	* Sets the pointer(index) to the next program in the F-preference list if present else to -1<br> 
	* It also changes the program its waiting for
	*/
	public void nextFVirtualProgramme(){
		appliedUpto++;												/** @note to Pranjal: Maybe you should call the function "setWaitListedFor( preferenceList.get(appliedUpto))"
													so that when this function is called from galeShapley class, the current waitListed Programme also gets updated automatically*/
		if(appliedUpto < fPreferenceList.size() )
		{
			setWaitListedFor(fPreferenceList.get(appliedUpto));		//@anmol: I think this should be in VirtualProgramme as when applying to the next programme we can get rejected so only the
		}															//programme knows we are waitlisted or rejected
		//Reappear for JEE
		if(appliedUpto==fPreferenceList.size()){
			appliedUpto=-1; //-1 means reappear next year
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/**********************************************************Functions for MeritOrder(Specific)******************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

	//Function for adding preferences to the preference list

	/**
	* Adds program choices provided into a list with some minor changes required for MeritOrder(task2)<p>
	* Do not take care of de-reservation but splits the program for non-GE categories.
	* @param choice An ArrayList of a single VirtualProgramme for all the 8 categories
	*/
	public void addPreference_task2(ArrayList<VirtualProgramme> choice) {
		if(category.equals("GE")) { 
			if(!pdStatus){
				preferenceList.add(choice.get(0));
			}
			else {
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(4));
			}
		}
		else if(category.equals("OBC")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(1));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(1));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(5));
			}
		}
		else if(category.equals("SC")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(2));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(2));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(6));
			}
		}
		else if(category.equals("ST")) {
			if(!pdStatus){
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(3));
			}
			else{
				preferenceList.add(choice.get(0));
				preferenceList.add(choice.get(3));
				preferenceList.add(choice.get(4));
				preferenceList.add(choice.get(7));
			}
		}
		if(dsStatus){
			dsPreferenceList.add(choice.get(0));
		}
		if(!nationality){
			if(!pdStatus){
				fPreferenceList.add(choice.get(0));
			}
			else{
				fPreferenceList.add(choice.get(0));
			}
		}
	}

}