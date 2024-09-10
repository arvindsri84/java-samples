package com.arvindsri84.javasamples.ds;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringReversalTest {


    @Test
    public void reverseStringExcludingAlphaNumeric() {
        assertEquals("321-cba", reverse("abc-123"));
        assertEquals("---", reverse("---"));
        assertEquals("", reverse(""));
        assertEquals("0123456", reverse("6543210"));

    }

    private String reverse(String testString) {
        var arr = testString.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {

            if (!isAlphaNumeric(arr[i])) {
                i++;
            } else if (!isAlphaNumeric(arr[j])) {
                j--;
            } else {
                char tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        return new String(arr);
    }

    private boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');

    }
}
