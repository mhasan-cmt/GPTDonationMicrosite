package com.example.fuckallgpt.exceptions;

public class UnExpectedDonationType extends IllegalStateException{
    public UnExpectedDonationType(){
        super("Unexpected Donation Type!");
    }
}
