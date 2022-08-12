package common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class PhyParas {

    /**
     * 参数名称
     */
    public String _Name;

    /**
     * 参数值类型
     */
    public String _Type;

    /**
     * 参数的输入值
     */
    public Object _StudentValue;
    /**
     * 参数的输出值
     */
    public Object _StandardValue;

    /**
     * 数据源List
     */
    public List<String> _DataSourse;

    /**
     * 利用输入值计算输出值时所用到的公式
     */
    public String _Formula;

    /**
     * 预留扩展接口
     */
    public Object NewPara(Object e) { return new Object(); }

    public PhyParas()
    {
        _Name = "";
        _Type = "";
        _StudentValue = new Object();
        _StandardValue = new Object();
        _DataSourse = new ArrayList<String>();
        _Formula = "";
    }

    public void Dispose()
    {
        _Name = null;
        _Type = null;
        _StudentValue = null;
        _StandardValue = null;
        _DataSourse = null;
        _Formula = null;
    }

    /**
     * 对当前参数进行深度复制
     * @return 返回原对象相同的新对象
     */
    public PhyParas Clone()
    {
        PhyParas phy = new PhyParas();
        phy._Name = this._Name;
        phy._Type = this._Type;
        phy._StudentValue = this._StudentValue;
        phy._StandardValue = this._StandardValue;
        phy._DataSourse = this._DataSourse;
        phy._Formula = this._Formula;

        return phy;
    }
}

