# Add — Add a Java Solution File to the Project

Add one LeetCode solution as a standalone, compiling `.java` file under `src/`, and add its row to `README.md`. This project has no build tool — every file is self-contained (own `import`, own top-level class named after the file) so `javac -d out src/*.java` always compiles the whole folder together.

Every file also gets a `public static void main` that runs the solution against the problem's worked examples and prints the result next to what's expected — this is a personal reference project, not a submission target, so a runnable demo matters more than a bare solution.

This command is invoked two ways:
- **Standalone**, with the user pasting a LeetCode URL + Java solution directly.
- **Cross-project sync from the Obsidian vault's `/solve` command**, which passes already-resolved metadata (problem id, title, difficulty, source URL, approach name, output filename base, and the example Input/Output/Explanation blocks already parsed from the note) alongside the Java code — skip straight to Step 2 in that case.

---

## Step 1 — Collect inputs (standalone invocation only)

From the user's URL and pasted code, derive the same identifiers `/solve` uses in the Obsidian vault, so filenames stay identical across both projects:

- `id` — numeric problem id, no leading zeros (e.g. `2401`)
- `title` — Title Case (e.g. `Longest Nice Subarray`)
- `difficulty` — Easy / Medium / Hard
- `source` — full LeetCode URL
- `base` — `LT_<zero-padded-4-digit-id>_<Title_With_Underscores>` (e.g. `LT_2401_Longest_Nice_SubArray`) — must match the corresponding note's filename (minus `.md`) in the Obsidian vault at `Tech/DSA/Questions/`, so if unsure, check that vault folder for the exact existing spelling before inventing your own.
- **Examples** — the same worked examples that belong on the Obsidian note. If a note for this problem already exists at `Tech/DSA/Questions/<base>.md`, read its `## 🧪 Examples` section rather than asking the user again. Otherwise take them from the LeetCode page (WebFetch) or ask the user to paste them.

If the language pasted is not Java (e.g. Python), tell the user this project is Java-only and skip — do not write a file.

---

## Step 2 — Sanitize the class/file name

Java identifiers can't contain characters outside `[A-Za-z0-9_]`, and can't start with a digit. Since `base` always starts with `LT_`, only mid-name characters are a risk (e.g. a hyphen from a title like "K-th"). Replace any character not in `[A-Za-z0-9_]` with `_` to get the final `className`/filename stem.

---

## Step 3 — Determine the target filename

- If `src/<className>.java` does **not** exist → this is the first solution for this problem. Target file: `src/<className>.java`.
- If it **does** exist → a second approach is being added for an already-solved problem. Ask the user for a short approach label (e.g. `BinarySearch`, `Greedy`), then:
  1. Rename the existing file to `<className>_1_<inferred-label-for-existing-approach>.java` (infer a label from its content/header comment if possible; otherwise ask), updating the class name inside it to match.
  2. Write the new file as `<className>_2_<new-label>.java`.
  3. Update both rows in `README.md` (see Step 6) to list both files under the same problem row.

---

## Step 4 — Rename the class in the code

The pasted code will typically be `class Solution { ... }` (occasionally `class LRUCache { ... }` or similar for design problems). Replace **every whole-word occurrence** of the original class name with the new `className` — not just the declaration, since constructors share the class name (e.g. `class LRUCache` → also rename `public LRUCache(int capacity)`).

---

## Step 5 — Write the file

Content, in order:
1. `// <title> — <source>` (one line)
2. `import java.util.*;` (covers HashMap, ArrayList, Set, TreeMap, Deque, PriorityQueue, Collections, Comparator, etc. — everything these solutions have needed so far)
3. blank line
4. the renamed class body, copied exactly — never reformat or re-indent

Write to `src/<className>.java`.

---

## Step 5.5 — Add a `main` method with worked examples

Append a `public static void main(String[] args) { ... }` to the class, right after the last existing method and before the class's closing `}`. It must actually compile and run — don't stub it.

Shape it to the method under test:

- **Plain instance method returning a value** (`int`, `boolean`, `String`, `int[]`, `List<...>`, etc.): instantiate the class once (`ClassName sol = new ClassName();` — skip if the solution method is `static`), then for each example call it with the example's literal input, `System.out.println` the result, with a trailing `// expected: <output>` comment. Use `Arrays.toString(...)` for 1D arrays and `Arrays.deepToString(...)` for 2D arrays/grids — never rely on default array `toString()`.
- **In-place mutation** (e.g. a board solver like Sudoku, or a function that mutates the input array and returns nothing): print the input before the call (`Arrays.deepToString(board)`), call the method, then print it again after, labelled `// expected: <output>`.
- **Design / stateful class** (e.g. `LRUCache`): the example's Input is two parallel arrays — operation names and their argument lists — and Output is one result per operation (`null` for constructor/void calls). Walk them in lockstep: instantiate on the constructor call, and for each subsequent operation call the matching method and print its result (or just note `// void`) next to the expected value at that position.
- **Multiple examples**: run all of them in `main`, in the order they appear in the note, separated by a blank `System.out.println()` or a short label comment if it helps readability.

Keep it plain — no test framework, no assertions, no helper harness classes. It's meant to be run with `java <ClassName>` and eyeballed against the `// expected` comments.

---

## Step 6 — Update README.md

Open `README.md` and find the problem table (`| # | Problem | Difficulty | Solution(s) |`). Insert or update the row for this problem, keeping rows sorted **numerically ascending by problem id**:

```
| <id> | [<title>](<source>) | <difficulty> | [`<className>.java`](src/<className>.java) |
```

For multiple approaches, join file links with `<br>` in id order (`_1_...`, `_2_...`, ...), matching the existing pattern already used for problems like `LT_0005`, `LT_0300`, etc.

Update the `## Problems (N)` heading count if a new row was added (not on an approach-only update to an existing row).

---

## Step 7 — Verify it compiles

Run `javac -d out src/*.java` from the project root, confirm no errors, then delete the `out/` directory (it's gitignored but no need to leave build artifacts lying around).

---

## Step 8 — Confirm to the user

Report: file path written, class name, and the README row. If this was a cross-project sync from `/solve`, keep it to one line (e.g. `"Synced to leetcode-dsa: src/LT_2401_Longest_Nice_SubArray.java"`) — the vault-side confirmation already covers the rest.

Do not commit. Committing is a separate, explicit user request (see the vault's `CLAUDE.md` git-safety conventions — the same "only commit when asked" rule applies here).
