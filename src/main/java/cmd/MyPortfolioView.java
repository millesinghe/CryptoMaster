package cmd;

import model.dao.app.CoinDAO;
import model.dao.app.CoinsDAO;
import model.dao.app.TransactionDAO;
import operation.ChoiceRequestor;
import process.BOHelper;
import process.BOManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milinda
 */
public class MyPortfolioView implements CmdView{

    public void display() {
        String[] options = {"Bought new coins","Sold existing coins","SETTING", "BACK", "QUIT"};
        int choice = ChoiceRequestor.requestOption(">> Select a choice on Update Coin Portfolio",options);
        boolean status = false;
        switch (choice) {
            case 1 :
                status= this.option1();
                break;
            case 2 :
                status= this.option2();
                break;
            case 3 :
                this.option3();
                break;
            case 4 :
                System.out.println("Unimplemented choice");
                break;
            case 5 :
                status= true;
                System.exit(1);
                break;
        }

        if (status) {
            System.out.println("Operation Success ");
        } else {
            System.err.println("Operation Failed");
        }


    }

    private boolean option1(){
        System.out.println(">> Add the bought coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t\t\t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("No coin detail matched with feed data");
            return false;
        }

        System.out.println("Coin Name \t\t\t - "+ name);
        double amount = ChoiceRequestor.requestDouble("Bought Amount * \t - ",false);
        double price = ChoiceRequestor.requestDouble("Unit Price ("+id+")* \t - ",false);
        String date = ChoiceRequestor.requestAnswer("Bought Date * \t\t - ",false);
        double fee = ChoiceRequestor.requestDouble("Transaction Fee \t - ",false);
        double equalUSDT = ChoiceRequestor.requestDouble("Unit price ( USDT ) \t - ",false);

        new BOManager().addNewCoin(id,name,date,amount,price,fee,equalUSDT);
        return true;
    }

    private boolean option2(){
        System.out.println(">> Add the Sold coin details");
        String id = ChoiceRequestor.requestAnswer("Coin Id \t\t\t - ",false);
        String name = new BOHelper().getCoinName(id);

        if (name == null) {
            System.err.println("No coin detail matched with feed data");
            return false;
        }

        System.out.println("Coin Name \t\t\t - "+ name);
        double amount = ChoiceRequestor.requestDouble("Sold Amount \t - ",false);
        double price = ChoiceRequestor.requestDouble("Unit Price ("+id+")\t - ",false);
        String date = ChoiceRequestor.requestAnswer("Sold Date \t\t - ",false);
        double fee = ChoiceRequestor.requestDouble("Transaction Fee \t - ",false);
        double equalUSDT = ChoiceRequestor.requestDouble("Unit price ( USDT ) \t - ",false);

        new BOManager().removeCoin(id,name,date,amount,price,fee,equalUSDT);
        return true;
    }


    private void option3(){

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

}
