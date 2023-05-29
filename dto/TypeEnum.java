package com.shoppinglistapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum TypeEnum {

    TYPE_A("kilo", "K"),
    TYPE_B("grams", "G"),
    TYPE_C("pieces", "P"),
    TYPE_D("", "P"),
    TYPE_E("liters", "L");




    private final String apiValue;
    private final String  dbValue;

    public static TypeEnum fromDBValue(String dbValue){
        return Arrays.stream(values())
                .filter(en -> Objects.equals(en.dbValue, dbValue))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static TypeEnum fromApiValue(String apiValue){
        return Arrays.stream(values())
                .filter(en -> Objects.equals(en.apiValue, apiValue))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getApiValue(){
        return apiValue;
    }


}
