package book;

public class Main {
    public static void main(String[] args) {
        
        var book = new book.entity.Book(
                "Memorias postumas de Brás Cubas",
                "Machado de Assis",
                1881
        );
        
        System.out.println(book.toString());
    }
}
