import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeTest {
    private Student student;
    private ArrayList<Education> educations;
    private ArrayList<String> skills;
    private ArrayList<Experience> workExperiences;
    private ArrayList<Experience> extraCurriculars;

    @BeforeEach
    public void setup() {
        Resume.clear();
    }


    @Test 
    public void testaddSkills() {
        skills.add(skills);
        assertTrue(Resume.getskills().size() > 0);
    }

    @Test
    public void testaddWorkExperience() {
        workExperiences.add(experience);
        assertTrue(Resume.getWorkExperience().size() > 0);
    }

    @Test
    public void testaddExtraCurricular() {
        extraCurriculars.add(experience);
        assertTrue(Resume.getExtraCurricular().size() > 0);
    }

    @Test
    public void testaddEducation() {
        educations.add(education);
        assertTrue(Resume.geteducation().size() > 0);
    }
    
}
