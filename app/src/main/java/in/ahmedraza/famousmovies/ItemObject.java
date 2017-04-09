package in.ahmedraza.famousmovies;

/**
 * Created by ahmedraza on 06/04/17.
 */

public class ItemObject{

    private String name;
    private int photo;

    public ItemObject(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}