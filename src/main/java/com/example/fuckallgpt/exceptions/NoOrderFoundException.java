package com.example.fuckallgpt.exceptions;

public class NoOrderFoundException extends IllegalStateException{
    public NoOrderFoundException(){
        super("No Orders found with this id!");
    }
}
