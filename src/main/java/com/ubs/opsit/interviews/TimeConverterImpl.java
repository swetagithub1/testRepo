package com.ubs.opsit.interviews;
import java.lang.Math;


import org.apache.commons.lang.StringUtils;

/**
 * Sweta Varshney
 * This class is to convert time as per the clock
 */
public class TimeConverterImpl implements TimeConverter {
    /*constants*/
    private final String midnightTime1 = "00:00:00";
    private final String midnightTime2 = "24:00:00";

    private final char charY = 'Y';
    private final char charR = 'R';
    private final char charO = 'O';

    /**
     * Method to convert time
     * @param aTime
     * @return String
     */
    @Override
    public String convertTime(String aTime) {
        /*variables for rows*/
        char[] firstRow = new char[1];
        char[] secondRow = new char[4];
        char[] thirdRow = new char[4];
        char[] fourthRow = new char[11];
        char[] fifthRow = new char[4];

        /*final time String*/
        StringBuffer finalTimeString =  new StringBuffer();

        if(!StringUtils.isEmpty(aTime)) {
            /*Split the time string*/
            String hours = aTime.substring(0,2);
            String minutes = aTime.substring(3,5);
            String seconds = aTime.substring(6,8);

            /*create first row*/
            if (aTime.equalsIgnoreCase(midnightTime1) || aTime.equalsIgnoreCase(midnightTime2)) {
                firstRow[0] = charY;
            } else {
                firstRow[0] = charO;
            }

            /* create second and third row (hours) */
            int noOfRsInSEcondRow = Math.floorDiv(Integer.parseInt(hours),5);
            for (int i = 0; i < noOfRsInSEcondRow ; i++) {
                secondRow[i] = charR;
            }
            FillBlankPlaces(secondRow,noOfRsInSEcondRow);

            int noOfRsinThirdRow = Math.floorMod(Integer.parseInt(hours),5);
            for (int i = 0; i < noOfRsinThirdRow ; i++) {
                thirdRow[i] = charR;
            }
            FillBlankPlaces(thirdRow,noOfRsinThirdRow);

            /* create fourth and fifth row (minutes) */
            int noOfRsAndYsInFourthRow = Math.floorDiv(Integer.parseInt(minutes),5);

            for (int i = 1; i <= noOfRsAndYsInFourthRow; i++) {
                if(Math.floorMod(i,3)==0){
                    fourthRow[i-1]=charR;
                }else{
                    fourthRow[i-1]=charY;
                }
            }
            FillBlankPlaces(fourthRow,noOfRsAndYsInFourthRow);

            int noOfYsinFifthRow = Math.floorMod(Integer.parseInt(minutes),5);
            for (int i = 0; i < noOfYsinFifthRow ; i++) {
                fifthRow[i] = charY;
            }
            FillBlankPlaces(fifthRow,noOfYsinFifthRow);

            /*append all rows*/
            finalTimeString.append(String.valueOf(firstRow)+"\r\n"+String.valueOf(secondRow)+"\r\n"+String.valueOf(thirdRow)+"\r\n"+String.valueOf(fourthRow)
            +"\r\n"+String.valueOf(fifthRow));

        }
        return finalTimeString.toString();
    }

    /**
     * This method is fill Os in blank places
     * @param row
     * @param noOfAlreadyFilledRows
     */
    private void FillBlankPlaces(char[] row, int noOfAlreadyFilledRows) {
        for (int j =noOfAlreadyFilledRows;j<row.length ;j++) {
            row[j] = charO;
        }

    }
}
