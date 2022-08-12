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

                switch (ParaList.get(num)._Formula)
                {
                    //
                    case "D1list":
                    {
                        ParaList.set(num,Function.D1list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    //
                    case "D2list":
                    {
                        ParaList.set(num,Function.D2list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D3list":
                    {
                        ParaList.set(num,Function.D3list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D4list":
                    {
                        ParaList.set(num,Function.D4list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D5list":
                    {
                        ParaList.set(num,Function.D5list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D7list":
                    {
                        ParaList.set(num,Function.D7list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D8list":
                    {
                        ParaList.set(num,Function.D8list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D9list":
                    {
                        ParaList.set(num,Function.D9list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D10list":
                    {
                        ParaList.set(num,Function.D10list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D11list":
                    {
                        ParaList.set(num,Function.D11list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D12list":
                    {
                        ParaList.set(num,Function.D12list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //
                    case "D13list":
                    {
                        ParaList.set(num,Function.D13list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D14list":
                    {
                        ParaList.set(num,Function.D14list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D15list":
                    {
                        ParaList.set(num,Function.D15list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D16list":
                    {
                        ParaList.set(num,Function.D16list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }

                    //
                    case "D17list":
                    {
                        ParaList.set(num,Function.D17list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D18list":
                    {
                        ParaList.set(num,Function.D18list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D19list":
                    {
                        ParaList.set(num,Function.D19list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D20list":
                    {
                        ParaList.set(num,Function.D20list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D21list":
                    {
                        ParaList.set(num,Function.D21list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D22list":
                    {
                        ParaList.set(num,Function.D22list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D23list":
                    {
                        ParaList.set(num,Function.D23list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    //
                    case "D24list":
                    {
                        ParaList.set(num,Function.D24list(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));

                        break;
                    }
                    default:
                        break;

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
