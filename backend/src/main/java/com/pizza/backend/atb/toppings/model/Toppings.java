package com.pizza.backend.atb.toppings.model;

import java.util.List;

public class Toppings {

    private List<String> sizes;
    private List<String> cheeses;
    private List<String> crusts;
    private List<String> meats;
    private List<String> sauces;
    private List<String> otherToppings;

    public Toppings() {
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<String> cheeses) {
        this.cheeses = cheeses;
    }

    public List<String> getCrusts() {
        return crusts;
    }

    public void setCrusts(List<String> crusts) {
        this.crusts = crusts;
    }

    public List<String> getMeats() {
        return meats;
    }

    public void setMeats(List<String> meats) {
        this.meats = meats;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public void setSauces(List<String> sauces) {
        this.sauces = sauces;
    }

    public List<String> getOtherToppings() {
        return otherToppings;
    }

    public void setOtherToppings(List<String> otherToppings) {
        this.otherToppings = otherToppings;
    }
}
