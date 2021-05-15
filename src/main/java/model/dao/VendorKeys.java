package model.dao;

/**
 * @author Milinda
 */
public class VendorKeys {

    private String apiKeys;
    private String apiSecrets;

    public VendorKeys(String apiKeys, String apiSecrets) {
        this.apiKeys = apiKeys;
        this.apiSecrets = apiSecrets;
    }

    public String getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(String apiKeys) {
        this.apiKeys = apiKeys;
    }

    public String getApiSecrets() {
        return apiSecrets;
    }

    public void setApiSecrets(String apiSecrets) {
        this.apiSecrets = apiSecrets;
    }
}
