import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

public class AppManager implements KeyListener, ButtonClickListener{
    int index = 0;
    final Frame frame;
    FilesManager manager = new FilesManager("/Users/sebastian/Desktop/dataset/");
    String[] keys = new String[]{"przód","tył","lewo","prawo","others","skip"};
    List<File> files;

    AppManager(){
       this.files = manager.getFiles();
        frame = new Frame();
        frame.showImage(manager.getFiles().get(index).getPath());
        frame.addKeyListener(this);
        frame.setListener(this);
        frame.generateButtons(keys);
        manager.createDirectories(keys);
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_A){

            this.loadNewImage();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void onButtonClick(String name) {
        System.out.println(name);
        if(name != "skip") {
            manager.moveFileToNewDirectory(files.get(index), name);
        }
        loadNewImage();

    }

    private void loadNewImage(){
        if(index+1 < this.files.size()) {
            index += 1;
            frame.showImage(manager.getFiles().get(index).getPath());
        }
        else {
            frame.close();
        }
    }
}
