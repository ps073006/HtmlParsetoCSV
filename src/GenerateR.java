import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;

public class GenerateR {
	private static void GenerateR(String sFileName, String Methodname, ArrayList MRname, ArrayList meanSCMD, ArrayList meanBCMD, ArrayList MRfailureRate){
		try
		{   
			//String sFileName = "c:\\MRtest\\Add_values.csv";
			FileWriter writer = new FileWriter(sFileName,true);
		    File file = new File(sFileName);
		    if ( !file.exists() ){
		    	file.createNewFile();	
		    }

		  for(int i=0; i<MRname.size();i++ )  {
			  	writer.append(Methodname);
			    writer.append(',');
			    writer.append(String.valueOf(MRname.get(i)));
			    writer.append(',');
			    writer.append("Mean SCMD =");
			    writer.append(String.valueOf(meanSCMD.get(i))); 
			    writer.append(',');
			    writer.append("Mean BCMD =");
			    writer.append(String.valueOf(meanBCMD.get(i)));
			    writer.append(',');
			    writer.append("Failure Detection Rate =");
			    writer.append(String.valueOf(MRfailureRate.get(i)));
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
			CSVReader csvReader = new CSVReader(new FileReader(args[0]), ',');
			String[] col;
	        ArrayList MRname= new ArrayList();
	        ArrayList meanSCMD= new ArrayList();
	        ArrayList meanBCMD= new ArrayList();
	        ArrayList MRfailureRate= new ArrayList();
	        int countAdd = 0, AddSCMD = 0, AddBCMD = 0, Addfailure = 0;
	        int countPerm = 0, PermSCMD = 0, PermBCMD = 0, Permfailure = 0;
	        int countMul = 0, MulSCMD = 0, MulBCMD = 0, Mulfailure = 0;
	        int countInc = 0, IncSCMD = 0, IncBCMD = 0, Incfailure = 0;
	        int countExc = 0, ExcSCMD = 0, ExcBCMD = 0, Excfailure = 0;
	        int countInv = 0, InvSCMD = 0, InvBCMD = 0, Invfailure = 0;
	        
	        while ((col = csvReader.readNext()) != null) {
	            try {
	            	String text = col[1];
	            	if(col[1].toLowerCase().equals("addition")){
	            		
	            		AddSCMD = AddSCMD + Integer.parseInt(col[2]);
	            		AddBCMD = AddBCMD + Integer.parseInt(col[3]);
	            		Addfailure = Addfailure + Integer.parseInt(col[4]);
	            		countAdd++;
	            		if(countAdd == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) AddSCMD/countAdd);
	            			meanBCMD.add( (float) AddBCMD/countAdd);
	            			MRfailureRate.add( (float) Addfailure/countAdd);
	            		}
	            	}
	            	else if(col[1].toLowerCase().equals("permutation")){
	            		
	            		PermSCMD = PermSCMD + Integer.parseInt(col[2]);
	            		PermBCMD = PermBCMD + Integer.parseInt(col[3]);
	            		Permfailure = Permfailure + Integer.parseInt(col[4]);
	            		countPerm++;
	            		if(countPerm == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) PermSCMD/countPerm);
	            			meanBCMD.add( (float) PermBCMD/countPerm);
	            			MRfailureRate.add( (float) Permfailure/countPerm);
	            		}
	            	}
	            	else if(col[1].toLowerCase().equals("multiplication")){
	            		
	            		MulSCMD = MulSCMD + Integer.parseInt(col[2]);
	            		MulBCMD = MulBCMD + Integer.parseInt(col[3]);
	            		Mulfailure = Mulfailure + Integer.parseInt(col[4]);
	            		countMul++;
	            		if(countMul == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) MulSCMD/countMul);
	            			meanBCMD.add( (float) MulBCMD/countMul);
	            			MRfailureRate.add( (float) Mulfailure/countMul);
	            		}
	            	}
	            	else if(col[1].toLowerCase().equals("inclusion")){
	            		
	            		IncSCMD = IncSCMD + Integer.parseInt(col[2]);
	            		IncBCMD = IncBCMD + Integer.parseInt(col[3]);
	            		Incfailure = Incfailure + Integer.parseInt(col[4]);
	            		countInc++;
	            		if(countInc == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) IncSCMD/countInc);
	            			meanBCMD.add( (float) IncBCMD/countInc);
	            			MRfailureRate.add( (float) Incfailure/countInc);
	            		}
	            	}
	            	else if(col[1].toLowerCase().equals("exclusive")){
	            		
	            		ExcSCMD = ExcSCMD + Integer.parseInt(col[2]);
	            		ExcBCMD = ExcBCMD + Integer.parseInt(col[3]);
	            		Excfailure = Excfailure + Integer.parseInt(col[4]);
	            		countExc++;
	            		if(countExc == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) ExcSCMD/countExc);
	            			meanBCMD.add( (float) ExcBCMD/countExc);
	            			MRfailureRate.add( (float) Excfailure/countExc);
	            		}
	            	}
	            	else if(col[1].toLowerCase().equals("inversion")){
	            		
	            		InvSCMD = InvSCMD + Integer.parseInt(col[2]);
	            		InvBCMD = InvBCMD + Integer.parseInt(col[3]);
	            		Invfailure = Invfailure + Integer.parseInt(col[4]);
	            		countInv++;
	            		if(countInv == Integer.parseInt(args[2])){
	            			MRname.add(col[1]);
	            			meanSCMD.add( (float) InvSCMD/countInv);
	            			meanBCMD.add( (float) InvBCMD/countInv);
	            			MRfailureRate.add( (float) Invfailure/countInv);
	            		}
	            	}
	            } catch (NumberFormatException e) {
	            }
	        }
	        //System.out.println("SCMD = "+ (float) MeanSCMD/(count+1) +" BCMD = " +(float) MeanBCMD/(count+1) );
	        String sFileName = "C:\\MRtest\\Coefficient_Report.csv";
			//String args[2] = "addition";
	        GenerateR(sFileName, args[1], MRname, meanSCMD, meanBCMD, MRfailureRate);
		}catch(IOException e)
		{
		     e.printStackTrace();
		}		
	}

}
