package cmd;

import model.dao.ProfitDAO;

import java.util.List;

/**
 * @author Milinda
 */
public class WalletView implements CmdView{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private List<ProfitDAO> profitDAO;
    private boolean isBuy;

    public WalletView(List<ProfitDAO> profitDAO, boolean isBuy) {
        this.profitDAO = profitDAO;
        this.isBuy = isBuy;
    }


    public void display() {
        this.headerformat();

        for(int i = 0; i < profitDAO.size(); i++){
            String str1 = String.format("|%10s|", profitDAO.get(i).getCoin());
            String str2 = String.format("%15s|", profitDAO.get(i).getTotal_assets());
            String str3 = String.format("%20s|", profitDAO.get(i).getUnit_cost());
            String str4 = String.format("%20s|", profitDAO.get(i).getMarket_price());
            String str5 = String.format("%20s|", profitDAO.get(i).getTotal_cost());
            String str6 = String.format("%20s|", profitDAO.get(i).getUnit_profit());
            String str7 = String.format("%20s|", profitDAO.get(i).getTotal_profit());
            String str9 = String.format("%20s|", profitDAO.get(i).getCurrent_value());
            String gain = profitDAO.get(i).getInvest_gain()+" %";
            gain = this.filterDigit(false,gain, 3);
            String str8 = String.format("%10s|", gain);

            if(Double.parseDouble(profitDAO.get(i).getUnit_profit()) >= 0){
                System.out.println(ANSI_GREEN+str1+str2 + str3 + str4 + str5 + str9+ str6 + str7+ str8);
            }else{
                System.out.println(ANSI_RED+str1+str2 + str3 + str4 + str5 + str9 + str6 + str7+ str8);
            }
        }
    }

    private String filterDigit(boolean isFowardDigits, String str, int digitCount) {
        String res = "";
        if (str.length()<=digitCount)
            return str;

        String digit = str.split("\\.")[1];
        if(isFowardDigits)
            digit = str.split(".")[0];

        for(int i = 0; i < digitCount; i++){
            res = res + digit.charAt(i);
        }

        if(isFowardDigits)
           res =  res + "."+ str.split("\\.")[1];
        else
            res = str.split("\\.")[0] + "." + res;

        return res;
    }

    private void headerformat() {
        System.out.println();
        String str1 = String.format("|%10s|", "Asset Name");
        String str2 = String.format("%15s|", "Total Amount");
        String str3 = String.format("%20s|", "AVG Unit Sell at");
        if(this.isBuy) {
            str3 = String.format("%20s|", "AVG Unit Buy at");
        }
        String str4 = String.format("%20s|", "Curr Price");
        String str5 = String.format("%20s|", "Total Spend");
        String str9 = String.format("%20s|", "Curr Total Value");
        String str6 = String.format("%20s|", "Unit Assert's Profit");
        String str7 = String.format("%20s|", "Total Profit");
        String str8 = String.format("%10s|", "Gain");
        System.out.println(ANSI_BLUE+str1+str2 + str3 + str4 + str5 + str9 + str6 + str7 + str8);
        str1 = String.format("|%10s|", "----------");
        str4 = String.format("%14s|", "---------------");
        str2 = String.format("%20s|", "--------------------");
        System.out.println(ANSI_BLUE+str1+str4 + str2 + str2 + str2 + str2 + str2 + str2 + str1);
    }

}
