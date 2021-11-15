import static org.junit.jupiter.api.Assertions.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EducationTest {
    private Education education = new Education("School",2023,"major","minor",3.8);

    @Test
    public void testToString() {
        String outcome = education.getSchool() + "\nClass Year: " + education.getClassYear() + "\nMajor: " + education.getMajor() +
        "\nMinor: " + education.getMinor() + "\nGPA: " + education.getGpa();

        assertEquals(outcome, education.toString());
    }
}
