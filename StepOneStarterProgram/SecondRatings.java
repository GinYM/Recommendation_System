
/**
 * Write a description of SecondRatings here.
 * 
 * @author (Yiming) 
 * @version (01/29/2018)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings readCSV = new FirstRatings();
        myMovies = readCSV.loadMovies(moviefile);
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
    
    public int getMovieSize(){
        return myMovies.size();
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
        String id;
        double avg = 0.0;
        for(Movie mv : myMovies){
            id = mv.getID();
            avg = getAverageByID(id,minimalRaters);
            if(avg != 0.0){
                result.add(new Rating(id,avg));
            }
        }
        return result;
    }
    
    public String getTitle(String id){
        String title = "String Not Found";
        for(Movie mv: myMovies){
            if(mv.getID().equals(id)){
                title = mv.getTitle();
            }
        }
        return title;
    }
    
    public String getID(String title){
        for(Movie mv: myMovies){
            if(mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
}