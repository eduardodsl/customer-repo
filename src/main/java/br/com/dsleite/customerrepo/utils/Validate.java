package br.com.dsleite.customerrepo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Validates if the given fields are valid
 */
public class Validate<T> {
    
    Map<String, T> fields = new HashMap<>();
    List<String> invalid = new ArrayList<>();

    public Validate<T> addField(String name, T value){
        this.fields.put(name, value);
        return this;
    }

    public Validate<T> validate(){
        fields.forEach((String field, T value) -> {
            if(this.isEmpty(value)){
                this.invalid.add(field);
            }        
        });
        return this;
    }

    public boolean isValid(){
        return this.invalid.size() == 0;
    }

    public List<String> getInvalidFields(){
        return this.invalid;
    }

    boolean isEmpty(T value){
        return value == "";
    }

}
