
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (Yiming) 
 * @version (01/30/2018)
 */
public class MinutesFilter implements Filter{
    
    private int minMinute;
    private int maxMinute;
    
    public MinutesFilter(int minMinute, int maxMinute){
        this.minMinute = minMinute;
        this.maxMinute = maxMinute;
    }
    
    public boolean satisfies(String id){
        int minutes = MovieDatabase.getMinutes(id);
        if(minutes>=minMinute && minutes<=maxMinute){
            return true;
        }
        else{
            return false;
        }
    }
}
