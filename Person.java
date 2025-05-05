package PhoneBook;

public class Person {
   
    String firstname;
    String lastname;
    String contactno;
    String fullname;
    Person left;
    Person right;
    Person(String f,String l,String c)
    {
     this.firstname=f;
     this.lastname=l;
     this.fullname=f.trim()+" "+l.trim();
     this.contactno=c;
     left=right=null;
    }

    
}
