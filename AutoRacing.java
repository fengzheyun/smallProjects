package Test_EA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SpaceshipScorer {
	
	private static SpaceshipScorer p = new SpaceshipScorer();

	/**
	 * Suppose you are a fan of auto-racing and want to figure out which drivers are likely to perform well in an upcoming race. 
	 * Luckily you have access to a log of the times that each racer started and finished their test race the day before.
	 * The particular rating algorithm you have chosen is to assign each racer R a score that equals the number of other racers who both started after R started 
	 * and also finished before R finished.
	 * Note that a lower score generally suggests that the racer is faster, and this rating algorithm keeps from penalizing fast racers who have slow times 
	 * simply because they are stuck behind a crash or slow racer. Additionally, this rating algorithm does not reward fast racers who pass tons of slow racers 
	 * in comparison to fast racers who race when there are not many slow racers on the track to pass (compare this with rating a racer based on the net number of passes).
	 * 
	 * More formally, you want to write a program that will read the test race log from standard input. 
	 * The first line of the log contains a single integer n from 0 to 70,000 that represents the number of racers in the log. 
	 * The next n lines of the test race log have the following format:
	 * racerId startTime endTime
	 * where racerId is an integer in the range [0,10^9] and startTime and endTime are both integers such that 0 <= startTime < endTime <= 10^18. 
	 * Each racerId will be distinct. Also, the collection of all start and end times will not contain any duplicate elements.
	 * 
	 * Given such an input, you should print output in the following format:
	 * racerId score
	 * where score is the score as defined above for racer racerId. The output lines should be sorted in ascending order ofscore with ties broken by sorting by racerId, 
	 * also in ascending order. This can be accomplished with a simple sort at the end.
	 * 
	 * Directions:
	 * Please code this problem in Java, C, or C++. Your solution should run in less than O(N^2) on all inputs.
	 * Hint: The naive brute force solution is too slow to run within the time limit. You will need to think of a faster solution. 
	 * Specifically, we are looking for a solution that is guaranteed to be less than O(N^2) on all inputs. One possible way to accomplish this 
	 * (there are several other acceptable methods) is to use a data structure with K buckets (e.g., K = 300), each of which is initially empty 
	 * and is defined by two times. Each bucket  will eventually contain racers whose start time falls between the two times. 
	 * The bucket boundaries should be chosen such that they ultimately will contain the same number of racers. Then iterate through the racers in end time order and, 
	 * as you iterate over each racer, build up this bucketed data structure in such a way that you can use it to quickly count the number of racers that finished 
	 * before him but started after him.
	 * 
	 * What We Are Looking For:
	 * For this problem, we simply want to see that you can implement the algorithm correctly, without particular regard to principles of object orientation or modularity.  
	 * Do give us at least minimal documentation to help us understand what you are trying to accomplish in certain key places of the algorithm.
	 * 
	 * Sample Testcases
	 * input:. visit 1point3acres.com for more.
	 * 5
	 * 2 100 200
	 * 3 110 190
	 * 4 105 145
	 * 1 90 150
	 * 5 102 198
	 * output:
	 * 3 0
	 * 4 0
	 * 1 1
	 * 5 2
	 * 2 3
	 * 
	 * Note in the above example that racer 3 has a score of 0 because no one starts after racer 3 (a drawback to this scoring system is the last racer 
	 * always has a score of 0). Racer 4 also has a score of 0 because the only racer who starts after racer 4's start time (racer 3) has a later finish time. 
	 * Racer 3 is listed ahead of racer 4 despite having a slower time because racer 3's id is lower. At the other end, racer 2 has a score of 3 because racers 3, 4, 
	 * and 5 start after racer 2 and finish before racer 2 finishes.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt( br.readLine() );
		Item[] ships = new Item[N];
		for(int i=0;i<N;i++){
			String input = br.readLine();
			String[] data = input.split(" ");
			int id = Integer.parseInt( data[0] );
			long start = Long.parseLong( data[1] );
			long end = Long.parseLong( data[2] );
			ships[i] = p.new Item(id, start, end);
		}

		p.computeScore(ships, N);
		PrintWriter writer = new PrintWriter(System.out);
		for(Item ship:ships){
			writer.println( ship.id +" "+ship.score );
		}

		
		writer.flush();
        writer.close();
	}
	
	public void sortShips( Item[] ships, int N){
		Arrays.sort( ships, new Comparator<Item>(){//sort the ships in ascending order of the ending time
			public int compare(Item a, Item b){
				if(a.end==b.end)
					return 0;
				if(a.end<b.end)
					return -1;
				return 1;
			}
		});
		
		for(int i=0;i<N;i++){
			ships[i].fend = i;
		}
		
		Arrays.sort( ships, new Comparator<Item>(){//sort the ships in descending order of the starting time
			public int compare(Item a, Item b){
				if(a.start==b.start)
					return 0;
				if(a.start<b.start)
					return 1;
				return -1;
			}
		});
	}
	
	public void computeScore(Item[] ships, int N){
		sortShips( ships, N);
		int num_buckets = (int)Math.ceil( Math.sqrt( N ) );
		
		List<List<Integer>> buckets = new ArrayList<List<Integer>>();
		int bucket_size = (int)Math.ceil( 1.0*N/num_buckets );
		for(int i=0;i<num_buckets;i++){
			buckets.add( new ArrayList<Integer>());
		}
		
		for( int i=0; i<N;i++){
			int bk_id = (int)(ships[i].fend/bucket_size);
			ships[i].score = 0;
			
			//in the current bucket
			List<Integer> cur_bucket = buckets.get(bk_id);
			for(int x:cur_bucket){
				if( x<ships[i].fend)
					ships[i].score++;
			}
			cur_bucket.add( ships[i].fend );
			
			//all the buckets smaller than the current bucket
			for(int j=0;j<bk_id;j++){
				ships[i].score += buckets.get(j).size();
			}
			
		}
		
		Arrays.sort(ships, new Comparator<Item>(){
			public int compare(Item a, Item b){
				return a.score==b.score?(a.id-b.id):(a.score-b.score);
			}
		});
	}
	
	public class Item{
		int id;
		long start;
		long end;
		int fend;//fake end: the order the the ending time
		int score;
		
		public Item(int _i, long _s, long _e){
			id = _i;
			start = _s;
			end = _e;
		}
	}

}
