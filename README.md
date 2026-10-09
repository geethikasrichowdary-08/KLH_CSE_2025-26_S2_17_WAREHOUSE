# Warehouse Inventory System

A self-contained Java console project that applies the six advanced-algorithm
modules to warehouse inventory operations. The project uses only core Java
packages; it does not use `java.util` collections or library sorting/search
algorithms. The inventory itself is stored in a hand-grown array.

## Build and run

Requires Java 17 or later.

```text
javac WarehouseInventorySystem.java
java WarehouseInventorySystem
```

The program starts with sample products and keeps changes in memory for the
current run. At startup, it loads the `.txt` documents in `corpus/` (100 are
included); the product search menu lets you choose between live inventory and
corpus search.
Run the app from the project directory so it can resolve `corpus/`. Use option
`0` to exit. Run the included algorithm checks with:

```text
java WarehouseInventorySystem --self-test
```

## Warehouse mapping of the modules

| Module | Warehouse feature | Algorithms demonstrated |
| --- | --- | --- |
| 1. System/API design | Inventory CRUD and the menu/API read-through | Hand-built dynamic array; each menu action describes its query, input, and output |
| 2. String algorithms | Search SKU, name, and description text; compare descriptions | KMP, Z search, double rolling hash, Aho-Corasick multi-pattern matching, suffix array and Kasai LCP |
| 3. Dynamic programming | Typo-tolerant product search, restock-budget selection, pick route | Levenshtein and Damerau-Levenshtein distance; 0/1 knapsack; bitmask TSP |
| 4. Network flow | Allocate available units against outlet demands | Dinic max-flow on an integer-capacity source/product/outlet/sink network |
| 5. NP-hardness and approximation | Pick-task conflicts for products sharing an aisle | Maximal-matching vertex-cover 2-approximation; exact route search is explicitly capped at 12 products |
| 6. Randomised and parallel algorithms | Large batch-ID primality check, low-stock sampling, stock summaries | Miller-Rabin with randomized witnesses; reservoir sampling; threaded reduction and chunked prefix scan |

The 100 generated corpus documents use warehouse operations vocabulary across
receiving, cycle counts, restocking, safety, picking, shipping, returns, quality
control, replenishment, and seasonal planning. They are synthetic examples
intended for algorithm demonstrations, not operational inventory data.

## Design notes

- Exact text search and fuzzy matching are separate choices in the search menu.
  Fuzzy search uses edit distance against substrings of each product's SKU,
  name, and description, so a short query can match within a longer field.
- Description comparison finds the longest common substring using a practical
  doubling suffix-array construction and the linear-time Kasai LCP pass.
- Flow assigns units from each product stock pool to any outlet demand. It is a
  demonstration of capacity-constrained allocation, not a product-specific
  order fulfillment model.
- The pick-conflict graph connects products stored in the same aisle. A greedy
  maximal matching returns both endpoints of every selected conflict edge,
  yielding a vertex cover of size at most twice optimum.
- The route optimizer uses absolute aisle-number distance and returns to the
  first product's aisle. This is a small exact bitmask-DP demonstration, not a
  substitute for a real warehouse map or a large-scale route planner.
- Miller-Rabin reports "probably prime"; it does not claim a proof. Modular
  multiplication is implemented by repeated doubling to avoid overflow for
  positive signed `long` inputs.
- The parallel scan parallelizes within chunks, then applies chunk offsets in
  a second pass. The implementation is instructional; thread startup and
  hardware limits can outweigh any speedup on small inventories.
