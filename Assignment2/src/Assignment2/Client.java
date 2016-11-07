package Assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * Created by eric on 9/22/16.
 */
public class Client {

    private int id;
    private List<Exercise> routine;
    private final int MAX_NUM_EXERCISES = 10;
    private static Random random = new Random();

    public Client(int id){
        this.id = id;
        routine = new ArrayList<Exercise>();
    }

    public void addExercise(Exercise exercise){
        routine.add(exercise);
    }

    public static Client generateRandom(int id){
        Client client = new Client(id);
        client.createRoutine();
        return client;
    }

    public List<Exercise> getRoutine(){
        return routine;
    }

    //Sample routine that one can follow
    public void createRoutine(){
        int numExercises = random.nextInt(MAX_NUM_EXERCISES);
        for(int i = 0; i < numExercises; i++) {
            HashMap<WeightPlateSize, Integer> weights = WeightPlateSize.generateRandomWeights();
            Exercise exercise = Exercise.generateRandom(weights);
            routine.add(exercise);
        }
    }

    public void performRoutine(List<Exercise> routine, ArrayList<Semaphore> apparatusAvailable, ArrayList<Semaphore> weightsAvailable){
        for(Exercise exercise : routine){
            System.out.println("Performing routine");
            int duration = exercise.getDuration();
            HashMap<WeightPlateSize, Integer> weights = exercise.getWeight();
            ApparatusType apparatusType = exercise.getApparatusType();
            try{
                apparatusAvailable.get(ApparatusType.getApparatusIndex(apparatusType)).acquire();
                for(WeightPlateSize weight : weights.keySet()){
                    System.out.println("Acquiring weight");
                    weightsAvailable.get(WeightPlateSize.getWeightIndex(weight)).acquire();
                }
                while(duration > 0) {
                    duration--;
                }
                apparatusAvailable.get(ApparatusType.getApparatusIndex(apparatusType)).release();
                for(WeightPlateSize weight : weights.keySet()){
                    System.out.println("Releasing Weight");
                    weightsAvailable.get(WeightPlateSize.getWeightIndex(weight)).release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}