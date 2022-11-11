
package src;
public interface Composite {
    public String getID();
    
    public String toString();
    
    public void accept(UserGroupV visitor);
}