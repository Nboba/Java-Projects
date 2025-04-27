package model;

import lombok.Data;

@Data
public class Theme {
    private String id;
    private String name;
    private String description;

    public String toStringTheme(){
        var coma=",";
        return id+coma+name+coma+description;
    }
}
