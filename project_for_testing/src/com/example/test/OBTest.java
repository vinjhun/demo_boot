package com.example.test;

import java.io.Serializable;

public class OBTest implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String id;
    private byte[] document;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public byte[] getDocument()
    {
        return document;
    }

    public void setDocument(byte[] document)
    {
        this.document = document;
    }

}
