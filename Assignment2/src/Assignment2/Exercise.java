package Assignment2;

import java.util.HashMap;

/**
 * Created by eric on 9/22/16.
 */
public class Exercise {

    private ApparatusType apparatusType;
    private HashMap<WeightPlateSize, Integer> weight;
    private int duration;

    public ApparatusType getApparatusType() {
        return apparatusType;
    }

    public HashMap<WeightPlateSize, Integer> getWeight(){
        return weight;
    }

    public int getDuration(){
        return duration;
    }

    public Exercise(ApparatusType apparatusType, HashMap<WeightPlateSize, Integer> weight, int duration){
        this.apparatusType = apparatusType;
        this.weight = weight;
        this.duration = duration;
    }

    public static Exercise generateRandom(HashMap<WeightPlateSize, Integer> weights){
        Exercise exercise = new Exercise(ApparatusType.generateRandom(), weights, 10); //TODO: Change "10" to actual variable duration
        return exercise;
    }

}
