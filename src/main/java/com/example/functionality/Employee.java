package com.example.functionality;

public class Employee extends Person{

    protected String position,graduationCollage;
    public String password;
    protected float salary,totalGrade;
    protected int graduationYear;

    public Employee(String ID,String fN,String lF,String telephone, String Address,String position, String graduationCollage, float salary, float totalGrade, int graduationYear, String password) {
        this.position = position;
        this.graduationCollage = graduationCollage;
        this.salary = salary;
        this.totalGrade = totalGrade;
        this.graduationYear = graduationYear;
        this.address=Address;
        this.id=ID;
        this.firstName=fN;
        this.lastName=lF;
        this.telephone=telephone;
        this.password = password;
    }

    public String getID(){
        return id;
    }
    public void setID(String id){
        this.id=id;
    }
    public String getfirstName(){
        return firstName;
    }
    public void setfirstName(String fN){
        this.firstName=fN;
    }
    public String getlastName(){
        return lastName;
    }
    public void setlastName(String lN){
        this.lastName=lN;
    }
    public String getTelephone(){
        return telephone;
    }
    public void setTelephone(String Tele){
        this.telephone=Tele;
    }
    public String getAddress(){
        return id;
    }
    public void setAddress(String add){
        this.address=add;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getGraduationCollage() {
        return graduationCollage;
    }
    public void setGraduationCollage(String graduationCollage) {
        this.graduationCollage = graduationCollage;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {this.salary = salary;}
    public float getTotalGrade() {
        return totalGrade;
    }
    public void setTotalGrade(float totalGrade) {this.totalGrade = totalGrade;}
    public int getGraduationYear() {
        return graduationYear;
    }
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void SalaryAdded(Object obj){
        //send notification salary added (david idea maybe erased if not used)
    }

    public String getPassword() {
        return password;
    }
}
