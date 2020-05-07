public class Board {
    public int[][] board;
    Board(){
        board = new int[15][15];
    }

    public boolean validMove(String position, int id){
        int vertical = position.charAt(0) - 97;

        int horizontal;
        if( position.length() > 2 )
            horizontal = Integer.parseInt(String.valueOf(position.charAt(1) + position.charAt(2)));
        else
            horizontal = Integer.parseInt(String.valueOf(position.charAt(1)));

        if( !( vertical < 15 && vertical > -1 && horizontal > -1 && horizontal < 15 ) )
            return false;

        if( board[vertical][horizontal] != 0 )
            return false;
        else
            board[vertical][horizontal] = id;

        System.out.println(" a piece was placed at the position " + vertical + "-" + horizontal);
        return true;
    }

    public boolean checkWinCondition(String position, int id){
        int vertical = position.charAt(0) - 97;
        int horizontal;
        if( position.length() > 2 )
            horizontal = Integer.parseInt(String.valueOf(position.charAt(1) + position.charAt(2)));
        else
            horizontal = Integer.parseInt(String.valueOf(position.charAt(1)));
        int ver;
        int orz;
        int countP = 1;

        // check horizontal
        orz  = horizontal - 1;
        while (orz > -1 ) {
            if (board[vertical][orz] == id) {
                --orz;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;
        orz = horizontal + 1;
        while( orz < 15 ){
            if( board[vertical][orz] == id ){
                ++orz;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;

        //check first diagonal
        orz = horizontal - 1; ver = vertical - 1;
        countP = 1;
        while( orz > -1 && ver > -1 ){
            if (board[ver][orz] == id) {
                --orz;
                --ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;
        orz = horizontal + 1; ver = vertical + 1;
        while( orz < 15 && ver < 15){
            if( board[vertical][orz] == id ){
                ++orz;
                ++ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;

        //check vertical
        countP = 1;
        ver = vertical - 1;
        while (ver > -1 ) {
            if (board[ver][horizontal] == id) {
                --ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;
        ver = vertical + 1;
        while( ver < 15 ){
            if( board[ver][horizontal] == id ){
                ++ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;

        // check second diagonal
        orz = horizontal - 1; ver = vertical + 1;
        countP = 1;
        while( orz > -1 && ver > -1 ){
            if (board[ver][orz] == id) {
                --orz;
                ++ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;
        orz = horizontal + 1; ver = vertical - 1;
        while( orz < 15 && ver < 15){
            if( board[vertical][orz] == id ){
                ++orz;
                --ver;
                ++countP;
            } else
                break;
        }
        if( countP >= 5 )
            return true;


        return false;
    }
}
