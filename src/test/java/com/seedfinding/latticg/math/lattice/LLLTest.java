package com.seedfinding.latticg.math.lattice;

import com.seedfinding.latticg.math.component.BigMatrix;
import com.seedfinding.latticg.math.component.BigMatrixUtil;
import com.seedfinding.latticg.math.lattice.LLL.LLL;
import com.seedfinding.latticg.math.lattice.LLL.Params;
import org.junit.Test;

import static com.seedfinding.latticg.math.lattice.LLL.Params.recommendedDelta;
import static org.junit.Assert.assertEquals;

public class LLLTest {

    @Test
    public void testReduce2ConsecutiveJavaCalls() {
        BigMatrix basis = BigMatrixUtil.fromString("{{1, 25214903917}, {0, 281474976710656}}");
        BigMatrix expected = BigMatrixUtil.fromString("{{7847617, 4824621}, {-18218081, 24667315}}");
        assertEquals(expected, LLL.reduce(basis, new Params()).getReducedBasis());
    }

    @Test
    public void testReduce12ConsecutiveJavaCalls() {
        BigMatrix basis = BigMatrixUtil.fromString(
            "{{1,25214903917,205749139540585,233752471717045,55986898099985,120950523281469,76790647859193,61282721086213,128954768138017,177269950146317,19927021227657,92070806603349}," +
                "{0, 281474976710656, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}," +
                "{0, 0, 281474976710656, 0, 0, 0, 0, 0, 0, 0, 0, 0}," +
                "{0, 0, 0, 281474976710656, 0, 0, 0, 0, 0, 0, 0, 0}," +
                "{0, 0, 0, 0, 281474976710656, 0, 0, 0, 0, 0, 0, 0}," +
                "{0, 0, 0, 0, 0, 281474976710656, 0, 0, 0, 0, 0, 0}," +
                "{0, 0, 0, 0, 0, 0, 281474976710656, 0, 0, 0, 0, 0}," +
                "{0, 0, 0, 0, 0, 0, 0, 281474976710656, 0, 0, 0, 0}," +
                "{0, 0, 0, 0, 0, 0, 0, 0, 281474976710656, 0, 0, 0}," +
                "{0, 0, 0, 0, 0, 0, 0, 0, 0, 281474976710656, 0, 0}," +
                "{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 281474976710656, 0}," +
                "{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 281474976710656}}");
        BigMatrix expected = BigMatrixUtil.fromString(
            "{{-3220584277339,-678163098047,-8530386804819,4029288934057,2295848026101,-9298238777007,-3232365392515,-4654942041031,3688082483781,1145092603233,4158323460173,-885077887287}," +
                "{-3340516617460,-7558844095460,2998660220908,-8923438766212,-11379173214260,4848015968732,478738963116,-5542299589828,2136460257420,4611134868380,5522033236332,2757441428220}," +
                "{10984234085718,-6406011989602,-1266420501434,1751661913550,8482227530934,-4526287024770,761009140390,6928756995246,-5355554536938,-7387675121826,4984680789766,-3267431673458}," +
                "{-4681649624622,12840992848490,-9649058669790,-5690163127942,1452987348210,5210479671050,581779614530,-1145467111654,5983719952914,-859604623958,7180148317538,530133880506}," +
                "{-4353713886109,-3909056866265,5885683045019,289721495039,8491180601235,-9745719766121,5398662267211,3436570980079,-7847088474685,-4036536560121,-5546606089477,6733721325279}," +
                "{2920819071883,-1626942694353,2223911254531,584029693255,14030410377531,-9489189449697,-2775806675149,-11313375243593,2173687030251,-3049350737137,-10149066516637,5905056311079}," +
                "{-1753331234266,-1925513677266,13587559884182,-4245051751202,-2137179994490,-6553268425970,-6365069498122,517301695678,2263559792870,-11305224483346,-4442850524586,-530899844450}," +
                "{4275813471710,-8854105259130,5271931937806,10923452673526,-3574547704386,-1170843994650,-3291920410898,-3656525195434,-4156752563298,11504064802374,16423165925326,1863615493814}," +
                "{-2927275271675,4358198759969,-749428089331,-5906764121719,6996620318037,-8434527046863,4013008064989,10544347457817,4690845726117,-1572849494719,-1562550095187,-15622834716247}," +
                "{3913481047550,11457107519782,11295936833838,-4892876006250,-2349194285090,-3961586281082,-10007826133490,-9624601063434,-4233142841410,5269765269478,-132373012242,-8329452594858}," +
                "{8462157635811,1251368972967,-1652918150373,1387004841087,1717588765715,-2363517938153,10131123425739,-11456623266449,5477436914243,-4759160995705,5496978719611,-6245443060385}," +
                "{14276340518069,11302502565649,-760697010115,6141148139513,-8322341108987,-2402663381215,6840633257741,3747223598729,-6011287794091,3767907907633,-10053902617891,6917101368857}}");
        assertEquals(expected, LLL.reduce(basis, new Params().setDelta(recommendedDelta)).getReducedBasis());
    }

    @Test
    public void testLinearDependent() {
        BigMatrix basis = BigMatrixUtil.fromString("{{2, 3}, {4, 9}, {6, 0}, {0, 9}, {30, 30}}");
        BigMatrix expected = BigMatrixUtil.fromString("{{-2, 0}, {0, 3}}");
        assertEquals(expected, LLL.reduce(basis, new Params()).getReducedBasis());
    }

    @Test
    public void testBigLinearDependant() {
        BigMatrix basis = BigMatrixUtil.fromString("{{2, 3,128,16}, {4, 9,354,17}, {6, 0,1921,15}, {0, 9,12637,15},{-4,0,-60,-62}}");
        BigMatrix expected = BigMatrixUtil.fromString("{{-2, 0, -30, -31}, {-12, -51, -15, -9}, {-28, 27, -12, 37}, {-42, -21, 41, -18}}");
        assertEquals(expected, LLL.reduce(basis, new Params()).getReducedBasis());
    }

    @Test
    public void testVectorsSpanningSubspace() {
        BigMatrix basis = BigMatrixUtil.fromString("{{2, 3,128,16}, {4, 9,354,17}, {6, 0,1921,15}}");
        BigMatrix expected = BigMatrixUtil.fromString("{{-2, 0, -30, -31}, {-12, -51, -15, -9}, {-2, 3, 68, -46}}");
        assertEquals(expected, LLL.reduce(basis, new Params()).getReducedBasis());
    }

    @Test
    public void testDependantVectorsSpanningSubspace() {
        BigMatrix basis = BigMatrixUtil.fromString("{{2, 3,128,16}, {4, 9,354,17}, {6, 0,1921,15}, {2, 3,128,16}, {4, 9,354,17}, {6, 0,1921,15}}");
        BigMatrix expected = BigMatrixUtil.fromString("{{-2, 0, -30, -31}, {-12, -51, -15, -9}, {-2, 3, 68, -46}}");
        assertEquals(expected, LLL.reduce(basis, new Params()).getReducedBasis());
    }
}
