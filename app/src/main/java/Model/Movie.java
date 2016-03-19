package Model;

/**
 * Created by Mohammed Abdelsattar on 3/17/2016.
 */
public class Movie {

    public int ID;
    public String original_title;
    public String title;
    public  boolean adult;
    public  String poster_path;
    public  String backdrop_path;
    public  double  popularity;
    public  int vote_count;
    public  double vote_average;
    public  String overview;
    public  boolean video;


    public  Movie(
           int ID,
             String original_title,
             String title,
              boolean adult,
              String poster_path,
              String backdrop_path,
              double  popularity,
              int vote_count,
              double vote_average,
              String overview,
              boolean video
    )
    {
        this.ID=ID;
         this.original_title=original_title;
         this.title=title;
         this.adult=adult;
         this.poster_path=poster_path;
         this.backdrop_path=backdrop_path;
            this.popularity=popularity;
         this.vote_count=vote_count;
           this.vote_average=vote_average;
           this.overview=overview;
           this.video=video;
    }

}
