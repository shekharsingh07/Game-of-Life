import java.util.*;
import java.math.*;

public class GameOfLife {
  public static void main(String[] args){

    //User Grid input
    /*Scanner sc = new Scanner(System.in);
    System.out.print("Enter grid size: ");
    int n = sc.nextInt();
    System.out.println("The gride size is " +n);*/
    int M=10, N=10;
    int gen=3;      //number if generations
    int [][] grid = new int [M][N];    //Initiate grid
    int [][] futureGrid = new int [M][N];
    Random rand = new Random();

    //Initial state
    System.out.print("Initial State: \n");
    for(int i=0; i<M; i++){
      for(int j=0; j<N; j++){
        grid[i][j] = rand.nextInt(2);
        //Initial Output
        System.out.print(grid[i][j] + " ");
      }
      System.out.println();


      }
      System.out.print("\n");
      for(int g=0; g<gen; g++){
        int n = g+1;
        futureGrid = applyRules(grid, M, N);
        System.out.print("Generation "+ n +": \n");
        print(futureGrid);
        grid = futureGrid;
    }
  }


  //Applying rules
  public static int[][] applyRules(int[][] grid, int M, int N){
    int[][] newGrid = new int[M][N];
    // int gen = 4;
    // Loop through all cells for each generations
    // for(int count=0; count< gen; count++) {

      for(int y=1; y < M-1; y++){
        for(int x=1; x < N-1; x++){

          int neighbours = neighbourCounter(grid, x ,y);
          //Living Cells
          if(grid[x][y] ==1){
            if((neighbours == 2)||(neighbours == 3))   //2-3 neighbours; Cell lives
              newGrid[x][y] = 1;

            if((neighbours < 2)||(neighbours > 3))     //<2 or >3; cell dies
              newGrid[x][y] = 0;

          }
          //Dead Cells
          else if(grid[x][y] == 0){
            if(neighbours == 3)
              newGrid[x][y] = 1;

            else
              newGrid[x][y] = 0;
          }
        }
      }

      return newGrid;
    }



  //Check the number of neighbours around each cell
  public static int neighbourCounter(int[][] grid, int x, int y) {
      int neighbours = 0;
      for(int i = x - 1; i <= x + 1; i++) {
          for(int j = y - 1; j <= y + 1; j++) {
              if(grid[i][j] == 1) {
                  neighbours++;
              }
          }
      }
      if(grid[x][y] == 1) {    //Removes living cell from counter
          neighbours--;
      }
      return neighbours;
  }


  public static void print(int[][] grid){
    for(int i = 0; i < grid.length; i++) {
        for(int j = 0; j < grid[i].length; j++) {
            System.out.print(grid[i][j] + " ");
            //System.out.println();
        }
        System.out.println();

    }
    System.out.print("\n");
  }

}
