package test.model;

import org.junit.BeforeClass;
import org.junit.Test;
import song.core.model.Admin;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;
import static org.junit.Assert.assertEquals;
/**
 * Created by Song on 2015/11/9.
 */
public class AdminTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                validator = factory.getValidator();

    }


    @Test
    public void test(){

        Admin admin = new Admin();
        admin.setEmail("as");
        Set<ConstraintViolation<Admin>> constraintViolations
                =validator.validate(admin);
        assertEquals(2,constraintViolations.size());
        constraintViolations.iterator().next().getMessage();
        assertEquals("User name requires at least 3 character.",constraintViolations.iterator().next());
        System.out.println(Arrays.toString(constraintViolations.toArray()));
    }
}
