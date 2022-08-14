import common.PhyParas;
import common.XmlAnalysis;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionTest extends TestCase {

    public void testLTF_list1() {

        String formula = "TLIST";
        String studentValue = "(测量孔编号,ZA(cm),ZB(cm),h(cm),t(ms),g(m/s2));(1,12,32,,,);(2,12,32,,,);(3,21,32,,,);(4,21,23,,,);(5,1,33,,,);(6,221,32,,,);(7,21,32,,,);(8,,,,,);(9,,,,,);(10,,,,,);";
        List<PhyParas> ParaList = testInit(formula, studentValue);
        Answer answer = new Answer();
        assertNotNull(answer.InitMethod(ParaList));
    }

    public void testLTF_GPJ1() {
        String formula = "LTF_GPJ1";
        String studentValue = "(测量孔编号,ZA(cm),ZB(cm),h(cm),t(ms),g(m/s2));(1,12,32,,,);(2,12,32,,,);(3,21,32,,,);(4,21,23,,,);(5,1,33,,,);(6,221,32,,,);(7,21,32,,,);(8,,,,,);(9,,,,,);(10,,,,,);";
        List<PhyParas> ParaList = testInit(formula, studentValue);
        Answer answer = new Answer();
        assertNotNull(answer.InitMethod(ParaList));
    }

    public void testLTF_BFWC1() {
        String formula = "LTF_BFWC1";
        String studentValue = "99.00";
        List<PhyParas> ParaList = testInit(formula, studentValue);
        Answer answer = new Answer();
        assertNotNull(answer.InitMethod(ParaList));
    }

    public void testLTF_list2() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_list2(paras, ParaList));
    }

    public void testLTF_GPJ2() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_GPJ2(paras, ParaList));
    }

    public void testLTF_BFWC2() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_BFWC2(paras, ParaList));
    }

    public void testLTF_X1() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_X1(paras, ParaList));
    }

    public void testLTF_Y1() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_Y1(paras, ParaList));
    }

    public void testLTF_X2() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_X2(paras, ParaList));
    }

    public void testLTF_Y2() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_Y2(paras, ParaList));
    }

    public void testLTF_XL() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_XL(paras, ParaList));
    }

    public void testLTF_BFWC3() {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();

        assertNotNull(Function.LTF_BFWC3(paras, ParaList));
    }

    /**
     * 测试初始化
     *
     * @param formula      公式名称
     * @param studentValue 参数
     * @return 结果
     */
    private List<PhyParas> testInit(String formula, String studentValue) {
        List<PhyParas> ParaList = new ArrayList<>();
        PhyParas paras = new PhyParas();
        paras._Name = "FJSD_ZYLTF";
        paras._Formula = formula;
        paras._Type = "TLIST";
        paras._StudentValue = studentValue;
        List<String> data = new ArrayList<>();
        data.add("FJSD_ZYLTF");
        paras._DataSourse = data;
        ParaList.add(paras);
        return ParaList;
    }
}