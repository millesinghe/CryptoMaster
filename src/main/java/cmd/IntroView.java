package cmd;

import helper.ChoiceRequestor;

/**
 * @author Milinda
 */
public class IntroView implements CmdView{

    private void splashScreen() {
        System.out.println("------- Crypto Manager -------");
    }

    public void display() {
        this.splashScreen();

        String[] options = {"View My Coins","Update Coin Portfolio","Calculate Profit Margins","SETTING","QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a option to proceed",options);
        CmdView view = null;

        switch (choice) {
            case 1 :
                view = new CoinView();
                view.display();
                break;
            case 2 :
                view = new MyMarketView();
                view.display();
                break;
            case 3 :
                System.out.println("Unimplemented choice");
                break;
            case 4 :
                System.out.println("Unimplemented choice");
                break;
            case 6 :
                System.exit(1);
                break;
        }
    }

}
