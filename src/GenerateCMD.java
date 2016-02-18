import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import au.com.bytecode.opencsv.*;

public class GenerateCMD {
	private static void GenerateCMD(String sFileName, String faultName, ArrayList MRname, ArrayList SCMD, ArrayList BCMD, ArrayList failure){
		try
		{   
			//String sFileName = "c:\\MRtest\\Add_values.csv";
			FileWriter writer = new FileWriter(sFileName,true);
		    File file = new File(sFileName);
		    if ( !file.exists() ){
		    	file.createNewFile();	
		    }
	            
		    writer.append("Fault Name");
		    writer.append(',');
		    writer.append("MR Name");
		    writer.append(',');
		    writer.append("SCMD");
		    writer.append(',');
		    writer.append("BCMD");
		    writer.append(',');
		    writer.append("Failure");
		    writer.append('\n');
		  for(int i=0; i<MRname.size();i++ )  {
			  	writer.append(faultName);
			    writer.append(',');
			    writer.append(String.valueOf(MRname.get(i)));
			    writer.append(',');
			    writer.append(String.valueOf(SCMD.get(i))); 
			    writer.append(',');
			    writer.append(String.valueOf(BCMD.get(i)));
			    writer.append(',');
			    writer.append(String.valueOf(failure.get(i)));
			    writer.append('\n');  
		  }
		    
		    
		    writer.flush();
		    writer.close();
			
			
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		}		
	}
public final static void main(String[] args) throws Exception{
	//BufferedReader reader = null;
	try
	{   
		//String sFileName = "c:\\MRtest\\Add_values.csv";
		//reader = new BufferedReader(args[0]);
		CSVReader csvReader = new CSVReader(new FileReader(args[0]), ';');
		String[] col;
        int res = 0;
        ArrayList MRname= new ArrayList();
        ArrayList SCMD= new ArrayList();
        ArrayList BCMD= new ArrayList();
        ArrayList MRfailure= new ArrayList();
        int[] StatementArr = null;
        int SCMDadd = 0;
        int BCMDadd = 0;
        int[] branchArr = null;
        int count = 0;
        while ((col = csvReader.readNext()) != null) {
            try {
                res++;
                if(res==2){
                	StatementArr = Arrays.stream(col[1].substring(1, col[1].length()-1).split(","))
                		    .map(String::trim).mapToInt(Integer::parseInt).toArray();
                	branchArr = Arrays.stream(col[2].substring(1, col[2].length()-1).split(","))
                		    .map(String::trim).mapToInt(Integer::parseInt).toArray();
                 // System.out.println(Arrays.toString(branchArr));
                	//System.out.println(col[1]);
                	//MeanSCMD[count] = 0;
                	//SCMD[count] = 0;
                	//BCMD[count] = 0;
                }
                else if(res != 2 && res%2 == 0){
                	SCMDadd = 0;
                	BCMDadd = 0;
                	MRname.add( col[0]);
                	int[] MRStatementArr = Arrays.stream(col[1].substring(1, col[1].length()-1).split(","))
                		    .map(String::trim).mapToInt(Integer::parseInt).toArray();
                	if((StatementArr.length != 0) && (MRStatementArr.length != 0) &&(StatementArr.length == MRStatementArr.length)){
                		for(int i=0;i < MRStatementArr.length;i++ ){
                			SCMDadd = SCMDadd + Math.abs(StatementArr[i] - MRStatementArr[i]);
                			
                		}
                		SCMD.add(SCMDadd);
                		//System.out.println(count +" = "+MeanSCMD);
                		   
                	}
                	int[] MRBranchArr = Arrays.stream(col[2].substring(1, col[2].length()-1).split(","))
                		    .map(String::trim).mapToInt(Integer::parseInt).toArray();
                	if((branchArr.length != 0) && (MRBranchArr.length != 0) &&(branchArr.length == MRBranchArr.length)){
                		for(int i=0;i < MRBranchArr.length;i++ ){
                			BCMDadd = BCMDadd + Math.abs(branchArr[i] - MRBranchArr[i]);
                		}
                		BCMD.add(BCMDadd);
                		//System.out.println(count +" = "+ MeanBCMD);
                		   
                	}
                	
                	
                	if(SCMDadd!=0 || BCMDadd!=0 ){
                		MRfailure.add(1);
                	}
                	else {
                		MRfailure.add(0);
                	}
                	count++;
                }
            } catch (NumberFormatException e) {
            }
        }
        //System.out.println("SCMD = "+ (float) MeanSCMD/(count+1) +" BCMD = " +(float) MeanBCMD/(count+1) );
        String sFileName = args[1]+"_CMD.csv";
		//String args[2] = "addition";
        GenerateCMD(sFileName, args[2], MRname, SCMD, BCMD, MRfailure);
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	}		
}
}

