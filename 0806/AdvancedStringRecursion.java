import java.util.*;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        String input = "abc";

        System.out.println("1. 所有排列組合：");
        generatePermutations("", input);

        System.out.println("\n2. 字串匹配：");
        String haystack = "mississippi";
        String needle = "issip";
        int index = recursiveStrStr(haystack, needle, 0);
        System.out.println("第一次出現位置：" + index);

        System.out.println("\n3. 移除重複字符：");
        String withDup = "abacbd";
        String noDup = removeDuplicates(withDup, 0, new HashSet<>(), new StringBuilder());
        System.out.println("移除後：" + noDup);

        System.out.println("\n4. 所有子字串組合：");
        String str = "abc";
        generateAllSubstrings("", str);
    }

    // 1. 遞迴產生字串所有排列組合
    public static void generatePermutations(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            generatePermutations(
                prefix + remaining.charAt(i),
                remaining.substring(0, i) + remaining.substring(i + 1)
            );
        }
    }

    // 2. 遞迴實作字串匹配 (類似 strStr)
    public static int recursiveStrStr(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) return -1;
        if (matchHere(text, pattern, index, 0)) return index;
        return recursiveStrStr(text, pattern, index + 1);
    }

    private static boolean matchHere(String text, String pattern, int ti, int pi) {
        if (pi == pattern.length()) return true;
        if (ti >= text.length()) return false;
        if (text.charAt(ti) != pattern.charAt(pi)) return false;
        return matchHere(text, pattern, ti + 1, pi + 1);
    }

    // 3. 遞迴移除重複字符
    public static String removeDuplicates(String str, int index, Set<Character> seen, StringBuilder result) {
        if (index == str.length()) {
            return result.toString();
        }

        char current = str.charAt(index);
        if (!seen.contains(current)) {
            seen.add(current);
            result.append(current);
        }

        return removeDuplicates(str, index + 1, seen, result);
    }

    // 4. 遞迴產生所有子字串組合
    public static void generateAllSubstrings(String prefix, String remaining) {
        if (!prefix.isEmpty()) {
            System.out.println(prefix);
        }

        for (int i = 0; i < remaining.length(); i++) {
            generateAllSubstrings(prefix + remaining.charAt(i), remaining.substring(i + 1));
        }
    }
}

