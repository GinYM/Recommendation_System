import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (Yiming) 
 * @version (01/31/2018)
 */
public class RecommendationRunner implements Recommender{
    private int num = 15;
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> all;
        all = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> ret = new ArrayList<String>();
        int idx = 0;
        Random random;
        int size = all.size();
        for(int i = 0;i<num;i++){
            random = new Random(i);
            ret.add(all.get(random.nextInt(size)));
        }
        return ret;
        
    }
    
    public void test(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings rating = new FourthRatings();
        ArrayList<Rater> name = RaterDatabase.getRaters();
        ArrayList<Rating> ret = rating.getSimilarRatings(name.get(0).getID(),10,5);
        if(ret.size()==0){
            System.out.println("Error! No enough rater!");
        }
        else{
            System.out.println("Movie: "+MovieDatabase.getTitle(ret.get(0).getItem()));
        }
        
    }
    
    
    public void printRecommendationsFor (String webRaterID){
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        //RaterDatabase.initialize("ratings.csv");
        FourthRatings rating = new FourthRatings();
        //System.out.println("Rater size: "+RaterDatabase.size());
        //System.out.println(" WebRaterID: "+webRaterID);
        ArrayList<Rating> ret = rating.getSimilarRatings(webRaterID,10,2);
        if(ret.size()==0){
            System.out.print("Error! No enough rater!");
        }
        else{
            String html = "";
            html+="<table><tr><th>Movie Title</th><th>Rating</th></tr>";
            //System.out.println("<table><tr><th>Movie Title</th><th>Rating</th></tr><tr>");
            for(int i = 0;i<Math.min(15,ret.size());i++){
                html+=("<tr><td>"+MovieDatabase.getTitle(ret.get(i).getItem())+"</td><td>"+ret.get(i).getValue()+"</td></tr>");
            }
            html+=("</table>");
            System.out.println(html);
        }
    }
}
