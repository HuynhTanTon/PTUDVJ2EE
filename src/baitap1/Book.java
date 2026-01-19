
package baitap1;

import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Nhap thong tin sach
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma sach: ");
        id = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap ten sach: ");
        title = sc.nextLine();
        System.out.print("Nhap tac gia: ");
        author = sc.nextLine();
        System.out.print("Nhap don gia: ");
        price = Double.parseDouble(sc.nextLine());
    }

    // Xuat thong tin sach
    public void output() {
        String msg = """
                BOOK: id=%d, title=%s, author=%s, price=%.2f
                """.formatted(id, title, author, price);
        System.out.println(msg);
    }
}
