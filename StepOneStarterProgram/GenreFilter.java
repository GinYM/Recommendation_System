
/**
 * Write a description of GenreFilter here.
 * 
 * @author (Yiming) 
 * @version (01/30/2018)
 */
public class GenreFilter implements Filter{
    String genre;
    
    public GenreFilter(String genre){
        this.genre = genre;
    }
    
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(genre);
    }
    
    
}
