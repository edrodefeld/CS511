package Assignment2;

import java.util.Random;

/**
 * Created by eric on 9/22/16.
 */
public enum ApparatusType{
    LEGPRESSMACHINE, BARBBELL, HACKSQUATMACHINE, LEGEXTENSIONMACHINE,
    LEGCURLMACHINE, LATPULLDOWNMACHINE, PECDECKMACHINE, CABLECROSSOVERMACHINE;

    private static ApparatusType[] apparatusTypesArr = ApparatusType.values();
    private static final int SIZE = apparatusTypesArr.length;
    private static Random random = new Random();

    public static int getApparatusIndex(ApparatusType apparatusType){
        for(int i = 0; i < SIZE; i++){
            if(apparatusType.equals(apparatusTypesArr[i])){
                return i;
            }
        }
        return -1;
    }

    public static ApparatusType generateRandom(){
        return apparatusTypesArr[random.nextInt(SIZE)];
    }
}

