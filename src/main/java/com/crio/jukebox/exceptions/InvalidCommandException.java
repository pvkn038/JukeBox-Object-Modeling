package com.crio.jukebox.exceptions;

public class InvalidCommandException extends RuntimeException{
    
    public InvalidCommandException()
    {
        super();
    }
    public InvalidCommandException(String msg)
    {
        super(msg);
    }
}
