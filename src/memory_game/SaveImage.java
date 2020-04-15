package memory_game;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SaveImage {
    private static ImageView image;
    private static FadeTransition imageView;

    public SaveImage(ImageView image) {
        this.image = image;

    }



    public SaveImage(Duration seconds, ImageView imageView2) {
        // TODO Auto-generated constructor stub
        imageView = new FadeTransition(seconds, imageView2);
    }

    public ImageView getImageView() {
        return this.image;
    }
    public FadeTransition getImageView2() {
        return this.imageView;
    }

    public Image getImage() {
        return this.image.getImage();
    }









}
