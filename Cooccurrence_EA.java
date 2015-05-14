/*
EADP-DATA Data Science Programming 

1. Description

Your program will read files containing English sentences, one sentence per line. The files will be specified 
on the command line. Once the files have been read, the program will generate a "query>" prompt. A 
query will consist of a sequence of words separated by white space and terminated by a carriage return-
-all words on a single line. The program will then generate a co-occurrence list consisting of filename, line 
number, and sentence from the input lines. Co-occurrence is when all the query words appear in the 
sentence. The program will continue to prompt for queries until the user enters "!exit". At this time, the 
program should stop accepting queries and report basic statistics regarding the number of sentences in 
the results. What you report is up to you. Your program should be able to process very large sentence 
files and many queries efficiently. It should also respond quickly to queries with empty result sets.


2. File Format

The input files contain lists of English language sentences. Here is a sample:

The house is red.
The baker said "The cakes are ready".
Don't touch the switch!

Line numbering starts at one.


3. Implementation

Implement the program described above in Java, Scala, C/C++, Python or Ruby. Do not spend more than 
two hours on this project.


4. Evaluation

Your program will be evaluated for readability, correctness, and efficient usage of data structures. Our 
tests will involve files containing up to one million sentences, as well as other edge cases.
*/

public class Solution {
	
	static Solution p = new Solution();
	
	public static void main(String[] args) throws IOException {
	// assume what input is the path of a file, which contains all the paths of the files containing English words
		args = new String[2];
		args[0] = "C:/zfeng/EA_test/paths.txt";
		args[1] = "C:/zfeng/EA_test/stop-words_english_6_en.txt";	
		
		//read the path of all files
		ArrayList<String> filenames = p.readPathOfFiles( args[0] );
		
		//read the stop words: either use the default one as in the file or set use the command
		Set<String> stopWords = p.readStopWrods( args[1]);
		
		//read the sentences in the files
		//create the dictionary of the gallery, mainly mapping a string word to an integer id, 
		//and as well as calculate the frequency of a word in the gallery and the number of sentences containing that word.
		HashMap<String, DictionaryTerm> dictionary = new HashMap<String, DictionaryTerm>();
		ArrayList<VecSentence> vec_sentences = p.readSentences(filenames, stopWords, dictionary );
		
		
		System.out.println("There are totally "+filenames.size()+" files with totally "+vec_sentences.size()+" valid sentences.");
		System.out.println("Offline processing finished!");
		System.out.println();
		
		System.out.println("Please input queries:");
		System.out.println("\t1. Words in a single query should be separated by white space;");
		System.out.println("\t2. Each query is terminated by a carriage-return;");
		System.out.println("\t2. Enter \"!exit\" to exit the query mode.");
		 
		//prompt queries
		p.onlineProcessingQueries(filenames, vec_sentences, dictionary, stopWords);
		
		System.out.println("Query mode exited.");
	}
	
	public void onlineProcessingQueries(ArrayList<String> filenames, ArrayList<VecSentence> vec_sentences, HashMap<String, DictionaryTerm> dictionary, Set<String> stopWords) throws IOException{
		String query = null;
		while(true){
			//input query in the terminal
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println();
			System.out.print("Query> ");
			query = br.readLine();
			
			if(query.equals("!exit"))
				break;
			
			String[] words = tokenizeWords( query );
			if(null==words || 0==words.length)
				continue;
			
			//convert query into sparse vector
			VecSentence vec_query = readQuery(words, stopWords, dictionary); 
			
			//estimate relevance scores between query and all sentences
			List<RelevantSentence> relevantSentences = estimateSimilarities(vec_sentences, vec_query);
			
			//Output basic statistics
			System.out.println("Answers:");
			if(null==relevantSentences || 0==relevantSentences.size() ){
				System.out.println("No relevant sentences.");
				continue;
			}
			
			System.out.println(relevantSentences.size() + " relevant sentences are found as follows (sorted in descending order of co-occurrence):");
			for(RelevantSentence s:relevantSentences){
				System.out.print("File <"+filenames.get(s.file_id)+">, ");
				System.out.print("Line number <"+s.line_id+">, ");
				System.out.print(readSpecificInputLine(filenames.get(s.file_id),s.line_id ));
				System.out.println(", and Co-occurrence =  \""+s.score+"\", ");
			}
		}
		
	}
	
	private String readSpecificInputLine(String file_path, int line_id ) throws IOException{		
		BufferedReader file_contents =  new BufferedReader(new FileReader(file_path));		
		String line = file_contents.readLine();//read a sentence of this file
		int i_line = 0;
		while(null!=line){
			i_line++;
			if( i_line==line_id )
				break;
			line = file_contents.readLine();
		}
		
		file_contents.close();
		return line;
	}
	
	public List<RelevantSentence> estimateSimilarities(ArrayList<VecSentence> vec_sentences, VecSentence vec_query){
		if( 0==vec_query.size() )
			return null;
		
		//find out the relevant sentences
		List<RelevantSentence> list = new ArrayList<RelevantSentence>();
		for(VecSentence s:vec_sentences){
			RelevantSentence sentence = estimateRelevanxce( s, vec_query);
			if( null!=sentence && sentence.score>0 ){
				list.add( sentence );
			}
		}
		
		//sort the sentences in descending order of the relevance score
		Collections.sort(list, new Comparator<RelevantSentence>(){
			public int compare(RelevantSentence a, RelevantSentence b){
				if( a.score==b.score )
					return 0;
				else
					return a.score>b.score?-1:1;
			}
		});
		
		return list;
	}
	
	public RelevantSentence estimateRelevanxce(VecSentence sentence, VecSentence query){//O(n) searching time
		if( null==query || 0==query.size() )
			return null;
		
		double score = 0;	
		Map<Integer, Double> q = new HashMap<Integer, Double>();
		for(int i=0;i<query.size();i++){
			WordTerm word = query.get(i);
			q.put(word.get_id(), word.get_val());
		}
		
		for(int j=0; j<sentence.size();j++){
			int termId = sentence.get(j).get_id();
			if( q.containsKey( termId )){
				score += q.get( termId) * sentence.get(j).get_val();
			}
		}
		
		return ( new RelevantSentence(sentence.get_file_id(), sentence.get_line_id(), score) );
	}
	
	public VecSentence readQuery(String[] words, Set<String> stopWords, HashMap<String, DictionaryTerm> dictionary){
		HashMap<Integer, Integer> query_map = new HashMap<Integer, Integer>();
		for(String w:words){			
			if( stopWords.contains(w))//remove stop words
				continue;
			
			if( dictionary.containsKey(w) ){
				int termId = dictionary.get(w).get_id();
				if( query_map.containsKey(termId)){
					query_map.put(termId, query_map.get(termId)+1);
				}else{
					query_map.put(termId, 1);
				}					
			}
		}
		
		VecSentence vec = new VecSentence();		
		for(int key:query_map.keySet()){				
			vec.add( new WordTerm(key, query_map.get(key)) );
		}
		
//		vec.sort(); //if sorting, it taks O(n lg n) time, so don't do it.
//		vec.print();
		return vec;
	}
	

	public ArrayList<VecSentence> readSentences(ArrayList<String> paths, Set<String> stopWords, HashMap<String, DictionaryTerm> dictionary ) throws IOException{
		System.out.println("Start to read files:");
		ArrayList<VecSentence> vecs = new ArrayList<VecSentence>();
		int word_id = 0;
		for(int i=0;i<paths.size();i++){
			// read the contents of each file
			BufferedReader file_contents =  new BufferedReader(new FileReader(paths.get(i)));
					
			String line = file_contents.readLine();//read a sentence of this file
			int i_line = 0;
			while(null!=line){
				i_line++;
				String[] words = tokenizeWords( line );
				if( null==words || 0==words.length ){	
					line = file_contents.readLine();
					continue;
				}
				
				//processing word by word
				//update the dictionary, and add to the sentence map
				HashMap<Integer, Integer> sent_map = new HashMap<Integer, Integer>();//record the frequency of each word within the sentence
				for(String w:words){
					word_id = addWordToDictAndSentence(w, word_id, stopWords, dictionary, sent_map);
				}
				
				//create the sparse vector of a sentence
				VecSentence vec = new VecSentence();
				vec.set_file_id(i);
				vec.set_line_id(i_line);
				for(int key:sent_map.keySet()){
					int val = sent_map.get(key);
					vec.add( new WordTerm(key,val) );
				}
				
//				vec.sort();
//				vec.print();
				vecs.add( vec );
				
				line = file_contents.readLine();
			}
			file_contents.close();
			System.out.println("\tfile <"+paths.get(i) +"> has been read in. It has "+i_line+" sentences.");
		}
		
		System.out.println("All files has been read in!");
		System.out.println();
		
		return vecs;
	}
	
	private int addWordToDictAndSentence(String w, int cur_word_id, Set<String> stopWords, HashMap<String, DictionaryTerm> dictionary, HashMap<Integer, Integer> map){
		if( stopWords.contains(w))//remove stop words
			return cur_word_id;
		
		if( dictionary.containsKey(w) ){//existing in dictionary
			int termId = dictionary.get(w).get_id();
			dictionary.get(w).set_freq( dictionary.get(w).get_freq()+1 );
			if( map.containsKey(termId) ){//existing in the sentence
				map.put(termId, map.get(termId)+1);
			}else{
				map.put(termId, 1);
				dictionary.get(w).set_ndoc( dictionary.get(w).get_ndoc()+1 );
			}			
		}else{
			int termId = cur_word_id++;
			DictionaryTerm term = new DictionaryTerm(termId,1,1);
			dictionary.put(w, term);
			map.put(termId, 1);
		}
		
		return cur_word_id;
	}
	
	private String[] tokenizeWords(String line){
		if( null==line || 0==line.length() )
			return null;
		
		String[] dilimiters = {".", ",", "!", "?", "\"", "(", ")", ":","~"};
		String str = line.toLowerCase();
		for(String dilimiter:dilimiters){
			str = str.replace(dilimiter, " ");
		}
		String[] words = str.split(" +");
		return words;
	}
	
	public Set<String> readStopWrods( String path) throws IOException{
		Set<String> words = new HashSet<String>();
		BufferedReader file_list;
		try {

			file_list = new BufferedReader(new FileReader(path));
			String row = file_list.readLine();
			while(null!=row){
				if(row.length()>0 )
					words.add( row );
				row = file_list.readLine();
			}
			file_list.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the stop word file!");
			e.printStackTrace();
		}		
		
		return words;
	}
	
	
	public ArrayList<String> readPathOfFiles( String path_file ) throws IOException{
		ArrayList<String> filenames = new ArrayList<String>();		
		BufferedReader file_list;
		try {
			file_list = new BufferedReader(new FileReader(path_file));
			String row = file_list.readLine();
			while(null!=row){
				filenames.add(row);
				row = file_list.readLine();
			}
			file_list.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the path of the file list!");
			e.printStackTrace();
		}		
		
		return filenames;
	}
	
	public class VecSentence{//sparse representation of a sentence vector
		int file_id;
		int line_id;
		ArrayList<WordTerm> vec;
		
		public VecSentence(){
			vec = new ArrayList<WordTerm>();
		}
		
		public int size(){
			return vec.size();
		}
		
		public WordTerm get(int i){
			return vec.get(i);
		}
		
		public void add(WordTerm e){
			vec.add(e);
		}
		
		public void sort(){//sort the vector elements in ascending order of term_id
		// actually not use because the complexity is O(n * ln n)
			Collections.sort(vec, new Comparator<WordTerm>(){
				public int compare( WordTerm a, WordTerm b){
					if( a.id==b.id ){
						if( a.val==b.val)
							return 0;
						else
							return a.val<b.val?-1:1;
					}else
						return a.id-b.id;
				}
			});			
		}
		
		public void set_file_id( int _id ){
			file_id = _id;
		}
		
		public void set_line_id( int _id ){
			line_id = _id;
		}
		
		public int get_file_id(){
			return file_id;
		}
		
		public int get_line_id(){
			return line_id;
		}
		
		public void print(){
			for(WordTerm e:vec){
				System.out.print(e.id+":"+e.val+" ");
			}
			System.out.println();
		}
	}
	
	public class WordTerm{
		int id; //the index of a word
		double val;//the weight of the word, ie, local or global, or the occurrence of this word in the whole sentence
		
		public WordTerm(int i, double v){
			id = i;
			val = v;
		}
		
		public int get_id(){
			return id;
		}
		
		public double get_val(){
			return val;
		}
		
		public void set_val(double _v){
			val = _v;
		}
	}
	
	public class DictionaryTerm{
		int id;//the id of a word term
		int freq;//the frequency of the word in the whole gallery
		int ndoc;//the number of documents containing this word
		
		public DictionaryTerm(int i, int f, int n){
			id = i;
			freq = f;
			ndoc = n;
		}
		
		public int get_id(){
			return id;
		}
		
		public int get_freq(){
			return freq;
		}
		
		public int get_ndoc(){
			return ndoc;
		}
		
		public void set_id(int x){
			id = x;
		}
		
		public void set_freq(int x){
			freq = x;
		}
		
		public void set_ndoc(int x){
			ndoc = x;
		}
	}
	
	public class RelevantSentence{
		int file_id;
		int line_id;
		double score;//relevant score, i.e. co-occurence
		
		public RelevantSentence(int f, int l, double s){
			file_id = f;
			line_id = l;
			score = s;
		}
	}
	
}
