import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.stream.*;
        import java.util.List;
        import java.util.*;

public class main {
    public static void main (String argvs[] ){
        var r =  IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Residents("R" + i) )
                .toArray(Residents[]::new);

        var h =  IntStream.rangeClosed(0, 2)
                .mapToObj( i -> new Hospitals( "H" + i ) )
                .toArray(Hospitals[]::new);

        //list which holds all residents
        List<Residents> residentList = new ArrayList<>();
        residentList.addAll( Arrays.asList(r) );

        //sorting the list
        residentList = residentList.stream()
                .sorted(Comparator.comparing(Residents::getName))
                .collect(Collectors.toList());

        for( Residents rs : residentList ){
            System.out.println( rs.getName() );
        }

        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);
        // treeset to hold the hospitlas
        TreeSet<Hospitals> hospitalList = new TreeSet<>();
        hospitalList.addAll( Arrays.asList(h) );
        // map res-pref
        Map<Residents, List<Hospitals>> resPrefMap = new HashMap<>();
        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        Iterator it = resPrefMap.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = resPrefMap.get(key);
            System.out.println( key + " " + value );
        }

        // map hos-pref
        Map<Hospitals, List<Residents>> hosPrefMap = new HashMap<>();
        hosPrefMap.put( h[0], Arrays.asList( r[3], r[0], r[1], r[2]));
        hosPrefMap.put( h[1], Arrays.asList( r[0], r[2], r[1]));
        hosPrefMap.put( h[2], Arrays.asList( r[0], r[1], r[3]));

        Iterator it2 = hosPrefMap.keySet().iterator();
        while (it2.hasNext()) {
            Object key = it2.next();
            Object value = hosPrefMap.get(key);
            System.out.println( key + " " + value );
        }

        // list with all res who have h[0] and h[2] in their list
        List<Hospitals> target = Arrays.asList(h[0], h[2]);
        List<Residents> result = residentList.stream()
                .filter(res -> resPrefMap.get(res).containsAll(target))
                .collect(Collectors.toList());

        System.out.println(" Residents who accept h[0] and h[2]: ");

        for( Residents rs : result ){
            System.out.println( rs );
        }

        // filtrez lista de spitale pentru a le afisa doar pe cele
        // ce il accepta pe r0
        System.out.println(" Residents who accept r[0]: ");
        hospitalList.stream()
                .filter(hos -> hosPrefMap.get(hos).contains(r[0]))
                .forEach(System.out::println);

        // i instantiate the matching
        Matching match = new Matching();
        // i give it the two maps defined previously as input
        match.createMatching(hosPrefMap, resPrefMap);

        // i create a new map in which i'll put the output from getMatching
        Map<Residents, Hospitals> mccc = new HashMap<>();
        mccc = match.getMatching();
        System.out.println(" This is the matching i create: ");
        Iterator itMC = mccc.keySet().iterator();
        while( itMC.hasNext() ){
            Object key = itMC.next();
            Object value = mccc.get(key);
            System.out.println( key + " " + value );
        }


        Faker faker = new Faker();
        // create residents
        int resNr = Integer.parseInt(faker.number().digit());
        var rfake =  IntStream.rangeClosed(0, resNr)
                .mapToObj(i -> new Residents(faker.name().fullName() + i) )
                .toArray(Residents[]::new);

        // create hospitals
        int hosNr = Integer.parseInt(faker.number().digit());
        var hfake =  IntStream.rangeClosed(0, hosNr)
                .mapToObj( i -> new Hospitals( faker.medical().hospitalName() + i ) )
                .toArray(Hospitals[]::new);

        System.out.println(" res nr is " + resNr + " hos nr is " + hosNr );
        // create map res-pref
        Map<Residents, List<Hospitals>> resPrefFake = new HashMap<>();
        for( int i = 0; i < resNr; ++i ){
            List<Hospitals> addfake = new ArrayList<>();
            for( int j = 0; j < hosNr; ++j ){
                if( new Random().nextInt(100) % 2 == 0 )
                    addfake.add(hfake[j]);
            }
            resPrefFake.put(rfake[i], addfake);
        }
        // create map hos-pref
        Map<Hospitals, List<Residents>> hosPrefFake = new HashMap<>();
        for( int i = 0; i < hosNr; ++i ){
            List<Residents> addfake = new ArrayList<>();
            for( int j = 0; j < resNr; ++j ){
                if( new Random().nextInt(100) % 2 == 0 )
                    addfake.add(rfake[j]);
            }
            hosPrefFake.put(hfake[i], addfake);
        }

        for( int i = 0; i < hosNr; ++i ){
            int fakeCap = Integer.parseInt(faker.number().digit());
            hfake[i].setCapacity(fakeCap);
            System.out.println( fakeCap);
        }

        System.out.println(" res fake map: ");
        Iterator itf = resPrefFake.keySet().iterator();
        while (itf.hasNext()) {
            Object key = itf.next();
            Object value = resPrefFake.get(key);
            System.out.println( key + " " + value );
        }

        System.out.println(" hos fake map: ");
        itf = hosPrefFake.keySet().iterator();
        while (itf.hasNext()) {
            Object key = itf.next();
            Object value = hosPrefFake.get(key);
            System.out.println( key + " " + value );
        }

        Matching fakeMatch = new Matching();
        fakeMatch.createMatching(hosPrefFake, resPrefFake);

        Map<Residents, Hospitals> mtchFake = new HashMap<>();
        Map<Hospitals, Residents> hosresFake = new HashMap<>();
        mtchFake = fakeMatch.getMatching();
        System.out.println(" Fake matching: ");
        Iterator itFake = mtchFake.keySet().iterator();
        while( itFake.hasNext() ){
            Object key = itFake.next();
            Object value = mtchFake.get(key);
            System.out.println( key + " " + value );
            hosresFake.put((Hospitals) value, (Residents) key);
        }

        List<Residents> residentFakeList = new ArrayList<>();
        residentFakeList.addAll( Arrays.asList(rfake) );

        List<Hospitals> hospitalFakeList = new ArrayList<>();
        hospitalFakeList.addAll( Arrays.asList(hfake) );

        // here i check if the matching is stable
        int isStable = 0;
        itFake = mtchFake.keySet().iterator();
        while( itFake.hasNext() ){
            Object key = itFake.next();
            Object value = mtchFake.get(key);
            List<Hospitals> rfPref = resPrefFake.get(key);
            // i go through the preference list of every resident
                for( int i = 0; i < rfPref.size(); ++i ){
                    if( rfPref.get(i) != value ){
                        // if the hospital i am at is not the one assigned in the matching( is preferred over it )
                        // then i have to check if this hospital doesn't also prefer the current resident over the one
                        // that has been assigned to it
                        List<Residents> hfPref = hosPrefFake.get(value);
                        // i go through the hospital's pref list until i get to the one that's been assigned to it
                        for( int j = 0; j < hfPref.size() && hfPref.get(j) != hosresFake.get(value); ++j){
                            if(hfPref.get(j) == key ) {
                                isStable = 1;
                                break;
                            }
                        }
                    }
                    else
                        break;
                    if( isStable == 1 )
                        break;
                }
        }

        if( isStable == 1 )
            System.out.println(" The matching is not stable ");
        else
            System.out.println(" The matching is stable ");



    }
}
