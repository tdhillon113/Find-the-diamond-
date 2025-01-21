// import java.util.Optional;


// public class myastar extends astar_base {
//     public myastar(int r, int c)
//     {super(r,c);}

//     @Override   
//     public void customize(){

//         /*things that can be done:
//         setcosts(2,0,1,10);
//         pathfinder.gap = 15;
//         pathfinder.yoff = 20;
//         setRandFactor(0.13);
//         */
//     }

//     public static void main(String[]av){
//         pathfinder.main(av);

//     }//main





// // notes 1:
// // A* focused on best and shortes path from src to specifc dest 
// // - uses heuristic approach 

// //     - src: given by coords sy, sx
// //     - dest : given by ty, tx

// // - terminates when dest node (coord obj) removed from frontier and inserted into interior
// // - while coord not on frontier (boolean closed = false) && move on interior:
// //     - terminate

// // - optional.empty() // no path found ?

// // notes 2:
// // - cost (each node in search tree) = known_cost_from_src + estimated_cost_to_dst
// // - known_cost_from_src = cost measured in dijkstras alg
// // - estimated_cost_to_dst = heuristic estimate of remaining cost from curr to dest 
// //     - ** ESTIMATE CANNOT EXCEED ACTUAL **
// // - when finding best route to certain dest:
// //     - estimate can be bee-line dist to dest (gurantees no overestimate)
// //     - ** also helps find optimal path 

// //     - ** use hashheap for frontier : //he gave it yay <3
// //         HashedHeap.set_initial_capacity(ROWS*COLS); //never have to resize/rehash (no collision?)
// //         var Frontier = new HashedHeap<Integer, coord>((x,y) ->y.compareTo(x));

// //         - this creates minheap:
// //             - keys : ints computed from xy coord of coord obj (y*COLS) 
// //                 - 1D rep of 2D coord 
// //                 - wanted to use 2 D array for the multiple directions

// //         - for Frontier to interior :
// //             .push()
// //             .pop()
// //             .and_modify() or get/set



    






// //change it so that alg A* is implementated 
//     @Override
//     public Optional<coord> search(int sy, int sx, int ty, int tx){
//        HashedHeap.set_initial_capacity(ROWS*COLS); //never have to resize/rehash (no collision?)

//        var Frontier = new HashedHeap<Integer, coord>((x,y) ->y.compareTo(x)); 
//        boolean[][] Interior = new boolean [ROWS][COLS]; //makes the interior 


//         // insert new coord - start node on frontier 
//         coord curr = new coord(sy, sx);
       
//         while (current.y!=ty || current.x!=tx)  //solve by random search 
//         {                                       // worse case O(infinity)
//             //random search 
//             int dir = (int)(Math.random()*6);
//             int cy = current.y, cx = current.x;
//             int ny = cy + DY[dir];
//             int nx = cx + DX[cy%2][dir];
//             if(nx>=0 && nx<COLS && ny >=0 && ny<ROWS)
//             {
//                 coord next = new coord(ny,nx);
//                 next.parent = Optional.of(current);
//                 current = next;
                
//             }
//             //else, loop back pick another dest
//         }//main while
//         return Optional.of(current);
//     } //search 
// }//myastar









