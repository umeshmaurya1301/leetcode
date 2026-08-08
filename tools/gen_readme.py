# -*- coding: utf-8 -*-
"""Regenerate README.md from the files on disk, and verify duplicate copies agree.

Solutions are filed under every technique they use, as byte-identical copies in
different packages (see README "Layout"). Two things follow from that:

  * The README tables are derived data -- never hand-edit them. Add the problem's
    metadata to PROBLEMS below, drop the .java copies into their packages, and
    re-run this script.
  * Copies can drift. `--check` re-reads every copy of every file and fails if two
    copies of the same solution differ anywhere except their `package` line.

Usage:
    python tools/gen_readme.py            # check copies, then rewrite README.md
    python tools/gen_readme.py --check    # check copies only, write nothing
"""

import collections
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, "src")

# Problem id -> (title, difficulty, leetcode url).
# Add a row here when you add a problem; keep it sorted by id.
PROBLEMS = {
3: ("Longest Substring Without Repeating Characters", "Medium", "https://leetcode.com/problems/longest-substring-without-repeating-characters/"),
4: ("Median of Two Sorted Arrays", "Hard", "https://leetcode.com/problems/median-of-two-sorted-arrays/"),
5: ("Longest Palindromic Substring", "Medium", "https://leetcode.com/problems/longest-palindromic-substring/"),
17: ("Letter Combinations of a Phone Number", "Medium", "https://leetcode.com/problems/letter-combinations-of-a-phone-number/"),
30: ("Substring with Concatenation of All Words", "Hard", "https://leetcode.com/problems/substring-with-concatenation-of-all-words/"),
33: ("Search in Rotated Sorted Array", "Medium", "https://leetcode.com/problems/search-in-rotated-sorted-array/"),
36: ("Valid Sudoku", "Medium", "https://leetcode.com/problems/valid-sudoku/"),
37: ("Sudoku Solver", "Hard", "https://leetcode.com/problems/sudoku-solver/"),
41: ("First Missing Positive", "Hard", "https://leetcode.com/problems/first-missing-positive/description/"),
42: ("Trapping Rain Water", "Hard", "https://leetcode.com/problems/trapping-rain-water/description/"),
43: ("Multiply Strings", "Medium", "https://leetcode.com/problems/multiply-strings/"),
49: ("Group Anagrams", "Medium", "https://leetcode.com/problems/group-anagrams/description/"),
51: ("N-Queens", "Hard", "https://leetcode.com/problems/n-queens/"),
76: ("Minimum Window Substring", "Hard", "https://leetcode.com/problems/minimum-window-substring/"),
121: ("Best Time to Buy and Sell Stock", "Easy", "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/"),
128: ("Longest Consecutive Sequence", "Medium", "https://leetcode.com/problems/longest-consecutive-sequence/description/"),
146: ("LRU Cache", "Medium", "https://leetcode.com/problems/lru-cache/"),
152: ("Maximum Product SubArray", "Medium", "https://leetcode.com/problems/maximum-product-subarray/"),
162: ("Find Peak Element", "Medium", "https://leetcode.com/problems/find-peak-element/"),
220: ("Contains Duplicate III", "Hard", "https://leetcode.com/problems/contains-duplicate-iii/description/"),
300: ("Longest Increasing Subsequence", "Medium", "https://leetcode.com/problems/longest-increasing-subsequence/"),
322: ("Coin Change", "Medium", "https://leetcode.com/problems/coin-change/"),
354: ("Russian Doll Envelopes", "Hard", "https://leetcode.com/problems/russian-doll-envelopes/"),
377: ("Combination Sum IV", "Medium", "https://leetcode.com/problems/combination-sum-iv/description/"),
435: ("Non-overlapping Intervals", "Medium", "https://leetcode.com/problems/non-overlapping-intervals/"),
454: ("4Sum II", "Medium", "https://leetcode.com/problems/4sum-ii/description/"),
474: ("Ones and Zeroes", "Medium", "https://leetcode.com/problems/ones-and-zeroes/"),
518: ("Coin Change II", "Medium", "https://leetcode.com/problems/coin-change-ii/description/"),
523: ("Continuous Subarray Sum", "Medium", "https://leetcode.com/problems/continuous-subarray-sum/"),
713: ("Subarray Product Less Than K", "Medium", "https://leetcode.com/problems/subarray-product-less-than-k/"),
740: ("Delete And Earn", "Medium", "https://leetcode.com/problems/delete-and-earn/"),
904: ("Fruit Into Baskets", "Medium", "https://leetcode.com/problems/fruit-into-baskets/"),
974: ("Subarray Sums Divisible by K", "Medium", "https://leetcode.com/problems/subarray-sums-divisible-by-k/"),
992: ("Subarrays with K Different Integers", "Hard", "https://leetcode.com/problems/subarrays-with-k-different-integers/"),
1011: ("Capacity To Ship Packages Within D Days", "Medium", "https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/"),
1695: ("Maximum Erasure Value", "Medium", "https://leetcode.com/problems/maximum-erasure-value/"),
1711: ("Count Good Meals", "Medium", "https://leetcode.com/problems/count-good-meals/"),
2401: ("Longest Nice Subarray", "Medium", "https://leetcode.com/problems/longest-nice-subarray/"),
2981: ("Find Longest Special Substring That Occurs Thrice I", "Medium", "https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/"),
2982: ("Find Longest Special Substring That Occurs Thrice II", "Medium", "https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/"),
3932: ("Count K-th Roots in a Range", "Medium", "https://leetcode.com/problems/count-k-th-roots-in-a-range/"),
3964: ("Minimum Lights To Illuminate A Road", "Medium", "https://leetcode.com/problems/minimum-lights-to-illuminate-a-road/"),
3965: ("Finish Time Of Tasks I", "Medium", "https://leetcode.com/problems/finish-time-of-tasks-i/"),
3968: ("Maximum Manhattan Distance After All Moves", "Easy", "https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves/"),
}

# Package -> (display name, what belongs here). A package listed here but absent
# from src/ is reported as an error; a package in src/ but absent here likewise.
PACKAGES = {
    "arrays":             ("Arrays", "In-place tricks, cyclic sort, scanning, counting grids."),
    "backtracking":       ("Backtracking", "Build a candidate, recurse, undo the choice."),
    "binarysearch":       ("Binary Search", "Search a sorted range, a rotated range, or the answer space."),
    "bitmanipulation":    ("Bit Manipulation", "Masks, XOR/OR/AND tricks, powers of two."),
    "design":             ("Design", "Stateful classes built to an operation contract."),
    "dynamicprogramming": ("Dynamic Programming", "Memoization and tabulation over overlapping subproblems."),
    "graphs":             ("Graphs", "Adjacency lists, DFS/BFS traversal."),
    "greedy":             ("Greedy", "Take the locally best choice and prove it stays optimal."),
    "hashing":            ("Hashing & Counting", "HashMap / HashSet / frequency keys for O(1) lookup."),
    "linkedlist":         ("Linked List", "Node wiring, pointer surgery, doubly linked lists."),
    "math":               ("Math", "Modular arithmetic, digits, powers, coordinate geometry."),
    "prefixsum":          ("Prefix Sum", "Running sums, difference arrays, remainder buckets."),
    "slidingwindow":      ("Sliding Window", "Two indices sweeping a contiguous range, expand then shrink."),
    "sorting":            ("Sorting", "Custom comparators and sort-then-scan."),
    "stackqueue":         ("Stack & Queue", "LIFO/FIFO structures, monotonic stacks, deques."),
    "strings":            ("Strings", "Substring scanning, character frequency, string building."),
    "trees":              ("Trees", "Rooted structures, recursion over children."),
    "twopointers":        ("Two Pointers", "Independent pointers converging or expanding from a center."),
}

PACKAGE_RE = re.compile(r"^package\s+\w+;\s*$")


def scan():
    """Return (placements: base -> [pkg], pkg_files: pkg -> [base])."""
    placements = collections.defaultdict(list)
    pkg_files = collections.defaultdict(list)
    for pkg in sorted(os.listdir(SRC)):
        d = os.path.join(SRC, pkg)
        if not os.path.isdir(d):
            continue
        for fn in sorted(os.listdir(d)):
            if fn.endswith(".java"):
                placements[fn[:-5]].append(pkg)
                pkg_files[pkg].append(fn[:-5])
    return placements, pkg_files


def problem_id(base):
    return int(re.match(r"LT_(\d+)_", base).group(1))


def approach_label(base):
    """`..._3_BinarySearchPatience` -> `Binary Search Patience`; '' if single-approach."""
    m = re.search(r"_(\d)_([A-Za-z]+)$", base)
    if not m:
        return ""
    return re.sub(r"(?<!^)(?=[A-Z])", " ", m.group(2))


def body_without_package(path):
    """File contents minus its `package x;` line, for comparing copies."""
    with open(path, encoding="utf-8") as f:
        return [ln for ln in f.read().splitlines() if not PACKAGE_RE.match(ln)]


def check(placements, pkg_files):
    """Report structural problems. Returns a list of error strings."""
    errors = []

    on_disk = set(pkg_files) | {
        d for d in os.listdir(SRC) if os.path.isdir(os.path.join(SRC, d))
    }
    for pkg in sorted(on_disk - set(PACKAGES)):
        errors.append(f"package src/{pkg}/ exists but is not described in PACKAGES")
    for pkg in sorted(set(PACKAGES) - on_disk):
        errors.append(f"PACKAGES describes '{pkg}' but src/{pkg}/ does not exist")

    for pid in sorted({problem_id(b) for b in placements} - set(PROBLEMS)):
        errors.append(f"problem {pid} has files on disk but no PROBLEMS entry")

    # every copy of a solution must be identical apart from its package line
    for base, pkgs in sorted(placements.items()):
        if len(pkgs) < 2:
            continue
        ref_pkg = pkgs[0]
        ref = body_without_package(os.path.join(SRC, ref_pkg, base + ".java"))
        for pkg in pkgs[1:]:
            other = body_without_package(os.path.join(SRC, pkg, base + ".java"))
            if other != ref:
                errors.append(
                    f"copies differ: src/{ref_pkg}/{base}.java vs src/{pkg}/{base}.java "
                    f"-- edit one, edit them all"
                )

    # each copy must declare the package it sits in
    for base, pkgs in sorted(placements.items()):
        for pkg in pkgs:
            path = os.path.join(SRC, pkg, base + ".java")
            with open(path, encoding="utf-8") as f:
                decl = [ln for ln in f.read().splitlines() if PACKAGE_RE.match(ln)]
            if decl != [f"package {pkg};"]:
                errors.append(
                    f"src/{pkg}/{base}.java declares {decl or ['(none)']}, expected 'package {pkg};'"
                )

    return errors


def render(placements, pkg_files):
    L = []
    w = L.append
    n_files = sum(len(v) for v in pkg_files.values())

    w("# leetcode-dsa")
    w("")
    w("Java solutions extracted from my [Obsidian DSA notes](../../Notes/Obsidian-Notes/Tech/DSA/Questions) "
      "— mirrors the problems tracked in `Tech/DSA/Roadmap.md`.")
    w("")
    w(f"**{len(PROBLEMS)} problems · {n_files} files across {len(PACKAGES)} topic packages.**")
    w("")
    w("> Everything below the *Layout* section is generated by "
      "[`tools/gen_readme.py`](tools/gen_readme.py) — don't hand-edit the tables.")
    w("")
    w("## Layout")
    w("")
    w("Every `.java` file under `src/` sits in a lowercase package named after its topic, is self-contained "
      "(own `package`, own `import java.util.*;`, one top-level class named after the file), and carries a "
      "`main` that runs the problem's worked examples against `// expected:` comments.")
    w("")
    w("**A solution is filed under every technique it uses.** A problem solved with a sliding window over a "
      "hash map lives in *both* `slidingwindow/` and `hashing/` as byte-identical copies — different package, "
      "different fully-qualified name, so both compile side by side. Browsing one topic folder therefore shows "
      "every solution that technique applies to.")
    w("")
    w("The trade-off is real: **editing a solution means editing all of its copies.** Each topic table has an "
      "*Also in* column pointing at the siblings, [the last table](#files-with-copies-in-more-than-one-package) "
      "lists every multi-homed file, and `python tools/gen_readme.py --check` fails loudly if two copies ever "
      "drift apart.")
    w("")
    w("Problems with more than one documented approach (e.g. brute force vs. optimal) get one file per approach, "
      "suffixed `_1_…`, `_2_…`, and each approach file is filed by *its own* technique — so LIS lands its two DP "
      "files in `dynamicprogramming/` and its patience-sorting file in `binarysearch/`.")
    w("")
    w("## Build & run")
    w("")
    w("No build tool. Compile everything at once, then run any solution by its fully-qualified name:")
    w("")
    w("```bash")
    w("# compile all packages")
    w('javac -d out $(find src -name "*.java")')
    w("")
    w("# run one solution's worked examples")
    w("java -cp out slidingwindow.LT_0076_Minimum_Window_Substring")
    w("java -cp out dynamicprogramming.LT_0322_Coin_Change")
    w("")
    w("# verify no duplicate copies have drifted, and refresh this README")
    w("python tools/gen_readme.py")
    w("```")
    w("")
    w("On PowerShell:")
    w("")
    w("```powershell")
    w("javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)")
    w("java -cp out slidingwindow.LT_0076_Minimum_Window_Substring")
    w("```")
    w("")

    w("## Packages")
    w("")
    w("| Package | Topic | Files | What lands here |")
    w("|---|---|---|---|")
    for pkg in sorted(PACKAGES):
        name, desc = PACKAGES[pkg]
        n = len(pkg_files.get(pkg, []))
        if n == 0:
            desc += " _(empty for now)_"
        w(f"| [`{pkg}`](src/{pkg}) | {name} | {n} | {desc} |")
    w("")

    w("## Solutions by topic")
    w("")
    for pkg in sorted(PACKAGES):
        name, _ = PACKAGES[pkg]
        files = pkg_files.get(pkg, [])
        w(f"### {name} — `{pkg}` ({len(files)})")
        w("")
        if not files:
            w("_No solutions yet._")
            w("")
            continue
        w("| # | Problem | Difficulty | Approach | File | Also in |")
        w("|---|---|---|---|---|---|")
        for base in sorted(files, key=lambda b: (problem_id(b), b)):
            i = problem_id(base)
            title, diff, url = PROBLEMS[i]
            others = [p for p in placements[base] if p != pkg]
            w(f"| {i} | [{title}]({url}) | {diff} | {approach_label(base) or '—'} | "
              f"[`{base}.java`](src/{pkg}/{base}.java) | "
              f"{', '.join(f'`{p}`' for p in others) if others else '—'} |")
        w("")

    w("## All problems by number")
    w("")
    w("| # | Problem | Difficulty | Approach files | Packages |")
    w("|---|---|---|---|---|")
    by_id = collections.defaultdict(list)
    for base in placements:
        by_id[problem_id(base)].append(base)
    for i in sorted(by_id):
        title, diff, url = PROBLEMS[i]
        bases = sorted(by_id[i])
        files = "<br>".join(f"[`{b}.java`](src/{placements[b][0]}/{b}.java)" for b in bases)
        pkgs = sorted({p for b in bases for p in placements[b]})
        w(f"| {i} | [{title}]({url}) | {diff} | {files} | {' '.join('`' + p + '`' for p in pkgs)} |")
    w("")

    multi = sorted(
        ((b, ps) for b, ps in placements.items() if len(ps) > 1),
        key=lambda x: (-len(x[1]), problem_id(x[0])),
    )
    w("## Files with copies in more than one package")
    w("")
    w("Edit one, edit them all — then run `python tools/gen_readme.py --check`. Most-copied first.")
    w("")
    w("| File | Copies | Packages |")
    w("|---|---|---|")
    for b, ps in multi:
        w(f"| `{b}.java` | {len(ps)} | {' '.join('`src/' + p + '/`' for p in sorted(ps))} |")
    w("")

    return "\n".join(L) + "\n", len(multi)


def main():
    check_only = "--check" in sys.argv
    placements, pkg_files = scan()

    errors = check(placements, pkg_files)
    if errors:
        print(f"FAIL — {len(errors)} problem(s):")
        for e in errors:
            print(f"  - {e}")
        return 1

    n_files = sum(len(v) for v in pkg_files.values())
    if check_only:
        print(f"OK — {len(PROBLEMS)} problems, {n_files} files, all duplicate copies agree.")
        return 0

    text, n_multi = render(placements, pkg_files)
    with open(os.path.join(ROOT, "README.md"), "w", encoding="utf-8", newline="\n") as f:
        f.write(text)
    print(f"wrote README.md — {len(PROBLEMS)} problems, {n_files} files, "
          f"{n_multi} multi-homed, all copies agree.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
