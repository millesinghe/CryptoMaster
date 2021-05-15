package model.dao;

/**
 * @author Milinda
 */
public class Param {
        private String type;
        private Object value;

        public Param(String type, Object value){
            this.type = type;
            this.value = value;
        }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
