package common;

import java.util.regex.Pattern;

public class DataValidity {

    /**
     * 判断字符串是否为数值类型：是数值类型则返回true，不是则返回false。
     * @param value
     * @return
     */
    public static Validity IsNumeric(String value)
    {
        String regx= "^[+-]?\\d*[.]?\\d*$";
        Validity result = new Validity();
        if (value.isEmpty() || "+".equals(value) || "+.".equals(value) ||  "-".equals(value) || "-.".equals(value))
        {
            result.isValid = false;
            return result;
        }
        Pattern pattern = Pattern.compile(regx);
        result.isValid = pattern.matcher(value).find();
        if (result.isValid)
        {
            result.result = Double.parseDouble(value);
        }
        else
        {
            try {
                result.result = Double.parseDouble(value);
                result.isValid=true;
            }catch (Exception ex) {
                result.isValid=false;
            }
        }
        return result;
    }

    /**
     * 判断字符串是否为int类型：是则返回true，不是则返回false。
     * @param value
     * @return
     */
    public static Validity isInt(String value)
    {
        String regx= "^[+-]?\\d*$";
        Validity result = new Validity();
        if (value.isEmpty() || "+".equals(value) || "+.".equals(value) ||  "-".equals(value) || "-.".equals(value))
        {
            result.isValid = false;
            return result;
        }
        Pattern pattern = Pattern.compile(regx);
        result.isValid = pattern.matcher(value).find();
        if (result.isValid)
        {
            result.result = Integer.parseInt(value);
        }
        else
        {
            try {
                result.result = Integer.parseInt(value);
                result.isValid=true;
            }catch (Exception ex) {
                result.isValid=false;
            }
        }
        return result;
    }

    /**
     * 判断字符串是否为非负无符号类型：是则返回true，不是则返回false。包括整数与小数
     * @param value
     * @return
     */
    public static Validity IsUnsign(String value)
    {
        String regx= "^\\d*[.]?\\d*$";
        Validity result = new Validity();
        if (value.isEmpty())
        {
            result.isValid = false;
            return result;
        }
        Pattern pattern = Pattern.compile(regx);
        result.isValid = pattern.matcher(value).find();
        if (result.isValid)
        {
            result.result = value;
        }
        else
        {
            if (IsNumeric(value).isValid && value.contains("+") == false && value.contains("-") == false)
            {
                result.isValid = true;
                result.result = value;
            }
        }
        return result;
    }

    /**
     * 是否是分数类型，如果是则为true，否则false
     * @param value
     * @return
     */
    public static Validity IsFraction(String value)
    {
        Validity result = new Validity();
        //正常数值格式
        if (IsNumeric(value).isValid)
        {
            result.isValid = true;
            result.result = Double.parseDouble(value);
            return result;
        }
        else
        {
            //含有分号“/”的的分数格式
            if (value.contains("/") == true)
            {
                Double fenzi = 0.0;
                Double fenmu = 0.0;
                //分子
                String strfz = value.substring(0, value.indexOf('/'));
                //分母
                String strfm = value.substring(value.indexOf('/') + 1, value.length() - value.indexOf('/') - 1);
                if (IsNumeric(strfz).isValid && IsNumeric(strfm).isValid)
                {
                    fenzi = Double.parseDouble(value.substring(0, value.indexOf('/')));
                    fenmu = Double.parseDouble(strfm);
                    if (Math.abs(fenmu) > ConstValue.minimumValue)
                    {
                        result.isValid = true;
                        result.result = fenzi / fenmu;
                    }
                    else
                    {
                        //分母为零
                        result.isValid = false;
                        result.result = null;
                    }
                }
                else
                {
                    //分子或者分母非数字
                    result.isValid = false;
                    result.result = null;
                }
            }
            else
            {
                //字符格式有误
                result.isValid = false;
                result.result = null;
            }
        }
        return result;
    }
}
