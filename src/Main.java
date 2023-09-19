import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Generates test cases for the ten-year risk.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("tyr_test_cases.py");
        int[] ages = {20, 34, 35, 39, 40, 44, 45, 49, 50, 54, 55, 59, 60, 64, 65, 69, 70, 74, 75, 79};
        int[] chol = {150, 159, 160, 199, 200, 239, 240, 279, 280, 281};
        int[] hdls = {30, 39, 40, 49, 50, 59, 60, 61};
        int[] sbps = {110, 119, 120, 129, 130, 139, 140, 159, 160, 161};

        // male
        HashSet<Person> male = new HashSet<>();
        for (int i = 0; i < ages.length; i += 2) { // calculations will be based on pairs of ages
            // lower bound of the age pair
            int cho = chol[0];
            int hdl = hdls[0];
            int sbp = sbps[0];
            boolean smoker = Math.round(Math.random()) == 0;
            boolean med = Math.round(Math.random()) == 0;
            male.add(new Person(false, ages[i], cho, smoker, hdl, sbp, med));

            // upper bound of the age pair
            cho = chol[chol.length - 1];
            hdl = hdls[hdls.length - 1];
            sbp = sbps[sbps.length - 1];
            smoker = Math.round(Math.random()) == 0;
            med = Math.round(Math.random()) == 0;
            male.add(new Person(false, ages[i], cho, smoker, hdl, sbp, med));

            // random values in between the age range
            for (int j = 0; j < 10; j++) {
                cho = chol[1 + (int) (Math.random() * (chol.length - 2))];
                hdl = hdls[1 + (int) (Math.random() * (hdls.length - 2))];
                sbp = sbps[1 + (int) (Math.random() * (sbps.length - 2))];
                smoker = Math.round(Math.random()) == 0;
                med = Math.round(Math.random()) == 0;
                male.add(new Person(false, ages[i], cho, smoker, hdl, sbp, med));
            }
        }

        // female; separating the two allows more randomness and therefore 100% coverage
        HashSet<Person> female = new HashSet<>();
        for (int i = 0; i < ages.length; i += 2) { // calculations will be based on pairs of ages
            // lower bound of the age pair
            int cho = chol[0];
            int hdl = hdls[0];
            int sbp = sbps[0];
            boolean smoker = Math.round(Math.random()) == 0;
            boolean med = Math.round(Math.random()) == 0;
            female.add(new Person(true, ages[i], cho, smoker, hdl, sbp, med));

            // upper bound of the age pair
            cho = chol[chol.length - 1];
            hdl = hdls[hdls.length - 1];
            sbp = sbps[sbps.length - 1];
            smoker = Math.round(Math.random()) == 0;
            med = Math.round(Math.random()) == 0;
            female.add(new Person(true, ages[i], cho, smoker, hdl, sbp, med));

            // random values in between the age range
            for (int j = 0; j < 10; j++) {
                cho = chol[1 + (int) (Math.random() * (chol.length - 2))];
                hdl = hdls[1 + (int) (Math.random() * (hdls.length - 2))];
                sbp = sbps[1 + (int) (Math.random() * (sbps.length - 2))];
                smoker = Math.round(Math.random()) == 0;
                med = Math.round(Math.random()) == 0;
                female.add(new Person(true, ages[i], cho, smoker, hdl, sbp, med));
            }
        }

        for (Person p : male) {
            file.write(p.toString());
            file.write("\n");
        }

        for (Person p : female) {
            file.write(p.toString());
            file.write("\n");
        }

        file.close();
    }
}