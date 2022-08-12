import common.ParaModel;
import common.PhyParas;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Answer {

    /**
     * 存放原始参数，该列表值直接参数计算
     */
    List<PhyParas> ParaList;

    /**
     * 存放缓存的各个参数计算结果的List,相当于原始数据的数值备份： List_PhyParas
     */
    List<PhyParas> TempParas;

    /**
     * 通用算法接口，供实验大厅调用
     * @param paras 由大厅传入的XML数据参数(不包含标准值)：List_PhyParas
     * @return 返回给实验大厅的参数完整数据结果
     */
    public List<PhyParas> InitMethod(List<PhyParas> paras)
    {
        ParaList = new ArrayList<PhyParas>();
        TempParas = new ArrayList<PhyParas>();
        ParaModel paraModel = CopyData.CopyParas(paras);
        ParaList=paraModel.getPhyParasList();
        //使用缓存数据进行数据计算
        CalParasMethod();
        //对完成计算的缓存数据进行转换,转换为大厅传入的标准格式，并存放到ParaList中
        TempParas = CopyData.ReCopyParas(ParaList, paraModel.getTempPhyParasList());
        //将参数返回到实验大厅
        return TempParas;
    }

    public void SetParam(String name, PhyParas value)
    {
        for (int i = 0; i < ParaList.stream().count(); i++)
        {
            if (name.equals(ParaList.get(i)._Name))
            {
                ParaList.set(i,value);
            }
        }
    }

    public PhyParas ReadParam(String name)
    {
        PhyParas para = new PhyParas();

        for (PhyParas item : ParaList)
        {
            if (name.equals(item._Name))
            {
                para = item;
            }
        }

        if (!"".equals(para._Name))
        {
            return para;
        }
        else
        {
            return null;
        }
    }

    public void CalParasMethod()
    {
        try
        {
            for (int num = 0; num < ParaList.stream().count(); num++)
            {

                switch (ParaList.get(num)._Formula) {

                    case "YB":
                    {
                        ParaList.set(num,Function.YB(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG1":
                    {
                        ParaList.set(num,Function.SJBG1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG2":
                    {
                        ParaList.set(num,Function.SJBG2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG3":
                    {
                        ParaList.set(num,Function.SJBG3(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "YZTTJ":
                    {
                        ParaList.set(num,Function.YZTTJ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "TJXDBQDD":
                    {
                        ParaList.set(num,Function.TJXDBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "TJBQDD":
                    {
                        ParaList.set(num,Function.TJBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "BDS1":
                    {
                        ParaList.set(num,Function.BDS1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "LXDS":
                    {
                        ParaList.set(num,Function.LXDS(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "XZT1":
                    {
                        ParaList.set(num,Function.XZT1(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "XZT2":
                    {
                        ParaList.set(num,Function.XZT2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG4":
                    {
                        ParaList.set(num,Function.SJBG4(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "TSZJPJZ":
                    {
                        ParaList.set(num,Function.TSZJPJZ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "TSZJBQDD":
                    {
                        ParaList.set(num,Function.TSZJBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "BDS2":
                    {
                        ParaList.set(num,Function.BDS2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "TSZJXDBQDD":
                    {
                        ParaList.set(num,Function.TSZJXDBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG5":
                    {
                        ParaList.set(num,Function.SJBG5(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "SJBG6":
                    {
                        ParaList.set(num,Function.SJBG6(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "JSSZJPJZ":
                    {
                        ParaList.set(num,Function.JSSZJPJZ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "JSSABQDD":
                    {
                        ParaList.set(num,Function.JSSABQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "JSSZJBQDD":
                    {
                        ParaList.set(num,Function.JSSZJBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "BDS3":
                    {
                        ParaList.set(num,Function.BDS3(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "JSSZJXDBQDD":
                    {
                        ParaList.set(num,Function.JSSZJXDBQDD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                }
            }
        }
        catch (Exception exception)
        { }
    }


    public List<PhyParas> DStoPP(List<String> paraDataSourse)
    {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++)
        {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }

}
