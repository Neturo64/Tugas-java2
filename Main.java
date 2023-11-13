import java.util.Scanner; // Import the Scanner class

//  Buat class Menu untuk menyimpan informasi-informasi yang ada di menu restoran
class Menu {
    int number; // Tambahkan nomor untuk setiap menu
    String name;
    double price;
    String category;

    // Buat constructor untuk menginisialisasi variabel-variabel di atas
    Menu(int number, String name, double price, String category) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in); // Buat scanner untuk membaca input dari user
    static Menu[] menus = {     // Buat array of objects untuk menyimpan menu-menu restoran
            new Menu(1, "Nasi Padang", 25000, "makanan"),
            new Menu(2, "Sate Ayam", 20000, "makanan"),
            new Menu(4, "Nasi Goreng", 22000, "makanan"),
            new Menu(6, "Mie Goreng", 23000, "makanan"),
            new Menu(3, "Es Teh", 5000, "minuman"),
            new Menu(5, "Es Jeruk", 5000, "minuman"),
    };

    // Buat method untuk menampilkan daftar menu
    static void displayMenu() {
        System.out.println("Daftar Menu Restoran:");
        for (Menu menu : menus) {
            System.out.println(menu.number + ". " + menu.name + " (" + menu.category + ") - Rp. " + menu.price);
        }
    }

    // Buat method untuk menampilkan semua menu
    static void displayAllMenu() {
        System.out.println("Semua Menu Restoran:");
        for (Menu menu : menus) {
            System.out.println(menu.number + ". " + menu.name + " (" + menu.category + ") - Rp. " + menu.price);
        }
    }

    // Buat method untuk mengambil pesanan
    static void takeOrder() {
        double totalBiaya = 0;
        int minumanCount = 0;
        String[] pesanan = new String[4];

        // Looping untuk mengambil pesanan
        for (int i = 0; i < pesanan.length; i++) {
            System.out.print("Pesanan ke-" + (i + 1) + " (Ketik 'selesai' untuk menyelesaikan pesanan): ");
            String pesananInput = scanner.nextLine();

            // Jika user mengetikkan "selesai", maka keluar dari loop
            if (pesananInput.equalsIgnoreCase("selesai")) {
                break;
            }

            // Cek apakah menu yang dipilih valid
            boolean menuValid = false;
            for (Menu menu : menus) {
                if (menu.name.equalsIgnoreCase(pesananInput)) {
                    menuValid = true;
                    System.out.print("Jumlah pesanan: ");
                    int jumlah = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Hitung subtotal dan total biaya
                    double subtotal = menu.price * jumlah;
                    totalBiaya += subtotal;

                    // Tambahkan pesanan ke array pesanan
                    pesanan[i] = menu.number + ". " + menu.name + " (" + menu.category + ") - " + jumlah + " x Rp."
                            + menu.price + " = Rp. " + subtotal;

                    // Hitung jumlah minuman
                    if (menu.category.equalsIgnoreCase("minuman")) {
                        minumanCount += jumlah;
                    }

                    break;
                }
            }

            // Jika menu tidak valid, maka ulangi iterasi saat ini
            if (!menuValid) {
                System.out.println("Menu tidak valid. Silakan pilih menu yang tersedia.");
                i--; // Decrement i to repeat the current iteration
            }
        }

        // Hitung diskon, pajak, dan total harga
        double diskon = 0;

        if (totalBiaya > 100000) {
            diskon = 0.1 * totalBiaya;
        }

        // Hitung total biaya setelah diskon
        double totalBiayaSetelahDiskon = totalBiaya - diskon;
        double pajak = 0.1 * totalBiayaSetelahDiskon;

        // Tampilkan struk pesanan
        System.out.println("\nStruk Pesanan:");
        for (String pesananItem : pesanan) {
            if (pesananItem != null) {
                System.out.println(pesananItem);
            }
        }

        // Tampilkan total biaya, diskon, pajak, dan total harga
        System.out.println("Total Biaya Pesanan: Rp. " + totalBiaya);

        // Tampilkan penawaran beli 1 gratis 1 minuman jika total biaya lebih dari Rp. 50000
        if (totalBiaya > 50000 && minumanCount > 0) {
            System.out.println("Anda dapat penawaran beli 1 gratis 1 minuman untuk pembelian di atas Rp. 50000.0.");
        }

        // Tampilkan diskon jika total biaya lebih dari Rp. 100000
        if (diskon > 0) {
            System.out.println("Diskon 10% telah diberikan karena total pesanan di atas Rp. 100000.0.");
            System.out.println("Potongan Diskon: - Rp. " + diskon);
            System.out.println("Total Biaya Pesanan Setelah Diskon: Rp. " + totalBiayaSetelahDiskon);
        }

        // Tampilkan pajak dan biaya pelayanan
        System.out.println("Pajak (10%): Rp. " + pajak);
        System.out.println("Biaya Pelayanan: Rp. 20000.0");
        double totalHarga = totalBiayaSetelahDiskon + pajak + 20000;
        System.out.println("Total: Rp. " + totalHarga);
    }

    // Buat method untuk mengelola menu
    static void manageMenu() {
        int choice;
        do {    // Looping untuk menampilkan menu pengelolaan
            System.out.println("\nMenu Pengelolaan:");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Switch case untuk memilih menu pengelolaan
            switch (choice) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    updateMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    System.out.println("Kembali ke Menu Utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 4);
    }

    // Buat method untuk menambahkan menu
    static void addMenu() {
        System.out.print("Masukkan nama menu baru: ");
        String newName = scanner.nextLine();

        // Cek apakah menu sudah ada
        System.out.print("Masukkan harga menu baru: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        // Cek apakah kategori menu valid
        System.out.print("Masukkan kategori menu baru (makanan/minuman): ");
        String newCategory = scanner.nextLine();

        // Tambahkan menu baru ke array of objects
        int newNumber = menus.length + 1; // Nomor baru akan menjadi satu lebih besar dari jumlah menu sebelumnya
        Menu newMenu = new Menu(newNumber, newName, newPrice, newCategory); // Buat object baru
        Menu[] newMenus = new Menu[menus.length + 1];
        System.arraycopy(menus, 0, newMenus, 0, menus.length);
        newMenus[menus.length] = newMenu;
        menus = newMenus;

        System.out.println("Menu baru berhasil ditambahkan!");
    }

    // Buat method untuk mengubah harga menu
    static void updateMenu() {
        displayMenu();

        // Cek apakah nomor menu valid
        System.out.print("Masukkan nomor menu yang ingin diubah: ");
        int menuNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Cek apakah harga menu valid
        if (menuNumber >= 1 && menuNumber <= menus.length) {
            System.out.println("Menu yang akan diubah: " + menus[menuNumber - 1].name);
            System.out.print("Masukkan harga baru: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            // Ubah harga menu
            menus[menuNumber - 1].price = newPrice;
            System.out.println("Harga menu berhasil diubah!");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    // Buat method untuk menghapus menu
    static void deleteMenu() {
        displayMenu();

        System.out.print("Masukkan nomor menu yang ingin dihapus: ");
        int menuNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (menuNumber >= 1 && menuNumber <= menus.length) {
            System.out.println("Menu yang akan dihapus: " + menus[menuNumber - 1].name);
            System.out.print("Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
            String confirmation = scanner.nextLine();

            // Hapus menu jika user mengkonfirmasi
            if (confirmation.equalsIgnoreCase("ya")) {
                Menu[] newMenus = new Menu[menus.length - 1];
                int newIndex = 0;

                // Copy semua menu ke array baru kecuali menu yang akan dihapus
                for (int i = 0; i < menus.length; i++) {
                    if (i != menuNumber - 1) {
                        newMenus[newIndex] = menus[i];
                        newMenus[newIndex].number = newIndex + 1; // Update nomor menu setelah penghapusan
                        newIndex++;
                    }
                }

                // Update array of objects menus
                menus = newMenus;

                // Tampilkan pesan berhasil
                System.out.println("Menu berhasil dihapus!");
            } else {
                System.out.println("Penghapusan menu dibatalkan.");
            }
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    // Buat method main untuk menjalankan program
    public static void main(String[] args) {
        int choice;
        do {    // Looping untuk menampilkan menu utama
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tampilkan Semua Menu");
            System.out.println("2. Pesan Menu");
            System.out.println("3. Kelola Menu");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Switch case untuk memilih menu utama
            switch (choice) {
                case 1:
                    displayAllMenu();
                    break;
                case 2:
                    takeOrder();
                    break;
                case 3:
                    manageMenu();
                    break;
                case 4:
                    System.out.println("Terima kasih! Sampai jumpa lagi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 4);  // Looping akan berhenti jika user memilih menu keluar
    }
}
