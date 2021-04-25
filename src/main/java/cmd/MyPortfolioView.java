package cmd;

import model.dao.app.CoinDAO;
import model.dao.app.CoinsDAO;
import model.dao.app.TransactionDAO;
import operation.ChoiceRequestor;
import operation.FileHandler;
import process.BOHelper;
import process.BOManager;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class MyPortfolioView implements CmdView{

    public void display() {
        String[] options = {"Bought new coins","Sold existing coins","SETTING", "BACK", "QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a choice on Update Coin Portfolio",options);

        switch (choice) {
            case 1 :
                this.option1();
                break;
            case 2 :
                this.option2();
                break;
            case 3 :
                this.option3();
                break;
            case 4 :
                System.out.println("Unimplemented choice");
                break;
            case 5 :
                System.exit(1);
                break;
        }
    }

    private void option1(){

        System.out.println(">> Add the bought coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("Invalid detail");
        }

        System.out.println("Coin Name \t - "+ name);
        double amount = ChoiceRequestor.requestDouble("Bought Amount \t - ",false);
        double price = ChoiceRequestor.requestDouble("Bought Price \t - ",false);
        String date = ChoiceRequestor.requestAnswer("Bought Date \t - ",false);

        new BOManager().addNewCoin(id,name,date,amount,price);

    }

    private CoinsDAO sampleCoin() {
        TransactionDAO tx1 = new TransactionDAO("21/01/21",10.00,100.00);
        TransactionDAO tx2 = new TransactionDAO("21/01/22",20.00,200.00,0.01,1);
        List txs = new ArrayList<TransactionDAO>();
        txs.add(tx1);

        CoinDAO c = new CoinDAO("BTC", "Bitcoin");
        c.setTx(txs);

        txs.add(tx2);
        CoinDAO c1 = new CoinDAO("ETH", "Etherium");
        c1.setTx(txs);

        List clist = new ArrayList<TransactionDAO>();
        CoinsDAO coinsdao = new CoinsDAO();
        clist.add(c);
        clist.add(c1);
        coinsdao.setCoin(clist);
        return coinsdao;
    }


    private void option2(){
        CoinsDAO c = this.sampleCoin();
        FileHandler.writeXMLFile(Constants.XML_SELL_PORTFOLIO,c);
    }


    private void option3(){

    }
}
