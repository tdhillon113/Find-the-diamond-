// skeleton program for you to complete...
import java.util.Optional;

// don't change name of your class (referred to in pathfinder.java)
public class myastar extends astar_base {
    public myastar(int r, int c)
    { super(r,c); }

    @Override
    public void customize() {
       ////// Things you can do here...
        setcosts(2,0,1,10); // cost of land, desert, fire, water
        pathfinder.gap = 15; // change size of graphical hexgagons
        pathfinder.yoff = 20; // graphical top margin adjustment
        pathfinder.delaytime = 300; //change animation speed
        setRandFactor(0.13); // increase amount of water/fire
    }
    public static void main(String[] av) {
        pathfinder.main(av);
    }//main

    ///// The following function just searches randomly.
    ///// You must replace it with an implementation of Algorithm A*:
    @Override
    public Optional<coord> search(int sy, int sx, int ty, int tx)  {
        HashedHeap.set_initial_capacity(ROWS*COLS); //never have to resize/rehash (no collision?)
        var Frontier = new HashedHeap<Integer, coord>((x,y) ->y.compareTo(x)); 
        boolean[][] Interior = new boolean [ROWS][COLS]; //makes the interior 

        //int idx;// direction index (for later)
//insert a new coord(sy,sx) : start node into frontier
        Frontier.push(hash_key(sy, sx), new coord(sy,sx));  //the src path 
//pushing the hash_key onto the frontier
//KNOWN COST = cost froc src to dest 
//est cost = cost from current node to final dest 
        coord curr = new coord(sy,sx); 

        while(Frontier.size() > 0) {
            //set current to forntier coord with lowest cost 
            curr = Frontier.pop().get().val(); //get next coord;
            // move current from fonteir to interior 
            //the interior will consist of frontiers src coords y and x ?
            Interior[curr.y][curr.x] = true; 
            // stop if the coord of src y (sy) equals the destination (means that u found the destination) 
            //same thing for the src x
            // both coords equaling dest means dest found = stop 
            if (curr.y == ty && curr.x == tx){
                return Optional.of(curr);
            }
            //for each of neighbors (up to six) of current node 
            //ny and nx represent neighbors of 
            // create new int variables to hold the values of current.y and current.x

        for(int idx = 0; idx<6; idx++) { // i repesents the direction index 
            int ny = curr.y + DY[idx];
            int nx = curr.x + DX[curr.y%2][idx];

//for checking if ny,nx in interior - use check by setting interior to false?
 
            if ((ny >= 0 && ny < ROWS) && (nx >=0 && nx < COLS) && costof[Map[ny][nx]] > 0){
                 if (!Interior[ny][nx]) { //if on interior
                    coord neighbor = make_neighbor (curr, ny,nx,ty,tx);
                   
                    // //set nightbor parent to current
                    // neighbor.parent = Optional.of(curr);
                    // //some stuff taken form astar_base.java
                    // //set nighbor known.cost to coset of terrain + current knonw cost
                    // neighbor.set_known_cost(costof[Map[ny][nx]] + curr.knowncost); //why knowncost? **
                    // //add to known cost using heuristic estimate (neighbor.add_estimated_cost)
                    // neighbor.add_estimated_cost(hexdist(ny, nx, ty, tx)); //?hmm
                    //  //coord c; //...

                    // var coordopt = Frontier.get(hash_key(ny,nx));
                    // if(coordopt.isPresent()){
                    //     Frontier.push(hash_key(ny,nx),neighbor);
                    // }
                    // else{
                    //     if(neighbor.compareTo(coordopt.get()) < 0) {
                    //         Frontier.remove(hash_key(ny,nx));
                    //         Frontier.push(hash_key(ny, nx), neighbor);

            if(!(Frontier.get(hash_key(ny,nx)).isPresent())){
                    Frontier.push(hash_key(ny,nx),neighbor);
                   // Frontier.insert_new(hash_key(ny,nx), neighbor);
                    }//if not in frontier
                    else{
                    coord oldcoord = (Frontier.get(hash_key(ny,nx))).get();
                    if (oldcoord.compareTo(neighbor) > 0){
                    Frontier.set(hash_key(ny,nx), neighbor);

                    }
                    }
                    }

            }
            }
            // neighbor.set_parent = Optional.of(curr);
            // neighbor.set_known_cost = (costof[M[ny][nx]]) + curr.known_cost();

            }
       // System.out.println("broke");
        return Optional.empty(); 
    }
}
           // Frontier = coord(sy,sx);
           // System.out.println(Frontier.size())
            // System.out.println("brokan");
            // return Optional.empty();    //no path found 
        

        // int coord c; //...
        // var coordopt = Frontier.get(hash_key(y,x));
        // if(coordopt.isPresent()) c = coordopt.get();

        // while (current.y!=ty || current.x!=tx)  // solve by random search!
        //     {                                   // worst case O(infinity)
        //         //pick random direction
        //         int dir = (int)(Math.random()*6);
        //         int cy = current.y, cx = current.x;
        //         int ny = cy + DY[dir]; 
        //         int nx = cx + DX[cy%2][dir];
        //         if (nx>=0 && nx<COLS && ny>=0 && ny<ROWS)


        //             {
        //                 coord next = new coord(ny,nx);
        //                 next.parent = Optional.of(current);
        //                 current = next; 
        //             }
                // else, loop back and pick another direction
        //     }// main while
        // return Optional.of(current);
    // }//search
            
//myastar
/*  More Help:
 
   Algorithm A* is a modification of Dijkstra's Algorithm with the following
   differences:
  
   1. Instead of finding shortest paths form the source to all destinations,
      A* is focused on finding the best path from the source one specific
      destination.  The source is given by coordinates sy,sx and the 
      destination by ty,tx.  The algorithm terminates when the destination
      node (coord object) has been removed from the frontier and inserted 
      into the interior.

   2. The cost of each node in the search tree is a sum of two elements:
        
             known_cost_from_src  +  estimated_cost_to_dst

      Where the known_cost_from_src is the same as the cost measure used
      in Dijkstra's algorithm.  The estimated_cost_to_dst is a heuristic
      estimate of the remaining cost from the current node to the
      destination.  Futhermore, the estimate must be conservative: it
      cannot exceed the actual cost.  For example, when finding the best
      route to drive to a certain destination, the estimate can be the
      straight-line distance to the destination, which is guaranteed to 
      not overestimate the actual distance.  Under this restriction, A*
      is guaranteed to also find the optimal path to target.  

      The `hexdist` function in the astar_base superclass provides a
      conservative estimate of the remaining cost to reach the target.

   You can also think of Dijkstra's algorithm as Algorithm A* with a 
   heuristic estimate of zero. 
   
   As for data structures, I suggest you use my HashedHeap for the 
   Frontier.  You can create an instance of it as follows:

     HashedHeap.set_initial_capacity(ROWS*COLS); //never have to resize/rehash
     var Frontier = new HashedHeap<Integer,coord>((x,y)->y.compareTo(x));

   This creates a minheap.  The keys of the HashedHeap are integers
   computed from the y,x coordinates of a coord object, specifically
   y*COLS+x: the one-dimensional representation of the 2D coordinate.
   If given initial capacity ROWS*COLS, this guarantees that there
   will be no hash-collisions, and no need to ever resize the
   structure.

   For the Interior, you can use a hashmap but I would suggest just using 
   a 2D array of booleans:

     boolean[][] Interior = new boolean[ROWS][COLS];

   Initially, each Interior[y][x]==false, until you set it otherwise.

   Refer to my notes on Dijkstra's Algorithm for pseudocode.
*/


// notes 1:
// A* focused on best and shortes path from src to specifc dest 
// - uses heuristic approach 

//     - src: given by coords sy, sx
//     - dest : given by ty, tx

// - terminates when dest node (coord obj) removed from frontier and inserted into interior
// - while coord not on frontier (boolean closed = false) && move on interior:
//     - terminate

// - optional.empty() // no path found ?

// notes 2:
// - cost (each node in search tree) = known_cost_from_src + estimated_cost_to_dst
// - known_cost_from_src = cost measured in dijkstras alg
// - estimated_cost_to_dst = heuristic estimate of remaining cost from curr to dest 
//     - ** ESTIMATE CANNOT EXCEED ACTUAL **
// - when finding best route to certain dest:
//     - estimate can be bee-line dist to dest (gurantees no overestimate)
//     - ** also helps find optimal path 

//     - ** use hashheap for frontier : //he gave it yay <3
//         HashedHeap.set_initial_capacity(ROWS*COLS); //never have to resize/rehash (no collision?)
//         var Frontier = new HashedHeap<Integer, coord>((x,y) ->y.compareTo(x));

//         - this creates minheap:
//             - keys : ints computed from xy coord of coord obj (y*COLS) 
//                 - 1D rep of 2D coord 
//                 - wanted to use 2 D array for the multiple directions

//         - for Frontier to interior :
//             .push()
//             .pop()
//             .and_modify() or get/set
