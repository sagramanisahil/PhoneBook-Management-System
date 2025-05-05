
package PhoneBook;
import java.util.Scanner;


class listt
{
    Person root;
    Scanner s=new Scanner(System.in);
    

  void insert(String name,String name2,String no)
  {
    
Person newperson=new Person(name,name2,no);
root=insert(root,newperson);

  
}
Person insert(Person root, Person newperson)
{
    if(root==null)
    {
   
        return  newperson;
    }
    int compare=root.fullname.compareToIgnoreCase(newperson.fullname);
 
   if(compare<0)
   {
    root.left=insert(root.left,newperson);
   }
   else if(compare>0)
   {
    root.right=insert(root.right,newperson);
   }
   else 
   {
    System.out.println("this name already exist please insert another name");

    //insert();
    return root;

   }

   return root;
}
void delete(String name,String name2)
{
 
  root=delete(root,name,name2);
  
  
  
}
Person delete(Person root, String name, String name2)
{

    if(root==null)
    {
      System.out.println("name doesnt exist");
          return null;
    }
    String f=name.trim()+" "+name2.trim();
    int compare=root.fullname.compareToIgnoreCase(f);
    
    
    if(compare<0 )
    {
        root.left=delete(root.left,name,name2); 
       //return root;
    }
     else if(compare>0)
    {
        root.right=delete(root.right,name,name2);
       // return root; 
    }
    else
    {
        if(root.left==null)
        return root.right;
        else if(root.right==null)
        return root.left;
        else
         root=worsecase(root);
        //return root.right;
    }
    return root;

}
Person worsecase(Person root)
{
  Person parent=root;
  Person current=root.right;
  while(current.left!=null)
  {
    parent=current;
     current=current.left;
  }
  root.fullname = current.fullname;
  root.firstname = current.firstname;
  root.lastname = current.lastname;
  root.contactno = current.contactno;

  if (parent == root) {
      root.right = current.right;
  } else {
      parent.left = current.right;
  }

  return root;


}
void print(Person root)
{
    if(root==null)
    {
        return;
    }
    print(root.left);
    System.out.println("first name: "+root.firstname+"\n"+"lastname: "+root.lastname+"\n"+"contact no: "+root.contactno);
    print(root.right);

}

}

