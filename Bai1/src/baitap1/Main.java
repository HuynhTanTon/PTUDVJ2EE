
package baitap1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String menu = """
                ===== CHUONG TRINH QUAN LY SACH =====
                1. Them 1 cuon sach
                2. Xoa 1 cuon sach
                3. Thay doi thong tin sach
                4. Xuat thong tin tat ca sach
                5. Tim sach co tieu de chua "lap trinh"
                6. Lay toi da K sach co gia <= P
                7. Tim sach theo danh sach tac gia
                0. Thoat
                Chon chuc nang:
                """;

        int choice;
        do {
            System.out.print(menu);
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                // CASE 1
                case 1 -> {
                    Book b = new Book();
                    b.input();

                    boolean exists = listBook.stream()
                            .anyMatch(x -> x.getId() == b.getId());

                    if (exists) {
                        System.out.println("Ma sach da ton tai!");
                    } else {
                        listBook.add(b);
                        System.out.println("Them sach thanh cong!");
                    }
                }

                // CASE 2
                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Book find = listBook.stream()
                            .filter(x -> x.getId() == id)
                            .findFirst()
                            .orElse(null);

                    if (find == null) {
                        System.out.println("Khong tim thay sach!");
                    } else {
                        listBook.remove(find);
                        System.out.println("Xoa sach thanh cong!");
                    }
                }

                // CASE 3
                case 3 -> {
                    System.out.print("Nhap ma sach can sua: ");
                    int id = Integer.parseInt(sc.nextLine());

                    Book find = listBook.stream()
                            .filter(x -> x.getId() == id)
                            .findFirst()
                            .orElse(null);

                    if (find == null) {
                        System.out.println("Khong tim thay sach!");
                    } else {
                        System.out.println("Nhap lai thong tin:");
                        find.input();
                        System.out.println("Cap nhat thanh cong!");
                    }
                }

                // CASE 4
                case 4 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sach rong!");
                    } else {
                        listBook.forEach(Book::output);
                    }
                }

                // CASE 5
                case 5 -> {
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lap trinh"))
                            .forEach(Book::output);
                }

                // CASE 6
                case 6 -> {
                    System.out.print("Nhap K: ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap gia P: ");
                    double p = Double.parseDouble(sc.nextLine());

                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }

                // CASE 7
                case 7 -> {
                    System.out.print("Nhap so tac gia can tim: ");
                    int n = Integer.parseInt(sc.nextLine());

                    Set<String> authorSet = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Nhap ten tac gia " + (i + 1) + ": ");
                        authorSet.add(sc.nextLine().toLowerCase());
                    }

                    listBook.stream()
                            .filter(b -> authorSet.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }

                case 0 -> System.out.println("Thoat chuong trinh!");

                default -> System.out.println("Chon sai chuc nang!");
            }

        } while (choice != 0);
    }
}
