package csci2020u.lab05.lab05;

public class StudentRecord {
    String studentID;
    float midtermGrade;
    float assignmentsGrade;
    float examGrade;
    float finalGrade;
    char letterGrade;

    // Student ID, Assignments, Midterm, Final exam
    public StudentRecord(String studentID, float assignments, float midterms, float exams) {
        this.studentID = studentID;
        this.assignmentsGrade = assignments;
        this.midtermGrade = midterms;
        this.examGrade = exams;

        // calculate final grade
        calcFinalGrade();
        // this.finalGrade = ((0.2f) * (this.assignmentsGrade)) + ((0.3f) * (this.midtermGrade)) + ((0.5f) * (this.finalGrade));

        // calculate letter grade
        calcLetterGrade();
    }

    // The final mark will be calculated as a weighted average of the assignments (20%), midterm (30%), and final exam (50%).
    private void calcFinalGrade() {
        this.finalGrade = ((0.2f) * (this.assignmentsGrade)) + ((0.3f) * (this.midtermGrade)) + ((0.5f) * (this.examGrade));
    }

    private void calcLetterGrade() {
        if (this.finalGrade >= 80) {
            this.letterGrade = 'A';
        } else if (this.finalGrade >= 70) {
            this.letterGrade = 'B';
        } else if (this.finalGrade >= 60) {
            this.letterGrade = 'C';
        } else if (this.letterGrade >= 50) {
            this.letterGrade = 'D';
        } else {
            this.letterGrade = 'F';
        }
    }

    public float getFinalGrade() {
        return this.finalGrade;
    }

    public char getLetterGrade() {
        return this.letterGrade;
    }

/*    public static void main(String[] args) {
//        StudentRecord student = new StudentRecord("100100100", 75.0f, 68.0f, 54.25f);
        StudentRecord student = new StudentRecord("100100109", 82.5f, 77.0f, 74.25f);

        System.out.println(student.getFinalGrade() + "\n" + student.getLetterGrade());
    }*/
}

