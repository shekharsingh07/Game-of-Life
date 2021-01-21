import java.util.*;

public class GameOfLife {
    public static void main(String[] args){

        //User Grid input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter grid size: ");
        int M = sc.nextInt();
        System.out.println("The grid size is " +M+ "x"+ M + "\n");
        System.out.print("Enter how many generations: ");
        int gen = sc.nextInt();


        int [][] grid = new int [M][M];    //Initiate grid
        int [][] futureGrid;
        Random rand = new Random();



//--------------------------Initial state--------------------------
        //(i) Randomized set - Uncomment to use
//        System.out.print("\nInitial State: \n");
//        for(int i=0; i<M; i++){
//            for(int j = 0; j< M; j++){
//                grid[i][j] = rand.nextInt(2);
//            }
//        }
        //(ii) Specific set - Uncomment to use
        System.out.print("Initial State: \n");
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                grid[i][j] = 0;
            }
        }

        grid[5][6]=1;
        grid[5][7]=1;
        grid[5][8]=1;
        grid[4][6]=1;
        grid[4][5]=1;

        for (int[] ints : grid) {                  //(use enhanced for loop for cleaner code)
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();

        }


        System.out.print("\n");
        for(int g=0; g<gen; g++){
            int n = g+1;
            futureGrid = applyRules(grid, M, M);
            System.out.print("Generation "+ n +": \n");
            print(futureGrid);
            grid = futureGrid;
        }
    }


    //--------------------------Applying rules--------------------------
    public static int[][] applyRules(int[][] grid, int M, int N){
        int[][] newGrid = new int[M][N];
//    _____CORNER CELLS:____
//      1. Corner Positions:
        int TL = grid[0][0], TR = grid[0][N-1], BL = grid[M-1][0], BR = grid[M-1][N-1];
//      2. Rules and neighbours:
        int[] corner = {TL, TR, BL, BR};
        int neighbourCorner=0;
        for(int t : corner){
            //Top Left corner (TL)
            if(t == TL){
                neighbourCorner=0;
                for(int i=0; i<=1; i++){
                    for(int j=0; j<=1; j++){
                        if(grid[i][j]==1){
                            neighbourCorner++;
                        }
                    }

                }

                if(TL==1){
                    neighbourCorner--;              //Removes living cell from counter
                    if((neighbourCorner==2)||(neighbourCorner==3)){         //2-3 neighbours; Cell lives
                        newGrid[0][0]=1;
                    }
                    if((neighbourCorner==0)||(neighbourCorner==1)){        //<2 neighbours; Cell dies
                        newGrid[0][0] = 0;
                    }
                }
                else if(TL==0){
                    if(neighbourCorner==3){                                 //3 neighbours; Cell comes to life
                        newGrid[0][0]=1;
                    }
                    else{
                        newGrid[0][0]=0;                                    //No rules; Dead cell stays dead
                    }
                }

            }
            //TR
            if(t == TR){
                neighbourCorner=0;
                for(int i=0; i<=1; i++){
                    for(int j=N-1; j>=N-2; j--){
                        if(grid[i][j]==1){
                            neighbourCorner++;
                        }
                    }

                }

                if(TR==1){
                    neighbourCorner--;              //Removes living cell from counter
                    if((neighbourCorner==2)||(neighbourCorner==3)){
                        newGrid[0][N-1]=1;
                    }
                    if((neighbourCorner==0)||(neighbourCorner==1)){
                        newGrid[0][N-1] = 0;
                    }
                }
                else if(TR==0){
                    if(neighbourCorner==3){
                        newGrid[0][N-1]=1;
                    }
                    else{
                        newGrid[0][N-1]=0;
                    }
                }
            }
            //BL
            if(t == BL){
                neighbourCorner=0;
                for (int i = M - 1; i >= M - 2; i--) {
                    for (int j=0; j<=1; j++) {
                        if (grid[i][j] == 1) {
                            neighbourCorner++;
                        }
                    }

                }

                if (BL == 1) {
                    neighbourCorner--;              //Removes living cell from counter
                    if ((neighbourCorner == 2) || (neighbourCorner == 3)) {
                        newGrid[M - 1][0] = 1;
                    }
                    if ((neighbourCorner == 0) || (neighbourCorner == 1)) {
                        newGrid[M - 1][0] = 0;
                    }
                } else if (BL == 0) {
                    if (neighbourCorner == 3) {
                        newGrid[M - 1][0] = 1;
                    }
                    else {
                        newGrid[M - 1][0] = 0;
                    }
                }
            }
            //BR
            if(t == BR){
                neighbourCorner=0;
                for(int i = M - 1; i >= M - 2; i--){
                    for (int j=N-1; j>=N-2; j--){
                        if(grid[i][j]==1){
                            neighbourCorner++;
                        }
                    }

                }

                if(BR==1){
                    neighbourCorner--;              //Removes living cell from counter
                    if((neighbourCorner==2)||(neighbourCorner==3)){
                        newGrid[M-1][N-1]=1;
                    }
                    if((neighbourCorner==0)||(neighbourCorner==1)){
                        newGrid[M-1][N-1] = 0;
                    }
                }
                else if(BR==0){
                    if(neighbourCorner==3){
                        newGrid[M-1][N-1]=1;
                    }
                    else{
                        newGrid[M-1][N-1]=0;
                    }
                }
            }
        }

//    ____EDGE CELLS:____
        int neighbourEdge;
        //Top:
        for(int x=0; x<1; x++){
            for(int y=1; y<N-1; y++){
                neighbourEdge=0;
                for(int i = x; i <= x + 1; i++){
                    for(int j = y-1; j <= y + 1; j++){
                        if(grid[i][j]==1){
                            neighbourEdge++;
                        }
                    }

                }

                if(grid[x][y] == 1){
                    neighbourEdge--;              //Removes living cell from counter
                    if((neighbourEdge==2)||(neighbourEdge==3)){
                        newGrid[x][y]=1;
                    }
                    if((neighbourEdge==0)||(neighbourEdge==1)){
                        newGrid[x][y] = 0;
                    }
                }
                else if(grid[x][y]==0){
                    if(neighbourCorner==3){
                        newGrid[x][y]=1;
                    }
                    else{
                        newGrid[x][y]=0;
                    }
                }

            }
        }

        //Bottom:
        for(int x=M-1; x>M-2; x--){
            for(int y=1; y<N-1; y++){
                neighbourEdge=0;
                for(int i = x; i >= x-1; i--){
                    for(int j = y-1; j <= y + 1; j++){
                        if(grid[i][j]==1){
                            neighbourEdge++;
                        }
                    }

                }

                if(grid[x][y] == 1){
                    neighbourEdge--;              //Removes living cell from counter
                    if((neighbourEdge==2)||(neighbourEdge==3)){
                        newGrid[x][y]=1;
                    }
                    if((neighbourEdge==0)||(neighbourEdge==1)){
                        newGrid[x][y] = 0;
                    }
                }
                else if(grid[x][y] == 0){
                    if(neighbourCorner==3){
                        newGrid[x][y]=1;
                    }
                    else{
                        newGrid[x][y]=0;
                    }
                }

            }
        }


        //Left:
        for(int x=1; x<M-1; x++){
            for(int y=0; y<1; y++){
                neighbourEdge=0;
                for(int i = x-1; i <= x+1; i++){
                    for(int j = y; j <= y + 1; j++){
                        if(grid[i][j]==1){
                            neighbourEdge++;
                        }
                    }

                }

                if(grid[x][y] == 1){
                    neighbourEdge--;              //Removes living cell from counter
                    if((neighbourEdge==2)||(neighbourEdge==3)){
                        newGrid[x][y]=1;
                    }
                    if((neighbourEdge==0)||(neighbourEdge==1)){
                        newGrid[x][y] = 0;
                    }
                }
                else if(grid[x][y] == 0){
                    if(neighbourCorner==3){
                        newGrid[x][y]=1;
                    }
                    else{
                        newGrid[x][y]=0;
                    }
                }

            }
        }


        //Right:
        for(int x=1; x<M-1; x++){
            for(int y=N-1; y>N-2; y--){
                neighbourEdge=0;
                for(int i = x-1; i <= x+1; i++){
                    for(int j = y; j >= y - 1; j--){
                        if(grid[i][j]==1){
                            neighbourEdge++;
                        }
                    }

                }

                if(grid[x][y] == 1){
                    neighbourEdge--;              //Removes living cell from counter
                    if((neighbourEdge==2)||(neighbourEdge==3)){
                        newGrid[x][y]=1;
                    }
                    if((neighbourEdge==0)||(neighbourEdge==1)){
                        newGrid[x][y] = 0;
                    }
                }
                else if(grid[x][y] == 0){
                    if(neighbourCorner==3){
                        newGrid[x][y]=1;
                    }
                    else{
                        newGrid[x][y]=0;
                    }
                }

            }
        }


//    ____INSIDE CELLS:____
        for(int y=1; y < M-1; y++){
            for(int x=1; x < N-1; x++){

                int neighboursIn = neighbourCounterInside(grid, x ,y);
                //Living Cells
                if(grid[x][y] ==1){
                    if((neighboursIn == 2)||(neighboursIn == 3))   //2-3 neighbours; Cell lives
                        newGrid[x][y] = 1;

                    if((neighboursIn < 2)||(neighboursIn > 3))     //<2 or >3; cell dies
                        newGrid[x][y] = 0;

                }
                //Dead Cells
                else if(grid[x][y] == 0){
                    if(neighboursIn == 3)
                        newGrid[x][y] = 1;

                    else
                        newGrid[x][y] = 0;
                }
            }
        }

//      Corners:


        return newGrid;
    }


    //--------------------------Neighbour Counter--------------------------
    //Check the number of neighbours around each INSIDE cell
    public static int neighbourCounterInside(int[][] grid, int x, int y) {
        int neighboursIn = 0;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(grid[i][j] == 1) {
                    neighboursIn++;
                }
            }
        }


        if(grid[x][y] == 1) {    //Removes living cell from counter
            neighboursIn--;
        }
        return neighboursIn;
    }

    //--------------------------Print--------------------------
    public static void print(int[][] grid){
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();

        }
        System.out.print("\n");
    }

}
