package Test_EA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class knightBoard {
	static Cruise_knightBoard p = new Cruise_knightBoard();
	
	private static final int[][] VALIDMOVES = {	{1,2}, {-1,2}, {1,-2}, {-1,-2}, {2,1}, {-2,1}, {2,-1}, {-2,-1}};

	/**
	 * [Knight Board]
	 * The knight board can be represented in x,y coordinates. The upper left position
	 * is (0,0) and the bottom right is (7,7). Assume there is a single knight chess
	 * piece on the board that can move according to chess rules. Sample S[tart] and
	 * E[nd] points are shown below:
	 * 	. . . . . . . .
	 * 	. . . . . . . .
	 * 	. S . . . . . .
	 * 	. . . . . . . .
	 * 	. . . . . E . .
	 * 	. . . . . . . .
	 * 	. . . . . . . .
	 * 	. . . . . . . .
	 * 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {	
		System.out.println("Please input the level number (1-5):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		p.parseLevelNum( Integer.parseInt( input ) );
	}
	
	private void parseLevelNum( int num ){
		int board_size = 1;
		Position start_position = null, end_position = null;
		switch( num ){
		case 1:
			System.out.println("================== Level 1 =======================");
			start_position = new Position(2,1);// initial position of the knight
			boolean printFinalState = true; //print the final state of the knight board
			board_size = 8;//the size of the chess board		
			
			List<Position> move_sequence = new LinkedList<Position>();//represent the sequence of moves using a LinkedList
			move_sequence.add( new Position(3,3) );//add moves to the sequence
			move_sequence.add( new Position(1,4) );
			move_sequence.add( new Position(2,2) );
			move_sequence.add( new Position(1,0) );
	//		move_sequence.add( p.new Position(0,-2) );
	//		move_sequence.add( p.new Position(4,2) );					
			if( isValidKnightMoveSequence( move_sequence,  start_position, board_size, printFinalState) )
				System.out.print("The sequence contains");
			else
				System.out.print("The sequence DOES NOT contain");
			System.out.println(" only valid knight moves.");
			break;
		case 2:	
			System.out.println("================== Level 2 =======================");	
			board_size = 8;//the size of the chess board
			start_position = new Position(0,0);// start position of the knight
			end_position = new Position(1,1);// end position of the knight
			List<Position> move_seqs = anyMoveSequence( start_position, end_position, board_size );
			if( 0==move_seqs.size() && start_position.equals( end_position) )
				System.out.println("There is no sequence from the starting position to the ending position.");
			else{
				System.out.println("There could be "+move_seqs.size()+" moves from the starting position to the ending position as follows:");
				for(Position m:move_seqs)
					System.out.println("("+m.x+", "+m.y+")");
				System.out.println();
			}
			break;
		case 3:
			System.out.println("================== Level 3 =======================");	
			board_size = 8;//the size of the chess board
			start_position = new Position(0,0);// start position of the knight
			end_position = new Position(2,2);// end position of the knight
			List<Position> move_min_seqs = minimumMoveSequence( start_position, end_position, board_size );
			if( 0==move_min_seqs.size() && start_position.equals( end_position) )
				System.out.println("There is no sequence from the starting position to the ending position.");
			else{
				System.out.println("There are at least "+move_min_seqs.size()+" moves from the starting position to the ending position as follows:");
				for(Position m:move_min_seqs)
					System.out.println("("+m.x+", "+m.y+")");
				System.out.println();
			}	
			break;
		case 4:
			System.out.println("================== Level 4 =======================");
//			String[] str = {". . . . . . . .",
//							". . . . . . . .",
//							". . . T . . . .",
//							". . . . . . . .",
//							". . . . B B B B",
//							". . . . B . . .",
//							". . . . B . . .",
//							". . . . B T . ."};
			String[] str = {". . . . . . . . B . . . L L L . . . . . . . . . . . . . . . . .",
							". . . . . . . . B . . . L L L . . . . . . . . . . . . . . . . .",
							". . . . . . . . B . . . L L L . . . L L L . . . . . . . . . . .",
							". . . . . . . . B . . . L L L . . L L L . . . R R . . . . . . .",
							". . . . . . . . B . . . L L L L L L L L . . . R R . . . . . . .",
							". . . . . . . . B . . . L L L L L L . . . . . . . . . . . . . .",
							". . . . . . . . B . . . . . . . . . . . . R R . . . . . . . . .",
							". . . . . . . . B B . . . . . . . . . . . R R . . . . . . . . .",
							". . . . . . . . W B B . . . . . . . . . . . . . . . . . . . . .",
							". . . R R . . . W W B B B B B B B B B B . . . . . . . . . . . .",
							". . . R R . . . W W . . . . . . . . . B . . . . . . . . . . . .",
							". . . . . . . . W W . . . . . . . . . B . . . . . . T . . . . .",
							". . . W W W W W W W . . . . . . . . . B . . . . . . . . . . . .",
							". . . W W W W W W W . . . . . . . . . B . . R R . . . . . . . .",
							". . . W W . . . . . . . . . . B B B B B . . R R . W W W W W W W",
							". . . W W . . . . . . . . . . B . . . . . . . . . W . . . . . .",
							"W W W W . . . . . . . . . . . B . . . W W W W W W W . . . . . .",
							". . . W W W W W W W . . . . . B . . . . . . . . . . . . B B B B",
							". . . W W W W W W W . . . . . B B B . . . . . . . . . . B . . .",
							". . . W W W W W W W . . . . . . . B W W W W W W B B B B B . . .",
							". . . W W W W W W W . . . . . . . B W W W W W W B . . . . . . .",
							". . . . . . . . . . . B B B . . . . . . . . . . B B . . . . . .",
							". . . . . R R . . . . B . . . . . . . . . . . . . B . . . . . .",
							". . . . . R R . . . . B . . . . . . . . . . . . . B . T . . . .",
							". . . . . . . . . . . B . . . . . R R . . . . . . B . . . . . .",
							". . . . . . . . . . . B . . . . . R R . . . . . . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . R R . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . R R . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . . . . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . . . . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . . . . . . . . . . .",
							". . . . . . . . . . . B . . . . . . . . . . . . . . . . . . . ."};
			char[][] board = getChessBoard( str );	
			start_position = new Position(2,6);// start position of the knight
			end_position = new Position(20,25);// end position of the knight
			
			List<Position> move_seqs_vlid_32 = new LinkedList<Position>();//represent the sequence of moves using a LinkedList
			move_seqs_vlid_32.add( new Position(1,4) );//add moves to the sequence
			move_seqs_vlid_32.add( new Position(2,2) );
			move_seqs_vlid_32.add( new Position(4,3) );
			move_seqs_vlid_32.add( new Position(3,5) );
	//		move_sequence.add( p.new Position(0,-2) );
	//		move_sequence.add( p.new Position(4,2) );
			boolean printFinalState32 = true;
			if( isValidKnightMoveSequence32( move_seqs_vlid_32, start_position, board, printFinalState32) )
				System.out.print("The sequence contains");
			else
				System.out.print("The sequence DOES NOT contain");
			System.out.println(" only valid knight moves.");
			
			
			List<Position> move_seqs32 = new LinkedList<Position>();
			int num_moves = minimumMoveSequence32( start_position, end_position, board, move_seqs32 );
			if( -1 == num_moves  )
				System.out.println("There is no sequence from the starting position to the ending position.");
			else{
				System.out.println("There are at least "+num_moves+" moves from the starting position to the ending position as follows:");
				for(Position m:move_seqs32)
					System.out.println("("+m.x+", "+m.y+")");
				System.out.println();
			}	
			break;
		case 5:
			System.out.println("================== Level 5 =======================");	
			board_size = 8;//the size of the chess board
			start_position = new Position(0,0);// start position of the knight
			end_position = new Position(2,1);// end position of the knight
			List<Position> move_max_seqs = maximumMoveSequence( start_position, end_position, board_size );
			if( 0==move_max_seqs.size() && start_position.equals( end_position) )
				System.out.println("There is no sequence from the starting position to the ending position.");
			else{
				System.out.println("There are at least "+move_max_seqs.size()+" moves from the starting position to the ending position as follows:");
				for(Position m:move_max_seqs)
					System.out.println("("+m.x+", "+m.y+")");
				System.out.println();
			}	
			break;
		default:
			System.out.println("Input level number invalid.");
			break;
		}
	}
	
	/** Level 1: 
	 * Write a function that accepts a sequence of moves and reports
	 * whether the sequence contains only valid knight moves. It should also
	 * optionally print the state of the knight board to the terminal as shown
	 * above after each move. The current position should be marked with a 'K'.
	 * 
	 * @param
	 * 	moves: the sequence of moves
	 * 	initial_position: initial position of the knight
	 * 	printFinalState: true: print the final state of the knight; false: not print
	 *  board_size: the defined size of the chess board
	 * */
	public boolean isValidKnightMoveSequence( List<Position> moves, Position initial_position, int board_size, boolean printFinalState){
		char[][] status = new char[board_size][board_size];
		status[initial_position.x][initial_position.y] = 'S';
		Position final_position = new Position( initial_position );
		
		int num = 0;
		for( Position m:moves){
			if( final_position.isValidMove_Knight( m ) ){//check if the move itself is valid or not
				if( m.isValidPosition( board_size ) ){
					final_position.copy( m );
					status[final_position.x][final_position.y] = 'K';
					if( printFinalState ){
						System.out.println( "At move " +(num++));
						for(int i=0;i<board_size;i++){
							for(int j=0;j<board_size;j++){
								System.out.print(status[i][j]==0?" .":" "+status[i][j]);
							}
							System.out.println();
						}
						System.out.println();
					}
				}else{//check if move outside of the board or not
					if( printFinalState )
						System.out.println("The move sequence is not valid because the knight move out of the chess board at move "+num +".");
					return false;
				}
			}else{
				if( printFinalState )
					System.out.println("The move sequence is not valid because it is against the chess rules at move "+num+".");
				return false;
			}	
		}
		
		if( printFinalState ){//if optionally choose print, then print out the final position or error message
			status[final_position.x][final_position.y] = 'E';
			System.out.println( "The final status:" );
			for(int i=0;i<board_size;i++){
				for(int j=0;j<board_size;j++){
					System.out.print(status[i][j]==0?" .":" "+status[i][j]);
				}
				System.out.println();
			}
		}
		return true;
	}

	/** Level 2: Compute a valid sequence of moves from a given start point to a given end point.
	 *  
	 *  Using dfs method
	 * */
	public List<Position> anyMoveSequence( Position p_start, Position p_end, int board_size ){
		boolean[][] visited = new boolean[board_size][board_size];
		List<Position> path = new LinkedList<Position>();
		anyMoveSequence_helper( p_start, p_end, visited, path, board_size );
		if( 0<path.size())
			path.remove(0);
		return path;
	}

	private boolean anyMoveSequence_helper( Position p_start, Position p_end, boolean[][] visited, List<Position> curPath, int board_size ){
		visited[p_start.x][p_start.y] = true;
		curPath.add( p_start );
		if( p_start.equals( p_end )){
			return true;
		}
		
		for(int[] m:VALIDMOVES ){
			Position temp = new Position( p_start );
			temp.applyMove( m[0], m[1] );
			if( temp.isValidPosition(board_size) && !visited[temp.x][temp.y] ){		
				if( anyMoveSequence_helper( temp, p_end, visited, curPath, board_size ) )
					return true;
			}
		}
		curPath.remove( curPath.size()-1 );
		visited[p_start.x][p_start.y] = false;
		return false;
	}
	
	
	/** Level 3: Compute a valid sequence of moves from a given start point to a given end point 
	 *  in the fewest number of moves.
	 *  
	 *  Using bfs method
	 * */
	public List<Position> minimumMoveSequence( Position p_start, Position p_end, int board_size ){
		boolean[][] visited = new boolean[board_size][board_size];
		Map<Position, Position> map = new HashMap<>();
		Queue<Position> q = new LinkedList<Position>();
		q.offer( p_end );
		visited[p_end.x][p_end.y] = true;
		
		boolean findSeq = false;//using bfs to find the paths
		while( !findSeq ){
			int Len = q.size();
			for(int i=0;i<Len &&!findSeq ;i++){
				Position cur = q.poll();
				for(int[] m:VALIDMOVES ){
					Position temp = new Position( cur );
					temp.applyMove( m[0], m[1] );
					if( temp.isValidPosition(board_size ) && !visited[temp.x][temp.y] ){
						if( temp.equals( p_start )){
							map.put(p_start, cur);
							findSeq = true;
							break;
						}
						visited[temp.x][temp.y] = true;
						map.put(temp, cur);
						q.offer( temp );
					}
				}
			}
		}
		
		List<Position> seqs = new LinkedList<Position>();//retrieval a path
		seqs.add( p_start );
		while(!seqs.get(seqs.size()-1).equals(p_end) ){
			seqs.add( map.get( seqs.get(seqs.size()-1) ) );
		}
		seqs.remove(0);
		return seqs;
	}
	
	/** Level 4: Modify your validator from Level 1 to check your solutions for the 32x32 board. 
	 *  This board has the following additional rules:
	 *  	1) W[ater] squares count as two moves when a piece lands there
	 *  	2) R[ock] squares cannot be used
	 *  	3) B[arrier] squares cannot be used AND cannot lie in the path
	 *  	4) T[eleport] squares instantly move you from one T to the other in the same move
	 *  	5) L[ava] squares count as five moves when a piece lands there
	 *  
	 * */
	public boolean isValidKnightMoveSequence32( List<Position> moves, Position initial_position, char[][] board, boolean printFinalState){
		Map<Integer, Set<Integer>> teleports = getConnectedTeleports( board );
		char[][] status = new char[board.length][board.length];
		status[initial_position.x][initial_position.y] = 'S';
		Position final_position = new Position( initial_position );
		
		int num = 0;
		for( Position m:moves){
			if( m.isValidPosition( board.length ) ){//check if move outside of the board or not
				if( board[final_position.x][final_position.y]=='T' && board[m.x][m.y]=='T' ){
					System.out.println( final_position.x*board.length+final_position.y );
					System.out.println( m.x*board.length+m.y );
					if( !teleports.get(final_position.x*board.length+final_position.y ).contains( m.x*board.length+m.y ) ){
						if( printFinalState )
							System.out.println("The move sequence is not valid because Barriers lie on the teleports'path at move "+num +".");
						return false;
					}
					num++;
				}else if( final_position.isValidMove_Knight( m ) ){//check if the move itself is valid or not
					if( 'R'==board[m.x][m.y] ){
						if( printFinalState )
							System.out.println("The move sequence is not valid because the knight lands at a Rock at move "+num +".");
						return false;
					}
					if( 'B'==board[m.x][m.y] ){
						if( printFinalState )
							System.out.println("The move sequence is not valid because the knight lands at a Barrier at move "+num +".");
						return false;
					}
					if( isCrossBarrier( final_position, m, board) ){
						if( printFinalState )
							System.out.println("The move sequence is not valid because it crosses the Barriers at move "+num +".");
						return false;
					}
				}else{
					if( printFinalState )
						System.out.println("The move sequence is not valid because it is against the chess rules at move "+num+".");
					return false;
				}
			}else{
				if( printFinalState )
					System.out.println("The move sequence is not valid because the knight moves out of the chess board at move "+num +".");	
				return false;
			}	
			
			final_position.copy( m );
			status[final_position.x][final_position.y] = 'K';
			if( printFinalState ){
				System.out.println( "At move " +(num++));
				for(int i=0;i<status.length;i++){
					for(int j=0;j<status.length;j++){
						System.out.print(status[i][j]==0?" .":" "+status[i][j]);
					}
					System.out.println();
				}
				System.out.println();
			}
		}
		
		if( printFinalState ){//if optionally choose print, then print out the final position or error message
			status[final_position.x][final_position.y] = 'E';
			System.out.println( "The final status:" );
			for(int i=0;i<status.length;i++){
				for(int j=0;j<status.length;j++){
					System.out.print(status[i][j]==0?" .":" "+status[i][j]);
				}
				System.out.println();
			}
		}
		return true;
	}
	
	/** Level 4: Repeat the Level 3 task for this 32x32 board. This board has the following additional rules:
	 *  	1) W[ater] squares count as two moves when a piece lands there
	 *  	2) R[ock] squares cannot be used
	 *  	3) B[arrier] squares cannot be used AND cannot lie in the path
	 *  	4) T[eleport] squares instantly move you from one T to the other in the same move
	 *  	5) L[ava] squares count as five moves when a piece lands there
	 *  
	 *  Using Dijkstra algorithm
	 * */
	public int minimumMoveSequence32( Position p_start, Position p_end, char[][] board, List<Position> seqs ){
		if( !p_end.isValidPosition( board.length ) || board[p_end.x][p_end.y]=='R' || board[p_end.x][p_end.y]=='B' )
			return -1;
		
		PriorityQueue<Node> q = new PriorityQueue<Node>(board.length*board.length, new Comparator<Node>(){
			public int compare(Node a, Node b){
				return a.dist-b.dist;
			}
		});
		Node[] nodes = new Node[board.length*board.length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				nodes[i*board.length+j] = new Node(i,j, (i==p_end.x &&j==p_end.y)?0:Integer.MAX_VALUE );
				q.offer( nodes[i*board.length+j] );
			}
		}
		Map<Integer, Set<Integer>> teleports = getConnectedTeleports( board );
		
		Map<Position, Position> map = new HashMap<>();
		while( !q.isEmpty() ){
			Node cur = q.poll();
			if( cur.dist==Integer.MAX_VALUE )
				break;

			for(int[] m:VALIDMOVES ){
				Position temp = new Position( cur.p.x+m[0], cur.p.y+m[1] );
				int duv = getDistBetweenNodes( temp, cur.p, board);
				if( duv<Integer.MAX_VALUE ){
					int alt = cur.dist + duv;
					if( alt < nodes[ temp.x*board.length+temp.y].dist ){
						nodes[ temp.x*board.length+temp.y].dist = alt;
						map.put( nodes[temp.x*board.length+temp.y].p, cur.p);
						q.remove( nodes[ temp.x*board.length+temp.y] );
						q.offer( nodes[ temp.x*board.length+temp.y] );
						
						if( board[temp.x][temp.y] == 'T' ){
							for( int connects:teleports.get( temp.x*board.length+temp.y) ){
								Position ptemp = nodes[ connects ].p;
								nodes[ ptemp.x*board.length+ptemp.y].dist = alt;
								map.put( ptemp, nodes[temp.x*board.length+temp.y].p);
								q.remove( nodes[ ptemp.x*board.length+ptemp.y] );
								q.offer( nodes[ ptemp.x*board.length+ptemp.y] );
							}
						}
					}
				}
			}
		}
		
		// retrieval the path
		if( map.containsKey(nodes[p_start.x*board.length+p_start.y].p) ){
			seqs.add( nodes[p_start.x*board.length+p_start.y].p );
			while(!seqs.get(seqs.size()-1).equals(p_end) ){
				seqs.add( map.get( seqs.get(seqs.size()-1) ) );
			}
			seqs.remove(0);
			return nodes[p_start.x*board.length+p_start.y].dist;
		}else
			return -1;
	}
	
	private Map<Integer, Set<Integer>> getConnectedTeleports(char[][] board){//find out the teleports pairs that there is at least one path between them with no barrier lying on it.
		Map<Integer, Set<Integer>> teleports = new HashMap<>();
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				if( board[i][j]=='T'){
					teleports.put( i*board.length+j, new HashSet<Integer>());
				}
			}
		}
		for(int key:teleports.keySet() ){
			if( 0==teleports.get(key).size() ){		
				Set<Integer> otherTs = teleports.get(key);
				getConnectedTeleports_helper( new Position(key/board.length, key%board.length) , board, otherTs );
				for( int other:otherTs ){
					Set<Integer> copy = new HashSet<Integer>( otherTs);
					copy.remove( other );
					copy.add( key );
					teleports.put(other, copy);
				}
			}
		}
		return teleports;
	}
	
	private void getConnectedTeleports_helper( Position p_start, char[][] board, Set<Integer> res ){//bfs: check if there's any other T could be connected with this T.
		boolean[][] visited = new boolean[board.length][board.length];
		Queue<Position> q = new LinkedList<Position>();
		q.offer( p_start );
		visited[p_start.x][p_start.y] = true;
		
		while( !q.isEmpty() ){
			int Len = q.size();
			for(int i=0;i<Len;i++){
				Position cur = q.poll();
				for(int[] m:VALIDMOVES ){
					Position temp = new Position( cur.x+m[0], cur.y+m[1] );
					if( getDistBetweenNodes(cur, temp, board)<Integer.MAX_VALUE && !visited[temp.x][temp.y] ){
						if( board[temp.x][temp.y]=='T' ){
							res.add( temp.x*board.length+temp.y);
						}
						visited[temp.x][temp.y] = true;
						q.offer( temp );
					}
				}
			}
		}
	}
	
	private int getDistBetweenNodes(Position s, Position e, char[][] board){
		if( !e.isValidPosition( board.length ) || board[e.x][e.y]=='R' || board[e.x][e.y]=='B' )
			return Integer.MAX_VALUE;
		if( !s.isValidPosition( board.length ) || board[s.x][s.y]=='R' || board[s.x][s.y]=='B' || isCrossBarrier( s, e, board) )
			return Integer.MAX_VALUE;
		
		if( board[e.x][e.y]=='W' )
			return 2;
		if( board[e.x][e.y]=='L' )
			return 5;
		else
			return 1;
	}
	
	private boolean isCrossBarrier(Position s, Position e, char[][] board){
		if( 2==Math.abs(e.x-s.x) ){
			if( board[(s.x+e.x)/2][s.y] !='B' && board[e.x][s.y] != 'B' )
				return false;
			if( board[(s.x+e.x)/2][e.y] !='B' && board[s.x][e.y] != 'B' )
				return false;
			if( board[(s.x+e.x)/2][s.y] !='B' && board[(s.x+e.x)/2][e.y] != 'B' )
				return false;
		}else{
			if( board[s.x][(s.y+e.y)/2] !='B' && board[s.x][e.y] != 'B' )
				return false;
			if( board[e.x][s.y] !='B' && board[e.x][(s.y+e.y)/2] != 'B' )
				return false;
			if( board[s.x][(s.y+e.y)/2] !='B' && board[e.x][(s.y+e.y)/2] != 'B' )
				return false;
		}
		return true;
	}
	
	private char[][] getChessBoard( String[] input){	
		char[][] board = new char[input.length][input.length];
		if( null==input || 0==input.length ){
			for(int k=0;k<input.length;k++){
				for(int i=0;i<input.length;i++)
					board[k][i] = '.';
			}
		}else{
			if( input[0].length()/2+1 != input.length ){
				System.out.println("The width and height of the inputed board should be the same!");
				return null;
			}
			
			int j = 0;
			for(String s:input){
				for(int i=0;i<s.length();i+=2)
					board[j][i/2] = s.charAt(i);
				j++;
			}
		}
		return board;
	}
	
	
	/** Level 5 [HARD]: Compute the longest sequence of moves to complete Level 3 without
	 * visiting the same square twice. Use the 32x32 board.
	 *  
	 *  Using dfs method (recursion)
	 * */	
	public List<Position> maximumMoveSequence( Position p_start, Position p_end, int board_size ){
		boolean[][] visited = new boolean[board_size][board_size];
		List<Position> maxPath = new LinkedList<Position>();
		if( p_start.equals( p_end ))
			return maxPath;
		
		List<Position> curPath = new LinkedList<Position>();
		maximumMoveSequence_helper( p_start, p_end, visited, maxPath, curPath, board_size );
		
		maxPath.remove(0);
		return maxPath;
	}
	
	private void maximumMoveSequence_helper( Position p_start, Position p_end, boolean[][] visited, List<Position> maxPath, List<Position> curPath, int board_size ){
		visited[p_start.x][p_start.y] = true;
		curPath.add( p_start );
		if( p_start.equals( p_end )){
			if( maxPath.size()<curPath.size() ){
				maxPath.clear();
				maxPath.addAll( curPath );
			}
		}else{		
			for(int[] m:VALIDMOVES ){
				Position temp = new Position( p_start.x+m[0], p_start.y+m[1] );
				if( temp.isValidPosition(board_size) && !visited[temp.x][temp.y] ){		
					maximumMoveSequence_helper( temp, p_end, visited, maxPath, curPath, board_size );		
				}
			}
		}
		curPath.remove( curPath.size()-1 );
		visited[p_start.x][p_start.y] = false;
	}
	
	
	public class Position{
		int x;//number of row: knight is at the x-th row
		int y;//number of column: knight is at the y-th column
		
		public Position(int _x, int _y){
			x = _x;
			y = _y;
		}
		
		public Position(Position _pos){
			x = _pos.x;
			y = _pos.y;
		}
		
		public void copy(Position _pos){
			x = _pos.x;
			y = _pos.y;
		}
		
		public void applyMove( int _x, int _y){
			x += _x;
			y += _y;
		}
		
		public boolean isValidPosition(int board_size ){//check is the position is out of the board or not
			return (x>=0 && x<board_size && y>=0 && y<board_size);
		}
		
		public boolean isValidMove_Knight( Position p){
			return (2==Math.abs( x-p.x ) && 1==Math.abs(y-p.y)) || (1==Math.abs( x-p.x ) && 2==Math.abs(y-p.y));
		}
		
		public boolean equals( Position _pos){
			return (x==_pos.x && y==_pos.y);
		}
		
	}
	
	public class Node{
		Position p;
		int dist;
		
		public Node(int _x, int _y, int _d){
			p = new Position(_x, _y);
			dist = _d;
		}
		
	}
}
