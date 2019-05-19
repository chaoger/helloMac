package com.chaoger;

public enum PermMapEnum {

    PermMapEnum("SkuSale",new String[]{"aa","bb"},"sale","销量");
    private String modelName;
    private String[] columns;
    private String permValue;
    private String desc;

    PermMapEnum(String modelName, String[] columns, String permValue, String desc) {
        this.modelName = modelName;
        this.columns = columns;
        this.permValue = permValue;
        this.desc = desc;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getPermValue() {
        return permValue;
    }

    public void setPermValue(String permValue) {
        this.permValue = permValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static PermMapEnum genPermMapEnumByPermVaules(String permValue){
        for(PermMapEnum permMapEnum : values()){
            if (permMapEnum.getPermValue().equals(permValue)) {
               return permMapEnum;
            }
        }
        return null;
    }
}
