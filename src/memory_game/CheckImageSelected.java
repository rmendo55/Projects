package memory_game;

import javafx.scene.image.ImageView;

public class CheckImageSelected {
    private SaveImage saveImageView;
    private ImageView imageView;

    public CheckImageSelected (SaveImage saveImageView, ImageView imageView) {
        this.saveImageView = saveImageView;
        this.imageView = imageView;
    }


    //getters
    public boolean getIsMatched() {
        if (this.saveImageView.getImageView().getImage().getUrl().equals(this.imageView.getImage().getUrl())) {
            return true;
        }
        else {
            return false;
        }

    }
    public SaveImage getSaveImageView() {
        return this.saveImageView;

    }
    public ImageView getImageView() {
        return this.imageView;

    }




}
