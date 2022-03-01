package csci2020u.lab05.lab05;

public class StudentRecord {
    String studentID;
    double midtermGrade;
    double assignmentsGrade;
    double examGrade;
    double finalGrade;
    char letterGrade;

    // Student ID, Assignments, Midterm, Final exam
    public StudentRecord(String studentID, double assignments, double midterms, double exams) {
        this.studentID = studentID;
        this.assignmentsGrade = assignments;
        this.midtermGrade = midterms;
        this.examGrade = exams;

        // calculate final grade
        calcFinalGrade();
        // calculate letter grade
        calcLetterGrade();
    }

    // The final mark will be calculated as a weighted average of the assignments (20%), midterm (30%), and final exam (50%).
    private void calcFinalGrade() {
        this.finalGrade = ((0.2) * (this.assignmentsGrade)) + ((0.3) * (this.midtermGrade)) + ((0.5) * (this.finalGrade));
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
}

