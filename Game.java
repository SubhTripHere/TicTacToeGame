public class Game {
    public static void main(String[] args) {
        Board board=new Board(2,3,3);
        board.addPlayers("subham",new Symbol("X"));
        board.addPlayers("raj",new Symbol("0"));
        System.out.println("************************ Game starting now ************************");
        board.startGame();
        System.out.println("************************ Game Ended ************************");
        System.out.println("************************ Congratulations to "+ board.winner.name +" ************************");
    }
}
