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

    /**
     * 根据不同参数，进行标准值求解过程
     */
    public void CalParasMethod() {
        try {
            for (int num = 0; num < ParaList.size(); num++) {

                switch (ParaList.get(num)._Formula) {

                    case "DataListOne": {
                        ParaList.set(num, Function.DataListOne(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Average_k": {
                        ParaList.set(num, Function.Average_k(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Average_DK2": {
                        ParaList.set(num, Function.Average_DK2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Average_kDK2": {
                        ParaList.set(num, Function.Average_kDK2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "Average_k2": {
                        ParaList.set(num, Function.Average_k2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Average_Dk22": {
                        ParaList.set(num, Function.Average_Dk22(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Ratio_a": {
                        ParaList.set(num, Function.Ratio_a(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Intercept_b": {
                        ParaList.set(num, Function.Intercept_b(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "CurvatureRadius": {
                        ParaList.set(num, Function.CurvatureRadius(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "CorrelationCoefficient": {
                        ParaList.set(num, Function.CorrelationCoefficient(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    case "StandardDeviation_SDk2": {
                        ParaList.set(num, Function.StandardDeviation_SDk2(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Uncertainty_ua": {
                        ParaList.set(num, Function.Uncertainty_ua(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Uncertainty_uR": {
                        ParaList.set(num, Function.Uncertainty_uR(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Uncertainty_UrR": {
                        ParaList.set(num, Function.Uncertainty_UrR(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }
                    case "Expression_R": {
                        ParaList.set(num, Function.Expression_R(ParaList.get(num), DStoPP(ParaList.get(num)._DataSourse)));
                        break;
                    }

                    default:
                        break;
                }

                //#region 各个参数值求解过程
                //switch (ParaList.get(num)._Name)
                //{
                //    case "":
                //        break;
                //    case "SYTmp":
                //        {
                //            PhyParas SYStratTmp = ReadParam("SYStratTmp");
                //            PhyParas SYEndTmp = ReadParam("SYEndTmp");

                //            PhyParas SYTmp = ReadParam("SYTmp");
                //            if (Function.IsValidData(SYStratTmp) == true && Function.IsValidData(SYEndTmp) == true)
                //            {
                //                SYTmp._StandardValue = (XmlAnalysis.GetDouble(SYStratTmp._StudentValue) + XmlAnalysis.GetDouble(SYEndTmp._StudentValue)) / 2.0;
                //            }
                //            else
                //            {
                //                SYTmp._StandardValue = "";
                //            }


                //            SetParam("SYTmp", SYTmp);

                //            break;
                //        }

                //    //不需要求解的参数，其返回值等于输入值
                //    default:
                //        {
                //            ParaList.get(num)._StandardValue = ParaList.get(num)._StudentValue;
                //            break;
                //        }

                //}

                //#endregion

            }
        } catch (Exception ex) {
        }
    }


    public List<PhyParas> DStoPP(List<String> paraDataSourse) {
        List<PhyParas> lp = new ArrayList<PhyParas>();

        for (int i = 0; i < paraDataSourse.stream().count(); i++) {
            PhyParas p = ReadParam(paraDataSourse.get(i));
            lp.add(p);
        }

        return lp;
    }

}
