package sample;

public class Movie implements Watchable {
    protected int length;
    protected String title;

    public Movie(int length, String title) {
        this.length = length;
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}