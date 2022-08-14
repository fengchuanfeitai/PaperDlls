import common.ParaModel;
import common.PhyParas;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验数据标准答案计算类
 *
 * @author cp
 */
public class Answer {
    // region Field

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
     *
     * @param paras 由大厅传入的XML数据参数(不包含标准值)：List_PhyParas
     * @return 返回给实验大厅的参数完整数据结果
     */
    public List<PhyParas> InitMethod(List<PhyParas> paras) {
        ParaList = new ArrayList<PhyParas>();
        TempParas = new ArrayList<PhyParas>();
        ParaModel paraModel = CopyData.CopyParas(paras);
        ParaList = paraModel.getPhyParasList();
        //使用缓存数据进行数据计算
        CalParasMethod();
        //对完成计算的缓存数据进行转换,转换为大厅传入的标准格式，并存放到ParaList中
        TempParas = CopyData.ReCopyParas(ParaList, paraModel.getTempPhyParasList());
        //将参数返回到实验大厅
        return TempParas;
    }

    // endregion

    // region Read and Set Param

    public void SetParam(String name, PhyParas value) {
        for (int i = 0; i < ParaList.stream().count(); i++) {
            if (name.equals(ParaList.get(i)._Name)) {
                ParaList.set(i, value);
            }
        }
    }

    public PhyParas ReadParam(String name) {
        PhyParas para = new PhyParas();

        for (PhyParas item : ParaList) {
            if (name.equals(item._Name)) {
                para = item;
            }
        }

        if (!"".equals(para._Name)) {
            return para;
        } else {
            return null;
        }
    }
    // endregion

    //region Method

    /**
     * 根据不同参数，进行标准值求解过程
     */
    public void CalParasMethod() {
        try {
            for (int num = 0; num < ParaList.size(); num++) {

                switch (ParaList.get(num)._Formula) {
                    case "GetGSHBJList": {
                        ParaList.set(num, Function.GetGSHBJList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    //计算直径
                    case "GetGSZHJList": {
                        ParaList.set(num, Function.GetGSZHJList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetQLBJDm": {
                        ParaList.set(num, Function.GetQLBJDm(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "GetQUBJDpj": {
                        ParaList.set(num, Function.GetQUBJDpj(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "GetQLBJUd": {
                        ParaList.set(num, Function.GetQLBJUd(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "GetQuLvR": {
                        ParaList.set(num, Function.GetQuLvR(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "GetQuLvU": {
                        ParaList.set(num, Function.GetQuLvU(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "GetXSZJ_AWList": {
                        ParaList.set(num, Function.GetXSZJ_AWList(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "GetXSZJ_Lpj": {
                        ParaList.set(num, Function.GetXSZJ_Lpj(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "GetXSZJ_Ul": {
                        ParaList.set(num, Function.GetXSZJ_Ul(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "GetXSZJ_D": {
                        ParaList.set(num, Function.GetXSZJ_D(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "GetXSZJ_Ud": {
                        ParaList.set(num, Function.GetXSZJ_Ud(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "Triple": {
                        ParaList.set(num, Function.Triple(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }


                    case "FinalResult": {
                        ParaList.set(num, Function.FinalResult(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Multiplication": {
                        ParaList.set(num, Function.Multiplication(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Division": {
                        ParaList.set(num, Function.Division(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "CompareBool": {
                        ParaList.set(num, Function.CompareBool(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    default:
                        break;
                }

            }
        } catch (Exception ex) {
        }
    }

    //endregion

    public List<PhyParas> DStoPP(List<String> paraDataSourse) {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++) {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }

}