import java.util.*;

public class WarehouseInventory {

    static Scanner sc = new Scanner(System.in);

    // Product list
    static ArrayList<String> products = new ArrayList<>();

    // ============================================================
    // KMP ALGORITHM
    // ============================================================

    static int[] computeLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } 
            else {

                if (length != 0) {
                    length = lps[length - 1];
                } 
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    static boolean KMP(String text, String pattern) {

        if (pattern.length() == 0)
            return true;

        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return true;
                }
            } 
            else {

                if (j != 0) {
                    j = lps[j - 1];
                } 
                else {
                    i++;
                }
            }
        }

        return false;
    }

    // ============================================================
    // Z FUNCTION
    // ============================================================

    static int[] ZFunction(String s) {

        int n = s.length();

        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {

                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    static boolean ZSearch(String text, String pattern) {

        String combined = pattern + "$" + text;

        int[] z = ZFunction(combined);

        for (int i = 0; i < z.length; i++) {

            if (z[i] == pattern.length()) {
                return true;
            }
        }

        return false;
    }

    // ============================================================
    // RABIN-KARP ALGORITHM
    // ============================================================

    static boolean RabinKarp(String text, String pattern) {

        if (pattern.length() > text.length())
            return false;

        int prime = 101;
        int base = 256;

        int m = pattern.length();
        int n = text.length();

        long patternHash = 0;
        long textHash = 0;

        long h = 1;

        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }

        for (int i = 0; i < m; i++) {

            patternHash =
                    (base * patternHash + pattern.charAt(i)) % prime;

            textHash =
                    (base * textHash + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match)
                    return true;
            }

            if (i < n - m) {

                textHash =
                        (base *
                        (textHash - text.charAt(i) * h)
                        + text.charAt(i + m)) % prime;

                if (textHash < 0)
                    textHash += prime;
            }
        }

        return false;
    }

    // ============================================================
    // SUFFIX ARRAY
    // ============================================================

    static int[] buildSuffixArray(String text) {

        int n = text.length();

        Integer[] suffixArray = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = i;
        }

        Arrays.sort(suffixArray, (a, b) -> {

            return text.substring(a)
                       .compareTo(text.substring(b));
        });

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = suffixArray[i];
        }

        return result;
    }

    // ============================================================
    // LCP ARRAY
    // ============================================================

    static int[] buildLCP(String text, int[] suffixArray) {

        int n = text.length();

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int[] lcp = new int[n];

        int k = 0;

        for (int i = 0; i < n; i++) {

            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = suffixArray[rank[i] + 1];

            while (i + k < n &&
                   j + k < n &&
                   text.charAt(i + k) == text.charAt(j + k)) {

                k++;
            }

            lcp[rank[i]] = k;

            if (k > 0)
                k--;
        }

        return lcp;
    }

    // ============================================================
    // DISPLAY SUFFIX ARRAY
    // ============================================================

    static void displaySuffixArray(String text) {

        int[] suffixArray = buildSuffixArray(text);

        int[] lcp = buildLCP(text, suffixArray);

        System.out.println("\n===== SUFFIX ARRAY =====");

        System.out.println("Index\tSuffix\t\tLCP");

        for (int i = 0; i < suffixArray.length; i++) {

            int index = suffixArray[i];

            String suffix = text.substring(index);

            System.out.println(
                    index + "\t" +
                    suffix + "\t\t" +
                    lcp[i]
            );
        }
    }

    // ============================================================
    // ADD PRODUCT
    // ============================================================

    static void addProduct() {

        System.out.print("Enter product name: ");

        String product = sc.nextLine();

        products.add(product);

        System.out.println("Product added successfully.");
    }

    // ============================================================
    // DISPLAY PRODUCTS
    // ============================================================

    static void displayProducts() {

        System.out.println("\n===== PRODUCTS =====");

        if (products.isEmpty()) {

            System.out.println("No products available.");

            return;
        }

        for (int i = 0; i < products.size(); i++) {

            System.out.println(
                    (i + 1) + ". " + products.get(i)
            );
        }
    }

    // ============================================================
    // KMP SEARCH
    // ============================================================

    static void searchUsingKMP() {

        System.out.print("Enter product pattern: ");

        String pattern = sc.nextLine();

        boolean found = false;

        for (String product : products) {

            if (KMP(product.toLowerCase(),
                    pattern.toLowerCase())) {

                System.out.println(
                        "Found: " + product
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No product found.");
        }
    }

    // ============================================================
    // Z FUNCTION SEARCH
    // ============================================================

    static void searchUsingZ() {

        System.out.print("Enter product pattern: ");

        String pattern = sc.nextLine();

        boolean found = false;

        for (String product : products) {

            if (ZSearch(product.toLowerCase(),
                        pattern.toLowerCase())) {

                System.out.println(
                        "Found: " + product
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No product found.");
        }
    }

    // ============================================================
    // RABIN-KARP SEARCH
    // ============================================================

    static void searchUsingRabinKarp() {

        System.out.print("Enter product pattern: ");

        String pattern = sc.nextLine();

        boolean found = false;

        for (String product : products) {

            if (RabinKarp(product.toLowerCase(),
                          pattern.toLowerCase())) {

                System.out.println(
                        "Found: " + product
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No product found.");
        }
    }

    // ============================================================
    // SUFFIX ARRAY
    // ============================================================

    static void suffixArrayOperation() {

        System.out.print(
                "Enter text for suffix array: "
        );

        String text = sc.nextLine();

        displaySuffixArray(text);
    }

    // ============================================================
    // MAIN MENU
    // ============================================================

    public static void main(String[] args) {

        // Sample products

        products.add("Laptop");
        products.add("Laptop Bag");
        products.add("Wireless Mouse");
        products.add("Mechanical Keyboard");
        products.add("USB Cable");
        products.add("Power Bank");
        products.add("Bluetooth Speaker");
        products.add("HD Monitor");

        while (true) {

            System.out.println(
                    "\n===================================="
            );

            System.out.println(
                    "     WAREHOUSE INVENTORY SYSTEM"
            );

            System.out.println(
                    "===================================="
            );

            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search using KMP");
            System.out.println("4. Search using Z-Function");
            System.out.println("5. Search using Rabin-Karp");
            System.out.println("6. Suffix Array and LCP");
            System.out.println("7. Exit");

            System.out.print(
                    "Enter your choice: "
            );

            int choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    displayProducts();
                    break;

                case 3:
                    searchUsingKMP();
                    break;

                case 4:
                    searchUsingZ();
                    break;

                case 5:
                    searchUsingRabinKarp();
                    break;

                case 6:
                    suffixArrayOperation();
                    break;

                case 7:

                    System.out.println(
                            "Thank you for using Warehouse Inventory System."
                    );

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }
}