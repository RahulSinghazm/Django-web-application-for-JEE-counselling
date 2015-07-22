import java.util.*;
import java.io.*;

/**
* <h1>GaleShapleyAdmission Algorithm</h1>
* This class contains various hashmaps and arraylists for storing data.<br>
* It also contains {@link #startAlgorithm()} method for starting the whole algorithm(visit the method for algo details).
*/
public class GaleShapleyAdmission
{
	private Map<String,Candidate> candidateMap = new HashMap<String,Candidate>();
	private Map<String,Candidate> dsCandidateMap = new HashMap<String,Candidate>();
	private Map<String,Candidate> fCandidateMap = new HashMap<String,Candidate>();
	private ArrayList<String> orderedCandidate = new ArrayList<String>();
	private Map<String , ArrayList<VirtualProgramme> > programMap = new HashMap<String , ArrayList<VirtualProgramme> >();						//the program map contains the program code as the key and the arrayList of virtual program as its key value
	private Map<String , ArrayList<Candidate> > instiAppliedMap = new HashMap<String , ArrayList<Candidate> >();
	private MeritList_task1[] meritList = new MeritList_task1[8];
	private MeritList_task1[] categoryList = new MeritList_task1[4];

	//Candidate tempCandidate;
	String tempId;
	String tempCategory;
	String tempPDStatus;
	String tempChoices;
	String garbage;
	boolean booleanTempPDStatus;
	boolean booleanTempDSStatus;
	boolean booleanTempNationality;

	/**
	* This is the constructor which initializes empty meritlists
	*/
	public GaleShapleyAdmission(){
		for(int i=0;i<8;i++){
			meritList[i] = new MeritList_task1();
		}
		for(int i=0;i<4;i++){
			categoryList[i] = new MeritList_task1();
		}
	}
	
	public Candidate getCandidate(Candidate candidate){
		return candidateMap.get(candidate.getUniqueID());
	}

	public ArrayList<VirtualProgramme> getProgram(VirtualProgramme program){
		return programMap.get(program.getProgramID());
	}

	/**
	* Uses modified built-in sort method to arrange list of candidates in increasing order of rank
	* @param rankList List to be sorted
	*/
	public void sortList(ArrayList<Candidate> rankList){
		Collections.sort(rankList, new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate candidate1, Candidate candidate2)
	        {
	        	if(meritList[0].getRank(candidate1.getUniqueID()) < meritList[0].getRank(candidate2.getUniqueID()))
	        		return -1;
	        	else
	        		return 1;
	        }
    	});
	}
	/**
	* Prints the final result in a file output_task1.csv and also catches IOException inside.<br>
	* <pre><code>
	* PrintWriter writer = new PrintWriter("output_task1.csv", "UTF-8");
	* </code></pre>
	* @see IOException
	*/
	public void printResult(){
		try{
			PrintWriter writer = new PrintWriter("output_task1.csv", "UTF-8");
			for (int i=0;i<orderedCandidate.size();i++){
				if(candidateMap.get(orderedCandidate.get(i)).getAppliedUpto()!=-1){
					writer.println(orderedCandidate.get(i) + "," + candidateMap.get(orderedCandidate.get(i)).getWaitListedFor().getProgramID() + "," + candidateMap.get(orderedCandidate.get(i)).getWaitListedFor().getCategory() );
				}
				else{
					writer.println(orderedCandidate.get(i) + ",-1");
				}
			}
			writer.close();
		} catch(IOException e){
			System.exit(1);
		}
	}

	/**
	* It does the following:
	* <ul>
	* 	<li>Reads all the three files and stores relevent data</li>
	* 	<li>Starts the GaleShapleyAlgorithm:
	* 		<ol>
	* 			<li>Firstly DS Candidates Apply</li>
	* 			<li>All the non-waitlisted candidates apply except Foreign candidates</li>
	* 			<li>Those who are not waitlisted gets into rejectionList</li>
	* 			<li>All the non-waitlisted except Foreign candidates i.e. those in rejectionList apply again untill rejectionlist is empty</li>
	* 			<li>Foreign Candidates apply</li>
	* 		</ol></li>
	* 	<li>Prints the result</li>
	* </ul>
	*/
	public void startAlgorithm()
	{

/** To read in the rank list of candidates and create the merit lists of different categories */
		try{
			Scanner sd = new Scanner(new File("../test-cases/11/ranklist.csv")).useDelimiter(",|\n");
		
		String tempId;
		int tempGender,tempCML,tempGE,tempOBC,tempSC,tempST,tempCML_PD,tempGE_PD,tempOBC_PD,tempSC_PD,tempST_PD;
		garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();
		garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();
		garbage = sd.next();garbage = sd.next();
		while(sd.hasNext())					/** @debug Write proper syntax */
		{
			tempId = sd.next();
	  		tempGender = sd.nextInt();
	  		tempCML = sd.nextInt();
	  		tempGE = sd.nextInt();
	  		tempOBC = sd.nextInt();
	  		tempSC = sd.nextInt();
	  		tempST = sd.nextInt();
	  		tempCML_PD = sd.nextInt();
	  		tempGE_PD = sd.nextInt();
	  		tempOBC_PD = sd.nextInt();
	  		tempSC_PD = sd.nextInt();
	  		tempST_PD = sd.nextInt();
			if(tempGE != 0)
			{
				meritList[0].addCandidate(tempId , tempGE);
				meritList[1].addCandidate(tempId , tempGE);
				meritList[4].addCandidate(tempId , tempGE);
				meritList[5].addCandidate(tempId , tempGE);
				categoryList[3].addCandidate(tempId , tempGE);
			}
			
			if(tempOBC != 0)
			{
				meritList[1].addCandidate(tempId , tempOBC);
				categoryList[0].addCandidate(tempId , tempOBC);
			}

			if(tempSC != 0)
			{
				meritList[2].addCandidate(tempId , tempSC);
				meritList[6].addCandidate(tempId , tempSC);
				categoryList[1].addCandidate(tempId , tempSC);
			}							

			if(tempST != 0)
			{
				meritList[3].addCandidate(tempId , tempST);
				meritList[7].addCandidate(tempId , tempST);
				categoryList[2].addCandidate(tempId , tempST);
			}				

			if(tempGE_PD != 0)
			{
				meritList[4].addCandidate(tempId , tempGE_PD);
			}

			if(tempOBC_PD != 0)
			{
				meritList[5].addCandidate(tempId , tempOBC_PD);
				categoryList[0].addCandidate(tempId , tempOBC_PD);
			}

			if(tempSC_PD != 0)
			{
				meritList[6].addCandidate(tempId , tempSC_PD);
				categoryList[1].addCandidate(tempId , tempSC_PD);
			}
			if(tempST_PD != 0)
			{
				meritList[7].addCandidate(tempId , tempST_PD);
				categoryList[2].addCandidate(tempId , tempST_PD);
			}
		}
		sd.close();
		} catch(IOException e){
			System.exit(1);
		}
		//All merit lists checked
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/** To read in all available programs, create their respective virtual programmes*/
		String programCode;
		String programName;
		String instiCode;
		int ge,obc,sc,st,ge_pd,obc_pd,sc_pd,st_pd;
		try{
		 Scanner sb = new Scanner(new File("../test-cases/11/programs.csv")).useDelimiter(",|\n");
		garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();
		garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();
		while(sb.hasNext())
		{
	  	//	inProgrammes>>garbage>>programCode>>programName>>ge>>obc>>sc>>st>>ge_pd>>obc_pd>>sc_pd>>st_pd;
	  		garbage = sb.next();
	  		programCode = sb.next();
	  		instiCode = programCode.substring(0,1);
	  		programName = sb.next();
	  		ge = sb.nextInt();
	  		obc = sb.nextInt();
	  		sc = sb.nextInt();
	  		st = sb.nextInt();
	  		ge_pd = sb.nextInt();
	  		obc_pd = sb.nextInt();
	  		sc_pd = sb.nextInt();
	  		st_pd = sb.nextInt();
	  		instiAppliedMap.put(instiCode , new ArrayList<Candidate>());
			programMap.put(programCode , new ArrayList<VirtualProgramme>());
			programMap.get(programCode).add(new VirtualProgramme("GE",false,ge,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC",false,obc,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("SC",false,sc,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("ST",false,st,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("GE_PD",true,ge_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC_PD",true,obc_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("SC_PD",true,sc_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("ST_PD",true,st_pd,meritList,programCode,instiCode));
		}
		sb.close();
		} catch(IOException e){
			System.exit(1);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/** To Read in the candidate list and their choices */
		try{
		 Scanner s = new Scanner(new File("../test-cases/11/choices.csv")).useDelimiter(",|\n");
		garbage = s.next();garbage = s.next();garbage = s.next();garbage = s.next();
		while(s.hasNext())				/** Write the correct syntax for reading in from the files */
		{
			tempId = s.next();
			tempCategory = s.next();
			tempPDStatus = s.next();
			tempChoices = s.next();
			if(tempPDStatus.equals("Y"))
				booleanTempPDStatus = true;
			else 
				booleanTempPDStatus = false;
			
			if(tempCategory.equals("DS")){
				booleanTempDSStatus = true;
				if(categoryList[3].getRank(tempId)==-1)
					booleanTempDSStatus = false;
				if(categoryList[0].getRank(tempId)!=-1)
					tempCategory="OBC";
				else if(categoryList[1].getRank(tempId)!=-1)
					tempCategory="SC";
				else if(categoryList[2].getRank(tempId)!=-1)
					tempCategory="ST";
				else
					tempCategory="GE";
			}
			else 
				booleanTempDSStatus = false;

			if(tempCategory.equals("F"))
				booleanTempNationality = false;
			else 
				booleanTempNationality = true;

			Candidate tempCandidate = new Candidate(tempId,tempCategory,booleanTempPDStatus,booleanTempDSStatus,booleanTempNationality);
			orderedCandidate.add(tempId);
			String[] choices = tempChoices.split("_");
			for(int i=0;i<choices.length;i++){
				tempCandidate.addPreference_task1(programMap.get(choices[i]));
			}
			if(booleanTempDSStatus)
				tempCandidate.setWaitListedFor(tempCandidate.getDSChoice(0));
			else if(booleanTempNationality)
				tempCandidate.setWaitListedFor(tempCandidate.getChoice(0));
			else
				tempCandidate.setWaitListedFor(tempCandidate.getFChoice(0));
			candidateMap.put(tempId, tempCandidate);
			if(booleanTempDSStatus){
				dsCandidateMap.put(tempId, tempCandidate);
			}
			if(!booleanTempNationality)
				fCandidateMap.put(tempId, tempCandidate);
		}
		s.close();
		} catch(IOException e){
			System.exit(1);
		}
		//Checked, everything OK till here
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the GaleShapley Algorithm***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		HashMap<String , Candidate> rejectionList = new HashMap<String , Candidate>();

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the DS Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boolean dsCompleted=false;
		while(dsCompleted == false){
			for (Map.Entry<String , Candidate> entry : dsCandidateMap.entrySet())
			{
				if(candidateMap.get(entry.getKey()).getAppliedUpto()!=-1){
					if(getCandidate(entry.getValue()).currentDSVirtualProgramme().getQuota()>0){
						instiAppliedMap.get(candidateMap.get(entry.getKey()).currentDSVirtualProgramme().getInstiID()).add(entry.getValue());
					}
					else{
						rejectionList.put(entry.getKey(), entry.getValue());
					}
				}
			}

			for (Map.Entry<String , ArrayList<Candidate> > entry : instiAppliedMap.entrySet())
			{
				sortList(entry.getValue());
				int seatsGiven=2;
				for(int i=2;i<entry.getValue().size();i++){
					if(meritList[0].getRank(entry.getValue().get(i).getUniqueID())==meritList[0].getRank(entry.getValue().get(i-1).getUniqueID()))
						seatsGiven++;
					else
						break;
				}
				for(int i=seatsGiven; i<entry.getValue().size(); i++){
					rejectionList.put(entry.getValue().get(i).getUniqueID(), entry.getValue().get(i));
				}
				entry.getValue().clear();
			}

			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
					candidateMap.get(entry.getKey()).nextDSVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
			}
			if(rejectionList.size()==0){dsCompleted = true;}
			rejectionList.clear();
		}
		for(Map.Entry<String , Candidate> entry : dsCandidateMap.entrySet()){
			if(candidateMap.get(entry.getKey()).getAppliedUpto()==-1){
				candidateMap.get(entry.getKey()).setDSStatus(false);
				candidateMap.get(entry.getKey()).setAppliedUpto(0);
			}
		}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the General Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		boolean completed=false;
		while(completed == false)
		{
			for (Map.Entry<String , Candidate> entry : candidateMap.entrySet())
			{
				if(entry.getValue().getAppliedUpto()!=-1 && entry.getValue().getNationality() && !(entry.getValue().getDSStatus())){
					programMap.get(entry.getValue().currentVirtualProgramme().getProgramID()).get(entry.getValue().currentVirtualProgramme().getMeritListIndex()).receiveApplication(entry.getValue() ,rejectionList);
				}
			}
			/** Till Now , all the candidates have applied to the programs. now the programs will either keep their applicants in the wait list or reject them (and add to the rejection list).*/
			
			for (Map.Entry<String , ArrayList<VirtualProgramme> > entry : programMap.entrySet())
			{
				for (ListIterator<VirtualProgramme> it = entry.getValue().listIterator(); it.hasNext(); )	/**listIterator with no arguements points to the beginning of the list*/
				 {
	  			 
	  			  it.next().filter(rejectionList);						/** Make sure that you add rejectionList parameter in the filter function for VirtualProgrammes */
				}		
			}
						/** @doubt Can java variables be redefined? as "entry" variable here */
			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
					candidateMap.get(entry.getKey()).nextVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
			}
			if(rejectionList.size()==0){completed = true;}		/** If no one is rejected in this iteration, rejectionList will be empty and the iteration can be terminated*/
																/** Iterations are terminated when , for all candidates:
																	1). When he reaches the end of his preference list and can not apply to any more programmes
																	2). When he is in Waitlist for some Program.
																When all the candidates satisfy one of the above two, the algoritihm can be safely terminated.
																Note that when a candidate reaches the end of his Preference list, he no longer applies to any Programme. 
																Thus he can not be added to rejectionList at all.(To be added in rejectionList, one needs to apply to some Programme in the first place.)
																Thus the algorithm is to be terminated iff the rejectionList is empty.*/
			rejectionList.clear();
							/**Clear the rejectionList before the next Iteration*/
		}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the Foreign Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		boolean fCompleted=false;
		while(fCompleted == false){
			for (Map.Entry<String , Candidate> entry : fCandidateMap.entrySet())
			{
				if(candidateMap.get(entry.getKey()).getAppliedUpto()!=-1){
					programMap.get(candidateMap.get(entry.getKey()).currentFVirtualProgramme().getProgramID()).get(entry.getValue().currentFVirtualProgramme().getMeritListIndex()).receiveApplication(entry.getValue() ,rejectionList);
				}
			}
			for (Map.Entry<String , ArrayList<VirtualProgramme> > entry : programMap.entrySet())
			{
				entry.getValue().get(0).fFilter(rejectionList);
				entry.getValue().get(1).fFilter(rejectionList);
				entry.getValue().get(4).fFilter(rejectionList);
				entry.getValue().get(5).fFilter(rejectionList);
			}
			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
					candidateMap.get(entry.getKey()).nextFVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
			}
			if(rejectionList.size()==0){fCompleted = true;}
			rejectionList.clear();
		}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/******************************************************Now to output the final Seat allocation*******************************************************************/
		
		printResult();
	}
}