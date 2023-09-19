/**
 * Creates a person that has a sex, age, total cholesterol, smoking status, HDL, and systolic BP.
 */
public class Person {
    private boolean sex; // sex: F/M; 0 = F, 1 = M
    private int age; // one's age
    private int cho; // one's cholesterol levels
    private boolean smo; // one's smoking habits; 0 = smoker, 1 = non smoker
    private int hdl; // one's HDL cholesterol levels
    private int sbp; // one's systolic BP
    private boolean med; // if one is treating the systolic BP with medicine or not; 0 = treating, 1 = not treating

    private int points; // cumulative amount of points used to calculate the ten-year risk

    public Person(boolean sex, int a, int c, boolean smo, int h, int sbp, boolean m) {
        this.sex = sex;
        age = a;
        cho = c;
        this.smo = smo;
        hdl = h;
        this.sbp = sbp;
        med = m;
        points = 0;
        calculatePoints();
    }

    /**
     * Adds up all the points, which are given based on which range the value provided is located in.
     * Sexes differ with some point values.
     */
    public void calculatePoints() {
        if (!sex) { // male
            int[] ageCutoffs = {34, 39, 44, 49, 54, 59, 64, 69, 74, 79};
            int[] agePoints = {-9, -4, 0, 3, 6, 8, 10, 11, 12, 13};
            for (int i = 0; i < ageCutoffs.length; i++) {
                if (age <= ageCutoffs[i]) {
                    points += agePoints[i];
                    break;
                }
            }

            int[] choCutoffs = {159, 199, 239, 279, Integer.MAX_VALUE};
            int[] ageCutoffs2 = {39, 49, 59, 69, 79};

            boolean flag = false; // break out of the entire loop once one value is assigned
            int[][] choPoints = {{0, 0, 0, 0, 0}, {4, 3, 2, 1, 0}, {7, 5, 3, 1, 0}, {9, 6, 4, 2, 1}, {11, 8, 5, 3, 1}};
            for (int i = 0; i < choCutoffs.length; i++) {
                if (flag) break;
                if (cho <= choCutoffs[i]) {
                    for (int j = 0; j < ageCutoffs2.length; j++) {
                        if (age <= ageCutoffs2[j]) {
                            points += choPoints[i][j];
                            flag = true;
                            break;
                        }
                    }
                }
            }

            int[] smoPoints = {8, 5, 3, 1, 1};
            if (smo) {
                for (int i = 0; i < ageCutoffs2.length; i++) {
                    if (age <= ageCutoffs2[i]) {
                        points += smoPoints[i];
                        break;
                    }
                }
            }

            // HDL is reverse
            int[] hdlCutoffs = {39, 49, 59, Integer.MAX_VALUE};
            int[] hdlPoints = {2, 1, 0, -1};
            for (int i = 0; i < hdlCutoffs.length; i++) {
                if (hdl <= hdlCutoffs[i]) {
                    points += hdlPoints[i];
                    break;
                }
            }

            int[] sbpCutoffs = {119, 129, 139, 159, Integer.MAX_VALUE};
            int[][] sbpPoints = {{0, 0, 1, 1, 2}, {0, 1, 2, 2, 3}};
            for (int i = 0; i < sbpCutoffs.length; i++) {
                if (sbp <= sbpCutoffs[i]) {
                    if (!med) { // untreated
                        points += sbpPoints[0][i];
                    } else {
                        points += sbpPoints[1][i];
                    }
                    break;
                }
            }
        } else { // female
            int[] ageCutoffs = {34, 39, 44, 49, 54, 59, 64, 69, 74, 79};
            int[] agePoints = {-7, -3, 0, 3, 6, 8, 10, 12, 14, 16};
            for (int i = 0; i < ageCutoffs.length; i++) {
                if (age <= ageCutoffs[i]) {
                    points += agePoints[i];
                    break;
                }
            }

            int[] choCutoffs = {159, 199, 239, 279, Integer.MAX_VALUE};
            int[] ageCutoffs2 = {39, 49, 59, 69, 79};

            boolean flag = false; // break out of the entire loop once one value is assigned
            int[][] choPoints = {{0, 0, 0, 0, 0}, {4, 3, 2, 1, 1}, {8, 6, 4, 2, 1}, {11, 8, 5, 3, 2}, {13, 10, 7, 4, 2}};
            for (int i = 0; i < choCutoffs.length; i++) {
                if (flag) break;
                if (cho <= choCutoffs[i]) {
                    for (int j = 0; j < ageCutoffs2.length; j++) {
                        if (age <= ageCutoffs2[j]) {
                            points += choPoints[i][j];
                            flag = true;
                            break;
                        }
                    }
                }
            }

            int[] smoPoints = {9, 7, 4, 2, 1};
            if (smo) {
                for (int i = 0; i < ageCutoffs2.length; i++) {
                    if (age <= ageCutoffs2[i]) {
                        points += smoPoints[i];
                        break;
                    }
                }
            }

            // HDL is reverse
            int[] hdlCutoffs = {39, 49, 59, Integer.MAX_VALUE};
            int[] hdlPoints = {2, 1, 0, -1};
            for (int i = 0; i < hdlCutoffs.length; i++) {
                if (hdl <= hdlCutoffs[i]) {
                    points += hdlPoints[i];
                    break;
                }
            }

            int[] sbpCutoffs = {119, 129, 139, 159, Integer.MAX_VALUE};
            int[][] sbpPoints = {{0, 1, 2, 3, 4}, {0, 3, 4, 5, 6}};
            for (int i = 0; i < sbpCutoffs.length; i++) {
                if (sbp <= sbpCutoffs[i]) {
                    if (!med) { // untreated
                        points += sbpPoints[0][i];
                    } else {
                        points += sbpPoints[1][i];
                    }
                    break;
                }
            }
        }
    }

    /**
     * Calculates a person's ten-year risk based on the amount of points they have.
     * @return the ten-year risk
     */
    public String tyr() {
        if (!sex) { // male
            int[] pointCutoffs = {1, 1, 1, 1, 1, 2, 2, 3, 4, 5, 6, 8, 10, 12, 16, 20, 25};
            if (points < 0) {
                return "<1";
            } else if (points >= 17) {
                return ">30";
            } else {
                return String.valueOf(pointCutoffs[points]);
            }
        } else {
            int[] pointCutoffs = {1, 1, 1, 1, 2, 2, 3, 4, 5, 6, 8, 11, 14, 17, 22, 27};
            if (points < 9) {
                return "<1";
            } else if (points >= 25) {
                return ">30";
            } else {
                return String.valueOf(pointCutoffs[points - 9]);
            }
        }
    }

    /**
     * Prints out the toString for the specified zyBooks format.
     * @return the formatted string that includes all values
     */
    public String toString() {
        String str = "";
        str += "sex:" + (sex ? "F":"M") + " ";
        str += "age:" + age + " cho:" + cho + " smo:" + (smo ? "Y":"N") + " ";
        str += "hdl:" + hdl + " sbp:" + sbp + " med:" + (med ? "Y":"N") + " ";
        str += "out:" + tyr();
        return str;
    }
}
