import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class ProbabilityTest {
    @Test
    public void onlyEquallyLikelyEventsHaveEqualProbabilities(){
        assertEquals(new Probability(0.3), new Probability(0.3));
        assertFalse(new Probability(0.3).equals(new Probability(0.2)));

    }

    @Test
    public void onlyProbabilityObjectsCanBeCompared() {
        assertFalse(new Probability(0.8).equals(new Double(0.8)));
    }

    @Test
    public void nullProbabilityObjectIsNeverEqualToAnyProbabilityObject() {
        assertFalse(new Probability(0.8).equals(null));
    }

    @Test
    public void sameProbabilityObjectsAreEqual() {
        Probability p = new Probability(0.7);
        assertEquals(p, p);
    }

    @Test
    public void shouldReturnTheProbabilityOfTheEventNotOccurring (){
        assertEquals(new Probability(0.8), new Probability(0.2).not());
    }

    @Test
    public void shouldReturnTheProbabilityOfTwoEventsOccurring (){
        assertEquals(new Probability(0.32), new Probability(0.4).and(new Probability(0.8)));
    }

    @Test
    public void shouldReturnTheProbabilityOfOneOrBothOfTheTwoEventsOccurring(){
        assertEquals(new Probability(0.6), new Probability(0.2).or(new Probability(0.5)));
    }
}
