package Testes;

import kata09.Checkout;
import kata09.CheckoutRules;
import org.junit.Test;

import java.util.List;

import static Utils.TestConstants.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPricingRules {

    public CheckoutRules rules = new CheckoutRules();

    private Long price(final String itens) {
        Checkout co = new Checkout(rules);
        itens.chars().forEachOrdered(i -> co.scan(String.valueOf((char) i)));
        return co.getTotalPrice();
    }

    @Test
    public void TestPricingRules() {

        assertEquals(Long.valueOf(0), price(""));
        assertEquals(Long.valueOf(50), price("A"));
        assertEquals(Long.valueOf(30), price("B"));
        assertEquals(Long.valueOf(20), price("C"));
        assertEquals(Long.valueOf(15), price("D"));

        assertEquals(Long.valueOf(100), price("AA"));
        assertEquals(Long.valueOf(130), price("AAA"));
        assertEquals(Long.valueOf(180), price("AAAA"));
        assertEquals(Long.valueOf(230), price("AAAAA"));
        assertEquals(Long.valueOf(260), price("AAAAAA"));

        assertEquals(Long.valueOf(45), price("BB"));
        assertEquals(Long.valueOf(75), price("BBB"));
        assertEquals(Long.valueOf(90), price("BBBB"));
        assertEquals(Long.valueOf(120), price("BBBBB"));
        assertEquals(Long.valueOf(135), price("BBBBBB"));

        assertEquals(Long.valueOf(40), price("CC"));
        assertEquals(Long.valueOf(60), price("CCC"));
        assertEquals(Long.valueOf(80), price("CCCC"));
        assertEquals(Long.valueOf(100), price("CCCCC"));
        assertEquals(Long.valueOf(120), price("CCCCCC"));

        assertEquals(Long.valueOf(30), price("DD"));
        assertEquals(Long.valueOf(45), price("DDD"));
        assertEquals(Long.valueOf(60), price("DDDD"));
        assertEquals(Long.valueOf(75), price("DDDDD"));
        assertEquals(Long.valueOf(90), price("DDDDDD"));

        assertEquals(Long.valueOf(80), price("AB"));
        assertEquals(Long.valueOf(115), price("CDBA"));
        assertEquals(Long.valueOf(160), price("AAAB"));
        assertEquals(Long.valueOf(175), price("AAABB"));
        assertEquals(Long.valueOf(190), price("AAABBD"));
        assertEquals(Long.valueOf(190), price("DABABA"));

    }

    @Test
    public void incrementalTests() {
        Checkout co = new Checkout(rules);
        assertEquals(Long.valueOf(0), co.getTotalPrice());
        co.scan("A");
        assertEquals(Long.valueOf(50), co.getTotalPrice());
        co.scan("B");
        assertEquals(Long.valueOf(80), co.getTotalPrice());
        co.scan("A");
        assertEquals(Long.valueOf(130), co.getTotalPrice());
        co.scan("A");
        assertEquals(Long.valueOf(160), co.getTotalPrice());
        co.scan("B");
        assertEquals(Long.valueOf(175), co.getTotalPrice());

    }

}
