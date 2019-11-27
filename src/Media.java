import java.util.*;

public class Media{
    protected String title;
    protected int year;
    protected double rating;
    protected List<String> category;

    public Media(String title, int year, double rating){
        this.title = title;
        this.year = year;
        this.rating = rating;
        category = new ArrayList<>();
    }

    //get methods
    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }

    public double getRating(){
        return rating;
    }

    public void addCategory(String genre){ //tilføjer en kategori
        category.add(genre);
    }
    public String getCategory(int index){ //returnere kategorien til et givet index
        return category.get(index);
    }
    public int numberOfCategories(){ //returnerer en int, som tæller hvor mange kategorier der er til et medie.
        return category.size();
    }

}