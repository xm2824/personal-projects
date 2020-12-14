
public class Main <T>{

    

    @Override
    public String toString() {
        return "{" +
            "}";
    }
    
    public void name(T hello) {
        System.out.println(hello);
    }
    public static void main(String[] args) {
       Main a= new Main();
       a.name(22);
    }
}

