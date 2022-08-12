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

                    case "GetHNJGZCFList":
                    {
                        ParaList.set(num,Function.GetHNJGZCFList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetXiPJ":
                    {
                        ParaList.set(num,Function.GetXiPJ(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetHeNeJGBC":
                    {
                        ParaList.set(num,Function.GetHeNeJGBC(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetHeNeJGBEU":
                    {
                        ParaList.set(num,Function.GetHeNeJGBEU(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetHeNeBCEr":
                    {
                        ParaList.set(num,Function.GetHeNeBCEr(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }




                    case "GetNHGZCFList":
                    {
                        ParaList.set(num,Function.GetNHGZCFList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNHGHp":
                    {
                        ParaList.set(num,Function.GetNHGHp(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNHGBC":
                    {
                        ParaList.set(num,Function.GetNHGBC(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNHGBCU":
                    {
                        ParaList.set(num,Function.GetNHGBCU(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNHGBCEr":
                    {
                        ParaList.set(num,Function.GetNHGBCEr(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }




                    case "GetNGSXdList":
                    {
                        ParaList.set(num,Function.GetNGSXdList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNGSXGCCL":
                    {
                        ParaList.set(num,Function.GetNGSXGCCL(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNGSXBCClmd":
                    {
                        ParaList.set(num,Function.GetNGSXBCClmd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNGSXUbc":
                    {
                        ParaList.set(num,Function.GetNGSXUbc(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetNGSXbcEr":
                    {
                        ParaList.set(num,Function.GetNGSXbcEr(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }




                    case "GetBMZSLdList":
                    {
                        ParaList.set(num,Function.GetBMZSLdList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetBMZSLdertaD":
                    {
                        ParaList.set(num,Function.GetBMZSLdertaD(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetBMZSLUd":
                    {
                        ParaList.set(num,Function.GetBMZSLUd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetBMZSLzslN":
                    {
                        ParaList.set(num,Function.GetBMZSLzslN(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetBMZSLUn":
                    {
                        ParaList.set(num,Function.GetBMZSLUn(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "FinalResult":
                    {
                        ParaList.set(num,Function.FinalResult(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }



                    case "HeNeStandardDeviation":
                    {
                        ParaList.set(num,Function.HeNeStandardDeviation(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "HeNeUncertainty_A":
                    {
                        ParaList.set(num,Function.HeNeUncertainty_A(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Uncertainty_B":
                    {
                        ParaList.set(num,Function.Uncertainty_B(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Uncertainty_C":
                    {
                        ParaList.set(num,Function.Uncertainty_C(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "NaYellowStandardDeviation":
                    {
                        ParaList.set(num,Function.NaYellowStandardDeviation(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "NaYellowUncertainty_A":
                    {
                        ParaList.set(num,Function.NaYellowUncertainty_A(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "NaDoubleStandardDeviation":
                    {
                        ParaList.set(num,Function.NaDoubleStandardDeviation(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "NaDoubleUncertainty_A":
                    {
                        ParaList.set(num,Function.NaDoubleUncertainty_A(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "NaDoubleUncertainty_D":
                    {
                        ParaList.set(num,Function.NaDoubleUncertainty_D(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
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
