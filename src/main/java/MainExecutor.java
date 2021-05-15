import cmd.IntroView;
import helper.FileHandler;

/**
 * @author Yasara
 */
public class MainExecutor {

    public static void main(String[] args) {

        FileHandler.loadProperties();
        IntroView intro = new IntroView();
        intro.display();
    }
}
