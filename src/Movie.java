public class Movie {
    protected String title;
    protected double length;
    protected boolean doSuck;
    protected int rating;

    public Movie(String title, double length, boolean doSuck, int rating) {
        this.title = title;
        this.length = length;
        this.doSuck = doSuck;
        this.rating=rating;
    }

}