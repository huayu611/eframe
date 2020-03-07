package com.huayu.eframe.server.tool.util;

import com.huayu.eframe.server.tool.basic.StringUtils;

import java.math.BigDecimal;

/**
 * 计算类， 用于double,float等计算，防止精度丢失
 * Created by Leo on 2019/9/23.
 */
public class CalculateNumber
{
    private BigDecimal bigDecimal;

    public CalculateNumber()
    {
        bigDecimal = new BigDecimal("0");
    }


    public static CalculateNumber getInstance()
    {
        return new CalculateNumber();
    }

    public static CalculateNumber getInstance(String value)
    {
        return new CalculateNumber(value);
    }

    public CalculateNumber(String value)
    {
        if (StringUtils.isNullOrEmpty(value))
        {
            value = "0";
        }
        bigDecimal = new BigDecimal(value);
    }

    public CalculateNumber(BigDecimal bigDecimalParameter)
    {
        if (null != bigDecimalParameter)
        {
            bigDecimal = bigDecimalParameter;
        }
        else
        {
            bigDecimal = new BigDecimal("0");
        }

    }

    public CalculateNumber(Double value)
    {
        if (null == value)
        {
            bigDecimal = new BigDecimal("0");
        }
        else
        {
            bigDecimal = new BigDecimal(value);
        }
    }

    public CalculateNumber(Float value)
    {
        if (null == value)
        {
            bigDecimal = new BigDecimal("0");
        }
        else
        {
            bigDecimal = new BigDecimal(value);
        }
    }

    public CalculateNumber(Integer value)
    {
        if (null == value)
        {
            bigDecimal = new BigDecimal("0");
        }
        else
        {
            bigDecimal = new BigDecimal(value);
        }
    }

    public CalculateNumber(Long value)
    {
        if (null == value)
        {
            bigDecimal = new BigDecimal("0");
        }
        else
        {
            bigDecimal = new BigDecimal(value);
        }
    }

    public CalculateNumber(Short value)
    {
        if (null == value)
        {
            bigDecimal = new BigDecimal("0");
        }
        else
        {
            bigDecimal = new BigDecimal(value);
        }
    }

    public CalculateNumber add(Object value)
    {
        String vet = StringUtils.getString(value);
        if (StringUtils.isNullOrEmpty(vet))
        {
            vet = "0";
        }
        bigDecimal = bigDecimal.add(new BigDecimal(vet));
        return this;
    }

    public CalculateNumber add(CalculateNumber value)
    {
        if (null != value)
        {
            bigDecimal = bigDecimal.add(value.getBigDecimal());
        }
        return this;
    }

    public CalculateNumber subtract(Object value)
    {
        String vet = StringUtils.getString(value);
        if (StringUtils.isNullOrEmpty(vet))
        {
            vet = "0";
        }
        bigDecimal = bigDecimal.subtract(new BigDecimal(vet));
        return this;
    }

    public CalculateNumber subtract(CalculateNumber value)
    {
        if (null != value)
        {
            bigDecimal = bigDecimal.subtract(value.getBigDecimal());
        }
        return this;
    }

    public CalculateNumber multiply(Object value)
    {
        String vet = StringUtils.getString(value);
        if (StringUtils.isNullOrEmpty(vet))
        {
            vet = "0";
        }
        bigDecimal = bigDecimal.multiply(new BigDecimal(vet));
        return this;
    }

    public CalculateNumber multiply(CalculateNumber value)
    {
        if (null != value)
        {
            bigDecimal = bigDecimal.multiply(value.getBigDecimal());
        }
        return this;
    }

    public CalculateNumber divide(Object value)
    {
        return divide(value, 2, BigDecimal.ROUND_HALF_UP);

    }

    public CalculateNumber divide(Object value, int dot, int halfUp)
    {
        String vet = StringUtils.getString(value);
        if (StringUtils.isNullOrEmpty(vet))
        {
            vet = "1";
        }
        bigDecimal = bigDecimal.divide(new BigDecimal(vet), dot, halfUp);
        return this;
    }

    public CalculateNumber divide(CalculateNumber value, int dot, int halfUp)
    {
        if (null != value)
        {
            bigDecimal = bigDecimal.divide(value.getBigDecimal(), dot, halfUp);
        }
        return this;
    }

    public CalculateNumber divide(CalculateNumber value)
    {
        return divide(value, 2, BigDecimal.ROUND_HALF_UP);
    }

    public int getInteger()
    {
        return bigDecimal.intValue();
    }

    public double getDouble()
    {
        return bigDecimal.doubleValue();
    }

    public float getFloat()
    {
        return bigDecimal.floatValue();
    }

    public long getLong()
    {
        return bigDecimal.longValue();
    }

    public short getShort()
    {
        return bigDecimal.shortValue();
    }

    public BigDecimal getBigDecimal()
    {
        return bigDecimal;
    }

    @Override
    public String toString()
    {
        return  bigDecimal.toString();
    }
}
