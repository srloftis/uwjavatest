package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
    ssn = "";
  }
  
  public int compareTo(Person other) {
    if (this.salary > other.salary)
      return -1;
    else if (this.salary < other.salary)
      return 1;
    else
      return 0;
  }
  
  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.age - p2.age;
    }
  }
  
  public static int count() {
    return count;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return (this.name == p.name && this.age == p.age);
    }
    return false;
  }
  
  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> newardFamily = new ArrayList<Person>();
    newardFamily.add(new Person("Ted", 41, 250000));
    newardFamily.add(new Person("Charlotte", 43, 150000));
    newardFamily.add(new Person("Michael", 22, 10000));
    newardFamily.add(new Person("Matthew", 15, 0));
    return newardFamily;
  }
   
  public void setAge(int newAge) {
    if (newAge < 0)
      throw new IllegalArgumentException();
    age = newAge;
  }
  
  public int getAge() {
    return age;
  } 
  
  public void setName(String newName) {
    if (newName == null)
      throw new IllegalArgumentException();
    name = newName;
  }   
  
  public String getName() {
    return name;
  }  
  
  public void setSalary(double newSalary) {
    salary = newSalary;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public String getSSN() {
    return ssn;
  }
  
  public void setPropertyChangeFired(boolean newPCF) {
    propertyChangeFired = newPCF;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public String toString() {
    return "[Person name:" + name +" age:" + age + " salary:" + salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
