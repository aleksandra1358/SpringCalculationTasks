package com.globallogic.ultimateCalculationTool.result;

public class ResultImpl implements Result
{
    private Double result;

    @Override
    public Double getResult()
    {
        return result != null ? result : 0d;
    }

    public void setResult(final Double result)
    {
        this.result = result;
    }
}
