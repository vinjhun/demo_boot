package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SDJSCHEAH
 *
 */
@Entity
@Table(name = "A_TEST2")
public class Test
{
    private String id;
    private byte[] code;

    @Id
    @Column(name = "ID", length = 1000)
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(name = "CODE", length = 1000)
    public byte[] getCode()
    {
        return code;
    }

    public void setCode(byte[] code)
    {
        this.code = code;
    }
    
}
