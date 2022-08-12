import common.PhyParas;
import common.XmlAnalysis;

import java.util.List;

/**
 * @author Administrator
 */
public class Function {

    public static PhyParas T11(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "560";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T12
    public static PhyParas T12(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "9440.0";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    //T13
    public static PhyParas T13(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double U = Double.parseDouble(para._StudentValue.toString());
            if (U >= -5 && U <= 5)
            {
                String U1 =String.format("%.2f",U);
                para._StandardValue = U1;
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T21
    public static PhyParas T21(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "560";

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T22
    public static PhyParas T22(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "29.5";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       //T23
    public static PhyParas T23(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double I = Double.parseDouble(para._StudentValue.toString());
            if (I >= -10 && I <= 10)
            {
                String I1 =  String.format("%.2f",I);
                para._StandardValue = I1;
            }
            else
            {
                para._StandardValue = "";
            }

        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
      // T31

    public static PhyParas T31(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "560";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T32
    public static PhyParas T32(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "2440.0";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T33
    public static PhyParas T33(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "43.5";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T34
    public static PhyParas T34(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double a = Double.parseDouble(para._StudentValue.toString());
            String a1 = String.valueOf(a);
            if (a1 != null)
            {

                int n1 = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
                if (n1 == 1)
                {

                    para._StandardValue = para._StudentValue;
                }
                else
                {
                    String a2 =String.format("%.1f",a);
                    para._StandardValue = a2;
                }
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T41
    public static PhyParas T41(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "560";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T42
    public static PhyParas T42(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "29.5";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       //T43
    public static PhyParas T43(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "472.0";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
      // T44
    public static PhyParas T44(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "122.0";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T45
    public static PhyParas T45(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            para._StandardValue = "60.0";
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T46
    public static PhyParas T46(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double I = Double.parseDouble(para._StudentValue.toString());
            if (I >= -10 && I <= 10)
            {
                String I1 =String.format("%.2f",I);
                para._StandardValue = I1;
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
       // T47
    public static PhyParas T47(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double U = Double.parseDouble(para._StudentValue.toString());

            if (U >= -5 && U <= 5)
            {
                String U1 = String.format("%.2f",U);
                para._StandardValue = U1;
            }
            else
            {
                para._StandardValue = "";
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
    // T48
    public static PhyParas T48(PhyParas para, List<PhyParas> DataSourse)
    {
        try
        {
            double a = Double.parseDouble(para._StudentValue.toString());
            String a1 = String.valueOf(a);
            if (a1 != null)
            {

                int n1 = DataConversion.GetDecimalPointDigits(para._StudentValue.toString());
                if (n1 == 1)
                {

                    para._StandardValue = para._StudentValue;
                }

                else
                {
                    String a2 = String.format("%.1f",a);
                    para._StandardValue = a2;
                }
            }
        }
        catch (Exception exception)
        {
            para._StandardValue = "";
        }
        return para;
    }
}
