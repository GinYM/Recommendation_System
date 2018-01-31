import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        RaterDatabase.initialize("ratings.csv");
        FourthRatings rating = new FourthRatings();
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        ArrayList<Rating> r = rating.getAverageRatings(35);
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
        /*
        String lowestName = "";
        double lowest = 12;
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            if(rt.getValue()<lowest){
                lowest = rt.getValue();
                //lowestName = rating.getTitle(rt.getItem());
            }
            
        }
        System.out.println("LowestName: "+lowestName);
        */
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        RaterDatabase.initialize("ratings.csv");
        FourthRatings rating = new FourthRatings();
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        AllFilters all = new AllFilters();
        all.addFilter(new YearAfterFilter(1990));
        all.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(8,all);
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
    }
    
    public void printSimilarRatings(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> result = rate.getSimilarRatings("71",20,5);
        System.out.println("result size "+result.size());
        System.out.println("The top is: "+MovieDatabase.getTitle(result.get(0).getItem())+" Rate: "+result.get(0).getValue());
        
    }
    
    public void printSimilarRatingByGenre(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> result = rate.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));
        System.out.println("result size "+result.size());
        System.out.println("The top is: "+MovieDatabase.getTitle(result.get(0).getItem())+" Rate: "+result.get(0).getValue());
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> result = rate.getSimilarRatingsByFilter("120",10,2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        System.out.println("result size "+result.size());
        System.out.println("The top is: "+MovieDatabase.getTitle(result.get(0).getItem())+"\nRate: "+result.get(0).getValue());
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(80,160));
        all.addFilter(new GenreFilter("Drama"));
        
        ArrayList<Rating> result = rate.getSimilarRatingsByFilter("168",10,3, all);
        System.out.println("result size "+result.size());
        System.out.println("The top is: "+MovieDatabase.getTitle(result.get(0).getItem())+"\nRate: "+result.get(0).getValue());
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters all = new AllFilters();
        all.addFilter(new YearAfterFilter(1975));
        all.addFilter(new MinutesFilter(70,200));
        
        ArrayList<Rating> result = rate.getSimilarRatingsByFilter("314",10,5, all);
        System.out.println("result size "+result.size());
        System.out.println("The top is: "+MovieDatabase.getTitle(result.get(0).getItem())+"\nRate: "+result.get(0).getValue());
    }
    
    public void printFour(){
        FourthRatings rate = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("StepFour");
        AllFilters all = new AllFilters();
        double result = rate.dotProduct(RaterDatabase.getRater("15"),RaterDatabase.getRater("20"));
        System.out.println("result is: "+result);
    }
    
    public void printFourExe4(){
        
        System.out.println("result is: "+((6*7+4*30)/2));
    }
}
