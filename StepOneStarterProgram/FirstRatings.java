import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (Yiming) 
 * @version (01/29/2018)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> result = new ArrayList<Movie>();
        try{
            Reader in = new FileReader(filename);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
            for(CSVRecord record : records){
                result.add(new Movie(record.get("id"),record.get("title"),record.get("year"),
                        record.get("genre"),record.get("director"),record.get("country"),
                        record.get("poster"),Integer.parseInt(record.get("minutes"))));

            }
            return result;
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return result;
    }

    public void testLoadMovies(){
        ArrayList<Movie> data = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> data = loadMovies("data/ratedmovies_short.csv");
        System.out.println("Number of data: "+data.size());
        int count_comedy = 0;
        int count_gt = 0;
        HashMap<String,Integer> director = new HashMap<String,Integer>();
        ArrayList<String> director_max = new ArrayList<String>();
        int director_max_count = 0;
        for(Movie mv: data){
            //System.out.println(mv.getDirector());
            String[] directors = mv.getDirector().split(",");
            for(String d : directors){
                if(!director.keySet().contains(d)){
                    director.put(d,1);
                }
                else{
                    director.put(d,director.get(d)+1);
                }
                if(director.get(d)>director_max_count){
                    director_max_count = director.get(d);
                    director_max = new ArrayList<String>();
                    director_max.add(d);
                }
                else if(director.get(d) == director_max_count){
                    director_max.add(d);
                }
            }
            if(mv.getGenres().contains("Comedy")){
                count_comedy++;
            }
            if(mv.getMinutes()>150){
                count_gt++;
            }
        }
        System.out.println("Number of comedy: "+count_comedy);
        System.out.println("Number of moives gt 150: "+count_gt);
        System.out.println("Maximum number of movies: "+director_max_count);
        System.out.println("Directors: "+director_max);
    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> result = new ArrayList<Rater>();
        HashMap<String,Rater> getRater = new HashMap<String,Rater>();
        String id;
        try{
            Reader in = new FileReader(filename);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
            for(CSVRecord record: records){
                id = record.get("rater_id");
                if(!getRater.keySet().contains(id)){
                    getRater.put(id,new Rater(id));
                }
                Rater rt = getRater.get(id);
                if(!rt.hasRating(record.get("movie_id"))){
                    rt.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        for(String rater_id: getRater.keySet()){
            result.add(getRater.get(rater_id));
        }
        return result;
    }

    public void testLoadRaters(){
        //ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Number of raters: "+raters.size());
        ArrayList<String> item;
        /*
        for(Rater r : raters){
        System.out.println("id: "+r.getID()+" number of rating: "+r.numRatings());
        item = r.getItemsRated();
        for(String item_id : item){
        System.out.println("movie_id: "+item_id+" rating: "+r.getRating(item_id));
        }
        }
         */
        String rater_id = "193";
        ArrayList<Rater> rater_max = new ArrayList<Rater>();
        int rater_max_num = 0;
        for(Rater r: raters){
            if(r.getID().equals(rater_id)){
                System.out.println("Number of ratings: "+r.numRatings());
            }
            if(r.numRatings()>rater_max_num){
                rater_max_num = r.numRatings();
                rater_max = new ArrayList<Rater>();
                rater_max.add(r);
            }
            else if(r.numRatings() == rater_max_num){
                rater_max.add(r);
            }
        }
        System.out.println("maximum num: "+rater_max_num);
        System.out.println("Rater size: "+rater_max.size());
        System.out.println("Rater id: ");
        for(Rater rt:rater_max){
            System.out.print(rt.getID()+" ");
        }
        System.out.println();
        
        String item_id = "1798709";
        int num_of_rate = 0;
        for(Rater r: raters){
            if(r.hasRating(item_id)){
                num_of_rate++;
            }
        }
        System.out.println("Number of rating: "+num_of_rate);
        
        HashMap<String,Boolean> hasMovie = new HashMap<String,Boolean>();
        
        for(Rater r: raters){
            item = r.getItemsRated();
            for(String it:item){
                if(!hasMovie.keySet().contains(it)){
                    hasMovie.put(it,true);
                }
            }
        }
        System.out.println("Number of movies: "+hasMovie.size());
    }
}
