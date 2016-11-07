package Assignment2;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * Created by eric on 9/22/16.
 */
public class Gym implements Runnable {

    private static final int GYM_SIZE = 30;
    private static final int GYM_REGISTERED_CLIENTS = 1000;
    private HashMap<WeightPlateSize, Integer> numWeightPlates;
    private HashMap<ApparatusType, Integer> apparatusTypes;
    private HashSet<Integer> clients;
    private ExecutorService executor;
    private ArrayList<Semaphore> weightsAvailable;
    private ArrayList<Semaphore> apparatusTypeAvailable;
    private Random random = new Random();


    public Gym(HashMap<WeightPlateSize, Integer> numWeightPlates, HashMap<ApparatusType, Integer> apparatusTypes0, HashSet<Integer> clients, ExecutorService executor) {
        this.numWeightPlates = numWeightPlates;
        this.apparatusTypes = apparatusTypes0;
        this.clients = clients;
        this.executor = executor;
        apparatusTypeAvailable = setupApparatusTypeSemaphore(apparatusTypes);
        weightsAvailable = setupWeightsAvailableSemaphore(numWeightPlates);
    }

    public void run() {
        Integer id = random.nextInt(GYM_REGISTERED_CLIENTS);
        clients.add(id);
        Client client = Client.generateRandom(id);
        client.performRoutine(client.getRoutine(), apparatusTypeAvailable, weightsAvailable);
    }

    public ArrayList<Semaphore> setupApparatusTypeSemaphore(HashMap<ApparatusType, Integer> apparatusTypes) {
        apparatusTypeAvailable = new ArrayList<Semaphore>();

        for (Integer n: apparatusTypes.values()) {
            apparatusTypeAvailable.add(new Semaphore(n));
        }
        return apparatusTypeAvailable;
    }

    public ArrayList<Semaphore> setupWeightsAvailableSemaphore(HashMap<WeightPlateSize, Integer> numWeightPlates) {
        weightsAvailable = new ArrayList<Semaphore>();
        for (Integer n : numWeightPlates.values()) {
            weightsAvailable.add(new Semaphore(n));
        }
        return weightsAvailable;
    }

    public static int getGymSize() {
        return GYM_SIZE;
    }
}

