package com.seedfinding.latticg;

import com.seedfinding.latticg.util.Rand;

import java.util.ArrayList;
import java.util.Random;

public class JavaRandomReverserTest {
    public static void main(String[] args) {
        Random random = new Random(5275345739485L);

        for (int attempt = 0; attempt < 100; attempt++) {
            Rand rand = Rand.ofInternalSeed(random.nextLong());
            long realSeed = rand.getSeed();
            JavaRandomReverser reverser = new JavaRandomReverser(new ArrayList<>());
            float[] vals = new float[5];
            float errorRange = 0.001f;
            for (int i = 0; i < vals.length; i++) {
                vals[i] = rand.nextFloat() - rand.nextFloat();
                reverser.addNextFloatDifferenceCalls(vals[i] - errorRange, vals[i] + errorRange, true, true, false);
            }

            long[] results = reverser.findAllValidSeeds().toArray();

            boolean foundRealSeed = false;
            for (long result : results) {
                if (result == realSeed) {
                    foundRealSeed = true;
                    break;
                }
            }

            boolean foundFalsePositives = false;
            foundFalsePositives:
            for (long result : results) {
                Rand r = Rand.ofInternalSeed(result);
                for (float val : vals) {
                    float randomVal = r.nextFloat() - r.nextFloat();
                    if (randomVal < val - errorRange || randomVal > val + errorRange) {
                        foundFalsePositives = true;
                        break foundFalsePositives;
                    }
                }
            }

            if (foundRealSeed && !foundFalsePositives) {
//                System.out.println("GOOD " + realSeed);
            } else {
                System.out.println("BAD " + realSeed + ": " + foundRealSeed + " " + foundFalsePositives);
            }
        }

        System.out.println("Done");
    }
}
