package CasualCoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.*;



public class FileInterface {
	
	static FileInterface p = new FileInterface();
	
	public static void main(String[] args) throws IOException {
	
		FastFileSearch x = p.new FastFileSearch();
		x.test_cases();
	}
	
	
	public boolean readFromFile( String filePath) throws IOException{		
		BufferedReader file_contents = null;
		try {
			file_contents = new BufferedReader(new FileReader( filePath ));
		} catch (FileNotFoundException e) {		
			e.printStackTrace();// Auto-generated catch block
			return false;
		}
		
		try {
			String line = file_contents.readLine();
		
			int i_line = 0;
			while(null!=line){
				i_line++;
				String[] words = line.split(" ");
				System.out.print("Line "+i_line+": ");
				for(String w:words)
					System.out.print( w +" ");			
				System.out.println();			
				line = file_contents.readLine();
			}
			file_contents.close();
			System.out.println("There are totally "+i_line+" lines, and file has been processed");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}//read a sentence of this file
		
		return true;	
	}

	public boolean writeToFile( String filePath){
		PrintWriter output = null;
		try {
			output = new PrintWriter( new FileWriter(filePath) );
		} catch (IOException e) {		
			e.printStackTrace(); //  Auto-generated catch block
			return false;
		}
		
		output.println("Hello Word!");
		output.println("Good luck!");
		
		output.close();
		return true;
	}
	
	
	/**
	 * Find all the files 1) whose name wild card matches the given "name", 2) with all properties indicating by "properties"
	 * 
	 * Example:
	 * root |-- a		|-- publications		|-- a.pdf
	 * 		|			|						|-- b.pdf
	 * 		|			|-- publications.java
	 * 		|
	 * 		|-- b		|-- icons				|-- email.png
	 * 		|			|						|-- facebook.png
	 *  	|			|						|-- linkedin.png
	 *  	|			|
	 * 		|			|-- images				|-- temp.jpg
	 * 		|			|						|-- tgbt.png
	 * 		|			|
	 * 		|			|-- tksgdt				|-- a.bib
	 * 		|			|-- icons.png
	 * 		|			|-- images.png
	 * 		|			|-- tksgdt.bib
	 * 		|
	 *  	|-targetgkt	|-- math				|-- math.tex
	 *  	|			|						|-- math.log
	 *  	|			|
	 *  	|			|-- tagt				|-- targqt.m
	 *  	|			|-- math.pdf
	 *  	|			|-- tagt.eps
	 *  	|-- a.txt
	 * 		|-- b.dvi
	 *  	|-- targetgkt.pdf
	 * 
	 * */
	public class FastFileSearch{
		public FastFileSearch(){}
		
		public void test_cases() throws IOException{
			String root = "C:/zfeng/TrecJava/Algorithms/testing_cases/fastFileSearch/";
			String name = "t*g?t";//"target";
			List<String[]> properties = new LinkedList<>();
			
			List<String> paths = qualifiedFiles( root, name, properties);
			for(String p:paths)
				System.out.println( p );
		}
		
		public List<String> qualifiedFiles(String root, String name, List<String[]> properties) throws IOException{
			List<String> res = new LinkedList<String>();
			File file = new File( root );
			if( !file.exists() )
				return res;
			
			File[] allFiles = file.listFiles();
			for(File f:allFiles){
				if( !f.isDirectory() ){
					String fname = f.getName().toLowerCase();
					int lastDotPos = fname.lastIndexOf('.');
					String fnameWithoutExt = -1== lastDotPos?fname:fname.substring(0, lastDotPos);
					if( isWildCardmatch( fnameWithoutExt, name ) ){
						BasicFileAttributes att_basic = null;//time, size, is (hidden, directory, ...)
						FileOwnerAttributeView att_owner = null;
//						PosixFileAttributes att_posix = null;//owner, permission
						DosFileAttributes att_dos = null;//is hidden, archive, readonly,...
						try {
							Path nio_filePath = Paths.get( f.getPath() );
							att_basic = Files.readAttributes( nio_filePath, BasicFileAttributes.class);
							att_owner = Files.getFileAttributeView(nio_filePath,FileOwnerAttributeView.class);
//							att_posix = Files.getFileAttributeView(nio_filePath, PosixFileAttributeView.class).readAttributes();
							att_dos = Files.readAttributes( nio_filePath, DosFileAttributes.class);
						} catch (IOException e) {
							e.printStackTrace();//Auto-generated catch block
							continue;
						}
						
						System.out.println( att_basic.size() );
						boolean Properties_Satisfied = true;
						for(String[] strs:properties){
							switch( strs[0]){
							case "Owner":
								if( !strs[1].equals( att_owner.getOwner().getName() ))
									Properties_Satisfied = false;
								break;
							case "Creation Time":
								String data = att_basic.creationTime().toString();//att_posix.lastAccessTime(), att_posix.lastModifiedTime()
								int T_index = data.indexOf('T');
								int D_index = data.indexOf('.');
								String date = data.substring(0,T_index);
								String time = data.substring(T_index+1, D_index);
								if( !(strs[1].equals(date) && strs[2].equals(time)))
									Properties_Satisfied = false;
								break;
							case "Size":
								if( att_basic.size() > Integer.parseInt(strs[1]))
									Properties_Satisfied = false;
								break;
							case "IsReadOnly":
								if( att_dos.isReadOnly() ){
									if( !strs[1].equals("Yes"))
										Properties_Satisfied = false;
								}else{
									if( strs[1].equals("Yes"))
										Properties_Satisfied = false;
								}
								break;
							default:
								Properties_Satisfied = false;
								break;
							}
//							att_posix.permissions()
//							att_dos.isArchive()
//							att_dos.isHidden()
//							att_dos.isReadOnly()
//							att_dos.isSystem()
							
							if( !Properties_Satisfied )
								break;
						}
						if( Properties_Satisfied )
							res.add( f.getPath() );
					}
				}else
					res.addAll( qualifiedFiles( f.getPath(), name, properties) );
			}	
			return res;
		}
		
		public boolean isWildCardmatch( String s, String p){
			int scur = 0, pcur = 0;
			int svalid = -1, pvalid = -1;
			while( scur<s.length() ){
				if( pcur<p.length() && (s.charAt(scur)==p.charAt(pcur) || '?'==p.charAt(pcur)) ){
					scur++;
					pcur++;
				}else if( pcur<p.length() && '*'==p.charAt(pcur) ){
					svalid = scur;
					pvalid = ++pcur;
				}else if( svalid >-1 ){
					scur = ++svalid;
					pcur = pvalid;
				}else
					return false;
				
			}
			while( pcur<p.length() && '*'==p.charAt(pcur) )
				pcur++;
			
			return pcur==p.length();
		}
		
	}

}
