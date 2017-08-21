package com.globallogic.ultimateCalculationTool.task;


import javax.persistence.*;

@Entity
public class ConcreteTask
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public ConcreteTask()
    {
    }

    public ConcreteTask(String s)
    {
        this.name = s;
    }

    public String getName()
    {
        return name;
    }

    public Long getId()
    {
        return id;
    }
}
