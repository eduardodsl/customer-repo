package br.com.dsleite.customerrepo.exceptions;

public class AddressNotFoundException  extends Exception {
    public AddressNotFoundException(String message){
        super(message);
    }
}