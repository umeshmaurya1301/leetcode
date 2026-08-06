# Add — Add a Java Solution File to the Project

Add one LeetCode solution as a standalone, compiling `.java` file under `src/`, and add its row to `README.md`. This project has no build tool — every file is self-contained (own `import`, own top-level class named after the file) so `javac -d out src/*.java` always compiles the whole folder together.

This command is invoked two ways:
- **Standalone**, with the user pasting a LeetCode URL + Java solution directly.
- **Cross-project sync from the Obsidian vault's `/solve` command**, which passes already-resolved metadata (problem id, title, difficulty, source URL, approach name, output filename base) alongside the Java code — skip straight to Step 2 in that case.

---

## Step 1 — Collect inputs (standalone invocation only)

From the user's URL and pasted code, derive the same identifiers `/solve` uses in the Obsidian vault, so filenames stay identical across both projects:

- `id` — numeric problem id, no leading zeros (e.g. `2401`)
- `title` — Title Case (e.g. `Longest Nice Subarray`)
- `difficulty` — Easy / Medium / Hard
- `source` — full LeetCode URL
- `base` — `LT_<zero-padded-4-digit-id>_<Title_With_Underscores>` (e.g. `LT_2401_Longest_Nice_SubArray`) — must match the corresponding note's filename (minus `.md`) in the Obsidian vault at `Tech/DSA/Questions/`, so if unsure, check that vault folder for the exact existing spelling before inventing your own.

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
