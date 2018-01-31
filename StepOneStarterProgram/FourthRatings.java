import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (Yiming) 
 * @version (01/30/2018)
 */
public class FourthRatings {

    public FourthRatings() {

    }

    public double getAverageByID(String id, int minimalRaters, ArrayList<Rater> myRaters){
        double avg = 0.0;
        double rating = 0.0;
        int count = 0;
        //ArrayList<Rater> myRaters =  RaterDatabase.getRaters();
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

    

    public double getAverageByID(String id, int minimalRaters){
        double avg = 0.0;
        double rating = 0.0;
        int count = 0;
        ArrayList<Rater> myRaters =  RaterDatabase.getRaters();
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

    public double dotProduct(Rater me, Rater r){
        ArrayList<String> meArr = me.getItemsRated();
        ArrayList<String> rArr = r.getItemsRated();
        Collections.sort(meArr);
        Collections.sort(rArr);
        //System.out.println(meArr);
        //System.out.println(rArr);
        int idx=0;
        double result = 0;
        int count = 0;
        for(String id : meArr){
            while(idx <rArr.size()&& rArr.get(idx).compareTo(id)<0){
                idx++;
            }
            if(idx >=rArr.size()){
                break;
            }
            if(rArr.get(idx).equals(id)){
                //count++;
                //System.out.println(rArr.get(idx));
                result += (me.getRating(id)-5)*(r.getRating(rArr.get(idx))-5);
            }

        }
        return result;
        /*
        if(count>0)
            return result/count;
        else
            return 0;
        */
        
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rater> rt =  RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        //System.out.println("Rater size: "+rt.size());
        double sim = 0.0;
        for(Rater r: rt){
            if(!r.getID().equals(id)){
                sim = dotProduct(me,r);
                
                if(sim >0){
                    //System.out.println("sim is "+sim);
                    ret.add(new Rating(r.getID(),sim));
                }
            }
        }
        Collections.sort(ret,Collections.reverseOrder());

        return ret;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> sim = getSimilarities(id);
        //System.out.println("sim is !!!"+sim.size());
        
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //System.out.println("Movies size: "+movies.size());
        //ArrayList<Rater> leftRater = new ArrayList<Rater>();
        if(sim.size() == 0){
            return ret;
        }
        int countRater = 0;
        Rater curRater;
        double rate = 0.0;
        double allRating = 0.0;
        for(String mv: movies){
            
            countRater = 0;
            allRating = 0.0;
            for(int i = 0;i<numSimilarRaters && i<sim.size();i++){
                curRater = RaterDatabase.getRater(sim.get(i).getItem());
                rate = curRater.getRating(mv);
                if(rate!=-1){
                    allRating += sim.get(i).getValue()*rate;
                    countRater++;
                }
            }
            //System.out.println("allRating: "+allRating);
            //System.out.println("CountRater: "+countRater);
            if(countRater >= minimalRaters){
                ret.add(new Rating(mv,allRating/countRater));
            }
        }

        Collections.sort(ret,Collections.reverseOrder());

        return ret;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> sim = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        //ArrayList<Rater> leftRater = new ArrayList<Rater>();
        int countRater = 0;
        Rater curRater;
        double rate = 0.0;
        double allRating = 0.0;
        for(String mv: movies){
            countRater = 0;
            allRating = 0.0;
            for(int i = 0;i<numSimilarRaters;i++){
                curRater = RaterDatabase.getRater(sim.get(i).getItem());
                rate = curRater.getRating(mv);
                if(rate!=-1){
                    allRating += sim.get(i).getValue()*rate;
                    countRater++;
                }
                
            }
            if(countRater >= minimalRaters){
                ret.add(new Rating(mv,allRating/countRater));
            }
        }

        Collections.sort(ret,Collections.reverseOrder());

        return ret;
    }
    
    

}
