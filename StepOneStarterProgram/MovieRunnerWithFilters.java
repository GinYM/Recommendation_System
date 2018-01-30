import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (Yiming) 
 * @version (01/30/2018)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
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
    
    public void printAverageRatingsByYear(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
        
        
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
            System.out.println("Rating: "+rt.getValue()+" Year: "+MovieDatabase.getYear(rt.getItem())+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            if(rt.getValue()<lowest){
                lowest = rt.getValue();
                //lowestName = rating.getTitle(rt.getItem());
            }
            
        }
        //System.out.println("LowestName: "+lowestName);
        */
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
        
        
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
            System.out.println("Genres: "+MovieDatabase.getGenres(rt.getItem()));
            if(rt.getValue()<lowest){
                lowest = rt.getValue();
            }            
        }
        */
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
        /*
        String lowestName = "";
        
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Time: "+MovieDatabase.getMinutes(rt.getItem())+" Title: "+MovieDatabase.getTitle(rt.getItem()));
                  
        }
        */
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(1,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
        
        /*
        String lowestName = "";
        
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            System.out.println("DIrectors: "+MovieDatabase.getDirector(rt.getItem()));      
        }
        */
    }
    
    public void printAverageRatingsByAll(){
        ThirdRatings rating = new ThirdRatings("data/ratings_short.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmovies_short.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        AllFilters all = new AllFilters();
        all.addFilter(new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(1,all);
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
        
        String lowestName = "";
        
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            System.out.println("Directors: "+MovieDatabase.getDirector(rt.getItem()));      
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
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
        
        /*
        String lowestName = "";
        
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Year: "+MovieDatabase.getYear(rt.getItem())+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            System.out.println("Genre: "+MovieDatabase.getGenres(rt.getItem()));      
        }
        */
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings rating = new ThirdRatings("data/ratings.csv");
        System.out.println(" Num of raters: "+rating.getRaterSize());
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println(" Num of movies: "+MovieDatabase.size());
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(90,180));
        all.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> r = rating.getAverageRatingsByFilter(3,all);
        
        
        Comparator c = new Comparator<Rating>(){
            public int compare(Rating o1, Rating o2){
                return o1.compareTo(o2);
            }
        };
        
        r.sort(c);
        
        System.out.println("Num of movies with ratings: "+r.size());
        
        /*
        String lowestName = "";
        
        for(Rating rt: r){
            System.out.println("Rating: "+rt.getValue()+" Time: "+MovieDatabase.getMinutes(rt.getItem())+" Title: "+MovieDatabase.getTitle(rt.getItem()));
            System.out.println("Directors: "+MovieDatabase.getDirector(rt.getItem()));      
        }
        */
    }
}
