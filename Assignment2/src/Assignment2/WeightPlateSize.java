package Assignment2;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by eric on 9/22/16.
 */
public enum WeightPlateSize{
    SMALL_3KG, MEDIUM_5KG, LARGE_10KG;

    private static final int MAX_NUM_WEIGHTS = 10;
    private static WeightPlateSize[] weightPlateSizeArr = WeightPlateSize.values();
    private static final int SIZE = weightPlateSizeArr.length;
    private static Random random = new Random();

    public static WeightPlateSize generateRandom(){
        return weightPlateSizeArr[random.nextInt(SIZE)];
    }

    public static int getWeightIndex(WeightPlateSize weightPlateSize){
        for(int i = 0; i < SIZE; i++){
            if(weightPlateSize.equals(weightPlateSizeArr[i])){
                return i;
            }
        }
        return -1;
    }

    public static HashMap<WeightPlateSize, Integer> generateRandomWeights(){
        HashMap<WeightPlateSize, Integer> weights = new HashMap<WeightPlateSize, Integer>();
        int numWeights = random.nextInt(MAX_NUM_WEIGHTS);
        for(int i = 0; i < numWeights; i++){
            weights.put(WeightPlateSize.generateRandom(), 1);
        }
        return weights;
    }

}


