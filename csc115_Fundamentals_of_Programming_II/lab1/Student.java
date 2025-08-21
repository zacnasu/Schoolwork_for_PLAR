public Student{
    private String sID;
    private int grade; 

    public Student(){
        this.sID = "";
        this.grade = -1;
    }

    public Student(String sID, int grade){
        this.sID = sID;
        this.grade = grade;
    }

    public String getSID(){
        return this.sID;
    }
    public void setSID(String sID){
        this.sID = sID;
    }

    public int getGrade(){
        return this.grade;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    public String toString(){
        return this.sID + ":" + this.grade;
    }

    public equals(Student other){
        if(this.grade == other.getGrade()){
            if(this.sID.equals(other.getSID)){
                return true;
            }
        }
        return false;
    }
    
}