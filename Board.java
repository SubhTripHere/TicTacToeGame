import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Board {
    Deque<Player> players=new LinkedList<>();
    int numPlayers;
    int rows;
    int cols;
    Cell[][] cells;
    Player winner=null;
    public static final Scanner sc=new Scanner(System.in);
    public Board(int players,int rows,int cols) {
        numPlayers=players;
        cells=new Cell[rows][cols];
        this.rows=rows;
        this.cols=cols;
        fillCells(cells);
    }
    public  void fillCells(Cell[][] cells){
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[0].length;j++){
                cells[i][j]=new Cell(".");
            }
        }
    }
    public void addPlayers(String name,Symbol symbol){
        Player player=new Player(name,symbol);
        if(players.size()<numPlayers)
            players.offer(player);
    }
    void startGame(){
        while(winner==null){
            Player player=players.pollFirst();
            System.out.println("make your move player "+player.name);
            int row=sc.nextInt();
            int col=sc.nextInt();
            if(isEmpty(row,col)){
                cells[row][col]=new Cell(player.symbol.symbol);
                if(canWin(player,row,col)){
                    winner=player;
                    return;
                }
                players.offerLast(player);
            }else{
                System.out.println("WARNING!! , not a valid move, try again...");
                players.addFirst(player);
            }
        }
    }

    private boolean isEmpty(int r,int c){
        if(r<0 || r>= cells.length || c<0 || c>= cells[0].length)
            return false;
        boolean equals = cells[r][c].symbol.equals(".");
        return equals;
    }

    private boolean canWin(Player player,int r,int c){
        int count=0;
        //check row
        for(int i=0;i<cells[0].length;i++){
            if(cells[r][i].symbol.equals(player.symbol))
                count++;
        }
        if(count==3)
            return true;
        count=0;
        //check column
        for(int i=0;i< cells.length;i++){
            if(cells[i][c].symbol.equals(player.symbol))
                count++;
        }
        if(count==3)
            return true;
        count=0;

        if(r==1 && c==1){
            //check lDiag
            for(int i=0;i< cells.length;i++){
                if(cells[i][i].symbol.equals(player.symbol))
                    count++;
            }
            if(count==3)
                return true;
            count=0;
            //check rDiag
            for(int i= cells.length-1; i>=0 ;i--){
                if(cells[i][i].symbol.equals(player.symbol))
                    count++;
            }
            if(count==3)
                return true;
        }

        return false;
    }
}
