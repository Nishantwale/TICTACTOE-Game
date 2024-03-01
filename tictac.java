import java.util.Scanner;

public class tictac {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY = ' ';
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char currentPlayer = 'X';
    private static String Player1Name;
    private static String Player2Name;
    private static boolean gameOver = false;
    private static boolean againstComputer = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome To Java TIC-TAC-TOE Game!");
        System.out.print("Enter The Name of Player1 :");
        Player1Name = sc.nextLine();
        System.out.print("Do You Want To Play With Computer? Press (A) or With Friend? Press (B): ");
        char choice = Character.toUpperCase(sc.next().charAt(0));
        againstComputer = (choice=='A');

        if (!againstComputer) {
            System.out.print("Enter The Name of Player2: ");
            Player2Name = sc.next();
        } else {
            Player2Name = "Computer";
        }

        boolean playAgain = true;
        while (playAgain) {
            playGame();
            System.out.print("Do you want to play again? (Y/N): ");
            char playChoice = Character.toUpperCase(sc.next().charAt(0));
            playAgain = (playChoice == 'Y');
        }
        System.out.println("Thanks for TIC TAC TOE GAME! Have a Nice Day");
    }

    private static void playGame() {
        gameOver = false; // Reset gameOver to false for a new game
        initializeBoard();
        printBoard();

        while (!gameOver) {
            if (currentPlayer == 'X') {
                getPlayerMove();
            } else {
                if (againstComputer) {
                    getComputerMove();
                } else {
                    getFriendMove();
                }
            }
            printBoard();
            checkGameStatus();
            switchPlayer();
        }
    }

    private static void initializeBoard(){
        for(int i=0; i<BOARD_SIZE; i++){
            for(int j=0; j<BOARD_SIZE; j++){
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void getPlayerMove(){
        Scanner sc = new Scanner(System.in);
        int row, col;
        do{
            System.out.print("Player " + Player1Name + ", enter your move (row and column): ");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col));
        board[row][col] = 'X';
    }

    private static void getFriendMove(){
        Scanner sc = new Scanner(System.in);
        int row, col;
        do{
            System.out.print("Player " + Player2Name + ", enter your move (row and column): ");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col));
        board[row][col] = 'O';
    }

    private static void getComputerMove() {
        int row, col;
        boolean validMove = false;
        do {
            row = (int) (Math.random() * BOARD_SIZE);
            col = (int) (Math.random() * BOARD_SIZE);
            if (board[row][col] == EMPTY) {
                validMove = true;
            }
        } while (!validMove);
        
        System.out.println(Player2Name + " Placed O at " + row + " " + col);
        board[row][col] = 'O';
    }

    private static boolean isValidMove(int row, int col){
        if(row<0 || row>=BOARD_SIZE || col<0 || col>=BOARD_SIZE || board[row][col]!= EMPTY){
            System.out.println("Invalid Move! Please Try Again");
            return false;
        }
        return true;
    }

    private static void checkGameStatus(){
        if(checkWin('X')){
            System.out.println("Player " + Player1Name + " Wins!");
            gameOver = true;
        }else if(checkWin('O')){
            System.out.println("Player " + Player2Name + " Wins");
            gameOver = true;
        }else if(isBoardFull()){
            System.out.println("Its a Draw!");
            gameOver = true;
        }
    }

    private static boolean checkWin(char player){
        for(int i=0; i<BOARD_SIZE; i++){
            if(board[i][0]==player && board[i][1]==player && board[i][2]==player){
                return true;
            }else if(board[0][i]==player && board[1][i]==player && board[2][i]==player){
                return true;
            }
        }
        if(board[0][0]==player && board[1][1]==player && board[2][2]==player){
            return true;
        }
        if(board[0][2]==player && board[1][1]==player && board[2][0]==player){
            return true;
        }
        return false;
    }

    private static boolean isBoardFull(){
        for(int i=0; i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                if(board[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer(){
        currentPlayer = (currentPlayer == 'X') ? 'O':'X';
    }
}
