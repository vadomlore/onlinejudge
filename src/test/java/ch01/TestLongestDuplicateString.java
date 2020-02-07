package ch01;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestDuplicateString {

    @Test
    public void testNormalCase(){
        String s = "aaabbbbbcccccccczzzzzzzz";
        LongestDuplicateString.Tuple<Integer, Integer> value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 12, s.length());
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(16, 8));

        s = "";
        value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, s.length());
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(-1, 0));

        s = null;
        value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, 1);
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(-1, 0));

        s = "a";
        value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, s.length());
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(0, 1));

        s = "aabbcc";
        value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, s.length());
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(0, 2));

        s = "ccbbaa";
        value =
                LongestDuplicateString.maxDuplicateSubstrInASCIIOrder(s, 0, s.length());
        Assert.assertEquals(value, LongestDuplicateString.Tuple.of(4, 2));
    }
}
