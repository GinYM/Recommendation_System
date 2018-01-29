import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (Yiming) 
 * @version (01/29/2018)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings rating = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        System.out.println("Num of movies: "+rating.getMovieSize()+" Num of raters: "+rating.getRaterSize());
        ArrayList<Rating> r = rating.getAverageRatings(12);
        String lowestName = "";
        double lowest = 12;
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Title: "+rating.getTitle(rt.getItem()));
            if(rt.getValue()<lowest){
                lowest = rt.getValue();
                lowestName = rating.getTitle(rt.getItem());
            }
            
        }
        System.out.println("LowestName: "+lowestName);
    }
    
    public void getAverageRatingOneMovie(){
        //SecondRatings rating = new SecondRatings("data/StepTwo_movie","data/StepTwo_ratings");
        SecondRatings rating = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        String id = rating.getID("Vacation");
        double avg = rating.getAverageByID(id, 20);
        System.out.println("Rating: "+avg);
        System.out.println("Movies >= 50: "+rating.movieWithRater(50));
    }
    
}
