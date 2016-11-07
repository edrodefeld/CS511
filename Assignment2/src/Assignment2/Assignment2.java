package Assignment2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eric on 9/22/16.
 */
public class Assignment2 {
    public static void main(String[] args){

        //Initialize max number of weights
        HashMap<WeightPlateSize, Integer> numWeightPlates = new HashMap<WeightPlateSize, Integer>();
        HashMap<ApparatusType, Integer> apparatusTypes = new HashMap<ApparatusType, Integer>();
        numWeightPlates.put(WeightPlateSize.SMALL_3KG, 40);
        numWeightPlates.put(WeightPlateSize.MEDIUM_5KG, 30);
        numWeightPlates.put(WeightPlateSize.LARGE_10KG, 20);
        for(ApparatusType at : ApparatusType.values()) {
            apparatusTypes.put(at, 5);
        }

        HashSet<Integer> clients = new HashSet<Integer>();
        ExecutorService executorService = Executors.newFixedThreadPool(Gym.getGymSize());
        Gym gym = new Gym(numWeightPlates, apparatusTypes, clients, executorService);
        for(int i = 0; i < 10000; i++){
            System.out.println("Client using gym");
            Runnable thread = new Thread(gym);
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}

