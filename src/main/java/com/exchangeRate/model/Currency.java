package com.exchangeRate.model;

import lombok.Getter;
import java.util.HashMap;

@Getter
public class Currency {
    HashMap<String, Double> rates;
}