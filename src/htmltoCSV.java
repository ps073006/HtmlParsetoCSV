import java.io.*;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class htmltoCSV {
	private static void generateCodeCoverageCSV(String sFileName, String testCaseName, int[] LineCoverage, int[] BranchCoverage){
		try
		{   
			//String sFileName = "c:\\MRtest\\Add_values.csv";
			FileWriter writer = new FileWriter(sFileName,true);
		    File file = new File(sFileName);
		    if ( !file.exists() ){
		    	file.createNewFile();	
		    }
	            
		    writer.append("Testcase");
		    writer.append(';');
		    writer.append("LineCoverage");
		    writer.append(';');
		    writer.append("branchCoverage");
		    writer.append('\n');
		    
		    writer.append(testCaseName);
		    writer.append(';');
		    writer.append('[');
		    for(int i=0;i<LineCoverage.length;i++){
			 
			    writer.append(Integer.toString(LineCoverage[i]) );
			    if(i < LineCoverage.length-1){
			        writer.append(',');
			    }
		    }  
		    writer.append(']');
		    writer.append(';');
		    writer.append('[');
		    for(int j=0;j<BranchCoverage.length;j++){
			 
			    writer.append(Integer.toString(BranchCoverage[j]));
			    if(j < BranchCoverage.length-1){
			        writer.append(',');
			    }
		    }   
		    writer.append(']');
		    writer.append('\n');
				
		    writer.flush();
		    writer.close();
			
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		}		
	}
public final static void main(String[] args) throws Exception{

		//String args[0]="C:\\MRtest\\AOIS_1\\addition\\testJava\\src\\Test\\";
		String url = args[0]+"\\MethodCollection2.html";
		File input = new File(url);
		Document page = Jsoup.parse(input, null);//Jsoup.connect(url).get();
		//String args[1]="add_values(INT[])";
		Element elements = page.select("a:contains("+ args[1]+")").first().parent().parent();
		//getting line number
		String href = elements.child(0).child(0).attr("href");
		String Lineref = href.substring(href.indexOf('#') + 1);
		int LineStart = Integer.parseInt(Lineref.substring(1));
		//getting total lines
		String tdElements = elements.select("td[class=ctr2]").get(3).toString();
		int totalLine = Integer.parseInt(tdElements.substring(tdElements.indexOf("</td>", 1)-1,tdElements.indexOf("</td>", 1)));
		
		//coverage page
		String url1 = args[0]+"\\MethodCollection2.java.html";
		File input1 = new File(url1);
		Document page1 = Jsoup.parse(input1, null);
		int count = 0;
		int[] lineCoverage = new int[totalLine];
		int[] branchcoverage1 = null;
		int[] AllBranch = null;
		
		for(int i = 0; i<totalLine;i++){
			int line = LineStart + count;
			Element element = page1.getElementById("L"+line);
			count++;
			
			if(element== null){
				i--;
			}
			else{
				//Line coverage part
				String getAllCov = element.attr("class").toString();
				if(getAllCov.startsWith("fc")){
					lineCoverage[i] = 1;
				}
				else if(getAllCov.startsWith("nc")){
					lineCoverage[i] = 0;
				}
				
				//branch Coverage part
				//if(getAllCov.endsWith("bfc")||getAllCov.endsWith("bnc")){
					//int getCov =Integer.parseInt(element.attr("title").toString().replaceAll("[^0-9]+", " ").trim());
				//	branchcoverage1 = new int[getCov];
				//	for(int j=0;j<getCov;j++){
				if(getAllCov.endsWith("bfc") || getAllCov.endsWith("bpc")){
							branchcoverage1 = new int[1];
							branchcoverage1[0] = 1;
							AllBranch = ArrayUtils.addAll(AllBranch, branchcoverage1);
						}
				else if (getAllCov.endsWith("bnc")){
							branchcoverage1 = new int[1];
							branchcoverage1[0] = 0;
							AllBranch = ArrayUtils.addAll(AllBranch, branchcoverage1);
						}
					//}
					
					//AllBranch = ArrayUtils.addAll(AllBranch, branchcoverage1);
			//	}
				/*else if(getAllCov.endsWith("bpc")){
					//for bpc part
					String[] getPartialCov = element.attr("title").toString().replaceAll("[^0-9]+", " ").split(" ");
					branchcoverage1 = new int[Integer.parseInt(getPartialCov[1])];
					for(int j=0;j<branchcoverage1.length;j++){
						if(j < Integer.parseInt(getPartialCov[0])){
							branchcoverage1[j] = 0;
						}
						else {
							branchcoverage1[j] = 1;
						}
					}
					AllBranch = ArrayUtils.addAll(AllBranch, branchcoverage1);	
					//System.out.println(getPartialCov);
				}*/
			}
		}
		
		//String classname = element.tagName();// attr("class");
		//System.out.println(classname);
	
	//	String[] segments = args[0].split("AOIS_1", 3);
		// Grab the last segment
		//int document = args[0].lastIndexOf("\\");
		String sFileName = "c:\\MRtest\\"+args[3]+"\\"+args[1]+"_Coverage.csv";
		//String args[2] = "addition";
		generateCodeCoverageCSV(sFileName,args[2], lineCoverage, AllBranch);
		
	 
	   
	}

}
