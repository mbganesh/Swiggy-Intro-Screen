package mb.ganesh.swiggyintro;

public class Model {
    String title;
    int image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Model(String title, int image) {
        this.title = title;
        this.image = image;
    }
}
