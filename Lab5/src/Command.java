public interface Command {

    String getName();

    void executeCommand();

    default String getType( String comm ){
        String hold = new String();
        for( int i = 0; i < comm.length(); ++i ){
            if( comm.charAt(i) == '(' )
                break;
            else
                hold += comm.charAt(i);
        }
        return hold;
    }
}
