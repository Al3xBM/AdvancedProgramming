import java.util.ArrayList;
import java.util.List;
import  java.util.*;

public class Matching {
    private Map<Residents, Hospitals> matching = new HashMap<>();

    public Matching() {
    }

    public Matching(Map<Residents, Hospitals> matching) {
        this.matching = matching;
    }

    public Map<Residents, Hospitals> getMatching() {
        return matching;
    }

    public void setMatching(Map<Residents, Hospitals> matching) {
        this.matching = matching;
    }

    public void createMatching(Map<Hospitals, List<Residents>> hosPref, Map<Residents, List<Hospitals>> resPref) {
        // iterators for both maps
        Iterator itHos = hosPref.keySet().iterator();
        Iterator itRes = resPref.keySet().iterator();

        // an array to tell me which resident doesn't have an hospital assigned yet
        boolean[] marked = new boolean[resPref.keySet().size()];

        int i = 0;
        while (itRes.hasNext()) {
            itRes.next();
            marked[i] = false;
            ++i;
        }
        int n = i;

        // goes over every resident's preference list until each
        // has a hospital assigned
        int ok = 1;
        // use checkWith to know at which element from the pref list
        // i should look at
        int checkWith = 0;
        while (ok == 1) {
            ok = 0;

            itRes = resPref.keySet().iterator();

            i = 0;
            while (itRes.hasNext()) {
                Object key = itRes.next();
                if( marked[i] != true ) {
                    // i put the pref list of the resident from
                    // the map into a list for ease of access
                    List<Hospitals> hList = resPref.get(key);
                    // i get the hospital's pref list. I use checkWith to
                    // know which hospital's list i need to retrieve
                    List<Residents> rList = hosPref.get(hList.get(checkWith));
                    for( int j = 0; j < checkWith; ++j )
                        if (rList.get(j).getName().equals(((Residents)key).getName()) ) {
                            if( hList.get(j).assignedRes()) {
                                marked[i] = true;
                                this.matching.put(((Residents) key), hList.get(j));
                                break;
                            }
                        }
                        if( marked[i] == false )
                            if( checkWith >= hList.size() - 1 ) {
                                marked[i] = true;
                                this.matching.put( ((Residents)key), hList.get(checkWith) );
                            }
                }
                ++i;
            }

            ++checkWith;
            for( i = 0; i < n; ++i ){
                if( marked[i] == false) {
                    ok = 1;
                    break;
                }
            }
        }
    }
}
