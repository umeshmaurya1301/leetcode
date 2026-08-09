# Add — Add a Java Solution File to the Project

Add one LeetCode solution as a standalone, compiling `.java` file under `src/<package>/`, and refresh `README.md`. This project has no build tool — every file is self-contained (own `package`, own `import`, one top-level class named after the file) so `javac -d out $(find src -name "*.java")` always compiles the whole tree together.

Every file also gets a `public static void main` that runs the solution against the problem's worked examples and prints the result next to what's expected — this is a personal reference project, not a submission target, so a runnable demo matters more than a bare solution.

**Every file is self-explaining**: a block-comment header carries the problem statement, its examples, and its constraints (Step 6), so a `.java` file read on its own — in the IDE, on GitHub — never needs the Obsidian note next to it.

**Solutions are filed by technique, and a solution that uses several techniques is copied into each one.** See Step 3. The copies are byte-identical apart from their `package` line, and `tools/gen_readme.py --check` enforces that.

This command is invoked two ways:
- **Standalone**, with the user pasting a LeetCode URL + Java solution directly.
- **Cross-project sync from the Obsidian vault's `/solve` command**, which passes already-resolved metadata (problem id, title, difficulty, source URL, output filename base, the problem description / examples / constraints already parsed for the note, and — per solution — its approach name and PascalCase label) alongside the Java code. Skip straight to Step 2 in that case, and run Steps 2–6.5 **once per solution**, since `/solve` may hand over several approaches for the same problem in one go.

---

## Step 1 — Collect inputs (standalone invocation only)

From the user's URL and pasted code, derive the same identifiers `/solve` uses in the Obsidian vault, so filenames stay identical across both projects:

- `id` — numeric problem id, no leading zeros (e.g. `2401`)
- `title` — Title Case (e.g. `Longest Nice Subarray`)
- `difficulty` — Easy / Medium / Hard
- `source` — full LeetCode URL
- `base` — `LT_<zero-padded-4-digit-id>_<Title_With_Underscores>` (e.g. `LT_2401_Longest_Nice_SubArray`) — must match the corresponding note's filename (minus `.md`) in the Obsidian vault at `Tech/DSA/Questions/`, so if unsure, check that vault folder for the exact existing spelling before inventing your own.
- **Description, examples, constraints** — the same problem text that belongs on the Obsidian note; needed for both the file header (Step 6) and `main` (Step 6.5). If a note for this problem already exists at `Tech/DSA/Questions/<base>.md`, read its `## 📝 Problem Description`, `## 🧪 Examples`, and `## ⚠️ Constraints` sections rather than asking the user again. Otherwise take them from the LeetCode page (WebFetch) or ask the user to paste them.

If the language pasted is not Java (e.g. Python), tell the user this project is Java-only and skip — do not write a file.

---

## Step 2 — Sanitize the class/file name

Java identifiers can't contain characters outside `[A-Za-z0-9_]`, and can't start with a digit. Since `base` always starts with `LT_`, only mid-name characters are a risk (e.g. a hyphen from a title like "K-th"). Replace any character not in `[A-Za-z0-9_]` with `_` to get the final `className`/filename stem.

---

## Step 3 — Pick the packages

Read the solution and list **every technique it actually uses** — not what the problem is tagged as on LeetCode, but what this code does. The existing packages, and what lands in each:

| Package | Lands here when the code… |
|---|---|
| `arrays` | scans/mutates an array in place, cyclic sort, counting grids |
| `backtracking` | builds a candidate, recurses, undoes the choice |
| `binarysearch` | halves a sorted range, a rotated range, or the answer space (incl. `TreeSet.ceiling`-style ordered lookup) |
| `bitmanipulation` | uses masks, XOR/OR/AND tricks, powers of two |
| `design` | implements a stateful class against an operation contract |
| `dynamicprogramming` | memoizes or tabulates overlapping subproblems |
| `graphs` | builds an adjacency list, DFS/BFS |
| `greedy` | takes the locally best choice |
| `hashing` | uses HashMap / HashSet / a frequency key for O(1) lookup |
| `linkedlist` | wires nodes, does pointer surgery |
| `math` | modular arithmetic, digits, powers, coordinate geometry |
| `prefixsum` | running sums, difference arrays, remainder buckets |
| `slidingwindow` | two indices sweeping a contiguous range, expand then shrink |
| `sorting` | sorts with a custom comparator, or sorts then scans |
| `stackqueue` | uses a stack, queue, deque, or monotonic stack |
| `strings` | scans substrings, counts characters, builds strings |
| `trees` | recurses over a rooted structure |
| `twopointers` | two independent pointers converging, or expanding from a center |

Rules:
- **Two to three packages is typical. One is fine. More than three usually means you're tagging the problem rather than the code** — drop the incidental ones.
- Judge each approach file separately. LIS files `_1_Memoization` and `_2_Tabulation` go to `dynamicprogramming`; `_3_BinarySearchPatience` goes to `binarysearch` + `dynamicprogramming`.
- If nothing fits, add a new package: create `src/<newpkg>/`, and add it to `PACKAGES` in `tools/gen_readme.py` with a display name and a one-line description. The generator errors out if a `src/` folder has no `PACKAGES` entry, or vice versa.

State the chosen packages to the user before writing.

---

## Step 4 — Determine the target filename

Check whether the problem already has files anywhere under `src/` (search all packages, not just one):

- **No existing file, one solution being added** → target filename: `<className>.java`.
- **No existing file, several solutions being added at once** (the usual `/solve` multi-approach case) → number them from the start: `<className>_1_<Label1>.java`, `<className>_2_<Label2>.java`, … in the order the approaches are meant to be read (naive first, optimal last), matching the `Solution N` order in the Obsidian note.
- **Already exists** → a further approach is being added. Use the label `/solve` supplied, or — standalone — ask the user for a short PascalCase approach label (e.g. `BinarySearch`, `Greedy`), then:
  1. Rename **every copy** of the existing file to `<className>_1_<inferred-label>.java` (infer the label from its content/header comment if possible; otherwise ask), updating the class name inside each copy to match.
  2. Write the new file as `<className>_<next-free-number>_<new-label>.java` into the packages chosen in Step 3 — which may differ from where the earlier approaches live.

---

## Step 5 — Rename the class in the code

The pasted code will typically be `class Solution { ... }` (occasionally `class LRUCache { ... }` or similar for design problems). Replace **every whole-word occurrence** of the original class name with the new `className` — not just the declaration, since constructors share the class name (e.g. `class LRUCache` → also rename `public LRUCache(int capacity)`).

Keep the class package-private (`class Foo`, not `public class Foo`) — that's the existing convention and `java -cp out <pkg>.<Class>` still runs it.

---

## Step 6 — Write the file into every chosen package

Content, in order:
1. the **problem header block comment** (below)
2. `package <pkg>;`
3. blank line
4. `import java.util.*;` (covers HashMap, ArrayList, Set, TreeMap, Deque, PriorityQueue, Collections, Comparator, etc. — everything these solutions have needed so far)
5. blank line
6. the renamed class body, copied exactly — never reformat or re-indent

### The header block

Every file states the problem it solves. Order: title line, URL, description, examples, constraints, approach. Keep the description to the problem's own wording, trimmed of LeetCode boilerplate ("Follow up:", images, ad copy); 2–8 lines is right. Include **every** example from the problem, and the constraints as given.

```java
/*
 * 2401. Longest Nice Subarray — Medium
 * https://leetcode.com/problems/longest-nice-subarray/
 *
 * You are given an array nums consisting of positive integers. We call a subarray
 * of nums nice if the bitwise AND of every pair of elements that are in different
 * positions in the subarray is equal to 0.
 * Return the length of the longest nice subarray.
 *
 * Example 1:
 *   Input:  nums = [1,3,8,48,10]
 *   Output: 3
 *   Explanation: The longest nice subarray is [3,8,48] — 3 AND 8 = 0,
 *                3 AND 48 = 0, 8 AND 48 = 0.
 *
 * Example 2:
 *   Input:  nums = [3,1,5,11,13]
 *   Output: 1
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^9
 *
 * Approach: Sliding Window + Bitmask — O(n) time, O(1) space.
 */
package slidingwindow;

import java.util.*;

class LT_2401_Longest_Nice_SubArray {
```

Rules for the header:
- Use `/* … */` with a leading ` * ` on every line — not `//`. If the problem text itself contains `*/`, rewrite that fragment (e.g. in backticks or spelled out) rather than breaking the comment.
- The `Approach:` line describes **this file's** approach, so on a multi-approach problem each file gets its own line (`Approach 1: Brute force …`, `Approach 2: Sliding window …`) while the description/examples/constraints stay identical across them.
- Write the header once per approach, then copy the finished file into its packages — don't retype it per package.
- Every file under `src/` already carries this header — the older one-line `// <title> — <source>` form is gone, so match the block format above rather than reviving it. Editing a header means editing **all** of that file's copies together, or `--check` will fail.
- Description, examples, and constraints are lifted from the problem's Obsidian note (`Tech/DSA/Questions/<base>.md`) — its `📝 Problem Description`, `🧪 Examples`, and `⚠️ Constraints` sections, de-markdowned (callout markers, `**bold**`, and backticks stripped). Take them from the note rather than re-typing from LeetCode, so the two stay in sync.

Write one copy to `src/<pkg>/<className>.java` for each package from Step 3. **The `package` line is the only line that may differ between copies** — everything else must be byte-identical, since `tools/gen_readme.py --check` compares them and fails on any other difference.

---

## Step 6.5 — Add a `main` method with worked examples

Append a `public static void main(String[] args) { ... }` to the class, right after the last existing method and before the class's closing `}`. It must actually compile and run — don't stub it. Write it **once**, then copy the finished file — don't hand-write `main` separately per package copy.

Shape it to the method under test:

- **Plain instance method returning a value** (`int`, `boolean`, `String`, `int[]`, `List<...>`, etc.): instantiate the class once (`ClassName sol = new ClassName();` — skip if the solution method is `static`), then for each example call it with the example's literal input, `System.out.println` the result, with a trailing `// expected: <output>` comment. Use `Arrays.toString(...)` for 1D arrays and `Arrays.deepToString(...)` for 2D arrays/grids — never rely on default array `toString()`.
- **In-place mutation** (e.g. a board solver like Sudoku, or a function that mutates the input array and returns nothing): print the input before the call (`Arrays.deepToString(board)`), call the method, then print it again after, labelled `// expected: <output>`.
- **Design / stateful class** (e.g. `LRUCache`): the example's Input is two parallel arrays — operation names and their argument lists — and Output is one result per operation (`null` for constructor/void calls). Walk them in lockstep: instantiate on the constructor call, and for each subsequent operation call the matching method and print its result (or just note `// void`) next to the expected value at that position.
- **Multiple examples**: run all of them in `main`, in the order they appear in the note, separated by a blank `System.out.println()` or a short label comment if it helps readability.

Keep it plain — no test framework, no assertions, no helper harness classes. It's meant to be run with `java -cp out <pkg>.<ClassName>` and eyeballed against the `// expected` comments.

---

## Step 7 — Regenerate README.md

`README.md` is **generated** — do not hand-edit its tables.

1. If this problem id is new, add one row to the `PROBLEMS` dict in `tools/gen_readme.py`, keeping it sorted by id:
   ```python
   2401: ("Longest Nice Subarray", "Medium", "https://leetcode.com/problems/longest-nice-subarray/"),
   ```
   (An approach-only addition to an already-listed problem needs no new row.)
2. Run it:
   ```bash
   python tools/gen_readme.py
   ```

It rebuilds the packages table, the per-topic tables, the by-number index, and the multi-homed-files table from whatever is on disk — and refuses to write if any duplicate copies have drifted, a package is undescribed, or a problem id has files but no `PROBLEMS` row. If it fails, fix the cause rather than editing `README.md` by hand.

---

## Step 8 — Verify it compiles

From the project root:

```bash
javac -d out $(find src -name "*.java")
java -cp out <pkg>.<className>     # sanity-check the worked examples print as expected
```

Confirm no errors, then delete the `out/` directory (it's gitignored but no need to leave build artifacts lying around).

---

## Step 9 — Confirm to the user

Report: every file path written (all package copies), the class name, and the packages chosen. If this was a cross-project sync from `/solve`, keep it to one line (e.g. `"Synced to leetcode-dsa: slidingwindow + bitmanipulation / LT_2401_Longest_Nice_SubArray.java"`) — the vault-side confirmation already covers the rest.

Do not commit. Committing is a separate, explicit user request (see the vault's `CLAUDE.md` git-safety conventions — the same "only commit when asked" rule applies here).
