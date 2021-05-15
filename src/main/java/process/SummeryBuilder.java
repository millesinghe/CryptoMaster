package process;

import util.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Milinda
 */
public class SummeryBuilder {

    public SummeryBuilder(){
        this.calcSummery();
        this.genSummeryXML();
    }

    private boolean calcSummery(){
        File[] coinList = getAvailableCoinList();
        this.readCoinXMLs(coinList);
        return false;
    }

    private File[] getAvailableCoinList() {
        File dir = new File(Constants.XML_DEAL_RECORDS_DIR);
        return dir.listFiles();
    }

    private void readCoinXMLs(File[] coinList) {
        List<Map> coinsData = new ArrayList<Map>();
        Map<String,Object> coinStat = new HashMap<String, Object>();

        for (File file : coinList) {
            int totalUSDTEarn = 0;
            int totalAmount = 0;

            coinStat.put(Constants.EARN, totalUSDTEarn);
            coinStat.put(Constants.AMOUNT, totalAmount);
            
        }
    }

    private boolean genSummeryXML(){

        return false;
    }

}
