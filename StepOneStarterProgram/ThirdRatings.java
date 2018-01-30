import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (Yiming) 
 * @version (01/29/2018)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings readCSV = new FirstRatings();
        myRaters = readCSV.loadRaters(ratingsfile);
    }
    
    public int movieWithRater(int minimal){
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        for(Rater r : myRaters){
            ArrayList<String> tmp = r.getItemsRated();
            for(String s:tmp){
                if(!result.keySet().contains(s)){
                    result.put(s,1);
                }
                else{
                    result.put(s,result.get(s)+1);
                }
            }
        }
        int count = 0;
        int num;
        for(String key: result.keySet()){
            num = result.get(key);
            if(num>=minimal){
                count++;
            }
        }
        return count;
    }
    
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double avg = 0.0;
        double rating = 0.0;
        int count = 0;
        for(Rater r: myRaters){
            rating = r.getRating(id);
            if(rating!=-1){
                avg+=rating;
                count++;
            }
        }
        //System.out.println("Count: "+count);
        if(count>=minimalRaters){
            return avg/count;
        }
        else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double avg = 0.0;
        for(String id : movies){
            avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                result.add(new Rating(id,avg));
            }
        }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters, int minimalYears){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(minimalYears));
        double avg = 0.0;
        for(String id : movies){
            avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                result.add(new Rating(id,avg));
            }
        }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> m = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        double avg = 0.0;
        for(String id: movies){
            avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                m.add(new Rating(id,avg));
            }
        }
        return m;
    }
    
}
