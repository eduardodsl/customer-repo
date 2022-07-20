package br.com.dsleite.customerrepo.exceptions;

public class DataNotFoundException  extends Exception {
    public DataNotFoundException(String message){
        super(message);
    }
}