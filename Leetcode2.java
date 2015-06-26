package LeetCode2;

import java.io.IOException;
import java.util.*;

public class Solution {
	static Solution p = new Solution();
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
		
		ListNode(int x, ListNode p){
			val = x;
			next = p;
		}
		
		public void print(){
			System.out.print( val );
			ListNode p = next;
			while( p != null ){
				System.out.print( "->"+p.val );
				p = p.next;
			}
			System.out.println("->NULL");
		}
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
		
		public void print(){
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add( left );
			q.add( right );
			StringBuilder str = new StringBuilder();
			str.append( val );
		
			while(!q.isEmpty() ){
				TreeNode cur = q.poll();
				if( cur==null )
					str.append(",#");
				else{
					str.append(","+cur.val);
					q.add( cur.left );
					q.add( cur.right);
				}
			}
			
			while( str.charAt(str.length()-1)=='#' ){
				str.deleteCharAt(str.length()-1);
				if( str.charAt(str.length()-1)==',' )
					str.deleteCharAt(str.length()-1);
			}
			System.out.println(str.toString());
		}
	}
	
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
		public void print(){
			System.out.print(val);
			TreeLinkNode cur = left==null?right:left;
			while(cur!=null){
				TreeLinkNode temp = cur;
				System.out.print(",#");
				while(temp!=null){
					System.out.print(","+temp.val);
					temp=temp.next;
				}
				while( cur!=null ){
					if( cur.left==null && cur.right==null)
						cur = cur.next;
					else{
						cur = cur.left==null?cur.right:cur.left;
						break;
					}
				}
			}
			
		}
	}
	
	public class UndirectedGraphNode {
		 int label;
		 List<UndirectedGraphNode> neighbors;
		 UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};
	
	class RandomListNode {
		 int label;
		 RandomListNode next, random;
		 RandomListNode(int x) { this.label = x; }
	};
		 
	class Point {
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int a, int b) { 
			x = a; 
			y = b; 
		}
	}
	
	class Line{
		double a;
		double b;
		Line(){
			a = 0;
			b = 0;
		}
		Line( double x, double y){
			a = x;
			b = y;
		}
	}
	
	class Interval{
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
		
		void print(){
			System.out.print( "["+start+","+end+"] ");
		}
	}
	
	private Solution(){}
	
	public static void main(String[] args) throws IOException{
		
		
//		String[] words = {"cat", "cats", "and", "sand", "dog"};//{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//		Set<String> st = new HashSet<String>();
//		for( String w:words)
//			st.add( w );
		
//		String treestr = "1,2";//"7,2,11,1,5,8,12,0,#,3,6,#,10";
//		TreeNode root = p.generateTreeNode( treestr );
//		TreeLinkNode root = p.generateTreeLinkNode( treestr ); 	
//			
//		p.connect( root );
//		TreeNode cur = p.upsideDownBinaryTree( root);
//		cur.print();
				
		
//		LRUCache cache = p.new LRUCache(10);
		
		BackTrack_36_37_51_93_126 x = p.new BackTrack_36_37_51_93_126();
		x.excute();
	}
	

 	public static ListNode generateListNode(int[] A){
		ListNode L = p.new ListNode(A[0]);
		ListNode LN = L;
		for(int i=1; i<A.length; i++){
			LN.next = p.new ListNode(A[i]);
			LN = LN.next;
		}	
		return L;
	}

	public static TreeNode generateTreeNode( String treestr ){
		String[] ch = treestr.split(",");
		TreeNode root = p.new TreeNode( Integer.parseInt(ch[0]));	
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add( root );		
		int i = 0;
		while(!q.isEmpty()){
			TreeNode r = q.poll();
			if((++i)<ch.length && !ch[i].equals("#")){
				r.left = p.new TreeNode(Integer.parseInt(ch[i]));
				q.add( r.left );
			}
			if((++i)<ch.length && !ch[i].equals("#")){
				r.right = p.new TreeNode(Integer.parseInt(ch[i]));
				q.add( r.right );
			}
		}
		return root;
	}
	
	public static TreeLinkNode generateTreeLinkNode( String treestr ){
		String[] ch = treestr.split(",");
		TreeLinkNode root = p.new TreeLinkNode( Integer.parseInt(ch[0]));	
		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		q.add( root );		
		int i = 0;
		while(!q.isEmpty()){
			TreeLinkNode r = q.poll();
			if((++i)<ch.length && !ch[i].equals("#")){
				r.left = p.new TreeLinkNode(Integer.parseInt(ch[i]));
				q.add( r.left );
			}
			if((++i)<ch.length && !ch[i].equals("#")){
				r.right = p.new TreeLinkNode(Integer.parseInt(ch[i]));
				q.add( r.right );
			}
		}
		return root;
	}

	//==============================================================================================
	
	public class TopologicalSort{//topological sort
		
		public TopologicalSort(){}
		
		public void excute(){
			int numCourses = 8;
			int[][] prerequisites = {{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}};
//			if( canFinish2(numCourses, prerequisites) )
//				System.out.println( "Possible");
//			else
//				System.out.println( "Impossible");	
			
			int[] res = findOrder(numCourses, prerequisites);
			for(int temp:res){
				System.out.print(temp+", ");
			}
			System.out.println();
		}
		
		public boolean canFinish2(int numCourses, int[][] prerequisites) {//topological sort, if circle exists or not
		    if( numCourses<2 || null==prerequisites || 2>prerequisites.length )
		        return true;
		        
		    HashMap<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		    for( int[] temp:prerequisites ){
		        if( map.containsKey(temp[1]) )
		            map.get(temp[1]).add(temp[0]);
		        else{
		            Set<Integer> arr = new HashSet<Integer>();
		            arr.add( temp[0] );
		            map.put(temp[1], arr);
		        }
		    }
		    
		    Queue<Integer> sinkVetex = new LinkedList<Integer>();
		    for(int i=0; i<numCourses;i++){
		        if( !map.containsKey(i) )
		            sinkVetex.offer( i );  
		    }
		    
		    Set<Integer> newSink = new HashSet<Integer>();
		    while( !sinkVetex.isEmpty() ){
		        int sink = sinkVetex.poll();
		        for( int key: map.keySet() ){
		            if( map.get(key).contains(sink) ){
		                map.get(key).remove(sink);
		                if( map.get(key).isEmpty() ){
		                    newSink.add( key );
		                }
		            }
		        }
		        for( int temp:newSink){
		            sinkVetex.offer( temp );
		            map.remove( temp );
		        }
		        newSink.clear();
		    }
		    
		    return map.isEmpty();
		}
		
		public boolean canFinish1(int numCourses, int[][] prerequisites) {//dfs
		    if( numCourses<2 || null==prerequisites || 2>prerequisites.length )
		        return true;
		        
		    HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		    for( int[] temp:prerequisites ){
		        if( map.containsKey(temp[1]) )
		            map.get(temp[1]).add(temp[0]);
		        else{
		            List<Integer> arr = new LinkedList<Integer>();
		            arr.add( temp[0] );
		            map.put(temp[1], arr);
		        }
		    }
		    
		    boolean[] visited = new boolean[numCourses]; 
		    Set<Integer> visiting = new HashSet<Integer>();
		    for(int i=0;i<numCourses;i++){
		        if( !visited[i] ){
		            if( isWithCircle1(i, map, visited, visiting) )
		                return false;
		        }
		    }
		    return true;
		}

		public boolean isWithCircle1(int id, HashMap<Integer, List<Integer>> map, boolean[] visited, Set<Integer> visiting){
		    visiting.add( id );		    
		    if(map.containsKey(id)){
		        List<Integer> nexts = map.get(id);
		        for( int temp:nexts ){
		            if( visiting.contains(temp) )
		                return true;
		            if( !visited[temp] && isWithCircle1(temp, map, visited, visiting) )
		                return true;
		        }   
		    }
		    visiting.remove(id );
		    visited[id] = true;
		    return false;
		}
	
		public int[] findOrder(int numCourses, int[][] prerequisites) {    
	        Map<Integer, Set<Integer>> edges = new HashMap<Integer, Set<Integer>>();
	        int[] numChildren = new int[ numCourses ];
	        for(int[] temp:prerequisites){
	            if( !edges.containsKey(temp[0])){
	            	edges.put( temp[0], new HashSet<Integer>() );
	            }
	            if( edges.get(temp[0]).add( temp[1]) ){
	            	numChildren[temp[1]]++;
	            }
	        }
	        
	        Stack<Integer> sinkVetex = new Stack<Integer>();
	        for(int i=0;i<numCourses;i++){
	            if( 0==numChildren[i] )
	                sinkVetex.push( i );
	        }
	        
	        int n = numCourses-1;
	        int[] res = new int[ numCourses ];
	        while( !sinkVetex.isEmpty() ){
	            int vetex = sinkVetex.pop();
	            res[n--] = vetex;
	            
	            if( edges.containsKey(vetex)){
	            	for(int v:edges.get(vetex)){
	            		numChildren[v]--;
	            		if( 0==numChildren[v] )
	            			sinkVetex.push( v );
	            		
	            	}
	            	edges.remove(vetex);
	            }
	        }
	        
	        if( edges.isEmpty() )
	            return res;
	        else
	            return (new int[0]);        
	    }
	}
	
public class DistinctSequencesOfSubstrings_115{//letter or words
	DistinctSequencesOfSubstrings_115(){}
	    
	public void excute(){
		String S = "rabbbit";//"waeginsapnaabangpisebbasepgnccccapisdnfngaabndlrjngeuiogbbegbuoecccc";
	    String T = "rabbit";//"aa+b+c-";
	    System.out.println( numDistinct(S,T) );
	}
	
	public int numDistinct(String s, String t) {    //leetcode 115
        int[] dp = new int[t.length()];
        for(int i=0;i<s.length();i++){
            int prev = 1, temp = 1;
            for(int j=0;j<t.length()&&j<i+1;j++){
                temp = dp[j];
                if( s.charAt(i)==t.charAt(j))
                    dp[j] += prev;
                prev = temp;
            }
        }
        return dp[t.length()-1];
    }
	    
	    public class RepeatedChar{
	        char ch;
	        int t;//repeated times
	        
	        RepeatedChar( char _ch, int _t){
	            ch = _ch;
	            t = _t;
	        }
	    }
	    
	    public int distinctSequencesOfSubstrings( String s, String t){
	        if( null==t || 0==t.length() )
	            return -1;
	        if( null==s || 0==s.length() )
	            return 0;
	            
	        List<RepeatedChar> S = reformS(s,t);
	        List<RepeatedChar> T = reformT(t);
	        
	        for( RepeatedChar x:S){
	        	System.out.print( x.ch);
	        	System.out.print( x.t+",");
	        }
	        System.out.println();
	        for( RepeatedChar x:T){
	        	System.out.print( x.ch);
	        	System.out.print(x.t+",");
	        }
	        System.out.println();
	        
	        int[] dp = new int[T.size()];
	        if( S.get(0).ch==T.get(0).ch && S.get(0).t>=T.get(0).t )
	        	dp[0] = 1+S.get(0).t-T.get(0).t;
	        
	        for(int i=1; i<S.size();i++){
	            int dp_pre = dp[0];
	            if( S.get(i).ch==T.get(0).ch && S.get(i).t>=T.get(0).t )
		        	dp[0] += 1+S.get(i).t-T.get(0).t;
	            for( int j=1; j<T.size();j++){
	                if( S.get(i).ch==T.get(j).ch && S.get(i).t>=S.get(j).t ){
	                    int temp = dp[j];
	                    dp[j] = dp_pre*(S.get(i).t+1-T.get(j).t)+dp[j];
	                    dp_pre = temp;
	                }else
	                    dp_pre = dp[j];
	            }
	        }
	        return dp[T.size()-1];
	    }
	    
	    private List<RepeatedChar> reformT( String t){
	        List<RepeatedChar> str = new ArrayList<RepeatedChar>();
	        for(int i=0;i<t.length();i++){
	            char tch = t.charAt(i);
	            int value = 1;
	            if( i+1<t.length() && (t.charAt(i+1)=='-'||t.charAt(i+1)=='+') ){
	                value = t.charAt(++i)=='-'?4:2;
	            }
	            str.add(new RepeatedChar(tch, value));
	        }
	        return str;
	    }
	    
	    private List<RepeatedChar> reformS( String s, String t){
	        Map<Character,Integer> map = new HashMap<Character,Integer>();
	        for(int i=0;i<t.length();i++){
	            char tch = t.charAt(i);
	            int value = 1;
	            if( i+1<t.length() && (t.charAt(i+1)=='-'||t.charAt(i+1)=='+') ){
	                value = t.charAt(++i)=='-'?4:2;
	            }
	            map.put( tch, map.containsKey(tch)?Math.min(value,map.get(tch)):value );
	        }
	        
	        List<RepeatedChar> str = new ArrayList<RepeatedChar>();
	        int k = 0;
	        while(k<s.length()){
	            char sch = s.charAt(k++);
	            if( map.containsKey( sch ) ){
	                int i = k;
	                while(i<s.length()&& sch==s.charAt(i))
	                    i++;
	                if( i-k+1 >= map.get(sch ) ){
	                    str.add(new RepeatedChar(sch,(i-k+1)));
	                } 
	                k = i;
	            }
	        }
	        return str;
	    }
}
	
	public class numberOfIslands_200{//connected components
		numberOfIslands_200(){}
		
		public void excute(){
//			int[][] A = {{0,0,0,1,0},{0,1,1,1,1},{0,0,0,0,1},{0,1,1,0,1},{0,0,1,0,1}};
			int[][] A = new int[5][];
	        A[0] = new int[]{0, 0, 1, 1, 1};
	        A[1] = new int[]{0, 1, 1, 1, 1};
	        A[2] = new int[]{0, 1, 0, 0, 1};
	        A[3] = new int[]{0, 0, 0, 0, 1};
	        A[4] = new int[]{0, 0, 0, 0, 1};
	        
			if ( isValidCrosswordO1( A ) )
				System.out.println( "true");
			else
				System.out.println("false");
		}
		// if only one island: true-yes, false-no
		// dfs: time:O(mn) space O(1)
		private boolean isValidCrosswordO1(int[][] puzzle){//4-connectivity
			boolean firstScan = true;
			for(int r=0;r<puzzle.length;r++){
				for(int c=0;c<puzzle[0].length;c++){
					if( 1==puzzle[r][c] )
						continue;				
					if( !firstScan && 0==puzzle[r][c])
						return false;
					
					int x = r, y = c;
					while(true){
						while( 0==puzzle[x][y] ){
							puzzle[x][y] = 2;
							if( x+1<puzzle.length && 0==puzzle[x+1][y] ){
								x += 1;
								continue;
							}
							if( y+1<puzzle[0].length && 0==puzzle[x][y+1] ){
								y += 1;
								continue;
							}
							if( x-1>=0 && 0==puzzle[x-1][y] ){
								x -= 1;
								continue;
							}
							if( y-1>=0 && 0==puzzle[x][y-1] ){
								y -= 1;
							}
						}
						
						while( 2==puzzle[x][y] && !((x+1<puzzle.length && 0==puzzle[x+1][y])|| (y+1<puzzle[0].length && 0==puzzle[x][y+1]) 
								|| (x-1>=0 && 0==puzzle[x-1][y]) || (y-1>=0 && 0==puzzle[x][y-1])) ){
							puzzle[x][y] = 3;
							if( x-1>=0 && 2==puzzle[x-1][y] ){
								x -= 1;
								continue;
							}
							if( y+1<puzzle[0].length && 2==puzzle[x][y+1] ){
								y += 1;
								continue;
							}
							if( x+1<puzzle.length && 2==puzzle[x+1][y] ){
								x += 1;
								continue;
							}
							if( y-1>=0 && 2==puzzle[x][y-1] ){
								y -= 1;
							}
						}
						
						if(x+1<puzzle.length && 0==puzzle[x+1][y])
							x += 1;
						else if(y+1<puzzle[0].length && 0==puzzle[x][y+1]) 
							y += 1;
						else if(x-1>=0 && 0==puzzle[x-1][y])
							x -= 1;
						else if (y-1>=0 && 0==puzzle[x][y-1])
							y -= 1;
						else
							break;
					}
					firstScan = false;
				}				
			}		
			return true;
		}
		
	}
	
public class GapOrMissingItems_41_56_57_163{//gap,range between array items, missing element, interval
	GapOrMissingItems_41_56_57_163(){}
		
	public void excute(){
//		int[] A = {3,4,-1,1};
//			int lower = 0, upper = 48;
//			
//			List<String> L = findMissingRanges(A, lower, upper);
//			for( int j=0;j<L.size();j++)
//				System.out.println(L.get(j));
			
//		System.out.println( firstMissingPositive(A) );
		
		int[][] B = {{1,3},{6,9}};
		List<Interval> intervals = new ArrayList<Interval>();
		for(int[] x:B){
			intervals.add( new Interval(x[0],x[1]));
		}
		
		List<Interval> list =  insertInterval(intervals, new Interval(2,5));
		for(Interval x:list)
			x.print();
		System.out.println();
	}
		
	public List<String> findMissingRanges(int[] nums, int lower, int upper){
		List<String> res = new ArrayList<String>();
		if( lower>upper )
			return res;
		if( lower==upper ){
			res.add( Integer.toString(lower) );
			return res;
		}				
		if( null==nums || 0==nums.length || upper<nums[0] || lower>nums[nums.length-1]){
			res.add( lower+"->"+upper );
			return res;
		}
			
		int i = 0;
		int tbegin = lower, tend = lower;
		while(i<nums.length && nums[i]<=upper){
			tend = nums[i]-1;
			if( tbegin==tend )
				res.add( Integer.toString(tbegin) );
			else if( tbegin<tend )
				res.add( tbegin+"->"+tend );
			tbegin = Math.max(nums[i]+1, lower);
			i++;
		}
		
		tend = upper;
		if( tbegin==tend )
			res.add( Integer.toString(tbegin) );
		else if( tbegin<tend )
			res.add( tbegin+"->"+tend );
		
		return res;
	}
	
	public int firstMissingPositive(int[] nums) {    
        for( int i=0;i<nums.length;i++){
            while( nums[i] != i+1 && nums[i]>0 && nums[i]<nums.length && nums[nums[i]-1]!=nums[i]){
                int k = nums[i];
                nums[i] = nums[k-1];
                nums[k-1] = k;
            }            
        }
        for( int i=0;i<nums.length;i++){
            if( nums[i] != i+1 )
                return (i+1);
        }
        return 1+nums.length;
    }
	
	public List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> res = new LinkedList<Interval>();
        if( null==intervals || 0==intervals.size())
            return res;
        if( 1==intervals.size() )
            return intervals;
            
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                if( a.start==b.start )
                    return a.end-b.end;
                return a.start-b.start;
            }       
        } );
        
        Interval comp = intervals.get( 0 );
        for(int i=1;i<intervals.size();i++){
            if( comp.end < intervals.get(i).start ){
                res.add( comp );
                comp = intervals.get(i);
            }else if( comp.end < intervals.get(i).end )
                comp.end = intervals.get(i).end;
        }
        res.add( comp );
        return res;
    }
	
	public List<Interval> insertInterval0(List<Interval> intervals, Interval newInterval) {//leetcode
        int i=intervals.size();
        while( i>0 ){
            Interval cur = intervals.get(i-1);
            if( newInterval.start>cur.end )     
                break;
            i--;
            if( newInterval.end < cur.start )
                continue;           
            newInterval.start = Math.min( newInterval.start, cur.start );
            newInterval.end = Math.max( newInterval.end, cur.end );
            intervals.remove(i);
        }
        intervals.add(i, newInterval );
        return intervals;
    }
	
	public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<Interval>();
        for( Interval cur:intervals ){
            if( newInterval.end<cur.start ){
                res.add( newInterval );
                res.add( cur );
            }else if( newInterval.start>cur.end )
                res.add( cur );
            else{
                newInterval.start = Math.min(newInterval.start, cur.start);
                newInterval.end = Math.max(newInterval.end, cur.end);
            }       
        }
        if( res.isEmpty() || res.get(res.size()-1).end<newInterval.start )
            res.add( newInterval );
        
        return res;
    }
}
	
public class longestSubstring_76_159{//with distinct characters, min window substring
	longestSubstring_76_159(){}
		
		public void excute(){
//			String S = "apdaeakkk";
//			System.out.println( longestSubstringwithAtMost2DistinctCharacters(S) );
//			System.out.println( longestSubstringwithAtMostKDistinctCharacters(S,3) );
			
			String s = "ADOBECODEBANC", t = "ABC";
			System.out.println( minWindow(s,t) );
		}
		
	public int longestSubstringwithAtMost2DistinctCharacters(String S){
			if( null==S )
				return 0;
			if( 3>S.length())
				return S.length();
			
			int maxLen = 0;
			int i=0,j=-1;
			for(int k=1;k<S.length();k++){
				if( S.charAt(k)==S.charAt(k-1))
					continue;
				
				if( 0<=j && S.charAt(k)!=S.charAt(j) ){
					maxLen = Math.max(maxLen, k-i);
					i = j + 1;;
				}
				j = k-1;
			}
			return Math.max(maxLen,S.length()-i);
	}
		
		//follow up: with at most k distinct characters
	public int longestSubstringwithAtMostKDistinctCharacters(String S, int k ){
			if( null==S || 0==k )
				return 0;
			if( k>=S.length() )
				return S.length();
			
			Map<Character, Integer> st = new HashMap<Character,Integer>();
			st.put( S.charAt(0),1 );
			
			int maxLen = 0;
			int tbegin = 0;
			for(int i=1;i<S.length();i++){
				char key = S.charAt(i);
				st.put( key, st.containsKey(key)?st.get(key)+1:1 );				
				maxLen = Math.max(maxLen,i-tbegin);		
				
				while( st.size()>k ){
					if( 1==st.get(S.charAt(tbegin)) )
						st.remove(S.charAt(tbegin));
					else
						st.put(S.charAt(tbegin), st.get(S.charAt(tbegin))-1);
					tbegin++;
				}
			}
			return Math.max(maxLen,S.length()-tbegin);
	}
	
	public String minWindow(String s, String t) {
        if( null==s || 0==s.length() || null==t || 0==t.length() )
            return "";
            
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            map.put(ch, map.containsKey(ch)?map.get(ch)+1:1);
        }
        
        int[] index = {-1,-1};
        int numCover = 0, minLen = Integer.MAX_VALUE, j = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if( map.containsKey( ch ) ){
                if( map.get(ch)>0 )
                    numCover++;
                map.put( ch, map.get( ch )-1 );
                
                if( t.length()==numCover ){                
                    while( j<i ){
                        char temp = s.charAt(j);
                        if( map.containsKey( temp ) ){
                            if( map.get( temp )<0 ){
                                map.put( temp, map.get( temp )+1 );
                            }else
                                break;                       
                        }
                        j++;
                    }                   
                    if( minLen > i-j ){
                        minLen = i-j;
                        index[0] = j;
                        index[1] = i;
                    }   
                }   
            }
        }
        return -1==index[0]?"":s.substring(index[0],1+index[1]);
    }
}
	
	public class readNCharactersGivenRead4_157_158{
		String file;
		
		char[] buffer = new char[4];
		int k = 4;
		
		readNCharactersGivenRead4_157_158(){}
		
		public void excute(){
			this.file = "Take it easy, OK?";
			char[] buffer1 = new char[1];
			int n = read(buffer1, buffer1.length);
			System.out.print( n +": ");
			System.out.println(new String(buffer1) );
			
			char[] buffer2 = new char[2];
			n = read(buffer2, buffer2.length);
			System.out.print( n +": ");
			System.out.println(new String(buffer2) );
		}
		
		private int read(char[] buf, int n){	
			int i = 0;
			while( k<4 && i<n ){
				buf[i++] = buffer[k++];
			}
			
			while(i<n){
				int num = read4( buffer );
				for(k=0;k<num && i+k<n;k++)
					buf[i+k] = buffer[k];
				i += k;
				if( num<4 )
					break;
			}
			return i;
		}
		
		private int read4(char[] buffer){
			if( 0==file.length() )
				return 0;
			
			int i = 0;
			for(i=0; i<4&&i<file.length();i++)
				buffer[i] = file.charAt(i);

			file = file.substring(i);			
			return i;
		}
	}
	
	public class upsideDownBinaryTree_156{//reverse linkedlist/tree
		
		upsideDownBinaryTree_156(){}
		
		public void excute(){
			String treestr = "1,2,3,4,5,7,8,#,#,9,10";
			TreeNode root = generateTreeNode( treestr );
			TreeNode cur = upsideDownBinaryTree3( root);
			cur.print();
		}
		
		private TreeNode upsideDownBinaryTree3(TreeNode root){//recursive, similar as reverse linkedlist
			TreeNode parent = null, parentRight = null;
			while( null!=root ){
				TreeNode left = root.left;
				root.left = parentRight;
				parentRight = upsideDownBinaryTree3(root.right);
				root.right = parent;
				parent = root;
				root = left;
			}			
			return parent;
		}
		
		@SuppressWarnings("unused")
		private TreeNode upsideDownBinaryTree2(TreeNode root){//Recursive, Time O(log n)
			return upsideDownBinaryTree2_dfs( root, null);
		}		
		private TreeNode upsideDownBinaryTree2_dfs( TreeNode root, TreeNode parent){
			if( null==root)
				return parent;
			
			TreeNode newParent = upsideDownBinaryTree2_dfs( root.left, root);
			if( null != parent ){
				root.left = upsideDownBinaryTree2_dfs(parent.right, null);
				root.right = parent;
				parent.right = null;
				parent.left = null;
			}			
			return newParent;
		}
		
		@SuppressWarnings("unused")
		private TreeNode upsideDownBinaryTree1(TreeNode root){//Recursive, Time O((log n)^2)
			if( null==root || null==root.left )
				return root;
			
			TreeNode cur = root;
			while( null!=cur.left.left  )
				cur = cur.left;
				
			TreeNode res = cur.left, right = cur.right;
			cur.left = null;
			cur.right = null;
			
			res.left = upsideDownBinaryTree1( right );			
			res.right = upsideDownBinaryTree1( root );
			return res;
		}
		
	}

public class TreeTraversalGeneration_94_95_96_98_99_100_101_102_103_105_106_107_108_109_110{//in-order traversal, generation, validation, recover
	public TreeTraversalGeneration_94_95_96_98_99_100_101_102_103_105_106_107_108_109_110(){}
	
	public void excute(){
//		String S = "6,2,9,1,4,7,11,#,#,3,5,#,#,10,12";
//		String B = "1,2,3,6,4,5,9,7,#,8,#,10,11";
//		TreeNode root = generateTreeNode( S );
//		List<Integer> list = inorderTraversal( root );
//		for(int x:list)
//			System.out.print(x+", ");
//		System.out.println();
		
		
//		List<TreeNode> roots = buildTree(A,B);
//		for(TreeNode r:roots)
//			r.print();
		
		int[] A = {1,2,3};
//		int[] A = {1,2,3,4,5,6,7,9,10,11,12};
//		int[] C = {1,3,5,4,2,7,10,12,11,9,6};
//		System.out.println( A );
		ListNode l = generateListNode( A );
		TreeNode root = sortedListToBST(l);
		root.print();
		
//		System.out.println( isSymmetric(root) );
		
//		List<List<Integer>> res = levelOrderBottom(root);
//		for(List<Integer> list:res){
//			for(int x:list)
//				System.out.print(x+", ");
//			System.out.println();			
//		}
	}
	
	public List<Integer> inorderTraversal0(TreeNode root) {//leetcode 94, recursive
        List<Integer> res = new LinkedList<Integer>();
        if( null==root )
            return res;
            
        res.addAll( inorderTraversal0( root.left ) );
        res.add( root.val );
        res.addAll( inorderTraversal0( root.right ) );
        return res;
    }
	
	public List<Integer> inorderTraversal1(TreeNode root) {//leetcode 94, iterative stack, O(n) time, O(n) space
        List<Integer> res = new LinkedList<Integer>();        
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        while( !stk.isEmpty() || null!=cur ){
        	if( null!=cur  ){
        		stk.push( cur );
        		cur = cur.left;        		
        	}else{
        		cur = stk.pop();
        		res.add( cur.val );
        		cur = cur.right;
        	}        	
        }        
        return res;
    }

	public List<Integer> inorderTraversal(TreeNode root) {//leetcode 94, iterative O(nlog n) time, O(1) space
        List<Integer> res = new LinkedList<Integer>();
        TreeNode cur = root;
        while( null!=cur ){
            TreeNode left = cur.left;
            if( null==left){
                res.add( cur.val );
                cur = cur.right;
            }else{
                TreeNode temp = left;
                while( null!=temp.right && cur != temp.right)
                    temp = temp.right;
                if( null==temp.right ){
                    temp.right = cur;
                    cur = left;
                }else{
                    temp.right = null;
                    res.add( cur.val );
                    cur = cur.right;
                }
            }       
        }
        return res;
    }

	public List<TreeNode> generateTrees(int n) {//leetcode 95
        return generateTrees_helper( 1, n );
    }    
    public List<TreeNode> generateTrees_helper(int m, int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if( m>n || 0==n )
        	res.add( null );
        for( int i=m;i<=n;i++){
            List<TreeNode> sub1 = generateTrees_helper(m, i-1);
            List<TreeNode> sub2 = generateTrees_helper(i+1, n);
            for(TreeNode left:sub1){
            	for(TreeNode right:sub2){
            		TreeNode root = new TreeNode( i );
                    root.left = left;
                    root.right = right;
                    res.add( root );
            	}
            }
        }
        return res;
    }
    
    public int numTrees0(int n) {//leetcode 96
        if( n<1 )
            return 0;
            
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            int sum = 0;
            for(int j=i;j>0;j--){
                sum += dp[j-1];
                dp[j] = sum;
            }
            dp[0] = sum;
        }
        int res = 0;
        for(int x:dp )
            res += x;
        return res;
    }

    public int numTrees(int n) {//leetcode 96
        if( n<1 )
            return 0;
            
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2;i<n+1;i++){
            for(int j=0;j<i;j++)
                dp[i] += dp[j]*dp[i-1-j];
        }
        return dp[n];
    }

    public boolean isValidBST0(TreeNode root) {//leetcode 98, recursive
        if( null==root )
            return true;
            
        int[] minmax = new int[2];
        return isValidBST_helper(root, minmax); 
    }    
    public boolean isValidBST_helper(TreeNode root, int[] minmax){
        minmax[0] = minmax[1] = root.val;               
        if( null!=root.left ){
            int[] temp = new int[2];
            if( !isValidBST_helper(root.left, temp) || temp[1]>= root.val )
                return false;
            else
                minmax[0] = temp[0];
        }
        if( null!=root.right){
            int[] temp = new int[2];
            if( !isValidBST_helper(root.right, temp) || temp[0]<=root.val )
                return false;
            else
                minmax[1] = temp[1];
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {//leetcode 98, iterative, traversal
        boolean init = false;
        int last = Integer.MIN_VALUE;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        while(null!=cur || !stk.isEmpty()){
            if( null==cur ){
                cur = stk.pop();
                if( !init ){
                    init = true;
                    last = cur.val;
                }else if( last>= cur.val )
                    return false;
                else
                    last = cur.val;
                cur = cur.right;
            }else{
                stk.push( cur );
                cur = cur.left;
            }
        
        }
        return true;
    }

    public void recoverTree(TreeNode root) {//leetcode 99
        TreeNode cur = root, last1 = null, last2 = null;
        while( null!=cur ){
            if( null==cur.left){
                if( null==last1 )
                    last1 = cur;
                else if( null==last2 ){
                    if( last1.val<cur.val )
                        last1 = cur;
                    else
                        last2 = cur;
                }else if(last2.val>cur.val)
                    last2 = cur;
                    
                cur = cur.right;
            }else{
                TreeNode t = cur.left;
                while( null!=t.right && t.right!=cur )
                    t = t.right;
                if( null==t.right ){
                    t.right = cur;
                    cur = cur.left;
                }else{
                    t.right = null;
                    if( null==last2 ){
                        if( last1.val<cur.val )
                            last1 = cur;
                        else
                            last2 = cur;
                    }else if(last2.val>cur.val)
                        last2 = cur;
                    cur = cur.right;               
                }           
            }
        }
        
        int temp = last1.val;
        last1.val = last2.val;
        last2.val = temp;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {//leetcode 100
        if( null==p )
            return null==q;
        if( null==q || p.val != q.val )
            return false;
            
        return isSameTree( p.left, q.left) && isSameTree( p.right, q.right);        
    }
    
    public boolean isSymmetric0(TreeNode root) {//leetcode 101
        if( null==root )
            return true;
        return isSymmetric_recursive(root.left, root.right);
    }   
    public boolean isSymmetric_recursive(TreeNode left, TreeNode right){
        if( null==left )
            return null==right;
        if( null==right || left.val != right.val )
            return false;
        return isSymmetric_recursive(left.left, right.right) && isSymmetric_recursive(left.right, right.left);
    }
    
    public boolean isSymmetric1(TreeNode root) {//leetcode 101, iterative
        if( null==root )
            return true;
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add( root );
        while( !list.isEmpty() ){
            int Len = list.size();
            for(int i=0;i<Len;i++){
                TreeNode t = list.get(i);
                if( null!=t ){
	                list.add( t.left );
	                list.add( t.right);
                }
            }
            for(int i=0;i<Len;i++)
                list.remove(0);
            if( list.size()%2==1)
                return false;
            for( int i=0;i<list.size()/2;i++){
            	if( null==list.get(i) && null==list.get(list.size()-1-i) )
            		continue;
            	if( null==list.get(i) || null==list.get(list.size()-1-i) )
            		return false;            		
                if( list.get(i).val != list.get(list.size()-1-i).val )
                    return false;
            }       
        }
        return true;
    }
    
    public boolean isSymmetric(TreeNode root) {//leetcode 101, iterative
        Stack<TreeNode[]> stk= new Stack<TreeNode[]>();
        stk.push( new TreeNode[]{root, root} );
        while( !stk.isEmpty() ){
            TreeNode[] pair = stk.pop();
            if( null==pair[0] && null==pair[1] )
                continue;
            if( null==pair[0] || null==pair[1] || pair[0].val != pair[1].val )
                return false;
            
            stk.push( new TreeNode[]{pair[0].left, pair[1].right} );
            stk.push( new TreeNode[]{pair[0].right, pair[1].left} );
        }
        return true;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {//leetcode 102
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer( root );
        while( !q.isEmpty() ){
            List<Integer> list = new LinkedList<Integer>();
            int Len = q.size();
            for(int i=0;i<Len;i++){
                TreeNode cur = q.poll();
                list.add( cur.val );
                if( null!=cur.left )
                    q.offer( cur.left );
                if( null!=cur.right )
                    q.offer( cur.right );
            }
            res.add( list );       
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {//leetcode 103
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Deque<TreeNode> q = new LinkedList<TreeNode>();
        if( null!=root )
            q.offerFirst( root );
        boolean inorder = true;
        while(!q.isEmpty()){
            List<Integer> list = new LinkedList<Integer>();
            int Len = q.size();
            if( inorder ){
                for(int i=0;i<Len;i++){
                    TreeNode cur = q.pollLast();
                    list.add( cur.val );
                    if(null!=cur.left)
                        q.offerFirst( cur.left );
                    if(null!=cur.right)
                        q.offerFirst( cur.right);               
                }
            }else{
                for(int i=0;i<Len;i++){
                    TreeNode cur = q.pollFirst();
                    list.add( cur.val );
                    if(null!=cur.right)
                        q.offerLast( cur.right);  
                    if(null!=cur.left)
                        q.offerLast( cur.left ); 
                }
            }
            inorder = !inorder;
            res.add( list );
        }
        return res;
    }

    public TreeNode buildTree_PreorderInorder0(int[] preorder, int[] inorder) {//leetcode 105, recursive
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++ )
            map.put( inorder[i], i);
            
        return buildTree_helper_PreInorder( map, preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    public TreeNode buildTree_helper_PreInorder(HashMap<Integer, Integer> map, int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
    	if( pstart>pend || pend-pstart != iend-istart)
    		return null;
    	
        TreeNode root = new TreeNode(preorder[pstart]);
        if( pstart<pend ){
            int mid = map.get( preorder[pstart]);
            root.left = buildTree_helper_PreInorder( map, preorder, pstart+1, mid-istart+pstart, inorder, istart, mid-1);
            root.right = buildTree_helper_PreInorder( map, preorder, mid-istart+pstart+1, pend, inorder, mid+1, iend);
        }
        return root;        
    }

    public TreeNode buildTree_PreorderInorder(int[] preorder, int[] inorder) {//leetcode 105, iterative
        if( null==preorder || 0==preorder.length || preorder.length != inorder.length )
            return null;
            
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode root = new TreeNode(preorder[0]);
        stk.push( root );
        int j = 0;
        for(int i=1;i<preorder.length;i++){
            TreeNode cur = stk.peek();
            if( inorder[j]!=cur.val ){
                cur.left = new TreeNode( preorder[i] );
                stk.push( cur.left );
            }else{
                while( !stk.isEmpty() && stk.peek().val==inorder[j] ){
                    cur = stk.pop();
                    j++;
                }
                cur.right = new TreeNode( preorder[i] );
                stk.push( cur.right );
            }
        }    
        return root;
    }

    public TreeNode buildTree_InorderPostorder0(int[] inorder, int[] postorder) {//leetcode 106, recursive
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            map.put( inorder[i],i);
        return buildTree_helper_InPostorder(map, inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);     
    }
    
    public TreeNode buildTree_helper_InPostorder(HashMap<Integer, Integer> map, int[] in, int ii, int ij, int[] post, int pi, int pj){
        if( pi>pj || ij-ii != pj-pi)
            return null;
        TreeNode root = new TreeNode( post[pj] );
        int index = map.get( post[pj] );
        root.left = buildTree_helper_InPostorder(map, in, ii, index-1, post, pi, index-ii+pi-1); 
        root.right = buildTree_helper_InPostorder(map, in, index+1, ij, post, index-ii+pi, pj-1); 
        return root;
    }
    
    public TreeNode buildTree_InorderPostorder(int[] inorder, int[] postorder) {//leetcode 106, iterative
        if( 0==inorder.length || inorder.length != postorder.length )
            return null;
        TreeNode root = new TreeNode( postorder[postorder.length-1] );
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push( root );
        int j = inorder.length-1;
        for(int i=postorder.length-2; i>=0;i--){
            TreeNode cur = stk.peek();
            if( inorder[j] != cur.val ){
                cur.right = new TreeNode( postorder[i] );
                stk.push( cur.right );
            }else{
                while(!stk.isEmpty() && stk.peek().val==inorder[j] ){
                    cur = stk.pop();
                    j--;
                }
                cur.left = new TreeNode( postorder[i] );
                stk.push( cur.left );
            }
        }
        return root;     
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {//leetcode 107
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if( null!=root )
            q.offer( root );
        while( !q.isEmpty() ){
            int Len = q.size();
            List<Integer> list = new LinkedList<Integer>();
            for(int i=0;i<Len;i++){
                TreeNode cur = q.poll();
                list.add( cur.val );
                if( null!=cur.left )
                    q.offer( cur.left );
                if( null!=cur.right )
                    q.offer( cur.right );
            }
            res.add(0, list );
        }
        return res;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if( null==nums || 0==nums.length )
            return null;
        return sortedArrayToBST_helper(nums, 0, nums.length-1 );
    }  
    public TreeNode sortedArrayToBST_helper(int[] nums, int i, int j){
        int mid = (i+j)/2;
        TreeNode root = new TreeNode( nums[mid] );
        root.left = i<mid?sortedArrayToBST_helper(nums, i, mid-1 ):null;
        root.right = j>mid?sortedArrayToBST_helper(nums, mid+1, j ):null;
        return root;
    }
    
    public TreeNode sortedListToBST(ListNode head){//leetcode 109
        if( null==head )
            return null;
        if( null==head.next )
            return (new TreeNode( head.val ));
            
        ListNode slow = head, fast = head, prev = null;
        while( null!=fast && null!=fast.next ){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        TreeNode root = new TreeNode( slow.val );
        prev.next = null;
        root.left = sortedListToBST( head );            
        root.right = sortedListToBST( slow.next );
        return root;
    }

    public boolean isBalanced(TreeNode root) {//leetcode 110
        int[] res = {0};
        return Depth( root, res );
    } 
    public boolean Depth( TreeNode root, int[] res ){
        if( null==root )
            return true;
            
        int[] dps1 = {0}, dps2 = {0};      
        if( !Depth( root.left, dps1) )
            return false;
        if( !Depth( root.right, dps2) )
            return false;
        res[0] = 1+Math.max(dps1[0],dps2[0]);
        
        return Math.abs( dps1[0]-dps2[0])<2;
    }
}

public class TreeFeatures_104_111_112_113_114_116_117_124{//depth, populate, flatten, has pathSum, max path
	public TreeFeatures_104_111_112_113_114_116_117_124(){}
	
	public void excute(){
//		String A = "6,2,9,1,4,7,11,#,#,3,5,#,#,10,12";
		String B = "5,4,8,11,#,13,4,7,2,#,#,5,1";
		TreeNode root = generateTreeNode( B );
//		List<Integer> list = inorderTraversal( root );
//		for(int x:list)
//			System.out.print(x+", ");
//		System.out.println();
		
//		List<TreeNode> roots = generateTrees(3);
//		for(TreeNode r:roots)
//			r.print();
		
//		System.out.println( A );
		flatten( root );
		root.print();
		
//		System.out.println( hasPathSum(root, 3) );
		
		List<List<Integer>> res = pathSum( root, 22 );		
		for( List<Integer> list:res){
			for(int x:list)
				System.out.print(x+", ");
			System.out.println();
		}
		
	}
	
	public int maxDepth(TreeNode root) {//leetcode 104
        if( null==root )
            return 0;
        return 1+Math.max( maxDepth(root.left), maxDepth( root.right) );  
    }
	
	public int minDepth(TreeNode root) {//leetcode 111
        if( null==root )
            return 0;
        if( null==root.left && null==root.right )
            return 1;
        if( null==root.left || null==root.right )
            return 1+(null==root.left?minDepth( root.right):minDepth( root.left));
        return 1+Math.min( minDepth( root.right), minDepth( root.left) );  
    }

	public boolean hasPathSum0(TreeNode root, int sum) {//leetcode 112
        if( null==root )
            return false;
            
        int val = sum-root.val;
        if( null==root.left && null==root.right )
            return 0==val;
        if( null==root.left || null==root.right )
            return null==root.left?hasPathSum0(root.right, val):hasPathSum0(root.left, val);
            
        return hasPathSum0(root.right, val) || hasPathSum0(root.left, val);
    }
	
	public boolean hasPathSum(TreeNode root, int sum) {//leetcode 112
        if( null==root )
            return false;
            
        Stack<TreeNode> stk = new Stack<TreeNode>();
        sum -= root.val;
        stk.push( root );
        TreeNode prev = root;
        while( !stk.isEmpty() ){
            TreeNode cur = stk.peek(); 
            if((null==cur.left && null==cur.right) || (prev==cur.left && null==cur.right) || ( prev==cur.right )){
                if( null==cur.left && null==cur.right && 0==sum )
                    return true;
                sum += cur.val;
                stk.pop();
            }else if( null!=cur.left && prev !=cur.left){
                stk.push( cur.left );
                sum -= cur.left.val;
            }else{
                stk.push( cur.right );
                sum -= cur.right.val;
            }
            prev = cur;
        }
        return false;
    }

	public List<List<Integer>> pathSum0(TreeNode root, int sum) {//leetcode 113
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( null!=root ){
            List<Integer> list = new LinkedList<Integer>();
            pathSum_helper(root, sum, list, res);
        }
        return res;
    }   
    public void pathSum_helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> res){
        list.add( root.val );
        if( null==root.left && null==root.right ){
            if( sum==root.val ){
                res.add( list );
            }
        }else if ( null==root.left || null==root.right ){
            pathSum_helper(null==root.left?root.right:root.left, sum-root.val, list, res);
        }else{
            List<Integer> copy = new LinkedList<Integer>( list );
            pathSum_helper(root.left, sum-root.val, list, res);
            pathSum_helper(root.right, sum-root.val, copy, res);
        }      
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( null==root )
            return res;
            
        List<TreeNode> list = new LinkedList<TreeNode>();
        list.add( root );
        int val = sum - root.val;
        TreeNode pre = root;
        while( !list.isEmpty() ){
            TreeNode cur = list.get( list.size()-1 ); 
            if( (null==cur.left&&null==cur.right)||( null==cur.right&&pre==cur.left)||pre==cur.right ){
                if( null==cur.left&&null==cur.right && val==0 ){
                    List<Integer> temp = new LinkedList<Integer>();
                    for(TreeNode t:list)
                        temp.add( t.val );
                    res.add( temp );
                }
                list.remove( list.size()-1 );
                val += cur.val;
            }else if( null!=cur.left && pre!=cur.left){
                list.add( cur.left );
                val -= cur.left.val;
            }else{
                list.add( cur.right );
                val -= cur.right.val;
            }
            pre = cur;
        }
        return res;
    }

    public void flatten0(TreeNode root) {//leetcode 114
        TreeNode cur = root;
        while( null!=cur ){
            if( null != cur.left ){
                if( null!=cur.right ){
                    TreeNode temp = cur.left;     
                    while( null!=temp.right )
                        temp = temp.right;
                    temp.right = cur.right;                
                }
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }        
    }
    
    public void flatten(TreeNode root) {//leetcode 114
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode cur = root;
        while( null!=cur || !stk.isEmpty() ){
            if( null!=cur.left ){
                if( null !=cur.right)
                    stk.push( cur.right );
                cur.right = cur.left;
                cur.left = null;
            }else if(null==cur.right && !stk.isEmpty())
                cur.right = stk.pop();
            cur = cur.right;
        }        
    }

    public void connect_PopulateTreeI_0(TreeLinkNode root) {//leetcode 116, recursion
        if( null==root )
            return;
        if( null!=root.left )
            root.left.next = root.right;
        if( null!=root.right && null!=root.next)
            root.right.next = root.next.left;
        connect_PopulateTreeI_0( root.left );
        connect_PopulateTreeI_0( root.right );
    }
    
    public void connect_PopulateTreeI(TreeLinkNode root) {//leetcode 116, iteration
        TreeLinkNode first = root;
        while(null!=first && null!=first.left){            
            first.left.next = first.right;
            TreeLinkNode cur = first.next, p = first.right;
            while( null!=cur ){
                p.next = cur.left;
                p = p.next;
                p.next = cur.right;
                p = p.next;
                cur = cur.next;
            }
            first = first.left;
        }        
    }

    public void connect_PopulateTreeII(TreeLinkNode root) {   
        TreeLinkNode cur = root;
        TreeLinkNode numpy = new TreeLinkNode(-1);
        while( null!=cur ){           
            TreeLinkNode p = numpy;
            while( null!=cur ){
                if( null!=cur.left){
                    p.next = cur.left;
                    p = p.next;
                }
                if( null!=cur.right ){
                    p.next = cur.right;
                    p = p.next;
                }
                cur = cur.next;
            }
            cur = numpy.next;
            numpy.next = null;
        }       
    }

    public int maxPathSum(TreeNode root){//leetcode 124
        int[] vals = new int[]{Integer.MIN_VALUE, 0};//max inside, max branch
        maxPathSum_helper(root, vals);
        return vals[0];
    }    
    public void maxPathSum_helper(TreeNode root, int[] vals ){
        if( null==root )
            return;
            
        int[] vals1 = new int[]{Integer.MIN_VALUE, 0}, vals2 = new int[]{Integer.MIN_VALUE, 0};
        maxPathSum_helper(root.left, vals1 );
        maxPathSum_helper(root.right, vals2 );
        vals[0] = Math.max(Math.max( vals1[0], vals2[0]), vals1[1]+vals2[1]+root.val);
        vals[1] = Math.max(0, root.val + Math.max( vals1[1], vals2[1]));
    }
}

public class editDistance_72_161{
	editDistance_72_161(){}
		
	public void excute(){
			String s = "pneumonoultramicroscopicsilicovolcanoconiosis", t = "ultramicroscopically";
//			String s = "abc", t = "c";
//			if ( isOneEditDistance( s, t ) )
//				System.out.println( "true");
//			else
//				System.out.println("false");
			System.out.println("Edit distance is "+minDistance(s,t));
	}
		
		
		//time:O(n), space:O(1)
	public boolean isOneEditDistance(String s, String t){
			if( s==null )
				return (null==t || t.length()<2);
			if( t==null )
				return s.length()<2;
			if( s.length()<t.length() )
				return isOneEditDistance(t,s);
			if( s.length()-t.length()>1 )
				return false;
						
			int dist = 0;
			int i = 0, j = 0;
			while(i<s.length()){
				if( s.charAt(i++)!=t.charAt(j++) ){
					dist++;
					if( dist>1 )
						return false;
					j = s.length()==t.length()?j:j-1;
				}
			}							
			return true;
	}
		
	public int minEditDistance(String s, String t){
			if( null==s && null==t)
				return 0;
			if( null==s || 0==s.length() )
				return t.length();
			if( null==t || 0==t.length() )
				return s.length();
			
			int slen = s.length(), tlen = t.length();
			int[] dp = new int[slen+1];
			dp[0] = s.charAt(0)==t.charAt(0)?0:1;
			for(int i=0;i<slen;i++)
				dp[i] = i;
			
			int pre = dp[0];
			for(int j=0;j<tlen;j++){
				dp[0] = j;
				for(int i=0;i<slen;i++){
					int temp = dp[i+1];
					if( t.charAt(j)==s.charAt(i) )
						dp[i+1] = pre;
					else
						dp[i+1] = 1+Math.min(dp[i], Math.min(pre, temp));
					pre = temp;
				}
			}
			return dp[slen];
	}
		
	public int minDistance(String word1, String word2) {
        if( null==word1 || 0==word1.length() )
            return null==word2?0:word2.length();
        if( null==word2 || 0==word2.length() )
            return word1.length();
    
        int[] dp = new int[word1.length()+1];
        for(int i=1;i<=word1.length();i++)
            dp[i] = i;
        for(int j=0;j<word2.length();j++){
            int dp_ij = dp[0];
            dp[0] = j+1;
            for(int i=1;i<=word1.length();i++){
                int temp = dp[i];
                dp[i] = word1.charAt(i-1)==word2.charAt(j)?dp_ij:1+Math.min(Math.min(dp[i-1], dp[i]),dp_ij);
                dp_ij = temp;
            }
        }
        return dp[word1.length()];
    }
	
}

public class GraphMatrixDfsBfsDp_79_85_221{
	public GraphMatrixDfsBfsDp_79_85_221(){}
	
	public void excute(){
		char[][] A = {{'1'}};//{"10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(), "10010".toCharArray()};
//		System.out.println( exist(A,"ABCESEEEFS") );//
		
		System.out.println( maximalSquare(A) );
		
	}
	
	public boolean exist(char[][] board, String word) {
        if( null==board || 0==board.length || null==board[0]||0==board[0].length)
            return (null==word||0==word.length())?true:false;
        if( null==word||0==word.length() )
            return false;
            
        int rows = board.length, cols = board[0].length;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if( board[i][j]==word.charAt(0) && wordSearch_helper(board, i, j, word, 0) )
                    return true;
            }
        }
        return false;
    }
    
    private static final char REPLACE = '.';
    
    public boolean wordSearch_helper(char[][] board, int i, int j, String word, int tbegin){
        if( tbegin== word.length() )
            return true;
        if( i<0 || i>=board.length || j<0 || j>=board[0].length )
            return false;
        if( board[i][j] != word.charAt( tbegin ) )
            return false;
    
        char ch = board[i][j];
        board[i][j] = REPLACE;
        if( wordSearch_helper(board, i+1, j, word, tbegin+1) ||
        		wordSearch_helper(board, i-1, j, word, tbegin+1) ||
        		wordSearch_helper(board, i, j+1, word, tbegin+1) ||
        		wordSearch_helper(board, i, j-1, word, tbegin+1) )
            return true;
            
        board[i][j] = ch;
        return false;
    }

    public int maximalRectangle(char[][] matrix) {
        if( null==matrix || 0==matrix.length || null==matrix[0] || 0==matrix[0].length)
            return 0;
            
        int maxVal = 0;
        int[] hist = new int[matrix[0].length];
        Stack<Integer> stk = new Stack<Integer>();
        for( int i=0;i<matrix.length;i++){
            for(int j=0;j<=matrix[0].length;j++){
            	if( j<matrix[i].length )
            		hist[j] = matrix[i][j]=='0'?0:hist[j]+1;
                while( !stk.isEmpty() && (j==matrix[i].length || hist[stk.peek()]>hist[j]) ){
                    int index = stk.pop();
                    int area = hist[index]*( stk.isEmpty()?j:j-stk.peek()-1);
                    maxVal = Math.max( maxVal, area);
                }
                if( j<matrix[i].length )
                	stk.push( j );
            }   
        }
        return maxVal;
    }

    public int maximalSquare0(char[][] matrix) {
        if(null==matrix||0==matrix.length||null==matrix[0]||0==matrix[0].length)
            return 0;
            
        int maxVal = 0;
        int[] dp = new int[1+matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            int dp_ij = 0;
            for(int j=0;j<matrix[i].length;j++){
                int temp = dp[j+1];
                dp[j+1] = matrix[i][j]=='0'?0:1+Math.min(Math.min(dp[j+1],dp[j]),dp_ij);
                maxVal = Math.max(maxVal, dp[j+1]);
                dp_ij = temp;
            }
        }
        return maxVal*maxVal;
    }
    
    public int maximalSquare(char[][] matrix) {//O(1) space
        if(null==matrix||0==matrix.length||null==matrix[0]||0==matrix[0].length)
            return 0;
            
        int maxVal = 0;
        for(int i=0;i<matrix.length && maxVal==0;i++)
            maxVal = matrix[i][0]=='1'?1:0;
        for(int j=1;j<matrix[0].length && maxVal==0;j++)
            maxVal = matrix[0][j]=='1'?1:0;
            
        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[i].length;j++){
                if( '1'==matrix[i][j]){
                    matrix[i][j] = (char)(1+Math.min(Math.min(matrix[i-1][j-1],matrix[i-1][j]),matrix[i][j-1]));
                    maxVal = Math.max(maxVal, matrix[i][j]-'0');
                }
            }
        }
        return maxVal*maxVal;
    }
}
	
public class BackTrack_36_37_51_93_126{//sudoku, N queens, word ladder
	public BackTrack_36_37_51_93_126(){}
	
	public void excute(){
//		char[][] board = {	{'5','3','.','.','7','.','.','.','.'},
//							{'6','.','.','1','9','5','.','.','.'},
//							{'.','9','8','.','.','.','.','6','.'},
//							{'8','.','.','.','6','.','.','.','3'},
//							{'4','.','.','8','.','3','.','.','1'},
//							{'7','.','.','.','2','.','.','.','6'},
//							{'.','6','.','.','.','.','2','8','.'},
//							{'.','.','.','4','1','9','.','.','5'},
//							{'.','.','.','.','8','.','.','7','9'}};
////		System.out.println(isValidSudoku(board)?"true":"false");
//		
//		solveSudoku( board );
//		for(int i=0;i<9;i++){
//			for(int j=0;j<9;j++){
//				System.out.print( board[i][j] + ", ");
//			}
//			System.out.println();
//		}
		
//		List<String[]> list = solveNQueens( 3 );
//		for(String[] strs:list){
//			for(String s:strs)
//				System.out.println(s);
//			System.out.println();
//		}
		
		String start = "qa", end = "sq";
		String[] words = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};//{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		Set<String> st = new HashSet<String>();
		for( String w:words)
			st.add( w );
		
		List<List<String>> list = findLadders( start, end, st);
		for(List<String> strs:list){
			for(String s:strs)
				System.out.print(s+", ");
			System.out.println();
		}

//		List<String> list = restoreIpAddresses( "19216811" );//
//		for(String s:list){
//			System.out.println(s);
//		}
		
//		System.out.println( totalNQueens(4) );
	}
	
	public void solveSudoku(char[][] board) {
        if( null==board || 9!=board.length )
            return;
        if( null==board[0] || 9!=board[0].length )
            return;
        fillSudoku( board );
    }
    public boolean fillSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(char k = '1'; k<='9';k++){
                        board[i][j] = k;
                        if( isValidSudoku( board, i, j ) && fillSudoku( board ) ){
                                return true;
                        }
                    }
                    board[i][j] = '.';
                    return false;
                }
            }
        }
        return true;
    }
	
    public boolean isValidSudoku(char[][] board, int m, int n) {           
        Set<Character> set = new HashSet<>();
        for(int j=0;j<9;j++){
            if( board[m][j] != '.' && !set.add( board[m][j]) ){
                    return false;
            }
        }
        
        set.clear();
        for(int i=0;i<9;i++){
            if( board[i][n] !='.' && !set.add(board[i][n]) )
                return false;
        }
                
        set.clear();
        for( int p=0;p<3;p++){
            for( int q=0;q<3;q++){
                char ch = board[(m/3)*3+p][(n/3)*3+q];
                if( ch != '.' && ! set.add( ch )){
                    return false;
                }
            }
        }
        return true;
    }
	
    public List<String[]> solveNQueens(int n) {//leetcode 51
        List<String[]> res = new LinkedList<String[]>();
        if( n<1 || (n>1&&n<4) )
            return res;
            
        List<Integer> cols = new ArrayList<Integer>();
        solveNQueens_helper(n, cols, res );
        return res;
    }   
    public void solveNQueens_helper(int n, List<Integer> cols, List<String[]> res ){
        if( n==cols.size() ){
            String[] strs = new String[n];
            char[] chs = new char[n];
            for(int i=0;i<n;i++)
                chs[i] = '.';
            for(int i=0;i<n;i++){
                chs[cols.get(i)] = 'Q';
                strs[i] = new String( chs );
                chs[cols.get(i)] = '.';
            }
            res.add( strs );
            return;
        }
        
        for( int i=0;i<n;i++){
            if( isValid_solveNQueens( cols, i ) ){
                cols.add( i );
                solveNQueens_helper(n, cols, res );
                cols.remove( cols.size()-1 );
            }       
        }
    }    
    public boolean isValid_solveNQueens( List<Integer> cols, int col ){
        for( int i=0;i<cols.size();i++ ){
            int x = cols.get( i );
            if( col==x )
                return false;
            if( cols.size()-i==col-x || cols.size()-i==x-col )
                return false;
        }
        return true;
    }

    public int totalNQueens(int n) {//leetcode 52
        if( 1==n )
            return 1;
        if( n<4 )
            return 0;
            
        List<Integer> cols = new ArrayList<Integer>();
        return totalNQueens_helper( n, cols, 0 );
    }    
    public int totalNQueens_helper( int n, List<Integer> cols, int num ){
        if( n==cols.size() )
            return num+1;
            
        for(int i=0;i<n;i++){
            if( isValid_totalNQueens(cols,i) ){
                cols.add( i );
                num = totalNQueens_helper( n, cols, num );
                cols.remove( cols.size()-1);
            }
        }
        return num;
    }    
    public boolean isValid_totalNQueens( List<Integer> cols, int col ){
        for(int i=0;i<cols.size();i++){
            int x = cols.get( i );
            if( x==col )
                return false;
            if( x-col==i-cols.size()|| col-x==i-cols.size() )
                return false;
        }
        return true;
    }

    public List<String> restoreIpAddresses0(String s) {//leetcode 93
        return restoreIpAddresses_helper(s, 0, 4);
    }    
    public List<String> restoreIpAddresses_helper(String s, int tbegin, int num) {
        List<String> res = new LinkedList<String>();
        if( s.length()-tbegin>3*num || s.length()-tbegin<num)
        	return res;
        if( 1==num ){
            String sub = s.substring(tbegin);
            if( isValidIpAddresses(sub)  )
                res.add( sub );
            return res;
        }
        for(int i=1;i<4 && tbegin+i<=s.length();i++){
            String sub = s.substring(tbegin, tbegin+i);            
            if( isValidIpAddresses( sub) ){
                List<String> subs = restoreIpAddresses_helper(s, tbegin+i, num-1);
                for(String x:subs)
                    res.add( sub+"."+x );
            }else
                break;
        }
        return res;
    }   
    public boolean isValidIpAddresses( String s){
    	return Integer.parseInt( s )<256 && ( s.charAt(0)!='0' || 1==s.length() );
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<String>();
        if( s.length()<4 || s.length()>12 )
        	return res;
        int ip1=0, ip2=0, ip3=0, ip4=0;
        for(int i=1;i<4&&i+2<s.length();i++){
            ip1 = Integer.parseInt( s.substring(0,i));
            if( ip1>255 || ('0'==s.charAt(0)&&i>1) )
                break;
            for(int j=1;j<4&&i+j+1<s.length();j++){
                ip2 = Integer.parseInt( s.substring(i,i+j) );
                if( ip2>255 || ('0'==s.charAt(i)&&j>1))
                    break;
                for(int k=1; j<4&&i+j+k<s.length();k++){
                    ip3 = Integer.parseInt( s.substring(i+j,i+j+k) );
                    if( ip3>255 || ('0'==s.charAt(i+j)&&k>1) )
                        break;
                    ip4 = Integer.parseInt( s.substring(i+j+k) );
                    if( ip4>255 || i+j+k+3<s.length() || ('0'==s.charAt(i+j+k)&&i+j+k+1<s.length()))
                        continue;
                    res.add( ip1+"."+ip2+"."+ip3+"."+ip4);
                }            
            }       
        }
        return res;
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {//leetcode 126
        List<List<String>> res = new ArrayList<List<String>>();
        dict.add( start );
        Map<String, List<String>> map = new HashMap<String, List<String>>();//prev, post
        Set<String> interm = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();
        q.offer( end );
        dict.remove( end );
        int nums = 1;
        
        while( dict.contains(start) && !q.isEmpty() ){
            int Len = q.size();
            for(int i=0;i<Len;i++){
                String word = q.poll();
                List<String> nns = findNeighbors(dict, word);
                for( String nn:nns ){
                    if(interm.add( nn ))
                        q.offer( nn );
                    if( !map.containsKey(nn) )
                        map.put( nn, new LinkedList<String>() );
                    map.get( nn ).add( word );
                }
            }
            dict.removeAll( interm );
            interm.clear();
            nums++;
        }
        if( dict.contains(start) )
        	return res;
        
        List<String> l = new LinkedList<String>();
        l.add( start );
        res.add( l );
        for( int k=1; k<nums;k++ ){
            int Len = res.size();
            for(int i=0;i<Len;i++){
                List<String> list = res.get(0);
                String w = list.get( list.size()-1 );                
                if( map.containsKey(w) ){
                	List<String> next = map.get( w );
	            	for( String n:next ){
	            		List<String> temp = new LinkedList<String>( list );
	                    temp.add( n );
	                    res.add( temp );
	            	}
                }
                res.remove( 0 );
            }
        }
        return res;
    }    
    public List<String> findNeighbors(Set<String> dict, String word){
        List<String> res = new LinkedList<String>();
        char[] chs = word.toCharArray();
        for(int i=0;i<chs.length;i++){
            char org = chs[i];
            for(char j='a';j<='z';j++){
                if( j!=org){
                    chs[i] = j;
                    if( dict.contains( new String(chs) ) )
                        res.add( new String(chs) );
                }
            }
            chs[i] = org;
        } 
        return res;
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {//leetcode 127
        wordDict.add( endWord );
        Queue<String> q = new LinkedList<String>();
        Set<String> levels = new HashSet<String>();
        q.offer( beginWord );
        wordDict.remove( beginWord );
        int nums = 1;
        
        while( !q.isEmpty() && wordDict.contains( endWord)  ){
            int L = q.size();
            for(int i=0;i<L;i++){
                String prev = q.poll();
                List<String> nns = findNeighbors_ladderLength( prev, wordDict );
                for(String nn:nns){
                    if( levels.add(nn) )
                        q.offer( nn ); 
                }
            }
            wordDict.removeAll( levels );
            levels.clear();
            nums++;
        }
        return wordDict.contains( endWord)?0:nums;
    }
    
    public List<String> findNeighbors_ladderLength( String word, Set<String> wordDict ){
        List<String> res = new LinkedList<String>();
        char[] chs = word.toCharArray();
        for(int i=0;i<chs.length;i++){
            char org = chs[i];
            for(char j='a';j<='z';j++){
                chs[i] = j;
                if( j!=org && wordDict.contains(new String(chs) ) ){
                    res.add( new String(chs) );
                }
            }
            chs[i] = org;
        }
        return res;
    }
}
	
public class PermutationCombinationSubset_31_39_40_46_47_60_77_78_89_90_118_119_216{//pascal triangle
	public PermutationCombinationSubset_31_39_40_46_47_60_77_78_89_90_118_119_216(){}
	
	public void excute(){
//		int[] A = {2,2,2,2,2};
//		nextPermutation( A );
		List<Integer> A = getPascalTriangleRow(8);
		for(int a:A)
			System.out.print(a+", ");
		System.out.println();
		
//		List<List<Integer>> list = combinationSum2( A, 8);
//		List<List<Integer>> list = combinationSum3( 3, 9);
//		List<List<Integer>> list = subsetsWithDup( A );
//		for( List<Integer> l:list){
//			for(int x:l)
//				System.out.print( x+", ");
//			System.out.println();
//		}
//		System.out.println( list.size());
		
//		System.out.println( getPermutation(4, 14) );
	}
		
	public void nextPermutation(int[] nums) {//leetcode 31
        if(null==nums || 2>nums.length)
            return;
            
        for(int i=nums.length-1; i>=0; i--){
            if( i==0 ){
                reverseArray( nums, i); 
            }else if( nums[i-1]<nums[i] ){
                int j = nums.length-1;
                for( ; j>i-1; j--){
                    if( nums[j]>nums[i-1])
                        break;
                }
                nums[i-1] = nums[i-1]^nums[j];
                nums[j] = nums[i-1]^nums[j];
                nums[i-1] = nums[i-1]^nums[j];
                reverseArray( nums, i);    
                break;
            }
        }
    }
    
    public void reverseArray( int[] nums, int tbegin ){
        int i = tbegin, j = nums.length-1;
        while(i<j){
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {//leetcode 39
        if( null==candidates || 0==candidates.length)
            return (new ArrayList<List<Integer>>());
            
        Arrays.sort( candidates );
        return combinationSum_helper(candidates, 0, target);        
    }    
    public List<List<Integer>> combinationSum_helper(int[] candidates, int tbegin, int target){//leetcode 39
        if( target<candidates[tbegin] || tbegin==candidates.length )
            return (new ArrayList<List<Integer>>());
        if( target==candidates[tbegin] ){
        	List<Integer> sub = new ArrayList<Integer>();
        	sub.add( target );
        	List<List<Integer>> temp = new ArrayList<List<Integer>>();
        	temp.add(sub);
            return temp;
        }
               
         int i = tbegin+1;
        while(i<candidates.length && candidates[i-1]==candidates[i])
            i++;
        List<List<Integer>> res = combinationSum_helper(candidates, tbegin, target-candidates[tbegin]);
        for(int j = 0;j<res.size();j++){
        	List<Integer> temp = res.get(j);
            temp.add(0, candidates[tbegin]);
            res.set(j,  temp);
        }
            
        res.addAll( combinationSum_helper(candidates, i, target) );
        return res;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {//leetcode 40
        if( null==candidates || 0==candidates.length )
            return ( new ArrayList<List<Integer>>());
            
        Arrays.sort( candidates );
        return combinationSum2_helper(candidates, candidates.length-1, target);
    }
    
    public List<List<Integer>> combinationSum2_helper(int[] A, int tend, int target) {
    	if( tend<0 || target<A[0] )
            return ( new ArrayList<List<Integer>>());
        while( tend>=0 && target<A[tend] )
            tend--;
               
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int i = tend-1;
        while(i>=0 && A[i]==A[tend])
            i--;
        for(int j=1;j<=tend-i;j++){
        	if( j*A[tend]==target){
        		ArrayList<Integer> x = new ArrayList<Integer>();
        		for(int k=0;k<j;k++)
                    x.add( A[tend]);
                res.add( x );
        	}
            List<List<Integer>> sub = combinationSum2_helper(A, i, target-j*A[tend]);
            for( List<Integer> x:sub){
                for(int k=0;k<j;k++)
                    x.add( A[tend]);
                res.add( x );
            }
        }
        
        res.addAll( combinationSum2_helper(A, i, target) );        
        return res;
    }

    public List<List<Integer>> combinationSum3(int k, int target) {
        return combinationSum3_helper(k, target, 9);       
    }
    
    public List<List<Integer>> combinationSum3_helper(int k, int target, int upper) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if( 0==k || 2*target>k*(2*upper-k+1) || 2*target<k*(1+k))
            return res;
        if( 1==k && target>0 && target<=upper ){
            List<Integer> x = new ArrayList<Integer>();
            x.add( target );
            res.add( x );
            return res;
        }            
        
        List<List<Integer>> sub = combinationSum3_helper(k-1, target-upper, upper-1);
        for( List<Integer> x:sub ){
            x.add( upper );
            res.add( x );
        }
        
        res.addAll( combinationSum3_helper(k, target, upper-1) );
        return res;
    }

    public List<List<Integer>> permute0(int[] nums) {//leetcode 46
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( null==nums || 0==nums.length )
            return res;
            
        List<Integer> init = new ArrayList<Integer>();
        init.add( nums[0] );
        res.add( init );
        
        for(int i=1;i<nums.length;i++){
            int Len = res.size();
            for( int j=0;j<Len;j++){
                List<Integer> old = res.get(0);
                res.remove( 0 );
                for( int k=0;k<=old.size();k++){
                    List<Integer> sub = new ArrayList<Integer>( old );
                    sub.add( k, nums[i]);
                    res.add( sub );
                }
            }        
        }
        return res;
    }
    
    public List<List<Integer>> permute(int[] nums) {//leetcode 45
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if( null==nums || 0==nums.length )
            return res;
            
        List<Integer> init = new ArrayList<Integer>();
        init.add( nums[0] );
        res.add( init );
        
        for( int i=1; i<nums.length;i++){
            List<List<Integer>> copy = new ArrayList<List<Integer>>( res );
            res.clear();
            for( List<Integer> old:copy){
                for(int j = 0;j<=old.size();j++){
                    List<Integer> sub = new ArrayList<Integer>(old);
                    sub.add( j, nums[i] );
                    res.add( sub );
                }
            }
        }
        return res;       
    }
    
    public List<List<Integer>> permuteUnique0(int[] nums) {//leetcode 46
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if( null==nums || 0==nums.length )
            return res;
            
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int x:nums )
            map.put( x, map.containsKey(x)?map.get(x)+1:1);
        
        for( int x: map.keySet() ){
            if( res.isEmpty() ){
                List<Integer> temp = new ArrayList<Integer>();
                temp.add( x );
                res.add( temp );
            }else{
                List<List<Integer>> copy = new ArrayList<List<Integer>>( res );
                res.clear();
                for( List<Integer> old:copy ){
                    for(int i=0;i<=old.size();i++){
                        List<Integer> temp = new ArrayList<Integer>( old );
                        temp.add(i, x );
                        res.add( temp );
                    }
                }
            }
            
            for(int k=1;k<map.get( x );k++){
                List<List<Integer>> copy = new ArrayList<List<Integer>>( res );
                res.clear();
                for( List<Integer> old:copy ){
                    int j = old.size();
                    while(j>0 && old.get(j-1)!=x )
                        j--;
                    for(;j<=old.size();j++){
                        List<Integer> temp = new ArrayList<Integer>( old );
                        temp.add(j, x );
                        res.add( temp );
                    }
                }
            }    
        }
        return res;       
    }

    public List<List<Integer>> permuteUnique(int[] nums) {//leetcode 46
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if( null==nums || 0==nums.length )
            return res;
            
        List<Integer> init = new ArrayList<Integer>();
        init.add( nums[0] );
        res.add( init );
        
        for( int i=1; i<nums.length;i++){
            List<List<Integer>> copy = new ArrayList<List<Integer>>( res );
            Set<List<Integer>> set = new HashSet<List<Integer>>();
            res.clear();
            for( List<Integer> old:copy){
                for(int j = 0;j<=old.size();j++){
                    List<Integer> sub = new ArrayList<Integer>(old);
                    sub.add( j, nums[i] );
                    if( set.add(sub) )
                        res.add( sub );
                }
            }
        }
        return res;       
    }

    public String getPermutation(int n, int k) {//leetcode 60
        int[] nums = new int[n];
        nums[0] = 1;
        for(int i=1;i<n;i++)
            nums[i] = nums[i-1]*(i+1);           
        if( k<1 || k>nums[n-1])
            return "";
        List<Integer> list = new ArrayList<Integer>();
        for(int i=1;i<=n;i++)
            list.add( i );
            
        StringBuilder str = new StringBuilder();
        for(int i=0;i<n-1;i++){
            int j = (k-1)/nums[n-2-i];
            str.append( list.get(j) );
            list.remove( j );
            k -= j*nums[n-2-i];
        }
        str.append( list.get(0) );
        return str.toString();
    }

    public List<List<Integer>> combine0(int n, int k) {//leetcode 77//iterative
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( n<k || 1>k )
            return res;
            
        for(int i=1;i<=n-k+1;i++){
            List<Integer> list = new LinkedList<Integer>();
            list.add( i );
            res.add( list );
        }
        
        while(!res.isEmpty()&&res.get(0).size()<k ){
            List<Integer> list = res.get( 0 );
            int L = list.size();
            res.remove( 0 );
            for(int i=list.get(L-1)+1;i<=n-k+L+1;i++){
                List<Integer> temp = new LinkedList<Integer>( list );
                temp.add( i );
                res.add( temp );
            }
        }
        return res;
    }

    public List<List<Integer>> combine(int n, int k) {//leetcode 77: recursive
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( n<k || 1>k )
            return res;
        if( 1==k ){
            for(int i=1;i<=n;i++){
                List<Integer> list = new LinkedList<Integer>();
                list.add( i );
                res.add( list );
            }
        }else{
            res.addAll( combine(n-1, k) );
            List<List<Integer>> temp = combine(n-1, k-1);
            for( List<Integer> x:temp){
                x.add( n );
                res.add( x );
            }       
        }
        return res;           
    }

    public List<List<Integer>> subsets0(int[] nums) {//leetcode 78
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( null==nums || 0==nums.length )
            return res;
            
        Arrays.sort( nums );            
        for(int i=0;i<Math.pow(2,nums.length);i++){
            int bits = i, j = 0;
            List<Integer> temp = new LinkedList<Integer>();
            while( bits>0 ){
                if( 1==(bits&1) )
                    temp.add( nums[j] );
                j++;
                bits >>= 1;
            }
            res.add( temp );
        }
        return res;
    }
    
    public List<List<Integer>> subsets(int[] nums) {      //leetcode 78
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add( new LinkedList<Integer>() );       
        for(int x:nums){
            List<List<Integer>> hold = new LinkedList<List<Integer>>();
            for( List<Integer> temp:res ){
                List<Integer> sub = new LinkedList<Integer>( temp );
                sub.add( x );
                hold.add( sub );
            }
            res.addAll( hold );
        }
        return res;
    }

    public List<Integer> grayCode(int n) {//leetcode 89
        List<Integer> res = new ArrayList<Integer>();
        if( n<0 )
            return res;
            
        res.add( 0 );
        int bit = 1;
        for(int i=0;i<n;i++){
            int L = res.size();
            for(int j=L-1;j>=0;j--)
                res.add( bit+res.get(j) );
            bit <<= 1;
        }
        return res;
    }

    public List<List<Integer>> subsetsWithDup0(int[] nums) {//leetcode 90
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int x:nums)
            map.put(x, map.containsKey(x)?map.get(x)+1:1);
            
        int[] vals = new int[map.size()];
        int[] count = new int[map.size()];
        int i = 0;
        for(int x:map.keySet()){
            vals[i] = x;
            count[i++] += map.get(x);
        }
        
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        for(i=0;i<Math.pow(2,map.size());i++){
            List<List<Integer>> temp = new LinkedList<List<Integer>>();
            int bits = i, j = 0;
            while( bits>0 ){
                if( 1==(bits&1) ){
                    if( temp.isEmpty() ){
                        List<Integer> sub = new ArrayList<Integer>();
                        sub.add( vals[j] );
                        temp.add( sub );
                    }else{
                        for(List<Integer> x:temp )
                            x.add( vals[j] );
                    }       

                    int L = temp.size();
                    for(int k=1;k<count[j];k++){
                    	int LL = temp.size();
                        for(int kk=L;kk>0;kk--){
                        	List<Integer> sub = new ArrayList<Integer>( temp.get(LL-kk) );
                            sub.add( vals[j] );
                            temp.add( sub );
                        }
                    }
                }
                bits >>= 1;
                j++;
            }
            if( temp.isEmpty() )
            	res.add( new LinkedList<Integer>());
            else res.addAll( temp );
        }
        return res;
    }

    public List<List<Integer>> subsetsWithDup1(int[] nums) {//leetcode 90
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int x:nums)
            map.put(x, map.containsKey(x)?map.get(x)+1:1);
        
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add( new LinkedList<Integer>() );
        for(int x:map.keySet()){
            int count = map.get( x );
            List<List<Integer>> temp = new LinkedList<List<Integer>>( );
            for(int k=0;k<count;k++){
                for( List<Integer> ls:res ){
                	List<Integer> list = new LinkedList<Integer>( ls );
                	for(int i=0;i<=k;i++)
                		list.add( x );      
                	temp.add( list );
                }
            } 
            res.addAll( temp );
        }
        return res;
    }
    
    public List<List<Integer>> subsetsWithDup2(int[] nums) {      
        Set<List<Integer>> st = new HashSet<List<Integer>>();
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add( new LinkedList<Integer>() );       
        for(int x:nums){
            List<List<Integer>> hold = new LinkedList<List<Integer>>();
            for( List<Integer> temp:res ){
                List<Integer> sub = new LinkedList<Integer>( temp );
                sub.add( x );
                if( st.add( sub ) )
                    hold.add( sub );
            }
            res.addAll( hold );
        }
        return res;
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {//leetcode 90
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add( new LinkedList<Integer>() );       
        Arrays.sort( nums );
        for(int i=0,count=0;i<nums.length;i+=count){
            count = 1;
            while(i+count<nums.length&&nums[i]==nums[i+count])
                count++;
            
            List<List<Integer>> hold = new LinkedList<List<Integer>>();
            for( List<Integer> temp:res ){
                for(int k=0;k<count;k++){
                    List<Integer> sub = new LinkedList<Integer>( temp );
                    for(int kk=0;kk<=k;kk++)
                        sub.add( nums[i] );
                    hold.add( sub );
                }
            }
            res.addAll( hold );
        }
        return res;
    }

    public List<List<Integer>> generatePascalTriangle(int numRows) {//leetcode 118
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        for(int i=0;i<numRows;i++){
            List<Integer> sub = new LinkedList<Integer>();
            sub.add( 1 );
            if( i>0 ){
                List<Integer> temp = res.get( i-1 );
                for(int j=1;j<i;j++)
                    sub.add( temp.get(j-1)+temp.get(j) );
                sub.add( 1 );
            }
            res.add( sub );
        }
        return res;
    }

    public List<Integer> getPascalTriangleRow(int rowIndex) {//leetcode 119
        List<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<rowIndex+1;i++){
            for(int j=i-1;j>0;j--){
                res.set(j, res.get(j)+res.get(j-1));
            }
            res.add( 1 );
        }
        return res;
    }
    
}
	
public class OtherStrings_27_30_58_68_71_125{//find substring, length of substring, is Palindrome
	public OtherStrings_27_30_58_68_71_125(){}
	
	public void excute(){
//		String haystack = "ABC ABCDAB ABCDABCDABDE";
//		String needle = "BCDABD";
//		System.out.println( strStr_kmp(haystack, needle) );
		
//		String s = "Hello World   ";
//		String[] words = {"oob","arf"};
//		List<Integer> L = findSubstring(s,  words);
//		for(int t:L)
//			System.out.print(t+", ");
//		System.out.println();
		
//		System.out.println( lengthOfLastWord(s));
		
//		String[] words = {"Listen","to","many,","speak","to","a","few."};
//		List<String> list = fullJustify( words, 6);
//		for(String x:list)
//			System.out.println(x);
		
		String path = "/";
		System.out.println( simplifyPath( path ));
	}
	
	public int strStr(String haystack, String needle) {
        if( 0==haystack.length() || 0==needle.length() || haystack.length()<needle.length())
            return -1;
    
        int i = 0;
        while( i<haystack.length() ){
            int j = haystack.indexOf(needle.charAt(0),i);
            if( -1==j || haystack.length()-j < needle.length() )
                return -1;
                
            if( haystack.substring(j,j+needle.length()).equals( needle) )
                return j;
            
            i = j+1;            
        }
        return -1;
    }

	public int strStr_kmp(String haystack, String needle) {
        if( 0==needle.length() )
            return 0;
        if( haystack.length() < needle.length()  )
            return -1;
            
        int[] table = generateKMPTable( needle );
        int i = 0, j = 0;
        while( i+j<haystack.length() ){
            if( haystack.charAt(i+j)==needle.charAt(j) ){
                if( (++j)==needle.length() )
                    return i;
            }else if(table[j]>-1){
                i = i + j - table[j];
                j = table[j];
            }else{
                i++;
                j = 0;
            }
        }        
        return -1;
    }
    
    private int[] generateKMPTable( String needle ){
        int[] table = new int[needle.length()];
        table[0] = -1;
        
        int i = 2, j = 0;
        while(i<needle.length()){
            if( needle.charAt(j)==needle.charAt(i-1) ){
                table[i++] = ++j;
            }else if(j>0){
                j = table[j];
            }else{
                table[i++] = 0;
            }       
        }       
        return table;
    }
	
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<Integer>();
        if( null==words || 0==words.length)
            return res;            
        int N = words.length, L = words[0].length();
        if( null==s || N*L>s.length() )
            return res;
          
        Map<String, Integer> dict = new HashMap<>();
        for( String w:words ){
            dict.put( w, dict.containsKey(w)?dict.get(w)+1:1 );
        }
        
        for(int i=0; i+N*L<=s.length(); i++ ){
        	Map<String, Integer> bag = new HashMap<>( dict );
            for(int j=0;j<N;j++){
            	String w = s.substring( i+j*L, i+L+j*L );
            	if( bag.containsKey(w) ){
            		if( 1==bag.get( w ) )
            			bag.remove( w );
            		else
            			bag.put(w, bag.get(w)-1);
            	}else
            		break;            	
            }
        	if( bag.isEmpty() )
        		res.add(i);
        }       
        return res;
    }        

    public int lengthOfLastWord(String s) {    //leetcode 58
        int i=s.length()-1;
        while(i>=0 && s.charAt(i)==' ')
            i--;
        if( -1==i )
            return 0;
        return i-s.lastIndexOf(' ', i);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new LinkedList<String>();
        if( null==words || 0==words.length )
            return res;
            
        int i = 0;
        while( i<words.length ){
            int nchs = words[i].length(), j = 0;
            while( i+(++j)<words.length && nchs+words[i+j].length()+1<=maxWidth )
                nchs += words[i+j].length()+1;
                
            StringBuilder str = new StringBuilder();
            int nspace = maxWidth - nchs;
            if( 1==j || i+j==words.length ){//last line
                str.append( words[i++] );
                for(int k=1;k<j;k++){
                    str.append( ' ');
                    str.append( words[i++] );
                }
                for(int k=0;k<nspace;k++)
                	str.append( ' ');
            }else{//evenly distributed
                int nsp = 1+nspace/(j-1), nk = nspace%(j-1);
                str.append( words[i++] );
                for(int k=1;k<j;k++){
                    for(int p=0;p<nsp;p++)
                        str.append( ' ');
                    if( k<=nk )
                        str.append( ' ' );
                    str.append( words[i++] );
                }
            }
            res.add( str.toString() );
        }
        return res;
    }

    public String simplifyPath(String path) {//leetcode 71
        if( null==path || 0==path.length() )
            return "/";
            
        String[] strs = path.split("/+");
        Stack<String> stk = new Stack<String>();
        for(String s:strs){
            if( s.length()>0 && !s.equals( "." ) ){
                if( s.equals("..") ){
                    if( !stk.isEmpty() )
                        stk.pop();
                }else{
                    stk.push( s );
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stk.isEmpty()){
            sb.insert( 0, '/'+ stk.pop());
        }        
        return 0==sb.length()?"/":sb.toString();        
    }

    public boolean isPalindrome(String s) {//leetcode 125
        s = s.toLowerCase();
        int i = 0, j=s.length()-1;
        while(i<j){
            if( !isAlphanumeric( s.charAt(i))){
                i++;
            }else if( !isAlphanumeric(s.charAt(j))){
                j--;
            }else{
                if( s.charAt(i) != s.charAt(j) )
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }    
    public boolean isAlphanumeric(char ch){
        return (ch>='a'&&ch<='z') || ( ch>='0' && ch<='9');
    }
}
	
public class ArrayReoderRemove_26_75_80_88{//remove duplicates, re-order, 
	public ArrayReoderRemove_26_75_80_88(){}
	
	public void excute(){
		int[] A = {1,2,3,6};
		int[] B = {1,4,5,7};
		merge(A,0,B,4);
		int len = A.length;
//		int len = removeDuplicatesSortedArray( A);
//		sortColors( A );
		for(int i=0;i<len;i++)
			System.out.print( A[i]+", ");
		System.out.println();
		
	}
	
	public int removeDuplicates(int[] nums) {//O(n) space
        int fast = 0, slow = 0;
        Set<Integer> set = new HashSet<Integer>();       
        while(fast<nums.length){
            if( set.add( nums[fast] ) ){
                nums[slow++] = nums[fast];
            }      
            fast++;
        }
        return slow;
    }
	
	public int removeDuplicates1(int[] nums) {//O(1) space
        if(null==nums || 0==nums.length )
            return 0;
            
        int j = 0;
        for(int i=1;i<nums.length;i++){
            if( nums[j]!=nums[i]){
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }

	public int removeElement(int[] nums, int val) {    
        int len = 0;
        for(int i=0;null!=nums && i<nums.length;i++){
            if( nums[i]!=val ){
                nums[len++] = nums[i];
            }
        }
        return len;
    }
	
	public void sortColors0(int[] nums) {//count sorting
        if( null==nums || 2>nums.length )
            return;
            
        int[] counts = new int[3];
        for( int x:nums )
            counts[x]++;
        
        int up = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<counts[i];j++)
                nums[up+j] = i;
            up += counts[i];
        }        
    }
	
	public void sortColors(int[] nums) {//one-pass, constant space
        if( null==nums || 2>nums.length )
            return;
            
        int i = 0, j = nums.length-1, k=0;        
        while( k<=j ){
            if( 0==nums[k] && k!=i++ ){
                nums[i-1] = 0;
                nums[k] = 1;
            }else if( 2==nums[k] ){
                while(j>k && 2==nums[j])
                    j--;
                nums[k] = nums[j];
                nums[j--] = 2;
                continue;
            }
            k++;
        }    
    }

	public int removeDuplicatesSortedArray(int[] nums) { //from sorted array, leetcode 80
        if( nums.length <3  )
            return nums.length;
            
        int i = 2, j = 1;
        while(j+1<nums.length ){
            if( nums[i-2]!=nums[++j] )
                nums[i++] = nums[j];
        }
        return i;
    }
	
	public void merge(int[] nums1, int m, int[] nums2, int n) {//leetcode 88
        int i = m-1, j = n-1;
        for(int k=m+n-1;k>=0;k--){
            if(i<0)
                nums1[k] = nums2[j--];
            else if(j<0)
                nums1[k] = nums1[i--];
            else{
                nums1[k] = nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
            }
        }
    }
}
	
public class Parenthese_20_22_32{//parenthese
	public Parenthese_20_22_32(){}
	
	public void excute(){
//		String s = "([])()";
//		System.out.println(isValid(s)?"true":"false");
//		
//		int n = 3;
//		List<String> strs = generateParenthesis( n );
//		for(String x:strs)
//			System.out.println(x);
		
		String s = "()";
		System.out.println( longestValidParentheses(s) );
	}
	
	public boolean isValid(String s) {// is valid parenthese
        if( null==s || 0==s.length() )
            return true;
        if( 1==s.length()%2 )
            return false;
            
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 0);map.put(')', 1);
        map.put('[', 2);map.put(']', 3);
        map.put('{', 4);map.put('}', 5);
        
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0;i<s.length();i++){
            int ch = map.get( s.charAt(i) );
            if( ch%2==0 ){
                stk.push( ch );
            }else{
                if( stk.isEmpty() || stk.pop()+1!=ch )
                    return false;
            }
        }
        return stk.isEmpty()?true:false;
    }
	
	public List<String> generateParenthesis0(int n) {
        return generateParenthesis0(n, n);       
    }
    
    public List<String> generateParenthesis0( int left, int right ) {
        List<String> res = new LinkedList<String>();
        if( 0==left && 0==right )
            return res;
        
        if( left>0 ){
            List<String> strs = generateParenthesis0( left-1, right );
            if( strs.isEmpty() )
            	res.add( "(");
            else{
	            for(String s:strs)
	                res.add( '('+s );
            }           
        }
        if( left<right ){
            List<String> strs = generateParenthesis0( left, right-1 );
            if( strs.isEmpty() )
            	res.add( ")");
            else{
	            for(String s:strs)
	                res.add( ')'+s );
            }           
        }       
        return res;
    }
    
    public List<String> generateParenthesis(int n) {
        return generateParenthesis(n, n, "");       
    }
    
    public List<String> generateParenthesis( int left, int right, String s ) {
        List<String> res = new LinkedList<String>();
        if( 0==left && 0==right ){
            res.add( s );
        }else{        
	        if( left>0 ){
	            res.addAll( generateParenthesis( left-1, right, s+'(' ) );         
	        }
	        if( left<right ){
	            res.addAll( generateParenthesis( left, right-1, s+')' ) );          
	        }
        }        
        return res;
    }

    public int longestValidParentheses0(String s) {//leetcode 32
        if( null==s || 2>s.length() )
            return 0;
        
        Stack<Integer> stk = new Stack<Integer>();//(-0, )-1
        stk.push(-11);
        for(int i=0;i<s.length();i++){
        	if( s.charAt(i)==')' ){
        		if( stk.peek()%10==0 )
        			stk.pop();
        		else
        			stk.push( i*10+1 );
        	}else
        		stk.push(i*10);
        }
        
        int maxLen = 0, i = 0, j = s.length()*10;
        while( !stk.isEmpty() ){
        	i = stk.pop();
        	maxLen = Math.max(maxLen, j/10-i/10-1);
        	j = i;
        }        
        return maxLen;
    }
    
    public int longestValidParentheses(String s) {//leetcode 32
        if( null==s || 2>s.length() )
            return 0;
        
        int leftmost = -1;
        int maxLen = 0;
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0;i<s.length();i++){
            if( s.charAt(i)=='(' ){
                stk.push( i );
            }else{
                if( stk.isEmpty() ){
                    leftmost = i;
                }else{
                    stk.pop();
                    maxLen = Math.max( maxLen, i-(stk.isEmpty()?leftmost:stk.peek()));
                }          
            }
        }           
        return maxLen;
    }
}
	
public class LinkedList_19_21_23_24_25_61_82_86_92{//merge, re-order, remove
	LinkedList_19_21_23_24_25_61_82_86_92(){}
	
	public void excute(){
		int[] A = {1};
//		int[] B = {2, 4, 6,10};
		ListNode l1 = generateListNode(A);
//		ListNode l2 = generateListNode(B);
//		ListNode n = removeNthFromEnd(l1,0);
//		n = mergeTwoLists(l1,l2);
		ListNode n = reverseBetween( l1,1,1 );
//		ListNode n = rotateRight(l1, 2);
		n.print();
		
//		int[] C = {2, 5, 8, 11, 25, 30};
//		ListNode[] ls = {generateListNode(A), generateListNode(B), generateListNode(C)};
//		ListNode n = mergeKLists( ls );
//		n.print();
	}
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if( 1>n ) return head;
	            
	    ListNode fast = head, slow = head;
	    int i = 0;
	    for(;i<n && null!=fast;i++){
	    	fast = fast.next;
	    }
	    if( null==fast ){
	    	return i==n?head.next:head;
	    }
	        
	    fast = fast.next;
	    while( null!=fast ){
	    	fast = fast.next;
	        slow = slow.next;
	    }
	    slow.next = slow.next.next;
	    return head;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), p = head;
        while( null!=l1 && null!=l2 ){
            if( l1.val<l2.val ){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = null==l1?l2:l1;
        
        return head.next;
    }
	
	public ListNode mergeKLists(ListNode[] lists) {
        if(null==lists || 0==lists.length)
            return null;
        if(1==lists.length)
            return lists[0];
        
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val-b.val;
            }
        });
        
        for(ListNode n:lists){
            if( null!=n){
                heap.offer( n );
            }
        }
        
        ListNode head = new ListNode(0), p = head;
        while( !heap.isEmpty() ){
            ListNode n = heap.poll();
            p.next = n;
            p = n;
            if( null!=n.next ){
                heap.offer( n.next );
            }
        }
        return head.next;
    }
	
	public ListNode swapPairs(ListNode head) {    
        ListNode p = head, temp;
        ListNode list = new ListNode( 0 ), last = list;
        while( null!=p && null!=p.next ){
            temp = p;
            p = p.next;
            last.next = p;
            p = p.next;
            last = last.next;
            last.next = temp;
            last = last.next;        
        }
        last.next = null==p?null:p;
        
        return list.next;
    }
	
	public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head, first, cur;
        ListNode newHead = new ListNode(0), prev = newHead;
        while( k>1 ){
            int n = 0;
            while( n<k && null!=p ){
                p = p.next;
                n++;
            }
            if( n<k && null==p )
                break;
                
            p = head;
            first = null;
            n = 0;
            while( (n++)<k ){
                cur = first;
                first = p;
                p = p.next;
                first.next = cur;
            }
            prev.next = first;
            prev = head;
            head = p;
        }
        prev.next = head;        
        return newHead.next;
    }

	public ListNode rotateRight(ListNode head, int k) {//leetcode 61
        int n = 0;
        ListNode p = head, fast = head, slow = head;
        for(;null!=p;n++ )
            p = p.next;      
        k = 0==n?0:k%n;
        if( 0==k )
            return head;
        
        for(int i=0;i<k;i++)
            fast = fast.next;
        while( null!=fast.next){
            fast = fast.next;
            slow = slow.next;
        }
        p = slow.next;
        slow.next = null;
        fast.next = head;
        return p;
    }

	public ListNode deleteDuplicatesII(ListNode head) {//leetcode 82
        if( null==head )
            return head;
            
        ListNode next = new ListNode(0), np = next;
        ListNode p = head, cur = head.next;
        while( null!=cur ){
            if( p.val != cur.val ){
                if( p.next == cur ){
                    np.next = p;
                    np = p;
                }
                p = cur; 
            }
            cur = cur.next;
        }
        np.next = null==p.next?p:null;
        return next.next;
    }
	
	public ListNode deleteDuplicates(ListNode head) {//leetcode 83
        if( null==head )
            return head;
            
        ListNode p = head, cur = head.next;
        while( null!=cur ){
            if( p.val != cur.val ){
                p.next = cur;
                p = cur;               
            }
            cur = cur.next;
        }
        p.next = null;
        return head;
    }

	public ListNode partition(ListNode head, int x) {//leetcode 86
        ListNode small = new ListNode(-1), ps = small;
        ListNode large = new ListNode(-1), pl = large;
        
        while(null!= head){
            if( head.val <x ){
                ps.next = head;
                ps = head;
            }else{
                pl.next = head;
                pl = head;
            }   
            head = head.next;
        }
        
        ps.next = large.next;
        pl.next = null;
        return small.next;
    }

	public ListNode reverseBetween(ListNode head, int m, int n) {//leetcode 92
        ListNode p = null, pmn  = null, plast = null;
        if( 1==m ){
            pmn = head;
            head = new ListNode(-1);
            p = head;       
        }else{
            p = head;
            for(int i=2;i<m;i++)
                p = p.next;
            pmn = p.next;
        }
        plast = pmn;
        p.next = null;
        for(int i=m;i<=n;i++){
            ListNode temp = p.next;
            p.next = pmn;
            pmn = pmn.next;
            p.next.next = temp;
        }
        plast.next = pmn;           
        return 1==m?head.next:head;
    }
}
	
public class minMax1DArrayDP_11_42_45_53_55_70_84_91_120_121_122_123{//water trapping, jumps, maximum subarray, maximum rectangle, decode ways, stock
	
	public minMax1DArrayDP_11_42_45_53_55_70_84_91_120_121_122_123(){}
	
	public void excute(){
//		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//		System.out.println( maxArea(height) );
//		System.out.println( trap(height) );
		
//		int[] A = {2,6,5,6,2,3};//{2,4,3,1,2,4,3,4,1,1,3,2,4,3};
//		System.out.println( largestRectangleArea(A) );
		System.out.println( numDecodings("101") );
	}
	
	public int maxArea(int[] height) {
        if(null==height||2>height.length)
            return 0;
            
        int i=0, j=height.length-1;
        int maxVal = 0;
        while(j>i){
            if( height[i]<height[j] ){
                maxVal = Math.max(maxVal, (j-i)*height[i] );
                i++;
            }else{
                maxVal = Math.max(maxVal, (j-i)*height[j] );
                j--;
            }
        }
        return maxVal; 
    }
	
	public int trap(int[] height) {//leetcode 42
        if( null==height || 3>height.length )
            return 0;
            
        int i = 0, j = height.length-1;
        int res = 0, H = 0;
        while(i<j){
            H = Math.max(H, Math.min(height[i],height[j]) );
            if( height[i]>H){
                res += H - height[j];
                j--;
            }else{
                res += H - height[i];
                i++;
            }
        }
        return res;
    }

	public int jump0(int[] nums) {//leetcode 45: greedy
        if( null == nums || 1==nums.length )
            return 0;
          
        int res = 0;  
        int prev = 0, cur = 0;
        while( cur+1<nums.length ){
            int maxPos = 0;
            for(; prev<=cur; prev++)
                maxPos = Math.max(maxPos, prev+nums[prev] );
            cur = maxPos;
            res++;
        }
        return res;
    }
	
	public int jump(int[] nums) {//dp
        if( null == nums )
            return 0;
          
        int res = 0;
        int pos = 0, large = 0;  
        for(int i=0;i<nums.length-1;i++){
            large = Math.max( large, i+nums[i] );
            if( i==pos ){
                res++;
                pos = large;
            }
        }       
        return res;
    }

	public int maxSubArray(int[] nums) {//leetcode 53
        if( null==nums )
            return 0;
          
        int maxVal = Integer.MIN_VALUE, subSum = 0;  
        for(int x:nums){
            if( subSum<0 )
                subSum = x;
            else
                subSum += x;
            maxVal = Math.max(maxVal, subSum);
        }
        return maxVal;         
    }

	public boolean canJump(int[] nums) {//leetcode 55
        if( null==nums || 2>nums.length )
            return true;
            
        int cur = 0, far = 0;
        for(;cur<=far && far<nums.length-1; cur++){
            if( 0==nums[cur] && cur==far )
                return false;
            far = Math.max( far, cur+nums[cur] );
        }            
        return true;
    }

	public int climbStairs(int n) {
        if( n<=0 )
            return 0;
        if( n<3 )
            return n;
            
        int x1 = 1, x2 = 2;
        for(int i=3; i<=n;i++){
            x2 += x1;
            x1 = x2 - x1; 
        }
        return x2;
    }

	public int largestRectangleArea(int[] height) {   //leetcode 84
        int maxVal = 0;
        Stack<Integer> stk = new Stack<Integer>();
        for(int i=0;i<=height.length;i++){
            while( !stk.isEmpty() && (i==height.length||height[stk.peek()]>height[i]) ){
                int index = stk.pop();
                maxVal = Math.max(maxVal, height[index]*(stk.isEmpty()?i:(i-stk.peek()-1)));
            }
            stk.push( i );
        }
        return maxVal;
    }

	public int numDecodings(String s) {//leetcode 91
        if( null==s || 0==s.length() || '0'==s.charAt(0))
            return 0;
            
        int mid = 1, res = 1;
        for(int i=1; i<s.length();i++ ){
            int val1 = s.charAt(i)-'0';
            int val2 = val1+ 10*(s.charAt(i-1)-'0');
            int temp = 0; 
            if( val1>0 && val1<10 )
                temp += res;
            if( val2>9 && val2<27 )
                temp += mid;
            else if( 0==val1 )
                return 0;
            mid = res;
            res = temp;
        }
        return res;
    }

	public int minimumTotal(List<List<Integer>> triangle) {//leetcode 120
        if( null==triangle || 0==triangle.size() )
            return 0;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for(int i=1;i<triangle.size();i++){
            List<Integer> list = triangle.get(i);
            dp[i] = dp[i-1] + list.get(i);
            for(int j=i-1;j>0;j--)
                dp[j] = list.get(j) + Math.min(dp[j],dp[j-1]);
            dp[0] = dp[0] + list.get(0);
        }
        
        int minVal = Integer.MAX_VALUE;
        for( int x:dp )
            minVal = Math.min( minVal, x);
        return minVal;       
    }

	public int maxProfitOneTime0(int[] prices) {//leetcode 121
        int buy = Integer.MIN_VALUE, sell = 0;
        for(int x:prices ){
            sell = Math.max(sell, x+buy );
            buy = Math.max(-x, buy);        
        }
        return sell;
    }
	
	public int maxProfitOneTime(int[] prices) {//leetcode 121
        if( null==prices || 2>prices.length )
            return 0;
        int buy = prices[0], sell = prices[0];
        int maxVal = 0;
        for(int i=1;i<prices.length;i++ ){
            if( buy>prices[i]){
                buy = sell = prices[i];
            }else if( sell<prices[i]){
                sell = prices[i];
                maxVal = Math.max(maxVal, sell-buy);
            }
        }
        return maxVal;
    }

	public int maxProfitMultipleTimes(int[] prices) {//leetcode 122  
        int res = 0;
        for(int i=1;i<prices.length;i++){
            if( prices[i]>prices[i-1])
                res += prices[i]-prices[i-1];
        }
        return res;
    }
	
	public int maxProfitTwoTimes(int[] prices) {//leetcode 123
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for(int x:prices){
            sell2 = Math.max(sell2, x+buy2);
            buy2 = Math.max(buy2,-x+sell1);
            sell1 = Math.max( sell1, x+buy1);
            buy1 = Math.max(buy1, -x);
        }
        return sell2;
    }
}

public class MatrixString2DDP_62_63_64_87_97{//scramble, interleaving
	public MatrixString2DDP_62_63_64_87_97(){}
	
	public void excute(){
//		int[][] A = {{1,2},{1,1}};
		String s1 = "aabcc", s2 = "dbbca";
		String s3 = "aadbbbaccc";
		System.out.println( isInterleave(s1, s2, s3) );
	}
	
	public int uniquePaths(int m, int n) {
        if( m<1 || n<1 )
            return 0;
        if( 1==m || 1==n )
            return 1;
        if( 2==m || 2==n )
            return m==2?n:m;
            
        int[] dp = new int[n];
        for(int i=0;i<n;i++)
            dp[i] = 1;
        for(int j=1;j<m;j++){
            for(int i=1;i<n;i++)
                dp[i] += dp[i-1];
        }
        return dp[n-1];
    }

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {//leetcode 63
        if(null==obstacleGrid || 0==obstacleGrid.length || null==obstacleGrid[0] || 0==obstacleGrid[0].length)
            return 0;
         
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        if( 1==obstacleGrid[0][0] || 1==obstacleGrid[rows-1][cols-1])
            return 0;
            
        int[] dp = new int[cols+1];
        for(int i=0;i<rows;i++){        	
            for(int j=0;j<cols;j++){            	
                dp[j+1] = (0==i&&0==j)?1:(obstacleGrid[i][j]==1?0:dp[j+1]+dp[j]);
            }
        }
        return dp[cols];
    }
	
	public int minPathSum(int[][] grid) {//leetcode 64
        if(null==grid || 0==grid.length || null==grid[0] || 0==grid[0].length )
            return 0;
            
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for(int j=1;j<grid[0].length;j++){
            dp[j] = dp[j-1]+grid[0][j];
        }
        for(int i=1;i<grid.length;i++){
            dp[0] += grid[i][0];
            for(int j=1;j<grid[0].length;j++){
                dp[j] = Math.min(dp[j],dp[j-1])+grid[i][j];
            }
        }
        return dp[grid[0].length-1];
    }

	public boolean isScramble(String s1, String s2) {//leetcode 87
        if((null==s1 || 0==s1.length())&&(null==s2 || 0==s2.length()))
            return true;
        if(null==s1 || null==s2 || s1.length()!=s2.length() )
            return false;
            
        int L = s1.length();
        boolean[][][] dp = new boolean[L][L][L];
        for(int i=0;i<L;i++){
            for(int j=0;j<L;j++)
                dp[0][i][j] = s1.charAt(i)==s2.charAt(j)?true:false;
        }
        for(int k=1;k<L;k++){
            for(int i=0;i<L-k;i++){
                for(int j=0;j<L-k;j++){
                    for(int n=1;n<k+1;n++){
                        dp[k][i][j] = dp[k][i][j] || (dp[n-1][i][j] && dp[k-n][i+n][j+n]);
                        dp[k][i][j] = dp[k][i][j] || (dp[n-1][i][j+k+1-n] && dp[k-n][i+n][j]);
                    }
                }
            }
        }
        return dp[L-1][0][0];
    }

	public boolean isInterleave(String s1, String s2, String s3) {
        if( null==s1 && null==s2 && null==s3 )
            return true;
        if( (null==s1 || 0==s1.length()) )
            return s2.equals(s3);
        if( (null==s2 || 0==s2.length()))
            return s1.equals( s3 );
        if( s1.length()+s2.length() != s3.length() )
            return false;
            
        boolean[] dp = new boolean[s1.length()+1];
        dp[0] = true;
        for(int i=0;i<s1.length() && s1.charAt(i)==s3.charAt(i) ;i++)
            dp[i+1] = true;
        for(int j=0;j<s2.length();j++){
            dp[0] = dp[0] && s2.charAt(j)==s3.charAt(j);
            for(int i=0;i<s1.length();i++){
                char ch = s3.charAt(i+j+1);
                dp[i+1] = (dp[i]&&s1.charAt(i)==ch) || ( dp[i+1]&&s2.charAt(j)==ch );
            } 
        }
        return dp[s1.length()];  
    }
	
	
}

public class RegularExpressionMatch_10_44{
	
	RegularExpressionMatch_10_44(){}
	
	public void excute(){
		String s = "c", p = "*?*";
		System.out.println(isMatchWildCard(s,p) );
		
	}
	
	public boolean isMatchRegularExpression(String s, String p) {//Regular Expression Match
        if( (null==s && null==p) || (0==s.length()&& 0==p.length()))
            return true;
        
        return isMatchSubstr( s, 0, p, 0);        
    }
    
    public boolean isMatchSubstr(String s, int sbegin, String p, int pbegin ){
        if(sbegin==s.length() ){
            if( pbegin==p.length() )
                return true;
            while(pbegin+1<p.length() ){
                if( p.charAt(pbegin+1)=='*' )
                    pbegin += 2;
                else
                    return false;
            }
            return pbegin==p.length()?true:false;
        }
        if( pbegin==p.length() )
            return sbegin==s.length();
        
        if( pbegin+1<p.length() && p.charAt( pbegin+1 )=='*' ){
        	if( isMatchSubstr( s, sbegin, p, pbegin+2) )
        		return true;
            while( sbegin<s.length() && (p.charAt(pbegin)==s.charAt(sbegin)||p.charAt(pbegin)=='.') ){
            	sbegin++;
                if( isMatchSubstr( s, sbegin, p, pbegin+2) )
                    return true;
            }
            return false;
        }else{
            if( s.charAt( sbegin)==p.charAt( pbegin) || p.charAt( pbegin )=='.' )
                return isMatchSubstr( s, sbegin+1, p, pbegin+1); 
            else
                return false; 
        }
    }	
	
    public boolean isMatchWildCard0(String s, String p) {//leetcode 44, backtrack
        if( null==s && null==p )
            return true;
        if( null==s || null==p )
            return false;
            
        int sp = -1, pp = -1;
        int st = 0, pt = 0;
        while( st<s.length() ){
            if( pt<p.length() && (p.charAt(pt)=='?' || s.charAt(st)==p.charAt(pt)) ){
                pt++;
                st++;
            }else if ( pt<p.length() && p.charAt( pt )=='*'){
                pt++;
                sp = st;
                pp = pt;
            }else if( sp>-1 ){
                sp++;
                st = sp;
                pt = pp;
            }else
                return false;       
        }
        
        while( pt<p.length() && p.charAt(pt)=='*')
            pt++;
        return pt==p.length()?true:false;
    }
    
    public boolean isMatchWildCard(String s, String p) {//leetcode 44, dp
        if( null==s && null==p )
            return true;
        if( null==s || null==p )
            return false;
        if( p.replace("*", "").length()>s.length() )
            return false;
            
        Queue<Integer> dp = new LinkedList<Integer>();
        dp.offer(-1);
        for(int i=0;i<p.length();i++){
            char ch = p.charAt(i);
            if( dp.isEmpty() )
                return false;
            if( ch=='*' ){
                int j = dp.poll()+1;
                dp.clear();
                while(i<p.length() && p.charAt(i)=='*')
                    i++;
                if( i==p.length() )
                    return true;
                ch = p.charAt(i);
                for(;j<s.length();j++){
                    if( ch=='?' || s.charAt(j)==ch )
                        dp.offer( j );
                }            
            }else{
                int Len = dp.size();
                for(int j=0;j<Len;j++){
                    int k = dp.poll();
                    if( k+1<s.length() && (ch=='?' || ch==s.charAt(k+1)) )
                        dp.offer(k+1);
                }
            }
        }
        int val = -1;
        while( !dp.isEmpty() )
            val = dp.poll();
        return val==s.length()-1?true:false;
    }
}
	
public class PalindromAnagram_8_48{//valid palindrom
	PalindromAnagram_8_48(){}
	
	public void excute(){
		int x = 21474120;
		boolean sign = isPalindrome(x);
		System.out.println(sign?"true":"false");	
		
		String[] strs = {"abc","cba","acb","dhgk","hju","kdhg"};
		List<String> l = anagrams(strs);
		for( String s:l)
			System.out.print( s +", ");
		System.out.println();
	}
	
	public boolean isPalindrome(int x) {
        if( x<0 )
            return false;
            
        int len = 0, y = x;
        while( y>0 ){
            len++;
            y /= 10;
        }
        
        int i=0, j=len-1;
        while(j>i){
            int ibit = (x/(int)Math.pow(10,i++))%10;
            int jbit = (x/(int)Math.pow(10,j--))%10;
            if( ibit!=jbit )
                return false;
        }
        return true;
    }
	
	public List<String> anagrams0(String[] strs) {//leetcode 49
        List<String> res = new LinkedList<String>();
        if( null==strs || 2>strs.length )
            return res;
          
        HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for(int i=0;i<strs.length;i++){
            char[] chs = strs[i].toCharArray();
            Arrays.sort( chs );
            String ss = new String( chs );
            if( map.containsKey(ss) ){
                map.get( ss ).add( i );
            }else{
                List<Integer> sub = new LinkedList<Integer>();
                sub.add( i );
                map.put( ss, sub );
            }           
        }
        
        for(List<Integer> l:map.values() ){
            if( l.size()>1 ){
                for( int x:l )
                    res.add( strs[x] );
            }
        }
        return res;
    }

	public List<String> anagrams(String[] strs) {
        List<String> res = new LinkedList<String>();
        if( null==strs || 2>strs.length )
            return res;
          
        int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for(int i=0;i<strs.length;i++){
            int prod = 1;
            for(int j=0;j<strs[i].length();j++)
                prod *= primes[strs[i].charAt(j)-'a'];
            if( !map.containsKey(prod) ){
                List<String> sub = new LinkedList<String>();
                map.put( prod, sub );
            } 
            map.get( prod ).add( strs[i] );          
        }
        
        for(List<String> l:map.values() ){
            if( l.size()>1 ){
                res.addAll( l );
            }
        }
        return res;
    }
}
	
public class reverseArrayStringInteger_7_48_186{//rotate image
		
		reverseArrayStringInteger_7_48_186(){}
		
		public void excute(){
//			String S = " the   sky is blue  ";
//			char[] arr = S.toCharArray();
//			reverseWordsO1( arr );
//			System.out.println(S );
//			System.out.println(new String(arr) );
//			
//			int x = -123;
//			System.out.println( reverse(x) );
			
			int[][] A = {{1,2,3},{5,6,7},{9,10,11}};
			rotate( A );
			for(int[] B:A){
				for(int x:B)
					System.out.print( x+", ");
				System.out.println();
			}
		}
		
		public int reverse(int x) {//reverse integer
	        boolean sign = true;
	        if( x<0 ){
	            if( x==Integer.MIN_VALUE )
	                return 0;
	            x = -x;
	            sign = false;
	        }
	        
	        int y = 0;
	        while( x>0 ){
	            if( y>Integer.MAX_VALUE/10)
	                return 0;
	            y *= 10;
	            if( y>Integer.MAX_VALUE-x%10 )
	                return 0;
	            y += x%10;
	            x /= 10;
	        }
	        
	        if( !sign )
	            y = -y;
	        return y;
	    }
		
		public void reverseWordsO1( char[] s){//time O(n), space O(1)
			if( null==s || 2>s.length )
				return;
			
			int i=0, j=0, len = 0;			
			while( j<s.length ){//remove leading/tailing/extra separating spaces
				while(j<s.length && s[j]==' ' )
					j++;
				if( j==s.length )
					break;
				while(j<s.length && s[j]!=' ' )
					s[i++] = s[j++];
				s[i++] = ' ';
			}
			len = i-1;
			while( i<s.length )
				s[i++] = ' ';
			
			reverseWords(s, 0, len-1);
			i=0; j=0;
			while( j<=s.length ){
				while( j<s.length && s[j]!=' ')
					j++;
				reverseWords(s,i,j-1);
				i = ++j;
			}
			
		} 
		
		public void reverseWords(char[] s, int l, int r){
			while( l<r ){
				s[l] ^= s[r];
				s[r] ^= s[l];
				s[l++] ^= s[r--];
			}
		}

	public void rotate(int[][] matrix) {//leetcode 48
	        int n = matrix.length;
	        for(int k=0;k<n/2;k++){
	            for(int j=k;j<n-1-k;j++){
	                int temp = matrix[k][j];
	                matrix[k][j] = matrix[n-1-j][k];
	                matrix[n-1-j][k] = matrix[n-1-k][n-1-j];
	                matrix[n-1-k][n-1-j] = matrix[j][n-1-k];
	                matrix[j][n-1-k] = temp;
	            }        
	        }
	}
}
	
public class ConversionEnumeration_6_8_12_13_17_38_54_59_73{//zig-zag, spiral, Roman, str to integer, telephone number, count, set zeros
	
	public ConversionEnumeration_6_8_12_13_17_38_54_59_73(){}
	
	public void excute(){
//		String s = "23";
//		int n = 94;
//		System.out.println( convert(s, n));		
//		System.out.println("str to int: "+myAtoi("  -0012a42") );
//		System.out.println("int to roman: "+ intToRoman(n));	
//		System.out.println("roman to int: "+ romanToInt(s));
		
//		List<String> list = letterCombinations( s );
//		for(String t:list)
//			System.out.print(t+", ");
//		System.out.println();
		
//		System.out.println( countAndSay(5) );
		
		int[][] A = {{1,2,0,4},{5,6,7,8},{0,10,11,12},{13,14,15,16}};
//		List<Integer> list = spiralOrder( A );
//		for(int x:list)
//			System.out.print( x+", ");
//		System.out.println();
		
//		int[][] A = generateMatrix( 4 );
		setZeroes( A );
		for(int[] x:A){
			for(int y:x)
				System.out.print( y+", ");
			System.out.println();
		}
	}
	
	public int myAtoi(String str) {
        if( null==str )
            return 0;
            
        int tb = 0;
        while( tb<str.length()&&str.charAt(tb)==' ' )
            tb++;
        if( tb==str.length() )
            return 0;
            
        boolean sign = str.charAt(tb)=='-'?false:true;
        if( str.charAt(tb)=='-' || str.charAt(tb)=='+' )
            tb++;
        str = str.substring(tb);
        
        int value = 0;
        for(int i=0;i<str.length();i++){
            int ch = str.charAt(i)-'0';
            if( 0<=ch && 10>ch ){ 
                if( value>(Integer.MAX_VALUE-ch)/10 )
                    return sign?Integer.MAX_VALUE:Integer.MIN_VALUE;               
                value = value*10+ch;
            }else
            	break;
        }       
        return sign?value:(-value);
    }
	
	public String convert(String s, int numRows) {//zig-zag conversion, leetcode 6
        if( null==s || 0==s.length() || numRows>=s.length() || numRows==1)
            return s;
            
        StringBuilder str = new StringBuilder();
        int gap = 2*(numRows-1);
        for(int i=0; i<numRows; i++){
            int j = i;      
            boolean inter = false;
            while(j<s.length()){
                str.append( s.charAt(j) );
                if( i==0 || i==numRows-1 ){
                	j += gap;
                }else{
                	j += inter? (2*i):(gap-2*i);
                	inter = (!inter);
                }
            }
        }
        return str.toString();
    }

	public String intToRoman(int num) {
        if( 1>num || 3999<num )
            return "";
            
        char[] roman = {'I','V','X','L','C','D','M'};
        int i = 0;
        StringBuilder str = new StringBuilder();
        while( num>0 ){
            int temp = num%10;
            if( 5==temp ){
                str.append(roman[i+1] );
            }else if( 4==temp || 9==temp ){
                str.append(4==temp?roman[i+1]:roman[i+2] );
                str.append(roman[i] );
            }else{
                for(int j=0;j<temp%5;j++)
                    str.append(roman[i] );
                if( temp>4 )
                    str.append(roman[i+1] );
            }
            num = num/10;
            i += 2;
        }
        return str.reverse().toString();
    }

	public int romanToInt(String s) {
        if(null==s || 0==s.length())
            return 0;
            
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put( 'I', 1 );
        map.put( 'V', 5 );
        map.put( 'X', 10 );
        map.put( 'L', 50 );
        map.put( 'C', 100 );
        map.put( 'D', 500 );
        map.put( 'M', 1000 );
        
        int res = 0;
        for( int i=0; i<s.length();i++){
            if( i+1<s.length() && map.get(s.charAt(i))<map.get(s.charAt(i+1)) )
                res -= map.get(s.charAt(i));
            else
                res += map.get(s.charAt(i));
        }
        return res;
    }
		
	public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if( null==digits ||0==digits.length() )
            return list;
                 
        String[] dict = { "", "","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};           
        for(int i=digits.length()-1;i>=0;i--){
            char[] chs = dict[digits.charAt(i)-'0'].toCharArray();
            if( 0==chs.length )
                continue;
                
            if( list.isEmpty() ){
                for( char ch:chs)
                    list.add( ch+"" );
            }else{
                int Len = list.size();
                for(int j=0;j<chs.length;j++){
                    for(int k=0;k<Len;k++)
                        list.add( chs[j]+list.get(k) );
                }
                for(int k=0;k<Len;k++)
                    list.remove(0);
            }
        }
        return list;
    }
	
	public String countAndSay(int n) {//leetcode 38
        if( n<1 )
            return "";
            
        StringBuilder str = new StringBuilder();
        str.append( 1 );
        for(int i=1; i<n;i++){
            int Len = str.length();
            int num = 1;
            for(int j=1;j<=Len;j++){
                if( j==Len || str.charAt(j-1)!=str.charAt(j) ){
                    str.append( num );
                    str.append( str.charAt(j-1) );
                    num = 1;
                }else{
                    num++;
                }
            }
            str.delete( 0, Len );
        }
        return str.toString();
    }

	public List<Integer> spiralOrder(int[][] matrix) {//leetcode 54
        List<Integer>  res = new LinkedList<Integer>();
        if( null==matrix || 0==matrix.length || null==matrix[0] || 0==matrix[0].length )
            return res;
            
        int rows = matrix.length, cols = matrix[0].length;
        int rounds = Math.min( rows,cols)/2;
        for(int k=0;k<rounds;k++){
            for(int i=k;i<cols-1-k;i++)
                res.add( matrix[k][i] );
            for(int i=k; i<rows-1-k;i++)
                res.add( matrix[i][cols-1-k] );
            for(int i=cols-1-k;i>k;i--)
                res.add( matrix[rows-1-k][i] );
            for(int i=rows-1-k;i>k;i--)
                res.add( matrix[i][k]);
        }
        if( Math.min( rows,cols)%2==1 ){
            if( rows>cols ){
                for(int i=rounds;i<rows-rounds;i++)
                    res.add( matrix[i][rounds] );            
            }else{
                for(int i=rounds;i<cols-rounds;i++)
                    res.add( matrix[rounds][i] );
            }
        }
        return res;
    }

	public int[][] generateMatrix(int n) {//leetcode 59, spiral matrix II
        if( n<1 )
            return (new int[0][0]);
            
        int[][] res = new int[n][n];
        int x = 0;
        for(int k=0;k<n/2;k++){
            for(int i=k;i<n-1-k;i++)
                res[k][i] = ++x;
            for(int i=k;i<n-1-k;i++)
                res[i][n-1-k] = ++x;
            for(int i=n-1-k;i>k;i--)
                res[n-1-k][i] = ++x;
            for(int i=n-1-k;i>k;i--)
                res[i][k] = ++x;
        }
        if( n%2==1 )
            res[n/2][n/2] = ++x;
        return res;
    }

	public void setZeroes(int[][] matrix) {//leetcode 73
        if( null==matrix || 0==matrix.length || null==matrix[0] || 0==matrix[0].length )
            return;
    
        int rows = matrix.length, cols = matrix[0].length;
        boolean isFirstRow0 = false, isFirstCol0 = false;
        for(int i=0;i<cols;i++)
            isFirstRow0 |= (0==matrix[0][i]);
        for(int j=0;j<rows;j++)
            isFirstCol0 |= (0==matrix[j][0]);
        for( int j=1;j<rows;j++){
            for(int i=1;i<cols;i++){
                if( 0==matrix[j][i]){
                    matrix[0][i] = 0;
                    matrix[j][0] = 0;
                }
            }
        }
        for( int j=1;j<rows;j++){
            for(int i=1;i<cols;i++){
                if( 0==matrix[0][i] || 0==matrix[j][0])
                    matrix[j][i] = 0;
            }
        }
        if( isFirstRow0  ){
            for(int i=0;i<cols;i++)
                matrix[0][i] = 0;
        }
        if( isFirstCol0 ){
            for(int j=0;j<rows;j++)
                matrix[j][0] = 0;
        }
    }
}
	

public class BinarySearch_Median_4_33_34_35_74_81{
		BinarySearch_Median_4_33_34_35_74_81(){}
		
		public void excute(){
//			int[] num1 = {1,2,4,7,10,11};
//			int[] num2 = {3,6,7,9,9,80};
//			System.out.println(findMedianSortedArrays(num1, num2));
			
			int[] A = {1,3,1,1,1};
			System.out.println(searchRotatedSortedArrayWithDups(A,3));
//			int[] B = searchRange(A,8);
//			System.out.println(B[0]+", "+B[1]);
			
//			int[][] A = {{1,3,5,7},{10,11,16,20},{23, 30, 34, 50}};
//			System.out.println( searchMatrix(A, 15) );
		}
		
		public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		    if( null==nums1 && null==nums2 )
		        return 0;
		    if( null==nums1 ){
		        if( 0==nums2.length )
		            return 0;
		        else if( nums2.length%2==0 )
		            return 0.5*( nums2[nums2.length/2]+nums2[nums2.length/2-1] );
		        else
		            return nums2[(nums2.length-1)/2];
		    }
		    if( null==nums2 )
		        return findMedianSortedArrays( nums2, nums1 );
		    
		    int len = nums1.length+nums2.length;
		    if( 0==len )
		        return 0;
		    else if( len%2==0 ){
		        int m1 = topKthItem(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2+1);
		        int m2 = topKthItem(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2);
		        return 0.5*(m1+m2);
		    }else
		        return (double)topKthItem(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (len+1)/2);
		}

		public int topKthItem( int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k ){
		    if( e1-s1<e2-s2 )
		        return topKthItem( nums2, s2, e2, nums1, s1, e1,  k );
		    if( s2>e2 )
		        return nums1[s1+k-1];
		    if( 1==k )
		        return Math.min(nums1[s1], nums2[s2]);
		        
		    int n2 = Math.min(e2-s2+1, k/2);
		    int n1 = k-n2;
		    int mid1 = s1+n1-1, mid2 = s2+n2-1;
		    if( nums1[mid1]==nums2[mid2] )
		        return nums1[mid1];
		    else if( nums1[mid1]<nums2[mid2] )
		        return topKthItem( nums1, mid1+1, e1, nums2, s2, mid2, k-n1 );
		    else
		        return topKthItem( nums1, s1, mid1, nums2, mid2+1, e2, k-n2 );
		}
		
		public int searchRotatedSortedArray(int[] nums, int target) {
	        if( null==nums || 0==nums.length )
	            return -1;
	            
	        int left = 0, right = nums.length-1, mid = 0;
	        while( left<=right ){
	            mid = (left+right)/2;
	            if( target==nums[mid])
	                return mid;
//	            else if(target==nums[left])
//	            	return left;
//	            else if(target==nums[right])
//	            	return right;
	            else if( nums[left]<=nums[mid] ){
	                if(target<nums[mid] && target>= nums[left])
	                    right = mid-1;
	                else
	                    left = mid+1;
	            }else{
	                if( target>nums[mid]&&target<=nums[right])
	                    left = mid+1;
	                else
	                    right = mid-1;
	            }
	        }
	        return -1;
	    }

		public int[] searchRange0(int[] nums, int target) {//leetcode 34
	        int[] res = {-1,-1};
	        if( null==nums || 0==nums.length || target<nums[0] || target>nums[nums.length-1])
	            return res;
	            
	        int left = 0, right = nums.length-1, mid = 0;
	        while(left<=right){
	            mid = (left+right)/2;
	            if( target<nums[mid] )
	                right = mid-1;
	            else if(target>nums[mid])
	                left = mid+1;
	            else
	                break;
	        }
	        if( target==nums[mid] ){
	            int l = left, r = mid;
	            while(l<=r){
	                int m = (l+r)/2;
	                if( nums[m]==target ){
	                    if( 0==m || nums[m-1]!=target ){
	                        res[0] = m;
	                        break;
	                    }else
	                        r = m-1;
	                }else
	                    l = m+1;
	            }
	                        
	            l = mid; r = right;
	            while(l<=r){
	                int m = (l+r)/2;
	                if( nums[m]==target ){
	                    if( nums.length-1==m || nums[m+1]!=target ){
	                        res[1] = m;
	                        break;
	                    }else
	                        l = m+1;
	                }else
	                    r = m-1;
	            }
	        
	        }        
	        return res;
	    }

		public int[] searchRange(int[] nums, int target) {//leetcode 34
	        int[] res = {-1,-1};
	        if( null==nums || 0==nums.length || target<nums[0] || target>nums[nums.length-1])
	            return res;
	            
	        int left = 0, right = nums.length-1, mid = 0;
	        while(left<=right){
	            mid = (left+right)/2;
	            if( target<nums[mid] )
	                right = mid-1;
	            else if(target>nums[mid])
	                left = mid+1;
	            else{
	                left = mid;
	                while(left>0 && nums[left-1]==target)
	                    left--;
	                right = mid;
	                while(right<nums.length && nums[right]==target)
	                    right++;
	                res[0] = left;
	                res[1] = right-1;
	                break;
	            }
	        }            
	        return res;
	    }

	public int searchInsert(int[] nums, int target) {//leetcode 35
	        if( null==nums || 0==nums.length || target<nums[0])
	            return 0;
	            
	        int left = 0, right = nums.length-1, mid = -1;
	        while( left<=right ){
	            mid = (left+right)/2;
	            if( nums[mid]==target)
	                return mid;
	            else if(nums[mid]<target && (mid==nums.length-1 || nums[mid+1]>target) )
	                return mid+1;
	            else if( nums[mid]<target )
	                left = mid+1;
	            else
	                right = mid -1;
	        }          
	        return 0;
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
        if( null==matrix || 0==matrix.length || null==matrix[0] || 0==matrix[0].length )
            return false;
    
        int rows = matrix.length, cols = matrix[0].length;
        int first = 0, last = rows*cols-1, mid = -1;
        while( first<=last ){
            mid = (first+last)/2;
            int v_mid = matrix[mid/cols][mid%cols];
            if( target==v_mid )
                return true;
            else if( target<v_mid )
                last = mid-1;
            else
                first = mid+1;
        }
        return false;
    }

	public boolean searchRotatedSortedArrayWithDups(int[] nums, int target) {      //leetcode 81 
        int left = 0, right = nums.length-1, mid = -1;
        while( left<= right ){
        	while(left<right && nums[left]==nums[right])
        		right--;
            mid = (left+right)/2;
            if( target == nums[ mid ])
                return true;
            if( nums[mid] >=nums[left] ){
                if( target>nums[mid] || target<nums[left] )
                    left = mid+1;
                else
                    right = mid-1;
            }else{
                if( target<nums[mid] || target>nums[right] )
                    right = mid-1;
                else
                    left = mid+1;
            }
        }
        return false;
    }
}
	
public class LongestSubstring_3_5_14{//without repeating chars, palindromic, prefix
		
	LongestSubstring_3_5_14(){}
		
	public void excute(){
//			String s = "abbcffc";
//			System.out.println( "longestPalindrome: " + longestPalindrome(s) );
//			System.out.println( "lengthOfLongestSubstring: "+lengthOfLongestSubstring(s) );
			
		String[] strs = {"aa", "a"};
		System.out.println( "lengthOfLongestSubstring: "+longestCommonPrefix2(strs) );
	}
		
	public String longestPalindrome(String s) {
		if( null==s || 0==s.length() )
			return "";
		        
		int[] max = new int[2];
		int i = 0, left = 0, right = 0;
		while( i<s.length()){
			right = i+1;       
		    while(right<s.length()&&s.charAt(i)==s.charAt(right) )
		    	right++;
		        
		    left = i-1;
		    i = right;
		                
		    while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
		    	left--;
		        right++;
		    }
		        
		    if( right-left>max[1]-max[0]){
		    	max[0] = left;
		        max[1] = right; 
		    }
		}		    
		return s.substring(max[0]+1, max[1]);   
	}
		
	public int lengthOfLongestSubstring(String s){//without repearting characters
		if( null==s || 0==s.length() )
			return 0;
		        
		int maxLen = 0, last = -1;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0; i<s.length();i++){
			if( map.containsKey( s.charAt(i)) ){
				maxLen = Math.max(maxLen, i-1-last);
		        last = Math.max(last, map.get(s.charAt(i)));
		    }
		    map.put( s.charAt(i), i );
		}
		maxLen = Math.max(maxLen, s.length()-1-last);
		return maxLen;
	}
	
	public String longestCommonPrefix(String[] strs) {//leetcode 14
		if( null==strs || 0==strs.length )
			return "";
	            
	    int last = strs[0].length();
	    for(int i=1;i<strs.length;i++){
	    	last = Math.min(last, strs[i].length());
	        for(int j=0; j<last;j++){
	        	if( strs[0].charAt(j)!=strs[i].charAt(j)){
	        		last = j;
	                break;
	            }                    
	        }       
	    }
	    return strs[0].substring(0, last);
	}
	
	public String longestCommonPrefix2(String[] strs) {//leetcode 14
		if( null==strs || 0==strs.length )
			return "";
	            
	    for(int i=0;i<strs[0].length();i++){
	    	char ch = strs[0].charAt(i);       
	        for(int j=1; j<strs.length;j++){
	        	if( i==strs[j].length() || ch!=strs[j].charAt(i)){
	        		return strs[0].substring(0, i);
	            }                    
	        }       
	    }
	    return strs[0];
	}
		
}
		
public class MathCalculation_2_29_43_50_65_66_67_69{
		
	public MathCalculation_2_29_43_50_65_66_67_69(){}
		
	public void excute(){
//			int[] A = {2,4,3};
//			int[] B = {5,6,4 };
//			ListNode l1 = generateListNode( A );
//			ListNode l2 = generateListNode( B );
//			ListNode l = addTwoNumbers(l1,l2);
//			l.print();
			
//			int dividend = Integer.MIN_VALUE;
//			int divisor = 13907;
//			System.out.println(divide(dividend, divisor));
//			System.out.println(dividend/divisor);
			
//			int num1 = 0;
//			int num2 = 0;
//			System.out.println( multiply(Integer.toString(num1), Integer.toString(num2) ) );
//			System.out.println( num1*num2);
			
//			double x = -8.39679;
//			int n = 7;
//			System.out.println( myPow(x, n ) );
//			System.out.println( Math.pow(x, n));
			
//		int[] A = {0};
//		A = plusOne( A );
//		for(int x:A)
//			System.out.print(x);
//		System.out.println(  );
			
//		String a = "0", b = "0";
//		System.out.println(addBinary(a, b));
		
		int x = Integer.MAX_VALUE;
		System.out.println(mySqrt(x));
		System.out.println((int)Math.sqrt( x ));
	}
		
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        int carrier = 0;
	        ListNode res = new ListNode(0), cur = res;
	        
	        while( null!=l1 || null!=l2 ){
	        	if( null!=l1 ){
	        		carrier += l1.val;
	        		l1 = l1.next;
	        	}
	        	if( null!=l2){
	        		carrier += l2.val;
	        		l2 = l2.next;
	        	}
	        	
	        	cur.next = new ListNode( carrier%10 );
	        	cur = cur.next;
	        	carrier /= 10;
	        }
	        
	        if( carrier>0 ){
	        	cur.next = new ListNode( carrier );
	        }
	        
	        return res.next;
	}
	    
	public int divide(int dividend, int divisor) {
	    	if( 0 == dividend )
	            return 0;
	        if( 0 == divisor )
	            return dividend>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
	        if( 1 == divisor )
	            return dividend;
	        if( -1 == divisor )
	            return dividend==Integer.MIN_VALUE?Integer.MAX_VALUE:-dividend;
	        if( Integer.MIN_VALUE==divisor )
	            return dividend==Integer.MIN_VALUE?1:0;
	            
	        boolean sign = true, isMin = false;
	        if( dividend<0 ){
	        	if( dividend!=Integer.MIN_VALUE ){
	        		dividend = -dividend;	        		  
	        	}else{
	        		isMin = true;
	        		dividend = Integer.MAX_VALUE; 
	        	}
	            sign = !sign;
	        }	        
	        if( divisor<0){
	            divisor = -divisor;
	            sign = !sign;
	        }
	        
	        int res = 0;
	        while( dividend >= divisor ){
	        	int unit_addition = 1, subdivisor = divisor;
	        	while( dividend >=(subdivisor+subdivisor) && subdivisor<=(Integer.MAX_VALUE>>1)){
	        		subdivisor = subdivisor << 1;
	        		unit_addition += unit_addition;
	        	}	        	
	            res += unit_addition;
	            dividend -= subdivisor;
	        }
	        if( isMin && dividend+1==divisor )
	            res++;
	        return sign?res:-res;
	}

	public String multiply(String num1, String num2) {	        //leetcode 43
	        int i = 0;
	        while( i<num1.length() && (num1.charAt(i)==' '|| num1.charAt(i)=='0'))
	            i++;
	        if( i>0 )
	            num1 = num1.substring( i );
	        i = 0;
	        while( i<num2.length() && (num2.charAt(i)==' '|| num2.charAt(i)=='0'))
	            i++;
	        if( i>0 )
	            num2 = num2.substring( i );	        
	        if(0==num1.length() || 0==num2.length() )
	        	return "0";
	            
	        int[] num = new int[ num1.length() ];
	        for(int j=0;j<num1.length();j++)
	            num[j] = num1.charAt(j)-'0';
	            
	        int[] prod = new int[num1.length()+num2.length()];
	        for(int j=num2.length();j>0;j--){
	            int mult = num2.charAt(j-1)-'0';
	            if( 0!=mult ){
	                for(int k=num.length;k>0;k--){
	                    prod[j+k-1] += mult*num[k-1];
	                }
	            }
	        }	        
	        for(int j=prod.length-1;j>0;j--){
	            prod[j-1] += prod[j]/10;
	            prod[j] %= 10;
	        }
	        
	        StringBuilder res = new StringBuilder();
	        i = 0;	        
	        while(i<prod.length && 0==prod[i])
	            i++;
	        while(i<prod.length)
	            res.append( prod[i++] );	        
	        return res.toString();        
	}

	public double myPow(double x, int n) {//leetcode 50
	        if( 0==n )
	            return 1;
	        if( 0==x )
	            return n>0?0:Double.POSITIVE_INFINITY;      
	            
	        double res = 1.0;
	        boolean sign = !(x<0 && Math.abs(n)%2==1);
	        x = x<0?-x:x;
	        if( 1==x )
	            return sign?1:-1;        
	        if( n<0 ){
	        	if( n==Integer.MIN_VALUE ){
	        		res *= x;
	        		n++;
	        	}	        		
	            if( x<1.0/Double.MAX_VALUE )
	                return sign?Double.MAX_VALUE:Double.MIN_VALUE;
	            x = 1/x;
	            n = -n;
	        }
	              
	        while( true ){	            
	            if( (n&1) == 1 ){
	                if( res >Double.MAX_VALUE/x )
	                    return sign?Double.MAX_VALUE:Double.MIN_VALUE;
	                res *= x;
	            }
	            n = n>>1; 
		        if( n==0 )
		        	break;
		        else if( x >Double.MAX_VALUE/x )
	                return sign?Double.MAX_VALUE:Double.MIN_VALUE;
	            x *= x;
	        }
	         
	        return sign?res:(-res);           
	}

	public boolean isNumber(String s) {
        if( null==s || 0==s.length() )
            return false;
        int i=0,j=s.length();
        while(i<j && s.charAt(i)==' ')
            i++;
        if( i<j && (s.charAt(i)=='+' || s.charAt(i)=='-') )
            i++;
        while(j>i && s.charAt(j-1)==' ')
            j--;
        if( i==j )
            return false;
        s = s.substring(i,j);
        
        int e_index = s.indexOf('e');
        if( e_index == -1 )
        	e_index = s.indexOf('E');
        if( e_index>-1 ){
            if( e_index==0 || !isDouble(s.substring(0, e_index) ) )
                return false;
            e_index++;
            if( e_index==s.length() || s.charAt( e_index)=='+' || s.charAt( e_index)=='-' )
                e_index++;
            if( e_index>=s.length() )
                return false;
            return isAllNumeric( s.substring(e_index) );
        }
        return isDouble( s );       
    }   
    public boolean isDouble( String s){
        int index = s.indexOf( '.' );
        if( 0==index && s.length()==1 )
        	return false;
        if( -1==index  )
            return isAllNumeric(s);
        return ( isAllNumeric(s.substring(0,index)) && isAllNumeric(s.substring(index+1)) );
    }    
    public boolean isAllNumeric( String s){
        for(int i=0;i<s.length();i++){
        	char ch = s.charAt(i);
            if( ch<'0' || ch>'9')
                return false;
        }
        return true;
    }

    public int[] plusOne(int[] digits) {//leetcode 66
        if( null==digits || 0==digits.length )
            return (new int[]{1});
            
        int i = 0;
        while(i<digits.length && digits[i]==9)
            i++;
        if( i==digits.length ){
            int[] res = new int[digits.length+1];
            res[0] = 1;
            return res;
        }
        
        for(i=digits.length-1;i>=0;i--){
            if( 9 == digits[i] ){
                digits[i] = 0;
            }else{
                digits[i] += 1;
                break;
            }
        }
        return digits;         
    }
    
    public String addBinary(String a, String b) {//leetcode 67
        if( null==a || 0==a.length() )
            return (null==b||0==b.length())?"0":b;
        if( null==b || 0==b.length() )
            return a;
        
        int la = a.length(), lb = b.length(), i = 1;
        while(la>0 && a.charAt(a.length()-la)=='0' )
        	la--;
        a = a.substring(a.length()-la);
        while(lb>0 && b.charAt(b.length()-lb)=='0' )
        	lb--;
        b = b.substring(b.length()-lb);
        if( 0==la )
        	return 0==lb?"0":b;
        if( 0==lb )
        	return a;
    
        StringBuilder str = new StringBuilder();        
        int carrier = 0;
        while( carrier>0 || i<=Math.max(la,lb) ){
            carrier += (la>=i && a.charAt(la-i)=='1')?1:0;
            carrier += (lb>=i && b.charAt(lb-i)=='1')?1:0;
            str.insert( 0, carrier%2 );
            carrier >>= 1;
            i++;
        }
        return str.toString();
    }
    
    public int mySqrt(int x) {//leetcode 69
        if( x<0 )
            return -1;
        if( x<2 )
            return x;
            
        int res = 1, y = x;
        while(y>=4){
            res <<= 1;
            y >>= 2;
        }
        
        int unit = res;
        while( unit>1){
            unit >>= 1;
	        if( res+unit<=Integer.MAX_VALUE/(res+unit) ){
	            int temp = (res+unit)*(res+unit);
	            if( temp <= x ){
	                res += unit;
	                if( temp==x )
	                	break;
	            }  
	        }
        }
        return res;
    }
}
	
public class kSum_1_15_16_18_167{
	kSum_1_15_16_18_167(){}
		
	public void excute(){
		int[] A = {1,-2,-5,-4,-3,3,3,5};
		int target = -11;
		int[] index = twoSum(A,target);
		System.out.println(index[0]+", "+index[1]);
			
		index = twoSumII_sortedArray(A,target);
		System.out.println(index[0]+", "+index[1]);
			
		twoSumIII_DataStructureDesign_1 x1 = new twoSumIII_DataStructureDesign_1();
		x1.add(1); x1.add(3); x1.add(5);x1.add(2);x1.add(4);x1.add(4);x1.add(4);
		System.out.println(x1.find(4) +","+x1.find(5)+","+x1.find(2)+","+x1.find(10)+","+x1.find(12));
			
		twoSumIII_DataStructureDesign_2 x2 = new twoSumIII_DataStructureDesign_2();
		x2.add(1); x2.add(3); x2.add(5);x2.add(2);x2.add(4);x2.add(4);x2.add(4);
		System.out.println(x2.find(4) +","+x2.find(5)+","+x2.find(2)+","+x2.find(10)+","+x2.find(12));
			
		twoSumIII_DataStructureDesign_3 x3 = new twoSumIII_DataStructureDesign_3();
		x3.add(1); x3.add(3); x3.add(5);x3.add(2);x3.add(4);x3.add(4);x3.add(4);
		System.out.println(x3.find(4) +","+x3.find(5)+","+x3.find(2)+","+x3.find(10)+","+x3.find(12));
		
		int[] S = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
		List<List<Integer>> l = fourSum( S, target );
		for( List<Integer> temp:l ){
			for(int t:temp)
				System.out.print( t+", ");
			System.out.println();
		}
		
		System.out.println( threeSumClosest(A, target) );
	}
		
	private int[] twoSum(int[] nums, int target) {
	    int[] index = {1,1};
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for(int i=0;i<nums.length;i++){
	        if( map.containsKey(nums[i]) ){
	            index[0] += map.get( nums[i] );
	            index[1] += i;
	            return index;
	        }else
	            map.put( target-nums[i], i);
	    }    
		return index;
	}
		
	private int[] twoSumII_sortedArray(int[] nums, int target){
		int[] res = {0,0};
		if( null==nums || 2>nums.length || target<nums[0]+nums[1] || target>nums[nums.length-1]+nums[nums.length-2])
			return res;
			
		int left = 0, right = nums.length-1;
		while(left<right){
			int mid = (left+right)/2;
			if( target==nums[mid]+nums[right] ){
				res[0] = 1+mid;
				res[1] = 1+right;
				return res;
			}else if(target>nums[mid]+nums[right])
				left = mid+1;
			else if(target<nums[left]+nums[mid])
				right = mid-1;
			else{
				int sum = nums[left]+nums[right];
				if( sum==target ){
					res[0] = 1+left;
					res[1] = 1+right;
					return res;
				}else if( sum<target)
					left++;
				else
					right--;
			}
		}
		return res;
	}
	
	private class twoSumIII_DataStructureDesign_1{// add:O(1), find:O(n), space:O(n)
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			
		twoSumIII_DataStructureDesign_1(){}
			
		public void add( int x){
			map.put(x, map.containsKey(x)?map.get(x)+1:1);
		}
			
		public boolean find( int target){
			Set<Integer> keys = map.keySet();
			for(int key:keys ){
				if( (2*key==target && 1<map.get(key)) || (2*key!=target && map.containsKey(target-key)) )
					return true;
			}				
			return false;
		}			
	}
	
	private class twoSumIII_DataStructureDesign_2{// add:O(log n), find:O(n), space:O(n)
		List<Integer> list = new LinkedList<Integer>();
			
		twoSumIII_DataStructureDesign_2(){}
			
		public void add(int x){
			if( list.isEmpty() )
				list.add(x);
			else if( x<= list.get(0) )
				list.add(0,x);
			else if( x>=list.get(list.size()-1))
				list.add(x);
			else{
				int left = 0, right = list.size()-1;					
				while(left+1<right){
					int mid = (left+right)/2;
					int val = list.get(mid);
					if( val==x ){
						list.add(mid,x);
						return;
					}else if( val<x )
						left = mid;
					else
						right = mid;
				}
				list.add(right,x);
			}
		}
			
		public boolean find(int target){
			int left = 0, right = list.size()-1;
			while(left<right){
				int sum = list.get(left)+list.get(right);
				if( sum==target )
					return true;
				else if( sum>target)
					right--;
				else
					left++;
			}
			return false;
		}
	}
	
	private class twoSumIII_DataStructureDesign_3{// add:O(n), find:O(1), space:O(n^2)
		Map<Integer, Integer> nums = new HashMap<Integer, Integer>();
		Set<Integer> sums = new HashSet<Integer>();
			
		twoSumIII_DataStructureDesign_3(){}
			
		public void add( int x){
			if( nums.containsKey(x) ){
				nums.put(x, 1+nums.get( x ));
				sums.add( x*2);
			}else{
				for(int temp:nums.keySet() )
					sums.add( x+temp );
				nums.put(x, 1);
			}
		}
			
		public boolean find( int target){
			return sums.contains(target);
		}
	}

	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(null==nums || 3>nums.length)
            return res;
            
        Arrays.sort( nums );
        for(int i=0; i<nums.length;i++){
        	if( i>0 && nums[i]==nums[i-1] )
        		continue;
        	
            int left = i+1, right = nums.length-1;
            while(left<right){
                int mid = (left+right)/2;
                if( nums[mid]+nums[right]<-nums[i] )
                    left = mid+1;
                else if( nums[mid]+nums[left]>-nums[i] )
                    right = mid-1;
                else{
                    if( nums[left]+nums[right]==-nums[i] ){
                        res.add( Arrays.asList(nums[i], nums[left], nums[right]) );
                        while(left<right&&nums[left]==nums[left+1])
                            left++;
                        left++;
                        while(right>left&&nums[right]==nums[right-1])
                            right--;
                        right--;
                    }else if( nums[left]+nums[right]<-nums[i] ){
                    	while(left<right&&nums[left]==nums[left+1])
                            left++;
                        left++;
                    }else{
                    	while(right>left&&nums[right]==nums[right-1])
                            right--;
                        right--;
                    }
                }
            }
        
        }
        
        return res;
    }

	public int threeSumClosest(int[] nums, int target) {
        int diff = 0;
        while(true){
            for(int i=0;i<nums.length-2;i++){
                if( existTwoSum(nums, target-nums[i]-diff, i+1) )
                    return target-diff;
                if( diff>0 && existTwoSum(nums, target-nums[i]+diff, i+1) ) 
                    return target+diff;                   
            }
            diff++;
        }
    }
    
    public boolean existTwoSum(int[] nums, int target, int tbegin){
        Set<Integer> set = new HashSet<Integer>();
        for(int i=tbegin; i<nums.length;i++){
            if( set.contains(target-nums[i]) )
                return true;
            set.add( nums[i] );
        }
        return false;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if( null==nums || 4>nums.length )
            return res;
        
        Arrays.sort(nums);
        
        for(int i=0;i<nums.length-3;i++){
            if( i>0 && nums[i]==nums[i-1])
                continue;
            for(int j=i+1;j<nums.length-2;j++){
                if( j>i+1 && nums[j]==nums[j-1])
                    continue;
                int sum = target-nums[i]-nums[j];
                int p=j+1, q = nums.length-1;
                while(p<q){
                    if( nums[p]+nums[q]==sum ){
                        res.add( Arrays.asList(nums[i],nums[j],nums[p],nums[q]) );
                        while(p<q && nums[p]==nums[p+1] )
                            p++;
                        while(q>p && nums[q]==nums[q-1])
                            q--;
                        p++;
                        q--;
                    }else if( nums[p]+nums[q]<sum ){
                        while(p<q && nums[p]==nums[p+1] )
                            p++;
                        p++;
                    }else{
                        while(q>p && nums[q]==nums[q-1])
                            q--;
                        q--;
                    }
                }
            
            }           
        }
        return res;
    }

}
	
}


