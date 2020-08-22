/**
 * this class represents status information
 *
 * @author Amir Naziri
 */
public class Status
{
    private static int dataHitNumber = 0;
    private static int dataMissNumber = 0;
    private static int dataAccess = 0;
    private static int dataReplace = 0;

    private static int instructionHitNumber = 0;
    private static int instructionMissNumber = 0;
    private static int instructionAccess = 0;
    private static int instructionReplace = 0;

    private static int demandFetch = 0 ;
    private static int copyBack = 0;

    /**
     * increaseDataHitNumber one unit
     */
    public static void increaseDataHitNumber () {
        dataHitNumber++;
    }

    /**
     * increaseDataMissNumber one unit
     */
    public static void increaseDataMissNumber () {
        Status.dataMissNumber++;
    }

    /**
     * increaseInstructionHitNumber one unit
     */
    public static void increaseInstructionHitNumber () {
        Status.instructionHitNumber++;
    }

    /**
     * increaseInstructionMissNumber one unit
     */
    public static void increaseInstructionMissNumber () {
        Status.instructionMissNumber++;
    }

    /**
     * increaseDataAccess one unit
     */
    public static void increaseDataAccess () {
        Status.dataAccess++;
    }

    /**
     * increaseInstructionAccess one unit
     */
    public static void increaseInstructionAccess () {
        Status.instructionAccess++;
    }

    /**
     * increaseDataReplace one unit
     */
    public static void increaseDataReplace () {
        Status.dataReplace++;
    }

    /**
     * increaseInstructionReplace one unit
     */
    public static void increaseInstructionReplace () {
        Status.instructionReplace++;
    }

    /**
     * increaseDemandFetch byte unit
     * @param bytes number's to add
     */
    public static void increaseDemandFetch (int bytes) {
        Status.demandFetch += bytes;
    }

    /**
     * increaseCopyBack byte unit
     * @param bytes number's to add
     */
    public static void increaseCopyBack (int bytes) {
        Status.copyBack += bytes;
    }

    /**
     * @return instructionMissNumber
     */
    public static int getInstructionMissNumber () {
        return instructionMissNumber;
    }

    /**
     * @return instructionHitNumber
     */
    public static int getInstructionHitNumber () {
        return instructionHitNumber;
    }

    /**
     * @return dataMissNumber
     */
    public static int getDataMissNumber () {
        return dataMissNumber;
    }

    /**
     * @return dataHitNumber
     */
    public static int getDataHitNumber () {
        return dataHitNumber;
    }

    /**
     * @return copyBack
     */
    public static int getCopyBack () {
        return copyBack;
    }

    /**
     * @return dataAccess
     */
    public static int getDataAccess () {
        return dataAccess;
    }

    /**
     * @return dataReplace
     */
    public static int getDataReplace () {
        return dataReplace;
    }

    /**
     * @return demandFetch
     */
    public static int getDemandFetch () {
        return demandFetch;
    }

    /**
     * @return instructionAccess
     */
    public static int getInstructionAccess () {
        return instructionAccess;
    }

    /**
     * @return instructionReplace
     */
    public static int getInstructionReplace () {
        return instructionReplace;
    }
}
