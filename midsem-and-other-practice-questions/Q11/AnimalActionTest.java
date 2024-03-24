import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import java.util.*;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class AnimalActionTest {

    /**
     * Complete this method
     * Hint: this corresponds to the `data` method in the lecture slides
     * (Information about parameterized tests are in the software testing lecture slides, starting from P.36)
     */
    @Parameters
    public static Collection<Object[]> testCases() {
        // TODO - START OF YOUR CODE

        // TODO - uncomment the code in the test() method below to run the tests.

        return Arrays.asList(
                new Object[][] {
                        // an example of a parameter is given to you in the comment.
                        // you can refer to the syntax and/or use this as a parameter if you wish.

                        // {AnimalBreeder.createAnimal(AnimalType.DOG, "W", 0,  new ArrayList<>()), new ArrayList<>(), 0},
                }

        );
        // TODO - END OF YOUR CODE
    }


    /**
     * Parameters for the Tests.
     * You cannot change given parameters and/or their orders,
     */
    @Parameter(0)
    public Animal animal;
    @Parameter(1)
    public List<String> expectedFood;
    @Parameter(2)
    public int expectedWeight;



    // TODO - uncomment the code for testing, but do NOT change the content.
    @Test(timeout = 1000)
    public void test() {
//        animal.eat();
//        List<String> actualFood = animal.getFood();
//        assertEquals(expectedFood, actualFood);
//        int actualWeight = animal.getWeight();
//        assertEquals(expectedWeight, actualWeight);
    }

}


